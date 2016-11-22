/*
 * Group 5
 * Project Assignment 5
 * Software Design Methods
 * Fall 2016, Florida Institute of Technology
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.List;
import javax.swing.border.TitledBorder;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class UI {
	private JFrame frmProjectAssignment;
	private JPanel landing;
	private JPanel order;
	private JPanel payment;

	private static JPanel toppingsBorder;
	private static JPanel menuBorder;
	private static JTextField currentItem;
	private static JTextField qty;
	private static JTextField subtotal;
	private static JButton btnAddItem;
	private static JList<ItemDetails> toppingsContainer;
	private static DefaultListModel<ItemDetails> toppingsList 
		= new DefaultListModel<ItemDetails>();
	private static JList<MenuItem> orderedItemsContainer;
	private static DefaultListModel<MenuItem> orderedItemsList
		= new DefaultListModel<MenuItem>();

	public static Store store;
	public static Register register;
	public static MenuCatalog catalog;

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
	 * Updates the Sale JList with up-to-date ordered items.
	 * Called on each successful item add.
	 */
	public void refreshSale() {
		orderedItemsList.clear();
		for (MenuItem item : register.currentSale.orderedItems) {
			orderedItemsList.addElement(item);
		}
	}

	/**
	 * Generate buttons based off MenuCatalog entrees/drinks.
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
					if (item.type == EItemType.Entree) {
						enableToppings(true);
					} else { // drink
						enableToppings(false);
					}

					if (register.currentItem != null) {
						toppingsList.clear();
					}

					register.enterItem(item.id);
					currentItem.setText(item.name);
					currentItem.setToolTipText(item.description);
					qty.setText("1");
					qty.setEditable(true);
					btnAddItem.setEnabled(true);
				}
			});
			menuBorder.add(menuBtns[i]);
		}
	}

	/**
	 * Generate buttons based off MenuCatalog toppings.
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
			toppingBtns[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					register.addCondiment(catalog.getItemDetails(item.id));
					toppingsList.addElement(catalog.getItemDetails(item.id));
				}
			});
			toppingsBorder.add(toppingBtns[i]);
		}
	}

	/**
	 * Helper method to enable or disable the toppings buttons.
	 * @param setEnabled - true to enable, false to disable.
	 */
	private void enableToppings(boolean setEnabled) {
		int length = toppingsBorder.getComponentCount();
		for (int i = 0; i < length; i++) {
			toppingsBorder.getComponents()[i].setEnabled(setEnabled);
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
		frmProjectAssignment.setBounds(100, 100, 559, 399);
		frmProjectAssignment.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmProjectAssignment.setLocationRelativeTo(null);
		frmProjectAssignment.getContentPane().setLayout(new CardLayout(0, 0));

		landing = new JPanel();
		landing.setBorder(null);
		frmProjectAssignment.getContentPane().add(landing, "landing");
		landing.setLayout(null);

		JButton btnEnter = new JButton("Enter");
		btnEnter.setToolTipText("View the menu and order");
		btnEnter.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEnter.setBounds(33, 117, 110, 43);
		btnEnter.setFocusPainted(false);
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				landing.setVisible(false);
				order.setVisible(true);
				createMenuButtons();
				createToppingButtons();
			}
		});
		landing.add(btnEnter);

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
		landing.add(btnAbout);

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
		landing.add(btnExit);

		JPanel welcomeBorder = new JPanel();
		welcomeBorder.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Welcome",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		welcomeBorder.setBounds(10, 7, 533, 352);
		landing.add(welcomeBorder);
		welcomeBorder.setLayout(null);

		JLabel lblBurg = new JLabel("Burger");
		lblBurg.setBounds(127, -22, 501, 434);
		welcomeBorder.add(lblBurg);
		lblBurg.setIcon(new ImageIcon(UI.class.getResource("/resources/burg.png")));

		order = new JPanel();
		frmProjectAssignment.getContentPane().add(order, "order");
		order.setLayout(null);

		toppingsBorder = new JPanel();
		toppingsBorder.setBorder(new TitledBorder(null, "Toppings", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		toppingsBorder.setBounds(189, 11, 354, 159);
		order.add(toppingsBorder);

		menuBorder = new JPanel();
		menuBorder.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Menu",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		menuBorder.setBounds(10, 11, 169, 249);
		order.add(menuBorder);

		JPanel toolsBorder = new JPanel();
		toolsBorder.setBorder(new TitledBorder(null, "Tools", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		toolsBorder.setBounds(10, 271, 169, 88);
		order.add(toolsBorder);
		toolsBorder.setLayout(null);

		JButton btnEndOrder = new JButton("Finish Order");
		btnEndOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnEndOrder.setEnabled(false);
				order.setVisible(false);
				payment.setVisible(true);
			}
		});
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
				
				register.newSale();
			}
		});
		toolsBorder.add(btnNewOrder);

		JPanel yourOrderBorder = new JPanel();
		yourOrderBorder.setBorder(new TitledBorder(null, "Your Order",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		yourOrderBorder.setBounds(189, 181, 354, 178);
		order.add(yourOrderBorder);
		yourOrderBorder.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(191, 42, 153, 100);
		yourOrderBorder.add(scrollPane_1);

		orderedItemsContainer = new JList<MenuItem>(orderedItemsList);
		scrollPane_1.setViewportView(orderedItemsContainer);
		orderedItemsContainer.setBackground(UIManager.getColor("Button.background"));
		orderedItemsContainer.setBorder(null);

		currentItem = new JTextField();
		currentItem.setEditable(false);
		currentItem.setBackground(UIManager.getColor("Button.background"));
		currentItem.setBounds(10, 42, 99, 20);
		currentItem.setColumns(10);
		yourOrderBorder.add(currentItem);

		JLabel lblCurrentItem = new JLabel("Current item");
		lblCurrentItem.setBounds(10, 25, 69, 14);
		yourOrderBorder.add(lblCurrentItem);

		JLabel lblToppings = new JLabel("Toppings");
		lblToppings.setBounds(10, 71, 69, 14);
		yourOrderBorder.add(lblToppings);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 88, 99, 79);
		yourOrderBorder.add(scrollPane);

		toppingsContainer = new JList<ItemDetails>(toppingsList);
		toppingsContainer.setBorder(null);
		scrollPane.setViewportView(toppingsContainer);
		toppingsContainer.setBackground(UIManager.getColor("Button.background"));

		qty = new JTextField();
		qty.setEditable(false);
		qty.setBounds(116, 42, 21, 20);
		yourOrderBorder.add(qty);
		qty.setColumns(10);

		JLabel lblQty = new JLabel("Qty");
		lblQty.setBounds(116, 25, 27, 14);
		yourOrderBorder.add(lblQty);

		btnAddItem = new JButton(">>");
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int quantity;
				try {
					quantity = Integer.parseInt(qty.getText());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(new JMenu(), "Quantity must be an integer above zero.", 
							"Invalid quantity", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if (quantity <= 0) {
					JOptionPane.showMessageDialog(new JMenu(), "Quantity must be an integer above zero.", 
							"Invalid quantity", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				register.addCurrentItemToSale(quantity);
				refreshSale();
				subtotal.setText("$" + String.format("%.2f", register.currentSale.getTotal()));
				currentItem.setText("");
				qty.setText("");
				qty.setEditable(false);
				toppingsList.clear();
				btnAddItem.setEnabled(false);
				enableToppings(false);
			}
		});
		btnAddItem.setEnabled(false);
		btnAddItem.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAddItem.setBounds(136, 72, 49, 27);
		btnAddItem.setFocusPainted(false);
		yourOrderBorder.add(btnAddItem);

		JLabel lblSubtotal = new JLabel("Subtotal:");
		lblSubtotal.setBounds(242, 153, 46, 14);
		yourOrderBorder.add(lblSubtotal);

		subtotal = new JTextField();
		subtotal.setHorizontalAlignment(SwingConstants.RIGHT);
		subtotal.setEditable(false);
		subtotal.setBounds(290, 150, 54, 17);
		yourOrderBorder.add(subtotal);
		subtotal.setColumns(10);

		JLabel lblSale = new JLabel("Sale");
		lblSale.setBounds(191, 25, 46, 14);
		yourOrderBorder.add(lblSale);

		payment = new JPanel();
		frmProjectAssignment.getContentPane().add(payment, "payment");
		payment.setLayout(null);
	}
}
