package nh.examples.springintegration.order.framework.domain;

import javax.persistence.Embeddable;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
@Embeddable
public class EntityIdentifier extends Identifier {

	public EntityIdentifier() {
		super();
	}

	public EntityIdentifier(String identifier) {
		super(identifier);
	}

}
