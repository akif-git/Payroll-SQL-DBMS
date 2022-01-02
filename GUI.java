import java.applet.Applet; // imports
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel; 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 
 */
public class GUI extends JFrame implements ActionListener{

	// Declaring GUI elements
	private Font font; 
	private JButton btn, btninstruction, btnexit, r2d2, c3p0, btnQuery;
	private JFrame f;
	private JPanel panel;

	public GUI () {
		
		setLayout(null);
		f = new JFrame (); // creating JFrame
		
		r2d2 = new JButton("Create Tables");
		
		panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(50,50,20,30));
		panel.add(r2d2);

		r2d2.addActionListener(this);
		c3p0 = new JButton ("Drop Tables");
		c3p0.setBounds(320, 250, 80, 30);
		c3p0.addActionListener(this);

		btninstruction = new JButton ("Populate Tables"); // creating button
		btninstruction.setBounds(165, 295, 120, 50); // setting bounds
		btninstruction.addActionListener(this); // making this class listen
		btnQuery = new JButton ("Queries"); // creating button
		btnQuery.setBounds(280, 295, 100, 50); // setting bounds
		btnQuery.addActionListener(this); // making this class listen
		btnexit = new JButton ("Exit"); // creating button
		btnexit.setBounds(280, 295, 100, 50); // setting bounds
		btnexit.addActionListener(this); // making this class listen
		panel.add(c3p0);
		panel.add(btninstruction);
		panel.add(btnQuery);
		panel.add(btnexit);
		
		f.add(panel);
		f.setSize(1080, 720); // setting size of the frame


