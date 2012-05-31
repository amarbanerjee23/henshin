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

import org.eclipse.emf.henshin.interpreter.ApplicationMonitor;
import org.eclipse.emf.henshin.interpreter.Assignment;
import org.eclipse.emf.henshin.interpreter.Change;
import org.eclipse.emf.henshin.interpreter.EGraph;
import org.eclipse.emf.henshin.interpreter.Engine;
import org.eclipse.emf.henshin.interpreter.Match;
import org.eclipse.emf.henshin.interpreter.RuleApplication;
import org.eclipse.emf.henshin.model.Parameter;
import org.eclipse.emf.henshin.model.Rule;
import org.eclipse.emf.henshin.model.TransformationUnit;

/**
 * An implementation of an executable rule application. 
 * It must be initialized with an instance of {@link Rule}.
 */
public class RuleApplicationImpl extends AbstractApplicationImpl implements RuleApplication {

	// Used matches:
	protected Match partialMatch, completeMatch, resultMatch;
	
	// Used change object:
	protected Change change;
	
	// Flag indicating whether the rule application was executed:
	protected boolean isExecuted, isUndone;
	
	/**
	 * Default constructor.
	 * @param engine Engine to be used.
	 */
	public RuleApplicationImpl(Engine engine) {
		super(engine);
		isExecuted = false;
		isUndone = false;
	}

	/**
	 * Default constructor.
	 * @param engine Engine to be used.
	 * @param graph Object graph to be transformed.
	 * @param rule Rule to be applied.
	 * @param partialMatch Partial match or assignment.
	 */
	public RuleApplicationImpl(Engine engine, EGraph graph, Rule rule, Assignment partialMatch) {
		this(engine);
		setEGraph(graph);
		setRule(rule);
		setAssignment(partialMatch);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.interpreter.UnitApplication#execute(org.eclipse.emf.henshin.interpreter.ApplicationMonitor)
	 */
	@Override
	public boolean execute(ApplicationMonitor monitor) {
		if (unit==null) {
			throw new NullPointerException("No transformation unit set");
		}
		if (!isExecuted) {
			if (completeMatch==null) {
				completeMatch = engine.findMatches((Rule) unit, graph, partialMatch).iterator().next();
			}
			if (completeMatch==null) {
				if (monitor!=null) {
					monitor.notifyExecute(this, false);
				}
				return false;
			}
			resultMatch = new ResultMatchImpl((Rule) unit);
			change = engine.createChange((Rule) unit, graph, completeMatch, resultMatch);
			if (change==null) {
				if (monitor!=null) {
					monitor.notifyExecute(this, false);
				}
				return false;
			}
			change.applyAndReverse();
			isExecuted = true;
			if (monitor!=null) {
				monitor.notifyExecute(this, true);
			}
			return true;
		}
		if (monitor!=null) {
			monitor.notifyExecute(this, false);
		}
		return false;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.interpreter.UnitApplication#undo()
	 */
	@Override
	public boolean undo(ApplicationMonitor monitor) {
		if (isExecuted && !isUndone) {
			change.applyAndReverse();
			isUndone = true;
			if (monitor!=null) {
				monitor.notifyUndo(this, true);
			}
			return true;
		}
		if (monitor!=null) {
			monitor.notifyUndo(this, false);
		}
		return false;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.interpreter.UnitApplication#redo(org.eclipse.emf.henshin.interpreter.ApplicationMonitor)
	 */
	public boolean redo(ApplicationMonitor monitor) {
		if (isExecuted && isUndone) {
			change.applyAndReverse();
			isUndone = false;
			if (monitor!=null) {
				monitor.notifyRedo(this, true);
			}
			return true;
		}
		if (monitor!=null) {
			monitor.notifyUndo(this, false);
		}
		return false;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.interpreter.RuleApplication#getRule()
	 */
	@Override
	public Rule getRule() {
		return (Rule) unit;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.interpreter.RuleApplication#setRule(org.eclipse.emf.henshin.model.Rule)
	 */
	@Override
	public void setRule(Rule rule) {
		setUnit(rule);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.interpreter.impl.AbstractApplicationImpl#setUnit(org.eclipse.emf.henshin.model.TransformationUnit)
	 */
	@Override
	public void setUnit(TransformationUnit unit) {
		if (unit!=null && !(unit instanceof Rule)) {
			throw new NullPointerException("Transformation unit must be a rule");
		}
		if (this.unit!=unit){
			this.unit = unit;
			this.partialMatch = null;
			this.completeMatch = null;
			this.resultMatch = null;
			this.change = null;
			this.isExecuted = false;
			this.isUndone = false;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.interpreter.UnitApplication#getAssignment()
	 */
	@Override
	public Assignment getAssignment() {
		return partialMatch;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.interpreter.UnitApplication#setAssignment(org.eclipse.emf.henshin.interpreter.Assignment)
	 */
	@Override
	public void setAssignment(Assignment assignment) {
		if (assignment==null) {
			partialMatch = null;
		}
		else if (assignment instanceof Match) {
			partialMatch = (Match) assignment;
		}
		else {
			partialMatch = new MatchImpl(assignment);
		}
		this.completeMatch = null;
		this.resultMatch = null;
		this.change = null;
		this.isExecuted = false;
		this.isUndone = false;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.interpreter.UnitApplication#getParameterValue(java.lang.String)
	 */
	@Override
	public Object getResultParameterValue(String paramName) {
		if (unit==null) {
			throw new RuntimeException("Rule not set");
		}
		Parameter param = unit.getParameterByName(paramName);
		if (param==null) {
			throw new RuntimeException("No parameter \"" + paramName + "\" in rule \"" + unit.getName() + "\" found" );
		}
		if (resultMatch!=null) {
			return resultMatch.getParameterValue(param);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.interpreter.UnitApplication#setParameterValue(java.lang.String, java.lang.Object)
	 */
	@Override
	public void setParameterValue(String paramName, Object value) {
		if (unit==null) {
			throw new RuntimeException("Rule not set");
		}
		Parameter param = unit.getParameterByName(paramName);
		if (param==null) {
			throw new RuntimeException("No parameter \"" + paramName + "\" in rule \"" + unit.getName() + "\" found" );
		}
		if (partialMatch==null) {
			partialMatch = new MatchImpl((Rule) unit);
		}
		partialMatch.setParameterValue(param, value);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.interpreter.RuleApplication#getPartialMatch()
	 */
	@Override
	public Match getPartialMatch() {
		return partialMatch;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.interpreter.RuleApplication#setPartialMatch(org.eclipse.emf.henshin.interpreter.Match)
	 */
	@Override
	public void setPartialMatch(Match partialMatch) {
		this.partialMatch = partialMatch; 
		this.completeMatch = null;
		this.resultMatch = null;
		this.change = null;
		this.isExecuted = false;
		this.isUndone = false;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.interpreter.RuleApplication#getCompleteMatch()
	 */
	@Override
	public Match getCompleteMatch() {
		return completeMatch;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.interpreter.RuleApplication#setCompleteMatch(org.eclipse.emf.henshin.interpreter.Match)
	 */
	@Override
	public void setCompleteMatch(Match completeMatch) {
		// don't change the partial match
		this.completeMatch = completeMatch;
		this.resultMatch = null;
		this.change = null;
		this.isExecuted = false;
		this.isUndone = false;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.interpreter.RuleApplication#getResultMatch()
	 */
	@Override
	public Match getResultMatch() {
		return resultMatch;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.interpreter.UnitApplication#getResultAssignment()
	 */
	@Override
	public Assignment getResultAssignment() {
		return resultMatch;
	}
	
}