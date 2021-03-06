/**
 * <copyright>
 * Copyright (c) 2010-2012 Henshin developers. All rights reserved. 
 * This program and the accompanying materials are made available 
 * under the terms of the Eclipse Public License v1.0 which 
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * </copyright>
 */
package org.eclipse.emf.henshin.interpreter.matching.conditions;

public class NotFormula implements IFormula {
	
	private final IFormula child;
	
	public NotFormula(IFormula child) {
		this.child = child;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.interpreter.matching.conditions.IFormula#eval()
	 */
	@Override
	public boolean eval() {
		return !child.eval();
	}
	
}
