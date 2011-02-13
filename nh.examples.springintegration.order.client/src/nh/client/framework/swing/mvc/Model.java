package nh.client.framework.swing.mvc;

import java.util.concurrent.CopyOnWriteArraySet;


public class Model {
	
	private final CopyOnWriteArraySet<ModelChangedListener> _modelChangedListeners = new CopyOnWriteArraySet<ModelChangedListener>();
	
	public void dispose() {
		_modelChangedListeners.clear();
	}
	
	public void addModelChangedListener(ModelChangedListener listener){
		_modelChangedListeners.add(listener);
	}
	
	protected void fireModelChangedEvent() {
		ModelChangedEvent modelChangedEvent = new ModelChangedEvent(this);
		
		for (ModelChangedListener listener : _modelChangedListeners) {
			try {
				listener.modelChanged(modelChangedEvent);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

}
