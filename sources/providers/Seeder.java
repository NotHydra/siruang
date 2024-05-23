package providers;

import models.pengguna.PenggunaSeeder;
import models.fasilitas.FasilitasSeeder;
import models.ruangan.RuanganSeeder;

public class Seeder {
	public static void main(String[] args) {
		Logger.getInstance().info("Seeding database");

		Logger.getInstance().debug("Seeding Pengguna");
		PenggunaSeeder.getInstance().seed();

		Logger.getInstance().debug("Seeding Fasilitas");
		FasilitasSeeder.getInstance().seed();

		Logger.getInstance().debug("Seeding Ruangan");
		RuanganSeeder.getInstance().seed();

		Logger.getInstance().info("Seeding completed");
	}
}
