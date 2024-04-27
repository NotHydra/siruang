package models.ruangan;

import java.sql.Timestamp;
import global.base.BaseModel;

public class RuanganModel {
	private final String nama;
	private final String deskirpsi;
	private final int kapasitas;
	private final Timestamp dibuat;
	private final Timestamp diubah;
	
	public RuanganModel(String nama, String deskirpsi, int kapasitas) {
		super(-1);
		validate(nama, deskirpsi, kapasitas);
		
		this.nama = nama;
		this.deskirpsi = deskirpsi;
		this.kapasitas = kapasitas;
		this.dibuat = null;
		this.diubah = null;
	}
	
	public RuanganModel(int id, String nama, String deskirpsi,int kapasitas,  Timestamp dibuat, Timestamp diubah) {
		super(id);

		validate(id, nama, deskirpsi, kapasitas, dibuat, diubah);

		this.nama = nama;
		this.deskirpsi = deskirpsi;
		this.dibuat = dibuat;
		this.diubah = diubah;
	}


	private void validate(String nama, String deskirpsi, int kapasitas) {
		if (nama == null ||  nama.trim().isEmpty()) { 
			throw new IllegalArgumentException("Nama tidak boleh kosong");
		}
		if (deskirpsi == null ||  deskirpsi.trim().isEmpty()) { 
			throw new IllegalArgumentException("Deskripsi tidak boleh kosong");
		}
		if (kapasitas == 0 ||  kapasitas == 0) { 
			throw new IllegalArgumentException("Kapasitas tidak boleh kosong");
		}
		
	}

	public String getnama() {
		return this.nama;
	}
	public String getDeskripsi() {
		return this.deskirpsi;
	}
	public int getKapasitas() {
		return this.kapasitas;
	}
	public Timestamp getTanggalDibuat() {
		return this.dibuat;
	}

	public Timestamp getTanggalDiubah() {
		return this.diubah;
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

