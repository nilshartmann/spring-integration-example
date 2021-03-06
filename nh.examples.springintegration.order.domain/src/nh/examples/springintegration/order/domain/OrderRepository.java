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

import nh.examples.springintegration.order.framework.domain.Repository;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
@org.springframework.stereotype.Repository
public class OrderRepository extends Repository<Order> {

}
