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
package nh.virgo.order.domain;

import static java.lang.String.format;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public class OrderException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OrderException(String message, Object... params) {
		super(format(message, params));
	}

}
