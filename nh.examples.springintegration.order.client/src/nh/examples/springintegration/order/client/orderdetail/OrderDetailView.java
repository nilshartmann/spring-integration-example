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

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;

import nh.client.framework.swing.mvc.View;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public class OrderDetailView extends View<OrderDetailModel> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton _refreshButton;
	private JButton _addLineItemButton;
	private JButton _closeButton;
	private JScrollPane _scrollPane;
	private JTable _table;

	public OrderDetailView(OrderDetailModel model) {
		super(model);
	}

	@Override
	protected void createUiComponents() {
		setPreferredSize(new Dimension(600, 400));
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);

		_refreshButton = new JButton("Refresh");
		springLayout.putConstraint(SpringLayout.NORTH, _refreshButton, 10,
				SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, _refreshButton, -10,
				SpringLayout.EAST, this);
		add(_refreshButton);

		_addLineItemButton = new JButton("Add Line Item");
		springLayout.putConstraint(SpringLayout.WEST, _addLineItemButton, 10,
				SpringLayout.WEST, this);
		add(_addLineItemButton);

		_closeButton = new JButton("Close");
		springLayout.putConstraint(SpringLayout.SOUTH, _closeButton, -10,
				SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, _addLineItemButton, 0,
				SpringLayout.NORTH, _closeButton);
		springLayout.putConstraint(SpringLayout.EAST, _closeButton, 0,
				SpringLayout.EAST, _refreshButton);
		add(_closeButton);

		_table = createTable();
		_scrollPane = new JScrollPane(_table);
		springLayout.putConstraint(SpringLayout.NORTH, _scrollPane, 10,
				SpringLayout.SOUTH, _refreshButton);
		springLayout.putConstraint(SpringLayout.WEST, _scrollPane, 10,
				SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, _scrollPane, -6,
				SpringLayout.NORTH, _addLineItemButton);
		springLayout.putConstraint(SpringLayout.EAST, _scrollPane, -10,
				SpringLayout.EAST, this);
		add(_scrollPane);
	}

	protected JTable createTable() {
		JTable table = new JTable();
		table.getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new OrderDetailTableModel(getModel().getOrder()
				.getListItems()));
		table.getColumnModel().getColumn(0).setPreferredWidth(125);
		table.getColumnModel().getColumn(1).setPreferredWidth(70);

		table.setFillsViewportHeight(true);

		return table;

	}

	public JButton getRefreshButton() {
		return _refreshButton;
	}

	public JButton getAddLineItemButton() {
		return _addLineItemButton;
	}

	public JButton getCloseButton() {
		return _closeButton;
	}

	public JTable getTable() {
		return _table;
	}

}
