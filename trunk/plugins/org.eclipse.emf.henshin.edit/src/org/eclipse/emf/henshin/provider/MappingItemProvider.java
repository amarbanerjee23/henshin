/*******************************************************************************
 * Copyright (c) 2010 CWI Amsterdam, Technical University Berlin, 
 * Philipps-University Marburg and others. All rights reserved. 
 * This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Technical University Berlin - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.henshin.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.emf.henshin.model.AmalgamationUnit;
import org.eclipse.emf.henshin.model.HenshinPackage;
import org.eclipse.emf.henshin.model.Mapping;
import org.eclipse.emf.henshin.model.Node;
import org.eclipse.emf.henshin.provider.descriptors.MappingImagePropertyDescriptor;
import org.eclipse.emf.henshin.provider.descriptors.MappingOriginPropertyDescriptor;

/**
 * This is the item provider adapter for a
 * {@link org.eclipse.emf.henshin.model.Mapping} object. <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class MappingItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider,
		IItemPropertySource {

	NodeMappingListener rmListener;

	/**
	 * This constructs an instance from a factory and a notifier. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public MappingItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
		
		rmListener = new NodeMappingListener();
	}

	/**
	 * This returns the property descriptors for the adapted class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addOriginPropertyDescriptor(object);
			addImagePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Origin feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected void addOriginPropertyDescriptor(Object object) {

		itemPropertyDescriptors.add(new MappingOriginPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
				getResourceLocator(), getString("_UI_Mapping_origin_feature"), getString(
						"_UI_PropertyDescriptor_description", "_UI_Mapping_origin_feature",
						"_UI_Mapping_type"), HenshinPackage.Literals.MAPPING__ORIGIN));

		// itemPropertyDescriptors.add
		// (createItemPropertyDescriptor
		// (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
		// getResourceLocator(),
		// getString("_UI_Mapping_origin_feature"),
		// getString("_UI_PropertyDescriptor_description",
		// "_UI_Mapping_origin_feature", "_UI_Mapping_type"),
		// HenshinPackage.Literals.MAPPING__ORIGIN,
		// true,
		// false,
		// true,
		// null,
		// null,
		// null));
	}

	/**
	 * This adds a property descriptor for the Image feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected void addImagePropertyDescriptor(Object object) {

		itemPropertyDescriptors.add(new MappingImagePropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
				getResourceLocator(), getString("_UI_Mapping_image_feature"), getString(
						"_UI_PropertyDescriptor_description", "_UI_Mapping_image_feature",
						"_UI_Mapping_type"), HenshinPackage.Literals.MAPPING__IMAGE));

		// itemPropertyDescriptors.add
		// (createItemPropertyDescriptor
		// (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
		// getResourceLocator(),
		// getString("_UI_Mapping_image_feature"),
		// getString("_UI_PropertyDescriptor_description",
		// "_UI_Mapping_image_feature", "_UI_Mapping_type"),
		// HenshinPackage.Literals.MAPPING__IMAGE,
		// true,
		// false,
		// true,
		// null,
		// null,
		// null));
	}

	/**
	 * This returns Mapping.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Mapping"));
	}

	/**
	 * This returns the label text for the adapted class. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public String getText(Object object) {
		Mapping mapping = (Mapping) object;
		String result = getString("_UI_Mapping_type");
		if (mapping.getOrigin() != null && mapping.getImage() != null) {
			String origin = (mapping.getOrigin() != null) ? mapping.getOrigin().getName() : "null";
			String image = (mapping.getImage() != null) ? mapping.getImage().getName() : "null";
			if (origin != null && image != null) {
				result = result + " " + origin + " -> " + image;
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.emf.edit.provider.ItemProviderAdapter#getParent(java.lang
	 * .Object)
	 */
	@Override
	public Object getParent(Object object) {

		Object o = super.getParent(object);
		if (o instanceof AmalgamationUnit) {
			AmalgamationUnit au = (AmalgamationUnit) o;
			AmalgamationUnitItemProvider auIp = (AmalgamationUnitItemProvider) adapterFactory
					.adapt(au, IEditingDomainItemProvider.class);
			if (au.getLhsMappings().contains(object)) {
				return auIp != null ? auIp.getLhsMappingsItemProvider() : null;
			} else {
				return auIp != null ? auIp.getRhsMappingsItemProvider() : null;
			}
		} else {
			return super.getParent(object);
		}
	}// getParent

	/**
	 * This handles model notifications by calling {@link #updateChildren} to
	 * update any cached children and by creating a viewer notification, which
	 * it passes to {@link #fireNotifyChanged}. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {

		/*
		 * Refresh the corresponding Nodes if the Mapping changes
		 */
		if (notification.getEventType() == Notification.SET) {

			Node n1 = (Node) notification.getNewValue();
			Node n2 = (Node) notification.getOldValue();

			notifyNodeForRefresh(notification, n1);
			notifyNodeForRefresh(notification, n2);

		}// if

		updateChildren(notification);
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s
	 * describing the children that can be created under this object. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);
	}

	/**
	 * Return the resource locator for this item provider's resources. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return HenshinEditPlugin.INSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.emf.edit.provider.ItemProviderAdapter#unsetTarget(org.eclipse
	 * .emf.common.notify.Notifier)
	 */
	@Override
	public void unsetTarget(Notifier target) {
		super.unsetTarget(target);

		// Get the container of the mapping and unregister the
		// NodeMappingListener
		EObject eobject = ((Mapping) target).eContainer();
		if (eobject != null) {
			ItemProviderAdapter eobjectAdapter = (ItemProviderAdapter) this.adapterFactory.adapt(
					eobject, null);
			eobjectAdapter.removeListener(rmListener);
		}// if

	}// unsetTarget

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.emf.edit.provider.ItemProviderAdapter#setTarget(org.eclipse
	 * .emf.common.notify.Notifier)
	 */
	@Override
	public void setTarget(Notifier target) {
		super.setTarget(target);

		// Get the container of the mapping and register the NodeMappingListener
		EObject eobject = ((Mapping) target).eContainer();
		if (eobject != null) {
			ItemProviderAdapter eobjectAdapter = (ItemProviderAdapter) this.adapterFactory.adapt(
					eobject, null);
			eobjectAdapter.addListener(rmListener);
		}// if
	}// setTarget

	/**
	 * Notifies the given node to refresh its label (only). The given
	 * notification is its base notification.
	 * 
	 * @param notification
	 * @param node
	 */
	private void notifyNodeForRefresh(Notification notification, Node node) {
		if (node != null) {
			ItemProviderAdapter adapter = (ItemProviderAdapter) this.adapterFactory.adapt(node,
					Node.class);
			Notification notif = new ViewerNotification(notification, node, false, true);
			adapter.fireNotifyChanged(notif);
		}// if
	}// notifyNodeForRefresh

	/**
	 * This Listener listens for added or removed Mapping object in order to
	 * synchronize the visualisation (delete, create, preserve) of Nodes.
	 * 
	 * @author sjtuner
	 * 
	 */
	private class NodeMappingListener implements INotifyChangedListener {

		@Override
		public void notifyChanged(Notification notification) {
			if (notification.getEventType() == Notification.ADD) {
				if (notification.getNewValue() instanceof Mapping) {
					Mapping map = (Mapping) notification.getNewValue();
					MappingItemProvider.this.notifyNodeForRefresh(notification, map.getOrigin());
					MappingItemProvider.this.notifyNodeForRefresh(notification, map.getImage());
				}// if
			} else if (notification.getEventType() == Notification.REMOVE) {
				if (notification.getOldValue() instanceof Mapping) {
					Mapping map = (Mapping) notification.getOldValue();
					MappingItemProvider.this.notifyNodeForRefresh(notification, map.getOrigin());
					MappingItemProvider.this.notifyNodeForRefresh(notification, map.getImage());
				}// if
			}// if else

		}// notifyChanged

	}// inner class

}// class
