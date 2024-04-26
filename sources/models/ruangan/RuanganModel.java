package models.ruangan;

import java.sql.Timestamp;
import global.base.BaseModel;

public class RuanganModel {
	private final String Nama;
	private final String Deskirpsi;
	private final int Kapasitas;
	private final Timestamp TanggalDibuat;
	private final Timestamp TanggalDiubah;
	
	public RuanganModel(String Nama, String Deskirpsi, int Kapasitas) {
		super(-1);
		validate(Nama, Deskirpsi, Kapasitas);
		
		this.Nama = Nama;
		this.Deskirpsi = Deskirpsi;
		this.Kapasitas = Kapasitas;
		this.TanggalDibuat = null;
		this.TanggalDiubah = null;
	}
	
	public RuanganModel(int id, String Nama, String Deskirpsi,int Kapasitas,  Timestamp TanggalDibuat, Timestamp TanggalDiubah) {
		super(id);

		validate(id, Nama, Deskirpsi, Kapasitas, TanggalDibuat, TanggalDiubah);

		this.Nama = Nama;
		this.Deskirpsi = Deskirpsi;
		this.TanggalDibuat = TanggalDibuat;
		this.TanggalDiubah = TanggalDiubah;
	}


	private void validate(String Nama, String Deskirpsi, int Kapasitas) {
		if (Nama == null ||  Nama.trim().isEmpty()) { 
			throw new IllegalArgumentException("Nama Ruangan cannot be empty");
		}
		if (Deskirpsi == null ||  Deskirpsi.trim().isEmpty()) { 
			throw new IllegalArgumentException("Deskripsi Ruangan cannot be empty");
		}
		if (Kapasitas == 0 ||  Kapasitas == 0) { 
			throw new IllegalArgumentException("Kapasitas Ruangan cannot be empty");
		}
		
	}

	public String getnama() {
		return this.Nama;
	}
	public String getDeskripsi() {
		return this.Deskirpsi;
	}
	public int getKapasitas() {
		return this.Kapasitas;
	}
	public Timestamp getTanggalDibuat() {
		return this.TanggalDibuat;
	}

	public Timestamp getTanggalDiubah() {
		return this.TanggalDiubah;
	}

	@Override
	public String toString() {
    	return "FasilitasModel{" +
            	"id=" + id() +
            	", Nama='" + Nama + '\'' +
           	 	", Deskripsi='" + Deskirpsi + '\'' +
           	 	", Kapasitas='" + Kapasitas + '\'' +
           	 	'}';
	}

}

