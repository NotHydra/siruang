package models.ruangan;


import java.sql.Timestamp;

import models.fasilitas.FasilitasModel;



public class RuanganDetailedModel extends RuanganModel {
	private final FasilitasModel[] fasilitas 	;

	public RuanganDetailedModel(int id, String nama, String deskripsi, int kapasitas, FasilitasModel[] fasilitas, Timestamp dibuat, Timestamp diubah) {
			super(id, nama, deskripsi, kapasitas, dibuat, diubah);

			this.fasilitas = fasilitas;

	}

	public FasilitasModel[] getfasilitas() {
			return this.fasilitas;

	}

	@Override
	public String toString() {
		return "RuanganDetailedModel("
				+ "id=" + this.getId() + ", "
				+ "nama=" + this.getNama() + ", "
				+ "deskripsi=" + this.getDeskripsi() + ", "
				+ "fasilitas=" + this.getfasilitas() + ", "
				+ "dibuat=" + this.getDibuat() + ", "
				+ "diubah=" + this.getDiubah()
				+ ")";
	}
}
