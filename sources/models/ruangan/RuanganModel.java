package models.ruangan;


import java.sql.Timestamp;

import global.base.BaseModel;

public class RuanganModel extends BaseModel {
	private final String nama;
	private final String deskripsi;
	private final int kapasitas;
	private final Timestamp dibuat;
	private final Timestamp diubah;

	public RuanganModel(String nama, String deskripsi, int kapasitas) {
		super(-1);

		validate(nama, deskripsi, kapasitas);

		this.nama = nama;
		this.deskripsi = deskripsi;
		this.kapasitas = kapasitas;
		this.dibuat = null;
		this.diubah = null;
	}

	public RuanganModel(int id, String nama, String deskripsi, int kapasitas, Timestamp dibuat, Timestamp diubah) {
		super(id);

		validate(nama, deskripsi, kapasitas);

		this.nama = nama;
		this.deskripsi = deskripsi;
		this.kapasitas = kapasitas;
		this.dibuat = dibuat;
		this.diubah = diubah;
	}

	private void validate(String nama, String deskripsi, int kapasitas) {
		if (nama == null || nama.trim().isEmpty()) {
			throw new IllegalArgumentException("Nama tidak boleh kosong");
		}

		if (deskripsi == null || deskripsi.trim().isEmpty()) {
			throw new IllegalArgumentException("Deskripsi tidak boleh kosong");
		}

		if (kapasitas <= 0) {
			throw new IllegalArgumentException("Kapasitas harus lebih dari 0");
		}

	}

	public String getNama() {
		return this.nama;
	}

	public String getDeskripsi() {
		return this.deskripsi;
	}

	public int getKapasitas() {
		return this.kapasitas;
	}

	public Timestamp getDibuat() {
		return this.dibuat;
	}

	public Timestamp getDiubah() {
		return this.diubah;
	}

	@Override
	public String toString() {
		return "RuanganModel("
				+ "id=" + this.id + ", "
				+ "nama=" + this.nama + ", "
				+ "deskripsi=" + this.deskripsi + ", "
				+ "kapasitas=" + this.kapasitas + ", "
				+ "dibuat=" + this.dibuat + ", "
				+ "diubah=" + this.diubah
				+ ")";
	}

}
