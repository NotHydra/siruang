package models.contoh.book;


import global.base.BaseModel;

public class BookModel extends BaseModel {
    private final String title;
    private final String description;

    public BookModel(String title, String description) {
        super(-1);

        validate(title, description);

        this.title = title;
        this.description = description;
    }

    public BookModel(int id, String title, String description) {
        super(id);

        validate(title, description);

        this.title = title;
        this.description = description;
    }

    private void validate(
            String title,
            String description) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }

        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }

    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "BookModel("
                + "id=" + this.id + ", "
                + "title=" + this.title + ", "
                + "description=" + this.description
                + ")";
    }
}
