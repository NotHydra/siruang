// package models.peminjaman;

// import interfaces.ServiceAddInterface;
// import interfaces.ServiceChangeInterface;
// import interfaces.ServiceChoiceBoxInterface;
// import interfaces.ServiceFindDetailedInterface;
// import interfaces.ServiceFindInterface;

// import providers.Logger;
// import providers.Database;

// import java.sql.ResultSet;

// import global.detailed.DetailedService;

// public class PeminjamanService
// extends DetailedService<PeminjamanModel, PeminjamanDetailedModel>
// implements ServiceFindDetailedInterface<PeminjamanDetailedModel>,
// ServiceFindInterface<PeminjamanModel>, ServiceAddInterface<PeminjamanModel>,
// ServiceChangeInterface<PeminjamanModel> {
// private static PeminjamanService instance;

// private PeminjamanService(Logger logger, Database database, String table) {
// super(logger, database, table);
// }

// public static PeminjamanService getInstance() {
// if (PeminjamanService.instance == null) {
// try {
// PeminjamanService.instance = new PeminjamanService(
// new Logger(PeminjamanService.class.getName()),
// Database.getInstance(),
// "peminjaman");
// }
// catch (Exception e) {
// PeminjamanService.instance.logger.error("Failed to initialize
// PeminjamanService instance: " + e.getMessage());

// throw new RuntimeException("Failed to initialize PeminjamanService
// instance");
// }
// }

// PeminjamanService.instance.logger.debug("Get Instance");

// return PeminjamanService.instance;
// }

// @Override
// public PeminjamanModel[] find() {
// this.logger.debug("Find");

// try {
// final int total = this.database.tableTotal(this.table);
// final ResultSet result = this.database.executeQuery(""
// + "SELECT "
// + "id, "
// + "id_anggota, "
// + "id_buku, "
// + "tanggal_pinjam, "
// + "tanggal_kembali, "
// + "status, "
// + "dibuat, "
// + "diubah "
// + "FROM " + this.table
// + ";");

// final PeminjamanModel[] models = new PeminjamanModel[total];

// int i = 0;
// while (result.next()) {
// models[i] = new PeminjamanModel();

// i++;
// }

// }
// catch (Exception e) {
// this.logger.error("Failed to find: " + e.getMessage());
// }

// return null;
// }

// }
