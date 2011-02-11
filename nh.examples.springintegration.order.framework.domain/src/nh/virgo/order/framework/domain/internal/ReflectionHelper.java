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
package nh.virgo.order.framework.domain.internal;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.lang.String.format;

import java.lang.reflect.Method;

import org.springframework.util.ReflectionUtils;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public class ReflectionHelper {

	public static Method getAccessibleMethod(String name, Class<?> targetClass,
			Object parameter) {
		checkNotNull(name);
		checkNotNull(targetClass);
		checkNotNull(parameter);

		Method executeMethod = ReflectionUtils.findMethod(targetClass, name,
				parameter.getClass());
		if (executeMethod == null) {
			throw new IllegalStateException(
					format("No '%s'-method for command type %s found on class %s",
							name, parameter.getClass().getName(),
							targetClass.getName()));
		}
		ReflectionUtils.makeAccessible(executeMethod);

		return executeMethod;
	}

}
