package nh.client.framework.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import nh.client.framework.swing.mvc.Request;
import nh.client.framework.swing.mvc.RequestHandler;
import nh.client.framework.swing.mvc.View;

public class DefaultApplicationDialog implements RequestHandler {

	private final View<?> _view;
	private JFrame _dialog;

	public DefaultApplicationDialog(String title, View<?> view,
			RequestHandler requestHandler) {
		_view = view;
		_dialog = createDialog(title);
		requestHandler.setSuccessor(this);

	}

	@Override
	public void setSuccessor(RequestHandler successor) {

	}

	public void open() {
		_dialog.setVisible(true);
	}

	protected JFrame createDialog(String title) {
		JFrame dialog = new JFrame(title);
		dialog.setPreferredSize(new Dimension(600, 400));

		dialog.setLayout(new BorderLayout());
		dialog.add(_view, BorderLayout.CENTER);

		dialog.pack();

		// center on screen
		dialog.setLocationRelativeTo(null);

		return dialog;
	}

	@Override
	public void handleRequest(Request request) {
		if (request instanceof CloseDialogRequest) {
			_dialog.dispose();
		}

	}

}
