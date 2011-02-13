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
package nh.virgo.order.client.swing;

import static com.google.common.base.Preconditions.checkNotNull;

import org.springframework.context.ApplicationContext;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public class ClientContext {

	private static ClientContext _instance;

	static void createInstance(ApplicationContext applicationContext) {
		if (_instance != null) {
			throw new IllegalStateException("clientcontext already initialized");
		}
		_instance = new ClientContext(applicationContext);
	}

	private final ApplicationContext _applicationContext;

	private ClientContext(ApplicationContext applicationContext) {
		_applicationContext = checkNotNull(applicationContext);
	}

	public static <T> T getBean(Class<T> type) {
		checkNotNull(type);
		return _instance._applicationContext.getBean(type);
	}

}
