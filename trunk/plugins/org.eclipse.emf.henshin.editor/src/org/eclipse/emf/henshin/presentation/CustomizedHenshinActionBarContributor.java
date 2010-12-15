/*******************************************************************************
 * Copyright (c) 2010 CWI Amsterdam, Technical University Berlin, 
 * Philipps-University Marburg and others. All rights reserved. 
 * This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Philipps-University Marburg - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.henshin.presentation;

import java.util.Collection;
import java.util.EventObject;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.henshin.editor.commands.MenuContributor;
import org.eclipse.emf.henshin.editor.menuContributors.CreateMappingCommandMenuContributor;
import org.eclipse.emf.henshin.editor.menuContributors.CreateNestedConditionMenuContributor;
import org.eclipse.emf.henshin.editor.menuContributors.EdgeCommandMenuContributor;
import org.eclipse.emf.henshin.editor.menuContributors.FormulaCommandMenuContributor;
import org.eclipse.emf.henshin.editor.menuContributors.RemoveMappedNodesMenuContributor;
import org.eclipse.emf.henshin.editor.menuContributors.SimpleCommandMenuContributor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.ui.IEditorPart;

/**
 * 
 * Provides more complexe editing actions by forwarding the current selection to
 * a set of {@link MenuContributor}s. Manages {@link MenuManager}s holding the
 * {@link Action}s and actualizes them on {@link SelectionListener}- and
 * {@link CommandStackListener} events. Implementing
 * {@link CommandStackListener} is required since applicability of more complex
 * actions might depend on changes that would go undetected by selection change
 * only. e.g. property changes may affect applicability.
 * 
 * @author Gregor Bonifer
 * @author Stefan Jurack (sjurack)
 * 
 */
public class CustomizedHenshinActionBarContributor extends HenshinActionBarContributor implements
		CommandStackListener {
	
	protected Collection<IMenuManager> managedMenus = new HashSet<IMenuManager>();
	
	protected EditingDomain domain;
	
	protected List<?> currentSelection;
	
	@Override
	public void setActiveEditor(IEditorPart part) {
		super.setActiveEditor(part);
		if (part != null) {
			domain = ((IEditingDomainProvider) part).getEditingDomain();
			domain.getCommandStack().addCommandStackListener(this);
		} else {
			if (domain != null) {
				domain.getCommandStack().removeCommandStackListener(this);
			}
			domain = null;
		}
	}
	
	@Override
	public void commandStackChanged(EventObject event) {
		clearMenuManagers();
	}
	
	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		super.selectionChanged(event);
		if (event.getSelection() instanceof IStructuredSelection) {
			currentSelection = ((IStructuredSelection) event.getSelection()).toList();
		}
		refreshMenuManagers();
	}
	
	protected void clearMenuManagers() {
		for (IMenuManager mm : managedMenus) {
			mm.removeAll();
		}
	}
	
	protected void refreshMenuManagers() {
		for (IMenuManager mm : managedMenus) {
			mm.removeAll();
			buildContributions(mm);
		}
	}
	
	@Override
	public void menuAboutToShow(IMenuManager menuManager) {
		super.menuAboutToShow(menuManager);
		IMenuManager submenuManager = new MenuManager(
				HenshinEditorPlugin.INSTANCE.getString("_UI_CreateAdvanced_menu_item"));
		buildContributions(submenuManager);
		menuManager.insertBefore("edit", submenuManager);
	}
	
	@Override
	public void contributeToMenu(IMenuManager menuManager) {
		super.contributeToMenu(menuManager);
		IMenuManager subMenuManager = (IMenuManager) menuManager
				.find("org.eclipse.emf.henshin.modelMenuID");
		MenuManager mm = new MenuManager(
				HenshinEditorPlugin.INSTANCE.getString("_UI_CreateAdvanced_menu_item"));
		subMenuManager.setVisible(false);
		subMenuManager.insertBefore("additions", mm);
		this.managedMenus.add(mm);
	}
	
	/**
	 * 
	 * Delegating the actual build of {@link Action}s to {@link MenuContributor}
	 * s.
	 * 
	 * @param menuManager
	 */
	protected void buildContributions(IMenuManager menuManager) {
		SimpleCommandMenuContributor.INSTANCE.buildContributions(menuManager, currentSelection,
				domain);
		EdgeCommandMenuContributor.INSTANCE.buildContributions(menuManager, currentSelection,
				domain);
		RemoveMappedNodesMenuContributor.INSTANCE.buildContributions(menuManager, currentSelection,
				domain);
		CreateMappingCommandMenuContributor.INSTANCE.buildContributions(menuManager,
				currentSelection, domain);
		FormulaCommandMenuContributor.INSTANCE.buildContributions(menuManager, currentSelection,
				domain);
		CreateNestedConditionMenuContributor.INSTANCE.buildContributions(menuManager,
				currentSelection, domain);
		menuManager.update(true);
	}
	
}