package models.peminjaman;


import java.sql.ResultSet;

import interfaces.ServiceAddInterface;
import interfaces.ServiceFindDetailedInterface;
import interfaces.ServiceFindInterface;

import enums.LevelEnum;
// import enums.StatusEnum;

import providers.Logger;
import providers.Database;

import global.detailed.DetailedService;

import models.pengguna.PenggunaModel;
import models.ruangan.RuanganModel;

public class PeminjamanService
		extends DetailedService<PeminjamanModel, PeminjamanDetailedModel>
		implements ServiceFindInterface<PeminjamanModel>, ServiceFindDetailedInterface<PeminjamanDetailedModel>, ServiceAddInterface<PeminjamanModel> {
	private static PeminjamanService instance;

	private PeminjamanService(Logger logger, Database database, String table) {
		super(logger, database, table);
	}

	public static PeminjamanService getInstance() {
		if (PeminjamanService.instance == null) {
			try {
				PeminjamanService.instance = new PeminjamanService(
						new Logger(PeminjamanService.class.getName()),
						Database.getInstance(),
						"peminjaman");
			}
			catch (Exception e) {
				PeminjamanService.instance.logger.error("Failed to initialize PeminjamanService instance: " + e.getMessage());

				throw new RuntimeException("Failed to initialize PeminjamanService instance");
			}
		}

		PeminjamanService.instance.logger.debug("Get Instance");

		return PeminjamanService.instance;
	}

	@Override
	public PeminjamanModel[] find() {
		this.logger.debug("Find");

		try {
			final int total = this.database.tableTotal(this.table);
			final ResultSet result = this.database.executeQuery(""
					+ "SELECT "
					+ "id, "
					+ "id_ruangan, "
					+ "id_pengguna, "
					+ "nama_peminjam, "
					+ "waktu_mulai, "
					+ "waktu_selesai, "
					+ "keterangan, "
					// + "status, "
					+ "dibuat "
					+ "FROM " + this.table
					+ ";");

			final PeminjamanModel[] models = new PeminjamanModel[total];

			int i = 0;
			while (result.next()) {
				models[i] = new PeminjamanModel(
						result.getInt("id"),
						result.getInt("id_ruangan"),
						result.getInt("id_pengguna"),
						result.getString("nama_peminjam"),
						result.getTimestamp("waktu_mulai").toLocalDateTime(),
						result.getTimestamp("waktu_selesai").toLocalDateTime(),
						result.getString("keterangan"),
						// StatusEnum.valueToEnum(result.getString("status")),
						result.getTimestamp("dibuat"));

				i++;
			}

			return models;
		}
		catch (Exception e) {
			this.logger.error("Failed to find: " + e.getMessage());
		}

		return null;
	}

	@Override
	public PeminjamanModel findId(int id) {
		this.logger.debug("Find Id");

		try {
			final ResultSet result = this.database.executeQuery(""
					+ "SELECT "
					+ "id, "
					+ "id_ruangan, "
					+ "id_pengguna, "
					+ "nama_peminjam, "
					+ "waktu_mulai, "
					+ "waktu_selesai, "
					+ "keterangan, "
					// + "status, "
					+ "dibuat "
					+ "FROM " + this.table + " "
					+ "WHERE id=" + id
					+ ";");

			if (result.next()) {
				return new PeminjamanModel(
						result.getInt("id"),
						result.getInt("id_ruangan"),
						result.getInt("id_pengguna"),
						result.getString("nama_peminjam"),
						result.getTimestamp("waktu_mulai").toLocalDateTime(),
						result.getTimestamp("waktu_selesai").toLocalDateTime(),
						result.getString("keterangan"),
						// StatusEnum.valueToEnum(result.getString("status")),
						result.getTimestamp("dibuat"));
			}
		}
		catch (Exception e) {
			this.logger.error("Failed to find id: " + e.getMessage());
		}

		return null;
	}

	@Override
	public PeminjamanDetailedModel[] findDetailed() {
		this.logger.debug("Find Detailed");

		try {
			final int total = this.database.tableTotal(this.table);
			final ResultSet result = this.database.executeQuery(""
					+ "SELECT "
					+ "peminjaman.id, "
					+ "peminjaman.id_ruangan, "
					+ "peminjaman.id_pengguna, "
					+ "peminjaman.nama_peminjam, "
					+ "peminjaman.waktu_mulai, "
					+ "peminjaman.waktu_selesai, "
					+ "peminjaman.keterangan, "
					// + "peminjaman.status, "
					+ "peminjaman.dibuat, "
					+ "ruangan.id, "
					+ "ruangan.nama, "
					+ "ruangan.deskripsi, "
					+ "ruangan.kapasitas, "
					+ "ruangan.dibuat, "
					+ "ruangan.diubah, "
					+ "pengguna.id, "
					+ "pengguna.nama, "
					+ "pengguna.username, "
					+ "pengguna.password, "
					+ "pengguna.aktif, "
					+ "pengguna.level, "
					+ "pengguna.dibuat, "
					+ "pengguna.diubah "
					+ "FROM peminjaman "
					+ "INNER JOIN ruangan ON peminjaman.id_ruangan=ruangan.id "
					+ "INNER JOIN pengguna ON peminjaman.id_pengguna=pengguna.id"
					+ ";");

			final PeminjamanDetailedModel[] models = new PeminjamanDetailedModel[total];

			int i = 0;
			while (result.next()) {
				models[i] = new PeminjamanDetailedModel(
						result.getInt("peminjaman.id"),
						result.getInt("peminjaman.id_ruangan"),
						result.getInt("peminjaman.id_pengguna"),
						new RuanganModel(
								result.getInt("ruangan.id"),
								result.getString("ruangan.nama"),
								result.getString("ruangan.deskripsi"),
								result.getInt("ruangan.kapasitas"),
								result.getTimestamp("ruangan.dibuat"),
								result.getTimestamp("ruangan.diubah")),
						new PenggunaModel(
								result.getInt("pengguna.id"),
								result.getString("pengguna.nama"),
								result.getString("pengguna.username"),
								result.getString("pengguna.password"),
								result.getBoolean("pengguna.aktif"),
								LevelEnum.valueToEnum(result.getString("pengguna.level")),
								result.getTimestamp("pengguna.dibuat"),
								result.getTimestamp("pengguna.diubah")),
						result.getString("peminjaman.nama_peminjam"),
						result.getTimestamp("peminjaman.waktu_mulai").toLocalDateTime(),
						result.getTimestamp("peminjaman.waktu_selesai").toLocalDateTime(),
						result.getString("peminjaman.keterangan"),
						// StatusEnum.valueToEnum(result.getString("peminjaman.status")),
						result.getTimestamp("peminjaman.dibuat"));

				i++;
			}

			return models;
		}
		catch (Exception e) {
			this.logger.error("Failed to find detailed: " + e.getMessage());
		}

		return null;
	}

	@Override
	public PeminjamanDetailedModel findIdDetailed(int id) {
		this.logger.debug("Find Id Detailed");

		try {
			final ResultSet result = this.database.executeQuery(""
					+ "SELECT "
					+ "peminjaman.id, "
					+ "peminjaman.id_ruangan, "
					+ "peminjaman.id_pengguna, "
					+ "peminjaman.nama_peminjam, "
					+ "peminjaman.waktu_mulai, "
					+ "peminjaman.waktu_selesai, "
					+ "peminjaman.keterangan, "
					// + "peminjaman.status, "
					+ "peminjaman.dibuat, "
					+ "ruangan.id, "
					+ "ruangan.nama, "
					+ "ruangan.deskripsi, "
					+ "ruangan.kapasitas, "
					+ "ruangan.dibuat, "
					+ "ruangan.diubah, "
					+ "pengguna.id, "
					+ "pengguna.nama, "
					+ "pengguna.username, "
					+ "pengguna.password, "
					+ "pengguna.aktif, "
					+ "pengguna.level, "
					+ "pengguna.dibuat, "
					+ "pengguna.diubah "
					+ "FROM peminjaman "
					+ "INNER JOIN ruangan ON peminjaman.id_ruangan=ruangan.id "
					+ "INNER JOIN pengguna ON peminjaman.id_pengguna=pengguna.id "
					+ "WHERE peminjaman.id=" + id
					+ ";");

			if (result.next()) {
				return new PeminjamanDetailedModel(
						result.getInt("peminjaman.id"),
						result.getInt("peminjaman.id_ruangan"),
						result.getInt("peminjaman.id_pengguna"),
						new RuanganModel(
								result.getInt("ruangan.id"),
								result.getString("ruangan.nama"),
								result.getString("ruangan.deskripsi"),
								result.getInt("ruangan.kapasitas"),
								result.getTimestamp("ruangan.dibuat"),
								result.getTimestamp("ruangan.diubah")),
						new PenggunaModel(
								result.getInt("pengguna.id"),
								result.getString("pengguna.nama"),
								result.getString("pengguna.username"),
								result.getString("pengguna.password"),
								result.getBoolean("pengguna.aktif"),
								LevelEnum.valueToEnum(result.getString("pengguna.level")),
								result.getTimestamp("pengguna.dibuat"),
								result.getTimestamp("pengguna.diubah")),
						result.getString("peminjaman.nama_peminjam"),
						result.getTimestamp("peminjaman.waktu_mulai").toLocalDateTime(),
						result.getTimestamp("peminjaman.waktu_selesai").toLocalDateTime(),
						result.getString("peminjaman.keterangan"),
						// StatusEnum.valueToEnum(result.getString("peminjaman.status")),
						result.getTimestamp("peminjaman.dibuat"));
			}
		}
		catch (Exception e) {
			this.logger.error("Failed to find id detailed: " + e.getMessage());
		}

		return null;
	}

	@Override
	public void add(PeminjamanModel model) {
		this.logger.debug("Add");

		try {
			this.database.executeUpdate(""
					+ "INSERT INTO " + this.table + " ("
					+ "id_ruangan, "
					+ "id_pengguna, "
					+ "nama_peminjam, "
					+ "waktu_mulai, "
					+ "waktu_selesai, "
					+ "keterangan"
					// + "status"
					+ ") VALUES ("
					+ "'" + model.getIdRuangan() + "', "
					+ "'" + model.getIdPengguna() + "', "
					+ "'" + model.getNamaPeminjam() + "', "
					+ "'" + model.getWaktuMulai() + "', "
					+ "'" + model.getWaktuSelesai() + "', "
					+ "'" + model.getKeterangan() + "'"
					// + "'" + model.getStatus().value + "'"
					+ ");");
		}
		catch (Exception e) {
			this.logger.error("Failed to add: " + e.getMessage());
		}
	}
}
