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

public class product_page  {

	private JFrame frmTechStoreadmin;
	private JTable table;
	private JMenuBar menuBar;
	private JTextField name_field;
	private JTextField type_field;
	private JTextField storage_field;
	private JTextField ram_field;
	private JTextField display_field;
	private JTextField price_field;
	private JTextField nameid_field;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				product_page window = new product_page();
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
	public product_page(){
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
	
	//notification when field is empty

	private boolean validateFields() {
		
		if(name_field.getText().isEmpty() || type_field.getText().isEmpty() || storage_field.getText().isEmpty()  || ram_field.getText().isEmpty() || display_field.getText().isEmpty() || price_field.getText().isEmpty() ) {
			JOptionPane.showMessageDialog(null, "Fill all fields!");
			return false;
		}
			return true;
	
	}
	
	
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
		frmTechStoreadmin.setBounds(100, 100, 749, 490);
		frmTechStoreadmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ButtonGroup groupe = new ButtonGroup();
		
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(298, 135, 425, 201);
		frmTechStoreadmin.setLocationRelativeTo(null);
		frmTechStoreadmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				TableModel model = table.getModel();
				name_field.setText(model.getValueAt(i, 0).toString());
				type_field.setText(model.getValueAt(i, 1).toString());
				storage_field.setText(model.getValueAt(i, 2).toString());
				ram_field.setText(model.getValueAt(i, 3).toString());
				display_field.setText(model.getValueAt(i, 4).toString());
			    price_field.setText(model.getValueAt(i, 5).toString());
			    nameid_field.setText(model.getValueAt(i, 6).toString());
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
		
		JMenu mnHome = new JMenu("Home");
		mnHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmTechStoreadmin.setVisible(false);
				admin_page window_page = new admin_page();
				JFrame frame= new JFrame();
				frame = window_page.getFrame();
				frame.setVisible(true);
			}
		});

		mnHome.setForeground(Color.LIGHT_GRAY);
		mnHome.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		menuBar.add(mnHome);
		
		JMenu mnProducts = new JMenu("Products");
		mnProducts.setForeground(Color.LIGHT_GRAY);
		mnProducts.setBackground(Color.BLACK);
		mnProducts.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		menuBar.add(mnProducts);
		
		JMenu mnEmployee = new JMenu("Employees");
		mnEmployee.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmTechStoreadmin.setVisible(false);
				employee_page window_page = new employee_page();
				JFrame frame= new JFrame();
				frame = window_page.getFrame();
				frame.setVisible(true);
			}
		});
		mnEmployee.setForeground(Color.LIGHT_GRAY);
		mnEmployee.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		menuBar.add(mnEmployee);
		
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
		
		JLabel products_label = new JLabel("Products");
		products_label.setHorizontalAlignment(SwingConstants.CENTER);
		products_label.setFont(new Font("Century Gothic", Font.PLAIN, 37));
		products_label.setBounds(331, 67, 351, 57);
		frmTechStoreadmin.getContentPane().add(products_label);
		
		JLabel add_product_label = new JLabel("Add Product");
		add_product_label.setHorizontalAlignment(SwingConstants.CENTER);
		add_product_label.setFont(new Font("Century Gothic", Font.PLAIN, 25));
		add_product_label.setBounds(10, 67, 237, 57);
		frmTechStoreadmin.getContentPane().add(add_product_label);
		
		JLabel name_label = new JLabel("Name");
		name_label.setHorizontalAlignment(SwingConstants.CENTER);
		name_label.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		name_label.setBounds(0, 135, 67, 42);
		frmTechStoreadmin.getContentPane().add(name_label);
		
		JLabel type_label = new JLabel("Type");
		type_label.setHorizontalAlignment(SwingConstants.CENTER);
		type_label.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		type_label.setBounds(0, 188, 67, 42);
		frmTechStoreadmin.getContentPane().add(type_label);
		
		JLabel storage_label = new JLabel("Storage");
		storage_label.setHorizontalAlignment(SwingConstants.CENTER);
		storage_label.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		storage_label.setBounds(0, 241, 67, 42);
		frmTechStoreadmin.getContentPane().add(storage_label);
		
		JLabel ram_label = new JLabel("Ram");
		ram_label.setHorizontalAlignment(SwingConstants.CENTER);
		ram_label.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		ram_label.setBounds(0, 294, 67, 42);
		frmTechStoreadmin.getContentPane().add(ram_label);
		
		JLabel display_label = new JLabel("Display");
		display_label.setHorizontalAlignment(SwingConstants.CENTER);
		display_label.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		display_label.setBounds(0, 347, 67, 42);
		frmTechStoreadmin.getContentPane().add(display_label);
		
		JLabel price_label = new JLabel("Price");
		price_label.setHorizontalAlignment(SwingConstants.CENTER);
		price_label.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		price_label.setBounds(0, 400, 67, 42);
		frmTechStoreadmin.getContentPane().add(price_label);
		
		name_field = new JTextField();
		name_field.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		name_field.setBorder(UIManager.getBorder("TextField.border"));
		name_field.setBounds(77, 147, 150, 20);
		frmTechStoreadmin.getContentPane().add(name_field);
		name_field.setColumns(10);
		
		type_field = new JTextField();
		type_field.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		type_field.setColumns(10);
		type_field.setBorder(UIManager.getBorder("TextField.border"));
		type_field.setBounds(77, 200, 150, 20);
		frmTechStoreadmin.getContentPane().add(type_field);
		
		storage_field = new JTextField();
		storage_field.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		storage_field.setColumns(10);
		storage_field.setBorder(UIManager.getBorder("TextField.border"));
		storage_field.setBounds(77, 253, 150, 20);
		frmTechStoreadmin.getContentPane().add(storage_field);
		
		ram_field = new JTextField();
		ram_field.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		ram_field.setColumns(10);
		ram_field.setBorder(UIManager.getBorder("TextField.border"));
		ram_field.setBounds(77, 306, 150, 20);
		frmTechStoreadmin.getContentPane().add(ram_field);
		
		display_field = new JTextField();
		display_field.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		display_field.setColumns(10);
		display_field.setBorder(UIManager.getBorder("TextField.border"));
		display_field.setBounds(77, 359, 150, 20);
		frmTechStoreadmin.getContentPane().add(display_field);
		
		price_field = new JTextField();
		price_field.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		price_field.setColumns(10);
		price_field.setBorder(UIManager.getBorder("TextField.border"));
		price_field.setBounds(77, 412, 150, 20);
		frmTechStoreadmin.getContentPane().add(price_field);
		
		JButton add_button = new JButton("Add");
		add_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validateFields()) {
				try {
					
					Connection conn= null;
					Class.forName("oracle.jdbc.driver.OracleDriver");
					conn =  DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ADMIN","system","1234");
					String query = "insert into products (NAME,TYPE,STORAGE,RAM,DISPLAY,PRICE,ID) values (?,?,?,?,?,?,?)";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, name_field.getText());
					pst.setString(2, type_field.getText());
					pst.setString(3, storage_field.getText());
					pst.setString(4, ram_field.getText());
					pst.setString(5, display_field.getText());
					pst.setString(6, price_field.getText());
					pst.setString(7, nameid_field.getText());
					JOptionPane.showMessageDialog(null, "Data Added!");
					pst.executeUpdate();
					//refresh table
					DefaultTableModel model = (DefaultTableModel)table.getModel();
					model.setRowCount(0);
					show_user();
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
			}
				
			}
		});
		add_button.setBackground(Color.WHITE);
		add_button.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		add_button.setBounds(298, 343, 89, 23);
		frmTechStoreadmin.getContentPane().add(add_button);
		
		JButton update_button = new JButton("Update");
		update_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					Connection conn= null;
					Class.forName("oracle.jdbc.driver.OracleDriver");
					conn =  DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ADMIN","system","1234");
					int row = table.getSelectedRow();
					String value = (table.getModel().getValueAt(row,6).toString());
					String query = "update products set NAME=?,TYPE=?,STORAGE=?,RAM=?,DISPLAY=?,PRICE=? where ID="+value;
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, name_field.getText());
					pst.setString(2, type_field.getText());
					pst.setString(3, storage_field.getText());
					pst.setString(4, ram_field.getText());
					pst.setString(5, display_field.getText());
					pst.setString(6, price_field.getText());
					JOptionPane.showMessageDialog(null, "Data Updated!");
					pst.executeUpdate();
					//refresh table
					DefaultTableModel model = (DefaultTableModel)table.getModel();
					model.setRowCount(0);
					show_user();
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
				
			}
		});
		update_button.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		update_button.setBackground(Color.WHITE);
		update_button.setBounds(397, 343, 89, 23);
		frmTechStoreadmin.getContentPane().add(update_button);
		
		JButton delete_button = new JButton("Delete");
		delete_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection conn= null;
					Class.forName("oracle.jdbc.driver.OracleDriver");
					conn =  DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ADMIN","system","1234");
					int row = table.getSelectedRow();
					String value = (table.getModel().getValueAt(row,6).toString());
					String query = "delete from products where ID="+value;
					PreparedStatement pst = conn.prepareStatement(query);
					JOptionPane.showMessageDialog(null, "Data Deleted!");
					pst.executeUpdate();
					//refresh table
					DefaultTableModel model = (DefaultTableModel)table.getModel();
					model.setRowCount(0);
					show_user();
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		delete_button.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		delete_button.setBackground(Color.WHITE);
		delete_button.setBounds(397, 377, 89, 23);
		frmTechStoreadmin.getContentPane().add(delete_button);
		
		JButton reset_button = new JButton("Reset");
		reset_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				name_field.setText("");
				storage_field.setText("");
				type_field.setText("");
				price_field.setText("");
				ram_field.setText("");
				display_field.setText("");
			}
		});
		reset_button.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		reset_button.setBackground(Color.WHITE);
		reset_button.setBounds(298, 378, 89, 23);
		frmTechStoreadmin.getContentPane().add(reset_button);
		
		JLabel nameid_label = new JLabel("NameId");
		nameid_label.setHorizontalAlignment(SwingConstants.CENTER);
		nameid_label.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		nameid_label.setBounds(298, 406, 67, 30);
		frmTechStoreadmin.getContentPane().add(nameid_label);
		
		nameid_field = new JTextField();
		nameid_field.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		nameid_field.setColumns(10);
		nameid_field.setBorder(UIManager.getBorder("TextField.border"));
		nameid_field.setBounds(397, 412, 89, 20);
		frmTechStoreadmin.getContentPane().add(nameid_field);
		
	
	}
}
