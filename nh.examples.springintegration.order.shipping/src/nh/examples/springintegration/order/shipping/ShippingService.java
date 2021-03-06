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
package nh.examples.springintegration.order.shipping;

import static com.google.common.base.Preconditions.checkNotNull;
import nh.examples.springintegration.order.domain.events.OrderSubmittedEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public class ShippingService {

	protected transient Logger _logger = LoggerFactory.getLogger(getClass());

	public void handle(OrderSubmittedEvent event) {
		checkNotNull(event);

		_logger.info("Handling OrderSubmittedEvent '{}'", event);

	}

}
