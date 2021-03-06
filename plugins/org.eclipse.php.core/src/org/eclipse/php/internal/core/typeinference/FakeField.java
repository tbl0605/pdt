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
package org.eclipse.php.internal.core.typeinference;

import org.eclipse.dltk.ast.Modifiers;
import org.eclipse.dltk.core.ISourceRange;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.SourceRange;
import org.eclipse.dltk.internal.core.ModelElement;
import org.eclipse.dltk.internal.core.SourceField;

/**
 * This is a fake model element that can live independently from the DLTK model
 * manager.
 * 
 * @author michael
 */
public class FakeField extends SourceField {
	private int offset;
	private int length;
	private int modifiers = Modifiers.AccPublic;

	public FakeField(ModelElement parent, String name, int offset, int length) {
		super(parent, name);
		this.offset = offset;
		this.length = length;
	}

	public FakeField(ModelElement parent, String name, int offset, int length, int modifiers) {
		super(parent, name);
		this.offset = offset;
		this.length = length;
		this.modifiers = modifiers;
	}

	public FakeField(ModelElement parent, String name, int modifiers) {
		super(parent, name);
		this.modifiers = modifiers;
	}

	@Override
	public ISourceRange getNameRange() throws ModelException {
		return new SourceRange(offset, length);
	}

	@Override
	public ISourceRange getSourceRange() throws ModelException {
		return new SourceRange(offset, length);
	}

	@Override
	public int getFlags() {
		return modifiers;
	}

	@Override
	public boolean equals(Object o) {
		if (super.equals(o) && o instanceof FakeField) {
			FakeField other = (FakeField) o;
			return other.offset == offset && other.length == length;
		}
		return false;
	}

	@Override
	public Object getElementInfo() throws ModelException {
		// return null instead of throwing ModelException
		// It sounds making sense, as the Fake field don't have element info at
		// all.
		// https://bugs.eclipse.org/bugs/show_bug.cgi?id=297579
		return null;
	}

}