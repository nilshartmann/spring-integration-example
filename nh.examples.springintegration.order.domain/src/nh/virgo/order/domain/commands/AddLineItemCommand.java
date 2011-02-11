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
package nh.virgo.order.domain.commands;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import nh.virgo.order.framework.domain.EntityIdentifier;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public class AddLineItemCommand extends OrderCommand {

	private final String _item;
	private int _quantity;

	public AddLineItemCommand(EntityIdentifier orderIdentifier, String item,
			int quantity) {
		super(orderIdentifier);
		checkNotNull(item);
		checkArgument(quantity > 0, "Quantity must be greater than zero");
		_item = item;
		_quantity = quantity;
	}

	public int getQuantity() {
		return _quantity;
	}

	public void setQuantity(int quantity) {
		_quantity = quantity;
	}

	public String getItem() {
		return _item;
	}

	@Override
	public String toString() {
		return "AddLineItemCommand [_orderIdentifier=" + getOrderIdentifier()
				+ ", _item=" + _item + ", _quantity=" + _quantity + "]";
	}

}
