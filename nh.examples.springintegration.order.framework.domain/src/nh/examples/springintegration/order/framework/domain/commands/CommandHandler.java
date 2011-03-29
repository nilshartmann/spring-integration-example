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

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.Method;

import nh.examples.springintegration.order.framework.domain.internal.ReflectionHelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

/**
 * Base class for CommandHandler
 * 
 * <p>
 * This class provides a single (convenience) method for all types of commands.
 * The commands are dispatched to their appropriate <tt>execute</tt> methods
 * that must be implemented in subclasses.
 * 
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
@Transactional(propagation = Propagation.REQUIRES_NEW)
public abstract class CommandHandler {

	protected Logger _logger = LoggerFactory.getLogger(getClass());

	/**
	 * Dispatches the given {@link Command} to a specialized <tt>handle</tt>
	 * -Method on this class
	 * 
	 * <p>
	 * If no execute-method for the specified command can be found an Exception
	 * will be raised.
	 * 
	 * @param command
	 *            the command to be executed
	 */
	public final void handle(Command command) {
		checkNotNull(command);

		// Get handle method ready for invocation
		Method handleMethod = ReflectionHelper.getAccessibleMethod("handle",
				getClass(), command);

		// Invoke handle method
		ReflectionUtils.invokeMethod(handleMethod, this, command);
	}

}
