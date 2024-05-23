package models.ruangan;


import providers.Logger;

import global.base.BaseSeeder;

public class RuanganSeeder extends BaseSeeder<RuanganModel, RuanganService> {
	private static RuanganSeeder instance;

	private RuanganSeeder(Logger logger, RuanganService modelService, RuanganModel[] models) {
		super(logger, modelService, models);
	}

	public static RuanganSeeder getInstance() {
		if (RuanganSeeder.instance == null) {
			try {
				RuanganSeeder.instance = new RuanganSeeder(
						new Logger(RuanganSeeder.class.getName()),
						RuanganService.getInstance(),
						new RuanganModel[] {
								new RuanganModel("A101", "Ruangan yang terletak di gedung A lantai 1", 50),
								new RuanganModel("A102", "Ruangan yang terletak di gedung A lantai 1", 45),
								new RuanganModel("A103", "Ruangan yang terletak di gedung A lantai 1", 40),
								new RuanganModel("A104", "Ruangan yang terletak di gedung A lantai 1", 35),
								new RuanganModel("A105", "Ruangan yang terletak di gedung A lantai 1", 30),
								new RuanganModel("A106", "Ruangan yang terletak di gedung A lantai 1", 25),
								new RuanganModel("A107", "Ruangan yang terletak di gedung A lantai 1", 20),
								new RuanganModel("A201", "Ruangan yang terletak di gedung A lantai 2", 50),
								new RuanganModel("A202", "Ruangan yang terletak di gedung A lantai 2", 45),
								new RuanganModel("A203", "Ruangan yang terletak di gedung A lantai 2", 40),
								new RuanganModel("A204", "Ruangan yang terletak di gedung A lantai 2", 35),
								new RuanganModel("A205", "Ruangan yang terletak di gedung A lantai 2", 30),
								new RuanganModel("A206", "Ruangan yang terletak di gedung A lantai 2", 25),
								new RuanganModel("A207", "Ruangan yang terletak di gedung A lantai 2", 20),
								new RuanganModel("A301", "Ruangan yang terletak di gedung A lantai 3", 50),
								new RuanganModel("A302", "Ruangan yang terletak di gedung A lantai 3", 45),
								new RuanganModel("A303", "Ruangan yang terletak di gedung A lantai 3", 40),
								new RuanganModel("A304", "Ruangan yang terletak di gedung A lantai 3", 35),
								new RuanganModel("A305", "Ruangan yang terletak di gedung A lantai 3", 30),
								new RuanganModel("A306", "Ruangan yang terletak di gedung A lantai 3", 25),
								new RuanganModel("A307", "Ruangan yang terletak di gedung A lantai 3", 20),
								new RuanganModel("Auditorium A", "Ruangan auditorium yang terletak di gedung A lantai 3", 200),
								new RuanganModel("Laboratorium Kimia 1 E101", "Laboratorium kimia yang terletak di gedung E lantai 1", 20),
								new RuanganModel("Laboratorium Fisika E102", "Laboratorium fisika yang terletak di gedung E lantai 1", 80),
								new RuanganModel("Laboratorium Kimia 2 E103", "Laboratorium kimia yang terletak di gedung E lantai 1", 20),
								new RuanganModel("E104", "Ruangan yang terletak di gedung E lantai 1", 35),
								new RuanganModel("E106", "Ruangan yang terletak di gedung E lantai 1", 25),
								new RuanganModel("E107", "Ruangan yang terletak di gedung E lantai 1", 20),
								new RuanganModel("E201", "Ruangan yang terletak di gedung E lantai 2", 50),
								new RuanganModel("E202", "Ruangan yang terletak di gedung E lantai 2", 45),
								new RuanganModel("E203", "Ruangan yang terletak di gedung E lantai 2", 40),
								new RuanganModel("E204", "Ruangan yang terletak di gedung E lantai 2", 35),
								new RuanganModel("E205", "Ruangan yang terletak di gedung E lantai 2", 30),
								new RuanganModel("E206", "Ruangan yang terletak di gedung E lantai 2", 25),
								new RuanganModel("E207", "Ruangan yang terletak di gedung E lantai 2", 20),
								new RuanganModel("E301", "Ruangan yang terletak di gedung E lantai 3", 50),
								new RuanganModel("E302", "Ruangan yang terletak di gedung E lantai 3", 45),
								new RuanganModel("E303", "Ruangan yang terletak di gedung E lantai 3", 40),
								new RuanganModel("E304", "Ruangan yang terletak di gedung E lantai 3", 35),
								new RuanganModel("E305", "Ruangan yang terletak di gedung E lantai 3", 30),
								new RuanganModel("E306", "Ruangan yang terletak di gedung E lantai 3", 25),
								new RuanganModel("E307", "Ruangan yang terletak di gedung E lantai 3", 20),
								new RuanganModel("F101", "Ruangan yang terletak di gedung F lantai 1", 50),
								new RuanganModel("F102", "Ruangan yang terletak di gedung F lantai 1", 45),
								new RuanganModel("F103", "Ruangan yang terletak di gedung F lantai 1", 40),
								new RuanganModel("F104", "Ruangan yang terletak di gedung F lantai 1", 35),
								new RuanganModel("F105", "Ruangan yang terletak di gedung F lantai 1", 30),
								new RuanganModel("F106", "Ruangan yang terletak di gedung F lantai 1", 25),
								new RuanganModel("F107", "Ruangan yang terletak di gedung F lantai 1", 20),
								new RuanganModel("F201", "Ruangan yang terletak di gedung F lantai 2", 50),
								new RuanganModel("F202", "Ruangan yang terletak di gedung F lantai 2", 45),
								new RuanganModel("F203", "Ruangan yang terletak di gedung F lantai 2", 40),
								new RuanganModel("F204", "Ruangan yang terletak di gedung F lantai 2", 35),
								new RuanganModel("F205", "Ruangan yang terletak di gedung F lantai 2", 30),
								new RuanganModel("F206", "Ruangan yang terletak di gedung F lantai 2", 25),
								new RuanganModel("F207", "Ruangan yang terletak di gedung F lantai 2", 20),
								new RuanganModel("F301", "Ruangan yang terletak di gedung F lantai 3", 50),
								new RuanganModel("F302", "Ruangan yang terletak di gedung F lantai 3", 45),
								new RuanganModel("F303", "Ruangan yang terletak di gedung F lantai 3", 40),
								new RuanganModel("F304", "Ruangan yang terletak di gedung F lantai 3", 35),
								new RuanganModel("F305", "Ruangan yang terletak di gedung F lantai 3", 30),
								new RuanganModel("F306", "Ruangan yang terletak di gedung F lantai 3", 25),
								new RuanganModel("F307", "Ruangan yang terletak di gedung F lantai 3", 20),
								new RuanganModel("G101", "Ruangan yang terletak di gedung G lantai 1", 50),
								new RuanganModel("G102", "Ruangan yang terletak di gedung G lantai 1", 45),
								new RuanganModel("G103", "Ruangan yang terletak di gedung G lantai 1", 40),
								new RuanganModel("G104", "Ruangan yang terletak di gedung G lantai 1", 35),
								new RuanganModel("G105", "Ruangan yang terletak di gedung G lantai 1", 30),
								new RuanganModel("G106", "Ruangan yang terletak di gedung G lantai 1", 25),
								new RuanganModel("G107", "Ruangan yang terletak di gedung G lantai 1", 20),
								new RuanganModel("G201", "Ruangan yang terletak di gedung G lantai 2", 50),
								new RuanganModel("G202", "Ruangan yang terletak di gedung G lantai 2", 45),
								new RuanganModel("G203", "Ruangan yang terletak di gedung G lantai 2", 40),
								new RuanganModel("G204", "Ruangan yang terletak di gedung G lantai 2", 35),
								new RuanganModel("G205", "Ruangan yang terletak di gedung G lantai 2", 30),
								new RuanganModel("G206", "Ruangan yang terletak di gedung G lantai 2", 25),
								new RuanganModel("G207", "Ruangan yang terletak di gedung G lantai 2", 20),
								new RuanganModel("G301", "Ruangan yang terletak di gedung G lantai 3", 50),
								new RuanganModel("G302", "Ruangan yang terletak di gedung G lantai 3", 45),
								new RuanganModel("G303", "Ruangan yang terletak di gedung G lantai 3", 40),
								new RuanganModel("G304", "Ruangan yang terletak di gedung G lantai 3", 35),
								new RuanganModel("G305", "Ruangan yang terletak di gedung G lantai 3", 30),
								new RuanganModel("G306", "Ruangan yang terletak di gedung G lantai 3", 25),
								new RuanganModel("G307", "Ruangan yang terletak di gedung G lantai 3", 20),
						});
			}
			catch (Exception e) {
				RuanganSeeder.instance.logger.error("Failed to initialize RuanganSeeder instance: " + e.getMessage());

				throw new RuntimeException("Failed to initialize RuanganSeeder instance");
			}
		}

		RuanganSeeder.instance.logger.debug("Get Instance");

		return RuanganSeeder.instance;
	}
}
