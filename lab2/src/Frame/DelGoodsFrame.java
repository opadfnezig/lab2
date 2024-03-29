package Frame;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import exceptions.NotUniqueElementException;
import goods.Good;
import goods.Group;

public class DelGoodsFrame extends JFrame{
	private JComboBox<String> groupField, goodsField;
	private JButton cancel, delete;
	
	private MainFrame main;
	
	public DelGoodsFrame(MainFrame main)
	{
		super("Delete goods");
		this.setSize(400, 400);
		this.setResizable(false);
		
		this.main = main;
		
		groupField = new JComboBox<String>();
		for(Group g:main.base)
			groupField.addItem(g.getName());
		
		goodsField = new JComboBox<String>();
		updateGoods();
		
		cancel = new JButton("Cancel");
		delete = new JButton("Delete");
		
		this.setLayout(new GridLayout(0, 1));
		this.add(new JLabel("Group"));
		this.add(groupField);
		this.add(new JLabel("Goods"));
		this.add(goodsField);
		
		JPanel pl = new JPanel(new FlowLayout());
		pl.add(delete);
		pl.add(cancel);
		
		groupField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				updateGoods();
			}
		});
		
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				main.base.findGroup((String)groupField.getSelectedItem())
					.remove((String)goodsField.getSelectedItem());
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
	
	private void exit(){this.dispose();}
}
