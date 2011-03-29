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
package nh.examples.springintegration.order.client.addorder;

import nh.client.framework.swing.mvc.Model;
import nh.examples.springintegration.order.client.ClientContext;
import nh.examples.springintegration.order.client.CreateOrderUseCase;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public class AddOrderModel extends Model {

	private String _customerName = "";
	private String _customerEMail = "";

	public String getCustomerName() {
		return _customerName;
	}

	public void setCustomerName(String customerName) {
		_customerName = customerName;
	}

	public String getCustomerEMail() {
		return _customerEMail;
	}

	public void setCustomerEMail(String customerEMail) {
		_customerEMail = customerEMail;
	}

	public void addOrder() {
		CreateOrderUseCase createOrderUseCase = ClientContext
				.getBean(CreateOrderUseCase.class);
		createOrderUseCase.newOrder(_customerName, _customerEMail);
	}

}
