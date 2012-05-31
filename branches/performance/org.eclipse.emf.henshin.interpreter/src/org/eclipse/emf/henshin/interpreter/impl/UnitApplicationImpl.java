/*******************************************************************************
 * Copyright (c) 2010 CWI Amsterdam, Technical University Berlin,
 * Philipps-University Marburg and others. All rights reserved. This program and
 * the accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Technical University Berlin - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.henshin.interpreter.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import org.eclipse.emf.henshin.interpreter.ApplicationMonitor;
import org.eclipse.emf.henshin.interpreter.Assignment;
import org.eclipse.emf.henshin.interpreter.EGraph;
import org.eclipse.emf.henshin.interpreter.Engine;
import org.eclipse.emf.henshin.interpreter.InterpreterFactory;
import org.eclipse.emf.henshin.interpreter.RuleApplication;
import org.eclipse.emf.henshin.interpreter.UnitApplication;
import org.eclipse.emf.henshin.model.ConditionalUnit;
import org.eclipse.emf.henshin.model.HenshinPackage;
import org.eclipse.emf.henshin.model.IndependentUnit;
import org.eclipse.emf.henshin.model.LoopUnit;
import org.eclipse.emf.henshin.model.Parameter;
import org.eclipse.emf.henshin.model.ParameterMapping;
import org.eclipse.emf.henshin.model.PriorityUnit;
import org.eclipse.emf.henshin.model.Rule;
import org.eclipse.emf.henshin.model.SequentialUnit;
import org.eclipse.emf.henshin.model.TransformationUnit;

/**
 * Default {@link UnitApplication} implementation.
 * 
 * @author Enrico Biermann, Gregor Bonifer, Christian Krause
 */
public class UnitApplicationImpl extends AbstractApplicationImpl {
	
	// Parameter assignments:
	protected Assignment assignment, resultAssignment;
	
	// Applied and undone rules:
	protected final Stack<RuleApplication> appliedRules, undoneRules;
	
	/**
	 * Default constructor.
	 * @param engine Engine to be used.
	 */
	public UnitApplicationImpl(Engine engine) {
		super(engine);
		appliedRules = new Stack<RuleApplication>();
		undoneRules = new Stack<RuleApplication>();
	}

