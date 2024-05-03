package models.ruangan;


import java.sql.ResultSet;

import interfaces.ServiceFindInterface;
import interfaces.ServiceFindDetailedInterface;
import interfaces.ServiceChoiceBoxInterface;
import interfaces.ServiceAddInterface;
import interfaces.ServiceChangeInterface;

import providers.Logger;
import providers.Database;

import global.detailed.DetailedService;
import global.choice_box.ChoiceBoxModel;

import models.fasilitas.FasilitasModel;

public class RuanganService
		extends DetailedService<RuanganModel, RuanganDetailedModel>
		implements ServiceFindInterface<RuanganModel>, ServiceFindDetailedInterface<RuanganDetailedModel>, ServiceChoiceBoxInterface, ServiceAddInterface<RuanganModel>, ServiceChangeInterface<RuanganModel> {
	private static RuanganService instance;

	private RuanganService(Logger logger, Database database, String table) {
		super(logger, database, table);
	}

	public static RuanganService getInstance() {
		if (RuanganService.instance == null) {
			try {
				RuanganService.instance = new RuanganService(
						new Logger(RuanganService.class.getName()),
						Database.getInstance(),
						"ruangan");
			}
			catch (Exception e) {
				RuanganService.instance.logger.error("Failed to initialize RuanganService instance: " + e.getMessage());

				throw new RuntimeException("Failed to initialize RuanganService instance");
			}
		}

		RuanganService.instance.logger.info("Get Instance");

		return RuanganService.instance;
	}

	@Override
	public RuanganModel[] find() {
		this.logger.debug("Find");

		try {
			final int total = this.database.tableTotal(this.table);
			final ResultSet result = this.database.executeQuery(""
					+ "SELECT "
					+ "id, "
					+ "nama, "
					+ "deskripsi, "
					+ "kapasitas, "
					+ "dibuat, "
					+ "diubah "
					+ "FROM " + this.table
					+ ";");

			final RuanganModel[] models = new RuanganModel[total];

			int i = 0;
			while (result.next()) {
				models[i] = new RuanganModel(
						result.getInt("id"),
						result.getString("nama"),
						result.getString("deskripsi"),
						result.getInt("kapasitas"),
						result.getTimestamp("dibuat"),
						result.getTimestamp("diubah"));

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
	public RuanganModel findId(int id) {
		this.logger.debug("Find Id");

		try {
			final ResultSet result = this.database.executeQuery(""
					+ "SELECT "
					+ "id, "
					+ "nama, "
					+ "deskripsi, "
					+ "kapasitas, "
					+ "dibuat, "
					+ "diubah "
					+ "FROM " + this.table
					+ " WHERE id = " + id
					+ ";");

			if (result.next()) {
				return new RuanganModel(
						result.getInt("id"),
						result.getString("nama"),
						result.getString("deskripsi"),
						result.getInt("kapasitas"),
						result.getTimestamp("dibuat"),
						result.getTimestamp("diubah"));
			}
		}
		catch (Exception e) {
			this.logger.error("Failed to find id: " + e.getMessage());
		}

		return null;
	}

	@Override
	public RuanganDetailedModel[] findDetailed() {
		this.logger.debug("Find Detailed");

		try {
			final int total = this.database.tableTotal(this.table);
			final ResultSet result = this.database.executeQuery(""
					+ "SELECT "
					+ "id, "
					+ "nama, "
					+ "deskripsi, "
					+ "kapasitas, "
					+ "dibuat, "
					+ "diubah "
					+ "FROM " + this.table
					+ ";");

			final RuanganDetailedModel[] models = new RuanganDetailedModel[total];

			int i = 0;
			while (result.next()) {
				final int ruanganFasilitasTotal = this.database.tableTotal("ruangan_fasilitas", "id_ruangan=" + result.getInt("id"));
				final ResultSet ruanganFasilitasResult = this.database.executeQuery(""
						+ "SELECT "
						+ "fasilitas.id, "
						+ "fasilitas.nama, "
						+ "fasilitas.keterangan, "
						+ "fasilitas.dibuat, "
						+ "fasilitas.diubah "
						+ "FROM ruangan_fasilitas "
						+ "INNER JOIN fasilitas ON ruangan_fasilitas.id_fasilitas = fasilitas.id "
						+ "WHERE ruangan_fasilitas.id_ruangan=" + result.getInt("id")
						+ ";");

				final FasilitasModel[] fasilitas = new FasilitasModel[ruanganFasilitasTotal];

				int fasilitasIndex = 0;
				while (ruanganFasilitasResult.next()) {
					fasilitas[fasilitasIndex] = new FasilitasModel(
							ruanganFasilitasResult.getInt("id"),
							ruanganFasilitasResult.getString("nama"),
							ruanganFasilitasResult.getString("keterangan"),
							ruanganFasilitasResult.getTimestamp("dibuat"),
							ruanganFasilitasResult.getTimestamp("diubah"));

					fasilitasIndex++;
				}

				models[i] = new RuanganDetailedModel(
						result.getInt("id"),
						result.getString("nama"),
						result.getString("deskripsi"),
						result.getInt("kapasitas"),
						fasilitas,
						result.getTimestamp("dibuat"),
						result.getTimestamp("diubah"));

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
	public RuanganDetailedModel findIdDetailed(int id) {
		this.logger.debug("Find Id Detailed");

		try {
			final ResultSet result = this.database.executeQuery(""
					+ "SELECT "
					+ "id, "
					+ "nama, "
					+ "deskripsi, "
					+ "kapasitas, "
					+ "dibuat, "
					+ "diubah "
					+ "FROM " + this.table + " "
					+ "WHERE id=" + id
					+ ";");

			if (result.next()) {
				final int ruanganFasilitasTotal = this.database.tableTotal("ruangan_fasilitas", "id_ruangan=" + result.getInt("id"));
				final ResultSet ruanganFasilitasResult = this.database.executeQuery(""
						+ "SELECT "
						+ "fasilitas.id, "
						+ "fasilitas.nama, "
						+ "fasilitas.keterangan, "
						+ "fasilitas.dibuat, "
						+ "fasilitas.diubah "
						+ "FROM ruangan_fasilitas "
						+ "INNER JOIN fasilitas ON ruangan_fasilitas.id_fasilitas = fasilitas.id "
						+ "WHERE ruangan_fasilitas.id_ruangan=" + result.getInt("id")
						+ ";");

				final FasilitasModel[] fasilitas = new FasilitasModel[ruanganFasilitasTotal];

				int fasilitasIndex = 0;
				while (ruanganFasilitasResult.next()) {
					fasilitas[fasilitasIndex] = new FasilitasModel(
							ruanganFasilitasResult.getInt("fasilitas.id"),
							ruanganFasilitasResult.getString("fasilitas.nama"),
							ruanganFasilitasResult.getString("fasilitas.keterangan"),
							ruanganFasilitasResult.getTimestamp("fasilitas.dibuat"),
							ruanganFasilitasResult.getTimestamp("fasilitas.diubah"));

					fasilitasIndex++;
				}

				return new RuanganDetailedModel(
						result.getInt("id"),
						result.getString("nama"),
						result.getString("deskripsi"),
						result.getInt("kapasitas"),
						fasilitas,
						result.getTimestamp("dibuat"),
						result.getTimestamp("diubah"));
			}
		}
		catch (Exception e) {
			this.logger.error("Failed to find id detailed: " + e.getMessage());
		}

		return null;
	}

	@Override
	public ChoiceBoxModel[] findChoiceBox() {
		this.logger.debug("Find Choice Box");

		try {
			final int total = this.database.tableTotal(this.table);
			final ResultSet result = this.database.executeQuery(""
					+ "SELECT "
					+ "id, "
					+ "nama, "
					+ "deskripsi "
					+ "FROM " + this.table
					+ ";");

			final ChoiceBoxModel[] models = new ChoiceBoxModel[total + 1];

			models[0] = new ChoiceBoxModel(0, "Pilih Ruangan");

			int i = 0;
			while (result.next()) {
				models[i] = new ChoiceBoxModel(
						result.getInt("id"),
						result.getString("nama") + " - " + result.getString("deskripsi"));

				i++;
			}

			return models;
		}
		catch (Exception e) {
			this.logger.error("Failed to find choice box: " + e.getMessage());
		}

		return null;
	}

	@Override
	public void add(RuanganModel model) {
		this.logger.debug("Add");

		try {
			this.database.executeUpdate(""
					+ "INSERT INTO " + this.table + " ("
					+ "nama, "
					+ "deskripsi, "
					+ "kapasitas "
					+ ") VALUES ("
					+ "'" + model.getNama() + "', "
					+ "'" + model.getDeskripsi() + "', "
					+ "'" + model.getKapasitas() + "'"
					+ ");");
		}
		catch (Exception e) {
			this.logger.error("Failed to add: " + e.getMessage());
		}
	}

	@Override
	public void change(int id, RuanganModel model) {
		this.logger.debug("Change");

		try {
			this.database.executeUpdate(""
					+ "UPDATE " + this.table + " SET "
					+ "nama='" + model.getNama() + "', "
					+ "deskripsi='" + model.getDeskripsi() + "', "
					+ "kapasitas='" + model.getKapasitas() + "' "
					+ "WHERE "
					+ "id=" + id
					+ ";");
		}
		catch (Exception e) {
			this.logger.error("Failed to change: " + e.getMessage());
		}
	}

	public void addFasilitas(int idRuangan, int idFasilitas) {
		this.logger.debug("Add Fasilitas");

		try {
			this.database.executeUpdate(""
					+ "INSERT INTO ruangan_fasilitas ("
					+ "id_ruangan, "
					+ "id_fasilitas"
					+ ") VALUES ("
					+ idRuangan + ", "
					+ idFasilitas
					+ ");");
		}
		catch (Exception e) {
			this.logger.error("Failed to add fasilitas: " + e.getMessage());
		}
	}

	public void removeFasilitas(int idRuangan, int idFasilitas) {
		this.logger.debug("Remove Fasilitas");

		try {
			this.database.executeUpdate(""
					+ "DELETE FROM ruangan_fasilitas "
					+ "WHERE "
					+ "id_ruangan=" + idRuangan + " AND "
					+ "id_fasilitas=" + idFasilitas
					+ ";");
		}
		catch (Exception e) {
			this.logger.error("Failed to remove fasilitas: " + e.getMessage());
		}
	}
}
