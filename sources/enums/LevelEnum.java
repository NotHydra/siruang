package enums;

public enum LevelEnum {
	OPERATOR("operator"), ADMINISTRATOR("administrator");

	public final String value;

	private LevelEnum(String value) {
		this.value = value;
	}

	public static LevelEnum valueToEnum(String value) {
		for (LevelEnum level : LevelEnum.values()) {
			if (level.value.equals(value)) {
				return level;
			}
		}

<<<<<<< HEAD
		throw new IllegalArgumentException("Nilai level tidak valid");
=======
		throw new IllegalArgumentException("Level tidak valid");
>>>>>>> db838e3b20a6e8e8092145de5f5b81dbe20f89b0
	}
}