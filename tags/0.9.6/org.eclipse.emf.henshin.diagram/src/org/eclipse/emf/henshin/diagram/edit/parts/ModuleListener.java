/**
 * <copyright>
 * Copyright (c) 2010-2012 Henshin developers. All rights reserved. 
 * This program and the accompanying materials are made available 
 * under the terms of the Eclipse Public License v1.0 which 
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * </copyright>
 */
package org.eclipse.emf.henshin.diagram.edit.parts;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.henshin.model.Module;

/**
 * @generated NOT
 * @author Christian Krause
 */
public class ModuleListener {
	
	// Adapter:
	private Adapter adapter;
	
	// Module:
	private Module module;
	
	/**
	 * Default constructor.
	 * @param adapter Adapter to be notified.
	 */
	public ModuleListener(Module module, Adapter adapter) {
		this.module = module;
		this.adapter = adapter;
		module.eAdapters().add(proxy);
		TreeIterator<Object> it = EcoreUtil.getAllContents(module, true);
		while (it.hasNext()) {
			Object next = it.next();
			if (next instanceof Notifier) {
				((Notifier) next).eAdapters().add(proxy);
			}
		}
	}
	
	/**
	 * Dispose this listener.
	 */
	public void dispose() {
		module.eAdapters().remove(proxy);
		TreeIterator<Object> it = EcoreUtil.getAllContents(module, true);
		while (it.hasNext()) {
			Object next = it.next();
			if (next instanceof Notifier) {
				((Notifier) next).eAdapters().remove(proxy);
			}
		}
	}
	
	/*
	 *  Proxy adapter.
	 */
	private Adapter proxy = new Adapter() {
		
		/*
		 * (non-Javadoc)
		 * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
		 */
		public void notifyChanged(Notification event) {

			// Notify the main adapter first:
			adapter.notifyChanged(event);
			
			// Containment reference value changed?
			if (event.getFeature() instanceof EReference) {

				EReference reference = (EReference) event.getFeature();
				if (reference.isContainment()) {

					if (event.getEventType()==Notification.ADD) {
						((Notifier) event.getNewValue()).eAdapters().add(proxy);
					}
					else if (event.getEventType()==Notification.REMOVE) {
						((Notifier) event.getOldValue()).eAdapters().remove(proxy);
					}
					else if (event.getEventType()==Notification.SET) {
						if (event.getOldValue()!=null) {
							((Notifier) event.getOldValue()).eAdapters().remove(proxy);								
						}
						if (event.getNewValue()!=null) {
							((Notifier) event.getNewValue()).eAdapters().add(proxy);								
						}
					}
				}
			}
			
		}
		
		public Notifier getTarget() {
			return adapter.getTarget();
		}

		public boolean isAdapterForType(Object type) {
			return adapter.isAdapterForType(type);
		}

		public void setTarget(Notifier newTarget) {
			adapter.setTarget(newTarget);
		}

	};
		
}
