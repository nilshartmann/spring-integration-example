package nh.virgo.order.client.swing.eventview;

import nh.client.framework.swing.table.DefaultTableModel;
import nh.virgo.order.framework.domain.events.Event;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public class EventViewTableModel extends DefaultTableModel<Event> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EventViewTableModel() {
		super(newColumnDescriptionBuilder().addString("Event")
				.addString("Event-Id").addString("Aggregate Id")
				.addString("Additional Information").build(), null);
	}

	@Override
	public Object getValue(Event rowData, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return rowData.getEventName();
		case 1:
			return rowData.getEventId();
		case 2:
			return rowData.getAggregateRootIdentifier().getId();
		case 3:
			return rowData.toString();
		}

		throw new IllegalArgumentException("Invalid columnIndex: "
				+ columnIndex);
	}

}
