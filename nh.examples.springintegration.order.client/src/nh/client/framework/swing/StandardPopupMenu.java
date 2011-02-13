package nh.client.framework.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class StandardPopupMenu {

	private final JPopupMenu _popupMenu;
	private final List<JMenuItem> _menuItems;
	private Object _eventHandler;

	private StandardPopupMenu(JPopupMenu popupMenu, List<JMenuItem> menuItems) {
		super();
		_popupMenu = popupMenu;
		_menuItems = menuItems;
	}

	public JPopupMenu getPopupMenu() {
		return _popupMenu;
	}

	public List<JMenuItem> getMenuItems() {
		return _menuItems;
	}

	public void addToComponent(JComponent component) {
		component.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				maybeShowPopup(e);
			}

			public void mouseReleased(MouseEvent e) {
				maybeShowPopup(e);
			}

			private void maybeShowPopup(MouseEvent e) {
				if (e.isPopupTrigger()) {
					_popupMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
	}

	public void setEventHandler(Object model) {
		if (_eventHandler != null) {
			throw new IllegalStateException("A model has already been set");
		}

		_eventHandler = model;

		for (JMenuItem jMenuItem : _menuItems) {
			jMenuItem.addActionListener(new ModelDelegatingActionListener(
					(String) jMenuItem.getClientProperty("menuitem.name")));
		}
	}

	public static PopupMenuBuilder newPopupMenuBuilder() {
		return new PopupMenuBuilder();
	}

	public static class PopupMenuBuilder {

		private final JPopupMenu _popupMenu;
		private final List<JMenuItem> _menuItems;

		private PopupMenuBuilder() {
			_popupMenu = new JPopupMenu();
			_menuItems = new LinkedList<JMenuItem>();
		}

		public PopupMenuBuilder addItem(String title, String name) {
			JMenuItem menuItem = new JMenuItem(title);
			menuItem.putClientProperty("menuitem.name", name);
			_popupMenu.add(menuItem);
			_menuItems.add(menuItem);
			return this;
		}

		public PopupMenuBuilder separator() {
			_popupMenu.addSeparator();
			return this;
		}

		public StandardPopupMenu build() {
			return new StandardPopupMenu(_popupMenu, _menuItems);
		}

	}

	class ModelDelegatingActionListener implements ActionListener {

		private final String _name;

		public ModelDelegatingActionListener(String name) {
			super();
			_name = name;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				Method method = _eventHandler.getClass().getMethod(
						"execute" + _name);
				method.invoke(_eventHandler);
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}

		}

	}

}
