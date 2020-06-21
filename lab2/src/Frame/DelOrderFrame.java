package Frame;

import javax.swing.*;

public class DelOrderFrame extends JFrame{
	private JComboBox<String> groupField, goodsField, ordersField;
	private JButton cancel, delete;
	
	private MainFrame main;
	
	public DelOrderFrame(MainFrame main) {
		
	}
	
	private void exit(){this.dispose();}
}
