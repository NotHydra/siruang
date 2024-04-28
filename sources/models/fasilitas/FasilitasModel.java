package models.fasilitas;


import java.sql.Timestamp;

import global.base.BaseModel;

public class FasilitasModel extends BaseModel {
	private final String nama;
	private final String keterangan;
	private final Timestamp dibuat;
	private final Timestamp diubah;

	public FasilitasModel(String nama, String keterangan) {
		super(-1);

		validate(nama, keterangan);

		this.nama = nama;
		this.keterangan = keterangan;
		this.dibuat = null;
		this.diubah = null;

	}

	public FasilitasModel(int id, String nama, String keterangan, Timestamp dibuat, Timestamp diubah) {
		super(id);

		validate(nama, keterangan);

		this.nama = nama;
		this.keterangan = keterangan;
		this.dibuat = dibuat;
		this.diubah = diubah;
	}

	private void validate(String nama, String keterangan) {
		if (nama == null || nama.trim().isEmpty()) {
			throw new IllegalArgumentException("Nama tidak boleh kosong");
		}

		if (keterangan == null || keterangan.trim().isEmpty()) {
			throw new IllegalArgumentException("Keterangan tidak boleh kosong");
		}
	}

	public String getNama() {
		return this.nama;
	}

	public String getKeterangan() {
		return this.keterangan;
	}

	public Timestamp getDibuat() {
		return this.dibuat;
	}

	public Timestamp getDiubah() {
		return this.diubah;
	}

	@Override
	public String toString() {
		return "FasilitasModel("
				+ "id=" + id + ", "
				+ "nama=" + nama + ", "
				+ "keterangan=" + keterangan
				+ ")";
	}
}