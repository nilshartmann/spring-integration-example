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

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public class XmlJmsMessageConverter implements MessageConverter {

	private XmlConverter _xmlConverter = new XStreamBasedXmlConverter();

	@Override
	public Object fromMessage(Message message) throws JMSException,
			MessageConversionException {
		TextMessage textMessage = (TextMessage) message;

		String xml = textMessage.getText();
		Object object = _xmlConverter.fromXml(xml);

		return object;
	}

	@Override
	public Message toMessage(Object object, Session session)
			throws JMSException, MessageConversionException {

		String xml = _xmlConverter.toXml(object);
		return session.createTextMessage(xml);

	}

	public void setXmlConverter(XmlConverter xmlConverter) {
		_xmlConverter = xmlConverter;
	}

}
