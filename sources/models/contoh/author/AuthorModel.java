package models.contoh.author;


import global.base.BaseModel;

public class AuthorModel extends BaseModel {
    private final String name;
    private final int idBook;

    public AuthorModel(String name, int idBook) {
        super(-1);

        validate(name, idBook);

        this.name = name;
        this.idBook = idBook;
    }

    public AuthorModel(int id, String name, int idBook) {
        super(id);

        validate(name, idBook);

        this.name = name;
        this.idBook = idBook;
    }

    private void validate(String name, int idBook) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        if (idBook <= 0) {
            throw new IllegalArgumentException("IdBook cannot be less than or equal to 0");
        }
    }

    public String getName() {
        return this.name;
    }

    public int getIdBook() {
        return this.idBook;
    }

    @Override
    public String toString() {
        return "AuthorModel("
                + "id=" + this.id + ", "
                + "name=" + this.name + ", "
                + "idBook=" + this.idBook
                + ")";
    }
}
