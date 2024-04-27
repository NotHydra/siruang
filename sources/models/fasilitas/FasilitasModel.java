package models.fasilitas;

import global.base.BaseModel;
import java.sql.Timestamp;

public class FasilitasModel extends BaseModel {
	private final String namaFasilitas;
	private final String keteranganFasilitas;
	private final Timestamp tanggalDibuat;
	private final Timestamp tanggalDiubah;

	public FasilitasModel(String namaFasilitas, String keteranganFasilitas) {

		validate(namaFasilitas, keteranganFasilitas);

		this.namaFasilitas = namaFasilitas;
		this.keteranganFasilitas = keteranganFasilitas;
	}

	public FasilitasModel(int id, String namaFasilitas, String keteranganFasilitas, Timestamp tanggalDibuat, Timestamp tanggalDiubah) {
		super(id);

		validate(id, namaFasilitas, keteranganFasilitas, tanggalDibuat, tanggalDiubah);

		this.namaFasilitas = namaFasilitas;
		this.keteranganFasilitas = keteranganFasilitas;
		this.tanggalDibuat = tanggalDibuat;
		this.tanggalDiubah = tanggalDiubah;
	}

	private void validate(String namaFasilitas, String keteranganFasilitas) {
		if (namaFasilitas == null ||  namaFasilitas.trim().isEmpty()) { 
			throw new IllegalArgumentException("Nama tidak boleh kosong");
		}

		if (keteranganFasilitas == null || keteranganFasilitas.trim()isEmpty()) {
			throw new IllegalArgumentException("Keterangan tidak boleh kosong");
		}
	}

	public String get.namaFasilitas() {
		return this.namaFasilitas;
	}

	public String get.keteranganFasilitas() {
		return this.keteranganFasilitas;
	}

	public Timestamp get.tanggalDibuat() {
		return this.tanggalDibuat;
	}

	public Timestamp get.tanggalDiubah() {
		return this.tanggalDiubah;
	}

	@Override
	public String toString() {
		return "FasilitasModel("
				+ "id=" + this.id() + ", "
				+ "namaFasilitas=" + this.namaFasilitas + ", "
				+ "keteranganFasilitas=" + this.keteranganFasilitas
				+ ")";
	}
}