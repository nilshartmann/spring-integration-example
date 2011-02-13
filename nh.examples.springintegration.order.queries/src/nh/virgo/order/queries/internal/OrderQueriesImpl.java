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
package nh.virgo.order.queries.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableList;

import nh.virgo.order.domain.LineItem;
import nh.virgo.order.domain.Order;
import nh.virgo.order.domain.OrderRepository;
import nh.virgo.order.queries.LineItemDto;
import nh.virgo.order.queries.OrderDto;
import nh.virgo.order.queries.OrderQueries;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
@Component
public class OrderQueriesImpl implements OrderQueries {

	protected transient Logger _logger = LoggerFactory.getLogger(getClass());

	private final OrderRepository _orderRepository;

	@Autowired
	public OrderQueriesImpl(OrderRepository orderRepository) {
		checkNotNull(orderRepository);
		_orderRepository = orderRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nh.virgo.order.queries.OrderQueries#findAllOrders(java.lang.String)
	 */
	@Override
	public List<OrderDto> findAllOrders(String dummy) {
		_logger.info("findAllOrders() invoked");
		List<Order> allOrders = _orderRepository.getAll();

		List<OrderDto> result = new LinkedList<OrderDto>();
		for (Order order : allOrders) {
			result.add(toDto(order));
		}

		_logger.info("Query Result: " + result);

		return result;
	}

	/**
	 * @param order
	 * @return
	 */
	private OrderDto toDto(Order order) {

		ImmutableList<LineItem> lineItems = order.lineItems();
		List<LineItemDto> lineItemDtos = new LinkedList<LineItemDto>();
		for (LineItem lineItem : lineItems) {
			lineItemDtos.add(toDto(lineItem));
		}

		OrderDto dto = new OrderDto(order.getIdentifier().getId(),
				order.customerName(), order.customerEmail(),
				String.valueOf(order.state()), lineItemDtos);
		return dto;
	}

	/**
	 * @param lineItem
	 * @return
	 */
	private LineItemDto toDto(LineItem lineItem) {
		return new LineItemDto(lineItem.getItem(), lineItem.getQuantity());
	}

}
