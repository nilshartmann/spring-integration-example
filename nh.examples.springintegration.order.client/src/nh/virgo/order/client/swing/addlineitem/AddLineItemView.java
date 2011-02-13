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
package nh.virgo.order.client.swing.addlineitem;

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
public class AddLineItemView extends View<AddLineItemModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel _label;
	private JTextField _itemText;
	private JLabel _label_1;
	private JTextField _quantityText;
	private JButton _okButton;

	public AddLineItemView(AddLineItemModel model) {
		super(model);
	}

	protected void createUiComponents() {
		setPreferredSize(new Dimension(300, 150));

		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);

		_label = new JLabel("Item");
		springLayout.putConstraint(SpringLayout.NORTH, _label, 10,
				SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, _label, 10,
				SpringLayout.WEST, this);
		add(_label);

		_itemText = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, _itemText, 4,
				SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, _itemText, 33,
				SpringLayout.EAST, _label);
		springLayout.putConstraint(SpringLayout.EAST, _itemText, -10,
				SpringLayout.EAST, this);
		add(_itemText);
		_itemText.setColumns(10);

		_label_1 = new JLabel("Quantity");
		springLayout.putConstraint(SpringLayout.NORTH, _label_1, 21,
				SpringLayout.SOUTH, _label);
		springLayout.putConstraint(SpringLayout.WEST, _label_1, 10,
				SpringLayout.WEST, this);
		add(_label_1);

		_quantityText = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, _quantityText, 15,
				SpringLayout.SOUTH, _itemText);
		springLayout.putConstraint(SpringLayout.WEST, _quantityText, 0,
				SpringLayout.WEST, _itemText);
		springLayout.putConstraint(SpringLayout.EAST, _quantityText, 0,
				SpringLayout.EAST, _itemText);
		add(_quantityText);
		_quantityText.setColumns(10);

		_okButton = new JButton("OK");
		springLayout.putConstraint(SpringLayout.NORTH, _okButton, 23,
				SpringLayout.SOUTH, _label_1);
		springLayout.putConstraint(SpringLayout.WEST, _okButton, 0,
				SpringLayout.WEST, _label);
		add(_okButton);
	}

	public JTextField getItemText() {
		return _itemText;
	}

	public JTextField getQuantityText() {
		return _quantityText;
	}

	public JButton getOkButton() {
		return _okButton;
	}

	@Override
	protected void handle(ModelChangedEvent event) {
	}

}
