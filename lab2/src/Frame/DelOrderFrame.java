package Frame;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import goods.Good;
import goods.Group;
import utils.Pair;

public class DelOrderFrame extends JFrame{
	private JComboBox<String> groupField, goodsField, ordersField;
	private JButton cancel, delete;
	
	private MainFrame main;
	
	public DelOrderFrame(MainFrame main) {
		super("Delete goods");
		this.setSize(400, 400);
		this.setResizable(false);
		
		this.main = main;
		
		groupField = new JComboBox<String>();
		for(Group g:main.base)
			groupField.addItem(g.getName());
		
		goodsField = new JComboBox<String>();
		updateGoods();
		
		ordersField = new JComboBox<String>();
		
		cancel = new JButton("Cancel");
		delete = new JButton("Delete");
		
		this.setLayout(new GridLayout(0, 1));
		this.add(new JLabel("Group"));
		this.add(groupField);
		this.add(new JLabel("Goods"));
		this.add(goodsField);
		this.add(new JLabel("Orders"));
		this.add(ordersField);
		
		JPanel pl = new JPanel(new FlowLayout());
		pl.add(delete);
		pl.add(cancel);
		
		groupField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				updateGoods();
			}
		});
		goodsField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				updateOreders();
			}
		});
		
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Pair p = main.base.findGood((String)goodsField.getSelectedItem());
				Good g = main.base.getGoodByIndex(p);
				g.remove(ordersField.getSelectedIndex());
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
	
	private void updateOreders()
	{
		ordersField.removeAllItems();
		Pair p = main.base.findGood((String)goodsField.getSelectedItem());
		if(p != null)
		{
			Good g = main.base.get((int)p.arg1).get((int)p.arg2);
			for(int i = 0; i < g.size(); i++)
				ordersField.addItem(g.get(i).toString());
		}
			
	}
	
	private void exit(){this.dispose();}
}
