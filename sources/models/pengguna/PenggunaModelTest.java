package models.pengguna;


import static org.junit.Assert.*;

import org.junit.Test;
import java.sql.Timestamp;

import enums.LevelEnum;

public class PenggunaModelTest {
	@Test
	public void testConstructorWithNamaUsernamePasswordAktifAndLevel() {
		String nama = "Test Nama";
		String username = "Test Username";
		String password = "Test Password";
		boolean aktif = true;
		LevelEnum level = LevelEnum.ADMINISTRATOR;

		PenggunaModel pengguna = new PenggunaModel(nama, username, password, aktif, level);

		assertNotNull(pengguna);
		assertEquals(nama, pengguna.getNama());
		assertEquals(username, pengguna.getUsername());
		assertEquals(password, pengguna.getPassword());
		assertEquals(aktif, pengguna.getAktif());
		assertEquals(level, pengguna.getLevel());
	}

	@Test
	public void testConstructorWithIdNamaUsernamePasswordAktifLevelDibuatAndDiubah() {
		int id = 1;
		String nama = "Test Nama";
		String username = "Test Username";
		String password = "Test Password";
		boolean aktif = true;
		LevelEnum level = LevelEnum.ADMINISTRATOR;
		Timestamp dibuat = new Timestamp(System.currentTimeMillis());
		Timestamp diubah = new Timestamp(System.currentTimeMillis());

		PenggunaModel pengguna = new PenggunaModel(id, nama, username, password, aktif, level, dibuat, diubah);

		assertNotNull(pengguna);
		assertEquals(id, pengguna.getId());
		assertEquals(nama, pengguna.getNama());
		assertEquals(username, pengguna.getUsername());
		assertEquals(password, pengguna.getPassword());
		assertEquals(aktif, pengguna.getAktif());
		assertEquals(level, pengguna.getLevel());
		assertEquals(dibuat, pengguna.getDibuat());
		assertEquals(diubah, pengguna.getDiubah());
	}

	@Test
	public void testConstructorWithNamaUsernameAktifAndLevel() {
		String nama = "Test Nama";
		String username = "Test Username";
		boolean aktif = true;
		LevelEnum level = LevelEnum.ADMINISTRATOR;

		PenggunaModel pengguna = new PenggunaModel(nama, username, aktif, level);

		assertNotNull(pengguna);
		assertEquals(nama, pengguna.getNama());
		assertEquals(username, pengguna.getUsername());
		assertEquals(aktif, pengguna.getAktif());
		assertEquals(level, pengguna.getLevel());
	}

	@Test
	public void testConstructorWithIdNamaUsernameAktifLevelDibuatAndDiubah() {
		int id = 1;
		String nama = "Test Nama";
		String username = "Test Username";
		boolean aktif = true;
		LevelEnum level = LevelEnum.ADMINISTRATOR;
		Timestamp dibuat = new Timestamp(System.currentTimeMillis());
		Timestamp diubah = new Timestamp(System.currentTimeMillis());

		PenggunaModel pengguna = new PenggunaModel(id, nama, username, aktif, level, dibuat, diubah);

		assertNotNull(pengguna);
		assertEquals(id, pengguna.getId());
		assertEquals(nama, pengguna.getNama());
		assertEquals(username, pengguna.getUsername());
		assertEquals(aktif, pengguna.getAktif());
		assertEquals(level, pengguna.getLevel());
		assertEquals(dibuat, pengguna.getDibuat());
		assertEquals(diubah, pengguna.getDiubah());
	}

	@Test
	public void testConstructorWithNullNama() {
		try {
			new PenggunaModel(null, "Test Username", "Test Password", true, LevelEnum.ADMINISTRATOR);

			fail("Expected IllegalArgumentException");
		}
		catch (IllegalArgumentException e) {
			assertEquals("Nama Pengguna tidak boleh kosong", e.getMessage());
		}
	}

	@Test
	public void testConstructorWithEmptyNama() {
		try {
			new PenggunaModel("", "Test Username", "Test Password", true, LevelEnum.ADMINISTRATOR);

			fail("Expected IllegalArgumentException");

		}
		catch (IllegalArgumentException e) {
			assertEquals("Nama Pengguna tidak boleh kosong", e.getMessage());
		}
	}

	@Test
	public void testConstructorWithNullUsername() {
		try {
			new PenggunaModel("Test Nama", null, "Test Password", true, LevelEnum.ADMINISTRATOR);

			fail("Expected IllegalArgumentException");
		}
		catch (IllegalArgumentException e) {
			assertEquals("Username tidak boleh kosong", e.getMessage());
		}
	}

	@Test
	public void testConstructorWithEmptyUsername() {
		try {
			new PenggunaModel("Test Nama", "", "Test Password", true, LevelEnum.ADMINISTRATOR);

			fail("Expected IllegalArgumentException");
		}
		catch (IllegalArgumentException e) {
			assertEquals("Username tidak boleh kosong", e.getMessage());
		}
	}

