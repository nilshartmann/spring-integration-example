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
package nh.virgo.order.utils;

/**
 * Converts a java object to XML and vice-versa
 * 
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public interface XmlConverter {

	/**
	 * Converts the given object to xml
	 * 
	 * @param o
	 * @return
	 */
	public String toXml(Object o);

	/**
	 * Converts the given xml to a {@link Object}
	 * 
	 * @param xml
	 * @return
	 */
	public abstract Object fromXml(String xml);

}