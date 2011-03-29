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
package nh.examples.springintegration.order.client.orderdetail;

import java.util.List;

import nh.client.framework.swing.table.DefaultTableModel;
import nh.examples.springintegration.order.queries.LineItemDto;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public class OrderDetailTableModel extends DefaultTableModel<LineItemDto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OrderDetailTableModel(List<LineItemDto> initialContent) {
		super(newColumnDescriptionBuilder().addString("Item")
				.addInt("Quantity").build(), initialContent);
	}

	@Override
	public Object getValue(LineItemDto rowData, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return rowData.getItem();
		case 1:
			return rowData.getQuantity();
		}
		throw new IllegalStateException("Invalid columnIndex: " + columnIndex);
	}

}
