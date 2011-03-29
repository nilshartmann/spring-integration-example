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
package nh.examples.springintegration.order.domain.commandhandler;

import static com.google.common.base.Preconditions.checkNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import nh.examples.springintegration.order.domain.Order;
import nh.examples.springintegration.order.domain.OrderRepository;
import nh.examples.springintegration.order.domain.commands.AddLineItemCommand;
import nh.examples.springintegration.order.domain.commands.ChangeQuantityCommand;
import nh.examples.springintegration.order.domain.commands.CreateOrderCommand;
import nh.examples.springintegration.order.domain.commands.OrderCommand;
import nh.examples.springintegration.order.framework.domain.EntityIdentifier;
import nh.examples.springintegration.order.framework.domain.commands.CommandHandler;
import nh.examples.springintegration.order.framework.eventstore.EventPublisher;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
@Component
@Transactional(propagation=Propagation.REQUIRES_NEW)
public class OrderCommandHandler extends CommandHandler {

	private final OrderRepository _orderRepository;

	@Autowired
	public OrderCommandHandler(OrderRepository orderRepository) {
		_orderRepository = checkNotNull(orderRepository);
	}

	public void handle(CreateOrderCommand command) {
		checkNotNull(command);

		// Create new Order
		EntityIdentifier newIdentifier = new EntityIdentifier();
		Order newOrder = new Order(newIdentifier, command.getCustomerName(),
				command.getCustomerEmail());
		_logger.info("new Order created: " + newOrder);
		EventPublisher eventPublisher = newOrder.getEventPublisher();
		_logger.info("new Order created eventPublisher: " + eventPublisher);

		// Save new Order to Repository
		_orderRepository.save(newOrder);
	}

	public void handle(AddLineItemCommand command) {
		checkNotNull(command);

		// Read order
		Order order = readOrderForCommand(command);

		// add new item
		order.addLineItem(command.getItem(), command.getQuantity());

		// save order
		_orderRepository.save(order);
	}

	public void handle(ChangeQuantityCommand command) {
		checkNotNull(command);

		// read order
		Order order = readOrderForCommand(command);

		// change quantity to new value
		order.changeQuantity(command.getItem(), command.getNewQuantity());

		// save
		_orderRepository.save(order);

	}

	public void handle(nh.examples.springintegration.order.domain.commands.SubmitOrderCommand command) {
		checkNotNull(command);

		// read order
		Order order = readOrderForCommand(command);

		// Submit the order
		order.submit();

		// save
		_orderRepository.save(order);
	}

	/**
	 * Read the {@link Order} that is referenced in the specified
	 * {@link OrderCommand} from the {@link OrderRepository}
	 * 
	 * @param orderCommand
	 * @return The Order. If no Order can be found, an Exception is raised.
	 */
	private Order readOrderForCommand(OrderCommand orderCommand) {
		checkNotNull(orderCommand);

		EntityIdentifier orderIdentifier = orderCommand.getOrderIdentifier();
		Order order = _orderRepository.getById(orderIdentifier);

		return order;

	}
}
