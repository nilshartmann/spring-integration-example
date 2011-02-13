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

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import nh.virgo.order.queries.FindAllLineItemsQuery;
import nh.virgo.order.queries.FindAllOrdersQuery;
import nh.virgo.order.queries.FindByCustomerAndEmail;
import nh.virgo.order.queries.FindByCustomerQuery;
import nh.virgo.order.queries.LineItemDto;
import nh.virgo.order.queries.OrderDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public class OrderQueryServiceImpl {
	private Logger _logger = LoggerFactory.getLogger(getClass());

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * nh.virgo.order.queries.OrderQueryService#findByCustomerName(nh.virgo.
	 * order.queries.FindByCustomerQuery)
	 */
	public String findByCustomerName(FindByCustomerQuery query)
			throws InterruptedException {
		_logger.info("Entered findByCustomerName sleeping "
				+ query.getCustomerName() + "...");
		long ms = Long.valueOf(query.getCustomerName());
		Thread.sleep(ms);
		_logger.info("findByCustomerName awake " + query.getCustomerName());
		return "findByCustomerName: " + query.getCustomerName();
	}

	public Collection<OrderDto> execute(FindAllOrdersQuery query) {
		_logger.info(" --> FindAllOrdersQuery invoked!");
		return dummyOrders();
	}

	public Collection<LineItemDto> findLineItems(FindAllLineItemsQuery query) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object find(FindByCustomerAndEmail email) {
		return "FindByCustomerAndEmail: " + email.getEmail();
	}

	private static List<OrderDto> dummyOrders() {
		LineItemDto lineItemDto = new LineItemDto("Item-1", 1);
		LineItemDto lineItemDto2 = new LineItemDto("Item-2", 2);

		List<LineItemDto> l = new LinkedList<LineItemDto>();
		l.add(lineItemDto);
		l.add(lineItemDto2);

		OrderDto order = new OrderDto("Order-1", "customer-1",
				"email@world.org", "ACTIVE", l);
		return Lists.newArrayList(order);

	}

}
