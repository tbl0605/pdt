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
package org.eclipse.php.internal.core.typeinference.evaluators;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.dltk.ti.GoalState;
import org.eclipse.dltk.ti.goals.ExpressionTypeGoal;
import org.eclipse.dltk.ti.goals.GoalEvaluator;
import org.eclipse.dltk.ti.goals.IGoal;
import org.eclipse.dltk.ti.types.IEvaluatedType;
import org.eclipse.php.core.compiler.ast.nodes.ArrayCreation;
import org.eclipse.php.core.compiler.ast.nodes.ArrayElement;
import org.eclipse.php.internal.core.typeinference.PHPTypeInferenceUtils;

public class ArrayCreationEvaluator extends GoalEvaluator {

	private List<IEvaluatedType> evaluated = new LinkedList<>();

	public ArrayCreationEvaluator(IGoal goal) {
		super(goal);
	}

	@Override
	public IGoal[] init() {
		ExpressionTypeGoal typedGoal = (ExpressionTypeGoal) goal;
		ArrayCreation arrayCreation = (ArrayCreation) typedGoal.getExpression();

		List<IGoal> subGoals = new LinkedList<>();
		for (ArrayElement arrayElement : arrayCreation.getElements()) {
			subGoals.add(new ExpressionTypeGoal(typedGoal.getContext(), arrayElement.getValue()));
		}
		return subGoals.toArray(new IGoal[subGoals.size()]);
	}

	@Override
	public Object produceResult() {
		return PHPTypeInferenceUtils.combineMultiType(evaluated);
	}

	@Override
	public IGoal[] subGoalDone(IGoal subgoal, Object result, GoalState state) {
		if (state != GoalState.RECURSIVE) {
			evaluated.add((IEvaluatedType) result);
		}
		return IGoal.NO_GOALS;
	}
}
