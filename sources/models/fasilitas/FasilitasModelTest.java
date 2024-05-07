package models.fasilitas;


import static org.junit.Assert.*;

import org.junit.Test;
import java.sql.Timestamp;

public class FasilitasModelTest {
	@Test
	public void testConstructorWithNamaAndKeterangan() {
		String nama = "Test Nama";
		String keterangan = "Test Keterangan";

		FasilitasModel fasilitas = new FasilitasModel(nama, keterangan);

		assertNotNull(fasilitas);
		assertEquals(nama, fasilitas.getNama());
		assertEquals(keterangan, fasilitas.getKeterangan());
	}

	@Test
	public void testConstructorWithIdNamaKeteranganDibuatAndDiubah() {
		int id = 1;
		String nama = "Test Nama";
		String keterangan = "Test Keterangan";
		Timestamp dibuat = new Timestamp(System.currentTimeMillis());
		Timestamp diubah = new Timestamp(System.currentTimeMillis());

		FasilitasModel fasilitas = new FasilitasModel(id, nama, keterangan, dibuat, diubah);

		assertNotNull(fasilitas);
		assertEquals(id, fasilitas.getId());
		assertEquals(nama, fasilitas.getNama());
		assertEquals(keterangan, fasilitas.getKeterangan());
		assertEquals(dibuat, fasilitas.getDibuat());
		assertEquals(diubah, fasilitas.getDiubah());
	}

	@Test
	public void testConstructorWithNullNama() {
		try {
			new FasilitasModel(null, "Test Keterangan");

			fail("Expected IllegalArgumentException");
		}
		catch (IllegalArgumentException e) {
			assertEquals("Nama tidak boleh kosong", e.getMessage());
		}
	}

	@Test
	public void testConstructorWithEmptyNama() {
		try {
			new FasilitasModel("", "Test Keterangan");

			fail("Expected IllegalArgumentException");

		}
		catch (IllegalArgumentException e) {
			assertEquals("Nama tidak boleh kosong", e.getMessage());
		}
	}

	@Test
	public void testConstructorWithNullKeterangan() {
		try {
			new FasilitasModel("Test Nama", null);

			fail("Expected IllegalArgumentException");
		}
		catch (IllegalArgumentException e) {
			assertEquals("Keterangan tidak boleh kosong", e.getMessage());
		}
	}

	@Test
	public void testConstructorWithEmptyKeterangan() {
		try {
			new FasilitasModel("Test Nama", "");

			fail("Expected IllegalArgumentException");
		}
		catch (IllegalArgumentException e) {
			assertEquals("Keterangan tidak boleh kosong", e.getMessage());
		}
	}

	@Test
	public void testGetId() {
		int id = 1;
		String nama = "Test Nama";
		String keterangan = "Test Keterangan";
		Timestamp dibuat = new Timestamp(System.currentTimeMillis());
		Timestamp diubah = new Timestamp(System.currentTimeMillis());

		FasilitasModel fasilitas = new FasilitasModel(id, nama, keterangan, dibuat, diubah);

		assertEquals(id, fasilitas.getId());
	}

	@Test
	public void testGetNama() {
		String nama = "Test Nama";
		String keterangan = "Test Keterangan";

		FasilitasModel fasilitas = new FasilitasModel(nama, keterangan);

		assertEquals(nama, fasilitas.getNama());

	}

	@Test
	public void testGetKeterangan() {
		String nama = "Test Nama";
		String keterangan = "Test Keterangan";

		FasilitasModel fasilitas = new FasilitasModel(nama, keterangan);

		assertEquals(keterangan, fasilitas.getKeterangan());
	}

	@Test
	public void testGetDibuat() {
		int id = 1;
		String nama = "Test Nama";
		String keterangan = "Test Keterangan";
		Timestamp dibuat = new Timestamp(System.currentTimeMillis());
		Timestamp diubah = new Timestamp(System.currentTimeMillis());

		FasilitasModel fasilitas = new FasilitasModel(id, nama, keterangan, dibuat, diubah);

		assertEquals(dibuat, fasilitas.getDibuat());
	}

	@Test
	public void testGetDiubah() {
		int id = 1;
		String nama = "Test Nama";
		String keterangan = "Test Keterangan";
		Timestamp dibuat = new Timestamp(System.currentTimeMillis());
		Timestamp diubah = new Timestamp(System.currentTimeMillis());

		FasilitasModel fasilitas = new FasilitasModel(id, nama, keterangan, dibuat, diubah);

		assertEquals(diubah, fasilitas.getDiubah());
	}

	@Test
	public void testToString() {
		int id = 1;
		String nama = "Test Nama";
		String keterangan = "Test Keterangan";
		Timestamp dibuat = new Timestamp(System.currentTimeMillis());
		Timestamp diubah = new Timestamp(System.currentTimeMillis());

		FasilitasModel fasilitas = new FasilitasModel(id, nama, keterangan, dibuat, diubah);

		String expected = "FasilitasModel("
				+ "id=" + id + ", "
				+ "nama=" + nama + ", "
				+ "keterangan=" + keterangan + ", "
				+ "dibuat=" + dibuat + ", "
				+ "diubah=" + diubah
				+ ")";

		assertEquals(expected, fasilitas.toString());
	}
}