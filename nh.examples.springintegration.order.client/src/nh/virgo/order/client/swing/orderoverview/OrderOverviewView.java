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
package nh.virgo.order.client.swing.orderoverview;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;

import nh.client.framework.swing.StandardPopupMenu;
import nh.client.framework.swing.mvc.ModelChangedEvent;
import nh.client.framework.swing.mvc.View;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public class OrderOverviewView extends View<OrderOverviewModel> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton _refreshButton;
	private JScrollPane _scrollPane;
	private JTable _table;

	private StandardPopupMenu _tablePopupMenu;
	private JButton _addOrderButton;

	public OrderOverviewView(OrderOverviewModel model) {
		super(model);

	}

	@Override
	protected void createUiComponents() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);

		_refreshButton = new JButton("Refresh");
		springLayout.putConstraint(SpringLayout.EAST, _refreshButton, -20,
				SpringLayout.EAST, this);
		add(_refreshButton);
		_table = createTable();
		_scrollPane = new JScrollPane(_table);
		springLayout.putConstraint(SpringLayout.SOUTH, _refreshButton, -6,
				SpringLayout.NORTH, _scrollPane);
		springLayout.putConstraint(SpringLayout.NORTH, _scrollPane, 39,
				SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, _scrollPane, 10,
				SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, _scrollPane, -44,
				SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, _scrollPane, -20,
				SpringLayout.EAST, this);
		add(_scrollPane);

		_addOrderButton = new JButton("Add Order");
		springLayout.putConstraint(SpringLayout.WEST, _addOrderButton, 10,
				SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, _addOrderButton, -10,
				SpringLayout.SOUTH, this);
		add(_addOrderButton);

		_tablePopupMenu = createTablePopupMenu();

	}

	private StandardPopupMenu createTablePopupMenu() {
		return StandardPopupMenu.newPopupMenuBuilder()
				.addItem("Edit Order", "EditOrder")
				.addItem("Submit Order", "SubmitOrder").build();

	}

	public StandardPopupMenu getTablePopupMenu() {
		return _tablePopupMenu;
	}

	protected JTable createTable() {
		JTable table = new JTable();
		table.getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new OrderOverviewTableModel());
		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setPreferredWidth(125);
		table.getColumnModel().getColumn(2).setPreferredWidth(125);

		table.setFillsViewportHeight(true);

		return table;

	}

	public JButton getRefreshButton() {
		return _refreshButton;
	}

	public JTable getTable() {
		return _table;
	}

	public OrderOverviewTableModel getOrderTableModel() {
		return (OrderOverviewTableModel) _table.getModel();
	}

	@Override
	protected void handle(ModelChangedEvent event) {
		getOrderTableModel().setNewContent(getModel().getOrders());
	}

	public JButton getAddOrderButton() {
		return _addOrderButton;
	}

}
