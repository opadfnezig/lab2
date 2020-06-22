package Frame;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import exceptions.NotUniqueElementException;
import goods.Good;
import goods.Group;
import utils.Pair;

public class EditGoodsFrame extends JFrame{
	private JComboBox<String> groupField, goodsField;
	private JTextField nameField, manufacturerField;
	private JTextArea descriptionField;
	private JSpinner priceField;
	private JButton cancel, edit;
	
	private MainFrame main;
	
	public EditGoodsFrame(MainFrame main) {
		super("Edit goods");
		this.setSize(400, 400);
		this.setResizable(false);
		
		this.main = main;
		
		nameField = new JTextField();
		manufacturerField = new JTextField();
		descriptionField = new JTextArea("Nothing");
		priceField = new JSpinner();
		
		cancel = new JButton("Cancel");
		edit = new JButton("Edit");
		
		groupField = new JComboBox<String>();
		for(Group g:main.base)
			groupField.addItem(g.getName());
		
		goodsField = new JComboBox<String>();
		updateGoods();
		
		this.setLayout(new GridLayout(0, 1));
		this.add(new JLabel("Group"));
		this.add(groupField);
		this.add(new JLabel("Goods"));
		this.add(goodsField);
		this.add(new JLabel("Goods name"));
		this.add(nameField);
		this.add(new JLabel("Goods manufacturer"));
		this.add(manufacturerField);
		this.add(new JLabel("Goods price"));
		this.add(priceField);
		this.add(new JLabel("Group description"));
		this.add(descriptionField);
		
		JPanel pl = new JPanel(new FlowLayout());
		pl.add(edit);
		pl.add(cancel);
		
		priceField.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				if((int)priceField.getValue() < 0)
					priceField.setValue(0);
			}
		});
		
		groupField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				updateGoods();
			}
		});
		
		goodsField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				updateTextFields();
			}
		});
		
		edit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					main.base.editGood((String)goodsField.getSelectedItem(), 
							new Good(nameField.getText(), descriptionField.getText(),
							manufacturerField.getText(), (int)priceField.getValue()));
					main.initFileTree();
					exit();
				} catch (NotUniqueElementException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} catch (IllegalArgumentException iae) {
					JOptionPane.showMessageDialog(null, iae.getMessage());
				}
				
			}
		});
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});
		
		this.add(pl);
		
		this.setVisible(true);
	}
	
	private void updateGoods()
	{
		goodsField.removeAllItems();
		Group gr = main.base.findGroup((String)groupField.getSelectedItem());
		if(gr != null)
			for(int i = 0; i < gr.size(); i++)
				goodsField.addItem(gr.get(i).getName());
	}
	
	private void updateTextFields()
	{
		if(goodsField.getSelectedItem() != null)
		{
			Pair p = main.base.findGood((String)goodsField.getSelectedItem());
			Good g = main.base.get((int)p.arg1).get((int)p.arg2);
			if(g != null)
			{
				nameField.setText(g.getName());
				manufacturerField.setText(g.getManufacturer());
				descriptionField.setText(g.getDescription());
				priceField.setValue((int)g.getPriceForOne());
			}
		}
	}
	
	private void exit(){this.dispose();}
}
