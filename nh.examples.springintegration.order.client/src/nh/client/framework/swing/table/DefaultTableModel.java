package nh.client.framework.swing.table;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public abstract class DefaultTableModel<C> extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final List<C> _content;
	private final ColumnDescription[] _columnDescriptions;

	public DefaultTableModel(ColumnDescription[] columnDescriptions,
			List<C> initialContent) {
		super();
		_columnDescriptions = columnDescriptions;
		_content = new LinkedList<C>();
		if (initialContent != null) {
			_content.addAll(initialContent);
		}
	}

	public void setNewContent(List<C> newContent) {
		_content.clear();
		_content.addAll(newContent);

		fireTableDataChanged();
	}

	public List<C> getContent() {
		return _content;
	}

	public C getRowData(int row) {
		return _content.get(row);
	}

	@Override
	public int getRowCount() {
		return _content.size();
	}

	@Override
	public int getColumnCount() {
		return _columnDescriptions.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		C rowData = _content.get(rowIndex);
		return getValue(rowData, columnIndex);
	}

	public abstract Object getValue(C rowData, int columnIndex);

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return _columnDescriptions[columnIndex].getType();
	}

	@Override
	public String getColumnName(int columnIndex) {
		return _columnDescriptions[columnIndex].getName();
	}

	public static class ColumnDescription {

		private final String _name;
		private final Class<?> _type;

		public ColumnDescription(String name, Class<?> type) {
			super();
			_name = name;
			_type = type;
		}

		public String getName() {
			return _name;
		}

		public Class<?> getType() {
			return _type;
		}

	}

	public static class ColumnDescriptionBuilder {

		private final List<ColumnDescription> _descriptions = new LinkedList<DefaultTableModel.ColumnDescription>();

		public ColumnDescriptionBuilder addString(String name) {
			_descriptions.add(new ColumnDescription(name, String.class));
			return this;
		}

		public ColumnDescriptionBuilder addInt(String name) {
			_descriptions.add(new ColumnDescription(name, Integer.class));
			return this;
		}

		public ColumnDescriptionBuilder add(String name, Class<?> type) {
			_descriptions.add(new ColumnDescription(name, type));
			return this;
		}

		public ColumnDescription[] build() {
			return _descriptions.toArray(new ColumnDescription[_descriptions
					.size()]);
		}
	}

	public static ColumnDescriptionBuilder newColumnDescriptionBuilder() {
		return new ColumnDescriptionBuilder();
	}

}
