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

public class employee_page  {

	private JFrame frmTechStoreadmin;
	private JTable table;
	private JMenuBar menuBar;
	private JTextField name_field;
	private JTextField salary_field;
	private JTextField id_field;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				employee_page window = new employee_page();
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
	public employee_page(){
		initialize();
	}
	
	//Show data from DataBase
	public ArrayList<Employee> employeeList(){
		ArrayList<Employee> employeeList  = new ArrayList<>();
		try {
		Connection conn= null;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn =  DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ADMIN","system","1234");
		String query1="select * from employee";
		Statement st = conn.createStatement();
		st= conn.createStatement();
	    ResultSet rs=st.executeQuery(query1);
		Employee employee;
		while(rs.next()) {
			employee=new Employee(rs.getString("NAME"),rs.getString("SALARY"),rs.getString("ID"));
			employeeList.add(employee);
		}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return employeeList;
	}
	
	//show users
	public void show_user() {
		ArrayList<Employee> list = employeeList();
		DefaultTableModel model= (DefaultTableModel) table.getModel();
		Object[] row = new Object[7];
		for(int i=0;i<list.size();i++) {
			row[0]=list.get(i).getName();
			row[1]=list.get(i).getSalary();
			row[2]=list.get(i).getId();
			model.addRow(row);
		}
		
	}
	
	//notification when field is empty

	private boolean validateFields() {
		
		if(name_field.getText().isEmpty() || salary_field.getText().isEmpty() || id_field.getText().isEmpty()   ) {
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
				salary_field.setText(model.getValueAt(i, 1).toString());
				id_field.setText(model.getValueAt(i, 2).toString());

			}
		});
		table.setFont(new Font("Century Gothic", Font.PLAIN, 11));

		frmTechStoreadmin.getContentPane().setLayout(null);
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				 "NAME", "SALARY", "ID"
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
				//frmTechStore.setVisible(false);
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
		
		JMenu mnEmployee = new JMenu("Employees");

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
		
		JLabel products_label = new JLabel("Employees");
		products_label.setHorizontalAlignment(SwingConstants.CENTER);
		products_label.setFont(new Font("Century Gothic", Font.PLAIN, 37));
		products_label.setBounds(331, 67, 351, 57);
		frmTechStoreadmin.getContentPane().add(products_label);
		
		JLabel add_employee_label = new JLabel("Add Employee");
		add_employee_label.setHorizontalAlignment(SwingConstants.CENTER);
		add_employee_label.setFont(new Font("Century Gothic", Font.PLAIN, 25));
		add_employee_label.setBounds(10, 67, 237, 57);
		frmTechStoreadmin.getContentPane().add(add_employee_label);
		
		JLabel name_label = new JLabel("Name");
		name_label.setHorizontalAlignment(SwingConstants.CENTER);
		name_label.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		name_label.setBounds(0, 135, 67, 42);
		frmTechStoreadmin.getContentPane().add(name_label);
		
		JLabel salary_label = new JLabel("Salary");
		salary_label.setHorizontalAlignment(SwingConstants.CENTER);
		salary_label.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		salary_label.setBounds(0, 188, 67, 42);
		frmTechStoreadmin.getContentPane().add(salary_label);
		
		JLabel id_label = new JLabel("ID");
		id_label.setHorizontalAlignment(SwingConstants.CENTER);
		id_label.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		id_label.setBounds(0, 241, 67, 42);
		frmTechStoreadmin.getContentPane().add(id_label);
		
		name_field = new JTextField();
		name_field.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		name_field.setBorder(UIManager.getBorder("TextField.border"));
		name_field.setBounds(77, 147, 150, 20);
		frmTechStoreadmin.getContentPane().add(name_field);
		name_field.setColumns(10);
		
		salary_field = new JTextField();
		salary_field.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		salary_field.setColumns(10);
		salary_field.setBorder(UIManager.getBorder("TextField.border"));
		salary_field.setBounds(77, 200, 150, 20);
		frmTechStoreadmin.getContentPane().add(salary_field);
		
		id_field = new JTextField();
		id_field.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		id_field.setColumns(10);
		id_field.setBorder(UIManager.getBorder("TextField.border"));
		id_field.setBounds(77, 253, 150, 20);
		frmTechStoreadmin.getContentPane().add(id_field);
		
		JButton add_button = new JButton("Add");
		add_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validateFields()) {
				try {
					
					Connection conn= null;
					Class.forName("oracle.jdbc.driver.OracleDriver");
					conn =  DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ADMIN","system","1234");
					String query = "insert into employee (NAME,SALARY,ID) values (?,?,?)";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, name_field.getText());
					pst.setString(2, salary_field.getText());
					pst.setString(3, id_field.getText());
					
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
		add_button.setBounds(10, 294, 89, 23);
		frmTechStoreadmin.getContentPane().add(add_button);
		
		JButton update_button = new JButton("Update");
		update_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					Connection conn= null;
					Class.forName("oracle.jdbc.driver.OracleDriver");
					conn =  DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ADMIN","system","1234");
					int row = table.getSelectedRow();
					String value = (table.getModel().getValueAt(row,2).toString());
					String query = "update employee set NAME=?,SALARY=? where ID="+value;
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, name_field.getText());
					pst.setString(2, salary_field.getText());
					//pst.setString(3, id_field.getText());

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
		update_button.setBounds(138, 328, 89, 23);
		frmTechStoreadmin.getContentPane().add(update_button);
		
		JButton delete_button = new JButton("Delete");
		delete_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection conn= null;
					Class.forName("oracle.jdbc.driver.OracleDriver");
					conn =  DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ADMIN","system","1234");
					int row = table.getSelectedRow();
					String value = (table.getModel().getValueAt(row,2).toString());
					String query = "delete from employee where ID="+value;
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
		delete_button.setBounds(10, 324, 89, 23);
		frmTechStoreadmin.getContentPane().add(delete_button);
		
		JButton reset_button = new JButton("Reset");
		reset_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				name_field.setText("");
				id_field.setText("");
				salary_field.setText("");
			}
		});
		reset_button.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		reset_button.setBackground(Color.WHITE);
		reset_button.setBounds(138, 294, 89, 23);
		frmTechStoreadmin.getContentPane().add(reset_button);
		
	
	}
}
