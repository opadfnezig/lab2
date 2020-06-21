package Frame;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import goods.Group;

public class DelGroupFrame extends JFrame{
	private JComboBox<String> groupField;
	private JButton cancel, delete;
	
	private MainFrame main;
	
	public DelGroupFrame(MainFrame main)
	{
		super("Delete group");
		this.setSize(400, 200);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.main = main;
		
		groupField = new JComboBox<String>();
		for(Group g:main.base)
			groupField.addItem(g.getName());
		
		cancel = new JButton("Cancel");
		delete = new JButton("Delete");
		
		JPanel pl = new JPanel(new FlowLayout());
		pl.add(delete);
		pl.add(cancel);
		
		this.setLayout(new GridLayout(0, 1));
		this.add(new JLabel("Group"));
		this.add(groupField);
		this.add(pl);
		
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				main.base.remove((String)groupField.getSelectedItem());
				main.initFileTree();
				exit();
			}
		});
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});
		
		this.setVisible(true);
	}
	
	private void exit(){this.dispose();}
}
