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

		throw new IllegalArgumentException("Level tidak valid");
	}
}