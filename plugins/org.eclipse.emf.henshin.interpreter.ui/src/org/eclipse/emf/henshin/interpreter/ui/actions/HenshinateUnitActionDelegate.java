/**
 * <copyright>
 * Copyright (c) 2010-2012 Henshin developers. All rights reserved. 
 * This program and the accompanying materials are made available 
 * under the terms of the Eclipse Public License v1.0 which 
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * </copyright>
 */
package org.eclipse.emf.henshin.interpreter.ui.actions;

import org.eclipse.emf.henshin.interpreter.ui.wizard.HenshinWizard;
import org.eclipse.emf.henshin.interpreter.ui.wizard.HenshinWizardDialog;
import org.eclipse.emf.henshin.model.Unit;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * @author Gregor Bonifer, Stefan Jurack, Christian Krause
 */
public class HenshinateUnitActionDelegate implements IObjectActionDelegate {
	
	// Target workbench part:
	protected IWorkbenchPart targetPart;
	
	// Unit to be applied:
	protected Unit unit;
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@Override
	public void run(IAction action) {
		HenshinWizard tWiz = new HenshinWizard(unit);
		HenshinWizardDialog dialog = new HenshinWizardDialog(targetPart.getSite().getShell(), tWiz);
		dialog.open();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IAction action, ISelection sel) {
		unit = null;
		if (sel instanceof IStructuredSelection) {
			Object object = ((IStructuredSelection) sel).getFirstElement();
			if (object instanceof IGraphicalEditPart) {
				object = ((IGraphicalEditPart) object).getNotationView().getElement();
			}
			if (object instanceof Unit) {
				unit = (Unit) object;
			}
		}
		action.setEnabled(unit!=null);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction, org.eclipse.ui.IWorkbenchPart)
	 */
	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		this.targetPart = targetPart;
	}
	
}
