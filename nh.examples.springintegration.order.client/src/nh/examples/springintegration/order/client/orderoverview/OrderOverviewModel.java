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
package nh.examples.springintegration.order.client.orderoverview;

import java.util.LinkedList;
import java.util.List;

import nh.client.framework.swing.mvc.Model;
import nh.examples.springintegration.order.client.ClientContext;
import nh.examples.springintegration.order.client.SubmitOrderUseCase;
import nh.examples.springintegration.order.queries.OrderDto;
import nh.examples.springintegration.order.queries.OrderQueries;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public class OrderOverviewModel extends Model {

	private List<OrderDto> _orders = new LinkedList<OrderDto>();
	private OrderDto _selectedOrder;

	public void refreshOrders() {
		_orders = getOrderQueries().findAllOrders("");
		fireModelChangedEvent();
	}

	public List<OrderDto> getOrders() {
		return _orders;
	}

	private OrderQueries getOrderQueries() {
		return ClientContext.getBean(OrderQueries.class);
	}

	public OrderDto getSelectedOrder() {
		return _selectedOrder;
	}

	public void setSelectedOrder(OrderDto selectedOrder) {
		_selectedOrder = selectedOrder;
	}

	/**
	 * 
	 */
	public void submitSelectedOrder() {
		if (_selectedOrder == null) {
			return;
		}

		SubmitOrderUseCase submitOrderUseCase = ClientContext
				.getBean(SubmitOrderUseCase.class);
		submitOrderUseCase.submit(_selectedOrder.getOrderId());

	}

}
