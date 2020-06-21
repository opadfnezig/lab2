package Frame;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import exceptions.NotUniqueElementException;
import goods.*;

public class NewGoodsFrame extends JFrame{
	private JComboBox<String> groupField;
	private JTextField nameField, manufacturerField;
	private JTextArea descriptionField;
	private JSpinner priceField;
	private JButton cancel, create;
	
	private MainFrame main;
	
	public NewGoodsFrame(MainFrame main) {
		super("New goods");
		this.setSize(400, 400);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.main = main;
		
		nameField = new JTextField();
		manufacturerField = new JTextField();
		descriptionField = new JTextArea("Nothing");
		priceField = new JSpinner();
		
		cancel = new JButton("Cancel");
		create = new JButton("Create");
		
		groupField = new JComboBox<String>();
		for(Group g:main.base)
			groupField.addItem(g.getName());
		
		this.setLayout(new GridLayout(0, 1));
		this.add(new JLabel("Group"));
		this.add(groupField);
		this.add(new JLabel("Goods name"));
		this.add(nameField);
		this.add(new JLabel("Goods manufacturer"));
		this.add(manufacturerField);
		this.add(new JLabel("Goods price"));
		this.add(priceField);
		this.add(new JLabel("Group description"));
		this.add(descriptionField);
		
		JPanel pl = new JPanel(new FlowLayout());
		pl.add(create);
		pl.add(cancel);
		
		create.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					main.base.addGood((String)groupField.getSelectedItem(), 
							new Good(nameField.getText(), descriptionField.getText(),
							manufacturerField.getText(), (int)priceField.getValue()));
					main.initFileTree();
					exit();
				} catch (NotUniqueElementException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
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
	
	private void exit(){this.dispose();}
}