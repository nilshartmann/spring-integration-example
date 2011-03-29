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
package nh.examples.springintegration.order.queries;

import java.util.Collection;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public interface OrderQueryService {

	public String findByCustomerName(FindByCustomerQuery query);

	public Collection<OrderDto> findAll(FindAllOrdersQuery query);

	public Collection<LineItemDto> findLineItems(FindAllLineItemsQuery query);

	public Object findByEmail(FindByCustomerAndEmail emailQuery);

}