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
package org.eclipse.php.internal.core.documentModel.loader;

import java.util.List;

import org.eclipse.php.internal.core.documentModel.DOMModelForPHP;
import org.eclipse.wst.html.core.internal.encoding.HTMLModelLoader;
import org.eclipse.wst.sse.core.internal.document.IDocumentLoader;
import org.eclipse.wst.sse.core.internal.provisional.IModelLoader;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;

public class PHPModelLoader extends HTMLModelLoader {

	@Override
	public IDocumentLoader getDocumentLoader() {
		if (documentLoaderInstance == null) {
			documentLoaderInstance = new PHPDocumentLoader();
		}
		return documentLoaderInstance;
	}

	@Override
	public IModelLoader newInstance() {
		return new PHPModelLoader();
	}

	@Override
	public List<?> getAdapterFactories() {

		// @GINO: Might want to add new adapter factories here
		return super.getAdapterFactories();
	}

	// Creating the PHPModel
	@Override
	public IStructuredModel newModel() {
		return new DOMModelForPHP();
	}
}
