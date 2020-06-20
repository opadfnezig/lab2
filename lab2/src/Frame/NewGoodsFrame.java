package Frame;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import goods.*;

public class NewGoodsFrame extends JFrame{
	private JTextField nameField, manufacturerField;
	private JTextArea descriptionField;
	private JSpinner priceField;
	private JButton cancel, create;
	
	private GroupBase base;
	private Group group;
	
	public NewGoodsFrame(GroupBase gb, Group gr) {
		super("New goods");
		this.setSize(400, 300);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		base = gb;
		group = gr;
		
		nameField = new JTextField();
		manufacturerField = new JTextField();
		descriptionField = new JTextArea("Nothing");
		priceField = new JSpinner();
		
		this.setLayout(new GridLayout(0, 1));
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
					base.addGood(group.getName(), new Good(nameField.getText(), descriptionField.getText(), manufacturerField.getText(), (double)priceField.getValue()));
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
