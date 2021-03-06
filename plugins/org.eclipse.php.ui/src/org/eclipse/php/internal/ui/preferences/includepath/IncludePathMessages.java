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
package org.eclipse.php.internal.ui.preferences.includepath;

import org.eclipse.osgi.util.NLS;

public class IncludePathMessages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.php.internal.ui.preferences.includepath.IncludePathMessages"; //$NON-NLS-1$

	public static String BuildPathDialogAccess_ZIPArchiveDialog_new_title;
	public static String BuildPathDialogAccess_ZIPArchiveDialog_new_description;

	public static String BuildPathDialogAccess_ZIPArchiveDialog_edit_title;
	public static String BuildPathDialogAccess_ZIPArchiveDialog_edit_description;

	public static String BuildPathDialogAccess_ExtZIPArchiveDialog_new_title;
	public static String BuildPathDialogAccess_ExtZIPArchiveDialog_edit_title;

	public static String LibrariesWorkbookPage_libraries_addzip_button;
	public static String LibrariesWorkbookPage_libraries_addextzip_button;
	public static String LibrariesWorkbookPage_libraries_replace_button;
	public static String LibrariesWorkbookPage_libraries_addvariables_button;

	public static String BuildPathsBlock_warning_EntryInvalid;

	public static String BuildPathsBlock_warning_EntriesInvalid;

	public static String CPListLabelProvider_invalid;

	public static String FilteredElementTreeSelectionDialog_1;

	private IncludePathMessages() {
		// Do not instantiate
	}

	static {
		NLS.initializeMessages(BUNDLE_NAME, IncludePathMessages.class);
	}
}
