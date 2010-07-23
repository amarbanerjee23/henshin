/**
 * 
 */
package org.eclipse.emf.henshin.provider;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;

/**
 * This class represents so-called <i>non-model intermediary view objects</i>
 * for better structuring the tree-based editor.
 * 
 * @author sjurack (Stefan Jurack)
 * 
 */
public class TransientItemProvider extends ItemProviderAdapter2 implements
		IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {

	/**
	 * @param adapterFactory
	 * @param target
	 */
	public TransientItemProvider(AdapterFactory adapterFactory, EObject target) {
		super(adapterFactory);
		target.eAdapters().add(this);
	}// constructor

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildren(java.lang
	 * .Object)
	 */
	@Override
	public Collection<?> getChildren(Object object) {
		return super.getChildren(target);
	}// getChildren

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.emf.edit.provider.ItemProviderAdapter#getParent(java.lang
	 * .Object)
	 */
	@Override
	public Object getParent(Object object) {
		return target;
	}// getParent

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.emf.edit.provider.ItemProviderAdapter#getNewChildDescriptors
	 * (java.lang.Object, org.eclipse.emf.edit.domain.EditingDomain,
	 * java.lang.Object)
	 */
	@Override
	public Collection<?> getNewChildDescriptors(Object object,
			EditingDomain editingDomain, Object sibling) {

		return super.getNewChildDescriptors(target, editingDomain, sibling);
	}// getNewChildDescriptors

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.emf.edit.provider.ItemProviderAdapter#createRemoveCommand
	 * (org.eclipse.emf.edit.domain.EditingDomain,
	 * org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EStructuralFeature,
	 * java.util.Collection)
	 */
	@Override
	protected Command createRemoveCommand(EditingDomain domain, EObject owner,
			EStructuralFeature feature, Collection<?> collection) {

		return createWrappedCommand(
				super.createRemoveCommand(domain, owner, feature, collection),
				owner);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.emf.edit.provider.ItemProviderAdapter#createAddCommand(org
	 * .eclipse.emf.edit.domain.EditingDomain, org.eclipse.emf.ecore.EObject,
	 * org.eclipse.emf.ecore.EStructuralFeature, java.util.Collection, int)
	 */
	@Override
	protected Command createAddCommand(EditingDomain domain, EObject owner,
			EStructuralFeature feature, Collection<?> collection, int index) {

		return createWrappedCommand(super.createAddCommand(domain, owner,
				feature, collection, index), owner);
	}// createAddCommand

	/**
	 * @param command
	 * @param owner
	 * @param feature
	 * @return
	 */
	protected Command createWrappedCommand(Command command, final EObject owner) {
		return new CommandWrapper(command) {
			public Collection getAffectedObjects() {
				Collection affected = super.getAffectedObjects();
				if (affected.contains(owner))
					affected = Collections
							.singleton(TransientItemProvider.this);
				return affected;
			}
		};
	}

	/**
	 * Return the resource locator for this item provider's resources. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return HenshinEditPlugin.INSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.emf.edit.provider.IEditingDomainItemProvider#createCommand
	 * (java.lang.Object, org.eclipse.emf.edit.domain.EditingDomain,
	 * java.lang.Class, org.eclipse.emf.edit.command.CommandParameter)
	 */
	public Command createCommand(final Object object,
			final EditingDomain domain, Class commandClass,
			CommandParameter commandParameter) {

		 commandParameter.setOwner(target);
		//
		// IEditingDomainItemProvider editingDomainItemProvider =
		// (IEditingDomainItemProvider)
		// adapterFactory.adapt(target, IEditingDomainItemProvider.class);
		//
		// return
		// editingDomainItemProvider != null ?
		// editingDomainItemProvider.createCommand(target, domain, commandClass,
		// commandParameter) :
		// super.createCommand(target, domain, commandClass,
		// commandParameter);

		return super.createCommand(object, domain, commandClass,
				commandParameter);
	}

}// class
