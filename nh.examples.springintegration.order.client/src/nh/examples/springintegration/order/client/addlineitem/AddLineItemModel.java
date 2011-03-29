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
package nh.examples.springintegration.order.client.addlineitem;

import static com.google.common.base.Preconditions.checkNotNull;
import nh.client.framework.swing.mvc.Model;
import nh.examples.springintegration.order.client.AddLineItemUseCase;
import nh.examples.springintegration.order.client.ClientContext;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public class AddLineItemModel extends Model {

	private final String _orderId;
	private String _item = "";
	private int _quantity;

	public AddLineItemModel(String orderId) {
		checkNotNull(orderId);
		_orderId = orderId;
	}

	public String getItem() {
		return _item;
	}

	public void setItem(String customerName) {
		_item = customerName;
	}

	public int getQuantity() {
		return _quantity;
	}

	public void setQuantitiy(String qty) {
		_quantity = Integer.parseInt(qty);
	}

	public void addLineItem() {
		AddLineItemUseCase addLineItemUseCase = ClientContext
				.getBean(AddLineItemUseCase.class);
		addLineItemUseCase.addLineItem(_orderId, _item, _quantity);
	}

}
