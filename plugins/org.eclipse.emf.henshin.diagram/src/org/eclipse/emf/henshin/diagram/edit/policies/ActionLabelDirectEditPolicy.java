package org.eclipse.emf.henshin.diagram.edit.policies;

import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.LabelDirectEditPolicy;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;

/**
 * A label direct edit policy for action labels.
 * @generated NOT
 * @author Christian Krause
 */
public class ActionLabelDirectEditPolicy extends LabelDirectEditPolicy {
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.editpolicies.LabelDirectEditPolicy#getDirectEditCommand(org.eclipse.gef.requests.DirectEditRequest)
	 */
	@Override
	protected Command getDirectEditCommand(DirectEditRequest request) {
		
		// Get the parse command:
		Command command = super.getDirectEditCommand(request);
		if (command==null) return null;
		
		// Add refresh commands:
		CompoundCommand result = new CompoundCommand();
		result.add(command);
		IGraphicalEditPart host = (IGraphicalEditPart) getHost();
		result.add(new ICommandProxy(new UpdateViewsCommand(host.getEditingDomain(), null)));
		
		return result;
		
	}
	
	/*
	 * Update views command.
	 */
	private class UpdateViewsCommand extends AbstractTransactionalCommand {

		public UpdateViewsCommand(TransactionalEditingDomain domain, List<?> affectedFiles) {
			super(domain, "Update Views", affectedFiles);
		}
		
		@Override
		protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
				IAdaptable info) throws ExecutionException {
			
			// Refresh all edit parts:
			EditPart editpart = getHost();			
			while (editpart!=null) {
				EditPolicy policy = editpart.getEditPolicy(EditPolicyRoles.CANONICAL_ROLE);				
				if (policy instanceof CanonicalEditPolicy) {
					((CanonicalEditPolicy) policy).refresh();
				}
				editpart = editpart.getParent();
			}
			
			// Done.
			return CommandResult.newOKCommandResult();
			
		}
		
	};
	
}
