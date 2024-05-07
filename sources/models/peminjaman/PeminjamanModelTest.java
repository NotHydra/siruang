package models.peminjaman;


import static org.junit.Assert.*;

import org.junit.Test;
import java.sql.Timestamp;

import java.time.LocalDateTime;

public class PeminjamanModelTest {
	@Test
	public void testConstructorWithIdRuanganIdPenggunaNamaPeminjamWaktuMulaiWaktuSelesaiAndKeterangan() {
		int idRuangan = 1;
		int idPengguna = 1;
		String namaPeminjam = "Test Nama";
		LocalDateTime waktuMulai = LocalDateTime.now();
		LocalDateTime waktuSelesai = LocalDateTime.now().plusHours(1);
		String keterangan = "Test Keterangan";

		PeminjamanModel peminjaman = new PeminjamanModel(idRuangan, idPengguna, namaPeminjam, waktuMulai, waktuSelesai, keterangan);

		assertNotNull(peminjaman);
		assertEquals(idRuangan, peminjaman.getIdRuangan());
		assertEquals(idPengguna, peminjaman.getIdPengguna());
		assertEquals(namaPeminjam, peminjaman.getNamaPeminjam());
		assertEquals(waktuMulai, peminjaman.getWaktuMulai());
		assertEquals(waktuSelesai, peminjaman.getWaktuSelesai());
		assertEquals(keterangan, peminjaman.getKeterangan());
	}

	@Test
	public void testConstructorWithIdIdRuanganIdPenggunaNamaPeminjamWaktuMulaiWaktuSelesaiKeteranganAndDibuat() {
		int id = 1;
		int idRuangan = 1;
		int idPengguna = 1;
		String namaPeminjam = "Test Nama";
		LocalDateTime waktuMulai = LocalDateTime.now();
		LocalDateTime waktuSelesai = LocalDateTime.now().plusHours(1);
		String keterangan = "Test Keterangan";
		Timestamp dibuat = new Timestamp(System.currentTimeMillis());

		PeminjamanModel peminjaman = new PeminjamanModel(id, idRuangan, idPengguna, namaPeminjam, waktuMulai, waktuSelesai, keterangan, dibuat);

		assertNotNull(peminjaman);
		assertEquals(id, peminjaman.getId());
		assertEquals(idRuangan, peminjaman.getIdRuangan());
		assertEquals(idPengguna, peminjaman.getIdPengguna());
		assertEquals(namaPeminjam, peminjaman.getNamaPeminjam());
		assertEquals(waktuMulai, peminjaman.getWaktuMulai());
		assertEquals(waktuSelesai, peminjaman.getWaktuSelesai());
		assertEquals(keterangan, peminjaman.getKeterangan());
		assertEquals(dibuat, peminjaman.getDibuat());
	}

	@Test
	public void testConstructorWithIdRuanganZero() {
		try {
			new PeminjamanModel(0, 1, "Test Nama", LocalDateTime.now(), LocalDateTime.now().plusHours(1), "Test Keterangan");

			fail("Expected IllegalArgumentException");
		}
		catch (IllegalArgumentException e) {
			assertEquals("Id ruangan harus lebih dari 0", e.getMessage());
		}
	}

	@Test
	public void testConstructorWithIdRuanganNegative() {
		try {
			new PeminjamanModel(-1, 1, "Test Nama", LocalDateTime.now(), LocalDateTime.now().plusHours(1), "Test Keterangan");

			fail("Expected IllegalArgumentException");
		}
		catch (IllegalArgumentException e) {
			assertEquals("Id ruangan harus lebih dari 0", e.getMessage());
		}
	}

	@Test
	public void testConstructorWithIdPenggunaZero() {
		try {
			new PeminjamanModel(1, 0, "Test Nama", LocalDateTime.now(), LocalDateTime.now().plusHours(1), "Test Keterangan");

			fail("Expected IllegalArgumentException");
		}
		catch (IllegalArgumentException e) {
			assertEquals("Id pengguna harus lebih dari 0", e.getMessage());
		}
	}

	@Test
	public void testConstructorWithIdPenggunaNegative() {
		try {
			new PeminjamanModel(1, -1, "Test Nama", LocalDateTime.now(), LocalDateTime.now().plusHours(1), "Test Keterangan");

			fail("Expected IllegalArgumentException");
		}
		catch (IllegalArgumentException e) {
			assertEquals("Id pengguna harus lebih dari 0", e.getMessage());
		}
	}

	@Test
	public void testConstructorWithNullNamaPeminjam() {
		try {
			new PeminjamanModel(1, 1, null, LocalDateTime.now(), LocalDateTime.now().plusHours(1), "Test Keterangan");

			fail("Expected IllegalArgumentException");
		}
		catch (IllegalArgumentException e) {
			assertEquals("Nama peminjam tidak boleh kosong", e.getMessage());
		}
	}

	@Test
	public void testConstructorWithEmptyNamaPeminjam() {
		try {
			new PeminjamanModel(1, 1, "", LocalDateTime.now(), LocalDateTime.now().plusHours(1), "Test Keterangan");

			fail("Expected IllegalArgumentException");
		}
		catch (IllegalArgumentException e) {
			assertEquals("Nama peminjam tidak boleh kosong", e.getMessage());
		}
	}

