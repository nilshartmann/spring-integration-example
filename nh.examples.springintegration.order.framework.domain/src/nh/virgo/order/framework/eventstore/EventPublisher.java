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
package nh.virgo.order.framework.eventstore;

import nh.virgo.order.framework.domain.events.Event;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public interface EventPublisher {

	public void publish(Event event);
}