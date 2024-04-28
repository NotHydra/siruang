package models.peminjaman;

import models.ruangan.RuanganModel;

import java.security.Timestamp;
import java.time.LocalDateTime;

import enums.StatusEnum;
import models.pengguna.PenggunaModel;

public class PeminjamanDetailModel {
	private final PenggunaModel pengguna;
	private final RuanganModel ruangan;

	public PeminjamanDetailModel(int id, int idRuangan, RuanganModel ruangan , PenggunaModel pengguna, String namaPeminjam, LocalDateTime waktuAwal, LocalDateTime waktuAkhir, String keterangan, StatusEnum status, Timestamp dibuat){
		super(id, idRuangan, namaPeminjam, waktuAwal, waktuAkhir, keterangan, status, dibuat );
	
		this.pengguna = pengguna;
		this.ruangan = ruangan;
	}

	public RuanganModel getRuangan(){
		return this.ruangan;
	}

	public PenggunaModel getPengguna(){
		return this.pengguna;
	}

	@Override
	public String toString(){
		return "PeminjamanDetailModel("
		+ "id=" + this.getId() + ","
		+ "idRuangan=" + this.getIdRuangan() + ","
		+ "ruangan=" + this.getRuangan() + ","
		+ "namaPeminjaman=" + this.getNamaPeminjam() + ","
		+ "waktuAwal=" + this.getWaktuAwal() + ","
		+ "waktuAkhir=" + this.getWaktuAkhir() + ","
		+ "keterangan=" + this.getKeterangan() + ","
		+ "status=" + this.getStatus() + ","
		+ "dibuat=" + this.getDibuat() + ","
		+ ")"	;
		
	}

	
}
