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
import static java.lang.String.format;
import nh.examples.springintegration.order.domain.Order;

import org.springframework.stereotype.Component;

/**
 * A <b>Transformer</b> that creates a confirmation E-Mail for a specified
 * {@link Order}
 * 
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
@Component
public class OrderSubmittedConfirmationEMailTransformer {

	public String createOrderSubmittedEMail(Order order) {
		checkNotNull(order);

		return format(
				"Hi %s,%n%nYour order %s has been committed and is forwarded to our Shipping company. It will be shipped soon.%n%n",
				order.customerName(), order.getIdentifier().getId());
	}

}
