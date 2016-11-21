/*
 * Group 5
 * Project Assignment 5
 * Software Design Methods
 * Fall 2016, Florida Institute of Technology
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.AbstractListModel;

public class UI {

	private JFrame frmProjectAssignment;
	private JPanel Landing;
	private JPanel Order;
	private JPanel toppingsBorder;
	private JPanel menuBorder;
	
	public static Store store;
	public static Register register;
	public static MenuCatalog catalog;
	
	public static boolean condimentsEnabled;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI window = new UI();
					window.frmProjectAssignment.setVisible(true);
					
					store = new Store();
					register = store.getRegister();
					catalog = store.getCatalog();
					
					condimentsEnabled = false;
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public UI() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		initialize();
	}

	/**
	 * Generate buttons based off MenuCatalog entrees/drinks
	 */
	private void createMenuButtons() {
		List<ItemDetails> menuItems = catalog.getEntrees();
		menuItems.addAll(catalog.getDrinks());
		JButton[] menuBtns = new JButton[menuItems.size()];

		for(int i = 0; i < menuItems.size(); i++) {
			ItemDetails item = menuItems.get(i);
			menuBtns[i] = new JButton(item.name);
			menuBtns[i].setToolTipText(item.description);
			menuBtns[i].setFont(new Font("Tahoma", Font.PLAIN, 15));
			menuBtns[i].setPreferredSize(new Dimension(150, 25));
			menuBtns[i].setFocusPainted(false);
			menuBtns[i].setEnabled(false);
			menuBtns[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (item.type == EItemType.Entree && !condimentsEnabled) {
						int length = toppingsBorder.getComponentCount();
						for (int i = 0; i < length; i++) {
							toppingsBorder.getComponents()[i].setEnabled(true);
						}
						
						condimentsEnabled = true;
					}
				}
			});
			menuBorder.add(menuBtns[i]);
		}
	}
	
	/**
	 * Generate buttons based off MenuCatalog toppings
	 */
	private void createToppingButtons() {
		List<ItemDetails> toppingItems = catalog.getToppingsFree();
		toppingItems.addAll(catalog.getToppingsNotFree());
		JButton[] toppingBtns = new JButton[toppingItems.size()];
		
		for(int i = 0; i < toppingItems.size(); i++) {
			ItemDetails item = toppingItems.get(i);
			toppingBtns[i] = new JButton(item.name);
			toppingBtns[i].setToolTipText(item.description);
			toppingBtns[i].setFont(new Font("Tahoma", Font.PLAIN, 15));
			toppingBtns[i].setBounds(33, 110, 110, 43);
			toppingBtns[i].setFocusPainted(false);
			toppingBtns[i].setEnabled(false);
			toppingsBorder.add(toppingBtns[i]);
		}
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmProjectAssignment = new JFrame();
		frmProjectAssignment.setIconImage(Toolkit.getDefaultToolkit().getImage(UI.class.getResource("/resources/burg.png")));
		frmProjectAssignment.setTitle("Coronary Castle");
		frmProjectAssignment.setResizable(false);
		frmProjectAssignment.setBounds(100, 100, 545, 399);
		frmProjectAssignment.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmProjectAssignment.setLocationRelativeTo(null);
		frmProjectAssignment.getContentPane().setLayout(new CardLayout(0, 0));
		
		Landing = new JPanel();
		Landing.setBorder(null);
		frmProjectAssignment.getContentPane().add(Landing, "landing");
		Landing.setLayout(null);
		
		JButton btnEnter = new JButton("Enter");
		btnEnter.setToolTipText("View the menu and order");
		btnEnter.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEnter.setBounds(33, 117, 110, 43);
		btnEnter.setFocusPainted(false);
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Landing.setVisible(false);
				Order.setVisible(true);
				createMenuButtons();
				createToppingButtons();
			}
		});
		Landing.add(btnEnter);
		
		JButton btnAbout = new JButton("About");
		btnAbout.setToolTipText("Credits and attribution");
		btnAbout.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAbout.setBounds(33, 167, 110, 43);
		btnAbout.setFocusPainted(false);
		btnAbout.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JOptionPane.showMessageDialog(new JMenu(),
						"This is the Coronary Castle project for"
						+ "\ngroup #5 in Software Design Methods.	"
						+ "\n\nGroup members:"
						+ "\n- Borja Canseco"
						+ "\n- Pablo Canseco"
						+ "\n- Waylon Hudson"
						+ "\n\nBurger pic courtesy of noBacks.com",
						"About", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		Landing.add(btnAbout);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setToolTipText("Have a Coronary day!");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnExit.setBounds(33, 217, 110, 43);
		btnExit.setFocusPainted(false);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmProjectAssignment.dispatchEvent(new WindowEvent(frmProjectAssignment, WindowEvent.WINDOW_CLOSING));
			}
		});
		Landing.add(btnExit);
		
		JPanel welcomeBorder = new JPanel();
		welcomeBorder.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Welcome", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		welcomeBorder.setBounds(10, 7, 519, 352);
		Landing.add(welcomeBorder);
		welcomeBorder.setLayout(null);
		
		JLabel lblBurg = new JLabel("Burger");
		lblBurg.setBounds(127, -22, 501, 434);
		welcomeBorder.add(lblBurg);
		lblBurg.setIcon(new ImageIcon(UI.class.getResource("/resources/burg.png")));
		
		Order = new JPanel();
		frmProjectAssignment.getContentPane().add(Order, "order");
		Order.setLayout(null);
		
		toppingsBorder = new JPanel();
		toppingsBorder.setBorder(new TitledBorder(null, "Toppings", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		toppingsBorder.setBounds(189, 11, 340, 159);
		Order.add(toppingsBorder);
		
		menuBorder = new JPanel();
		menuBorder.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Menu", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		menuBorder.setBounds(10, 11, 169, 249);
		Order.add(menuBorder);
		
		//Menu items and toppings panels created in their respective functions
		
		JPanel toolsBorder = new JPanel();
		toolsBorder.setBorder(new TitledBorder(null, "Tools", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		toolsBorder.setBounds(10, 271, 169, 88);
		Order.add(toolsBorder);
		toolsBorder.setLayout(null);
		
		JButton btnEndOrder = new JButton("Finish Order");
		btnEndOrder.setBounds(30, 50, 109, 27);
		btnEndOrder.setHorizontalAlignment(SwingConstants.LEFT);
		btnEndOrder.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEndOrder.setFocusPainted(false);
		btnEndOrder.setEnabled(false);
		toolsBorder.add(btnEndOrder);
		
		JButton btnNewOrder = new JButton("Start New Order");
		btnNewOrder.setBounds(15, 18, 138, 27);
		btnNewOrder.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewOrder.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewOrder.setFocusPainted(false);
		btnNewOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEndOrder.setEnabled(true);
				btnNewOrder.setEnabled(false);
				
				int length = menuBorder.getComponentCount();
				for (int i = 0; i < length; i++) {
					menuBorder.getComponents()[i].setEnabled(true);
				}
			}
		});
		toolsBorder.add(btnNewOrder);
		
		JPanel yourOrderBorder = new JPanel();
		yourOrderBorder.setBorder(new TitledBorder(null, "Your Order", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		yourOrderBorder.setBounds(189, 181, 340, 178);
		Order.add(yourOrderBorder);
		yourOrderBorder.setLayout(null);
		
		JList list = new JList();
//		list.setModel(new AbstractListModel() {
//			String[] values = new String[] {"adsasdas", "das", "da", "sd", "a", "sd", "a"};
//			public int getSize() {
//				return values.length;
//			}
//			public Object getElementAt(int index) {
//				return values[index];
//			}
//		});
		list.setBounds(10, 21, 320, 146);
		yourOrderBorder.add(list);
	}
}
