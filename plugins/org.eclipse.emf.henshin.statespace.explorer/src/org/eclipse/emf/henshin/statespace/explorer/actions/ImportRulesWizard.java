package org.eclipse.emf.henshin.statespace.explorer.actions;

import java.util.List;

import org.eclipse.emf.henshin.model.Rule;
import org.eclipse.emf.henshin.statespace.StateSpace;
import org.eclipse.emf.henshin.statespace.explorer.parts.StateSpaceExplorer;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.Wizard;

/**
 * A wizard for importing rules into a statespace.
 * @author Christian Krause
 */
public class ImportRulesWizard extends Wizard {
	
	// State space explorer to be used:
	private StateSpaceExplorer explorer;
	
	// Rules page:
	private ImportRulesPage rulesPage;
	
	/**
	 * Default constructor.
	 * @param explorer Explorer to be used.
	 */
	public ImportRulesWizard(StateSpaceExplorer explorer) {
		this.explorer = explorer;
		setNeedsProgressMonitor(false);
		setWindowTitle("Import Rules");
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	@Override
    public void addPages() {
		
		// The state space:
		StateSpace stateSpace = explorer.getStateSpaceManager().getStateSpace();
		
		// Create the rule page:
		rulesPage = new ImportRulesPage();
		rulesPage.setStateSpaceResource(stateSpace.eResource());
		rulesPage.getRules().addAll(stateSpace.getRules());
		addPage(rulesPage);
		
    }
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		
		List<Rule> newRules = rulesPage.getRules();
		List<Rule> oldRules = explorer.getStateSpaceManager().getStateSpace().getRules();
		
		if (oldRules.size()!=newRules.size() || !oldRules.containsAll(newRules)) {
			oldRules.clear();
			oldRules.addAll(newRules);
			
			if (MessageDialog.openQuestion(getShell(), "Reset", "Do you want to reset the state space (highly recommended)?")) {
				explorer.getStateSpaceManager().resetStateSpace();
			}
		}
		
		return true;
	}
	
}