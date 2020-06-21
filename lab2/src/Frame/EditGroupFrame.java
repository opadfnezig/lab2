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
	private JButton cancel, create;
	
	private MainFrame main;
	
	public EditGroupFrame(MainFrame main)
	{
		super("Edit group");
		this.setSize(400, 300);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.main = main;
		
		groupField = new JComboBox<String>();
		for(Group g:main.base)
			groupField.addItem(g.getName());
		
		nameField = new JTextField();
		descriptionField = new JTextArea("Nothing");
		
		cancel = new JButton("Cancel");
		create = new JButton("Edit");
		
		this.setLayout(new GridLayout(0, 1));
		this.add(new JLabel("Group"));
		this.add(groupField);
		this.add(new JLabel("New name"));
		this.add(nameField);
		this.add(new JLabel("New description"));
		this.add(descriptionField);
		
		JPanel pl = new JPanel(new FlowLayout());
		pl.add(create);
		pl.add(cancel);
		
		create.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					main.base.editGroup((String)groupField.getSelectedItem(), 
							nameField.getText(), descriptionField.getText());
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

