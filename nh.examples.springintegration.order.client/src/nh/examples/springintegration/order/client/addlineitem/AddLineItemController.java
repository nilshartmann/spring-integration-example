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
package nh.examples.springintegration.order.client.addlineitem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import nh.client.framework.swing.CloseDialogRequest;
import nh.client.framework.swing.mvc.Controller;
import nh.client.framework.swing.mvc.RequestHandler;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public class AddLineItemController extends
		Controller<AddLineItemModel, AddLineItemView> {

	public AddLineItemController(AddLineItemView view, RequestHandler successor) {
		super(view, successor);

		view.getOkButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				getModel().setQuantitiy(getView().getQuantityText().getText());
				getModel().setItem(getView().getItemText().getText());
				try {
					getModel().addLineItem();
				} catch (Exception ex) {
					ex.printStackTrace();
					return;
				}

				handleRequest(new CloseDialogRequest());
			}
		});
	}

	public AddLineItemController(AddLineItemView view) {
		this(view, null);
	}

}
