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
package nh.virgo.order.client.swing.orderdetail;

import static com.google.common.base.Preconditions.checkNotNull;
import nh.client.framework.swing.mvc.Model;
import nh.virgo.order.queries.LineItemDto;
import nh.virgo.order.queries.OrderDto;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public class OrderDetailModel extends Model {

	private final OrderDto _order;

	private LineItemDto _selectedLineItem;

	public OrderDetailModel(OrderDto order) {
		checkNotNull(order);
		_order = order;
	}

	public OrderDto getOrder() {
		return _order;
	}

	public LineItemDto getSelectedLineItem() {
		return _selectedLineItem;
	}

	public void setSelectedLineItem(LineItemDto selectedLineItem) {
		_selectedLineItem = selectedLineItem;
	}

}
