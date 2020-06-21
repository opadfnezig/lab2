package Frame;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import exceptions.NotEnoughGoodException;
import exceptions.NotUniqueElementException;
import goods.Good;
import goods.Group;
import stock.Order;
import stock.Order.OperationType;

public class NewOrderFrame extends JFrame{
	private JComboBox<String> groupField, goodsField;
	private JRadioButton purchase, sale;
	private JSpinner countField;
	private JButton cancel, create;
	
	private MainFrame main;
	
	public NewOrderFrame(MainFrame main) {
		super("New order");
		this.setSize(400, 400);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.main = main;
		
		countField = new JSpinner();
		
		purchase = new JRadioButton("Purchase");
		sale = new JRadioButton("Sale");
		
		cancel = new JButton("Cancel");
		create = new JButton("Create");
		
		groupField = new JComboBox<String>();
		for(Group g:main.base)
			groupField.addItem(g.getName());
		
		goodsField = new JComboBox<String>();
		updateGoods();
		
		JPanel pl = new JPanel(new FlowLayout());
		pl.add(create);
		pl.add(cancel);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(purchase);
		bg.add(sale);
		
		JPanel bp = new JPanel(new FlowLayout());
		bp.add(purchase);
		bp.add(sale);
		
		JPanel cp = new JPanel(new GridLayout(0, 2));
		cp.add(new JLabel("Count: "));
		cp.add(countField);
		
		groupField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				updateGoods();
			}
		});
		
		create.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				if(purchase.isSelected())
					main.base.addOrder((String)goodsField.getSelectedItem(), 
						new Order(OperationType.purchase, (String)goodsField.getSelectedItem(), 
						(int)countField.getValue()));
				else if(sale.isSelected())
					main.base.addOrder((String)goodsField.getSelectedItem(), 
						new Order(OperationType.sale, (String)goodsField.getSelectedItem(), 
						(int)countField.getValue()));
				else
				{
					JOptionPane.showMessageDialog(null, "Choose purchase or sale"); 
					return;
				}
				main.initFileTree();
				exit();
				} catch(NotEnoughGoodException nege) {
					JOptionPane.showMessageDialog(null, nege.getMessage());
				}
			}
		});
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});
		
		this.setLayout(new GridLayout(0, 1));
		this.add(new JLabel("Group"));
		this.add(groupField);
		this.add(new JLabel("Goods"));
		this.add(goodsField);
		this.add(bp);
		this.add(cp);
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
