package Frame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.StringTokenizer;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import goods.GroupBase;

public class MainFrame extends JFrame{

	private JMenuBar topMenu;
	
	private JMenu fileMenu, groupMenu, goodsMenu, orderMenu;
	private JMenuItem load, save, exit;
	private JMenuItem newGroup, delGroup, editGroup;
	private JMenuItem newGoods, delGoods, editGoods;
	private JMenuItem newOrder, delOrder, editOrder;
	
	private JPanel leftPanel;
	private JTree fileSystem;
	
	private JPanel mainPanel;
	private JTextArea mainTextArea;
	
	private GroupBase base;
	
	public MainFrame()
	{
		super("Storage");
		this.setSize(800, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		initTopMenu();
		//initLeftPanel();
		initMainPanel();
		
		this.add(topMenu, BorderLayout.PAGE_START);
		
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
	}
	private void initFile()
	{
		fileMenu = new JMenu("File");
		
		save = new JMenuItem("Save  Ctrl+s");
		load = new JMenuItem("Load  Ctrl+l");
		exit = new JMenuItem("Exit");
		
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		load.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
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
				NewGroupFrame ngf = new NewGroupFrame(base);
			}
		});
		editGroup.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		delGroup.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
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
				
			}
		});
		editGoods.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		delGoods.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
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
				
			}
		});
		editOrder.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		delOrder.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
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
	private void initFileTree() {
        DefaultMutableTreeNode top = new DefaultMutableTreeNode(base.getName());
        
        
        fileSystem = new JTree(top);
        JScrollPane treeView = new JScrollPane(fileSystem);
        
        fileSystem.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				
				
			}
		});
        
        leftPanel.add(treeView, BorderLayout.CENTER);
    }
    private DefaultMutableTreeNode initTree(File file, DefaultMutableTreeNode node) {
    	File[] files = file.listFiles();
    	if (files!=null){
            for (File f : files) {
                DefaultMutableTreeNode newDirect = new DefaultMutableTreeNode(f.getName());
                if (f.isDirectory()) {
                    initTree(f, newDirect);
                }
                node.add(newDirect);
            }
        }
        return node;
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
    
	public static void main(String[] args) {
		MainFrame mf = new MainFrame();
	}
}
