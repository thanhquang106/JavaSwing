package QlyHocVien.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import QlyHocVien.Connect.ConnectDB;
import QlyHocVien.button.HocVienbuttonSua;
import QlyHocVien.button.HocVienbuttonThem;
import QlyHocVien.model.HocVien;

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

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class HocVienJFrame extends JFrame {

	Object[] row = new Object[6];
	private JPanel contentPane;
	private JTable table;
	private JLabel lblNewLabel;
	private DefaultTableModel dtm1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HocVienJFrame frame = new HocVienJFrame();
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
	public HocVienJFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 870, 566);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(166, 83, 560, 353);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Họ tên", "Ngày sinh", "Giới tính", "Số điện thoại", "Địa chỉ" }));
		scrollPane.setViewportView(table);
		showStudent();
		lblNewLabel = new JLabel("QUẢN LÝ HỌC VIÊN\r\n");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel.setBounds(236, 10, 404, 50);
		contentPane.add(lblNewLabel);
		JButton btnThem = new JButton("Th\u00EAm");
		btnThem.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HocVienbuttonThem fr = new HocVienbuttonThem();
				fr.setVisible(true);
				setVisible(false);
			}
		});
		btnThem.setBounds(315, 443, 96, 35);
		contentPane.add(btnThem);

		JButton btnSua = new JButton("S\u1EEDa");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = (int) table.getValueAt(table.getSelectedRow(), 0);
				HocVienbuttonSua fr;
				try {
					fr = new HocVienbuttonSua(selectedRow);
					fr.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setVisible(false);
			}
		});
		btnSua.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnSua.setBounds(486, 443, 96, 35);
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
					ps = conn.prepareStatement("Delete From hoc_vien where idHoc_vien = " + selectedRow);
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
						new String[] { "ID", "Họ tên", "Ngày sinh", "Giới tính", "Số điện thoại", "Địa chỉ" }));
				scrollPane.setViewportView(table);
				showStudent();
			}
		});
		btnXoa.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnXoa.setBounds(630, 443, 96, 35);
		contentPane.add(btnXoa);

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

	public ArrayList<HocVien> hocvienlist() {
		ArrayList<HocVien> hocvienlist = new ArrayList<>();
		Connection conn = ConnectDB.getConnection();
		try {
			String sql = "Select * from hoc_vien";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			HocVien hocvien;
			while (rs.next()) {
				hocvien = new HocVien(rs.getInt("idHoc_vien"), rs.getString("Ho_ten"),rs.getDate("Ngay_sinh") , rs.getString("Gioi_tinh"),
			 rs.getString("SDT"),rs.getString("Dia_chi"));
				hocvienlist.add(hocvien);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hocvienlist;
	}

	public void showStudent() {
		ArrayList<HocVien> list = hocvienlist();
		dtm1 = (DefaultTableModel) table.getModel();

		for (int i = 0; i < list.size(); i++) {
			row[0] = list.get(i).getMa_hoc_vien();
			row[1] = list.get(i).getHo_ten();
			row[2] = list.get(i).getNgay_sinh();
			row[3] = list.get(i).getGioi_tinh();
			row[4] = list.get(i).getSo_dien_thoai();
			row[5] = list.get(i).getDia_chi();

			dtm1.addRow(row);
		}
	}
}
