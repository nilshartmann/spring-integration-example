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
import static java.lang.String.format;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableList;

import nh.virgo.order.domain.LineItem;
import nh.virgo.order.domain.Order;
import nh.virgo.order.domain.OrderRepository;
import nh.virgo.order.domain.events.OrderSubmittedEvent;

/**
 * A <b>Transformer</b> that transforms an {@link OrderSubmittedEvent} into a
 * (fake) "ShippingOrder" that is sent to a (fake) Shipping Service
 * 
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
@Component
public class OrderSubmittedEventTransformer {

	protected transient Logger _logger = LoggerFactory.getLogger(getClass());

	private final OrderRepository _orderRepository;

	@Autowired
	public OrderSubmittedEventTransformer(OrderRepository orderRepository) {
		super();
		_orderRepository = orderRepository;
	}

	public String newShippingOrder(OrderSubmittedEvent orderSubmittedEvent) {
		checkNotNull(orderSubmittedEvent);
		_logger.info("Transforming OrderSubmittedEvent '{}'",
				orderSubmittedEvent);

		Order order = _orderRepository.getById(orderSubmittedEvent
				.getSubmittedOrderIdentifier());

		ShippingOrderBuilder builder = new ShippingOrderBuilder();
		builder.append("Shipping Order:  #%s", order.getIdentifier().getId())
				.newLine();
		builder.append("Received:        %1$td.%1$tm.%1$tY %1$tT", new Date())
				.newLine();
		builder.append("Customer Name:   %s", order.customerName()).newLine();
		builder.append(
				"+------------------------------------------------+-------+")
				.newLine();
		builder.append(
				"I Item                                           I  Qty  I")
				.newLine();
		builder.append(
				"+------------------------------------------------+-------+")
				.newLine();

		ImmutableList<LineItem> lineItems = order.lineItems();
		for (LineItem lineItem : lineItems) {
			builder.append("I %-45.45s  I %5d I", lineItem.getItem(),
					lineItem.getQuantity());
			builder.newLine();
		}

		builder.append(
				"+------------------------------------------------+-------+")
				.newLine();

		return builder.buildRequest();

	}

	private class ShippingOrderBuilder {
		private final StringBuilder _builder = new StringBuilder();

		public ShippingOrderBuilder append(String template, Object... args) {
			_builder.append(format(template, args));
			return this;
		}

		public ShippingOrderBuilder newLine() {
			_builder.append(System.getProperty("line.separator"));
			return this;
		}

		public String buildRequest() {
			return _builder.toString();
		}
	}

}
