package models.authentication;


import java.sql.ResultSet;
import java.util.HashMap;

import components.Modal;
import enums.LevelEnum;
import providers.Logger;
import providers.View;
import providers.Database;

public class LoginService {
	private static LoginService instance;

	private Logger logger;
	private Database database;
	private HashMap<String, Object> session = new HashMap<String, Object>();

	private LoginService(Logger logger, Database database) {
		this.logger = logger;
		this.database = database;
	}

	public static LoginService getInstance() {
		if (LoginService.instance == null) {
			try {
				LoginService.instance = new LoginService(
						new Logger(LoginService.class.getName()),
						Database.getInstance());

				LoginService.instance.session.put("id", null);
				LoginService.instance.session.put("nama", null);
				LoginService.instance.session.put("username", null);
				LoginService.instance.session.put("aktif", null);
				LoginService.instance.session.put("level", null);
				LoginService.instance.session.put("dibuat", null);
				LoginService.instance.session.put("diubah", null);
			}
			catch (Exception e) {
				LoginService.instance.logger.error("Failed to initialize LoginService instance: " + e.getMessage());

				throw new RuntimeException("Failed to initialize LoginService instance");
			}
		}

		LoginService.instance.logger.debug("Get Instance");

		return LoginService.instance;
	}

	public boolean login(String username, String password) {
		this.logger.debug("Login");

		try {
			final ResultSet result = this.database.executeQuery(""
					+ "SELECT "
					+ "id "
					+ "FROM pengguna "
					+ "WHERE "
					+ "username='" + username + "' "
					+ "AND password='" + password + "' "
					+ "AND aktif=1"
					+ ";");

			if (result.next()) {
				this.session.put("id", result.getInt("id"));

				this.getProfile();

				return true;
			}
			else {
				throw new RuntimeException("Username atau Password tidak valid");
			}
		}
		catch (RuntimeException e) {
			this.logger.error("Failed to login: " + e.getMessage());

			throw e;
		}
		catch (Exception e) {
			this.logger.error("Failed to login: " + e.getMessage());
		}

		return false;
	}

	public void logout() {
		this.logger.debug("Logout");

		this.session.put("id", null);
		this.session.put("nama", null);
		this.session.put("username", null);
		this.session.put("aktif", null);
		this.session.put("level", null);
		this.session.put("dibuat", null);
		this.session.put("diubah", null);

		View.getInstance().set("login");
	}

	public HashMap<String, Object> getSession() {
		this.logger.debug("Get Session");

		return this.session;

	}

	public void getProfile() {
		this.logger.debug("Get Profile");

		try {
			final ResultSet result = this.database.executeQuery(""
					+ "SELECT "
					+ "nama, "
					+ "username, "
					+ "aktif, "
					+ "level, "
					+ "dibuat, "
					+ "diubah "
					+ "FROM pengguna "
					+ "WHERE "
					+ "id=" + this.session.get("id") + " "
					+ "AND aktif=1"
					+ ";");

			if (result.next()) {
				this.session.put("nama", result.getString("nama"));
				this.session.put("username", result.getString("username"));
				this.session.put("aktif", result.getBoolean("aktif"));
				this.session.put("level", LevelEnum.valueToEnum(result.getString("level")));
				this.session.put("dibuat", result.getTimestamp("dibuat"));
				this.session.put("diubah", result.getTimestamp("diubah"));
			}
			else {
				Modal.getInstance().fail("Pengguna tidak ditemukan");

				this.logout();
			}
		}
		catch (Exception e) {
			this.logger.error("Failed to get profile: " + e.getMessage());
		}
	}
}
