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
package nh.virgo.order.domain.shipping;

import static com.google.common.base.Preconditions.checkNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nh.virgo.order.domain.Order;
import nh.virgo.order.domain.OrderRepository;
import nh.virgo.order.domain.events.OrderSubmittedEvent;

/**
 * A <b>Transformer</b> that "transforms" the message to the referenced type in
 * a given <b>Event</b>
 * 
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
@Component
public class ToOrderTransformer {

	private OrderRepository _orderRepository;

	@Autowired
	public ToOrderTransformer(OrderRepository orderRepository) {
		super();
		_orderRepository = orderRepository;
	}

	public Order toOrder(OrderSubmittedEvent event) {
		checkNotNull(event);
		Order order = _orderRepository.getById(event
				.getSubmittedOrderIdentifier());
		return order;
	}

}
