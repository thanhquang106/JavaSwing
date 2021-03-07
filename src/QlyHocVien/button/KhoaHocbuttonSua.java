package QlyHocVien.button;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import QlyHocVien.Connect.ConnectDB;
import QlyHocVien.view.HocVienJFrame;
import QlyHocVien.view.KhoaHocJFrame;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

public class KhoaHocbuttonSua extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private String Ten_khoahoc, Mota;
	private Date Ngaybatdau,Ngayketthuc;
	/**
	 * Launch the application.
	 */
	public KhoaHocbuttonSua(int id) throws SQLException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 565, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Connection conn = ConnectDB.getConnection();
		Statement stm;
		ResultSet rs;

		String sql_select = "SELECT * FROM khoa_hoc where idKhoa_hoc = " +id;
		PreparedStatement prst = conn.prepareStatement(sql_select);
		System.out.println(sql_select);
		rs = prst.executeQuery();
		while (rs.next())
		{
		    Ten_khoahoc = rs.getString(2);
		    Mota = rs.getString(3);
		    Ngaybatdau = rs.getDate(4);
		    Ngayketthuc = rs.getDate(5);
		    
		}
		
		
		
		JLabel lblNewLabel = new JLabel("Tên khóa học :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel.setBounds(92, 88, 89, 33);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField(Ten_khoahoc);
		textField.setBounds(170, 96, 277, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Mô tả :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(82, 131, 89, 33);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Ngày bắt đầu :");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(82, 174, 99, 33);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("ngày kết thúc :");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel_2_1.setBounds(55, 217, 126, 33);
		contentPane.add(lblNewLabel_2_1);
		
		textField_1 = new JTextField(Mota.toString());
		textField_1.setColumns(10);
		textField_1.setBounds(170, 139, 277, 19);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField(Ngaybatdau.toString());
		textField_2.setColumns(10);
		textField_2.setBounds(170, 182, 277, 19);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField(Ngayketthuc.toString());
		textField_3.setColumns(10);
		textField_3.setBounds(170, 225, 277, 19);
		contentPane.add(textField_3);
		
		
		JButton btnNewButton = new JButton("Sửa");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql = "update khoa_hoc set Ten_khoa_hoc = ? , Mo_Ta = ?, Ngay_bat_dau = ?, Ngay_ket_thuc = ? where idKhoa_hoc = ?";
					System.out.println(sql);
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, textField.getText());
					ps.setString(2, textField_1.getText());
					ps.setString(3, textField_2.getText());
					ps.setString(4, textField_3.getText());
					ps.setInt(5, id);
					System.out.println("id "+id);
					ps.execute();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setVisible(false);
				KhoaHocJFrame fr = new KhoaHocJFrame();
				fr.setVisible(true);
			}
		});
		btnNewButton.setBackground(Color.PINK);
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		btnNewButton.setBounds(238, 313, 126, 33);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("SỬA HỌC VIÊN");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblNewLabel_3.setBounds(191, 35, 215, 33);
		contentPane.add(lblNewLabel_3);
	}

	
}