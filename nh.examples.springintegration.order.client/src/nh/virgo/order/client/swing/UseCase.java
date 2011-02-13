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
package nh.virgo.order.client.swing;

import static com.google.common.base.Preconditions.checkNotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import nh.virgo.order.framework.domain.commands.Command;
import nh.virgo.order.framework.domain.commands.CommandBus;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 *
 */
public class UseCase {
	
	Logger _logger = LoggerFactory.getLogger(getClass());
	
	private final CommandBus _commandBus;

	@Autowired
	public UseCase(CommandBus commandBus) {
		_commandBus = checkNotNull(commandBus);
	}
	
	protected void dispatch(Command command) {
		checkNotNull(command);
		
		_logger.info("Dispatching command '{}'", command);
		_commandBus.dispatch(command);
	}
	
	

}
