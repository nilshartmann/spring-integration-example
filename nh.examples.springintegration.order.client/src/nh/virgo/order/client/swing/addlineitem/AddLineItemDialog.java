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
package nh.virgo.order.client.swing.addlineitem;

import nh.client.framework.swing.DefaultDialog;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public class AddLineItemDialog {

	public static void open(String orderId) {
		AddLineItemModel model = new AddLineItemModel(orderId);
		AddLineItemView view = new AddLineItemView(model);
		AddLineItemController controller = new AddLineItemController(view);

		DefaultDialog dialog = new DefaultDialog("Add Line Item", view,
				controller);
		dialog.open();
	}

}
