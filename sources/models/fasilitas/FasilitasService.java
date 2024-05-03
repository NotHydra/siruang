package models.fasilitas;


import java.sql.ResultSet;

import interfaces.ServiceFindInterface;
import interfaces.ServiceChoiceBoxInterface;
import interfaces.ServiceAddInterface;
import interfaces.ServiceChangeInterface;

import providers.Logger;
import providers.Database;

import global.base.BaseService;
import global.choice_box.ChoiceBoxModel;

public class FasilitasService
		extends BaseService<FasilitasModel>
		implements ServiceFindInterface<FasilitasModel>, ServiceAddInterface<FasilitasModel>, ServiceChangeInterface<FasilitasModel>, ServiceChoiceBoxInterface {
	private static FasilitasService instance;

	private FasilitasService(Logger logger, Database database, String table) {
		super(logger, database, table);
	}

	public static FasilitasService getInstance() {
		if (FasilitasService.instance == null) {
			try {
				FasilitasService.instance = new FasilitasService(
						new Logger(FasilitasService.class.getName()),
						Database.getInstance(),
						"fasilitas");
			}
			catch (Exception e) {
				FasilitasService.instance.logger.error("Failed to initialize FasilitasService instance: " + e.getMessage());

				throw new RuntimeException("Failed to initialize FasilitasService instance");
			}
		}

		FasilitasService.instance.logger.debug("Get Instance");

		return FasilitasService.instance;
	}

	@Override
	public FasilitasModel[] find() {
		this.logger.debug("Find");

		try {
			final int total = this.database.tableTotal(this.table);
			final ResultSet result = this.database.executeQuery(""
					+ "SELECT "
					+ "id, "
					+ "nama, "
					+ "keterangan, "
					+ "dibuat, "
					+ "diubah "
					+ "FROM " + this.table
					+ ";");

			final FasilitasModel[] models = new FasilitasModel[total];

			int i = 0;
			while (result.next()) {
				models[i] = new FasilitasModel(
						result.getInt("id"),
						result.getString("nama"),
						result.getString("keterangan"),
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
	public FasilitasModel findId(int id) {
		this.logger.debug("Find Id");

		try {
			final ResultSet result = this.database.executeQuery(""
					+ "SELECT "
					+ "id, "
					+ "nama, "
					+ "keterangan, "
					+ "dibuat, "
					+ "diubah "
					+ "FROM " + this.table
					+ "WHERE id=" + id
					+ ";");

			if (result.next()) {
				return new FasilitasModel(
						result.getInt("id"),
						result.getString("nama"),
						result.getString("keterangan"),
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
	public ChoiceBoxModel[] findChoiceBox() {
		this.logger.debug("Find Choice Box");

		try {
			final int total = this.database.tableTotal(this.table);
			final ResultSet result = this.database.executeQuery(""
					+ "SELECT "
					+ "id, "
					+ "nama "
					+ "FROM " + this.table
					+ ";");

			final ChoiceBoxModel[] models = new ChoiceBoxModel[total + 1];

			models[0] = new ChoiceBoxModel("Pilih Fasilitas");

			int i = 1;
			while (result.next()) {
				models[i] = new ChoiceBoxModel(
						result.getInt("id"),
						result.getString("nama"));

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
	public void add(FasilitasModel model) {
		this.logger.debug("Add");

		try {
			this.database.executeUpdate(""
					+ "INSERT INTO " + this.table + " ("
					+ "nama, "
					+ "keterangan"
					+ ") VALUES ("
					+ "'" + model.getNama() + "', "
					+ "'" + model.getKeterangan() + "'"
					+ ");");
		}

		catch (Exception e) {
			this.logger.error("Failed to add: " + e.getMessage());
		}
	}

	@Override
	public void change(int id, FasilitasModel model) {
		this.logger.debug("Change");

		try {
			this.database.executeUpdate(""
					+ "UPDATE " + this.table + " SET "
					+ "nama='" + model.getNama() + "', "
					+ "keterangan='" + model.getKeterangan() + "' "
					+ "WHERE "
					+ "id=" + id
					+ ";");
		}
		catch (Exception e) {
			this.logger.error("Failed to change: " + e.getMessage());
		}
	}
}
