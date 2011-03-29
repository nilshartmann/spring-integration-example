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
package nh.examples.springintegration.order.framework.domain.commands;

/**
 * A <b>Messaging Gateway</b>, that dispatches a <b>{@link Command}</b> to a
 * {@link CommandHandler} that is able to handle it
 * 
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public interface CommandBus {

	/**
	 * Dispatches the specified command.
	 * 
	 * @param command
	 */
	public void dispatch(Command command);

}
