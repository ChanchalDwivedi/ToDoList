package Classes;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

public class AppFrame extends JFrame {
	

	private static final long serialVersionUID = 1L;
	public static final String timeLabel = null;
	private JPanel contentPane;
	private JTable table;
	public DefaultTableModel mode;
	ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("todo.jpeg"));
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppFrame frame = new AppFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AppFrame() {
		setIconImage(icon.getImage());
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 483, 640);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 128));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setAutoscrolls(true);
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		scrollPane.setBounds(49, 166, 383, 337);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"SRNO", "TASK", "TIME", "STATUS"
			}
		));
		table.getColumnModel().getColumn(3).setResizable(false);
		mode = (DefaultTableModel)table.getModel();
table.setDefaultRenderer(Object.class, new colorRen());
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 128));
		panel.setBorder(new CompoundBorder());
		panel.setBounds(60, 25, 345, 77);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("To Do List");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 37));
		lblNewLabel.setBounds(79, 11, 217, 44);
		panel.add(lblNewLabel);

		JButton btnNewButton = new JButton("ADD");
		btnNewButton.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AppFrame2 af2 = new AppFrame2();
				af2.show();
			}
		});
		btnNewButton.setBounds(24, 536, 99, 44);
		contentPane.add(btnNewButton);

		JButton btnDelete = new JButton("DELETE");
		btnDelete.setBackground(new Color(255, 255, 255));
		btnDelete.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selected = table.getSelectedRow();
				try {
					int id = Integer.parseInt(mode.getValueAt(selected, 0).toString());
					int dialogResult = JOptionPane.showConfirmDialog(null,"do you want to delete task", "warning", JOptionPane.YES_NO_OPTION);
					if(dialogResult == JOptionPane.YES_OPTION)
					{
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost/task", "root", "");
					Statement st = con.createStatement();
					st.executeUpdate("DELETE FROM `manage` WHERE NO = '"+id+"'");
					ResultSet rs = st.executeQuery("select * from manage");
					JOptionPane.showMessageDialog(null, "task deleted.....");
					table.setModel(DbUtils.resultSetToTableModel(rs));
					}
				}

				catch (ClassNotFoundException e2) {
				System.out.println("driver not loaded");
					e2.printStackTrace();
				}
				catch (SQLException e1) {
					e1.printStackTrace();
					System.out.println("driver not loaded");
				}
				
			}
		});
	
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnDelete.setBounds(130, 536, 93, 44);
		contentPane.add(btnDelete);

		JButton btnDone = new JButton("DONE");
		btnDone.setBackground(new Color(255, 255, 255));
		btnDone.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selected = table.getSelectedRow();
				try {
					int id = Integer.parseInt(mode.getValueAt(selected, 0).toString());
					int dialogResult = JOptionPane.showConfirmDialog(null,"done task??", "warning", JOptionPane.YES_NO_OPTION);
					if(dialogResult == JOptionPane.YES_OPTION)
					{
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost/task", "root", "");
					Statement st = con.createStatement();
					st.executeUpdate("update manage set status='1' where NO= '"+id+"'");
					ResultSet rs = st.executeQuery("select * from manage");
					JOptionPane.showMessageDialog(null, "task done....");
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					}
				}

				catch (ClassNotFoundException e2) {
				System.out.println("driver not loaded");
					e2.printStackTrace();
				}
				catch (SQLException e1) {
					e1.printStackTrace();
					System.out.println("driver not loaded");
				}
			}
		});
		btnDone.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnDone.setBounds(248, 536, 86, 44);
		contentPane.add(btnDone);
		
		JButton btnProgress = new JButton("Progress");
		btnProgress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selected = table.getSelectedRow();
				try {
					int id = Integer.parseInt(mode.getValueAt(selected, 0).toString());
					int dialogResult = JOptionPane.showConfirmDialog(null,"work in progress", "warning", JOptionPane.YES_NO_OPTION);
					if(dialogResult == JOptionPane.YES_OPTION)
					{
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost/task", "root", "");
					Statement st = con.createStatement();
					st.executeUpdate("update manage set status='2' where NO= '"+id+"'");
					ResultSet rs = st.executeQuery("select * from manage");
					JOptionPane.showMessageDialog(null, "task in progress....");
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					}
				}

				catch (ClassNotFoundException e2) {
				System.out.println("driver not loaded");
					e2.printStackTrace();
				}
				catch (SQLException e1) {
					e1.printStackTrace();
					System.out.println("driver not loaded");
				}
			}
		});
		btnProgress.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnProgress.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		btnProgress.setBackground(Color.WHITE);
		btnProgress.setBounds(344, 536, 115, 44);
		contentPane.add(btnProgress);

	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/task", "root", "");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from manage");
		while(rs.next()) {
			mode.addRow(new Object[] {rs.getInt(1),rs.getString(2),rs.getTime(3),rs.getInt(4)});
			
			}
	}
	
	catch (ClassNotFoundException e2) {
	System.out.println("driver not loaded");
		e2.printStackTrace();
	}
	catch (SQLException e1) {
		e1.printStackTrace();
		System.out.println("driver not loaded");
	}

}
}
