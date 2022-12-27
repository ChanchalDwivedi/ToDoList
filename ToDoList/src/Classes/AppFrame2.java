package Classes;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.sql.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

public class AppFrame2 extends JFrame {

	private JPanel contentPane;
	private JTextField taskname;
	private JLabel lblNewLabel_5;
	private static final long serialVersionUID = 2L;
	private JButton btnNewButton_1_1;
	private JButton btnNewButton_1_2;
	private JLabel lblNewLabel_1_2;
	private JLabel lblNewLabel_2;
	private FileInputStream fileInputStream;
	private String sound, title, hourAlarm, minuteAlarm, hour, minute, second;
	private Player player;
	long all;
	private int h, m, Ah,Am;
	private BufferedInputStream bufferedinputStream;
	private JLabel lblNewLabel_3;
	private JLabel alarmtime;
	private JComboBox comboBox_3;
	private JComboBox comboBox_4;
	ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("todo.jpeg"));
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppFrame2 frame = new AppFrame2();
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
	public AppFrame2() {
		setIconImage(icon.getImage());
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 449, 640);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 128));
		contentPane.setToolTipText("");
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("TASK");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(55, 104, 104, 37);
		contentPane.add(lblNewLabel);

		taskname = new JTextField();
		taskname.setBounds(153, 104, 216, 44);
		contentPane.add(taskname);
		taskname.setColumns(10);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnCancel.setBackground(new Color(255, 255, 255));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AppFrame af1 = new AppFrame();
				af1.show();
				dispose();
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCancel.setBounds(277, 513, 138, 58);
		contentPane.add(btnCancel);

		JLabel lblNewLabel_4 = new JLabel("ADD TASK");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel_4.setBounds(119, 6, 210, 48);
		contentPane.add(lblNewLabel_4);

		JPanel panel = new JPanel();
		panel.setForeground(new Color(255, 255, 255));
		panel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "ALARM", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel.setBackground(new Color(0, 128, 128));
		panel.setBounds(10, 175, 415, 298);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Timer");
		lblNewLabel_1.setBounds(21, 113, 104, 37);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 25));

		JLabel lblNewLabel_1_1 = new JLabel("HH");
		lblNewLabel_1_1.setBounds(148, 64, 104, 37);
		panel.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 25));

		lblNewLabel_1_2 = new JLabel("MM");
		lblNewLabel_1_2.setBounds(229, 64, 104, 37);
		panel.add(lblNewLabel_1_2);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 25));

		JButton btnRing = new JButton("Ring");
		btnRing.setBackground(new Color(255, 255, 255));
		btnRing.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnRing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choosesong();
				if (!sound.equals(null)) {				
					lblNewLabel_2.setText("alarm music");

				}
			}
		});
		btnRing.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnRing.setBounds(21, 240, 104, 47);
		panel.add(btnRing);

		btnNewButton_1_1 = new JButton("listen");
		btnNewButton_1_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnNewButton_1_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnNewButton_1_1.getText().equals("listen")) {
					start_alarm();
					btnNewButton_1_1.setText("stop");
				} else if (btnNewButton_1_1.getText().equals("stop")) {
					stopalarm();
					btnNewButton_1_1.setText("listen");

				}
			}
		});

		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_1_1.setBounds(149, 240, 104, 47);
		panel.add(btnNewButton_1_1);

		btnNewButton_1_2 = new JButton("Set");
		btnNewButton_1_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnNewButton_1_2.setBackground(new Color(255, 255, 255));
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				hourAlarm = comboBox_3.getSelectedItem().toString();
				minuteAlarm = comboBox_4.getSelectedItem().toString();
				alarmtime.setText(hourAlarm + ":" + minuteAlarm);
				h = Integer.parseInt(hourAlarm);
				m = Integer.parseInt(minuteAlarm);
				if (lblNewLabel_2.getText() != "alarm music") {
					JOptionPane.showMessageDialog(null, "you dont have choose alarm music...", "Warning",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					setalarm();
				}
			}
		});
		btnNewButton_1_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_1_2.setBounds(278, 240, 104, 47);
		panel.add(btnNewButton_1_2);

		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(92, 182, 212, 31);
		panel.add(lblNewLabel_2);

		alarmtime = new JLabel(" ");
		alarmtime.setFont(new Font("Tahoma", Font.PLAIN, 20));
		alarmtime.setBackground(new Color(240, 240, 240));
		alarmtime.setBounds(184, 11, 166, 31);
		panel.add(alarmtime);

		comboBox_3 = new JComboBox();
		comboBox_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		comboBox_3.setBounds(135, 107, 65, 61);
		panel.add(comboBox_3);

		comboBox_4 = new JComboBox();
		comboBox_4.setFont(new Font("Tahoma", Font.BOLD, 20));
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"00", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		comboBox_4.setBounds(239, 107, 65, 61);
		panel.add(comboBox_4);
		
		JLabel lblNewLabel_6_1 = new JLabel("Alarm Time:");
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_6_1.setBounds(21, 12, 123, 28);
		panel.add(lblNewLabel_6_1);

		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(55, 582, 141, 21);
		contentPane.add(lblNewLabel_3);

		lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setBounds(139, 65, 244, 28);
		contentPane.add(lblNewLabel_5);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 22));
		
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String task_name, time_alarm;
				task_name = taskname.getText();
				time_alarm = alarmtime.getText();
				if(task_name.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Not Entered Task");
				}
				else if(time_alarm.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Didn't Set Alarm");
				}
				else
				{
					try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost/task", "root", "");
					Statement st = con.createStatement();
					st.executeUpdate("insert into manage(TASK,TIME ) values('"+task_name+"','"+time_alarm+"')");
					JOptionPane.showMessageDialog(null, "task added.....");
				}
				
				catch (ClassNotFoundException e2) {
				System.out.println("driver not loaded");
					e2.printStackTrace();
				}
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("driver not loaded");
				}
					AppFrame af1 = new AppFrame();
					af1.show();
					dispose();
					
				}
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(55, 513, 138, 58);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_6 = new JLabel("TIME :");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_6.setBounds(55, 65, 80, 28);
		contentPane.add(lblNewLabel_6);

		current();
	};;

	public void setalarm() {
		Thread t = new Thread() {
			public void run() {
				while (true) {
					if(h == Ah &&m== Am)
					{
						
						start_alarm();
						JOptionPane.showMessageDialog(null, "Alarm");
						JOptionPane.showConfirmDialog(null, "Stop Alarm","",JOptionPane.CLOSED_OPTION);
						stopalarm();
						break;
					}
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		};
		t.setPriority(Thread.MIN_PRIORITY);
		t.start();
	}

	public void stopalarm() {
		// TODO Auto-generated method stub
		if (player != null) {
			player.close();

		}
	}

	public void choosesong() {
		// TODO Auto-generated method stub
		JFileChooser jfc = new JFileChooser();
		int soundINT = jfc.showOpenDialog(null);
		if (soundINT == JFileChooser.APPROVE_OPTION) {
			File alarmmusic = jfc.getSelectedFile();
			sound = alarmmusic.getAbsolutePath();
			title = jfc.getSelectedFile().getName();
		} else if (soundINT == JFileChooser.CANCEL_OPTION)

		{
			JOptionPane.showMessageDialog(null, "please choose alarm music");
		}
	}

	public void start_alarm() {
		try {
			fileInputStream = new FileInputStream(sound);
			bufferedinputStream = new BufferedInputStream(fileInputStream);
			player = new Player(bufferedinputStream);
			all = fileInputStream.available();
			new Thread() {
				public void run() {
					try {
						player.play();
					} catch (JavaLayerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}.start();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void current() {
		Thread clock = new Thread() {
			public void run() {
				while (true) {
					Calendar calendar = new GregorianCalendar();
					second = Integer.toString(calendar.get(Calendar.SECOND));
					minute = Integer.toString(calendar.get(Calendar.MINUTE));
					hour = Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
					Ah = Integer.parseInt(hour);
					Am = Integer.parseInt(minute);
					lblNewLabel_5.setText(hour + " : " + minute+ " : " + second);

					try {
						sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		};
		clock.setPriority(Thread.MIN_PRIORITY);
		clock.start();
	}
}
