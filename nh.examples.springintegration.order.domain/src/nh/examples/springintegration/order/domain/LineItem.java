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
package nh.examples.springintegration.order.domain;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
@Entity
public class LineItem {

	@SuppressWarnings("unused")
	@Id
	@GeneratedValue
	private String id;

	/**
	 * The item (e.g. "CD Pink Floyd - Ummagumma")
	 * 
	 * <p>
	 * An item can be added to an order only once, thus it works as an
	 * identifier
	 */
	private String _item;
	private int _quantity;

	public LineItem(String item, int quantity) {
		checkNotNull(item);
		checkArgument(quantity > 0);

		_item = item;
		_quantity = quantity;
	}

	LineItem() {
		// JPA only
	}

	public String getItem() {
		return _item;
	}

	public int getQuantity() {
		return _quantity;
	}

	@Override
	public String toString() {
		return "LineItem [_item=" + _item + ", _quantity=" + _quantity + "]";
	}

	/**
	 * @param newQuantity
	 */
	public void changeQuantity(int newQuantity) {
		this._quantity = newQuantity;
	}

}
