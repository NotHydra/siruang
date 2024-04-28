package models.ruangan;


import java.security.Timestamp;
import models.fasilitas.FasilitasModel;



public class RuanganDetailedModel {
	private final FasilitasModel[] fasilitas 	;

	public RuanganDetailedModel(int id, String nama, String deskripsi, int kapasitas, FasilitasModel[] fasilitas, Timestamp dibuat, Timestamp diubah) {
			super(id, nama, deskripsi, kapasitas);

			this.fasilitas = fasilitas;

	}

	public FasilitasModel[] getfasilitas() {
			return this.fasilitas;

	}

	@Override
	public String toString() {
		return "RuanganDetailedModel("
				+ "id=" + this.getid() + ", "
				+ "nama=" + this.getnama() + ", "
				+ "keterangan=" + this.getketerangan()
				+ ")";
	}
}
