package models.fasilitas;

import global.base.BaseModel;

public class FasilitasModel extends BaseModel {
	private final String NamaFasilitas;
	private final String KeteranganFasilitas;
	private final Timestamp TanggalDibuat;
	private final Timestamp TanggalDiubah;

	public FasilitasModel(String NamaFasilitas, String KeteranganFasilitas) {

		validate(NamaFasilitas, KeteranganFasilitas);

		this.NamaFasilitas = NamaFasilitas;
		this.KeteranganFasilitas = KeteranganFasilitas;
	}

	public FasilitasModel(int id, String NamaFasilitas, String KeteranganFasilitas, Timestamp TanggalDibuat, Timestamp TanggalDiubah) {
		super(id);

		validate(id, NamaFasilitas, KeteranganFasilitas, TanggalDibuat, TanggalDiubah);

		this.NamaFasilitas = NamaFasilitas;
		this.KeteranganFasilitas = KeteranganFasilitas;
		this.TanggalDibuat = TanggalDibuat;
		this.TanggalDiubah = TanggalDiubah;
	}

	private void validate(String NamaFasilitas, String KeteranganFasilitas) {
		if (NamaFasilitas == null ||  NamaFasilitas.trim().isEmpty()) { 
			throw new IllegalArgumentException("NamaFasilitas cannot be empty");
		}

		if (KeteranganFasilitas == null || KeteranganFasilitas.trim()isEmpty()) {
			throw new IllegalArgumentException("KeteranganFasilitas cannot be empty");
		}
	}

	public String get.NamaFasilitas() {
		return this.NamaFasilitas;
	}

	public String get.KeteranganFasilitas() {
		return this.KeteranganFasilitas;
	}

	public Timestamp get.TanggalDibuat() {
		return this.TanggalDibuat;
	}

	public Timestamp get.TanggalDiubah() {
		return this.TanggalDiubah;
	}

	@Override
	public String toString() {
		return "FasilitasModel("
				+ "id=" + this.id() + ", "
				+ "NamaFasilitas=" + this.NamaFasilitas + ", "
				+ "KeteranganFasilitas=" + this.KeteranganFasilitas
				+ ")";
	}
}