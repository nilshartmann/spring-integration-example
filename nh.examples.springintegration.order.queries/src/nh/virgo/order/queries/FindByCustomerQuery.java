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

import java.io.Serializable;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public class FindByCustomerQuery extends OrderQuery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String _customerName;

	public FindByCustomerQuery(String customerName) {
		super();
		_customerName = customerName;
	}

	public String getCustomerName() {
		return _customerName;
	}

}
