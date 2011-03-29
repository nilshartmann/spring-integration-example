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
package nh.examples.springintegration.order.domain;

import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.Message;
import org.springframework.integration.MessageHeaders;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public class SimpleEventListener {

	Logger _logger = LoggerFactory.getLogger(getClass());

	public void onEvent(Message<?> message) {
		_logger.info("Message received: " + message);
		MessageHeaders headers = message.getHeaders();
		Set<Entry<String, Object>> entries = headers.entrySet();
		for (Entry<String, Object> entry : entries) {
			_logger.info("\tHeader '{}' -> '{}'", entry.getKey(),
					entry.getValue());
		}
	}

}
