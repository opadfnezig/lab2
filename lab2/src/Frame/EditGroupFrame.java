package Frame;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import exceptions.NotUniqueElementException;
import goods.Group;

public class EditGroupFrame extends JFrame{
	private JComboBox<String> groupField;
	private JTextField nameField;
	private JTextArea descriptionField;
	private JButton cancel, edit;
	
	private MainFrame main;
	
	public EditGroupFrame(MainFrame main)
	{
		super("Edit group");
		this.setSize(400, 300);
		this.setResizable(false);
		
		this.main = main;
		
		groupField = new JComboBox<String>();
		for(Group g:main.base)
			groupField.addItem(g.getName());
		
		nameField = new JTextField();
		descriptionField = new JTextArea("Nothing");
		
		updateTextFields();
		
		cancel = new JButton("Cancel");
		edit = new JButton("Edit");
		
		this.setLayout(new GridLayout(0, 1));
		this.add(new JLabel("Group"));
		this.add(groupField);
		this.add(new JLabel("New name"));
		this.add(nameField);
		this.add(new JLabel("New description"));
		this.add(descriptionField);
		
		JPanel pl = new JPanel(new FlowLayout());
		pl.add(edit);
		pl.add(cancel);
		
		groupField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				updateTextFields();
			}
		});
		
		edit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					main.base.edit((String)groupField.getSelectedItem(), 
							nameField.getText(), descriptionField.getText());
					main.initFileTree();
					exit();
				} catch (NotUniqueElementException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} catch(IllegalArgumentException iae) {
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
	
	private void updateTextFields()
	{
		Group gr = main.base.findGroup((String)groupField.getSelectedItem());
		if(gr != null)
		{
			nameField.setText(gr.getName());
			descriptionField.setText(gr.getDescription());
		}
	}
	
	private void exit(){this.dispose();}
}

