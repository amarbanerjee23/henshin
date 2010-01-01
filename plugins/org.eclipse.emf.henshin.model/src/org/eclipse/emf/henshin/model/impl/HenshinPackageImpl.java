/**
 * <copyright>
 * </copyright>
 *
 * $Id: HenshinPackageImpl.java,v 1.1 2009/10/28 10:38:15 enrico Exp $
 */
package org.eclipse.emf.henshin.model.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.henshin.model.AmalgamatedUnit;
import org.eclipse.emf.henshin.model.And;
import org.eclipse.emf.henshin.model.Attribute;
import org.eclipse.emf.henshin.model.AttributeCondition;
import org.eclipse.emf.henshin.model.BinaryFormula;
import org.eclipse.emf.henshin.model.ConditionalUnit;
import org.eclipse.emf.henshin.model.CountedUnit;
import org.eclipse.emf.henshin.model.DescribedElement;
import org.eclipse.emf.henshin.model.Edge;
import org.eclipse.emf.henshin.model.Formula;
import org.eclipse.emf.henshin.model.Graph;
import org.eclipse.emf.henshin.model.HenshinFactory;
import org.eclipse.emf.henshin.model.HenshinPackage;
import org.eclipse.emf.henshin.model.IndependentUnit;
import org.eclipse.emf.henshin.model.Mapping;
import org.eclipse.emf.henshin.model.NamedElement;
import org.eclipse.emf.henshin.model.NestedCondition;
import org.eclipse.emf.henshin.model.Node;
import org.eclipse.emf.henshin.model.Not;
import org.eclipse.emf.henshin.model.Or;
import org.eclipse.emf.henshin.model.Port;
import org.eclipse.emf.henshin.model.PortKind;
import org.eclipse.emf.henshin.model.PortObject;
import org.eclipse.emf.henshin.model.PortParameter;
import org.eclipse.emf.henshin.model.PriorityUnit;
import org.eclipse.emf.henshin.model.Rule;
import org.eclipse.emf.henshin.model.SequentialUnit;
import org.eclipse.emf.henshin.model.SingleUnit;
import org.eclipse.emf.henshin.model.Transformation;
import org.eclipse.emf.henshin.model.TransformationSystem;
import org.eclipse.emf.henshin.model.TransformationUnit;
import org.eclipse.emf.henshin.model.UnaryFormula;
import org.eclipse.emf.henshin.model.Variable;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class HenshinPackageImpl extends EPackageImpl implements HenshinPackage {
        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        private EClass namedElementEClass = null;

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        private EClass describedElementEClass = null;

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        private EClass transformationSystemEClass = null;

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        private EClass ruleEClass = null;

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        private EClass attributeConditionEClass = null;

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        private EClass variableEClass = null;

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        private EClass portEClass = null;

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        private EClass portObjectEClass = null;

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        private EClass portParameterEClass = null;

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        private EClass graphEClass = null;

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        private EClass mappingEClass = null;

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        private EClass nodeEClass = null;

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        private EClass attributeEClass = null;

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        private EClass edgeEClass = null;

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        private EClass transformationEClass = null;

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        private EClass transformationUnitEClass = null;

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        private EClass independentUnitEClass = null;

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        private EClass sequentialUnitEClass = null;

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        private EClass conditionalUnitEClass = null;

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        private EClass priorityUnitEClass = null;

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        private EClass singleUnitEClass = null;

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        private EClass amalgamatedUnitEClass = null;

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        private EClass countedUnitEClass = null;

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        private EClass nestedConditionEClass = null;

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        private EClass formulaEClass = null;

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        private EClass unaryFormulaEClass = null;

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        private EClass binaryFormulaEClass = null;

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        private EClass andEClass = null;

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        private EClass orEClass = null;

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        private EClass notEClass = null;

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        private EEnum portKindEEnum = null;

        /**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.emf.henshin.model.HenshinPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
        private HenshinPackageImpl() {
		super(eNS_URI, HenshinFactory.eINSTANCE);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        private static boolean isInited = false;

        /**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link HenshinPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
        public static HenshinPackage init() {
		if (isInited) return (HenshinPackage)EPackage.Registry.INSTANCE.getEPackage(HenshinPackage.eNS_URI);

		// Obtain or create and register package
		HenshinPackageImpl theHenshinPackage = (HenshinPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof HenshinPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new HenshinPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theHenshinPackage.createPackageContents();

		// Initialize created meta-data
		theHenshinPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theHenshinPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(HenshinPackage.eNS_URI, theHenshinPackage);
		return theHenshinPackage;
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EClass getNamedElement() {
		return namedElementEClass;
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EAttribute getNamedElement_Name() {
		return (EAttribute)namedElementEClass.getEStructuralFeatures().get(0);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EClass getDescribedElement() {
		return describedElementEClass;
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EAttribute getDescribedElement_Description() {
		return (EAttribute)describedElementEClass.getEStructuralFeatures().get(0);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EClass getTransformationSystem() {
		return transformationSystemEClass;
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getTransformationSystem_Rules() {
		return (EReference)transformationSystemEClass.getEStructuralFeatures().get(0);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getTransformationSystem_Transformations() {
		return (EReference)transformationSystemEClass.getEStructuralFeatures().get(1);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getTransformationSystem_Imports() {
		return (EReference)transformationSystemEClass.getEStructuralFeatures().get(2);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getTransformationSystem_Instances() {
		return (EReference)transformationSystemEClass.getEStructuralFeatures().get(3);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EClass getRule() {
		return ruleEClass;
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getRule_Lhs() {
		return (EReference)ruleEClass.getEStructuralFeatures().get(0);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getRule_Rhs() {
		return (EReference)ruleEClass.getEStructuralFeatures().get(1);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getRule_AttributeConditions() {
		return (EReference)ruleEClass.getEStructuralFeatures().get(2);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getRule_Mappings() {
		return (EReference)ruleEClass.getEStructuralFeatures().get(3);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getRule_TransformationSystem() {
		return (EReference)ruleEClass.getEStructuralFeatures().get(4);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getRule_Variables() {
		return (EReference)ruleEClass.getEStructuralFeatures().get(5);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EClass getAttributeCondition() {
		return attributeConditionEClass;
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getAttributeCondition_Rule() {
		return (EReference)attributeConditionEClass.getEStructuralFeatures().get(0);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EAttribute getAttributeCondition_ConditionText() {
		return (EAttribute)attributeConditionEClass.getEStructuralFeatures().get(1);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EClass getVariable() {
		return variableEClass;
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getVariable_Rule() {
		return (EReference)variableEClass.getEStructuralFeatures().get(0);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EClass getPort() {
		return portEClass;
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EAttribute getPort_Direction() {
		return (EAttribute)portEClass.getEStructuralFeatures().get(0);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getPort_Unit() {
		return (EReference)portEClass.getEStructuralFeatures().get(1);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EClass getPortObject() {
		return portObjectEClass;
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getPortObject_Node() {
		return (EReference)portObjectEClass.getEStructuralFeatures().get(0);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EClass getPortParameter() {
		return portParameterEClass;
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getPortParameter_Variable() {
		return (EReference)portParameterEClass.getEStructuralFeatures().get(0);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EClass getGraph() {
		return graphEClass;
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getGraph_Nodes() {
		return (EReference)graphEClass.getEStructuralFeatures().get(0);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getGraph_Edges() {
		return (EReference)graphEClass.getEStructuralFeatures().get(1);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getGraph_Formula() {
		return (EReference)graphEClass.getEStructuralFeatures().get(2);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EClass getMapping() {
		return mappingEClass;
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getMapping_Origin() {
		return (EReference)mappingEClass.getEStructuralFeatures().get(0);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getMapping_Image() {
		return (EReference)mappingEClass.getEStructuralFeatures().get(1);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EClass getNode() {
		return nodeEClass;
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getNode_Type() {
		return (EReference)nodeEClass.getEStructuralFeatures().get(0);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getNode_Attributes() {
		return (EReference)nodeEClass.getEStructuralFeatures().get(1);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getNode_Graph() {
		return (EReference)nodeEClass.getEStructuralFeatures().get(2);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getNode_Incoming() {
		return (EReference)nodeEClass.getEStructuralFeatures().get(3);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getNode_Outgoing() {
		return (EReference)nodeEClass.getEStructuralFeatures().get(4);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EClass getAttribute() {
		return attributeEClass;
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getAttribute_Type() {
		return (EReference)attributeEClass.getEStructuralFeatures().get(0);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EAttribute getAttribute_Value() {
		return (EAttribute)attributeEClass.getEStructuralFeatures().get(1);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getAttribute_Node() {
		return (EReference)attributeEClass.getEStructuralFeatures().get(2);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EClass getEdge() {
		return edgeEClass;
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getEdge_Source() {
		return (EReference)edgeEClass.getEStructuralFeatures().get(0);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getEdge_Target() {
		return (EReference)edgeEClass.getEStructuralFeatures().get(1);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getEdge_Type() {
		return (EReference)edgeEClass.getEStructuralFeatures().get(2);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getEdge_Graph() {
		return (EReference)edgeEClass.getEStructuralFeatures().get(3);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EClass getTransformation() {
		return transformationEClass;
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getTransformation_MainUnit() {
		return (EReference)transformationEClass.getEStructuralFeatures().get(0);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getTransformation_TransformationSystem() {
		return (EReference)transformationEClass.getEStructuralFeatures().get(1);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EClass getTransformationUnit() {
		return transformationUnitEClass;
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EAttribute getTransformationUnit_Activated() {
		return (EAttribute)transformationUnitEClass.getEStructuralFeatures().get(0);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getTransformationUnit_Ports() {
		return (EReference)transformationUnitEClass.getEStructuralFeatures().get(1);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EClass getIndependentUnit() {
		return independentUnitEClass;
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getIndependentUnit_SubUnits() {
		return (EReference)independentUnitEClass.getEStructuralFeatures().get(0);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EClass getSequentialUnit() {
		return sequentialUnitEClass;
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getSequentialUnit_SubUnits() {
		return (EReference)sequentialUnitEClass.getEStructuralFeatures().get(0);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EClass getConditionalUnit() {
		return conditionalUnitEClass;
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getConditionalUnit_If() {
		return (EReference)conditionalUnitEClass.getEStructuralFeatures().get(0);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getConditionalUnit_Then() {
		return (EReference)conditionalUnitEClass.getEStructuralFeatures().get(1);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getConditionalUnit_Else() {
		return (EReference)conditionalUnitEClass.getEStructuralFeatures().get(2);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EClass getPriorityUnit() {
		return priorityUnitEClass;
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getPriorityUnit_SubUnits() {
		return (EReference)priorityUnitEClass.getEStructuralFeatures().get(0);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EClass getSingleUnit() {
		return singleUnitEClass;
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getSingleUnit_Rule() {
		return (EReference)singleUnitEClass.getEStructuralFeatures().get(0);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EClass getAmalgamatedUnit() {
		return amalgamatedUnitEClass;
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getAmalgamatedUnit_KernelRule() {
		return (EReference)amalgamatedUnitEClass.getEStructuralFeatures().get(0);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getAmalgamatedUnit_MultiRules() {
		return (EReference)amalgamatedUnitEClass.getEStructuralFeatures().get(1);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getAmalgamatedUnit_LhsMappings() {
		return (EReference)amalgamatedUnitEClass.getEStructuralFeatures().get(2);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getAmalgamatedUnit_RhsMappings() {
		return (EReference)amalgamatedUnitEClass.getEStructuralFeatures().get(3);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EClass getCountedUnit() {
		return countedUnitEClass;
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getCountedUnit_SubUnit() {
		return (EReference)countedUnitEClass.getEStructuralFeatures().get(0);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EAttribute getCountedUnit_Count() {
		return (EAttribute)countedUnitEClass.getEStructuralFeatures().get(1);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EClass getNestedCondition() {
		return nestedConditionEClass;
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EAttribute getNestedCondition_Negated() {
		return (EAttribute)nestedConditionEClass.getEStructuralFeatures().get(0);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getNestedCondition_Conclusion() {
		return (EReference)nestedConditionEClass.getEStructuralFeatures().get(1);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getNestedCondition_Mappings() {
		return (EReference)nestedConditionEClass.getEStructuralFeatures().get(2);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EClass getFormula() {
		return formulaEClass;
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EClass getUnaryFormula() {
		return unaryFormulaEClass;
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getUnaryFormula_Child() {
		return (EReference)unaryFormulaEClass.getEStructuralFeatures().get(0);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EClass getBinaryFormula() {
		return binaryFormulaEClass;
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getBinaryFormula_Left() {
		return (EReference)binaryFormulaEClass.getEStructuralFeatures().get(0);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EReference getBinaryFormula_Right() {
		return (EReference)binaryFormulaEClass.getEStructuralFeatures().get(1);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EClass getAnd() {
		return andEClass;
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EClass getOr() {
		return orEClass;
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EClass getNot() {
		return notEClass;
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public EEnum getPortKind() {
		return portKindEEnum;
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public HenshinFactory getHenshinFactory() {
		return (HenshinFactory)getEFactoryInstance();
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        private boolean isCreated = false;

        /**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		namedElementEClass = createEClass(NAMED_ELEMENT);
		createEAttribute(namedElementEClass, NAMED_ELEMENT__NAME);

		describedElementEClass = createEClass(DESCRIBED_ELEMENT);
		createEAttribute(describedElementEClass, DESCRIBED_ELEMENT__DESCRIPTION);

		transformationSystemEClass = createEClass(TRANSFORMATION_SYSTEM);
		createEReference(transformationSystemEClass, TRANSFORMATION_SYSTEM__RULES);
		createEReference(transformationSystemEClass, TRANSFORMATION_SYSTEM__TRANSFORMATIONS);
		createEReference(transformationSystemEClass, TRANSFORMATION_SYSTEM__IMPORTS);
		createEReference(transformationSystemEClass, TRANSFORMATION_SYSTEM__INSTANCES);

		ruleEClass = createEClass(RULE);
		createEReference(ruleEClass, RULE__LHS);
		createEReference(ruleEClass, RULE__RHS);
		createEReference(ruleEClass, RULE__ATTRIBUTE_CONDITIONS);
		createEReference(ruleEClass, RULE__MAPPINGS);
		createEReference(ruleEClass, RULE__TRANSFORMATION_SYSTEM);
		createEReference(ruleEClass, RULE__VARIABLES);

		attributeConditionEClass = createEClass(ATTRIBUTE_CONDITION);
		createEReference(attributeConditionEClass, ATTRIBUTE_CONDITION__RULE);
		createEAttribute(attributeConditionEClass, ATTRIBUTE_CONDITION__CONDITION_TEXT);

		variableEClass = createEClass(VARIABLE);
		createEReference(variableEClass, VARIABLE__RULE);

		portEClass = createEClass(PORT);
		createEAttribute(portEClass, PORT__DIRECTION);
		createEReference(portEClass, PORT__UNIT);

		portObjectEClass = createEClass(PORT_OBJECT);
		createEReference(portObjectEClass, PORT_OBJECT__NODE);

		portParameterEClass = createEClass(PORT_PARAMETER);
		createEReference(portParameterEClass, PORT_PARAMETER__VARIABLE);

		graphEClass = createEClass(GRAPH);
		createEReference(graphEClass, GRAPH__NODES);
		createEReference(graphEClass, GRAPH__EDGES);
		createEReference(graphEClass, GRAPH__FORMULA);

		mappingEClass = createEClass(MAPPING);
		createEReference(mappingEClass, MAPPING__ORIGIN);
		createEReference(mappingEClass, MAPPING__IMAGE);

		nodeEClass = createEClass(NODE);
		createEReference(nodeEClass, NODE__TYPE);
		createEReference(nodeEClass, NODE__ATTRIBUTES);
		createEReference(nodeEClass, NODE__GRAPH);
		createEReference(nodeEClass, NODE__INCOMING);
		createEReference(nodeEClass, NODE__OUTGOING);

		attributeEClass = createEClass(ATTRIBUTE);
		createEReference(attributeEClass, ATTRIBUTE__TYPE);
		createEAttribute(attributeEClass, ATTRIBUTE__VALUE);
		createEReference(attributeEClass, ATTRIBUTE__NODE);

		edgeEClass = createEClass(EDGE);
		createEReference(edgeEClass, EDGE__SOURCE);
		createEReference(edgeEClass, EDGE__TARGET);
		createEReference(edgeEClass, EDGE__TYPE);
		createEReference(edgeEClass, EDGE__GRAPH);

		transformationEClass = createEClass(TRANSFORMATION);
		createEReference(transformationEClass, TRANSFORMATION__MAIN_UNIT);
		createEReference(transformationEClass, TRANSFORMATION__TRANSFORMATION_SYSTEM);

		transformationUnitEClass = createEClass(TRANSFORMATION_UNIT);
		createEAttribute(transformationUnitEClass, TRANSFORMATION_UNIT__ACTIVATED);
		createEReference(transformationUnitEClass, TRANSFORMATION_UNIT__PORTS);

		independentUnitEClass = createEClass(INDEPENDENT_UNIT);
		createEReference(independentUnitEClass, INDEPENDENT_UNIT__SUB_UNITS);

		sequentialUnitEClass = createEClass(SEQUENTIAL_UNIT);
		createEReference(sequentialUnitEClass, SEQUENTIAL_UNIT__SUB_UNITS);

		conditionalUnitEClass = createEClass(CONDITIONAL_UNIT);
		createEReference(conditionalUnitEClass, CONDITIONAL_UNIT__IF);
		createEReference(conditionalUnitEClass, CONDITIONAL_UNIT__THEN);
		createEReference(conditionalUnitEClass, CONDITIONAL_UNIT__ELSE);

		priorityUnitEClass = createEClass(PRIORITY_UNIT);
		createEReference(priorityUnitEClass, PRIORITY_UNIT__SUB_UNITS);

		singleUnitEClass = createEClass(SINGLE_UNIT);
		createEReference(singleUnitEClass, SINGLE_UNIT__RULE);

		amalgamatedUnitEClass = createEClass(AMALGAMATED_UNIT);
		createEReference(amalgamatedUnitEClass, AMALGAMATED_UNIT__KERNEL_RULE);
		createEReference(amalgamatedUnitEClass, AMALGAMATED_UNIT__MULTI_RULES);
		createEReference(amalgamatedUnitEClass, AMALGAMATED_UNIT__LHS_MAPPINGS);
		createEReference(amalgamatedUnitEClass, AMALGAMATED_UNIT__RHS_MAPPINGS);

		countedUnitEClass = createEClass(COUNTED_UNIT);
		createEReference(countedUnitEClass, COUNTED_UNIT__SUB_UNIT);
		createEAttribute(countedUnitEClass, COUNTED_UNIT__COUNT);

		nestedConditionEClass = createEClass(NESTED_CONDITION);
		createEAttribute(nestedConditionEClass, NESTED_CONDITION__NEGATED);
		createEReference(nestedConditionEClass, NESTED_CONDITION__CONCLUSION);
		createEReference(nestedConditionEClass, NESTED_CONDITION__MAPPINGS);

		formulaEClass = createEClass(FORMULA);

		unaryFormulaEClass = createEClass(UNARY_FORMULA);
		createEReference(unaryFormulaEClass, UNARY_FORMULA__CHILD);

		binaryFormulaEClass = createEClass(BINARY_FORMULA);
		createEReference(binaryFormulaEClass, BINARY_FORMULA__LEFT);
		createEReference(binaryFormulaEClass, BINARY_FORMULA__RIGHT);

		andEClass = createEClass(AND);

		orEClass = createEClass(OR);

		notEClass = createEClass(NOT);

		// Create enums
		portKindEEnum = createEEnum(PORT_KIND);
	}

        /**
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        private boolean isInitialized = false;

        /**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @generated
	 */
        public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		transformationSystemEClass.getESuperTypes().add(this.getDescribedElement());
		transformationSystemEClass.getESuperTypes().add(this.getNamedElement());
		ruleEClass.getESuperTypes().add(this.getDescribedElement());
		ruleEClass.getESuperTypes().add(this.getNamedElement());
		attributeConditionEClass.getESuperTypes().add(this.getDescribedElement());
		attributeConditionEClass.getESuperTypes().add(this.getNamedElement());
		variableEClass.getESuperTypes().add(this.getDescribedElement());
		variableEClass.getESuperTypes().add(this.getNamedElement());
		portEClass.getESuperTypes().add(this.getDescribedElement());
		portEClass.getESuperTypes().add(this.getNamedElement());
		portObjectEClass.getESuperTypes().add(this.getPort());
		portParameterEClass.getESuperTypes().add(this.getPort());
		graphEClass.getESuperTypes().add(this.getNamedElement());
		nodeEClass.getESuperTypes().add(this.getNamedElement());
		transformationEClass.getESuperTypes().add(this.getDescribedElement());
		transformationEClass.getESuperTypes().add(this.getNamedElement());
		independentUnitEClass.getESuperTypes().add(this.getTransformationUnit());
		sequentialUnitEClass.getESuperTypes().add(this.getTransformationUnit());
		conditionalUnitEClass.getESuperTypes().add(this.getTransformationUnit());
		priorityUnitEClass.getESuperTypes().add(this.getTransformationUnit());
		singleUnitEClass.getESuperTypes().add(this.getTransformationUnit());
		amalgamatedUnitEClass.getESuperTypes().add(this.getTransformationUnit());
		countedUnitEClass.getESuperTypes().add(this.getTransformationUnit());
		nestedConditionEClass.getESuperTypes().add(this.getFormula());
		unaryFormulaEClass.getESuperTypes().add(this.getFormula());
		binaryFormulaEClass.getESuperTypes().add(this.getFormula());
		andEClass.getESuperTypes().add(this.getBinaryFormula());
		orEClass.getESuperTypes().add(this.getBinaryFormula());
		notEClass.getESuperTypes().add(this.getUnaryFormula());

		// Initialize classes and features; add operations and parameters
		initEClass(namedElementEClass, NamedElement.class, "NamedElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNamedElement_Name(), ecorePackage.getEString(), "name", null, 0, 1, NamedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(describedElementEClass, DescribedElement.class, "DescribedElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDescribedElement_Description(), ecorePackage.getEString(), "description", null, 0, 1, DescribedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(transformationSystemEClass, TransformationSystem.class, "TransformationSystem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTransformationSystem_Rules(), this.getRule(), this.getRule_TransformationSystem(), "rules", null, 0, -1, TransformationSystem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTransformationSystem_Transformations(), this.getTransformation(), this.getTransformation_TransformationSystem(), "transformations", null, 0, -1, TransformationSystem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTransformationSystem_Imports(), ecorePackage.getEPackage(), null, "imports", null, 0, -1, TransformationSystem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTransformationSystem_Instances(), this.getGraph(), null, "instances", null, 0, -1, TransformationSystem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(ruleEClass, Rule.class, "Rule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRule_Lhs(), this.getGraph(), null, "lhs", null, 1, 1, Rule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRule_Rhs(), this.getGraph(), null, "rhs", null, 1, 1, Rule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRule_AttributeConditions(), this.getAttributeCondition(), this.getAttributeCondition_Rule(), "attributeConditions", null, 0, -1, Rule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRule_Mappings(), this.getMapping(), null, "mappings", null, 0, -1, Rule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRule_TransformationSystem(), this.getTransformationSystem(), this.getTransformationSystem_Rules(), "transformationSystem", null, 1, 1, Rule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRule_Variables(), this.getVariable(), this.getVariable_Rule(), "variables", null, 0, -1, Rule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(attributeConditionEClass, AttributeCondition.class, "AttributeCondition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAttributeCondition_Rule(), this.getRule(), this.getRule_AttributeConditions(), "rule", null, 0, 1, AttributeCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttributeCondition_ConditionText(), ecorePackage.getEString(), "conditionText", null, 0, 1, AttributeCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(variableEClass, Variable.class, "Variable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getVariable_Rule(), this.getRule(), this.getRule_Variables(), "rule", null, 0, 1, Variable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(portEClass, Port.class, "Port", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPort_Direction(), this.getPortKind(), "direction", null, 0, 1, Port.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPort_Unit(), this.getTransformationUnit(), this.getTransformationUnit_Ports(), "unit", null, 0, 1, Port.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(portObjectEClass, PortObject.class, "PortObject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPortObject_Node(), this.getNode(), null, "node", null, 0, 1, PortObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(portParameterEClass, PortParameter.class, "PortParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPortParameter_Variable(), this.getVariable(), null, "variable", null, 0, 1, PortParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(graphEClass, Graph.class, "Graph", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGraph_Nodes(), this.getNode(), this.getNode_Graph(), "nodes", null, 0, -1, Graph.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGraph_Edges(), this.getEdge(), this.getEdge_Graph(), "edges", null, 0, -1, Graph.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGraph_Formula(), this.getFormula(), null, "formula", null, 0, 1, Graph.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(mappingEClass, Mapping.class, "Mapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMapping_Origin(), this.getNode(), null, "origin", null, 1, 1, Mapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMapping_Image(), this.getNode(), null, "image", null, 1, 1, Mapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(nodeEClass, Node.class, "Node", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getNode_Type(), ecorePackage.getEClass(), null, "type", null, 1, 1, Node.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNode_Attributes(), this.getAttribute(), this.getAttribute_Node(), "attributes", null, 0, -1, Node.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNode_Graph(), this.getGraph(), this.getGraph_Nodes(), "graph", null, 1, 1, Node.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNode_Incoming(), this.getEdge(), this.getEdge_Target(), "incoming", null, 0, -1, Node.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNode_Outgoing(), this.getEdge(), this.getEdge_Source(), "outgoing", null, 0, -1, Node.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(attributeEClass, Attribute.class, "Attribute", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAttribute_Type(), ecorePackage.getEAttribute(), null, "type", null, 1, 1, Attribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttribute_Value(), ecorePackage.getEString(), "value", null, 0, 1, Attribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAttribute_Node(), this.getNode(), this.getNode_Attributes(), "node", null, 1, 1, Attribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(edgeEClass, Edge.class, "Edge", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEdge_Source(), this.getNode(), this.getNode_Outgoing(), "source", null, 1, 1, Edge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEdge_Target(), this.getNode(), this.getNode_Incoming(), "target", null, 1, 1, Edge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEdge_Type(), ecorePackage.getEReference(), null, "type", null, 1, 1, Edge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEdge_Graph(), this.getGraph(), this.getGraph_Edges(), "graph", null, 1, 1, Edge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(transformationEClass, Transformation.class, "Transformation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTransformation_MainUnit(), this.getTransformationUnit(), null, "mainUnit", null, 1, 1, Transformation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTransformation_TransformationSystem(), this.getTransformationSystem(), this.getTransformationSystem_Transformations(), "transformationSystem", null, 1, 1, Transformation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(transformationUnitEClass, TransformationUnit.class, "TransformationUnit", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTransformationUnit_Activated(), ecorePackage.getEBoolean(), "activated", null, 0, 1, TransformationUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTransformationUnit_Ports(), this.getPort(), this.getPort_Unit(), "ports", null, 0, -1, TransformationUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(independentUnitEClass, IndependentUnit.class, "IndependentUnit", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIndependentUnit_SubUnits(), this.getTransformationUnit(), null, "subUnits", null, 0, -1, IndependentUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sequentialUnitEClass, SequentialUnit.class, "SequentialUnit", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSequentialUnit_SubUnits(), this.getTransformationUnit(), null, "subUnits", null, 0, -1, SequentialUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(conditionalUnitEClass, ConditionalUnit.class, "ConditionalUnit", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConditionalUnit_If(), this.getTransformationUnit(), null, "if", null, 1, 1, ConditionalUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConditionalUnit_Then(), this.getTransformationUnit(), null, "then", null, 1, 1, ConditionalUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConditionalUnit_Else(), this.getTransformationUnit(), null, "else", null, 0, 1, ConditionalUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(priorityUnitEClass, PriorityUnit.class, "PriorityUnit", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPriorityUnit_SubUnits(), this.getTransformationUnit(), null, "subUnits", null, 0, -1, PriorityUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(singleUnitEClass, SingleUnit.class, "SingleUnit", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSingleUnit_Rule(), this.getRule(), null, "rule", null, 1, 1, SingleUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(amalgamatedUnitEClass, AmalgamatedUnit.class, "AmalgamatedUnit", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAmalgamatedUnit_KernelRule(), this.getRule(), null, "kernelRule", null, 0, 1, AmalgamatedUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAmalgamatedUnit_MultiRules(), this.getRule(), null, "multiRules", null, 0, -1, AmalgamatedUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAmalgamatedUnit_LhsMappings(), this.getMapping(), null, "lhsMappings", null, 0, -1, AmalgamatedUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAmalgamatedUnit_RhsMappings(), this.getMapping(), null, "rhsMappings", null, 0, -1, AmalgamatedUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(countedUnitEClass, CountedUnit.class, "CountedUnit", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCountedUnit_SubUnit(), this.getTransformationUnit(), null, "subUnit", null, 1, 1, CountedUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCountedUnit_Count(), ecorePackage.getEInt(), "count", null, 0, 1, CountedUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(nestedConditionEClass, NestedCondition.class, "NestedCondition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNestedCondition_Negated(), ecorePackage.getEBoolean(), "negated", null, 0, 1, NestedCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNestedCondition_Conclusion(), this.getGraph(), null, "conclusion", null, 0, 1, NestedCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNestedCondition_Mappings(), this.getMapping(), null, "mappings", null, 0, -1, NestedCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(formulaEClass, Formula.class, "Formula", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(unaryFormulaEClass, UnaryFormula.class, "UnaryFormula", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUnaryFormula_Child(), this.getFormula(), null, "child", null, 0, 1, UnaryFormula.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(binaryFormulaEClass, BinaryFormula.class, "BinaryFormula", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBinaryFormula_Left(), this.getFormula(), null, "left", null, 0, 1, BinaryFormula.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBinaryFormula_Right(), this.getFormula(), null, "right", null, 0, 1, BinaryFormula.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(andEClass, And.class, "And", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(orEClass, Or.class, "Or", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(notEClass, Not.class, "Not", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Initialize enums and add enum literals
		initEEnum(portKindEEnum, PortKind.class, "PortKind");
		addEEnumLiteral(portKindEEnum, PortKind.INPUT);
		addEEnumLiteral(portKindEEnum, PortKind.OUTPUT);
		addEEnumLiteral(portKindEEnum, PortKind.INPUT_OUTPUT);

		// Create resource
		createResource(eNS_URI);
	}

} //HenshinPackageImpl