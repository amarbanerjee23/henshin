/*******************************************************************************
 * Copyright (c) 2010 CWI Amsterdam, Technical University Berlin, 
 * Philipps-University Marburg and others. All rights reserved. 
 * This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Technical University Berlin - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.henshin.interpreter.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.henshin.interpreter.Change;
import org.eclipse.emf.henshin.interpreter.EGraph;
import org.eclipse.emf.henshin.interpreter.Engine;
import org.eclipse.emf.henshin.interpreter.InterpreterFactory;
import org.eclipse.emf.henshin.interpreter.Match;
import org.eclipse.emf.henshin.interpreter.impl.ChangeImpl.AttributeChangeImpl;
import org.eclipse.emf.henshin.interpreter.impl.ChangeImpl.ComplexChangeImpl;
import org.eclipse.emf.henshin.interpreter.impl.ChangeImpl.ObjectChangeImpl;
import org.eclipse.emf.henshin.interpreter.impl.ChangeImpl.ReferenceChangeImpl;
import org.eclipse.emf.henshin.interpreter.info.ConditionInfo;
import org.eclipse.emf.henshin.interpreter.info.RuleChangeInfo;
import org.eclipse.emf.henshin.interpreter.info.RuleInfo;
import org.eclipse.emf.henshin.interpreter.info.VariableInfo;
import org.eclipse.emf.henshin.interpreter.matching.conditions.AndFormula;
import org.eclipse.emf.henshin.interpreter.matching.conditions.ApplicationCondition;
import org.eclipse.emf.henshin.interpreter.matching.conditions.AttributeConditionHandler;
import org.eclipse.emf.henshin.interpreter.matching.conditions.IFormula;
import org.eclipse.emf.henshin.interpreter.matching.conditions.NotFormula;
import org.eclipse.emf.henshin.interpreter.matching.conditions.OrFormula;
import org.eclipse.emf.henshin.interpreter.matching.conditions.TrueFormula;
import org.eclipse.emf.henshin.interpreter.matching.conditions.XorFormula;
import org.eclipse.emf.henshin.interpreter.matching.constraints.DomainSlot;
import org.eclipse.emf.henshin.interpreter.matching.constraints.Solution;
import org.eclipse.emf.henshin.interpreter.matching.constraints.SolutionFinder;
import org.eclipse.emf.henshin.interpreter.matching.constraints.Variable;
import org.eclipse.emf.henshin.model.And;
import org.eclipse.emf.henshin.model.Attribute;
import org.eclipse.emf.henshin.model.Edge;
import org.eclipse.emf.henshin.model.Formula;
import org.eclipse.emf.henshin.model.Graph;
import org.eclipse.emf.henshin.model.Mapping;
import org.eclipse.emf.henshin.model.NestedCondition;
import org.eclipse.emf.henshin.model.Node;
import org.eclipse.emf.henshin.model.Not;
import org.eclipse.emf.henshin.model.Or;
import org.eclipse.emf.henshin.model.Parameter;
import org.eclipse.emf.henshin.model.Rule;
import org.eclipse.emf.henshin.model.Xor;
import org.eclipse.emf.henshin.model.util.HenshinMappingUtil;

/**
 * The default implementation of {@link Engine}.
 * 
 * @author Christian Krause, Gregor Bonifer, Enrico Biermann
 */
public class EngineImpl implements Engine {

	// Options to be used:
	protected final Map<String,Object> options;

	// Script engine used to compute Java expressions in attributes:
	protected final ScriptEngine scriptEngine;

	// Cached information lookup map for each rule:
	protected final Map<Rule,RuleInfo> ruleInfos;

	// Cached graph options:
	protected final Map<Graph,Map<String,Object>> graphOptions;

