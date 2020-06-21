package Frame;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import exceptions.NotUniqueElementException;
import goods.Group;
import goods.GroupBase;

public class NewGroupFrame extends JFrame{
	
	private JTextField nameField;
	private JTextArea descriptionField;
	private JButton cancel, create;
	
	private MainFrame main;
	
	public NewGroupFrame(MainFrame main)
	{
		super("New group");
		this.setSize(400, 300);
		this.setResizable(false);
		
		this.main = main;
		
		nameField = new JTextField();
		descriptionField = new JTextArea("Nothing");
		
		cancel = new JButton("Cancel");
		create = new JButton("Create");
		
		this.setLayout(new GridLayout(0, 1));
		this.add(new JLabel("Group name"));
		this.add(nameField);
		this.add(new JLabel("Group description"));
		this.add(descriptionField);
		
		JPanel pl = new JPanel(new FlowLayout());
		pl.add(create);
		pl.add(cancel);
		
		create.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					main.base.addElement(new Group(nameField.getText(), descriptionField.getText()));
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
	
	private void exit(){this.dispose();}
}
