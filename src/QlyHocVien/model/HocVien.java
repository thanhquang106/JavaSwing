package QlyHocVien.model;

import java.io.Serializable;
import java.sql.Date;

public class HocVien implements Serializable {
	private int ma_hoc_vien;
	private String ho_ten;
	private String so_dien_thoai;
	private String dia_chi;
	private Date Ngay_sinh;
	private String gioi_tinh;

	public HocVien(int ma_hoc_vien, String ho_ten, Date ngay_sinh, String gioi_tinh, String so_dien_thoai, String dia_chi) {
		super();
		this.ma_hoc_vien = ma_hoc_vien;
		this.ho_ten = ho_ten;
		this.so_dien_thoai = so_dien_thoai;
		this.dia_chi = dia_chi;
		Ngay_sinh = ngay_sinh;
		this.gioi_tinh = gioi_tinh;
		
	}


	public int getMa_hoc_vien() {
		return ma_hoc_vien;
	}

	public void setMa_hoc_vien(int ma_hoc_vien) {
		this.ma_hoc_vien = ma_hoc_vien;
	}

	public String getHo_ten() {
		return ho_ten;
	}

	public void setHo_ten(String ho_ten) {
		this.ho_ten = ho_ten;
	}

	public String getSo_dien_thoai() {
		return so_dien_thoai;
	}

	public void setSo_dien_thoai(String so_dien_thoai) {
		this.so_dien_thoai = so_dien_thoai;
	}

	public String getDia_chi() {
		return dia_chi;
	}

	public void setDia_chi(String dia_chi) {
		this.dia_chi = dia_chi;
	}

	public Date getNgay_sinh() {
		return Ngay_sinh;
	}

	public void setNgay_sinh(Date ngay_sinh) {
		Ngay_sinh = ngay_sinh;
	}


	public String getGioi_tinh() {
		return gioi_tinh;
	}


	public void setGioi_tinh(String gioi_tinh) {
		this.gioi_tinh = gioi_tinh;
	}

	
}
