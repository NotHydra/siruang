package models.pengguna;


import interfaces.ServiceAddInterface;
import interfaces.ServiceChangeInterface;
import interfaces.ServiceFindInterface;

import providers.Logger;
import providers.Database;

import java.sql.ResultSet;

import enums.LevelEnum;
import global.base.BaseService;

public class PenggunaService
		extends BaseService<PenggunaModel>
		implements ServiceFindInterface<PenggunaModel>, ServiceAddInterface<PenggunaModel>, ServiceChangeInterface<PenggunaModel> {
	private static PenggunaService instance;

	private PenggunaService(Logger logger, Database database, String table) {
		super(logger, database, table);
	}

	public static PenggunaService getInstance() {
		if (PenggunaService.instance == null) {
			try {
				PenggunaService.instance = new PenggunaService(
						new Logger(PenggunaService.class.getName()),
						Database.getInstance(),
						"pengguna");
			}
			catch (Exception e) {
				PenggunaService.instance.logger.error("Failed to initialize PenggunaService instance: " + e.getMessage());

				throw new RuntimeException("Failed to initialize PenggunaService instance");
			}
		}

		PenggunaService.instance.logger.debug("Get Instance");

		return PenggunaService.instance;
	}

	@Override
	public PenggunaModel[] find() {
		this.logger.debug("Find");

		try {
			final int total = this.database.tableTotal(this.table);
			final ResultSet result = this.database.executeQuery(""
					+ "SELECT "
					+ "id, "
					+ "nama, "
					+ "username, "
					+ "aktif, "
					+ "level, "
					+ "dibuat, "
					+ "diubah "
					+ "FROM " + this.table
					+ ";");

			final PenggunaModel[] models = new PenggunaModel[total];

			int i = 0;
			while (result.next()) {
				models[i] = new PenggunaModel(
						result.getInt("id"),
						result.getString("nama"),
						result.getString("username"),
						result.getBoolean("aktif"),
						LevelEnum.valueToEnum(result.getString("level")),
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
	public PenggunaModel findId(int id) {
		this.logger.debug("Find Id");

		try {
			final int total = this.database.tableTotal(this.table);
			final ResultSet result = this.database.executeQuery(""
				+ "SELECT "
				+ "id, "
				+ "nama, "
				+ "username, "
				+ "aktif, "
				+ "level, "
				+ "dibuat, "
				+ "diubah "
				+ "FROM " + this.table
				+ "WHERE id=" + id
				+ "; ");

			if (result.next()) {
				return new PenggunaModel(
						result.getInt("id"),
						result.getString("nama"),
						result.getString("username"),
						result.getBoolean("aktif"),
						LevelEnum.valueToEnum(result.getString("level")),
						result.getTimestamp("dibuat"),
						result.getTimestamp("diubah"));
			}
		}
		catch (Exception e) {
			this.logger.error("Failed to find id:" + e.getMessage());
		}

		return null;
	}	

	@Override
	public void add(PenggunaModel model) {
		this.logger.debug("Add");

		try {
			this.database.executeUpdate(""
					+ "INSERT INTO " + this.table + " ("
					+ "nama, "
					+ "username, "
					+ "password, "
					+ "aktif, "
					+ "level "
					+ ") VALUES ("
					+ "'" + model.getNama() + "', "
					+ "'" + model.getUsername() + "', "
					+ "'" + model.getPassword() + "', "
					+ "'" + (model.getAktif() ? 1 : 0) + "', "
					+ "'" + model.getLevel() + "'"
					+ ");");
		}
		catch (Exception e) {
			this.logger.error("Failed to add:" + e.getMessage());
		}
	}

	@Override
	public void change(int id, PenggunaModel model) {
		this.logger.debug("Change");

		try {
			this.database.executeUpdate(""
					+ "UPDATE " + this.table + " SET "
					+ "nama='" + model.getNama() + "', "
					+ "username='" + model.getUsername() + "', "
					+ "aktif='" + (model.getAktif() ? 1 : 0) + "', "
					+ "level='" + model.getLevel() + "' "
					+ "WHERE "
					+ "id=" + id
					+ ";");
		}
		catch (Exception e) {
			this.logger.error("Failed to Change:" + e.getMessage());
		}
	}

	public void changePassword(int id, String password) {
		this.logger.debug("Change Password");

		try {
			this.database.executeUpdate(""
					+ "UPDATE " + this.table + " SET "
					+ "password='" + password + "' "
					+ "WHERE "
					+ "id=" + id
					+ ";");
		}
		catch (Exception e) {
			this.logger.error("Failed to Change Password:" + e.getMessage());
		}
	}
}
