package org.eclipse.emf.henshin.diagram.edit.policies;

import org.eclipse.emf.henshin.diagram.edit.commands.NodeCreateCommand;
import org.eclipse.emf.henshin.diagram.providers.HenshinElementTypes;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

/**
 * @generated
 */
public class RuleCompartmentItemSemanticEditPolicy extends
		HenshinBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public RuleCompartmentItemSemanticEditPolicy() {
		super(HenshinElementTypes.Rule_2001);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (HenshinElementTypes.Node_3001 == req.getElementType()) {
			return getGEFWrapper(new NodeCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

}