	@Test
	public void testConstructorWithNullPassword() {
		try {
			new PenggunaModel("Test Nama", "Test Username", null, true, LevelEnum.ADMINISTRATOR);

			fail("Expected IllegalArgumentException");
		}
		catch (IllegalArgumentException e) {
			assertEquals("Password tidak boleh kosong", e.getMessage());
		}
	}

	@Test
	public void testConstructorWithEmptyPassword() {
		try {
			new PenggunaModel("Test Nama", "Test Username", "", true, LevelEnum.ADMINISTRATOR);

			fail("Expected IllegalArgumentException");

		}
		catch (IllegalArgumentException e) {
			assertEquals("Password tidak boleh kosong", e.getMessage());
		}
	}

	@Test
	public void testGetId() {
		int id = 1;
		String nama = "Test Nama";
		String username = "Test Username";
		String password = "Test Password";
		boolean aktif = true;
		LevelEnum level = LevelEnum.ADMINISTRATOR;
		Timestamp dibuat = new Timestamp(System.currentTimeMillis());
		Timestamp diubah = new Timestamp(System.currentTimeMillis());

		PenggunaModel pengguna = new PenggunaModel(id, nama, username, password, aktif, level, dibuat, diubah);

		assertEquals(id, pengguna.getId());
	}

	@Test
	public void testGetNama() {
		String nama = "Test Nama";
		String username = "Test Username";
		String password = "Test Password";
		boolean aktif = true;
		LevelEnum level = LevelEnum.ADMINISTRATOR;

		PenggunaModel pengguna = new PenggunaModel(nama, username, password, aktif, level);

		assertEquals(nama, pengguna.getNama());
	}

	@Test
	public void testGetUsername() {
		String nama = "Test Nama";
		String username = "Test Username";
		String password = "Test Password";
		boolean aktif = true;
		LevelEnum level = LevelEnum.ADMINISTRATOR;

		PenggunaModel pengguna = new PenggunaModel(nama, username, password, aktif, level);

		assertEquals(username, pengguna.getUsername());
	}

	@Test
	public void testGetPassword() {
		String nama = "Test Nama";
		String username = "Test Username";
		String password = "Test Password";
		boolean aktif = true;
		LevelEnum level = LevelEnum.ADMINISTRATOR;

		PenggunaModel pengguna = new PenggunaModel(nama, username, password, aktif,
				level);

		assertEquals(password, pengguna.getPassword());
	}

	@Test
	public void testGetAktif() {
		String nama = "Test Nama";
		String username = "Test Username";
		String password = "Test Password";
		boolean aktif = true;
		LevelEnum level = LevelEnum.ADMINISTRATOR;

		PenggunaModel pengguna = new PenggunaModel(nama, username, password, aktif, level);

		assertEquals(aktif, pengguna.getAktif());
	}

	@Test
	public void testGetLevel() {
		String nama = "Test Nama";
		String username = "Test Username";
		String password = "Test Password";
		boolean aktif = true;
		LevelEnum level = LevelEnum.ADMINISTRATOR;

		PenggunaModel pengguna = new PenggunaModel(nama, username, password, aktif, level);

		assertEquals(level, pengguna.getLevel());
	}

	@Test
	public void testGetDibuat() {
		int id = 1;
		String nama = "Test Nama";
		String username = "Test Username";
		String password = "Test Password";
		boolean aktif = true;
		LevelEnum level = LevelEnum.ADMINISTRATOR;
		Timestamp dibuat = new Timestamp(System.currentTimeMillis());
		Timestamp diubah = new Timestamp(System.currentTimeMillis());

		PenggunaModel pengguna = new PenggunaModel(id, nama, username, password, aktif, level, dibuat, diubah);

		assertEquals(dibuat, pengguna.getDibuat());
	}

	@Test
	public void testGetDiubah() {
		int id = 1;
		String nama = "Test Nama";
		String username = "Test Username";
		String password = "Test Password";
		boolean aktif = true;
		LevelEnum level = LevelEnum.ADMINISTRATOR;
		Timestamp dibuat = new Timestamp(System.currentTimeMillis());
		Timestamp diubah = new Timestamp(System.currentTimeMillis());

		PenggunaModel pengguna = new PenggunaModel(id, nama, username, password, aktif, level, dibuat, diubah);

		assertEquals(diubah, pengguna.getDiubah());
	}

	@Test
	public void testToString() {
		int id = 1;
		String nama = "Test Nama";
		String username = "Test Username";
		String password = "Test Password";
		boolean aktif = true;
		LevelEnum level = LevelEnum.ADMINISTRATOR;
		Timestamp dibuat = new Timestamp(System.currentTimeMillis());
		Timestamp diubah = new Timestamp(System.currentTimeMillis());

		PenggunaModel pengguna = new PenggunaModel(id, nama, username, password, aktif, level, dibuat, diubah);

		String expected = "PenggunaModel("
				+ "id=" + id + ", "
				+ "nama=" + nama + ", "
				+ "username=" + username + ", "
				+ "password=" + password + ", "
				+ "aktif=" + aktif + ", "
				+ "level=" + level + ", "
				+ "dibuat=" + dibuat + ", "
				+ "diubah=" + diubah
				+ ")";

		assertEquals(expected, pengguna.toString());
	}
}