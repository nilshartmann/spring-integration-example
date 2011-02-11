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
package nh.virgo.order.framework.domain;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.lang.String.format;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public abstract class Repository<T extends AggregateRoot> {

	Logger _logger = LoggerFactory.getLogger(getClass());

	@PersistenceContext
	private EntityManager _entityManager;

	/**
	 * Saves the given aggregate root.
	 * 
	 * @param aggregateRoot
	 */
	@Transactional(propagation = Propagation.MANDATORY)
	public void save(T aggregateRoot) {
		checkNotNull(aggregateRoot);

		_logger.info("Saving {}", aggregateRoot);

		_entityManager.persist(aggregateRoot);
	}

	public T getById(EntityIdentifier entityIdentifier) {
		checkNotNull(entityIdentifier);

		T aggregateRoot = _entityManager.find(getAggregateType(),
				entityIdentifier);

		if (aggregateRoot == null) {
			throw new EntityNotFoundException(format(
					"Aggregate with id '{}' not found", entityIdentifier));
		}

		return aggregateRoot;

	}

	public List<T> getAll() {
		TypedQuery<T> query = _entityManager
				.createQuery(
						format("SELECT o FROM %s o", getAggregateType()
								.getSimpleName()), getAggregateType());
		return query.getResultList();
	}

	private Class<T> getAggregateType() {
		@SuppressWarnings("unchecked")
		Class<T> clazz = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		return clazz;
	}
}
