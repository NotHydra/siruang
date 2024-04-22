package global.choice_box;

public class ChoiceBoxModel {
	private final int id;
	private final String text;

	public ChoiceBoxModel(String text) {
		this.id = -1;
		this.text = text;
	}

	public ChoiceBoxModel(int id, String text) {
		this.id = id;
		this.text = text;
	}

	public int getId() {
		return this.id;
	}

	public String getText() {
		return this.text;
	}

	@Override
	public String toString() {
		return this.text;
	}
}
