import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFormattedTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class register {

	private JFrame frmTechStore;
	private JTextField fullname_field;
	private JPasswordField password_field;
	private JTextField pnumber_field;
	private JTextField wallet_field;
	private JTextField email_field;
	
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
					register window = new register();
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
	public register() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTechStore = new JFrame();
		frmTechStore.setResizable(false);
		frmTechStore.setTitle("Tech Store");
		frmTechStore.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Antonio_Rizk\\Documents\\UA\\OOP2\\Project\\pictures\\logo.png"));
		frmTechStore.setBounds(100, 100, 788, 491);
		frmTechStore.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTechStore.getContentPane().setLayout(null);
		
		JPanel image_panel = new JPanel();
		image_panel.setBackground(new Color(0, 0, 0));
		image_panel.setBounds(0, 0, 432, 452);
		frmTechStore.getContentPane().add(image_panel);
		image_panel.setLayout(null);
		
		JLabel image_label = new JLabel("");
		image_label.setIcon(new ImageIcon("C:\\Users\\Antonio_Rizk\\Documents\\UA\\OOP2\\Project\\pictures\\gaming_pc.jpg"));
		image_label.setBounds(0, 0, 432, 452);
		image_panel.add(image_label);
		
		JPanel phonenum_field = new JPanel();
		phonenum_field.setBackground(new Color(0, 0, 0));
		phonenum_field.setBounds(431, 0, 350, 452);
		frmTechStore.getContentPane().add(phonenum_field);
		phonenum_field.setLayout(null);
		
		JLabel register_label = new JLabel("Register");
		register_label.setForeground(Color.WHITE);
		register_label.setFont(new Font("Century Gothic", Font.PLAIN, 40));
		register_label.setBounds(0, 0, 160, 107);
		phonenum_field.add(register_label);
		
		JLabel fullname_label = new JLabel("Full Name");
		fullname_label.setForeground(Color.WHITE);
		fullname_label.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		fullname_label.setBounds(10, 111, 107, 26);
		phonenum_field.add(fullname_label);
		
		fullname_field = new JTextField();
		fullname_field.setColumns(10);
		fullname_field.setBorder(null);
		fullname_field.setBounds(10, 148, 131, 26);
		phonenum_field.add(fullname_field);
		
		JLabel password_label = new JLabel("Password");
		password_label.setForeground(Color.WHITE);
		password_label.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		password_label.setBounds(10, 200, 107, 26);
		phonenum_field.add(password_label);
		
		password_field = new JPasswordField();
		password_field.setBorder(null);
		password_field.setBounds(10, 237, 141, 26);
		phonenum_field.add(password_field);
		
		JLabel address_label = new JLabel("Address");
		address_label.setForeground(Color.WHITE);
		address_label.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		address_label.setBounds(10, 285, 107, 26);
		phonenum_field.add(address_label);
		
		JTextArea address = new JTextArea();
		address.setBounds(10, 322, 131, 107);
		phonenum_field.add(address);
		
		JLabel phonenum_label = new JLabel("Phone Number");
		phonenum_label.setForeground(Color.WHITE);
		phonenum_label.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		phonenum_label.setBounds(163, 200, 107, 26);
		phonenum_field.add(phonenum_label);
		
		pnumber_field = new JTextField();
		pnumber_field.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!Character.isDigit(c) ) {
					e.consume();
				}
				if(pnumber_field.getText().length()>7) {
					e.consume();
				}
			}
		});


		pnumber_field.setColumns(10);
		pnumber_field.setBorder(null);
		pnumber_field.setBounds(163, 237, 131, 26);
		phonenum_field.add(pnumber_field);
		
		JLabel wallet_label = new JLabel("wallet");
		wallet_label.setForeground(Color.WHITE);
		wallet_label.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		wallet_label.setBounds(163, 285, 107, 26);
		phonenum_field.add(wallet_label);
		
		wallet_field = new JTextField();
		wallet_field.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!Character.isDigit(c) ) {
					e.consume();
				}
			}
		});
		wallet_field.setColumns(10);
		wallet_field.setBorder(null);
		wallet_field.setBounds(163, 321, 131, 26);
		phonenum_field.add(wallet_field);
		
		JLabel alert_fname = new JLabel("");
		alert_fname.setForeground(Color.RED);
		alert_fname.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		alert_fname.setBounds(10, 100, 131, 14);
		phonenum_field.add(alert_fname);
		
		JLabel alert_email = new JLabel("");
		alert_email.setForeground(Color.RED);
		alert_email.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		alert_email.setBounds(163, 100, 131, 14);
		phonenum_field.add(alert_email);
		
		JLabel alert_password = new JLabel("");
		alert_password.setForeground(Color.RED);
		alert_password.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		alert_password.setBounds(10, 185, 131, 14);
		phonenum_field.add(alert_password);
		
		JLabel alert_address = new JLabel("");
		alert_address.setForeground(Color.RED);
		alert_address.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		alert_address.setBounds(10, 274, 131, 14);
		phonenum_field.add(alert_address);
		
		JLabel alert_pnumber = new JLabel("");
		alert_pnumber.setForeground(Color.RED);
		alert_pnumber.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		alert_pnumber.setBounds(163, 185, 177, 14);
		phonenum_field.add(alert_pnumber);
		
		JLabel alert_wallet = new JLabel("");
		alert_wallet.setForeground(Color.RED);
		alert_wallet.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		alert_wallet.setBounds(163, 274, 177, 14);
		phonenum_field.add(alert_wallet);
		
		JButton register_button = new JButton("Register");
		register_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				register_button.setForeground(Color.red);
				register_button.setBackground(Color.black);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				register_button.setForeground(Color.white);
				register_button.setBackground(Color.black);
			}
		});
		register_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					Connection conn= null;
					Class.forName("oracle.jdbc.driver.OracleDriver");
					conn =  DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ADMIN","system","1234");
					if(!fullname_field.getText().trim().isEmpty()  && !email_field.getText().trim().isEmpty() && !password_field.getText().trim().isEmpty() && !address.getText().trim().isEmpty() && !pnumber_field.getText().trim().isEmpty() && !wallet_field.getText().trim().isEmpty() ) {
						alert_fname.setText("");
						alert_email.setText("");
						alert_password.setText("");
						alert_address.setText("");
						alert_wallet.setText("");
						alert_pnumber.setText("");
					    if(pnumber_field.getText().length() == 8 && wallet_field.getText().length() >=3  ) {
						String query = "select * from users where EMAIL = ?";
						PreparedStatement pst = conn.prepareStatement(query);
						pst.setString(1, email_field.getText());
						ResultSet res =pst.executeQuery();
						if(res.next()) {
							alert_email.setText("Email already used!");
						}else {
							String sql = "insert into users (FULL_NAME,EMAIL,ADDRESS,PASSWORD,WALLET,PHONE_NUMBER) values(?,?,?,?,?,?)";
							PreparedStatement pst1 = conn.prepareStatement(sql);
							pst1.setString(1, fullname_field.getText());
							pst1.setString(2, email_field.getText());
							pst1.setString(3, address.getText());
							pst1.setString(4, password_field.getText());
							pst1.setString(5, wallet_field.getText());
							pst1.setString(6, pnumber_field.getText());
							ResultSet res2 = pst1.executeQuery();
							if(res2.next()) {
								frmTechStore.setVisible(false);
								login window_login = new login();
								JFrame frame= new JFrame();
								frame = window_login.getFrame();
								frame.setVisible(true);
							}
						}
						
					  } else {
						  if(pnumber_field.getText().length() != 8) {
							  alert_pnumber.setText("Field Contains less the 8 numbers!");
						  } else {
							  alert_wallet.setText("Wallet should be higher then 99$!");
						  }
					  }
					} else {
						alert_fname.setText("This field is empty!");
						alert_email.setText("This field is empty!");
						alert_password.setText("This field is empty!");
						alert_address.setText("This field is empty!");
						alert_wallet.setText("This field is empty!");
						alert_pnumber.setText("This field is empty!");
					}
				} catch (SQLException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		register_button.setForeground(Color.WHITE);
		register_button.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		register_button.setBorder(null);
		register_button.setBackground(Color.BLACK);
		register_button.setBounds(202, 378, 56, 31);
		phonenum_field.add(register_button);
		
		JLabel email_label = new JLabel("Email");
		email_label.setForeground(Color.WHITE);
		email_label.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		email_label.setBounds(163, 111, 107, 26);
		phonenum_field.add(email_label);
		
		email_field = new JTextField();
		email_field.setColumns(10);
		email_field.setBorder(null);
		email_field.setBounds(163, 148, 131, 26);
		phonenum_field.add(email_field);
	}
}
