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
public class LineItemDto {

	private final String _item;
	private final int _quantity;

	public LineItemDto(String item, int quantity) {
		super();
		_item = item;
		_quantity = quantity;
	}

	public String getItem() {
		return _item;
	}

	public int getQuantity() {
		return _quantity;
	}

}
