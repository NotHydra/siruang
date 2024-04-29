package models.peminjaman;


import java.sql.Timestamp;
import java.time.LocalDateTime;

import enums.StatusEnum;

import models.pengguna.PenggunaModel;
import models.ruangan.RuanganModel;

public class PeminjamanDetailModel extends PeminjamanModel {
	private final RuanganModel ruangan;
	private final PenggunaModel pengguna;

	public PeminjamanDetailModel(int id, int idRuangan, int idPengguna, RuanganModel ruangan, PenggunaModel pengguna, String namaPeminjam, LocalDateTime waktuMulai, LocalDateTime waktuSelesai, String keterangan, StatusEnum status, Timestamp dibuat) {
		super(id, idRuangan, idPengguna, namaPeminjam, waktuMulai, waktuSelesai, keterangan, status, dibuat);

		this.pengguna = pengguna;
		this.ruangan = ruangan;
	}

	public RuanganModel getRuangan() {
		return this.ruangan;
	}

	public PenggunaModel getPengguna() {
		return this.pengguna;
	}

	@Override
	public String toString() {
		return "PeminjamanDetailModel("
				+ "id=" + this.getId() + ", "
				+ "idRuangan=" + this.getIdRuangan() + ", "
				+ "idPengguna=" + this.getIdPengguna() + ", "
				+ "ruangan=" + this.ruangan + ", "
				+ "pengguna=" + this.pengguna + ", "
				+ "namaPeminjam=" + this.getNamaPeminjam() + ", "
				+ "waktuMulai=" + this.getWaktuMulai() + ", "
				+ "waktuSelesai=" + this.getWaktuSelesai() + ", "
				+ "keterangan=" + this.getKeterangan() + ", "
				+ "status=" + this.getStatus() + ", "
				+ "dibuat=" + this.getDibuat()
				+ ")";
	}
}
