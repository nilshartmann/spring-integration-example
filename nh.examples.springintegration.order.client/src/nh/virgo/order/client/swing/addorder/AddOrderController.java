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
package nh.virgo.order.client.swing.addorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import nh.client.framework.swing.CloseDialogRequest;
import nh.client.framework.swing.mvc.Controller;
import nh.client.framework.swing.mvc.RequestHandler;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public class AddOrderController extends Controller<AddOrderModel, AddOrderView> {

	public AddOrderController(AddOrderView view, RequestHandler successor) {
		super(view, successor);

		view.getOkButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				getModel().setCustomerEMail(
						getView().getCustomerEMailText().getText());
				getModel().setCustomerName(
						getView().getCustomerNameText().getText());
				try {
					getModel().addOrder();
				} catch (Exception ex) {
					ex.printStackTrace();
					return;
				}

				handleRequest(new CloseDialogRequest());
			}
		});
	}

	public AddOrderController(AddOrderView view) {
		this(view, null);
	}

}
