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
public class ChangeQuantityCommand extends OrderCommand {

	private final String _item;
	private final int _newQuantity;

	public ChangeQuantityCommand(EntityIdentifier orderIdentifier, String item,
			int newQuantity) {
		super(orderIdentifier);
		checkNotNull(item);
		checkArgument(newQuantity > 0, "New quantity must be greater than zero");

		_item = item;
		_newQuantity = newQuantity;
	}

	public String getItem() {
		return _item;
	}

	public int getNewQuantity() {
		return _newQuantity;
	}

	@Override
	public String toString() {
		return "ChangeQuantityCommand [_item=" + _item + ", _newQuantity="
				+ _newQuantity + "]";
	}
}