	@Test
	public void testConstructorWithWaktuMulaiEqualWaktuSelesai() {
		LocalDateTime waktu = LocalDateTime.now();

		try {
			new PeminjamanModel(1, 1, "Test Nama", waktu, waktu, "Test Keterangan");

			fail("Expected IllegalArgumentException");
		}
		catch (IllegalArgumentException e) {
			assertEquals("Waktu mulai tidak boleh sama dengan waktu selesai", e.getMessage());
		}
	}

	@Test
	public void testConstructorWithWaktuMulaiAfterWaktuSelesai() {
		LocalDateTime waktuMulai = LocalDateTime.now();
		LocalDateTime waktuSelesai = waktuMulai.minusHours(1);

		try {
			new PeminjamanModel(1, 1, "Test Nama", waktuMulai, waktuSelesai, "Test Keterangan");

			fail("Expected IllegalArgumentException");
		}
		catch (IllegalArgumentException e) {
			assertEquals("Waktu mulai harus sebelum waktu selesai", e.getMessage());
		}
	}

	@Test
	public void testConstructorWithNullKeterangan() {
		try {
			new PeminjamanModel(1, 1, "Test Nama", LocalDateTime.now(), LocalDateTime.now().plusHours(1), null);

			fail("Expected IllegalArgumentException");
		}
		catch (IllegalArgumentException e) {
			assertEquals("Keterangan tidak boleh kosong", e.getMessage());
		}
	}

	@Test
	public void testConstructorWithEmptyKeterangan() {
		try {
			new PeminjamanModel(1, 1, "Test Nama", LocalDateTime.now(), LocalDateTime.now().plusHours(1), "");

			fail("Expected IllegalArgumentException");
		}
		catch (IllegalArgumentException e) {
			assertEquals("Keterangan tidak boleh kosong", e.getMessage());
		}
	}

	@Test
	public void testGetIdRuangan() {
		int idRuangan = 1;
		PeminjamanModel peminjaman = new PeminjamanModel(idRuangan, 1, "Test Nama", LocalDateTime.now(), LocalDateTime.now().plusHours(1), "Test Keterangan");

		assertEquals(idRuangan, peminjaman.getIdRuangan());
	}

	@Test
	public void testGetIdPengguna() {
		int idPengguna = 1;
		PeminjamanModel peminjaman = new PeminjamanModel(1, idPengguna, "Test Nama", LocalDateTime.now(), LocalDateTime.now().plusHours(1), "Test Keterangan");

		assertEquals(idPengguna, peminjaman.getIdPengguna());
	}

	@Test
	public void testGetNamaPeminjam() {
		String namaPeminjam = "Test Nama";
		PeminjamanModel peminjaman = new PeminjamanModel(1, 1, namaPeminjam, LocalDateTime.now(), LocalDateTime.now().plusHours(1), "Test Keterangan");

		assertEquals(namaPeminjam, peminjaman.getNamaPeminjam());
	}

	@Test
	public void testGetWaktuMulai() {
		LocalDateTime waktuMulai = LocalDateTime.now();
		PeminjamanModel peminjaman = new PeminjamanModel(1, 1, "Test Nama", waktuMulai, LocalDateTime.now().plusHours(1), "Test Keterangan");

		assertEquals(waktuMulai, peminjaman.getWaktuMulai());
	}

	@Test
	public void testGetWaktuSelesai() {
		LocalDateTime waktuSelesai = LocalDateTime.now().plusHours(1);
		PeminjamanModel peminjaman = new PeminjamanModel(1, 1, "Test Nama", LocalDateTime.now(), waktuSelesai, "Test Keterangan");

		assertEquals(waktuSelesai, peminjaman.getWaktuSelesai());
	}

	@Test
	public void testGetKeterangan() {
		String keterangan = "Test Keterangan";
		PeminjamanModel peminjaman = new PeminjamanModel(1, 1, "Test Nama", LocalDateTime.now(), LocalDateTime.now().plusHours(1), keterangan);

		assertEquals(keterangan, peminjaman.getKeterangan());
	}

	@Test
	public void testGetDibuat() {
		Timestamp dibuat = new Timestamp(System.currentTimeMillis());
		PeminjamanModel peminjaman = new PeminjamanModel(1, 1, 1, "Test Nama", LocalDateTime.now(), LocalDateTime.now().plusHours(1), "Test Keterangan", dibuat);

		assertEquals(dibuat, peminjaman.getDibuat());
	}

	@Test
	public void testToString() {
		int id = 1;
		int idRuangan = 1;
		int idPengguna = 1;
		String namaPeminjam = "Test Nama";
		LocalDateTime waktuMulai = LocalDateTime.now();
		LocalDateTime waktuSelesai = LocalDateTime.now().plusHours(1);
		String keterangan = "Test Keterangan";
		Timestamp dibuat = new Timestamp(System.currentTimeMillis());

		PeminjamanModel peminjaman = new PeminjamanModel(id, idRuangan, idPengguna, namaPeminjam, waktuMulai, waktuSelesai, keterangan, dibuat);

		String expected = "PeminjamanModel("
				+ "id=" + id + ", "
				+ "idRuangan=" + idRuangan + ", "
				+ "idPengguna=" + idPengguna + ", "
				+ "namaPeminjam=" + namaPeminjam + ", "
				+ "waktuMulai=" + waktuMulai + ", "
				+ "waktuSelesai=" + waktuSelesai + ", "
				+ "keterangan=" + keterangan + ", "
				+ "dibuat=" + dibuat
				+ ")";

		assertEquals(expected, peminjaman.toString());
	}
}
