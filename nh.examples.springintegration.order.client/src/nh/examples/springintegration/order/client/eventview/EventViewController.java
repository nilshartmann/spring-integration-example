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

import nh.client.framework.swing.mvc.Controller;
import nh.client.framework.swing.mvc.RequestHandler;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public class EventViewController extends
		Controller<EventViewModel, EventViewView> {

	public EventViewController(EventViewView view, RequestHandler successor) {
		super(view, successor);

	}

	public EventViewController(EventViewView view) {
		this(view, null);
	}

}
