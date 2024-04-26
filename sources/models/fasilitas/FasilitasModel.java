package models.fasilitas;

import global.base.BaseModel;
import camelcase.CamelCase;
import java.sql.Timestamp;


public class FasilitasModel extends BaseModel {
	private final String Nama;
	private final String Keterangan;
	private final Timestamp TanggalDibuat;
	private final Timestamp TanggalDiubah;

	public FasilitasModel(String Nama, String Keterangan) {
		super(-1);

		validate(Nama, Keterangan);

		this.Nama = Nama;
		this.Keterangan = Keterangan;
	}

	public FasilitasModel(int id, String Nama, String Keterangan, Timestamp TanggalDibuat, Timestamp TanggalDiubah) {
		super(id);

		validate(id, Nama, Keterangan, TanggalDibuat, TanggalDiubah);

		this.Nama = Nama;
		this.Keterangan = Keterangan;
		this.TanggalDibuat = TanggalDibuat;
		this.TanggalDiubah = TanggalDiubah;
	}

	private void validate(String Nama, String Keterangan) {
		if (Nama == null ||  Nama.trim().isEmpty()) { 
			throw new IllegalArgumentException("Nama cannot be empty");
		}

		if (Keterangan == null || Keterangan.trim()isEmpty()) {
			throw new IllegalArgumentException("Keterangan cannot be empty");
		}
	}

	public String getNama() {
		return this.Nama;
	}

	public String getKeterangan() {
		return this.Keterangan;
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
           	 	", Keterangan='" + Keterangan + '\'' +
           	 	'}';
	}
}