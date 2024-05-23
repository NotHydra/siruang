package models.pengguna;


import providers.Logger;

import enums.LevelEnum;

import global.base.BaseSeeder;

public class PenggunaSeeder extends BaseSeeder<PenggunaModel, PenggunaService> {
	private static PenggunaSeeder instance;

	private PenggunaSeeder(Logger logger, PenggunaService modelService, PenggunaModel[] models) {
		super(logger, modelService, models);
	}

	public static PenggunaSeeder getInstance() {
		if (PenggunaSeeder.instance == null) {
			try {
				PenggunaSeeder.instance = new PenggunaSeeder(
						new Logger(PenggunaSeeder.class.getName()),
						PenggunaService.getInstance(),
						new PenggunaModel[] {
								new PenggunaModel("Operator 1", "operator1", "operator1", true, LevelEnum.OPERATOR),
								new PenggunaModel("Operator 2", "operator2", "operator2", true, LevelEnum.OPERATOR),
								new PenggunaModel("Operator 3", "operator3", "operator3", true, LevelEnum.OPERATOR),
								new PenggunaModel("Administrator 1", "administrator1", "administrator1", true, LevelEnum.ADMINISTRATOR),
								new PenggunaModel("Administrator 2", "administrator2", "administrator2", true, LevelEnum.ADMINISTRATOR),
								new PenggunaModel("Administrator 3", "administrator3", "administrator3", true, LevelEnum.ADMINISTRATOR),
						});
			}
			catch (Exception e) {
				PenggunaSeeder.instance.logger.error("Failed to initialize PenggunaSeeder instance: " + e.getMessage());

				throw new RuntimeException("Failed to initialize PenggunaSeeder instance");
			}
		}

		PenggunaSeeder.instance.logger.info("Get Instance");

		return PenggunaSeeder.instance;
	}
}
