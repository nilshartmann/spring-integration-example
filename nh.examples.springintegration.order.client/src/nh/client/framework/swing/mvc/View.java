package nh.client.framework.swing.mvc;

import static nh.client.framework.swing.GuiExecutor.execute;

import javax.swing.JPanel;

public abstract class View<M extends Model> extends JPanel implements
		ModelChangedListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final M _model;

	protected View(M model) {
		super();
		_model = model;
		if (_model != null) {
			_model.addModelChangedListener(this);
		}

		createUiComponents();

	}

	protected abstract void createUiComponents();

	@Override
	public final void modelChanged(final ModelChangedEvent event) {
		execute(new Runnable() {

			@Override
			public void run() {
				handle(event);
			}
		});
	}

	public M getModel() {
		return _model;
	}

	protected void handle(ModelChangedEvent event) {

	}

}
