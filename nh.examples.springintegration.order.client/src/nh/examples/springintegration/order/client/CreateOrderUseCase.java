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

import static com.google.common.base.Preconditions.checkNotNull;
import nh.examples.springintegration.order.domain.commands.CreateOrderCommand;
import nh.examples.springintegration.order.framework.domain.commands.CommandBus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
@Component
public class CreateOrderUseCase extends UseCase {

	@Autowired
	public CreateOrderUseCase(CommandBus commandBus) {
		super(commandBus);
	}

	public void newOrder(String customerName, String email) {
		checkNotNull(customerName);

		CreateOrderCommand createOrderCommand = new CreateOrderCommand(
				customerName, email);
		dispatch(createOrderCommand);
	}

}
