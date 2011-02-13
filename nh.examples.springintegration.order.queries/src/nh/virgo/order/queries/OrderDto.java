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

import java.util.List;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public class OrderDto {

	private final String _orderId;
	private final String _customerName;
	private final String _customerEmail;
	private final String _state;
	private final List<LineItemDto> _listItems;

	public OrderDto(String orderId, String customerName, String customerEmail,
			String state, List<LineItemDto> listItems) {
		super();
		_orderId = orderId;
		_customerName = customerName;
		_customerEmail = customerEmail;
		_state = state;
		_listItems = listItems;
	}

	public String getOrderId() {
		return _orderId;
	}

	public String getCustomerName() {
		return _customerName;
	}

	public String getCustomerEmail() {
		return _customerEmail;
	}

	public String getState() {
		return _state;
	}

	public List<LineItemDto> getListItems() {
		return _listItems;
	}

}
