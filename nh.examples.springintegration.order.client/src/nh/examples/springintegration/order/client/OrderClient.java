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
package nh.examples.springintegration.order.client;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import nh.client.framework.swing.DefaultApplicationDialog;
import nh.examples.springintegration.order.client.eventview.EventViewDialog;
import nh.examples.springintegration.order.client.orderoverview.OrderOverviewController;
import nh.examples.springintegration.order.client.orderoverview.OrderOverviewModel;
import nh.examples.springintegration.order.client.orderoverview.OrderOverviewView;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public class OrderClient {
	private static Logger _logger = LoggerFactory.getLogger(OrderClient.class);

	public static void main(String[] args) {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"/META-INF/spring/client-swing-ctx.xml");
		_logger.info(" Main gestartet ");

		ClientContext.createInstance(context);

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				setLnF();

				EventViewDialog.openEventViewDialog();

				OrderOverviewModel model = new OrderOverviewModel();
				OrderOverviewView view = new OrderOverviewView(model);
				OrderOverviewController controller = new OrderOverviewController(
						view);

				DefaultApplicationDialog dialog = new DefaultApplicationDialog(
						"Order Overview", view, controller);
				dialog.open();

			}
		});

	}

	private static void setLnF() {
		try {
			UIManager
					.setLookAndFeel("org.pushingpixels.substance.api.skin.SubstanceBusinessLookAndFeel");
			SubstanceLookAndFeel
					.setSkin("org.pushingpixels.substance.api.skin.OfficeBlue2007Skin");

		} catch (Exception e) {
			System.out.println("Substance Graphite failed to initialize");
		}

	}
}
