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

import java.util.LinkedList;
import java.util.List;

import nh.examples.springintegration.order.queries.LineItemDto;
import nh.examples.springintegration.order.queries.OrderDto;
import nh.examples.springintegration.order.queries.OrderQueries;
import nh.examples.springintegration.order.utils.XStreamBasedXmlConverter;

import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
@Component
public class DummyOrders implements OrderQueries {

	public static List<OrderDto> dummyOrders() {
		LineItemDto lineItemDto = new LineItemDto("Item-1", 1);
		LineItemDto lineItemDto2 = new LineItemDto("Item-2", 2);

		List<LineItemDto> l = new LinkedList<LineItemDto>();
		l.add(lineItemDto);
		l.add(lineItemDto2);

		OrderDto order = new OrderDto("Order-1", "customer-1",
				"email@world.org", "ACTIVE", l);
		return Lists.newArrayList(order);

	}

	@Override
	public List<OrderDto> findAllOrders(String dummy) {
		return dummyOrders();
	}

	public static void main(String[] args) {
		XStreamBasedXmlConverter c = new XStreamBasedXmlConverter();
		List<OrderDto> dummyOrders = dummyOrders();
		String xml = c.toXml(dummyOrders);
		Object o = c.fromXml(xml);
	}

}
