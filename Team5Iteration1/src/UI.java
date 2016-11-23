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
import java.util.Queue;
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
import javax.swing.JTextPane;
import java.awt.SystemColor;

public class UI {
	private JFrame frmProjectAssignment;
	private JPanel landing;
	private JPanel order;
	private JPanel payment;
	private JPanel toppingsBorder;
	private JPanel menuBorder;
	private JTextField currentItem;
	private JTextField qty;
	private JTextField yourTotal;
	private JTextField inputCashAmount;
	private JTextField subtotal;
	private JButton btnNewOrder;
	private JButton btnAddItem;
	private JButton btnCash;
	private JButton btnCredit;
	private JButton btnSubmitOrder;
	private JButton btnCancelOrder;
	private JList<ItemDetails> toppingsContainer;
	private JList<MenuItem> orderedItemsContainer;
	private DefaultListModel<ItemDetails> toppingsList 
		= new DefaultListModel<ItemDetails>();
	private DefaultListModel<MenuItem> orderedItemsList
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
			menuBtns[i].setToolTipText("$" + item.getPrice() + " - " + item.description);
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
						if (currentItem.getText().equals(item.name)) {
							try {
								qty.setText("" + (Integer.parseInt(qty.getText()) + 1));
							} catch (Exception ex) {
								qty.setText("2");
							}
							return;
						}
						toppingsList.clear();
					}

					register.enterItem(item.id);
					currentItem.setText(item.name);
					currentItem.setToolTipText(item.description);
					qty.setText("1");
					qty.setEnabled(true);
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
			toppingBtns[i].setToolTipText((item.getPrice() == 0.0 ? "FREE" : "$" + item.getPrice()) + " - " + item.description);
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
	 * Called to go back to the landing panel.
	 */
	private void resetApp() {
		menuBorder.removeAll();
		toppingsBorder.removeAll();
		landing.setVisible(true);
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
	 * Helper method to enable or disable the entrees buttons.
	 * @param setEnabled - true to enable, false to disable.
	 */
	private void enableEntrees(boolean setEnabled) {
		int length = menuBorder.getComponentCount();
		for (int i = 0; i < length; i++) {
			menuBorder.getComponents()[i].setEnabled(setEnabled);
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
				// Switch to Ordering panel
				landing.setVisible(false);
				order.setVisible(true);
				btnNewOrder.grabFocus();
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
				// Switch to payment panel
				btnEndOrder.setEnabled(false);
				btnSubmitOrder.setText("Submit Order");
				btnCancelOrder.setText("Cancel Order");
				btnCancelOrder.setBackground(new Color(220, 20, 60));
				yourTotal.setText("$" + String.format("%.2f", register.currentSale.getTotal()));
				orderedItemsList.clear();
				toppingsList.clear();
				qty.setText("");
				qty.setEnabled(false);
				currentItem.setText("");
				subtotal.setText("");
				btnAddItem.setEnabled(false);
				btnNewOrder.setEnabled(true);
				order.setVisible(false);
				if (register.currentSale.orderedItems.isEmpty()) {
					resetApp(); // Back to landing if no items picked
				} else {
					payment.setVisible(true);
				}
			}
		});
		btnEndOrder.setBounds(30, 50, 109, 27);
		btnEndOrder.setHorizontalAlignment(SwingConstants.LEFT);
		btnEndOrder.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEndOrder.setFocusPainted(false);
		btnEndOrder.setEnabled(false);
		toolsBorder.add(btnEndOrder);

		btnNewOrder = new JButton("Start New Order");
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
					menuBorder.getComponents()[i].setEnabled(true); // enable menu items now that order started
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
		lblCurrentItem.setBounds(10, 25, 99, 14);
		yourOrderBorder.add(lblCurrentItem);

		JLabel lblToppings = new JLabel("Toppings");
		lblToppings.setBounds(10, 71, 99, 14);
		yourOrderBorder.add(lblToppings);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 88, 99, 79);
		yourOrderBorder.add(scrollPane);

		toppingsContainer = new JList<ItemDetails>(toppingsList);
		toppingsContainer.setBorder(null);
		scrollPane.setViewportView(toppingsContainer);
		toppingsContainer.setBackground(UIManager.getColor("Button.background"));

		qty = new JTextField();
		qty.setEditable(true);
		qty.setEnabled(false);
		qty.setBounds(116, 42, 21, 20);
		yourOrderBorder.add(qty);
		qty.setColumns(10);

		JLabel lblQty = new JLabel("Qty");
		lblQty.setBounds(116, 25, 27, 14);
		yourOrderBorder.add(lblQty);

		btnAddItem = new JButton(">>");
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// add item to your order list
				int quantity;
				try {
					quantity = Integer.parseInt(qty.getText());
				} catch (Exception e) {
					// error checking
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
				qty.setEnabled(false);
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
		lblSubtotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSubtotal.setBounds(191, 153, 97, 14);
		yourOrderBorder.add(lblSubtotal);

		subtotal = new JTextField();
		subtotal.setHorizontalAlignment(SwingConstants.RIGHT);
		subtotal.setEditable(false);
		subtotal.setBounds(290, 150, 54, 17);
		yourOrderBorder.add(subtotal);
		subtotal.setColumns(10);

		JLabel lblSale = new JLabel("Sale");
		lblSale.setBounds(191, 25, 153, 14);
		yourOrderBorder.add(lblSale);

		payment = new JPanel();
		frmProjectAssignment.getContentPane().add(payment, "payment");
		payment.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Receipt", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(259, 11, 284, 348);
		payment.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBorder(null);
		scrollPane_2.setBounds(12, 24, 260, 312);
		panel.add(scrollPane_2);
		
		JTextPane receiptPane = new JTextPane();
		receiptPane.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		receiptPane.setBorder(null);
		scrollPane_2.setViewportView(receiptPane);
		receiptPane.setEditable(false);
		receiptPane.setBackground(SystemColor.menu);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "We Accept", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 284, 244, 75);
		payment.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(UI.class.getResource("/resources/cards.png"))));
		lblNewLabel.setBounds(10, 19, 229, 52);
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Payment", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 11, 243, 267);
		payment.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblYourTotal = new JLabel("Your total:");
		lblYourTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblYourTotal.setBounds(10, 45, 84, 14);
		panel_2.add(lblYourTotal);
		
		yourTotal = new JTextField();
		yourTotal.setEditable(false);
		yourTotal.setBounds(99, 42, 57, 20);
		panel_2.add(yourTotal);
		yourTotal.setColumns(10);
		
		JLabel lblThanksForOrdering = new JLabel("Thanks for ordering at Coronary Castle!");
		lblThanksForOrdering.setBounds(15, 23, 218, 14);
		panel_2.add(lblThanksForOrdering);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Choose tender", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(10, 81, 223, 93);
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblInputCashAmount = new JLabel("Input cash amount:");
		lblInputCashAmount.setEnabled(false);
		lblInputCashAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblInputCashAmount.setBounds(10, 63, 117, 14);
		panel_3.add(lblInputCashAmount);
		
		btnCredit = new JButton("Credit");
		btnCredit.setToolTipText("The amount will automatically be paid in full.");
		btnCredit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// no need to input amount, the cc will pay it full evenly
				btnCredit.setEnabled(false);
				btnCash.setEnabled(true);
				btnSubmitOrder.setEnabled(true);
				lblInputCashAmount.setEnabled(false);
				inputCashAmount.setEnabled(false);
				inputCashAmount.setText("");
			}
		});
		btnCredit.setBounds(110, 21, 81, 31);
		btnCredit.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnCredit.setFocusPainted(false);
		panel_3.add(btnCredit);
		
		inputCashAmount = new JTextField();
		inputCashAmount.setEnabled(false);
		inputCashAmount.setColumns(10);
		inputCashAmount.setBounds(134, 60, 57, 20);
		panel_3.add(inputCashAmount);
		
		btnCash = new JButton("Cash");
		btnCash.setToolTipText("You will need to enter the amount you wish to pay.");
		btnCash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ask for exact input
				btnCash.setEnabled(false);
				btnCredit.setEnabled(true);
				btnSubmitOrder.setEnabled(true);
				lblInputCashAmount.setEnabled(true);
				inputCashAmount.setEnabled(true);
				inputCashAmount.grabFocus();
			}
		});
		btnCash.setBounds(32, 21, 73, 31);
		btnCash.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnCash.setFocusPainted(false);
		panel_3.add(btnCash);
		
		btnCancelOrder = new JButton("Cancel Order");
		btnCancelOrder.setFocusable(false);
		btnCancelOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				payment.setVisible(false);
				btnSubmitOrder.setEnabled(false);
				inputCashAmount.setEnabled(false);
				lblInputCashAmount.setEnabled(false);
				btnCredit.setEnabled(true);
				btnCash.setEnabled(true);
				receiptPane.setText("");
				if (register.currentSale.isComplete) {
					resetApp(); // return to landing (canceled after submitting)
				} else {
					enableEntrees(false); // return to ordering (canceled before submitting)
					order.setVisible(true);
					btnNewOrder.grabFocus();
				}
			}
		});
		btnCancelOrder.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnCancelOrder.setBackground(new Color(220, 20, 60));
		btnCancelOrder.setBounds(43, 222, 156, 30);
		btnCancelOrder.setFocusPainted(false);
		panel_2.add(btnCancelOrder);
		
		btnSubmitOrder = new JButton("Submit Order");
		btnSubmitOrder.setEnabled(false);
		btnSubmitOrder.setFocusable(false);
		btnSubmitOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lblInputCashAmount.isEnabled()) {
					// cash must be validated
					
					double balance;
					try {
						balance = Double.parseDouble(inputCashAmount.getText().replace("$", ""));
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(new JMenu(), "Cash amount must be an amount above zero.", 
								"Invalid cash amount", JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					if (Double.compare(balance, register.currentSale.getTotal()) < 0) {
						JOptionPane.showMessageDialog(new JMenu(), "Insufficient cash! You need $"
								+ String.format("%.2f", register.currentSale.getTotal()) + ".", "Invalid cash amount", JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					register.makePayment(true, balance);
				} else {
					// credit pays all evenly
					register.makePayment(false, register.currentSale.getTotal());
				}
				
				register.endSale();
				
				btnCash.setEnabled(false);
				lblInputCashAmount.setEnabled(false);
				inputCashAmount.setEnabled(false);
				inputCashAmount.setText("");
				btnCredit.setEnabled(false);
				btnSubmitOrder.setEnabled(false);
				btnCancelOrder.setText("Main Menu");
				btnCancelOrder.setBackground(null);
				
				// print receipt
				Queue<String> receiptData = register.getReceiptData();
				
				while (!receiptData.isEmpty()) {
					receiptPane.setText(receiptPane.getText() + "\n" + receiptData.poll());
				}
			}
		});
		btnSubmitOrder.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnSubmitOrder.setBackground(new Color(34, 139, 34));
		btnSubmitOrder.setBounds(43, 185, 156, 30);
		btnSubmitOrder.setFocusPainted(false);
		panel_2.add(btnSubmitOrder);
	}
}
