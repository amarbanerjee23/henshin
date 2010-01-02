package org.eclipse.emf.henshin.diagram.part;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.henshin.diagram.edit.parts.TransformationSystemEditPart;
import org.eclipse.emf.henshin.model.TransformationSystem;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramCommandStack;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;

/**
 * @generated NOT
 * @author Christian Krause
 */
public class ImportPackageAction extends org.eclipse.emf.henshin.editor.actions.ImportPackageAction {
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.editor.actions.ImportPackageAction#runImportCommand(org.eclipse.emf.ecore.EPackage)
	 */
	@Override
	public void runImportCommand(final EPackage epackage) {
		
		// Create a new transactional command:
		HenshinDiagramEditor editor = (HenshinDiagramEditor) workbenchPart;
		TransactionalEditingDomain domain = editor.getEditingDomain();
		AbstractTransactionalCommand command = new AbstractTransactionalCommand(domain, "Import", null) {
			@Override
			protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
				doImport(epackage);
				return CommandResult.newOKCommandResult(epackage);
			}
		};
		
		// Execute it:
		DiagramCommandStack stack = editor.getDiagramEditDomain().getDiagramCommandStack();
		stack.execute(new ICommandProxy(command));
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction, org.eclipse.ui.IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart part) {
		workbenchPart = (part instanceof HenshinDiagramEditor) ? part : null;
		action.setEnabled(workbenchPart!=null);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		transformationSystem = null;
		if (selection instanceof IStructuredSelection) {
			Object first = ((IStructuredSelection) selection).getFirstElement();
			if (first instanceof TransformationSystemEditPart) {
				TransformationSystemEditPart editpart = (TransformationSystemEditPart) first;
				transformationSystem = (TransformationSystem) editpart.getNotationView().getElement();
			}
		}
		action.setEnabled(transformationSystem!=null);
	}

}
