/**********************************************************************
 * Copyright (c) 2010 Nils Hartmann and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Nils Hartmann initial implementation
 **********************************************************************/
package nh.examples.springintegration.order.client.addorder;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import nh.client.framework.swing.mvc.ModelChangedEvent;
import nh.client.framework.swing.mvc.View;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public class AddOrderView extends View<AddOrderModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel _label;
	private JTextField _customerNameText;
	private JLabel _label_1;
	private JTextField _customerEMailText;
	private JButton _okButton;

	public AddOrderView(AddOrderModel model) {
		super(model);
	}

	protected void createUiComponents() {

		setPreferredSize(new Dimension(350, 150));

		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);

		_label = new JLabel("Customer Name");
		springLayout.putConstraint(SpringLayout.NORTH, _label, 10,
				SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, _label, 10,
				SpringLayout.WEST, this);
		add(_label);

		_customerNameText = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, _customerNameText, 4,
				SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, _customerNameText, 33,
				SpringLayout.EAST, _label);
		springLayout.putConstraint(SpringLayout.EAST, _customerNameText, -10,
				SpringLayout.EAST, this);
		add(_customerNameText);
		_customerNameText.setColumns(10);

		_label_1 = new JLabel("Customer E-Mail");
		springLayout.putConstraint(SpringLayout.NORTH, _label_1, 21,
				SpringLayout.SOUTH, _label);
		springLayout.putConstraint(SpringLayout.WEST, _label_1, 10,
				SpringLayout.WEST, this);
		add(_label_1);

		_customerEMailText = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, _customerEMailText, 15,
				SpringLayout.SOUTH, _customerNameText);
		springLayout.putConstraint(SpringLayout.WEST, _customerEMailText, 0,
				SpringLayout.WEST, _customerNameText);
		springLayout.putConstraint(SpringLayout.EAST, _customerEMailText, 0,
				SpringLayout.EAST, _customerNameText);
		add(_customerEMailText);
		_customerEMailText.setColumns(10);

		_okButton = new JButton("OK");
		springLayout.putConstraint(SpringLayout.NORTH, _okButton, 23,
				SpringLayout.SOUTH, _label_1);
		springLayout.putConstraint(SpringLayout.WEST, _okButton, 0,
				SpringLayout.WEST, _label);
		add(_okButton);
	}

	public JTextField getCustomerNameText() {
		return _customerNameText;
	}

	public void setCustomerNameText(JTextField customerNameText) {
		_customerNameText = customerNameText;
	}

	public JTextField getCustomerEMailText() {
		return _customerEMailText;
	}

	public void setCustomerEMailText(JTextField customerEMailText) {
		_customerEMailText = customerEMailText;
	}

	public JButton getOkButton() {
		return _okButton;
	}

	@Override
	protected void handle(ModelChangedEvent event) {
	}

}
