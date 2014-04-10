package funksjonTesting;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.sql.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class Ufullstendig extends Mats implements ActionListener{
	JFrame frame3, frame4;
	JTextField txtSearchU;
	JLabel labelU;
	JButton btnSearch1;
	JPanel panel, SearchContentU;
	
	
	static JTable table;
	
	String driverName = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://sql4.freemysqlhosting.net/sql435045";
	String userName = "sql435045";
	String password = "mG2%nR5!";
	String[] column = {"eik", "ros", "einer", "bjørk", "furu"};
	

	public void createUI()
	{
		JFrame frame = new JFrame("Database søk resultat");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		txtSearchU = new JTextField();
		txtSearchU.setBounds(310,250,150,20); 
		labelU = new JLabel("Søk etter værdata");
		labelU.setBounds(330, 140, 200, 200);
		btnSearch1 = new JButton("Søk");
		btnSearch1.setBounds(340,290, 80,20);
		btnSearch1.addActionListener(this);

		frame.add(txtSearchU);
		frame.add(labelU);
		frame.add(btnSearch1);
		frame.setVisible(true);
		frame.setSize(800, 600);
		frame.setLocation(500, 200);
		
		
	}	
	
	public void actionPerformed(ActionEvent ae)
	{
		btnSearch1 = (JButton)ae.getSource();
		System.out.println("Viser tabelldata");
			showTableData();			
	}	
	
	public void showTableData()
	{
		
		frame4 = new JFrame("Database Search Result");
		frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame4.setLayout(new BorderLayout());		
		//TableModel tm = new TableModel();
		DefaultTableModel model = new DefaultTableModel();
		//DefaultTableModel model = new DefaultTableModel(tm.getData1(), tm.getColumnVIDs());		
		//table = new JTable(model);
		table = new JTable();
		table.setModel(model);		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFillsViewportHeight(true);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setHorizontalScrollBarPolicy(
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);		
		String textvalue = txtSearchU.getText();
		String eik= "";
		
	
		try
		{			
			Class.forName(driverName);		
			Connection con = DriverManager.getConnection(url, userName, password);
			String sql = "select * from beiteregistrering where eik = "+textvalue;
			PreparedStatement ps = con.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	        int i =0;
			if(rs.next())
	        {
				eik = rs.getString("eik");
			
									
				model.addRow(new Object[]{eik});
				i++;				
	        }
			if(i <1)
			{
				JOptionPane.showMessageDialog(null, "Ingen treff","Error",
						JOptionPane.ERROR_MESSAGE);
			}
			if(i ==1)
			{
			System.out.println(i+" treff funnet");
			}
			else
			{
				System.out.println(i+" treff funnet");
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",
					JOptionPane.ERROR_MESSAGE);
		}
		frame4.add(scroll);
		frame4.setVisible(true);
		frame4.setSize(400,300);
		frame4.setLocation(500,280);
	        setResizable(false);
	    
	}
	
	public static void main(String args[])
	{
		SearchResult sr = new SearchResult();
		        sr.createUI();	

		     
		        
	}
}