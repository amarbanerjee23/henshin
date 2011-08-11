/*******************************************************************************
 * Copyright (c) 2010 CWI Amsterdam, Technical University Berlin, 
 * Philipps-University Marburg and others. All rights reserved. 
 * This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     CWI Amsterdam - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.henshin.statespace.explorer.edit;

import java.util.List;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.EllipseAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.henshin.statespace.State;
import org.eclipse.emf.henshin.statespace.StateSpaceManager;
import org.eclipse.emf.henshin.statespace.explorer.commands.ExploreStatesCommand;
import org.eclipse.emf.henshin.statespace.impl.StateSpacePackageImpl;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;

/**
 * State diagram edit part.
 * @author Christian Krause
 */
public class StateEditPart extends AbstractGraphicalEditPart implements NodeEditPart, Adapter {
	
	// Size of state figures.
	public final static int SIZE = 20;
	
	// Default color to be used.
	public final static Color COLOR_DEFAULT = RGB2Color(State.COLOR_DEFAULT);

	// Color to be used for initial states.
	public final static Color COLOR_INITIAL = RGB2Color(State.COLOR_INITIAL);

	// Color to be used for terminal states.
	public final static Color COLOR_TERMINAL = RGB2Color(State.COLOR_TERMINAL);
	
	// Color to be used for open states.
	public final static Color COLOR_OPEN = RGB2Color(State.COLOR_OPEN);

	// Connection anchor:
	private ConnectionAnchor anchor;
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#activate()
	 */
	@Override
	public void activate() {
		if (!isActive()) {
			super.activate();
			getState().eAdapters().add(this);
		}
	}
	
	/* 
	 * (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new StateComponentEditPolicy());
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	@Override
	protected IFigure createFigure() {
		return new StateFigure();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#deactivate()
	 */
	@Override
	public void deactivate() {
		if (isActive()) {
			getState().eAdapters().remove(this);
			super.deactivate();
		}
	}
	
	/**
	 * Get the state that this edit part belongs to.
	 * @return State.
	 */
	public State getState() {
		return (State) getModel();
	}
	
	/**
	 * Get the used state space manager.
	 * @return State space manager.
	 */
	public StateSpaceManager getStateSpaceManager() {
		return ((StateSpaceEditPart) getParent()).getStateSpaceManager();
	}
	
	/*
	 * Get the connection anchor.
	 */
	protected ConnectionAnchor getConnectionAnchor() {
		if (anchor == null) {
			anchor = new EllipseAnchor(getFigure());
		}
		return anchor;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getModelSourceConnections()
	 */
	@Override
	protected List<?> getModelSourceConnections() {
		return getState().getOutgoing();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getModelTargetConnections()
	 */
	@Override
	protected List<?> getModelTargetConnections() {
		return getState().getIncoming();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.NodeEditPart#getSourceConnectionAnchor(org.eclipse.gef.ConnectionEditPart)
	 */
	public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
		return getConnectionAnchor();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.NodeEditPart#getSourceConnectionAnchor(org.eclipse.gef.Request)
	 */
	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		return getConnectionAnchor();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.NodeEditPart#getTargetConnectionAnchor(org.eclipse.gef.ConnectionEditPart)
	 */
	public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
		return getConnectionAnchor();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.NodeEditPart#getTargetConnectionAnchor(org.eclipse.gef.Request)
	 */
	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
		return getConnectionAnchor();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
	 */
	@Override
	protected void refreshVisuals() {
		boolean hideLabel = getState().getStateSpace().isHideLabels();
		refreshLocation(hideLabel);
		refreshLabel(hideLabel);
		refreshColor();
	}
	
	/*
	 * Update the state's location.
	 */
	private void refreshLocation(boolean hideLabel) {
		int[] loc = getState().getLocation();
		Rectangle bounds = new Rectangle(loc[0], loc[1], hideLabel ? SIZE : -1, SIZE);
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), bounds);
	}
	
	/**
	 * Update the name label.
	 */
	public void refreshLabel(boolean hideLabel) {
		
		// Update label text:
		if (hideLabel) {
			((StateFigure) getFigure()).setHideLabel(true);
		} else {
			((StateFigure) getFigure()).setHideLabel(false);
			((StateFigure) getFigure()).getLabel().setText(" " + getState().getIndex() + " ");
		}
		
		// Update tool tip:
		if (getState().isInitial()) {
			URI modelURI = getState().getModel().getResource().getURI();
			String tooltip = modelURI.deresolve(getState().eResource().getURI()).toString();
			getFigure().setToolTip(new Label(tooltip));
		}
		
	}
	
	/*
	 * Refresh the color.
	 */
	private void refreshColor() {
		State state = getState();
		if (state.isInitial()) {
			getFigure().setBackgroundColor(COLOR_INITIAL);	
		}
		else if (state.isTerminal()) {
			getFigure().setBackgroundColor(COLOR_TERMINAL);	
		}
		else if (state.isOpen()) {
			getFigure().setBackgroundColor(COLOR_OPEN);
		}
		else {
			getFigure().setBackgroundColor(COLOR_DEFAULT);			
		}		
	}
	
	/*
	 * Convert an RGB value to a color.
	 */
	private static Color RGB2Color(int[] rgb) {
		return new Color(null, rgb[0], rgb[1], rgb[2]);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart#performRequest(org.eclipse.gef.Request)
	 */
	@Override
	public void performRequest(Request request) {
		if (request.getType()==RequestConstants.REQ_OPEN && getState().isOpen()) {
			// Explore the current state:
			ExploreStatesCommand command = new ExploreStatesCommand(getStateSpaceManager(), getState());
			command.setGenerateLocations(true);
			CommandStack stack = getViewer().getEditDomain().getCommandStack();
			stack.execute(command);
		} else {
			super.performRequest(request);
		}
	}
	
	/**
	 * State figure.
	 * @author Christian Krause
	 */
	static class StateFigure extends Ellipse {
		
		private Label label;
		
		StateFigure() {
			setAntialias(SWT.ON);
			setOpaque(true);
			setLayoutManager(new StackLayout());
		}
		
		public Label getLabel() {
			return label;
		}
		
		public void setHideLabel(boolean hideLabel) {
			if (hideLabel && label!=null) {
				remove(label);
				label = null;
			} else if (!hideLabel && label==null) {
				add(label = new Label());
			}
		}
	}
	
	
	/* -------------------- *
	 * --- Notification --- *
	 * -------------------- */
	
	private Notifier target;
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.Adapter#getTarget()
	 */
	public Notifier getTarget() {
		return target;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.Adapter#isAdapterForType(java.lang.Object)
	 */
	public boolean isAdapterForType(Object type) {
		return false;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	public void notifyChanged(final Notification event) {
		
		boolean hideLabel = getState().getStateSpace().isHideLabels();
		switch (event.getFeatureID(State.class)) {
		
		case StateSpacePackageImpl.STATE__DATA: 
			refreshColor();
			refreshLocation(hideLabel);
			break;
		
		case StateSpacePackageImpl.STATE__INDEX: 
			refreshLabel(hideLabel); 
			refreshLocation(hideLabel);
			break;
			
		case StateSpacePackageImpl.STATE__OUTGOING: 
			refreshSourceConnections(); 
			break;

		case StateSpacePackageImpl.STATE__INCOMING: 
			refreshTargetConnections(); 
			break;
			
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.Adapter#setTarget(org.eclipse.emf.common.notify.Notifier)
	 */
	public void setTarget(Notifier target) {
		this.target = target;
	}
	
}