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
package nh.examples.springintegration.order.client.eventview;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;

import nh.client.framework.swing.mvc.ModelChangedEvent;
import nh.client.framework.swing.mvc.View;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public class EventViewView extends View<EventViewModel> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton _closeButton;
	private JScrollPane _scrollPane;
	private JTable _table;

	public EventViewView(EventViewModel model) {
		super(model);
	}

	@Override
	protected void createUiComponents() {
		setPreferredSize(new Dimension(800, 300));
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);

		_closeButton = new JButton("Close");
		springLayout.putConstraint(SpringLayout.SOUTH, _closeButton, -10,
				SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, _closeButton, -10,
				SpringLayout.EAST, this);
		add(_closeButton);

		_table = createTable();
		_scrollPane = new JScrollPane(_table);
		springLayout.putConstraint(SpringLayout.NORTH, _scrollPane, 10,
				SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, _scrollPane, 10,
				SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, _scrollPane, -10,
				SpringLayout.NORTH, _closeButton);
		springLayout.putConstraint(SpringLayout.EAST, _scrollPane, -10,
				SpringLayout.EAST, this);
		add(_scrollPane);

	}

	protected JTable createTable() {
		JTable table = new JTable();
		table.getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new EventViewTableModel());
		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setPreferredWidth(125);
		table.getColumnModel().getColumn(2).setPreferredWidth(125);

		table.setFillsViewportHeight(true);

		return table;

	}

	protected EventViewTableModel getEventViewTableModel() {
		return (EventViewTableModel) _table.getModel();
	}

	@Override
	protected void handle(ModelChangedEvent event) {
		getEventViewTableModel().setNewContent(getModel().getEvents());
	}

	public JButton getCloseButton() {
		return _closeButton;
	}

	public JTable getTable() {
		return _table;
	}

}
