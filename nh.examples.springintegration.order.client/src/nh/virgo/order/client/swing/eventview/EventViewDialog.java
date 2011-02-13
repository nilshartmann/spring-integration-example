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
package nh.virgo.order.client.swing.eventview;

import javax.swing.JDialog;

import nh.client.framework.swing.DefaultDialog;
import nh.client.framework.swing.mvc.RequestHandler;
import nh.client.framework.swing.mvc.View;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public class EventViewDialog extends DefaultDialog {

	public static EventViewDialog openEventViewDialog() {
		EventViewModel model = new EventViewModel();
		EventViewView view = new EventViewView(model);
		EventViewController controller = new EventViewController(view);

		EventViewDialog dialog = new EventViewDialog(view, controller);
		dialog.open();
		return dialog;
	}

	private EventViewDialog(View<?> view, RequestHandler requestHandler) {
		super("Event Browser", view, requestHandler);
	}

	@Override
	protected JDialog createDialog(String title) {
		JDialog dialog = super.createDialog(title);
		dialog.setModal(false);
		return dialog;
	}

}
