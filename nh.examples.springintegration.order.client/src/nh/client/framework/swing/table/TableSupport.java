package nh.client.framework.swing.table;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class TableSupport {

	public static <C> void addSelectionListener(final JTable table,
			final SimpleTableSelectionListener<C> selectionListener) {
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					ListSelectionModel model = table.getSelectionModel();
					int viewRowIndex = model.getMinSelectionIndex();
					if (viewRowIndex < 0) {
						selectionListener.rowSelected(null);
						return;
					}
					int selectedRow = table.convertRowIndexToModel(viewRowIndex);
					@SuppressWarnings("unchecked")
					DefaultTableModel<C> tableModel = (DefaultTableModel<C>) table.getModel();
					C rowData = tableModel.getRowData(selectedRow);
					selectionListener.rowSelected(rowData);
				}
			}
		});
	}

}
