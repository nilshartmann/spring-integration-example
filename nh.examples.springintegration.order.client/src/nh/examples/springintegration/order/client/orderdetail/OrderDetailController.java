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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import nh.client.framework.swing.CloseDialogRequest;
import nh.client.framework.swing.mvc.Controller;
import nh.client.framework.swing.mvc.RequestHandler;
import nh.examples.springintegration.order.client.addlineitem.AddLineItemDialog;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public class OrderDetailController extends
		Controller<OrderDetailModel, OrderDetailView> {

	public OrderDetailController(OrderDetailView view, RequestHandler successor) {
		super(view, successor);

		view.getCloseButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				handleRequest(new CloseDialogRequest());

			}
		});

		view.getAddLineItemButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddLineItemDialog.open(getModel().getOrder().getOrderId());

			}
		});
	}

	public OrderDetailController(OrderDetailView view) {
		this(view, null);
	}

}
