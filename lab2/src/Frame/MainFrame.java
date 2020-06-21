package Frame;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
import java.io.File;
import java.text.AttributedCharacterIterator;
import java.util.StringTokenizer;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import goods.Group;
import goods.GroupBase;

public class MainFrame extends JFrame{

	private JMenuBar topMenu;
	
	private JMenu fileMenu, groupMenu, goodsMenu, orderMenu;
	private JMenuItem load, save, exit;
	private JMenuItem newGroup, delGroup, editGroup;
	private JMenuItem newGoods, delGoods, editGoods;
	private JMenuItem newOrder, delOrder, editOrder;
	
	private JPanel leftPanel;
	private JTree fileSystem = null;
	private JScrollPane treeView;
	
	private JPanel mainPanel;
	private JTextArea mainTextArea;
	
	private MainFrame ths;
	
	GroupBase base;
	
	public MainFrame()
	{
		super("Storage");
		this.setSize(800, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ths = this;
		
		base = new GroupBase("StorageGoodsSystem", "C:\\Users\\Igor\\Storage.dat");
		
		initTopMenu();
		initLeftPanel();
		initMainPanel();
		
		this.setVisible(true);
	}
	
	private void initTopMenu()
	{
		topMenu = new JMenuBar();
		
		initFile();
		initGroup();
		initGoods();
		initOrder();
		
		topMenu.add(fileMenu);
		topMenu.add(groupMenu);
		topMenu.add(goodsMenu);
		topMenu.add(orderMenu);
		
		this.add(topMenu, BorderLayout.PAGE_START);
	}
	private void initFile()
	{
		fileMenu = new JMenu("File");
		
		save = new JMenuItem("Save");
		load = new JMenuItem("Load");
		exit = new JMenuItem("Exit");
		
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				base.save();
			}
		});
		load.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					base = base.load();
					initFileTree();
				} catch(NullPointerException npe) {
					JOptionPane.showMessageDialog(null, "Base is not found");
				}
			}
		});
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});
		
		fileMenu.add(save);
		fileMenu.add(load);
		fileMenu.add(exit);
		
	}
	private void initGroup()
	{
		groupMenu = new JMenu("Group");
		
		newGroup = new JMenuItem("New group");
		editGroup = new JMenuItem("Edit group");
		delGroup = new JMenuItem("Delete group");
		
		newGroup.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				NewGroupFrame ngf = new NewGroupFrame(ths);
			}
		});
		editGroup.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				EditGroupFrame egf = new EditGroupFrame(ths);
			}
		});
		delGroup.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DelGroupFrame dgf = new DelGroupFrame(ths);
			}
		});
		
		groupMenu.add(newGroup);
		groupMenu.add(editGroup);
		groupMenu.add(delGroup);
	}
	private void initGoods()
	{
		goodsMenu = new JMenu("Goods");
		
		newGoods = new JMenuItem("New goods");
		editGoods = new JMenuItem("Edit goods");
		delGoods = new JMenuItem("Delete goods");
		
		newGoods.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				NewGoodsFrame ngf = new NewGoodsFrame(ths);
			}
		});
		editGoods.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				EditGoodsFrame egf = new EditGoodsFrame(ths);
			}
		});
		delGoods.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DelGoodsFrame dgf = new DelGoodsFrame(ths);
			}
		});
		
		goodsMenu.add(newGoods);
		goodsMenu.add(editGoods);
		goodsMenu.add(delGoods);
	}
	private void initOrder()
	{
		orderMenu = new JMenu("Order");
		
		newOrder = new JMenuItem("New order");
		editOrder = new JMenuItem("Edit order");
		delOrder = new JMenuItem("Delete order");
		
		newOrder.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				NewOrderFrame nof = new NewOrderFrame(ths);
			}
		});
		editOrder.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				EditOrderFrame eof = new EditOrderFrame(ths);
			}
		});
		delOrder.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DelOrderFrame dof = new DelOrderFrame(ths);
			}
		});
		
		orderMenu.add(newOrder);
		orderMenu.add(editOrder);
		orderMenu.add(delOrder);
	}
	
	private void initLeftPanel() {
		leftPanel = new JPanel(new BorderLayout());
		Border border = BorderFactory.createTitledBorder("File system");
		leftPanel.setBorder(border);
		initFileTree();
		this.add(leftPanel, BorderLayout.WEST);
		
	}
	void initFileTree() {
		DefaultMutableTreeNode top = new DefaultMutableTreeNode(base.getName());
        for(Group g:base)
        {
        	DefaultMutableTreeNode newD = new DefaultMutableTreeNode(g.getName());
        	for(int i = 0; i < g.size(); i++)
        	{
        		DefaultMutableTreeNode newG = new DefaultMutableTreeNode(g.get(i).getName());
        		newD.add(newG);
        	}
        	top.add(newD);
        }
        DefaultTreeModel tree = new DefaultTreeModel(top);
        if(fileSystem == null)
        {
	        fileSystem = new JTree();
	        fileSystem.setModel(tree);
	        treeView = new JScrollPane(fileSystem);
	        leftPanel.add(treeView, BorderLayout.CENTER);
	        fileSystem.addTreeSelectionListener(new TreeSelectionListener() {
				
				@Override
				public void valueChanged(TreeSelectionEvent e) {
					StringTokenizer st = new StringTokenizer(e.getPath().toString(), " ,[]");
					st.nextToken();
					if(st.countTokens() == 1)
						mainTextArea.setText(base.findGroup(st.nextToken()).toString());
					else if(st.countTokens() == 2)
					{
						st.nextToken();
						mainTextArea.setText(base.findGood(st.nextToken()).arg2.toString());
					}
					else
						mainTextArea.setText(base.toString());
				}
			});
        }
        else
        	fileSystem.setModel(tree);
    }
	
    private void initMainPanel()
    {
    	mainPanel = new JPanel(new BorderLayout());
    	
    	mainTextArea = new JTextArea();
    	mainTextArea.setEditable(false);
    	JScrollPane sp = new JScrollPane(mainTextArea,
    			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    	
    	mainPanel.add(sp, BorderLayout.CENTER);
    	
    	this.add(mainPanel, BorderLayout.CENTER);
    }
    
    private void exit(){this.dispose();}
    private void update() {this.update(this.getGraphics());}
    
	public static void main(String[] args) {
		MainFrame mf = new MainFrame();
	}
}
