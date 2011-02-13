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

import nh.client.framework.swing.DefaultDialog;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public class AddOrderDialog {

	public static void open() {
		AddOrderModel model = new AddOrderModel();
		AddOrderView view = new AddOrderView(model);
		AddOrderController controller = new AddOrderController(view);

		DefaultDialog dialog = new DefaultDialog("New Order", view, controller);
		dialog.open();
	}

}
