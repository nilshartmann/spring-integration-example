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
package nh.virgo.order.queries;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public class FindByCustomerAndEmail extends FindByCustomerQuery {

	private final String _email;

	public FindByCustomerAndEmail(String customerName, String email) {
		super(customerName);
		_email = email;
	}

	public String getEmail() {
		return _email;
	}

}
