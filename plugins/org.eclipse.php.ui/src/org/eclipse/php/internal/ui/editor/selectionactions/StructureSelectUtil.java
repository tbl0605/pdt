/*******************************************************************************
 * Copyright (c) 2000, 2019 IBM Corporation and others.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Dawid Pakuła - PHP Adaptation
 *******************************************************************************/
package org.eclipse.php.internal.ui.editor.selectionactions;

import java.io.IOException;
import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ISourceRange;
import org.eclipse.dltk.core.ISourceReference;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.ui.editor.EditorUtility;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.Region;
import org.eclipse.php.core.ast.nodes.*;
import org.eclipse.php.internal.core.corext.dom.SelectionAnalyzer;
import org.eclipse.php.internal.core.documentModel.dom.ElementImplForPHP;
import org.eclipse.php.internal.ui.Logger;
import org.eclipse.php.ui.editor.SharedASTProvider;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.wst.sse.core.internal.provisional.IndexedRegion;

final class StructureSelectUtil {
	static ISourceModule getSourceModule(ExecutionEvent event) {
		IEditorPart editor = HandlerUtil.getActiveEditor(event);
		ITextEditor textEditor = null;
		if (editor instanceof ITextEditor)
			textEditor = (ITextEditor) editor;
		else {
			Object o = editor.getAdapter(ITextEditor.class);
			if (o != null)
				textEditor = (ITextEditor) o;
		}
		if (textEditor != null) {
			try {
				ISourceModule sourceModule = EditorUtility.getEditorInputModelElement(textEditor, false);
				ISourceRange sourceRange;
				sourceRange = sourceModule.getSourceRange();
				if (sourceRange == null || sourceRange.getLength() == 0) {
					MessageDialog.openInformation(textEditor.getEditorSite().getShell(),
							Messages.StructureSelect_error_title, Messages.StructureSelect_error_message);
					return null;
				}
				return sourceModule;
			} catch (ModelException e) {
				Logger.logException(e);
			}
		}

		return null;
	}

	static boolean isPHP(IndexedRegion indexedRegion) {
		return indexedRegion instanceof ElementImplForPHP && ((ElementImplForPHP) indexedRegion).isPHPTag();
	}

	static Program getAST(ISourceModule sourceModule) throws ModelException, IOException {
		return SharedASTProvider.getAST(sourceModule, SharedASTProvider.WAIT_YES, null);
	}

	static Region getLastCoveringNodeRange(Region oldSourceRange, ISourceReference sr, SelectionAnalyzer selAnalyzer)
			throws ModelException {
		if (selAnalyzer.getLastCoveringNode() == null)
			return oldSourceRange;
		else
			return getSelectedNodeSourceRange(sr, selAnalyzer.getLastCoveringNode());
	}

	static Region getSelectedNodeSourceRange(ISourceReference sr, ASTNode nodeToSelect) throws ModelException {
		int offset = nodeToSelect.getStart();
		int end = Math.min(sr.getSourceRange().getLength(), nodeToSelect.getStart() + nodeToSelect.getLength() - 1);
		return createSourceRange(offset, end);
	}

	static Region createSourceRange(int start, int end) {
		return new Region(start, end - start + 1);
	}

	static ASTNode[] getSiblingNodes(ASTNode node) {
		ASTNode parent = node.getParent();
		StructuralPropertyDescriptor locationInParent = node.getLocationInParent();
		if (locationInParent.isChildListProperty()) {
			List<? extends ASTNode> siblings = ASTNodes.getChildListProperty(parent,
					(ChildListPropertyDescriptor) locationInParent);
			return siblings.toArray(new ASTNode[siblings.size()]);
		}
		return null;
	}

	static int findIndex(Object[] array, Object o) {
		for (int i = 0; i < array.length; i++) {
			Object object = array[i];
			if (object == o)
				return i;
		}
		return -1;
	}
}
