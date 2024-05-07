package models.ruangan;


import static org.junit.Assert.*;

import org.junit.Test;
import java.sql.Timestamp;

public class RuanganModelTest {
	@Test
	public void testConstructorWithNamaDeskripsiAndKapasitas() {
		String nama = "Test Nama";
		String deskripsi = "Test Deskripsi";
		int kapasitas = 10;

		RuanganModel ruangan = new RuanganModel(nama, deskripsi, kapasitas);

		assertNotNull(ruangan);
		assertEquals(nama, ruangan.getNama());
		assertEquals(deskripsi, ruangan.getDeskripsi());
		assertEquals(kapasitas, ruangan.getKapasitas());
	}

	@Test
	public void testConstructorWithIdNamaDeskripsiKapasitasDibuatAndDiubah() {
		int id = 1;
		String nama = "Test Nama";
		String deskripsi = "Test Deskripsi";
		int kapasitas = 10;
		Timestamp dibuat = new Timestamp(System.currentTimeMillis());
		Timestamp diubah = new Timestamp(System.currentTimeMillis());

		RuanganModel ruangan = new RuanganModel(id, nama, deskripsi, kapasitas, dibuat, diubah);

		assertNotNull(ruangan);
		assertEquals(id, ruangan.getId());
		assertEquals(nama, ruangan.getNama());
		assertEquals(deskripsi, ruangan.getDeskripsi());
		assertEquals(kapasitas, ruangan.getKapasitas());
		assertEquals(dibuat, ruangan.getDibuat());
		assertEquals(diubah, ruangan.getDiubah());
	}

	@Test
	public void testConstructorWithNullNama() {
		try {
			new RuanganModel(null, "Test Deskripsi", 10);

			fail("Expected IllegalArgumentException");
		}
		catch (IllegalArgumentException e) {
			assertEquals("Nama tidak boleh kosong", e.getMessage());
		}
	}

	@Test
	public void testConstructorWithEmptyNama() {
		try {
			new RuanganModel("", "Test Deskripsi", 10);

			fail("Expected IllegalArgumentException");

		}
		catch (IllegalArgumentException e) {
			assertEquals("Nama tidak boleh kosong", e.getMessage());
		}
	}

	@Test
	public void testConstructorWithNullDeskripsi() {
		try {
			new RuanganModel("Test Nama", null, 10);

			fail("Expected IllegalArgumentException");
		}
		catch (IllegalArgumentException e) {
			assertEquals("Deskripsi tidak boleh kosong", e.getMessage());
		}
	}

	@Test
	public void testConstructorWithEmptyDeskripsi() {
		try {
			new RuanganModel("Test Nama", "", 10);

			fail("Expected IllegalArgumentException");

		}
		catch (IllegalArgumentException e) {
			assertEquals("Deskripsi tidak boleh kosong", e.getMessage());
		}
	}

	@Test
	public void testConstructorWithKapasitasZero() {
		try {
			new RuanganModel("Test Nama", "Test Deskripsi", 0);

			fail("Expected IllegalArgumentException");

		}
		catch (IllegalArgumentException e) {
			assertEquals("Kapasitas harus lebih dari 0", e.getMessage());
		}
	}

	@Test
	public void testConstructorWithKapasitasNegative() {
		try {
			new RuanganModel("Test Nama", "Test Deskripsi", -1);

			fail("Expected IllegalArgumentException");

		}
		catch (IllegalArgumentException e) {
			assertEquals("Kapasitas harus lebih dari 0", e.getMessage());
		}
	}

	@Test
	public void testGetId() {
		int id = 1;
		String nama = "Test Nama";
		String deskripsi = "Test Deskripsi";
		int kapasitas = 10;
		Timestamp dibuat = new Timestamp(System.currentTimeMillis());
		Timestamp diubah = new Timestamp(System.currentTimeMillis());

		RuanganModel ruangan = new RuanganModel(id, nama, deskripsi, kapasitas, dibuat, diubah);

		assertEquals(id, ruangan.getId());
	}

	@Test
	public void testGetNama() {
		String nama = "Test Nama";
		String deskripsi = "Test Deskripsi";
		int kapasitas = 10;

		RuanganModel ruangan = new RuanganModel(nama, deskripsi, kapasitas);

		assertEquals(nama, ruangan.getNama());
	}

	@Test
	public void testGetDeskripsi() {
		String nama = "Test Nama";
		String deskripsi = "Test Deskripsi";
		int kapasitas = 10;

		RuanganModel ruangan = new RuanganModel(nama, deskripsi, kapasitas);

		assertEquals(deskripsi, ruangan.getDeskripsi());
	}

	@Test
	public void testGetKapasitas() {
		String nama = "Test Nama";
		String deskripsi = "Test Deskripsi";
		int kapasitas = 10;

		RuanganModel ruangan = new RuanganModel(nama, deskripsi, kapasitas);

		assertEquals(kapasitas, ruangan.getKapasitas());
	}

	@Test
	public void testGetDibuat() {
		int id = 1;
		String nama = "Test Nama";
		String deskripsi = "Test Deskripsi";
		int kapasitas = 10;
		Timestamp dibuat = new Timestamp(System.currentTimeMillis());
		Timestamp diubah = new Timestamp(System.currentTimeMillis());

		RuanganModel ruangan = new RuanganModel(id, nama, deskripsi, kapasitas, dibuat, diubah);

		assertEquals(dibuat, ruangan.getDibuat());
	}

	@Test
	public void testGetDiubah() {
		int id = 1;
		String nama = "Test Nama";
		String deskripsi = "Test Deskripsi";
		int kapasitas = 10;
		Timestamp dibuat = new Timestamp(System.currentTimeMillis());
		Timestamp diubah = new Timestamp(System.currentTimeMillis());

		RuanganModel ruangan = new RuanganModel(id, nama, deskripsi, kapasitas, dibuat, diubah);

		assertEquals(diubah, ruangan.getDiubah());
	}

	@Test
	public void testToString() {
		int id = 1;
		String nama = "Test Nama";
		String deskripsi = "Test Deskripsi";
		int kapasitas = 10;
		Timestamp dibuat = new Timestamp(System.currentTimeMillis());
		Timestamp diubah = new Timestamp(System.currentTimeMillis());

		RuanganModel ruangan = new RuanganModel(id, nama, deskripsi, kapasitas, dibuat, diubah);

		String expected = "RuanganModel("
				+ "id=" + id + ", "
				+ "nama=" + nama + ", "
				+ "deskripsi=" + deskripsi + ", "
				+ "kapasitas=" + kapasitas + ", "
				+ "dibuat=" + dibuat + ", "
				+ "diubah=" + diubah
				+ ")";

		assertEquals(expected, ruangan.toString());
	}
}