	/**
	 * Default constructor.
	 * @param engine Engine to be used.
	 * @param unit Unit to be used.
	 */
	protected UnitApplicationImpl(Engine engine, EGraph graph, TransformationUnit unit, Assignment assignment) {
		this(engine);
		setEGraph(graph);
		setUnit(unit);
		setAssignment(assignment);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.interpreter.UnitApplication#execute(org.eclipse.emf.henshin.interpreter.ApplicationMonitor)
	 */
	@Override
	public boolean execute(ApplicationMonitor monitor) {
		if (monitor==null) {
			monitor = InterpreterFactory.INSTANCE.createApplicationMonitor();
		}
		resultAssignment = (assignment!=null) ? new AssignmentImpl(assignment) : new AssignmentImpl(unit);
		return doExecute(monitor);
	}

	/*
	 * Do execute a unit. Assumes that the monitor and the result assignment is set.
	 */
	protected boolean doExecute(ApplicationMonitor monitor) {
		if (unit.isActivated()) {
			switch (unit.eClass().getClassifierID()) {
				case HenshinPackage.RULE:
					return executeRule(monitor);
				case HenshinPackage.INDEPENDENT_UNIT:
					return executeIndependentUnit(monitor);
				case HenshinPackage.SEQUENTIAL_UNIT:
					return executeSequentialUnit(monitor);
				case HenshinPackage.CONDITIONAL_UNIT:
					return executeConditionalUnit(monitor);
				case HenshinPackage.PRIORITY_UNIT:
					return executePriorityUnit(monitor);
				case HenshinPackage.LOOP_UNIT:
					return executeLoopUnit(monitor);
				default:
					return false;
			}
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.interpreter.UnitApplication#undo(org.eclipse.emf.henshin.interpreter.ApplicationMonitor)
	 */
	@Override
	public boolean undo(ApplicationMonitor monitor) {
		if (appliedRules.isEmpty()) {
			return true;
		}
		if (monitor==null) {
			monitor = InterpreterFactory.INSTANCE.createApplicationMonitor();
		}
		while (!appliedRules.isEmpty()) {
			RuleApplication ruleApplication = appliedRules.pop();
			ruleApplication.undo(monitor);
			undoneRules.push(ruleApplication);
		}
		Assignment dummy = assignment;
		assignment = resultAssignment;
		resultAssignment = dummy;
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.interpreter.UnitApplication#redo(org.eclipse.emf.henshin.interpreter.ApplicationMonitor)
	 */
	@Override
	public boolean redo(ApplicationMonitor monitor) {
		if (undoneRules.isEmpty()) {
			return true;
		}
		if (monitor==null) {
			monitor = InterpreterFactory.INSTANCE.createApplicationMonitor();
		}
		while (!undoneRules.isEmpty()) {
			RuleApplication ruleApplication = undoneRules.pop();
			ruleApplication.redo(monitor);
			appliedRules.push(ruleApplication);
		}
		Assignment dummy = assignment;
		assignment = resultAssignment;
		resultAssignment = dummy;
		return true;
	}
	
	/*
	 * Execute a Rule.
	 */
	private boolean executeRule(ApplicationMonitor monitor) {
		Rule rule = (Rule) unit;
		RuleApplication ruleApp = new RuleApplicationImpl(engine, graph, rule, resultAssignment);
		if (ruleApp.execute(monitor)) {
			resultAssignment = new AssignmentImpl(ruleApp.getResultMatch());
			appliedRules.push(ruleApp);
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Execute an IndependentUnit.
	 */
	protected boolean executeIndependentUnit(ApplicationMonitor monitor) {
		IndependentUnit indepUnit = (IndependentUnit) unit;
		List<TransformationUnit> subUnits = new ArrayList<TransformationUnit>(indepUnit.getSubUnits());
		while (!subUnits.isEmpty()) {
			if (monitor.isCanceled()) {
				if (monitor.isUndo()) undo(monitor);
				return false;
			}
			int index = new Random().nextInt(subUnits.size());
			UnitApplicationImpl unitApp = createApplicationFor(subUnits.remove(index));
			if (unitApp.execute(monitor)) {
				updateParameterValues(unitApp);
				appliedRules.addAll(unitApp.appliedRules);
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Execute a SequentialUnit.
	 */
	protected boolean executeSequentialUnit(ApplicationMonitor monitor) {
		SequentialUnit seqUnit = (SequentialUnit) unit;
		for (TransformationUnit subUnit : seqUnit.getSubUnits()) {
			if (monitor.isCanceled()) {
				if (monitor.isUndo()) undo(monitor);
				return false;
			}
			UnitApplicationImpl unitApp = createApplicationFor(subUnit);
			if (unitApp.execute(monitor)) {
				updateParameterValues(unitApp);
				appliedRules.addAll(unitApp.appliedRules);
			} else {
				if (seqUnit.isStrict()) {
					if (seqUnit.isRollback()) {
						undo(monitor);
					}
					return false;
				}
			}
		}
		return true;
	}
	
	/*
	 * Execute a ConditionalUnit.
	 */
	protected boolean executeConditionalUnit(ApplicationMonitor monitor) {
		boolean success = false;
		ConditionalUnit condUnit = (ConditionalUnit) unit;
		UnitApplicationImpl ifUnitApp = createApplicationFor(condUnit.getIf());
		if (ifUnitApp.execute(monitor)) {
			updateParameterValues(ifUnitApp);
			appliedRules.addAll(ifUnitApp.appliedRules);
			UnitApplicationImpl thenUnitApp = createApplicationFor(condUnit.getThen());
			success = thenUnitApp.execute(monitor);
			if (success) {
				updateParameterValues(thenUnitApp);
			}
			appliedRules.addAll(thenUnitApp.appliedRules);
		} else {
			if (condUnit.getElse() != null) {
				UnitApplicationImpl elseUnitApp = createApplicationFor(condUnit.getElse());
				success = elseUnitApp.execute(monitor);
				if (success) {
					updateParameterValues(elseUnitApp);
				}
				appliedRules.addAll(elseUnitApp.appliedRules);
			}
		}
		if (monitor.isCanceled()) {
			if (monitor.isUndo()) undo(monitor);
			return false;
		}
		if (!success) {
			undo(monitor);
		}
		return success;
	}
	
	/*
	 * Execute a PriorityUnit.
	 */
	protected boolean executePriorityUnit(ApplicationMonitor monitor) {
		PriorityUnit priUnit = (PriorityUnit) unit;
		for (TransformationUnit subUnit : priUnit.getSubUnits()) {
			if (monitor.isCanceled()) {
				if (monitor.isUndo()) undo(monitor);				
				return false;
			}
			UnitApplicationImpl unitApp = createApplicationFor(subUnit);
			if (unitApp.execute(monitor)) {
				updateParameterValues(unitApp);
				appliedRules.addAll(unitApp.appliedRules);
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Execute a LoopUnit.
	 */
	protected boolean executeLoopUnit(ApplicationMonitor monitor) {
		LoopUnit loopUnit = (LoopUnit) unit;
		while (true) {
			if (monitor.isCanceled()) {
				if (monitor.isUndo()) undo(monitor);
				return false;
			}
			UnitApplicationImpl unitApp = createApplicationFor(loopUnit.getSubUnit());
			if (unitApp.execute(monitor)) {
				updateParameterValues(unitApp);
				appliedRules.addAll(unitApp.appliedRules);
			} else {
				return true;
			}
		}
	}
	
	/*
	 * Create an ApplicationUnit for a given TransformationUnit.
	 */
	protected UnitApplicationImpl createApplicationFor(TransformationUnit subUnit) {
		Assignment assign = new AssignmentImpl(subUnit);
		if (resultAssignment!=null) {
			for (ParameterMapping mapping : unit.getParameterMappings()) {
				Parameter source = mapping.getSource();
				Parameter target = mapping.getTarget();
				if (target.getUnit()==subUnit) {
					assign.setParameterValue(target, resultAssignment.getParameterValue(source));
				}
			}
		}
		return new UnitApplicationImpl(engine, graph, subUnit, assign);
	}
	
	/*
	 * Update the parameter values.
	 */
	protected void updateParameterValues(UnitApplicationImpl subUnitApp) {
		if (resultAssignment==null) {
			resultAssignment = new AssignmentImpl(unit);
		}
		for (ParameterMapping mapping : unit.getParameterMappings()) {
			Parameter source = mapping.getSource();
			Parameter target = mapping.getTarget();
			if (source.getUnit()==subUnitApp.getUnit()) {
				Parameter param = subUnitApp.getUnit().getParameterByName(source.getName());
				if (param!=null) {
					resultAssignment.setParameterValue(target, 
							subUnitApp.getResultAssignment().getParameterValue(param));
				}
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.interpreter.UnitApplication#getAssignment()
	 */
	@Override
	public Assignment getAssignment() {
		return assignment;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.interpreter.UnitApplication#setAssignment(org.eclipse.emf.henshin.interpreter.Assignment)
	 */
	@Override
	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.interpreter.UnitApplication#getResultAssignment()
	 */
	@Override
	public Assignment getResultAssignment() {
		return resultAssignment;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.interpreter.UnitApplication#getParameterValue(java.lang.String)
	 */
	@Override
	public Object getResultParameterValue(String paramName) {
		if (unit==null) {
			throw new RuntimeException("Transformation unit not set");
		}
		Parameter param = unit.getParameterByName(paramName);
		if (param==null) {
			throw new RuntimeException("No parameter \"" + paramName + "\" in transformation unit \"" + unit.getName() + "\" found" );
		}
		if (resultAssignment!=null) {
			return resultAssignment.getParameterValue(param);
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
			throw new RuntimeException("Transformation unit not set");
		}
		Parameter param = unit.getParameterByName(paramName);
		if (param==null) {
			throw new RuntimeException("No parameter \"" + paramName + "\" in transformation unit \"" + unit.getName() + "\" found" );
		}
		if (assignment==null) {
			assignment = InterpreterFactory.INSTANCE.createAssignment(unit);
		}
		assignment.setParameterValue(param, value);
	}

}