		f.setVisible(true);
	}

	public void actionPerformed (ActionEvent e) // action performed
	{
		

		if (e.getSource () == r2d2)  // if create tables
		{
			Connection conn1 = null;

	        try {
	            // registers Oracle JDBC driver - though this is no longer required
	            // since JDBC 4.0, but added here for backward compatibility
	            Class.forName("oracle.jdbc.OracleDriver");

	            String dbURL1 = "jdbc:oracle:thin:amimtiaz/08184438@oracle.scs.ryerson.ca:1521:orcl";  // that is school Oracle database and you can only use it in the labs

				conn1 = DriverManager.getConnection(dbURL1);
	            if (conn1 != null) {
	                System.out.println("Connected with connection #1");
	                JLabel connect = new JLabel("Connected to DBMS!");
	        	    connect.setFont(new Font("Verdana",30,50));
	        	    connect.setBounds(380, 795, 200, 150);
	        	    panel.add(connect);
	        	    f.setVisible(true);
	            }
	            
	            String query1 = "CREATE TABLE DISPATCHES("
	            	      + "ProductName VARCHAR (20) NOT NULL, "
	            	      + "CustomerName VARCHAR (20) NOT NULL, "
	            	      + "DispatchDate date, "
	            	      + "DeliveryTime timestamp, "
	            	      + "Price INT, "
	            	      + "Location varchar(20))";
	            
	            String payroll = "CREATE TABLE payroll("
	            		+ "PAYROLL_ID INT PRIMARY KEY, "
	            		+ "TotalCompanyRevenue FLOAT, "
	            		+ "PaymentBudget FLOAT, "
	            		+ "PayrollType VARCHAR2(20), "
	            		+ "PayrollDesc VARCHAR2(100))";
	            
	            String payment = "CREATE TABLE payment("
	            		+ "PAY_ID INT PRIMARY KEY, "
	            		+ "PAYROLL_ID_FOREIGN INT, "
	            		+ "BiWeeklySalary FLOAT, "
	            		+ "OvertimeAmount FLOAT, "
	            		+ "Claimable_Expenses FLOAT, "
	            		+ "VacationPay FLOAT, "
	            		+ "BonusAndComissions FLOAT, "
	            		+ "FOREIGN KEY(PAYROLL_ID_FOREIGN) REFERENCES payroll(PAYROLL_ID) ON DELETE CASCADE)";
	            
	            String department = "CREATE TABLE department("
	            		+ "DEPARTMENT_ID INT PRIMARY KEY, "
	            		+ "NumberOfEmployees INT NOT NULL, "
	            		+ "DEP_NAME VARCHAR2(20), "
	            		+ "DEP_MANAGER VARCHAR2(20))";
	            
	            String login = "CREATE TABLE login("
	            		+ "EMAIL VARCHAR(35) PRIMARY KEY, "
	            		+ "Password1 VARCHAR2(20), "
	            		+ "Username VARCHAR2(12), "
	            		+ "Seq_questions VARCHAR(600), "
	            		+ "Answers VARCHAR(30))";
	            
	            String ll_employee = "CREATE TABLE lower_level_employee("
	            		+ "EMAIL VARCHAR(35) PRIMARY KEY REFERENCES login(EMAIL), "
	            		+ "RestrictedAccess int)";
	            
	            String manager = "CREATE TABLE manager("
	            		+ "EMAIL VARCHAR(35) PRIMARY KEY REFERENCES login(EMAIL), "
	            		+ "ViewDownAccess int)";
	            
	            String HR = "CREATE TABLE HR("
	            		+ "EMAIL VARCHAR(35) PRIMARY KEY REFERENCES login(EMAIL), "
	            		+ "SuperAccess int)";
	            
	            String employee = "CREATE TABLE employee("
	            		+ "EMP_ID  INT PRIMARY KEY, "
	            		+ "PAY_ID_FOREIGN INT, "
	            		+ "DEPT_ID_FOREIGN INT, "
	            		+ "EMAIL_FOREIGN VARCHAR(35), "
	            		+ "E_Name  VARCHAR2(20), "
	            		+ "E_Salary FLOAT, "
	            		+ "Pay_Date DATE, "
	            		+ "FOREIGN KEY (PAY_ID_FOREIGN) REFERENCES payment(PAY_ID) ON DELETE CASCADE, "
	            		+ "FOREIGN KEY (DEPT_ID_FOREIGN) REFERENCES department(DEPARTMENT_ID) ON DELETE CASCADE, "
	            		+ "FOREIGN KEY (EMAIL_FOREIGN) REFERENCES login(email) ON DELETE CASCADE)";
	            
	            String parttime = "CREATE TABLE parttime("
	            		+ "EMP_ID INT REFERENCES employee(EMP_ID), "
	            		+ "HourlyRate FLOAT, "
	            		+ "Type VARCHAR(20), "
	            		+ "PRIMARY KEY(EMP_ID)) ";
	            
	            String fulltime = "CREATE TABLE fulltime("
	            		+ "EMP_ID INT REFERENCES employee(EMP_ID), "
	            		+ "MonthlyRate FLOAT, "
	            		+ "PRIMARY KEY(EMP_ID)) ";
	            
	            String locations = "CREATE TABLE locations("
	            		+ "DEPARTMENT_ID INT PRIMARY KEY REFERENCES department(DEPARTMENT_ID), "
	            		+ "West_Department VARCHAR2(20), "
	            		+ "East_Department VARCHAR2(20), "
	            		+ "North_Department VARCHAR2(20), "
	            		+ "South_Department VARCHAR2(20))";
	            
	            String dependant= "CREATE TABLE dependant("
	            		+ "EMP_ID INT REFERENCES employee(EMP_ID), "
	            		+ "Birthdate DATE, "
	            		+ "StreetNumber INT, "
	            		+ "StreetName VARCHAR(20), "
	            		+ "PostalCode VARCHAR(20), "
	            		+ "City VARCHAR(20), "
	            		+ "Country VARCHAR(20), "
	            		+ "PRIMARY KEY(EMP_ID))";
	            
				try (Statement stmt = conn1.createStatement()) {

				stmt.executeQuery(payroll);
				JLabel q = new JLabel("Payroll Table Created......");
				panel.add(q);
				f.setVisible(true);
				stmt.executeQuery(payment);
				JLabel a = new JLabel("Payment Table Created......");
				q.setFont(new Font("Verdana",30,10));
				stmt.executeQuery(department);
				JLabel s = new JLabel("Department Table Created......");
				q.setFont(new Font("Verdana",30,10));
				stmt.executeQuery(login);
				JLabel d = new JLabel("Login Table Created......");
				q.setFont(new Font("Verdana",30,10));
				stmt.executeQuery(ll_employee);
				JLabel f = new JLabel("Lower level employee Table Created......");
				stmt.executeQuery(manager);
				JLabel g = new JLabel("Manager Table Created......");
				stmt.executeQuery(HR);
				JLabel h = new JLabel("HR Table Created......");
				stmt.executeQuery(employee);
				JLabel z = new JLabel("Employee Table Created......");
				stmt.executeQuery(parttime);
				JLabel x = new JLabel("Part time Table Created......");
				stmt.executeQuery(fulltime);
				JLabel c = new JLabel("Full time Table Created......");
				stmt.executeQuery(locations);
				JLabel v = new JLabel("Locations Table Created......");
				stmt.executeQuery(dependant);
				JLabel b = new JLabel("Dependant Table Created......");
				
				panel.add(q);
				panel.add(a);
				panel.add(s);
				panel.add(d);
				panel.add(f);
				panel.add(g);
				panel.add(h);
				panel.add(z);
				panel.add(x);
				panel.add(c);
				panel.add(v);
				panel.add(b);
				
        	    f.setVisible(true);

				
				} catch (SQLException q) {
					JOptionPane.showMessageDialog(null, "ERROR: " + q.getErrorCode() + " , Table already exists. Please drop tables first then create");
				
					f.setVisible(true);
					System.out.println("ERROR: " + q.getErrorCode() + " , Table already exists. Please drop tables first then create");
				}

	        } catch (ClassNotFoundException ex) {
	            ex.printStackTrace();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        } finally {
	            try {
	                if (conn1 != null && !conn1.isClosed()) {
	                    conn1.close();
	                }
	     
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	        }
			
		}
		else if (e.getSource()== c3p0) {
			Connection conn1 = null;

	        try {
	            // registers Oracle JDBC driver - though this is no longer required
	            // since JDBC 4.0, but added here for backward compatibility
	            Class.forName("oracle.jdbc.OracleDriver");
	 
	           
	            String dbURL1 = "jdbc:oracle:thin:amimtiaz/08184438@oracle.scs.ryerson.ca:1521:orcl";  // that is school Oracle database and you can only use it in the labs
																							
				conn1 = DriverManager.getConnection(dbURL1);
	            if (conn1 != null) {
	                System.out.println("Connected with connection #1");
	                JLabel connect = new JLabel("Connected to DBMS!");
	        	    connect.setFont(new Font("Verdana",30,50));
	        	    connect.setBounds(380, 795, 200, 150);
	        	    panel.add(connect);
	        	    f.setVisible(true);
	            }
				
				String dropLocations = "DROP TABLE LOCATIONS";
				String drop2 = "DROP TABLE fulltime";
				String drop3 = "DROP TABLE parttime";
				String drop4 = "DROP TABLE lower_level_employee";
				String drop5 = "DROP TABLE manager";
				String drop6 = "DROP TABLE HR";
				String drop7 = "DROP TABLE dependant";
				String drop8 = "DROP TABLE employee";
				String drop9 = "DROP TABLE payment";
				String drop10 = "DROP TABLE department";
				String drop11 = "DROP TABLE payroll";
				String drop12 = "DROP TABLE login";
								
				try (Statement stmt = conn1.createStatement()) {

					stmt.executeQuery(dropLocations);
					System.out.println("Locations table dropped!");
					JLabel q = new JLabel("Locations table dropped!");
					panel.add(q);
					f.setVisible(true);
					stmt.executeQuery(drop2);
					System.out.println("fulltime table dropped!");
					JLabel a = new JLabel("fulltime table dropped!");
					panel.add(a);
					f.setVisible(true);
					stmt.executeQuery(drop3);
					System.out.println("parttime table dropped!");
					JLabel s = new JLabel("parttime table dropped!");
					panel.add(s);
					f.setVisible(true);
					stmt.executeQuery(drop4);
					System.out.println("LL Employee table dropped!");
					JLabel d = new JLabel("LL Employee table dropped!");
					panel.add(d);
					f.setVisible(true);
					stmt.executeQuery(drop5);
					System.out.println("Manager table dropped!");
					JLabel n = new JLabel("Manager table dropped!");
					panel.add(n);
					f.setVisible(true);
					stmt.executeQuery(drop6);
					System.out.println("HR table dropped!");
					JLabel g = new JLabel("HR table dropped!");
					panel.add(g);
					f.setVisible(true);
					stmt.executeQuery(drop7);
					System.out.println("Dependant table dropped!");
					JLabel h = new JLabel("Dependant table dropped!");
					panel.add(h);
					f.setVisible(true);
					stmt.executeQuery(drop8);
					System.out.println("Employee table dropped!");
					JLabel z = new JLabel("Employee table dropped!");
					panel.add(z);
					f.setVisible(true);
					stmt.executeQuery(drop9);
					System.out.println("Payment table dropped!");
					JLabel x = new JLabel("Payment table dropped!");
					panel.add(x);
					f.setVisible(true);
					stmt.executeQuery(drop10);
					System.out.println("Department table dropped!");
					JLabel c = new JLabel("Department table dropped!");
					panel.add(c);
					f.setVisible(true);
					stmt.executeQuery(drop11);
					System.out.println("Payroll table dropped!");
					JLabel v = new JLabel("Payroll table dropped!");
					panel.add(v);
					f.setVisible(true);
					stmt.executeQuery(drop12);
					System.out.println("Login table dropped!");
					JLabel b = new JLabel("Login table dropped!");
					panel.add(b);
					f.setVisible(true);
					
					panel.add(q);
					f.setVisible(true);
					panel.add(a);
					f.setVisible(true);
					panel.add(s);
					f.setVisible(true);
					panel.add(d);
					f.setVisible(true);
					panel.add(n);
					f.setVisible(true);
					panel.add(g);
					f.setVisible(true);
					panel.add(h);
					f.setVisible(true);
					panel.add(z);
					f.setVisible(true);
					panel.add(x);
					f.setVisible(true);
					panel.add(c);
					f.setVisible(true);
					panel.add(v);
					f.setVisible(true);
					panel.add(b);
					f.setVisible(true);
	        	    f.setVisible(true);
				
				} catch (SQLException p) {
					JOptionPane.showMessageDialog(null, "ERROR: " + p.getErrorCode() + ", Tables do not exist, please create tables first");
					System.out.println("Error: " + p.getErrorCode() + ", Tables do not exist, please create tables first");
				}

	        } catch (ClassNotFoundException ex) {
	            ex.printStackTrace();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        } finally {
	            try {
	                if (conn1 != null && !conn1.isClosed()) {
	                    conn1.close();
	                }
	     
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	        }
			 
		}
		else if(e.getSource() == btninstruction) { // if the instruction button is clicker
			Connection conn1 = null;

	        try {
	            // registers Oracle JDBC driver - though this is no longer required
	            // since JDBC 4.0, but added here for backward compatibility
	            Class.forName("oracle.jdbc.OracleDriver");
	            String dbURL1 = "jdbc:oracle:thin:amimtiaz/08184438@oracle.scs.ryerson.ca:1521:orcl";  // that is school Oracle database and you can only use it in the labs
																							
	            // String dbURL1 = "jdbc:oracle:thin:amimtiaz/08184438@localhost:1521:xe";
				/* This XE or local database that you installed on your laptop. 1521 is the default port for database, change according to what you used during installation. 
				xe is the sid, change according to what you setup during installation. */
				
				conn1 = DriverManager.getConnection(dbURL1);
	            if (conn1 != null) {
	                System.out.println("Connected with connection #1");
	                JLabel connect = new JLabel("Connected to DBMS!");
	        	    connect.setFont(new Font("Verdana",30,50));
	        	    connect.setBounds(380, 795, 200, 150);
	        	    panel.add(connect);
	        	    f.setVisible(true);
	            }
				
				String i1 = "insert into payroll values (1, 5000000.69, 2000000.43, 'Direct Deposit', 'Payed On time')";
				String i2 = "insert into payment values (10, 1, 876.27, 87.3, 0, 0, 0)";
				String i3 = "insert into login values ('billybob@gmail.com', 'Billybob123', 'BillyBob', 'What was your first car?', '1992 Corolla')";
				String i4 = "insert into department values (15, 235, 'Software Department', 'Andrew Ng')";
				String i5 = "insert into employee values (111, 10, 15, 'billybob@gmail.com','Billy Bob',50000.00,TO_DATE('2021-08-05','YYYY-MM-DD'))";
				String i6 = "insert into dependant values (111,TO_DATE('2000-08-18','YYYY-MM-DD'), 62, 'Dundas', 'H7Z P0Q', 'Toronto', 'Canada')";
				String i7 = "insert into fulltime values(111, 4166.67)";
				String i8 = "insert into locations values(15, '32 York St', NULL, NULL, '12 Dundas St')";
				String i9 = "insert into payroll values (2, 5000000.69, 2000000.43, 'Certified Cheque', 'Payed On time')";
				String i10 = "insert into payment values (11, 2,1200.23, 100.00, 0, 200.00, 0)";
				String i11 = "insert into login values ('johndoe@gmail.com', '!Password274', 'JohnDoe', 'What was your first pet’s name?', 'Draco')";
				String i12 = "insert into department values (20, 235, 'Software Department', 'Andrew Ng')";
				String i13= "insert into employee values (130, 11, 20, 'johndoe@gmail.com','John Doe',100000.00,TO_DATE('2021-08-05','YYYY-MM-DD'))";
				String i14= "insert into dependant values (130, TO_DATE('1954-03-21','YYYY-MM-DD'), 126, 'Bayview', 'MK5 T3B', 'Toronto', 'Canada')";
				String i15= "insert into fulltime values(130, 8333.33)";
				String i16= "insert into locations values(20, '32 York St', NULL, NULL, '12 Dundas St')";
				String i17= "insert into payroll values (3, 5000000.69, 1000000.43, 'Direct Deposit', 'Payed On time')";
				String i18= "insert into payment values (13,3,1100.06, 0, 0, 0, 0)";
				String i19= "insert into login values ('robertsmith@gmail.com', 'Derozan4life', 'RobertSmith', 'In what city did your parents meet?', 'Paris')";
				String i20= "insert into department values (25, 180, 'Law Department', 'Robert Kardashian')";
				String i21= "insert into employee values (140,13, 25, 'robertsmith@gmail.com','Robert Smith',800000.00,TO_DATE('2021-08-05','YYYY-MM-DD'))";
				String i22= "insert into dependant values (140, TO_DATE('1972-04-20','YYYY-MM-DD'), 456, 'Kennedy', 'MK5 L6N', 'Toronto', 'Canada')";
				String i23= "insert into fulltime values(140, 6666.67)";
				String i24= "insert into locations values(25, '32 York St', NULL, NULL,NULL)";
				String i25= "insert into payroll values (6, 5000000.69, 500000.67, 'Direct Deposit', 'Payed On time')";
				String i26= "insert into payment values (15,6,1300.00, 35.55, 71.22, 735.60, 14.02)";
				String i27= "insert into login values ('tommylee@gmail.com', '!Sharklover47', 'TommyLee', 'Who did you take to prom?', 'Jessica')";
				String i28= "insert into department values (35, 12, 'HR Department', 'Victoria Willow')";
				String i29= "insert into employee values (160,15, 35, 'tommylee@gmail.com','TommyLee',470000.00,TO_DATE('2021-08-05','YYYY-MM-DD'))";
				String i30= "insert into dependant values (160, TO_DATE('1991-12-25','YYYY-MM-DD'), 61, 'Crowchild', 'T1Y 1A7', 'Calgary', 'Canada')";
				String i31= "insert into fulltime values(160, 3916.67)";
				String i32= "insert into locations values(35, NULL, NULL, NULL, '12 Dundas St')";
				String i33= "insert into payroll values (4, 5000000.69, 2000000.43, 'Cash', 'Payed  Late')";
				String i34= "insert into payment values (12, 4, 450.75, 20.25, 0, 0, 0)";
				String i35= "insert into login values ('kaylierodgers@gmail.com', 'kaylirodgers123', 'KayliRodgers', 'What city were you born in?', 'Toronto')";
				String i36= "insert into department values (10, 235, 'Cleaning Department', 'Rachel Davis')";
				String i37= "insert into employee values (120, 12, 10, 'kaylierodgers@gmail.com','kaylie',25000.00,TO_DATE('2021-08-05','YYYY-MM-DD'))";
				String i38= "insert into dependant values (120, TO_DATE('1984-06-20','YYYY-MM-DD'), 05, 'George Street', 'P3D P3W', 'Toronto', 'Canada')";
				String i39= "insert into parttime values(120, 12.82, 'Intern')";
				String i40= "insert into locations values(10, NULL,  '20 Eagle Ave', NULL, '50 King St')";
				String i41= "insert into payroll values (5, 5000000.69, 2000000.43, 'Certified Cheque', 'Payed On time')";
				String i42= "insert into payment values (14, 5, 3200.00,110.00, 300.00, 600.00, 10000.00)";
				String i43= "insert into login values ('stevenadams@gmail.com', 'Stevenadams123', 'StevenAdams', 'Favorite basketball team?', 'OKC Thunder')";
				String i44= "insert into department values (40, 300, 'Finance Department', 'Josh Albert')";
				String i45= "insert into employee values (150, 14, 40, 'stevenadams@gmail.com','Stevens Adams',120000.00, TO_DATE('2021-08-05','YYYY-MM-DD'))";
				String i46= "insert into dependant values (150, TO_DATE('1998-03-05','YYYY-MM-DD'), 09, 'Sky Drive', 'G9J 10Q', 'Vancouver', 'Canada')";
				String i47= "insert into fulltime values(150, 10000.00)";
				String i48= "insert into locations values(40,NULL, NULL, NULL, '12 Dundas St')";
				String i49= "insert into payroll values (7, 5000000.69, 2000000.43, 'Direct Deposit', 'Payed On time')";
				String i50= "insert into payment values (30, 7, 2200.00, 94.87, 0, 0, 0)";
				String i51= "insert into login values ('JakePaul@gmail.com', 'JakePaul123', 'JakePaul', 'Favorite high school teacher?', 'Mr. Josh')";
				String i52= "insert into department values (18, 235, 'Software Department', 'Andrew Ng')";
				String i53= "insert into employee values (141, 10, 18, 'JakePaul@gmail.com','Jake Paul',52800.00, TO_DATE('2021-08-05','YYYY-MM-DD'))";
				String i54=  "insert into dependant values (141, TO_DATE('2000-01-12','YYYY-MM-DD'), 32, 'Queen Street', 'J8F P0Q', 'Toronto', 'Canada')";
				String i55= "insert into fulltime values(141, 4400.00)";
				String i56= "insert into locations values(18, '32 York St', NULL, NULL, '12 Dundas St')";
				String i57= "insert into payroll values (9, 3000000.69, 4000000.43, 'Cash', 'Payed  Late')";
				String i58= "insert into payment values (33, 9, 450.75, 20.25, 0, 0, 0)";
				String i59= "insert into login values ('kayrodgers1@gmail.com', 'karodgers1234', 'KRodgers5', 'What city were you born in?', 'Toronto')";
				String i60= "insert into department values (99, 255, 'Cleaning Department', 'Rachel Davis')";
				String i61= "insert into employee values (121, 33, 99, 'kayrodgers1@gmail.com','kaylie',25000.00, TO_DATE('2021-08-05','YYYY-MM-DD'))";
				String i62= "insert into dependant values (121, TO_DATE('1984-06-20','YYYY-MM-DD'), 05, 'George Street', 'P3D P3W', 'Toronto', 'Canada')";
				String i63= "insert into parttime values(121, 12.82, 'Intern')";
				String i64= "insert into locations values(99, NULL,  '20 Eagle Ave', NULL, '12 Dundas St')";
								
				try (Statement stmt = conn1.createStatement()) {

					stmt.executeQuery(i1);
					stmt.executeQuery(i2);
					stmt.executeQuery(i3);
					stmt.executeQuery(i4);
					stmt.executeQuery(i5);
					stmt.executeQuery(i6);
					stmt.executeQuery(i7);
					stmt.executeQuery(i8);
					stmt.executeQuery(i9);
					stmt.executeQuery(i10);
					stmt.executeQuery(i11);
					stmt.executeQuery(i12);
					stmt.executeQuery(i13);
					stmt.executeQuery(i14);
					stmt.executeQuery(i15);
					stmt.executeQuery(i16);
					stmt.executeQuery(i17);
					stmt.executeQuery(i18);
					stmt.executeQuery(i19);
					stmt.executeQuery(i20);
					stmt.executeQuery(i21);
					stmt.executeQuery(i22);
					stmt.executeQuery(i23);
					stmt.executeQuery(i24);
					stmt.executeQuery(i25);
					stmt.executeQuery(i26);
					stmt.executeQuery(i27);
					stmt.executeQuery(i28);
					stmt.executeQuery(i29);
					stmt.executeQuery(i30);
					stmt.executeQuery(i31);
					stmt.executeQuery(i32);
					stmt.executeQuery(i33);
					stmt.executeQuery(i34);
					stmt.executeQuery(i35);
					stmt.executeQuery(i36);
					stmt.executeQuery(i37);
					stmt.executeQuery(i38);
					stmt.executeQuery(i39);
					stmt.executeQuery(i40);
					stmt.executeQuery(i41);
					stmt.executeQuery(i42);
					stmt.executeQuery(i43);
					stmt.executeQuery(i44);
					stmt.executeQuery(i45);
					stmt.executeQuery(i46);
					stmt.executeQuery(i47);
					stmt.executeQuery(i48);
					stmt.executeQuery(i49);
					stmt.executeQuery(i50);
					stmt.executeQuery(i51);
					stmt.executeQuery(i52);
					stmt.executeQuery(i53);
					stmt.executeQuery(i54);
					stmt.executeQuery(i55);
					stmt.executeQuery(i56);
					stmt.executeQuery(i57);
					stmt.executeQuery(i58);
					stmt.executeQuery(i59);
					stmt.executeQuery(i60);
					stmt.executeQuery(i61);
					stmt.executeQuery(i62);
					stmt.executeQuery(i63);
					stmt.executeQuery(i64);
					
					JLabel q = new JLabel("The database has been populated!");
					q.setFont(new Font("Verdana",30,50));
					panel.add(q);
					f.setVisible(true);
				
				//	for (int i = 14; i < 57; i++) {
					//	String x = "i"+i;
						//System.out.println(x);
						//stmt.executeQuery(x);	
					//}
				
				} catch (SQLException l) {
					JOptionPane.showMessageDialog(null, "Error: " + l.getErrorCode() + ", Already populated or tables do not exist.");
					System.out.println("Error: " + l.getErrorCode() + ", Already populated or tables do not exist.");
				}
				
	        } catch (ClassNotFoundException ex) {
	            ex.printStackTrace();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        } finally {
	            try {
	                if (conn1 != null && !conn1.isClosed()) {
	                    conn1.close();
	                }
	     
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	        }
		}
		else if (e.getSource()== btnQuery) {
			Connection conn1 = null;

	        try {
	            // registers Oracle JDBC driver - though this is no longer required
	            // since JDBC 4.0, but added here for backward compatibility
	            Class.forName("oracle.jdbc.OracleDriver");
	 
	           
	            String dbURL1 = "jdbc:oracle:thin:amimtiaz/08184438@oracle.scs.ryerson.ca:1521:orcl";  // that is school Oracle database and you can only use it in the labs
				
				conn1 = DriverManager.getConnection(dbURL1);
	            if (conn1 != null) {
	                System.out.println("Connected with connection #1");
	            }
				
				String query = "select E_NAME from EMPLOYEE";
								
				try (Statement stmt = conn1.createStatement()) {
				System.out.println("List all employee names");
				ResultSet rs = stmt.executeQuery(query);

				JLabel x = new JLabel("List all employee names");
				x.setFont(new Font("Verdana",30,30));
				panel.add(x);
				f.setVisible(true);
				
				while (rs.next()) {
					String name = rs.getString("E_NAME");
					System.out.println("Employee name: " + name + " ");
					JLabel h = new JLabel("<html>Employee name: " + name + " | <br/></html>");
					panel.add(h);
					f.setVisible(true);
				}
				
				} catch (SQLException l) {
					System.out.println(l.getErrorCode());
				}
				
				String query2 = "SELECT EMP_ID, E_name\r\n"
						+ "FROM employee \r\n"
						+ "WHERE EXISTS\r\n"
						+ "(SELECT DEP_NAME\r\n"
						+ "FROM department\r\n"
						+ "WHERE DEP_NAME = 'Software Department')";
				
				
				try (Statement stmt1 = conn1.createStatement()) {
					ResultSet rs2 = stmt1.executeQuery(query2);
					System.out.println("All employees who work in the software department:");
					JLabel x = new JLabel("All employees who work in the software department:");
					x.setFont(new Font("Verdana",30,30));
					panel.add(x);
					f.setVisible(true);
					while (rs2.next()) {
						int num = rs2.getInt("EMP_ID");
						String name = rs2.getString("E_name");
						System.out.println("Emp name: " + name + " | Emp ID: " + num);
						JLabel h = new JLabel("Emp name: " + name + " | Emp ID: " + num);
						panel.add(h);
						f.setVisible(true);
					}
				
				}
				
				catch (SQLException p) {
					System.out.println(p.getErrorCode());
				}
				
				String query3 = "SELECT  employee.E_name, employee.E_salary, dependant.city\r\n"
						+ "FROM   employee\r\n"
						+ "INNER JOIN dependant ON employee.EMP_ID=dependant.emp_id\r\n"
						+ "WHERE employee.E_salary > 100000\r\n"
						+ "ORDER BY employee.e_salary asc";
				System.out.println("Display the city of all the employee's who make over 100k");
				JLabel x = new JLabel("Display the city of all the employee's who make over 100k");
				x.setFont(new Font("Verdana",30,30));
				panel.add(x);
				f.setVisible(true);
				try (Statement stmt = conn1.createStatement()) {
					ResultSet rs3 = stmt.executeQuery(query3);
					while (rs3.next()) {
						String name = rs3.getString("E_name");
						int num = rs3.getInt("E_salary");
						String city = rs3.getString("city");
						System.out.println("Emp name: " + name + " | Salary: " + num + " | City: " +  city);
						JLabel h = new JLabel("Emp name: " + name + " | Salary: " + num + " | City: " +  city);
						panel.add(h);
						f.setVisible(true);
					} 
					
				}
				catch (SQLException m) {
					System.out.println(m.getErrorCode());
				}
				
	        } catch (ClassNotFoundException ex) {
	            ex.printStackTrace();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        } finally {
	            try {
	                if (conn1 != null && !conn1.isClosed()) {
	                    conn1.close();
	                }
	     
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	        }
			
		}

		else if (e.getSource() == btnexit) { // if the exit button is clicked
			System.exit(0); // exit
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GUI o = new GUI (); // running program
	}
}
