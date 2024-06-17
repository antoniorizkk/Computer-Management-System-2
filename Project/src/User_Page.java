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
import javax.swing.UIManager;

public class User_Page  {

	private JFrame frmTechStoreadmin;
	private JTable table;
	private JMenuBar menuBar;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				User_Page window = new User_Page();
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
	public User_Page(){
		initialize();
	}
	
	//Show data from DataBase
	public ArrayList<Product> productList(){
		ArrayList<Product> productList  = new ArrayList<>();
		try {
		Connection conn= null;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn =  DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ADMIN","system","1234");
		String query1="select * from products";
		Statement st = conn.createStatement();
		st= conn.createStatement();
	    ResultSet rs=st.executeQuery(query1);
		Product product;
		while(rs.next()) {
			product=new Product(rs.getString("NAME"),rs.getString("TYPE"),rs.getString("STORAGE"),rs.getString("RAM"),rs.getString("DISPLAY"),rs.getString("PRICE"),rs.getString("ID"));
			productList.add(product);
		}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return productList;
	}
	
	//show users
	public void show_user() {
		ArrayList<Product> list = productList();
		DefaultTableModel model= (DefaultTableModel) table.getModel();
		Object[] row = new Object[7];
		for(int i=0;i<list.size();i++) {
			row[0]=list.get(i).getName();
			row[1]=list.get(i).getType();
			row[2]=list.get(i).getStorage();
			row[3]=list.get(i).getRam();
			row[4]=list.get(i).getDisplay();
			row[5]=list.get(i).getPrice();
			row[6]=list.get(i).getId();
			model.addRow(row);
		}
		
	}
	

	
	
	// main 
	private void initialize() {
		frmTechStoreadmin = new JFrame();
		frmTechStoreadmin.setResizable(false);
		frmTechStoreadmin.setTitle("Tech Store");
		frmTechStoreadmin.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Antonio_Rizk\\Documents\\UA\\OOP2\\Project\\pictures\\logo.png"));
		frmTechStoreadmin.getContentPane().setBackground(Color.WHITE);
		frmTechStoreadmin.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				
				show_user();
				
			}
		});
		frmTechStoreadmin.setBounds(100, 100, 693, 378);
		frmTechStoreadmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ButtonGroup groupe = new ButtonGroup();
		
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 135, 664, 200);
		frmTechStoreadmin.setLocationRelativeTo(null);
		frmTechStoreadmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				TableModel model = table.getModel();
			}
		});
		table.setFont(new Font("Century Gothic", Font.PLAIN, 11));

		frmTechStoreadmin.getContentPane().setLayout(null);
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				 "NAME", "TYPE", "STORAGE","RAM","DISPLAY", "PRICE","ID"
			}
		));
		frmTechStoreadmin.getContentPane().add(scrollPane);
		
		menuBar = new JMenuBar();
		menuBar.setBackground(Color.BLACK);
		menuBar.setBounds(0, 0, 980, 56);
		frmTechStoreadmin.getContentPane().add(menuBar);
		
		JMenu mnLogout = new JMenu("Logout");
		mnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				frmTechStoreadmin.setVisible(false);
			    login window_page = new login();
				JFrame frame= new JFrame();
				frame = window_page.getFrame();
				frame.setVisible(true);
				
			}
		});
		mnLogout.setForeground(Color.LIGHT_GRAY);
		mnLogout.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		menuBar.add(mnLogout);
		
		JLabel products_label = new JLabel("Products");
		products_label.setHorizontalAlignment(SwingConstants.CENTER);
		products_label.setFont(new Font("Century Gothic", Font.PLAIN, 37));
		products_label.setBounds(170, 67, 351, 57);
		frmTechStoreadmin.getContentPane().add(products_label);
		
	
	}
}
