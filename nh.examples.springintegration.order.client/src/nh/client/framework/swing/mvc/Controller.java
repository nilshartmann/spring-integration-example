package nh.client.framework.swing.mvc;

public class Controller<M extends Model, V extends View<M>> implements RequestHandler {

	private final V					_view;
	private RequestHandler	_successor;

	protected Controller(V view) {
		this(view, null);
	}

	protected Controller(V view, RequestHandler successor) {
		_view = view;
		_successor = successor;
	}

	public void setSuccessor(RequestHandler successor) {
		_successor = successor;
	}

	protected M getModel() {
		return _view.getModel();
	}

	protected V getView() {
		return this._view;
	}

	@Override
	public void handleRequest(Request request) {
		if (_successor != null) {
			_successor.handleRequest(request);
		}

	}

}
