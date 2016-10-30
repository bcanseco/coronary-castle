/*
 * Group 5
 * Project Assignment 5
 * Software Design Methods
 * Fall 2016, Florida Institute of Technology
 */

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class UI {

	private JFrame frmProjectAssignment;

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
		frmProjectAssignment.setTitle("Project Assignment #6");
		frmProjectAssignment.setResizable(false);
		frmProjectAssignment.setBounds(100, 100, 450, 300);
		frmProjectAssignment.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmProjectAssignment.getContentPane().setLayout(null);
		frmProjectAssignment.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Coronary Castle", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 424, 249);
		frmProjectAssignment.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblBurg = new JLabel("Burger");
		lblBurg.setIcon(new ImageIcon(UI.class.getResource("/resources/burg.png")));
		lblBurg.setBounds(125, 7, 297, 239);
		panel.add(lblBurg);
		
		JButton btnStart = new JButton("Start");
		btnStart.setToolTipText("Begin a new sale");
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnStart.setBounds(24, 60, 110, 43);
		btnStart.setFocusPainted(false);
		panel.add(btnStart);
		
		JButton btnAbout = new JButton("About");
		btnAbout.setToolTipText("Credits and attribution");
		btnAbout.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAbout.setBounds(24, 110, 110, 43);
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
		panel.add(btnAbout);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setToolTipText("Have a Coronary day!");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnExit.setBounds(24, 160, 110, 43);
		btnExit.setFocusPainted(false);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmProjectAssignment.dispatchEvent(new WindowEvent(frmProjectAssignment, WindowEvent.WINDOW_CLOSING));
			}
		});
		panel.add(btnExit);
	}
}
