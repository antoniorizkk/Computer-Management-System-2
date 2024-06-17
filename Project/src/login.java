import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class login {

	private JFrame frmTechStore;
	private JTextField email_textfiel;
	private JPasswordField passwordField;
	
	public JFrame getFrame() {
		return frmTechStore;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
					window.frmTechStore.setVisible(true);
					window.frmTechStore.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					window.frmTechStore.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTechStore = new JFrame();
		frmTechStore.setResizable(false);
		frmTechStore.setTitle("Tech Store");
		frmTechStore.setForeground(Color.BLACK);
		frmTechStore.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Antonio_Rizk\\Documents\\UA\\OOP2\\Project\\pictures\\logo.png"));
		frmTechStore.setBounds(100, 100, 636, 342);
		frmTechStore.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTechStore.getContentPane().setLayout(null);
		
		JPanel image_panel = new JPanel();
		image_panel.setBounds(0, 0, 400, 303);
		frmTechStore.getContentPane().add(image_panel);
		image_panel.setLayout(null);
		
		JLabel image_label = new JLabel("");
		image_label.setBackground(new Color(0, 0, 0));
		image_label.setIcon(new ImageIcon("C:\\Users\\Antonio_Rizk\\Documents\\UA\\OOP2\\Project\\pictures\\dark_image.jpg"));
		image_label.setBounds(0, 0, 400, 303);
		image_panel.add(image_label);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 0));
		panel_1.setBounds(400, 0, 220, 303);
		frmTechStore.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel email_textfield = new JLabel("Login");
		email_textfield.setForeground(new Color(255, 255, 255));
		email_textfield.setFont(new Font("Century Gothic", Font.PLAIN, 40));
		email_textfield.setBounds(0, 40, 107, 63);
		panel_1.add(email_textfield);
		
		email_textfiel = new JTextField();
		email_textfiel.setBorder(null);
		email_textfiel.setBounds(0, 159, 168, 26);
		panel_1.add(email_textfiel);
		email_textfiel.setColumns(10);
		
		JLabel email_label = new JLabel("Email");
		email_label.setLabelFor(email_textfiel);
		email_label.setForeground(new Color(255, 255, 255));
		email_label.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		email_label.setBounds(0, 132, 107, 26);
		panel_1.add(email_label);
		
		JLabel password_label = new JLabel("Password");
		password_label.setForeground(Color.WHITE);
		password_label.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		password_label.setBounds(0, 206, 107, 26);
		panel_1.add(password_label);
		
		passwordField = new JPasswordField();
		passwordField.setBorder(null);
		password_label.setLabelFor(passwordField);
		passwordField.setBounds(0, 233, 168, 26);
		panel_1.add(passwordField);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("show password");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxNewCheckBox.isSelected()) {
					passwordField.setEchoChar((char)0);
				} else {
					passwordField.setEchoChar('\u25cf');
				}
			}
		});
		chckbxNewCheckBox.setBorder(null);
		chckbxNewCheckBox.setForeground(new Color(255, 255, 255));
		chckbxNewCheckBox.setFont(new Font("Century Gothic", Font.PLAIN, 10));
		chckbxNewCheckBox.setBackground(new Color(0, 0, 0));
		chckbxNewCheckBox.setBounds(0, 277, 107, 26);
		panel_1.add(chckbxNewCheckBox);
		
		
		JLabel alert_email = new JLabel("");
		alert_email.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		alert_email.setForeground(new Color(255, 0, 0));
		alert_email.setBounds(0, 192, 168, 14);
		panel_1.add(alert_email);
		
		JLabel alert_password = new JLabel("");
		alert_password.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		alert_password.setForeground(new Color(255, 0, 0));
		alert_password.setLabelFor(passwordField);
		alert_password.setBounds(0, 263, 137, 14);
		panel_1.add(alert_password);
		
		JLabel alert_login = new JLabel("");
		alert_login.setForeground(Color.RED);
		alert_login.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		alert_login.setBounds(0, 119, 137, 14);
		panel_1.add(alert_login);
		
		JButton login_button = new JButton("Login");
		login_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				

				if(!email_textfiel.getText().trim().isEmpty()   &&  !passwordField.getText().trim().isEmpty() ) {
				try {
					alert_email.setText("");
					alert_password.setText("");
					Connection conn= null;
					Class.forName("oracle.jdbc.driver.OracleDriver");
					conn =  DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ADMIN","system","1234");
					String sql = "select *  from USERS where EMAIL = ? and PASSWORD = ?";
					PreparedStatement pst = conn.prepareStatement(sql);
					pst.setString(1, email_textfiel.getText());
					pst.setString(2, passwordField.getText());
					ResultSet res = pst.executeQuery();
					if(res.next()) {
						frmTechStore.setVisible(false);
						User_Page window_page = new User_Page();
						JFrame frame= new JFrame();
						frame = window_page.getFrame();
						frame.setVisible(true);
					}else {
						alert_login.setText("Wrong Email or password!");
					}
				} catch (SQLException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				if(email_textfiel.getText().trim().isEmpty()) {
					alert_email.setText("This field is empty!");
				}
				else {
					alert_email.setText("");
				}
				if(passwordField.getText().trim().isEmpty()) {
					alert_password.setText("This field is empty!");
				}
				else {
					alert_password.setText("");
				}
			}

			}
		});
		login_button.setBackground(new Color(0, 0, 0));
		login_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				login_button.setForeground(Color.red);
				login_button.setBackground(Color.black);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				login_button.setForeground(Color.white);
				login_button.setBackground(Color.black);
			}
		});
		login_button.setForeground(new Color(255, 255, 255));
		login_button.setBorder(null);
		login_button.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		login_button.setBounds(169, 279, 41, 21);
		panel_1.add(login_button);
		
		JLabel register_label = new JLabel("Register");
		register_label.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				register_label.setForeground(Color.red);
				register_label.setBackground(Color.black);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				register_label.setForeground(Color.white);
				register_label.setBackground(Color.black);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				frmTechStore.setVisible(false);
				register window_register = new register();
				JFrame frame= new JFrame();
				frame = window_register.getFrame();
				frame.setVisible(true);
				
			}
		});
		register_label.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		register_label.setForeground(new Color(255, 255, 255));
		register_label.setBounds(117, 283, 46, 14);
		panel_1.add(register_label);
		
	
		
	
	}
	private static class __Tmp {
		private static void __tmp() {
			  javax.swing.JPanel __wbp_panel = new javax.swing.JPanel();
		}
	}
}
