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

public class UI {

	private JFrame frmProjectAssignment;
	private JPanel Landing;
	private JPanel Order;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI window = new UI();
					window.frmProjectAssignment.setVisible(true);
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
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmProjectAssignment = new JFrame();
		frmProjectAssignment.setIconImage(Toolkit.getDefaultToolkit().getImage(UI.class.getResource("/resources/burg.png")));
		frmProjectAssignment.setTitle("Coronary Castle");
		frmProjectAssignment.setResizable(false);
		frmProjectAssignment.setBounds(100, 100, 450, 300);
		frmProjectAssignment.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmProjectAssignment.setLocationRelativeTo(null);
		frmProjectAssignment.getContentPane().setLayout(new CardLayout(0, 0));
		
		Landing = new JPanel();
		Landing.setBorder(null);
		frmProjectAssignment.getContentPane().add(Landing, "landing");
		Landing.setLayout(null);
		
		JLabel lblBurg = new JLabel("Burger");
		lblBurg.setIcon(new ImageIcon(UI.class.getResource("/resources/burg.png")));
		lblBurg.setBounds(138, 14, 293, 242);
		Landing.add(lblBurg);
		
		JButton btnStart = new JButton("Start");
		btnStart.setToolTipText("Begin a new sale");
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnStart.setBounds(33, 60, 110, 43);
		btnStart.setFocusPainted(false);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Landing.setVisible(false);
				Order.setVisible(true);
			}
		});
		Landing.add(btnStart);
		
		JButton btnAbout = new JButton("About");
		btnAbout.setToolTipText("Credits and attribution");
		btnAbout.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAbout.setBounds(33, 110, 110, 43);
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
		btnExit.setBounds(33, 160, 110, 43);
		btnExit.setFocusPainted(false);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmProjectAssignment.dispatchEvent(new WindowEvent(frmProjectAssignment, WindowEvent.WINDOW_CLOSING));
			}
		});
		Landing.add(btnExit);
		
		JPanel welcomeBorder = new JPanel();
		welcomeBorder.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Welcome", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		welcomeBorder.setBounds(10, 7, 424, 253);
		Landing.add(welcomeBorder);
		
		Order = new JPanel();
		frmProjectAssignment.getContentPane().add(Order, "order");
		Order.setLayout(null);
		
		JPanel menuBorder = new JPanel();
		menuBorder.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Menu", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		menuBorder.setBounds(10, 11, 169, 249);
		Order.add(menuBorder);
		
		JPanel toppingsBorder = new JPanel();
		toppingsBorder.setBorder(new TitledBorder(null, "Toppings", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		toppingsBorder.setBounds(189, 11, 245, 127);
		Order.add(toppingsBorder);
		
		JPanel toolsBorder = new JPanel();
		toolsBorder.setBorder(new TitledBorder(null, "Tools", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		toolsBorder.setBounds(189, 149, 245, 111);
		Order.add(toolsBorder);
	}
}
