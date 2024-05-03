package models.ruangan;


import java.sql.Timestamp;

import models.fasilitas.FasilitasModel;

<<<<<<< HEAD


public class RuanganDetailedModel extends RuanganModel {
	private final FasilitasModel[] fasilitas 	;

	public RuanganDetailedModel(int id, String nama, String deskripsi, int kapasitas, FasilitasModel[] fasilitas, Timestamp dibuat, Timestamp diubah) {
			super(id, nama, deskripsi, kapasitas, dibuat, diubah);

			this.fasilitas = fasilitas;

	}

	public FasilitasModel[] getfasilitas() {
			return this.fasilitas;
=======
public class RuanganDetailedModel extends RuanganModel {
	private final FasilitasModel[] fasilitas;

	public RuanganDetailedModel(int id, String nama, String deskripsi, int kapasitas, FasilitasModel[] fasilitas, Timestamp dibuat, Timestamp diubah) {
		super(id, nama, deskripsi, kapasitas, dibuat, diubah);

		this.fasilitas = fasilitas;
	}

	public FasilitasModel[] getfasilitas() {
		return this.fasilitas;
>>>>>>> db838e3b20a6e8e8092145de5f5b81dbe20f89b0

	}

	@Override
	public String toString() {
		return "RuanganDetailedModel("
				+ "id=" + this.getId() + ", "
				+ "nama=" + this.getNama() + ", "
				+ "deskripsi=" + this.getDeskripsi() + ", "
<<<<<<< HEAD
=======
				+ "kapasitas=" + this.getKapasitas() + ", "
>>>>>>> db838e3b20a6e8e8092145de5f5b81dbe20f89b0
				+ "fasilitas=" + this.getfasilitas() + ", "
				+ "dibuat=" + this.getDibuat() + ", "
				+ "diubah=" + this.getDiubah()
				+ ")";
	}
}
