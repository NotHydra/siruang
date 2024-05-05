package models.peminjaman;


import java.sql.Timestamp;
import java.time.LocalDateTime;

// import enums.StatusEnum;

import global.base.BaseModel;

public class PeminjamanModel extends BaseModel {
	private final int idRuangan;
	private final int idPengguna;
	private final String namaPeminjam;
	private final LocalDateTime waktuMulai;
	private final LocalDateTime waktuSelesai;
	private final String keterangan;
	// private final StatusEnum status;
	private final Timestamp dibuat;

	// public PeminjamanModel(int idRuangan, int idPengguna, String namaPeminjam,
	// LocalDateTime waktuMulai, LocalDateTime waktuSelesai, String keterangan,
	// StatusEnum status) {
	public PeminjamanModel(int idRuangan, int idPengguna, String namaPeminjam, LocalDateTime waktuMulai, LocalDateTime waktuSelesai, String keterangan) {
		super(-1);

		validate(idRuangan, idPengguna, namaPeminjam, waktuMulai, waktuSelesai, keterangan);

		this.idRuangan = idRuangan;
		this.idPengguna = idPengguna;
		this.namaPeminjam = namaPeminjam;
		this.waktuMulai = waktuMulai;
		this.waktuSelesai = waktuSelesai;
		this.keterangan = keterangan;
		// this.status = status;
		this.dibuat = null;
	}

	// public PeminjamanModel(int id, int idRuangan, int idPengguna, String
	// namaPeminjam, LocalDateTime waktuMulai, LocalDateTime waktuSelesai, String
	// keterangan, StatusEnum status, Timestamp dibuat) {
	public PeminjamanModel(int id, int idRuangan, int idPengguna, String namaPeminjam, LocalDateTime waktuMulai, LocalDateTime waktuSelesai, String keterangan, Timestamp dibuat) {

		super(id);

		validate(idRuangan, idPengguna, namaPeminjam, waktuMulai, waktuSelesai, keterangan);

		this.idRuangan = idRuangan;
		this.idPengguna = idPengguna;
		this.namaPeminjam = namaPeminjam;
		this.waktuMulai = waktuMulai;
		this.waktuSelesai = waktuSelesai;
		this.keterangan = keterangan;
		// this.status = status;
		this.dibuat = dibuat;
	}

	private void validate(int idRuangan, int idPengguna, String namaPeminjam, LocalDateTime waktuMulai, LocalDateTime waktuSelesai, String keterangan) {
		if (idRuangan <= 0) {
			throw new IllegalArgumentException("Id ruangan harus lebih dari 0");
		}

		if (idPengguna <= 0) {
			throw new IllegalArgumentException("Id pengguna harus lebih dari 0");
		}

		if (namaPeminjam == null || namaPeminjam.trim().isEmpty()) {
			throw new IllegalArgumentException("Nama peminjam tidak boleh kosong");
		}

		if (waktuMulai.isEqual(waktuSelesai)) {
			throw new IllegalArgumentException("Waktu mulai tidak boleh sama dengan waktu selesai");
		}

		if (waktuMulai.isAfter(waktuSelesai)) {
			throw new IllegalArgumentException("Waktu mulai harus sebelum waktu selesai");
		}

		if (keterangan == null || keterangan.trim().isEmpty()) {
			throw new IllegalArgumentException("Keterangan tidak boleh kosong");
		}
	}

	public int getIdRuangan() {
		return this.idRuangan;
	}

	public int getIdPengguna() {
		return this.idPengguna;
	}

	public String getNamaPeminjam() {
		return this.namaPeminjam;
	}

	public LocalDateTime getWaktuMulai() {
		return this.waktuMulai;
	}

	public LocalDateTime getWaktuSelesai() {
		return this.waktuSelesai;
	}

	public String getKeterangan() {
		return this.keterangan;
	}

	// public StatusEnum getStatus() {
	// return this.status;
	// }

	public Timestamp getDibuat() {
		return this.dibuat;
	}

	@Override
	public String toString() {
		return "PeminjamanModel("
				+ "id=" + this.id + ", "
				+ "idRuangan=" + this.idRuangan + ", "
				+ "idPengguna=" + this.idPengguna + ", "
				+ "namaPeminjam=" + this.namaPeminjam + ", "
				+ "waktuMulai=" + this.waktuMulai + ", "
				+ "waktuSelesai=" + this.waktuSelesai + ", "
				+ "keterangan=" + this.keterangan + ", "
				// + "status=" + this.status + ", "
				+ "dibuat=" + this.dibuat
				+ ")";
	}
}
