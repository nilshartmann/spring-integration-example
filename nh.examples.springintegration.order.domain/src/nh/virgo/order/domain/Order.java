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
package nh.virgo.order.domain;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.lang.String.format;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import nh.virgo.order.domain.events.LineItemAddedEvent;
import nh.virgo.order.domain.events.OrderCreatedEvent;
import nh.virgo.order.domain.events.OrderSubmittedEvent;
import nh.virgo.order.domain.events.QuantityChangedEvent;
import nh.virgo.order.framework.domain.AggregateRoot;
import nh.virgo.order.framework.domain.EntityIdentifier;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.util.StringUtils;

import com.google.common.collect.ImmutableList;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
@Configurable(preConstruction = true)
@Entity
@Table(name = "ORDER_AGGREGATE")
public class Order extends AggregateRoot {

	private String _customerName;

	private String _customerEmail;

	private OrderState _orderState;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<LineItem> _lineItems = new LinkedList<LineItem>();

	// ---------------------------------------------------------------------
	// -- Constructor --
	// ---------------------------------------------------------------------

	public Order(EntityIdentifier identifier) {
		super(identifier);
	}

	public Order(EntityIdentifier identifier, String customerName,
			String customerEmail) {
		super(identifier);
		checkNotNull(customerName);

		apply(new OrderCreatedEvent(identifier, customerName, customerEmail));
	}

	// JPA only
	Order() {
		super();

	}

	// ---------------------------------------------------------------------
	// -- Domain API --
	// ---------------------------------------------------------------------
	public void addLineItem(String item, int quantity) {
		checkNotNull(item);
		checkArgument(quantity > 0);
		ensureActiveOrder();

		if (hasLineItem(item)) {
			throw new OrderException("Item '%s' is already part of Order '%s'",
					item, this);
		}

		apply(new LineItemAddedEvent(getIdentifier(), item, quantity));
	}

	public void changeQuantity(String item, int newQuantity) {
		checkNotNull(item);
		ensureActiveOrder();

		if (newQuantity < 1) {
			throw new OrderException("Quantity must be at least 1");
		}

		apply(new QuantityChangedEvent(getIdentifier(), item, newQuantity));
	}

	public void submit() {
		ensureActiveOrder();

		apply(new OrderSubmittedEvent(getIdentifier()));
	}

	public String customerName() {
		return _customerName;
	}

	public String customerEmail() {
		return _customerEmail;
	}

	public OrderState state() {
		return _orderState;
	}

	public boolean isCustomerAbleToReceiveEmails() {
		return StringUtils.hasText(_customerEmail);
	}

	// ---------------------------------------------------------------------
	// -- Event Handler --
	// ---------------------------------------------------------------------

	@SuppressWarnings("unused")
	private void applyEvent(OrderCreatedEvent orderCreatedEvent) {
		checkNotNull(orderCreatedEvent);

		_orderState = OrderState.ACTIVE;
		_customerName = orderCreatedEvent.getCustomerName();
		_customerEmail = orderCreatedEvent.getCustomerEmail();
	}

	@SuppressWarnings("unused")
	private void applyEvent(LineItemAddedEvent event) {
		checkNotNull(event);
		ensureActiveOrder();

		LineItem newLineItem = new LineItem(event.getItem(),
				event.getQuantity());
		_lineItems.add(newLineItem);
	}

	@SuppressWarnings("unused")
	private void applyEvent(QuantityChangedEvent event) {
		checkNotNull(event);
		ensureActiveOrder();

		LineItem lineItem = getLineItem(event.getItem());
		lineItem.changeQuantity(event.getNewQuantity());
	}

	@SuppressWarnings("unused")
	private void applyEvent(OrderSubmittedEvent event) {
		checkNotNull(event);
		ensureActiveOrder();

		// Mark order as 'submitted'
		_orderState = OrderState.SUBMITTED;
	}

	public ImmutableList<LineItem> lineItems() {
		return ImmutableList.copyOf(_lineItems);
	}

	// ---------------------------------------------------------------------
	// -- Private --
	// ---------------------------------------------------------------------

	private LineItem getLineItem(final String item) {
		checkNotNull(item);

		for (LineItem lineItem : _lineItems) {
			if (item.equals(lineItem.getItem())) {
				return lineItem;
			}
		}

		throw new IllegalStateException(format(
				"No line item '%s' on Order '%s'", item, this));
	}

	private boolean hasLineItem(final String item) {
		checkNotNull(item);

		for (LineItem lineItem : _lineItems) {
			if (item.equals(lineItem.getItem())) {
				return true;
			}
		}

		return false;
	}

	private void ensureActiveOrder() {
		if (_orderState != OrderState.ACTIVE) {
			throw new OrderException("Order must be ACTIVE but is '{}'",
					_orderState);
		}
	}

	// ---------------------------------------------------------------------
	// -- toString --
	// ---------------------------------------------------------------------

	@Override
	public String toString() {
		return "Order [identifier=" + getIdentifier() + ", _customerName="
				+ _customerName + ", _lineItems=" + _lineItems + "]";
	}

}
