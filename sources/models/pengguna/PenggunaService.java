package models.pengguna;


import interfaces.ServiceAddInterface;
import interfaces.ServiceChangeInterface;
import interfaces.ServiceFindInterface;

import providers.Logger;
import providers.Database;

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

		throw new UnsupportedOperationException("Unimplemented method 'find'");
	}

	@Override
	public PenggunaModel findId(int id) {
		// Tugas Bang Bastian

		throw new UnsupportedOperationException("Unimplemented method 'findId'");
	}

	@Override
	public void add(PenggunaModel model) {
		this.logger.debug("Add");

		try {
			this.database.executeUpdate(""
			+ "INSERT INTO " + this.table + " ("
			+ "nama,"
			+ "password,"
			+ "aktif,"
			+ "level"
			+ ") VALUES ("
			+ "'" + model.getNama() +"', "
			+ "'" + model.getUsername() + "', "
			+ "'" + model.getPassword() + "', "
			+ "'" + model.getAktif() + "', "
			+ "'" + model.getLevel() + "'"
			+ ");");
		}
		catch (Exception e) {
		    this.logger.error("Failed to add:" + e.getMessage());
		}
	}

	@Override
	public void change(int id, PenggunaModel model) {
		// Tugas Juwan

		throw new UnsupportedOperationException("Unimplemented method 'change'");
	}
}
