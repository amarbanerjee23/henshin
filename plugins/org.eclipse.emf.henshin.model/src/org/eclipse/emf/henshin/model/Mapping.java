/**
 * <copyright>
 * </copyright>
 *
 * $Id: Mapping.java,v 1.1 2009/10/28 10:38:06 enrico Exp $
 */
package org.eclipse.emf.henshin.model;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.henshin.model.Mapping#getOrigin <em>Origin</em>}</li>
 *   <li>{@link org.eclipse.emf.henshin.model.Mapping#getImage <em>Image</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.henshin.model.HenshinPackage#getMapping()
 * @model
 * @generated
 */
public interface Mapping extends EObject {
        /**
	 * Returns the value of the '<em><b>Origin</b></em>' reference.
	 * <!-- begin-user-doc -->
         * <p>
         * If the meaning of the '<em>Origin</em>' reference isn't clear,
         * there really should be more of a description here...
         * </p>
         * <!-- end-user-doc -->
	 * @return the value of the '<em>Origin</em>' reference.
	 * @see #setOrigin(Node)
	 * @see org.eclipse.emf.henshin.model.HenshinPackage#getMapping_Origin()
	 * @model required="true"
	 * @generated
	 */
        Node getOrigin();

        /**
	 * Sets the value of the '{@link org.eclipse.emf.henshin.model.Mapping#getOrigin <em>Origin</em>}' reference.
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Origin</em>' reference.
	 * @see #getOrigin()
	 * @generated
	 */
        void setOrigin(Node value);

        /**
	 * Returns the value of the '<em><b>Image</b></em>' reference.
	 * <!-- begin-user-doc -->
         * <p>
         * If the meaning of the '<em>Image</em>' reference isn't clear,
         * there really should be more of a description here...
         * </p>
         * <!-- end-user-doc -->
	 * @return the value of the '<em>Image</em>' reference.
	 * @see #setImage(Node)
	 * @see org.eclipse.emf.henshin.model.HenshinPackage#getMapping_Image()
	 * @model required="true"
	 * @generated
	 */
        Node getImage();

        /**
	 * Sets the value of the '{@link org.eclipse.emf.henshin.model.Mapping#getImage <em>Image</em>}' reference.
	 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Image</em>' reference.
	 * @see #getImage()
	 * @generated
	 */
        void setImage(Node value);

} // Mapping