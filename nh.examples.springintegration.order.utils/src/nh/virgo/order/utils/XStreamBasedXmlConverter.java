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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.mapper.Mapper;
import com.thoughtworks.xstream.mapper.MapperWrapper;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public class XStreamBasedXmlConverter implements XmlConverter {

	static Logger _logger = LoggerFactory
			.getLogger(XStreamBasedXmlConverter.class);

	private final XStream _xstream;

	public XStreamBasedXmlConverter() {
		_xstream = createConfiguredInstance();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nh.virgo.contact.utils.XmlConverter#toXml(java.lang.Object)
	 */
	@Override
	public String toXml(Object o) {
		return _xstream.toXML(o);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nh.virgo.contact.utils.XmlConverter#fromXml(java.lang.String)
	 */
	@Override
	public Object fromXml(String xml) {
		return _xstream.fromXML(xml);

	}

	private static XStream createConfiguredInstance() {
		XStream xstream = new XStream(new DomDriver()) {

			@Override
			protected MapperWrapper wrapMapper(MapperWrapper next) {
				return new FieldPrefixStrippingMapper(next);
			}

		};

		return xstream;
	}

	/**
	 * taken from src/com/thoughtworks/acceptance/CustomMapperTest
	 * 
	 */
	private static class FieldPrefixStrippingMapper extends MapperWrapper {
		public FieldPrefixStrippingMapper(Mapper wrapped) {
			super(wrapped);
		}

		public String serializedMember(
				@SuppressWarnings("rawtypes") Class type, String memberName) {
			_logger.trace("memberName: '{}'", memberName);
			if (memberName.startsWith("_")) {
				// _blah -> blah
				memberName = memberName.substring(1); // chop off leading char
														// (the underscore)

			}
			return super.serializedMember(type, memberName);
		}

		public String realMember(@SuppressWarnings("rawtypes") Class type,
				String serialized) {
			String fieldName = super.realMember(type, serialized);
			_logger.trace("realMember, serialized: '{}', fieldName: '{}'",
					serialized, fieldName);
			// Not very efficient or elegant, but enough to get the point
			// across.
			// Luckily the CachingMapper will ensure this is only ever called
			// once per field per class.

			if (hasField(type, "_" + fieldName)) {
				_logger.trace(
						"realMember, serialized: '{}', fieldName: '{}' using _",
						serialized, fieldName);
				return "_" + fieldName;
			}
			_logger.trace(
					"realMember, serialized: '{}', fieldName: '{}' using same fieldname",
					serialized, fieldName);
			return fieldName;
		}

		private boolean hasField(Class<?> type, String name) {
			return ReflectionUtils.findField(type, name) != null;

		}
	}

}
