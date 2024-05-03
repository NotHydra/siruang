package enums;

public enum StatusEnum {
	BELUMDIRESPON("belum direspon"), DITOLAK("ditolak"), DITERIMA("diterima");

	public final String value;

	private StatusEnum(String value) {
		this.value = value;
	}

	public static StatusEnum valueToEnum(String value) {
		for (StatusEnum level : StatusEnum.values()) {
			if (level.value.equals(value)) {
				return level;
			}
		}

<<<<<<< HEAD
		throw new IllegalArgumentException("Nilai status tidak valid");
=======
		throw new IllegalArgumentException("Status tidak valid");
>>>>>>> db838e3b20a6e8e8092145de5f5b81dbe20f89b0
	}
}