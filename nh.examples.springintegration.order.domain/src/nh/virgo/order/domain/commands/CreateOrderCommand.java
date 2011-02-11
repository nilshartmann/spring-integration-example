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
package nh.virgo.order.domain.commands;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import org.springframework.util.StringUtils;

import nh.virgo.order.framework.domain.commands.Command;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public class CreateOrderCommand extends Command {

	private final String _customerName;
	private final String _customerEmail;

	public CreateOrderCommand(String customerName, String customerEmail) {
		checkArgument(StringUtils.hasText(customerName),
				"A valid customer name must be specified");
		_customerName = checkNotNull(customerName);
		_customerEmail = checkNotNull(customerEmail);
	}

	public String getCustomerName() {
		return _customerName;
	}

	/**
	 * @return
	 */
	public String getCustomerEmail() {
		return _customerEmail;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((_customerEmail == null) ? 0 : _customerEmail.hashCode());
		result = prime * result
				+ ((_customerName == null) ? 0 : _customerName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreateOrderCommand other = (CreateOrderCommand) obj;
		if (_customerEmail == null) {
			if (other._customerEmail != null)
				return false;
		} else if (!_customerEmail.equals(other._customerEmail))
			return false;
		if (_customerName == null) {
			if (other._customerName != null)
				return false;
		} else if (!_customerName.equals(other._customerName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CreateOrderCommand [_customerName=" + _customerName
				+ ", _customerEmail=" + _customerEmail + "]";
	}

}
