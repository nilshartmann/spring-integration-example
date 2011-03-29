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

import nh.client.framework.swing.table.DefaultTableModel;
import nh.examples.springintegration.order.queries.OrderDto;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public class OrderOverviewTableModel extends DefaultTableModel<OrderDto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OrderOverviewTableModel() {
		super(newColumnDescriptionBuilder().addString("Order Id")
				.addString("Customer Name").addString("E-Mail")
				.addString("State").addInt("Line Items").build(), null);
	}

	@Override
	public Object getValue(OrderDto rowData, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return rowData.getOrderId();
		case 1:
			return rowData.getCustomerName();
		case 2:
			return rowData.getCustomerEmail();
		case 3:
			return rowData.getState();
		case 4:
			return rowData.getListItems().size();
		}

		throw new IllegalArgumentException("Invalid columnIndex: "
				+ columnIndex);
	}

}
