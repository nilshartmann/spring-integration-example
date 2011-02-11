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
package nh.virgo.order.domain.events;

import static com.google.common.base.Preconditions.checkNotNull;
import nh.virgo.order.framework.domain.EntityIdentifier;
import nh.virgo.order.framework.domain.events.Event;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public class QuantityChangedEvent extends Event {
	private final String _item;
	private final int _newQuantity;

	public QuantityChangedEvent(EntityIdentifier entityIdentifier, String item,
			int newQuantity) {
		super(entityIdentifier);
		_item = checkNotNull(item);
		_newQuantity = newQuantity;
	}

	public String getItem() {
		return _item;
	}

	public int getNewQuantity() {
		return _newQuantity;
	}

}
