package enums;

public enum StatusEnum {
	BELUMDIRESPON("belum direspon"), DITOLAK("ditolak"), DITERIMA("diterima");

	private final String value;

	private StatusEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static StatusEnum valueToEnum(String value) {
		for (StatusEnum level : StatusEnum.values()) {
			if (level.value.equals(value)) {
				return level;
			}
		}

		throw new IllegalArgumentException("Status tidak valid");
	}
}