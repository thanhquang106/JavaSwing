package QlyHocVien.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainJFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainJFrame frame = new MainJFrame();
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
	public MainJFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 831, 525);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("QU\u1EA2N L\u00DD H\u1ECCC VI\u00CAN");
		lblNewLabel.setBounds(5, 5, 807, 35);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		contentPane.add(lblNewLabel);

		JButton lblNewLabel_1 = new JButton("QU\u1EA2N L\u00DD H\u1ECCC VI\u00CAN");
		lblNewLabel_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				HocVienJFrame frame = new HocVienJFrame();
				setVisible(false);
				frame.setVisible(true);

			}
		});
		lblNewLabel_1.setBounds(214, 222, 199, 53);
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		contentPane.add(lblNewLabel_1);

		JButton lblNewLabel_2 = new JButton("QU\u1EA2N L\u00DD KH\u00D3A H\u1ECCC");
		lblNewLabel_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KhoaHocJFrame KHFram = new KhoaHocJFrame();
				setVisible(false);
				KHFram.setVisible(true);
			}
		});
		lblNewLabel_2.setBounds(494, 219, 199, 53);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		contentPane.add(lblNewLabel_2);

		

	}
}
