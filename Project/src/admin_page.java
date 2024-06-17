import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import java.awt.ScrollPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class admin_page  {

	private JFrame frmTechStoreadmin;
	private JTable table;
	private JMenuBar menuBar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				admin_page window = new admin_page();
				window.frmTechStoreadmin.setVisible(true);
				
			}
		});
	}


	public JFrame getFrame() {
		return frmTechStoreadmin;
	}
	/**
	 * @wbp.parser.entryPoint
	 */
	public admin_page(){
		initialize();
	}
	
	//Show data from DataBase
	public ArrayList<User> usersList(){
		ArrayList<User> usersList  = new ArrayList<>();
		try {
		Connection conn= null;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn =  DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ADMIN","system","1234");
		String query1="select * from users";
		Statement st = conn.createStatement();
		st= conn.createStatement();
	    ResultSet rs=st.executeQuery(query1);
		User user;
		while(rs.next()) {
			user=new User(rs.getString("FULL_NAME"),rs.getString("EMAIL"),rs.getString("ADDRESS"),rs.getString("PASSWORD"),rs.getString("WALLET"),rs.getString("PHONE_NUMBER"));
			usersList.add(user);
		}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return usersList;
	}
	
	//show users
	public void show_user() {
		ArrayList<User> list = usersList();
		DefaultTableModel model= (DefaultTableModel) table.getModel();
		Object[] row = new Object[6];
		for(int i=0;i<list.size();i++) {
			row[0]=list.get(i).getFull_name();
			row[1]=list.get(i).getEmail();
			row[2]=list.get(i).getAddress();
			row[3]=list.get(i).getPassword();
			row[4]=list.get(i).getWallet();
			row[5]=list.get(i).getPhone_number();
			model.addRow(row);
		}
		
	}
	
	//notification when field is empty

	
	
	// main 
	private void initialize() {
		frmTechStoreadmin = new JFrame();
		frmTechStoreadmin.setResizable(false);
		frmTechStoreadmin.setTitle("Tech Store-Admin");
		frmTechStoreadmin.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Antonio_Rizk\\Documents\\UA\\OOP2\\Project\\pictures\\logo.png"));
		frmTechStoreadmin.getContentPane().setBackground(Color.WHITE);
		frmTechStoreadmin.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				
				show_user();
				
			}
		});
		frmTechStoreadmin.setBounds(100, 100, 749, 366);
		frmTechStoreadmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ButtonGroup groupe = new ButtonGroup();
		
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 135, 740, 201);
		frmTechStoreadmin.setLocationRelativeTo(null);
		frmTechStoreadmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		table = new JTable();

		frmTechStoreadmin.getContentPane().setLayout(null);
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				 "FULL_NAME", "EMAIL", "ADDRESS", "PASSWORD", "WALLET","PHONE_NUMBER"
			}
		));
		frmTechStoreadmin.getContentPane().add(scrollPane);
		
		menuBar = new JMenuBar();
		menuBar.setBackground(Color.BLACK);
		menuBar.setBounds(0, 0, 980, 56);
		frmTechStoreadmin.getContentPane().add(menuBar);
		
		JMenu mnProducts = new JMenu("Products");
		mnProducts.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmTechStoreadmin.setVisible(false);
				product_page window_page = new product_page();
				JFrame frame= new JFrame();
				frame = window_page.getFrame();
				frame.setVisible(true);
			}
		});
		mnProducts.setForeground(Color.LIGHT_GRAY);
		mnProducts.setBackground(Color.BLACK);
		mnProducts.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		menuBar.add(mnProducts);
		
		JMenu mnUsers = new JMenu("Employees");
		mnUsers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmTechStoreadmin.setVisible(false);
				employee_page window_page = new employee_page();
				JFrame frame= new JFrame();
				frame = window_page.getFrame();
				frame.setVisible(true);
			}
		});
		mnUsers.setForeground(Color.LIGHT_GRAY);
		mnUsers.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		menuBar.add(mnUsers);
		
		JMenu mnLogout = new JMenu("Logout");
		mnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmTechStoreadmin.setVisible(false);
				login_admin window_page = new login_admin();
				JFrame frame= new JFrame();
				frame = window_page.getFrame();
				frame.setVisible(true);
			}
		});
		mnLogout.setForeground(Color.LIGHT_GRAY);
		mnLogout.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		menuBar.add(mnLogout);
		
		JLabel views_users = new JLabel("View Users");
		views_users.setFont(new Font("Century Gothic", Font.PLAIN, 37));
		views_users.setHorizontalAlignment(SwingConstants.CENTER);
		views_users.setBounds(194, 67, 351, 57);
		frmTechStoreadmin.getContentPane().add(views_users);
	}
}
