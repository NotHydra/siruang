package models.contoh.genre;


import global.base.BaseModel;

public class GenreModel extends BaseModel {
    private final String name;

    public GenreModel(String name) {
        super(-1);

        validate(name);

        this.name = name;
    }

    public GenreModel(int id, String name) {
        super(id);

        validate(name);

        this.name = name;
    }

    private void validate(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "GenreModel("
                + "id=" + this.id + ", "
                + "name=" + this.getName()
                + ")";
    }
}
