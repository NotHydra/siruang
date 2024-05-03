package models.contoh.book;


import java.sql.ResultSet;

import interfaces.ServiceFindInterface;
import interfaces.ServiceAddInterface;
import interfaces.ServiceChangeInterface;
import interfaces.ServiceChoiceBoxInterface;
import interfaces.ServiceFindDetailedInterface;

import providers.Logger;
import providers.Database;

import global.choice_box.ChoiceBoxModel;
import global.detailed.DetailedService;

import models.contoh.genre.GenreModel;

public class BookService
        extends DetailedService<BookModel, BookDetailedModel>
        implements ServiceFindInterface<BookModel>, ServiceFindDetailedInterface<BookDetailedModel>, ServiceChoiceBoxInterface, ServiceAddInterface<BookModel>, ServiceChangeInterface<BookModel> {
    private static BookService instance;

    private BookService(Logger logger, Database database, String table) {
        super(logger, database, table);
    }

    public static BookService getInstance() {
        if (BookService.instance == null) {
            try {
                BookService.instance = new BookService(
                        new Logger(BookService.class.getName()),
                        Database.getInstance(),
                        "book");
            }
            catch (Exception e) {
                BookService.instance.logger.error("Failed to initialize BookService instance: " + e.getMessage());

                throw new RuntimeException("Failed to initialize BookService instance");
            }
        }

        BookService.instance.logger.debug("Get Instance");

        return BookService.instance;
    }

    @Override
    public BookModel[] find() {
        this.logger.debug("Find");

        try {
            final int total = this.database.tableTotal(this.table);
            final ResultSet result = this.database.executeQuery(""
                    + "SELECT "
                    + "id, "
                    + "title, "
                    + "description "
                    + "FROM " + this.table
                    + ";");

            final BookModel[] models = new BookModel[total];

            int i = 0;
            while (result.next()) {
                models[i] = new BookModel(
                        result.getInt("id"),
                        result.getString("title"),
                        result.getString("description"));

                i++;
            }

            return models;
        }
        catch (Exception e) {
            this.logger.error("Failed to find: " + e.getMessage());
        }

        return null;
    }

    @Override
    public BookModel findId(int id) {
        this.logger.debug("Find Id");

        try {
            final ResultSet result = this.database.executeQuery(""
                    + "SELECT "
                    + "id, "
                    + "title, "
                    + "description "
                    + "FROM " + this.table + " "
                    + "WHERE id=" + id
                    + ";");

            if (result.next()) {
                return new BookModel(
                        result.getInt("id"),
                        result.getString("title"),
                        result.getString("description"));
            }
        }
        catch (Exception e) {
            this.logger.error("Failed to find id: " + e.getMessage());
        }

        return null;
    }

    @Override
    public BookDetailedModel[] findDetailed() {
        this.logger.debug("Find Detailed");

        try {
            final int total = this.database.tableTotal(this.table);
            final ResultSet result = this.database.executeQuery(""
                    + "SELECT "
                    + "id, "
                    + "title, "
                    + "description "
                    + "FROM " + this.table
                    + ";");

            final BookDetailedModel[] models = new BookDetailedModel[total];

            int i = 0;
            while (result.next()) {
                final int bookGenreTotal = this.database.tableTotal("book_genre", "id_book=" + result.getInt("id"));
                final ResultSet bookGenreResult = this.database.executeQuery(""
                        + "SELECT "
                        + "genre.id, "
                        + "genre.name "
                        + "FROM book_genre "
                        + "INNER JOIN genre ON book_genre.id_genre=genre.id "
                        + "WHERE book_genre.id_book=" + result.getInt("id")
                        + ";");

                final GenreModel[] genres = new GenreModel[bookGenreTotal];

                int genreIndex = 0;
                while (bookGenreResult.next()) {
                    genres[genreIndex] = new GenreModel(
                            bookGenreResult.getInt("genre.id"),
                            bookGenreResult.getString("genre.name"));

                    genreIndex++;
                }

                models[i] = new BookDetailedModel(
                        result.getInt("id"),
                        result.getString("title"),
                        result.getString("description"),
                        genres);

                i++;
            }

            return models;
        }
        catch (Exception e) {
            this.logger.error("Failed to find detailed: " + e.getMessage());
        }

        return null;
    }

    @Override
    public BookDetailedModel findIdDetailed(int id) {
        this.logger.debug("Find Id Detailed");

        try {
            final ResultSet result = this.database.executeQuery(""
                    + "SELECT "
                    + "id, "
                    + "title, "
                    + "description "
                    + "FROM " + this.table + " "
                    + "WHERE id=" + id
                    + ";");

            if (result.next()) {
                final int bookGenreTotal = this.database.tableTotal("book_genre", "id_book=" + result.getInt("id"));
                final ResultSet bookGenreResult = this.database.executeQuery(""
                        + "SELECT "
                        + "genre.id, "
                        + "genre.name "
                        + "FROM book_genre "
                        + "INNER JOIN genre ON book_genre.id_genre=genre.id "
                        + "WHERE book_genre.id_book=" + result.getInt("id")
                        + ";");

                final GenreModel[] genres = new GenreModel[bookGenreTotal];

                int genreIndex = 0;
                while (bookGenreResult.next()) {
                    genres[genreIndex] = new GenreModel(
                            bookGenreResult.getInt("genre.id"),
                            bookGenreResult.getString("genre.name"));

                    genreIndex++;
                }

                return new BookDetailedModel(
                        result.getInt("id"),
                        result.getString("title"),
                        result.getString("description"),
                        genres);
            }
        }
        catch (Exception e) {
            this.logger.error("Failed to find id detailed: " + e.getMessage());
        }

        return null;
    }

    @Override
    public ChoiceBoxModel[] findChoiceBox() {
        this.logger.debug("Find Choice Box");

        try {
            final int total = this.database.tableTotal(this.table);
            final ResultSet result = this.database.executeQuery(""
                    + "SELECT "
                    + "id, "
                    + "title, "
                    + "description "
                    + "FROM " + this.table
                    + ";");

            final ChoiceBoxModel[] models = new ChoiceBoxModel[total + 1];

            models[0] = new ChoiceBoxModel("Select Book");

            int i = 1;
            while (result.next()) {
                models[i] = new ChoiceBoxModel(
                        result.getInt("id"),
                        result.getString("title") + " - " + result.getString("description"));

                i++;
            }

            return models;
        }
        catch (Exception e) {
            this.logger.error("Failed to find choice box: " + e.getMessage());
        }

        return null;
    }

    @Override
    public void add(BookModel model) {
        this.logger.debug("Add");

        try {
            this.database.executeUpdate(""
                    + "INSERT INTO " + this.table + " ("
                    + "title, "
                    + "description"
                    + ") VALUES ("
                    + "'" + model.getTitle() + "', "
                    + "'" + model.getDescription() + "'"
                    + ");");
        }
        catch (Exception e) {
            this.logger.error("Failed to add: " + e.getMessage());
        }
    }

    @Override
    public void change(int id, BookModel model) {
        this.logger.debug("Change");

        try {
            this.database.executeUpdate(""
                    + "UPDATE " + this.table + " SET "
                    + "title='" + model.getTitle() + "', "
                    + "description='" + model.getDescription() + "' "
                    + "WHERE "
                    + "id=" + id
                    + ";");
        }
        catch (Exception e) {
            this.logger.error("Failed to change: " + e.getMessage());
        }
    }

    public void addGenre(int idBook, int idGenre) {
        this.logger.debug("Add Genre");

        try {
            this.database.executeUpdate(""
                    + "INSERT INTO book_genre ("
                    + "id_book, "
                    + "id_genre"
                    + ") VALUES ("
                    + idBook + ", "
                    + idGenre
                    + ");");
        }
        catch (Exception e) {
            this.logger.error("Failed to add book genre: " + e.getMessage());
        }
    }

    public void removeGenre(int idBook, int idGenre) {
        this.logger.debug("Remove Book Genre");

        try {
            this.database.executeUpdate(""
                    + "DELETE FROM book_genre "
                    + "WHERE "
                    + "id_book=" + idBook + " AND "
                    + "id_genre=" + idGenre
                    + ";");
        }
        catch (Exception e) {
            this.logger.error("Failed to remove book genre: " + e.getMessage());
        }
    }
}
