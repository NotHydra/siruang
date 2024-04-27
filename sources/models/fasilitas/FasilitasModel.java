package models;

import global.base.BaseModel;
import java.sql.Timestamp;

public class Model extends BaseModel {

	private final String nama;
	private final String keterangan;
	private final Timestamp dibuat;
	private final Timestamp diubah;

	public Model(String nama, String keterangan) {
		super(-1);

		validate(nama, keterangan);

		this.nama = nama;
		this.keterangan = keterangan;
		this.dibuat = null;
		this.diubah = null;

	}

	public Model(int id, String nama, String keterangan) {
		super(id);

		validate(id, nama, keterangan, dibuat, diubah);

		this.nama = nama;
		this.keterangan = keterangan;
		this.dibuat = dibuat;
		this.diubah = diubah;
	}

	private void validate(String nama, String keterangan) {
		if (nama == null ||  nama.trim().isEmpty()) { 
			throw new IllegalArgumentException("Nama tidak boleh kosong");
		}

		if (keterangan == null || keterangan.trim()isEmpty()) {
			throw new IllegalArgumentException("Keterangan tidak boleh kosong");
		}
	}

	public String getnama() {
		return this.nama;
	}

	public String getketerangan() {
		return this.keterangan;
	}

	public Timestamp getdibuat() {
		return this.dibuat;
	}

	public Timestamp getdiubah() {
		return this.diubah;
	}

	@Override
	public String toString() {
		return "Model("
				+ "id=" + id + ", "
				+ "nama=" + nama + ", "
				+ "keterangan=" + keterangan
				+ ")";
	}
}