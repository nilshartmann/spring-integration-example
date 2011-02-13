package nh.client.framework.swing.mvc;

public interface RequestHandler {

	/**
	 * Either handle the request or pass to successor
	 * 
	 * @param request
	 */
	public void handleRequest(Request request);

	public void setSuccessor(RequestHandler successor);

}
