/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Zend Technologies
 *******************************************************************************/
package org.eclipse.php.core.compiler.ast.nodes;

import java.util.List;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.TypeReference;

public class TraitPrecedence extends Expression {
	private FullyQualifiedTraitMethodReference methodReference;
	private List<TypeReference> trList;

	public TraitPrecedence(int start, int end, FullyQualifiedTraitMethodReference methodReference,
			List<TypeReference> trList) {
		super(start, end);
		this.methodReference = methodReference;
		this.trList = trList;
	}

	@Override
	public void traverse(ASTVisitor visitor) throws Exception {
		if (visitor.visit(this)) {
			if (methodReference != null) {
				methodReference.traverse(visitor);
			}
			if (trList != null) {
				for (TypeReference tr : trList) {
					tr.traverse(visitor);
				}
			}
			visitor.endvisit(this);
		}
	}

	public FullyQualifiedTraitMethodReference getMethodReference() {
		return methodReference;
	}

	public List<TypeReference> getTrList() {
		return trList;
	}

	@Override
	public int getKind() {
		// TODO Auto-generated method stub
		return 0;
	}

}
