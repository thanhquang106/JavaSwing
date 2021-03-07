package QlyHocVien.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import QlyHocVien.Connect.ConnectDB;
import QlyHocVien.button.HocVienbuttonSua;
import QlyHocVien.button.HocVienbuttonThem;
import QlyHocVien.button.KhoaHocbuttonSua;
import QlyHocVien.button.KhoahocbuttonThem;
import QlyHocVien.model.HocVien;
import QlyHocVien.model.KhoaHoc;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class KhoaHocJFrame extends JFrame {
	Object[] row = new Object[5];
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KhoaHocJFrame frame = new KhoaHocJFrame();
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
	public KhoaHocJFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 895, 539);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(224, 74, 476, 311);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Tên khóa học", "Mô tả", "ngày bắt đầu", "Ngày kết thúc" }));
		scrollPane.setViewportView(table);
		showStudent();
		JLabel lblNewLabel = new JLabel("QUẢN LÝ  KHÓA HỌC");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(316, 10, 288, 65);
		contentPane.add(lblNewLabel);
		JButton btnThem = new JButton("Th\u00EAm");
		btnThem.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KhoahocbuttonThem fr = new KhoahocbuttonThem();
				fr.setVisible(true);
				setVisible(false);
			}
		});
		btnThem.setBounds(355, 443, 96, 35);
		contentPane.add(btnThem);

		JButton btnSua = new JButton("S\u1EEDa");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = (int) table.getValueAt(table.getSelectedRow(), 0);
				KhoaHocbuttonSua fr;
				try {
					fr = new KhoaHocbuttonSua(selectedRow);
					fr.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setVisible(false);

			}
		});
		btnSua.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnSua.setBounds(479, 443, 96, 35);
		contentPane.add(btnSua);

		JButton btnXoa = new JButton("X\u00F3a");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = (int) table.getValueAt(table.getSelectedRow(), 0);
				System.out.println("select row " + selectedRow);
				int ret = JOptionPane.showConfirmDialog(null, "Do you want delete?");
				if (ret != JOptionPane.YES_OPTION) {
					return;
				}
				PreparedStatement ps = null;
				Connection conn = null;
				String url = "jdbc:mysql://localhost:3306/nvduy";
				try {
					conn = DriverManager.getConnection(url, "root", null);
					ps = conn.prepareStatement("Delete From khoa_hoc where idKhoa_hoc = " + selectedRow);
					ret = ps.executeUpdate();
					if (ret != -1) {
						JOptionPane.showMessageDialog(btnXoa, this, "this member has been remove ", ret);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					try {
						if (conn != null) {
							conn.close();
						}
						if (ps != null) {
							ps.close();
						}
					} catch (Exception ex2) {
						ex2.printStackTrace();
					}
				}
				table = new JTable();
				setEnabled(false);
				table.setFont(new Font("Times New Roman", Font.PLAIN, 10));
				table.setModel(new DefaultTableModel(new Object[][] {},
						new String[] { "ID", "Tên khóa học", "Mô tả", "Ngày bắt đầu", "Ngày kết thúc" }));
				scrollPane.setViewportView(table);
				showStudent();
			}
		});
		btnXoa.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnXoa.setBounds(630, 443, 96, 35);
		contentPane.add(btnXoa);
		btnXoa.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnXoa.setBounds(604, 443, 96, 35);
		
		
		JButton btnQuay = new JButton("Quay lại");
		btnQuay.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnQuay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainJFrame frame = new MainJFrame();
				setVisible(false);
				frame.setVisible(true);

			}
		});
		btnQuay.setBounds(166, 443, 96, 35);
		contentPane.add(btnQuay);
	}

	public ArrayList<KhoaHoc> khoahoclist() {
		ArrayList<KhoaHoc> khoahoclist = new ArrayList<>();
		Connection conn = ConnectDB.getConnection();
		try {
			String sql = "Select * from khoa_hoc";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			KhoaHoc khoahoc;
			while (rs.next()) {
				khoahoc = new KhoaHoc(rs.getInt("idKhoa_hoc"), rs.getString("Ten_khoa_hoc"), rs.getString("Mo_ta"),
						rs.getDate("Ngay_bat_dau"), rs.getDate("Ngay_ket_thuc"));

				khoahoclist.add(khoahoc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return khoahoclist;
	}

	public void showStudent() {
		ArrayList<KhoaHoc> list = khoahoclist();
		DefaultTableModel dtm1 = (DefaultTableModel) table.getModel();

		for (int i = 0; i < list.size(); i++) {
			row[0] = list.get(i).getMa_khoa_hoc();
			row[1] = list.get(i).getTen_khoa_hoc();
			row[2] = list.get(i).getMo_ta();
			row[3] = list.get(i).getNgay_bat_dau();
			row[4] = list.get(i).getNgay_ket_thuc();

			dtm1.addRow(row);
		}
	}

}
