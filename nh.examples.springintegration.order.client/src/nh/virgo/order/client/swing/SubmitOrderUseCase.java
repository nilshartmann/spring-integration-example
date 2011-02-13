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

import nh.virgo.order.domain.commands.SubmitOrderCommand;
import nh.virgo.order.framework.domain.EntityIdentifier;
import nh.virgo.order.framework.domain.commands.CommandBus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
@Component
public class SubmitOrderUseCase extends UseCase {

	@Autowired
	public SubmitOrderUseCase(CommandBus commandBus) {
		super(commandBus);
	}

	public void submit(String orderId) {
		SubmitOrderCommand command = new SubmitOrderCommand(
				new EntityIdentifier(orderId));
		dispatch(command);
	}

}
