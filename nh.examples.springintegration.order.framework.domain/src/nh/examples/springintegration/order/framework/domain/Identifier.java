/**********************************************************************
 * Copyright (c) 2010 Nils Hartmann and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Nils Hartmann initial implementation
 **********************************************************************/
package nh.examples.springintegration.order.framework.domain;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.UUID;

import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
@MappedSuperclass
public class Identifier {

	private String identifier;

	public Identifier() {
		identifier = UUID.randomUUID().toString();
	}

	public Identifier(String identifier) {
		this.identifier = checkNotNull(identifier);
	}

	@Override
	public String toString() {
		return "Identifier [_id=" + identifier + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((identifier == null) ? 0 : identifier.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Identifier other = (Identifier) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		return true;
	}

	public String getId() {
		return identifier;
	}

}
