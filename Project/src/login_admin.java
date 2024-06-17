import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class login_admin {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel login_label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login_admin window = new login_admin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public login_admin() {
		initialize();
	}
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		
		JLabel admin_label = new JLabel("Admin Name");
		admin_label.setHorizontalAlignment(SwingConstants.CENTER);
		admin_label.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		admin_label.setBounds(10, 85, 98, 42);
		frame.getContentPane().add(admin_label);
		
		JLabel password_label = new JLabel("Password");
		password_label.setHorizontalAlignment(SwingConstants.CENTER);
		password_label.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		password_label.setBounds(10, 142, 98, 42);
		frame.getContentPane().add(password_label);
		
		textField = new JTextField();
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setColumns(10);
		textField.setBorder(null);
		textField.setBounds(130, 97, 168, 26);
		frame.getContentPane().add(textField);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(Color.LIGHT_GRAY);
		passwordField.setBorder(null);
		passwordField.setBounds(130, 151, 168, 26);
		frame.getContentPane().add(passwordField);
		
		login_label = new JLabel("Login");
		login_label.setHorizontalAlignment(SwingConstants.LEFT);
		login_label.setFont(new Font("Century Gothic", Font.PLAIN, 37));
		login_label.setBounds(10, 29, 198, 57);
		frame.getContentPane().add(login_label);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("show password");
		chckbxNewCheckBox.setFont(new Font("Century Gothic", Font.PLAIN, 9));
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxNewCheckBox.isSelected()) {
					passwordField.setEchoChar((char)0);
				} else {
					passwordField.setEchoChar('\u25cf');
				}
			}
		});
		chckbxNewCheckBox.setBackground(Color.WHITE);
		chckbxNewCheckBox.setBounds(20, 191, 110, 23);
		frame.getContentPane().add(chckbxNewCheckBox);
		
		JButton login_button = new JButton("Login");
		login_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				if(!textField.getText().trim().isEmpty()   &&  !passwordField.getText().trim().isEmpty() ) {
				try {
					Connection conn= null;
					Class.forName("oracle.jdbc.driver.OracleDriver");
					conn =  DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ADMIN","system","1234");
					String sql = "select *  from admin where ADMIN_NAME = ? and PASSWORD = ?";
					PreparedStatement pst = conn.prepareStatement(sql);
					pst.setString(1, textField.getText());
					pst.setString(2, passwordField.getText());
					ResultSet res = pst.executeQuery();
					if(res.next()) {
						//JOptionPane.showMessageDialog(null, "Success");
						frame.setVisible(false);
						admin_page window_page = new admin_page();
						JFrame frame1= new JFrame();
						frame1 = window_page.getFrame();
						frame1.setVisible(true);
					}else {
						//alert_login.setText("Wrong Email or password!");
						JOptionPane.showMessageDialog(null, "Wrong Email!");
					}
				} catch (SQLException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				if(textField.getText().trim().isEmpty()) {
					//alert_email.setText("This field is empty!");
					JOptionPane.showMessageDialog(null, "Admin Name is empty!");
				}
				else {
					//alert_email.setText("");
				}
				if(passwordField.getText().trim().isEmpty()) {
					//alert_password.setText("This field is empty!");
					JOptionPane.showMessageDialog(null, "Password is empty!");
				}
				else {
					//alert_password.setText("");
				}
			}

			}
		});
		login_button.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		login_button.setBackground(Color.WHITE);
		login_button.setBounds(209, 188, 89, 23);
		frame.getContentPane().add(login_button);
		frame.setBounds(100, 100, 324, 302);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
