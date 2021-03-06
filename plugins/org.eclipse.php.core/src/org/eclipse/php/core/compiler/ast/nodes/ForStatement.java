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

import java.util.Collection;
import java.util.List;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.utils.CorePrinter;
import org.eclipse.php.internal.core.compiler.ast.visitor.ASTPrintVisitor;

/**
 * Represents a for statement
 * 
 * <pre>
 * e.g.
 * 
 * for (expr1; expr2; expr3) statement;
 * 
 * for (expr1; expr2; expr3): statement ... endfor;
 * </pre>
 */
public class ForStatement extends Statement {

	private final List<? extends Expression> initializations;
	private final List<? extends Expression> conditions;
	private final List<? extends Expression> increasements;
	private final Statement action;

	public ForStatement(int start, int end, List<? extends Expression> initializations,
			List<? extends Expression> conditions, List<? extends Expression> increasements, Statement action) {
		super(start, end);
		assert initializations != null && conditions != null && increasements != null && action != null;
		this.initializations = initializations;
		this.conditions = conditions;
		this.increasements = increasements;
		this.action = action;
	}

	@Override
	public void traverse(ASTVisitor visitor) throws Exception {
		final boolean visit = visitor.visit(this);
		if (visit) {
			for (Expression initialization : initializations) {
				initialization.traverse(visitor);
			}
			for (Expression condition : conditions) {
				condition.traverse(visitor);
			}
			for (Expression increasement : increasements) {
				increasement.traverse(visitor);
			}
			action.traverse(visitor);
		}
		visitor.endvisit(this);
	}

	@Override
	public int getKind() {
		return ASTNodeKinds.FOR_STATEMENT;
	}

	public Statement getAction() {
		return action;
	}

	public Collection<? extends Expression> getConditions() {
		return conditions;
	}

	public Collection<? extends Expression> getIncreasements() {
		return increasements;
	}

	public Collection<? extends Expression> getInitializations() {
		return initializations;
	}

	/**
	 * We don't print anything - we use {@link ASTPrintVisitor} instead
	 */
	@Override
	public final void printNode(CorePrinter output) {
	}

	@Override
	public String toString() {
		return ASTPrintVisitor.toXMLString(this);
	}
}
