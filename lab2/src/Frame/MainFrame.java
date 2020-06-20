package Frame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.StringTokenizer;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class MainFrame extends JFrame{

	private JMenuBar topMenu;
	
	private JMenu groupMenu, goodsMenu, orderMenu;
	private JMenuItem newGroup, delGroup, editGroup;
	private JMenuItem newGoods, delGoods, editGoods;
	private JMenuItem newOrder, delOrder, editOrder;
	
	private JPanel leftPanel, mainPanel;
	private JTree fileSystem;
	
	private File coreFolder;
	
	public MainFrame()
	{
		super("Storage");
		this.setSize(800, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		coreFolder = new File("Storage");
		
		initLeftPanel();
		
		this.add(topMenu, BorderLayout.PAGE_START);
		
		this.setVisible(true);
	}
	
	private void initTopMenu()
	{
		topMenu = new JMenuBar();
		
		initGroup();
		initGoods();
		initOrder();
		
		topMenu.add(groupMenu);
		topMenu.add(goodsMenu);
		topMenu.add(orderMenu);
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
		leftPanel = new JPanel(new GridLayout(10, 10));
		Border border = BorderFactory.createTitledBorder("File system");
		leftPanel.setBorder(border);
		initFileTree();
		this.add(leftPanel, BorderLayout.WEST);
	}
	private void initFileTree() {
        DefaultMutableTreeNode top = new DefaultMutableTreeNode(coreFolder.getName());
        initTree(coreFolder,top);
        
        fileSystem = new JTree(top);
        JScrollPane treeView = new JScrollPane(fileSystem);
        
        fileSystem.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				
				
			}
		});
        
        leftPanel.add(treeView);
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
	
    
    
	public static void main(String[] args) {
		MainFrame mf = new MainFrame();
	}
}
