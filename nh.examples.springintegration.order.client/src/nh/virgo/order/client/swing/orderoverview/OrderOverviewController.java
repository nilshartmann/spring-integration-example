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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import nh.client.framework.swing.StandardPopupMenu;
import nh.client.framework.swing.mvc.Controller;
import nh.client.framework.swing.mvc.RequestHandler;
import nh.client.framework.swing.table.SimpleTableSelectionListener;
import nh.client.framework.swing.table.TableSupport;
import nh.virgo.order.client.swing.addorder.AddOrderDialog;
import nh.virgo.order.client.swing.orderdetail.OrderDetailDialog;
import nh.virgo.order.queries.OrderDto;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public class OrderOverviewController extends
		Controller<OrderOverviewModel, OrderOverviewView> {

	public OrderOverviewController(OrderOverviewView view,
			RequestHandler successor) {
		super(view, successor);

		view.getRefreshButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				getModel().refreshOrders();
			}
		});

		view.getAddOrderButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddOrderDialog.open();
			}
		});

		TableSupport.addSelectionListener(view.getTable(),
				new SimpleTableSelectionListener<OrderDto>() {

					@Override
					public void rowSelected(OrderDto rowdata) {
						getModel().setSelectedOrder(rowdata);
					}
				});

		StandardPopupMenu tablePopupMenu = view.getTablePopupMenu();
		tablePopupMenu.setEventHandler(this);
		tablePopupMenu.addToComponent(view.getTable());

	}

	public OrderOverviewController(OrderOverviewView view) {
		this(view, null);
	}

	public void executeEditOrder() {
		OrderDto order = getModel().getSelectedOrder();
		if (order == null) {
			return;
		}

		OrderDetailDialog.open(order);
	}

	public void executeSubmitOrder() {
		getModel().submitSelectedOrder();
	}

}
