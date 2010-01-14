package org.eclipse.emf.henshin.diagram.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.henshin.diagram.edit.parts.AttributeEditPart;
import org.eclipse.emf.henshin.diagram.edit.parts.EdgeTypeEditPart;
import org.eclipse.emf.henshin.diagram.edit.parts.NodeActionEditPart;
import org.eclipse.emf.henshin.diagram.edit.parts.NodeTypeEditPart;
import org.eclipse.emf.henshin.diagram.edit.parts.RuleNameEditPart;
import org.eclipse.emf.henshin.diagram.parsers.AttributeParser;
import org.eclipse.emf.henshin.diagram.parsers.EdgeTypeParser;
import org.eclipse.emf.henshin.diagram.parsers.MessageFormatParser;
import org.eclipse.emf.henshin.diagram.parsers.NodeActionParser;
import org.eclipse.emf.henshin.diagram.parsers.NodeTypeParser;
import org.eclipse.emf.henshin.diagram.part.HenshinVisualIDRegistry;
import org.eclipse.emf.henshin.model.HenshinPackage;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.GetParserOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserProvider;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserService;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.parser.ParserHintAdapter;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class HenshinParserProvider extends AbstractProvider implements
		IParserProvider {

	/**
	 * @generated
	 */
	private IParser ruleName_5001Parser;

	/**
	 * @generated NOT
	 */
	private IParser nodeTypeParser = new NodeTypeParser();

	/**
	 * @generated NOT
	 */
	private IParser nodeActionParser = new NodeActionParser();

	/**
	 * @generated NOT
	 */
	private IParser edgeTypeParser = new EdgeTypeParser();

	/**
	 * @generated NOT
	 */
	private IParser attributeParser = new AttributeParser();

	/**
	 * @generated
	 */
	private IParser getRuleName_5001Parser() {
		if (ruleName_5001Parser == null) {
			EAttribute[] features = new EAttribute[] { HenshinPackage.eINSTANCE
					.getNamedElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { HenshinPackage.eINSTANCE
					.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features,
					editableFeatures);
			parser.setViewPattern("{0}"); //$NON-NLS-1$
			parser.setEditorPattern("{0}"); //$NON-NLS-1$
			parser.setEditPattern("{0}"); //$NON-NLS-1$
			ruleName_5001Parser = parser;
		}
		return ruleName_5001Parser;
	}

	/**
	 * @generated NOT
	 */
	protected IParser getParser(int visualID) {
		switch (visualID) {
		case RuleNameEditPart.VISUAL_ID:
			return getRuleName_5001Parser();
		case NodeTypeEditPart.VISUAL_ID:
			return nodeTypeParser;
		case NodeActionEditPart.VISUAL_ID:
			return nodeActionParser;
		case EdgeTypeEditPart.VISUAL_ID:
			return edgeTypeParser;
		case AttributeEditPart.VISUAL_ID:
			return attributeParser;
		}
		return null;
	}

	/**
	 * Utility method that consults ParserService
	 * @generated
	 */
	public static IParser getParser(IElementType type, EObject object,
			String parserHint) {
		return ParserService.getInstance().getParser(
				new HintAdapter(type, object, parserHint));
	}

	/**
	 * @generated
	 */
	public IParser getParser(IAdaptable hint) {
		String vid = (String) hint.getAdapter(String.class);
		if (vid != null) {
			return getParser(HenshinVisualIDRegistry.getVisualID(vid));
		}
		View view = (View) hint.getAdapter(View.class);
		if (view != null) {
			return getParser(HenshinVisualIDRegistry.getVisualID(view));
		}
		return null;
	}

	/**
	 * @generated
	 */
	public boolean provides(IOperation operation) {
		if (operation instanceof GetParserOperation) {
			IAdaptable hint = ((GetParserOperation) operation).getHint();
			if (HenshinElementTypes.getElement(hint) == null) {
				return false;
			}
			return getParser(hint) != null;
		}
		return false;
	}

	/**
	 * @generated
	 */
	private static class HintAdapter extends ParserHintAdapter {

		/**
		 * @generated
		 */
		private final IElementType elementType;

		/**
		 * @generated
		 */
		public HintAdapter(IElementType type, EObject object, String parserHint) {
			super(object, parserHint);
			assert type != null;
			elementType = type;
		}

		/**
		 * @generated
		 */
		public Object getAdapter(Class adapter) {
			if (IElementType.class.equals(adapter)) {
				return elementType;
			}
			return super.getAdapter(adapter);
		}
	}

}