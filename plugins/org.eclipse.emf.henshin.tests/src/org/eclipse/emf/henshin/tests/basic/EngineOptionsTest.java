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
package org.eclipse.emf.henshin.tests.basic;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.henshin.interpreter.Engine;
import org.eclipse.emf.henshin.interpreter.Match;
import org.eclipse.emf.henshin.tests.framework.HenshinTest;
import org.eclipse.emf.henshin.tests.framework.Matches;
import org.eclipse.emf.henshin.tests.framework.Rules;
import org.eclipse.emf.henshin.tests.framework.Tools;
import org.junit.Before;
import org.junit.Test;

/**
 * tests several engine options
 * 
 * @author Felix Rieger
 * @author Stefan Jurack (sjurack)
 * 
 */
public class EngineOptionsTest extends HenshinTest {
	
	@Before
	public void setUp() throws Exception {
		init("basic/rules/engineOptionsTests.henshin");
		setModelGraphProperties("basic/models/engineOptionsTestsModels/", "testmodel");
	}
	
	@Test
	public void testNonInjective1() {
		/**
		 * graph:
		 * 
		 *      n1
		 *     /
		 *   n2
		 */
		loadGraph("nonInjective1");
		
		Collection<EObject> objGroup = new ArrayList<EObject>();
		objGroup.add(Tools.getFirstElementFromOCLQueryResult("self.nodename='n1'", htEGraph));
		objGroup.add(Tools.getFirstElementFromOCLQueryResult("self.nodename='n2'", htEGraph));
		objGroup.add(Tools.getFirstElementFromOCLQueryResult("self.nodename='n2'", htEGraph));
		
		loadRule("non-injectiveMatching");
		Rules.assertRuleHasNoMatch(htRule, htEGraph, null, htEngine); // Rule should have no match, as
												// we're looking for a Node with
												// two child nodes, but the
												// graph contains just a Node
												// with one child node.
		htEngine.getOptions().put(Engine.OPTION_INJECTIVE_MATCHING, Boolean.FALSE);
		loadRule("non-injectiveMatching");
		Rules.assertRuleHasNMatches(htRule, htEGraph, null, htEngine, 1); // Rule should have exactly 1
													// match
		Matches.assertOnlyGroupIsMatched(htRule, htEGraph, null, htEngine, objGroup); // This match
																// should
																// contain n1,
																// n2, n2
	}
	
	@Test
	public void testNonInjective2() {
		/**
		 * graph:
		 * 
		 *       n1
		 *      /  \
		 *    n2   n3
		 */
		loadGraph("nonInjective2");
		
		Collection<EObject> objGroup = new ArrayList<EObject>();
		objGroup.add(Tools.getFirstElementFromOCLQueryResult("self.nodename='n1'", htEGraph));
		objGroup.add(Tools.getFirstElementFromOCLQueryResult("self.nodename='n2'", htEGraph));
		objGroup.add(Tools.getFirstElementFromOCLQueryResult("self.nodename='n3'", htEGraph));
		
		loadRule("non-injectiveMatching");
		
		// assert Rule is correct for injective matching
		
		Rules.assertRuleHasNMatches(htRule, htEGraph, null, htEngine, 2); // expected matches: n1 <->
													// (n2, n3) ; n1 <-> (n3,
													// n2)
		Matches.assertOnlyGroupIsMatched(htRule, htEGraph, null, htEngine, objGroup);
		
		// turn off injective matching and try again
		
		Collection<EObject> ninjObjGroup1 = new ArrayList<EObject>();
		ninjObjGroup1
				.add(Tools.getFirstElementFromOCLQueryResult("self.nodename='n1'", htEGraph));
		ninjObjGroup1
				.add(Tools.getFirstElementFromOCLQueryResult("self.nodename='n2'", htEGraph));
		ninjObjGroup1
				.add(Tools.getFirstElementFromOCLQueryResult("self.nodename='n2'", htEGraph));
		
		Collection<EObject> ninjObjGroup2 = new ArrayList<EObject>();
		ninjObjGroup2
				.add(Tools.getFirstElementFromOCLQueryResult("self.nodename='n1'", htEGraph));
		ninjObjGroup2
				.add(Tools.getFirstElementFromOCLQueryResult("self.nodename='n3'", htEGraph));
		ninjObjGroup2
				.add(Tools.getFirstElementFromOCLQueryResult("self.nodename='n3'", htEGraph));
		
		htEngine.getOptions().put(Engine.OPTION_INJECTIVE_MATCHING, Boolean.FALSE);
		loadRule("non-injectiveMatching");
		Rules.assertRuleHasNMatches(htRule, htEGraph, null, htEngine, 4); // expected matches: n1 <->
													// (n2, n3) ; n1 <-> (n3,
													// n2) ; n1 <-> (n2, n2) ;
													// n1 <-> (n3, n3)
		
		for (Match ma : htEngine.findMatches(htRule, htEGraph, null)) {
			if (ma.getNodeTargets().contains(
					Tools.getFirstElementFromOCLQueryResult("self.nodename='n2'", htEGraph))) {
				if (ma.getNodeTargets().contains(
						Tools.getFirstElementFromOCLQueryResult("self.nodename='n3'", htEGraph))) {
					Matches.assertMatchIsGroup(ma, objGroup);
				} else {
					Matches.assertMatchIsGroup(ma, ninjObjGroup1);
				}
			} else {
				Matches.assertMatchIsGroup(ma, ninjObjGroup2);
			}
		}
		
	}
	
}