package org.eclipse.emf.henshin.interpreter;

import org.eclipse.emf.henshin.model.Rule;

/**
 * Rule application interface for executing a {@link Rule}.
 * {@link RuleApplication}s are reusable. To reuse an instance
 * you must invoke {@link #setPartialMatch(Match)} or
 * {@link #setCompleteMatch(Match)} before every execution. 
 * 
 * @author Christian Krause
 */
public interface RuleApplication extends UnitApplication {

	/**
	 * Get the rule to be applied.
	 * @return The rule to be applied.
	 */
	Rule getRule();

	/**
	 * Set the rule to be applied.
	 * @param rule The rule to be applied.
	 */
	void setRule(Rule rule);

	/**
	 * Get the partial match to be used.
	 * @return The partial match.
	 */
	Match getPartialMatch();

	/**
	 * Set the partial match to be used.
	 * @param partialMatch The partial match.
	 */
	void setPartialMatch(Match partialMatch);

	/**
	 * Get the complete match to be used.
	 * @return The complete match.
	 */
	Match getCompleteMatch();

	/**
	 * Set the complete match to be used.
	 * @param completeMatch The complete match.
	 */
	void setCompleteMatch(Match completeMatch);

	/**
	 * Get the match for the result of the transformation (after the unit has been applied).
	 * @return The match for the result.
	 */
	Match getResultMatch();

}
