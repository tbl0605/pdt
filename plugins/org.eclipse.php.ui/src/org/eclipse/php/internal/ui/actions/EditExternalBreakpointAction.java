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
/**
 * 
 */
package org.eclipse.php.internal.ui.actions;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.wst.sse.ui.internal.debug.EditBreakpointAction;

/**
 * Edit breakpoint action that supports external files.
 * 
 * @author shalom
 * 
 */
public class EditExternalBreakpointAction extends EditBreakpointAction {

	/**
	 * @param editor
	 * @param rulerInfo
	 */
	public EditExternalBreakpointAction(ITextEditor editor, IVerticalRuler rulerInfo) {
		super(editor, rulerInfo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.sse.ui.internal.debug.BreakpointRulerAction#hasMarkers()
	 */
	@Override
	protected boolean hasMarkers() {
		return ExternalBreakpointActionHelper.hasMarkers(getTextEditor(), getResource(), getDocument(),
				getAnnotationModel(), getRulerInfo());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.sse.ui.internal.debug.BreakpointRulerAction#getMarkers()
	 */
	@Override
	protected IMarker[] getMarkers() {
		return ExternalBreakpointActionHelper.getMarkers(getTextEditor(), getResource(), getDocument(),
				getAnnotationModel(), getRulerInfo());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.sse.ui.internal.debug.EditBreakpointAction#update()
	 */
	@Override
	public void update() {
		breakpoints = getBreakpoints(getMarkers());
		boolean enableThisAction = hasMarkers() && breakpoints.length > 0;
		setEnabled(enableThisAction);
	}
}
