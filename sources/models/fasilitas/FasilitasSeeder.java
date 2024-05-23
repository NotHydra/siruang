package models.fasilitas;


import providers.Logger;

import global.base.BaseSeeder;

public class FasilitasSeeder extends BaseSeeder<FasilitasModel, FasilitasService> {
	private static FasilitasSeeder instance;

	private FasilitasSeeder(Logger logger, FasilitasService modelService, FasilitasModel[] models) {
		super(logger, modelService, models);
	}

	public static FasilitasSeeder getInstance() {
		if (FasilitasSeeder.instance == null) {
			try {
				FasilitasSeeder.instance = new FasilitasSeeder(
						new Logger(FasilitasSeeder.class.getName()),
						FasilitasService.getInstance(),
						new FasilitasModel[] {
								new FasilitasModel("AC", "AC yang dapat digunakan untuk pendingin ruangan"),
								new FasilitasModel("Kipas Angin", "Kipas angin yang dapat digunakan untuk mengalirkan udara"),
								new FasilitasModel("Komputer", "Komputer yang dapat digunakan untuk belajar"),
								new FasilitasModel("Kursi", "Kursi yang dapat digunakan untuk belajar"),
								new FasilitasModel("Lampu", "Lampu yang dapat digunakan untuk menerangi ruangan"),
								new FasilitasModel("Layar Proyektor", "Layar proyektor yang dapat digunakan untuk menampilkan gambar"),
								new FasilitasModel("Lemari", "Lemari yang dapat digunakan untuk menyimpan barang"),
								new FasilitasModel("Meja", "Meja yang dapat digunakan untuk belajar"),
								new FasilitasModel("Microphone", "Microphone yang dapat digunakan untuk berbicara"),
								new FasilitasModel("Papan Tulis", "Papan tulis yang dapat digunakan untuk belajar"),
								new FasilitasModel("Proyektor", "Proyektor yang dapat digunakan untuk presentasi"),
								new FasilitasModel("Printer", "Printer yang dapat digunakan untuk mencetak dokumen"),
								new FasilitasModel("Scanner", "Scanner yang dapat digunakan untuk memindai dokumen"),
								new FasilitasModel("Sound System", "Sound system yang dapat digunakan untuk mendengarkan musik"),
								new FasilitasModel("Speaker", "Speaker yang dapat digunakan untuk mendengarkan musik"),
								new FasilitasModel("Televisi", "Televisi yang dapat digunakan untuk menonton acara televisi"),
								new FasilitasModel("Whiteboard", "Whiteboard yang dapat digunakan untuk belajar")
						});
			}
			catch (Exception e) {
				FasilitasSeeder.instance.logger.error("Failed to initialize FasilitasSeeder instance: " + e.getMessage());

				throw new RuntimeException("Failed to initialize FasilitasSeeder instance");
			}
		}

		FasilitasSeeder.instance.logger.info("Get Instance");

		return FasilitasSeeder.instance;
	}
}