	/**
	 * Default constructor.
	 */
	public EngineImpl() {
		ruleInfos = new HashMap<Rule, RuleInfo>();
		graphOptions = new HashMap<Graph,Map<String,Object>>();
		options = new EngineOptions();
		scriptEngine = new ScriptEngineManager().getEngineByName("JavaScript");
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.interpreter.Engine#createChange(org.eclipse.emf.henshin.model.Rule, org.eclipse.emf.henshin.interpreter.EGraph, org.eclipse.emf.henshin.interpreter.Match, org.eclipse.emf.henshin.interpreter.Match)
	 */
	@Override
	public Change createChange(Rule rule, EGraph graph, Match completeMatch, Match resultMatch) {

		// We need a result match:
		if (resultMatch==null) {
			resultMatch = new ResultMatchImpl(rule);
		}

		// Get the rule change info:
		RuleChangeInfo ruleChange = getRuleInfo(rule).getChangeInfo();

		// Create the object change info:
		ComplexChangeImpl complexChange = new ComplexChangeImpl(graph);
		List<Change> changes= complexChange.getChanges();

		for (Parameter param : rule.getParameters()) {
			Object value = completeMatch.getParameterValue(param);
			resultMatch.setParameterValue(param, value);
			scriptEngine.put(param.getName(), value);
		}

		// Created objects:
		for (Node node : ruleChange.getCreatedNodes()) {
			EClass type = node.getType();
			EObject createdObject = type.getEPackage().getEFactoryInstance().create(type);
			changes.add(new ObjectChangeImpl(graph, createdObject, true));
			resultMatch.setNodeTarget(node, createdObject);
		}

		// Deleted objects:
		for (Node node : ruleChange.getDeletedNodes()) {			
			EObject deletedObject = completeMatch.getNodeTarget(node);
			changes.add(new ObjectChangeImpl(graph, deletedObject, false));
			// TODO: Shouldn't we check the rule options?
			if (!rule.isCheckDangling()) {
				// TODO: What about outgoing edges?
				Collection<Setting> removedEdges = graph.getCrossReferenceAdapter().getInverseReferences(deletedObject);
				for (Setting edge : removedEdges) {
					changes.add(new ReferenceChangeImpl(graph, 
							edge.getEObject(), deletedObject, 
							(EReference) edge.getEStructuralFeature(), false));
				}
			}
		}

		// Preserved objects:
		for (Node node : ruleChange.getPreservedNodes()) {
			Node lhsNode = HenshinMappingUtil.getRemoteNode(rule.getMappings(), node);
			resultMatch.setNodeTarget(node, completeMatch.getNodeTarget(lhsNode));
		}

		// Deleted edges:
		for (Edge edge : ruleChange.getDeletedEdges()) {
			changes.add(new ReferenceChangeImpl(graph,
					completeMatch.getNodeTarget(edge.getSource()), 
					completeMatch.getNodeTarget(edge.getTarget()), 
					edge.getType(),
					false));
		}

		// Created edges:
		for (Edge edge : ruleChange.getCreatedEdges()) {
			changes.add(new ReferenceChangeImpl(graph,
					resultMatch.getNodeTarget(edge.getSource()),
					resultMatch.getNodeTarget(edge.getTarget()), 
					edge.getType(), 
					true));
		}

		// Attribute changes:
		for (Attribute attribute : ruleChange.getAttributeChanges()) {
			EObject object = resultMatch.getNodeTarget(attribute.getNode());
			String valueString = evalAttributeExpression(attribute);
			Object value = (valueString!=null) ? EcoreUtil.createFromString(attribute.getType().getEAttributeType(), valueString) : null;
			changes.add(new AttributeChangeImpl(graph, object, attribute.getType(), value));
		}

		return complexChange;

	}

	/*
	 * Evaluates the given JavaScript-Expression.
	 */
	protected String evalAttributeExpression(Attribute attribute) {

		/* If the attribute's type is an Enumeration, its value shall be rather
		 * checked against the Ecore model than against the JavaScript machine. */
		if ((attribute.getType()!=null) && (attribute.getType().getEType() instanceof EEnum)) {
			EEnum eenum = (EEnum) attribute.getType().getEType();
			EEnumLiteral eelit = eenum.getEEnumLiteral(attribute.getValue());
			if (eelit!=null) {
				return eelit.toString();
			}
		}

		// Try to evaluate the expression:
		try {
			Object value = scriptEngine.eval(attribute.getValue());
			if (value==null) {
				return null;
			}
			String valueString = value.toString();

			// Workaround for Double conversion:
			// Javascript evaluated numbers are always shown in floating point
			// notation. This leads to a NumberFormatException if the EAttribute
			// has EDataType EInt or ELong.
			switch (attribute.getType().getEAttributeType().getClassifierID()) {
			case EcorePackage.EBYTE:
			case EcorePackage.EINT:
			case EcorePackage.ELONG:
				int index = valueString.indexOf('.');
				if (index==0) {
					valueString = "0";
				} else if (index>0) {
					valueString = valueString.substring(0, index);
				}
				break;
			}
			return valueString;
		}
		catch (ScriptException e) {
			throw new RuntimeException(e.getMessage());
		}

	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.interpreter.Engine#findMatches(org.eclipse.emf.henshin.model.Rule, org.eclipse.emf.henshin.interpreter.EGraph, org.eclipse.emf.henshin.interpreter.Match)
	 */
	@Override
	public Iterable<Match> findMatches(Rule rule, EGraph graph, Match partialMatch) {
		if (rule==null || graph==null) {
			throw new NullPointerException("Rule and graph must not be null");
		}
		if (partialMatch==null) {
			partialMatch = new MatchImpl(rule);
		}
		return new MatchGenerator(rule, graph, partialMatch);
	}

	/**
	 * Match generator. Delegates to {@link MatchFinder}.
	 */
	private final class MatchGenerator implements Iterable<Match> {

		// Rule to be matched:
		private final Rule rule; 

		// Object graph:
		private final EGraph graph;

		// A partial match:
		private final Match partialMatch;

		/**
		 * Default constructor.
		 * @param rule Rule to be matched.
		 * @param graph Object graph.
		 * @param partialMatch Partial match.
		 */
		public MatchGenerator(Rule rule, EGraph graph, Match partialMatch) {
			this.rule = rule;
			this.graph = graph;
			this.partialMatch = partialMatch;
		}

		/*
		 * (non-Javadoc)
		 * @see java.lang.Iterable#iterator()
		 */
		@Override
		public Iterator<Match> iterator() {
			return new MatchFinder(rule, graph, partialMatch, new HashSet<EObject>());
		}

	}

	/**
	 * Match finder. Uses {@link SolutionFinder} to find matches.
	 */
	private final class MatchFinder implements Iterator<Match> {

		// The next match:
		private Match nextMatch;

		private final EGraph graph;

		// Solution finder to be used
		private final SolutionFinder solutionFinder;

		// Cached rule info:
		private final RuleInfo ruleInfo;

		// Used objects:
		private final Set<EObject> usedObjects;

		/**
		 * Default constructor.
		 * @param rule Rule to be matched.
		 * @param graph Object graph.
		 * @param partialMatch A partial match.
		 * @param usedObjects Used objects (for ensuring injectivity).
		 */
		public MatchFinder(Rule rule, EGraph graph, Match partialMatch, Set<EObject> usedObjects) {
			this.graph = graph;
			this.solutionFinder = createSolutionFinder(rule, graph, partialMatch, usedObjects);
			this.ruleInfo = getRuleInfo(rule);
			this.usedObjects = usedObjects;
			computeNextMatch(); // compute the first match
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			return (nextMatch!=null);
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Iterator#next()
		 */
		@Override
		public Match next() {
			Match result = nextMatch;
			computeNextMatch();
			return result;
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.Iterator#remove()
		 */
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		/*
		 * Compute the next match.
		 */
		private void computeNextMatch() {

			// We definitely need a solution finder:
			if (solutionFinder==null) {
				nextMatch = null;
				return;
			}

			// Find the next solution:
			Solution solution = solutionFinder.getNextSolution();
			if (solution==null) {
				nextMatch = null;
				return;
			}

			// Create the new match:
			nextMatch = InterpreterFactory.INSTANCE.createMatch(ruleInfo.getRule());
			Map<Variable, EObject> objectMatch = solution.getObjectMatches();
			Map<Node, Variable> node2var = ruleInfo.getVariableInfo().getNode2variable();
			for (Node node : ruleInfo.getRule().getLhs().getNodes()) {
				nextMatch.setNodeTarget(node, objectMatch.get(node2var.get(node)));
			}
			for (Entry<String,Object> entry : solution.getParameterValues().entrySet()) {
				Parameter param = nextMatch.getUnit().getParameterByName(entry.getKey());
				if (param!=null) {
					nextMatch.setParameterValue(param, entry.getValue());
				}
			}

			// Now handle the multi-rules:
			addMultiMatches(ruleInfo.getRule(), nextMatch, usedObjects);

		}

		/*
		 * Add the multi-matches to a given kernel-match.
		 */
		private void addMultiMatches(Rule kernelRule, Match kernelMatch, Set<EObject> usedObjects) {
			for (Rule multiRule : kernelRule.getMultiRules()) {

				// The used kernel objects:
				Set<EObject> usedKernelObjects = new HashSet<EObject>(usedObjects);
				usedKernelObjects.addAll(kernelMatch.getAllNodeTargets());

				// Create the partial multi match:
				Match partialMultiMatch = new MatchImpl(multiRule);
				for (Parameter param : kernelRule.getParameters()) {
					Parameter multiParam = multiRule.getParameterByName(param.getName());
					if (multiParam!=null) {
						partialMultiMatch.setParameterValue(multiParam, kernelMatch.getParameterValue(param));
					}
				}
				for (Mapping mapping : multiRule.getMultiMappings()) {
					partialMultiMatch.setNodeTarget(mapping.getImage(),
							kernelMatch.getNodeTarget(mapping.getOrigin()));
				}

				// Find all multi-matches:
				MatchFinder matchFinder = new MatchFinder(multiRule, graph, partialMultiMatch, usedKernelObjects);
				List<Match> multiMatches = new ArrayList<Match>();
				while (matchFinder.hasNext()) {
					multiMatches.add(matchFinder.next());
				}
				for (Match match : multiMatches) {
					Set<EObject> usedMultiObjects = new HashSet<EObject>(usedKernelObjects);
					usedMultiObjects.addAll(match.getAllNodeTargets());
					addMultiMatches(multiRule, match, usedMultiObjects);
				}
				kernelMatch.getNestedMatches(multiRule).addAll(multiMatches);

			}
		}

		/*
		 * Create a solution finder.
		 */
		protected SolutionFinder createSolutionFinder(Rule rule, EGraph graph, Match partialMatch, Set<EObject> usedObjects) {

			// Get the required info objects:
			RuleInfo ruleInfo = getRuleInfo(rule);
			ConditionInfo conditionInfo = ruleInfo.getConditionInfo();
			VariableInfo variableInfo = ruleInfo.getVariableInfo();

			// Evaluates attribute conditions of the rule:
			AttributeConditionHandler conditionHandler = new AttributeConditionHandler(
					conditionInfo.getConditionParameters(), scriptEngine);

			/* usedObjects ensures injective matching by removing already *
			 * matched objects from other DomainSlots                     */

			/* Create a domain map where all variables are mapped to slots. *
			 * Different variables may share one domain slot, if there is a *
			 * mapping between the nodes of the variables.                  */

			Map<Variable,DomainSlot> domainMap = new HashMap<Variable,DomainSlot>();
			for (Variable mainVariable : variableInfo.getMainVariables()) {
				Node node = variableInfo.getVariableForNode(mainVariable);
				DomainSlot domainSlot = new DomainSlot(conditionHandler, usedObjects, getGraphOptions(node.getGraph()));

				// Fix this slot?
				EObject target = partialMatch.getNodeTarget(node);
				if (target!=null) {
					domainSlot.fixInstantiation(target);
				}

				// Add the dependent variables to the domain map:
				for (Variable dependendVariable : variableInfo.getDependendVariables(mainVariable)) {
					domainMap.put(dependendVariable, domainSlot);
				}
			}

			// Check if any of the conditions failed:
			for (Parameter param : rule.getParameters()) {
				Object value = partialMatch.getParameterValue(param);
				if (value!=null && !conditionHandler.setParameter(param.getName(), value)) {
					return null;
				}
			}

			// Now initialize the match finder:
			SolutionFinder solutionFinder = new SolutionFinder(graph, domainMap, conditionHandler);
			Map<Graph,List<Variable>> graphMap = ruleInfo.getVariableInfo().getGraph2variables();
			solutionFinder.setVariables(graphMap.get(ruleInfo.getRule().getLhs()));
			solutionFinder.setFormula(initFormula(ruleInfo.getRule().getLhs().getFormula(), graph, graphMap, domainMap));
			return solutionFinder;

		}

		/*
		 * Initialize a formula.
		 */
		private IFormula initFormula(Formula formula, EGraph graph, Map<Graph,List<Variable>> graphMap, Map<Variable,DomainSlot> domainMap) {
			if (formula instanceof And) {
				And and = (And) formula;
				IFormula left = initFormula(and.getLeft(), graph, graphMap, domainMap);
				IFormula right = initFormula(and.getRight(), graph, graphMap, domainMap);
				return new AndFormula(left, right);
			}
			else if (formula instanceof Or) {
				Or or = (Or) formula;
				IFormula left = initFormula(or.getLeft(), graph, graphMap, domainMap);
				IFormula right = initFormula(or.getRight(), graph, graphMap, domainMap);
				return new OrFormula(left, right);
			}
			else if (formula instanceof Xor) {
				Xor xor = (Xor) formula;
				IFormula left = initFormula(xor.getLeft(), graph, graphMap, domainMap);
				IFormula right = initFormula(xor.getRight(), graph, graphMap, domainMap);
				return new XorFormula(left, right);
			}
			else if (formula instanceof Not) {
				Not not = (Not) formula;
				IFormula child = initFormula(not.getChild(), graph, graphMap, domainMap);
				return new NotFormula(child);
			}
			else if (formula instanceof NestedCondition) {
				NestedCondition nc = (NestedCondition) formula;
				return initApplicationCondition(nc, graph, graphMap, domainMap);
			}
			return new TrueFormula();
		}

		/*
		 * Initialize an application condition.
		 */
		private ApplicationCondition initApplicationCondition(NestedCondition nc, EGraph graph, Map<Graph, List<Variable>> graphMap, Map<Variable,DomainSlot> domainMap) {
			ApplicationCondition ac = new ApplicationCondition(graph, domainMap, false);
			ac.setVariables(graphMap.get(nc.getConclusion()));
			ac.setFormula(initFormula(nc.getConclusion().getFormula(), graph, graphMap, domainMap));
			return ac;
		}

	}

	/*
	 * Get the cached rule info for a given rule.
	 */
	protected RuleInfo getRuleInfo(Rule rule) {
		RuleInfo ruleInfo = ruleInfos.get(rule);
		if (ruleInfo == null) {
			ruleInfo = new RuleInfo(rule, scriptEngine);
			ruleInfos.put(rule, ruleInfo);
		}
		return ruleInfo;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.interpreter.Engine#getOptions()
	 */
	@Override
	public Map<String,Object> getOptions() {
		return options;
	}

	/*
	 * Get the options for a specific rule graph.
	 * The graph should be either the LHS or a nested condition.
	 */
	protected Map<String,Object> getGraphOptions(Graph graph) {

		// Already defined?
		Map<String,Object> options = graphOptions.get(graph);
		if (options==null) {

			// Use the base options:
			options = new HashMap<String,Object>(this.options);
			Rule rule = graph.getContainerRule();

			// Refine...
			if (!options.containsKey(OPTION_DETERMINISTIC)) {
				options.put(OPTION_DETERMINISTIC, Boolean.TRUE);
			}
			if (!options.containsKey(OPTION_INJECTIVE_MATCHING)) {
				options.put(OPTION_INJECTIVE_MATCHING, rule.isInjectiveMatching());
			}
			if (!options.containsKey(OPTION_CHECK_DANGLING)) {
				options.put(OPTION_CHECK_DANGLING, rule.isCheckDangling());
			}

			// Always use default values for nested conditions:
			if (graph!=rule.getLhs()) {
				options.put(OPTION_DETERMINISTIC, Boolean.TRUE);
				options.put(OPTION_INJECTIVE_MATCHING, Boolean.TRUE);
				options.put(OPTION_CHECK_DANGLING, Boolean.FALSE);
			}
			graphOptions.put(graph, options);

		}
		return options;

	}

	/**
	 * An options map which clears cached rule options.
	 * @see #getOptions()
	 */
	private class EngineOptions extends HashMap<String,Object> {
		private static final long serialVersionUID = 1L;

		/*
		 * (non-Javadoc)
		 * @see java.util.HashMap#put(java.lang.String, java.lang.Object)
		 */
		@Override
		public Object put(String key, Object value) {
			Object result = super.put(key, value);
			graphOptions.clear();
			return result;
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.HashMap#putAll(java.util.Map)
		 */
		@Override
		public void putAll(Map<? extends String,? extends Object> map) {
			super.putAll(map);
			graphOptions.clear();
		}

		/*
		 * (non-Javadoc)
		 * @see java.util.HashMap#clear()
		 */
		@Override
		public void clear() {
			super.clear();
			graphOptions.clear();
		}

	};

}