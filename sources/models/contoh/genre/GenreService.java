package models.contoh.genre;


import java.sql.ResultSet;

import interfaces.ServiceFindInterface;
import interfaces.ServiceAddInterface;
import interfaces.ServiceChangeInterface;
import interfaces.ServiceChoiceBoxInterface;

import providers.Logger;
import providers.Database;

import global.base.BaseService;
import global.choice_box.ChoiceBoxModel;

public class GenreService
        extends BaseService<GenreModel>
        implements ServiceFindInterface<GenreModel>, ServiceAddInterface<GenreModel>, ServiceChangeInterface<GenreModel>, ServiceChoiceBoxInterface {
    private static GenreService instance;

    private GenreService(Logger logger, Database database, String table) {
        super(logger, database, table);
    }

    public static GenreService getInstance() {
        if (GenreService.instance == null) {
            try {
                GenreService.instance = new GenreService(
                        new Logger(GenreService.class.getName()),
                        Database.getInstance(),
                        "genre");
            }
            catch (Exception e) {
                GenreService.instance.logger.error("Failed to initialize GenreService instance: " + e.getMessage());

                throw new RuntimeException("Failed to initialize GenreService instance");
            }
        }

        GenreService.instance.logger.debug("Get Instance");

        return GenreService.instance;
    }

    @Override
    public GenreModel[] find() {
        this.logger.debug("Find");

        try {
            final int total = this.database.tableTotal(this.table);
            final ResultSet result = this.database.executeQuery(""
                    + "SELECT "
                    + "id, "
                    + "name "
                    + "FROM " + this.table
                    + ";");

            final GenreModel[] models = new GenreModel[total];

            int i = 0;
            while (result.next()) {
                models[i] = new GenreModel(
                        result.getInt("id"),
                        result.getString("name"));

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
    public GenreModel findId(int id) {
        this.logger.debug("Find Id");

        try {
            final ResultSet result = this.database.executeQuery(""
                    + "SELECT "
                    + "id, "
                    + "name "
                    + "FROM " + this.table + " "
                    + "WHERE id=" + id
                    + ";");

            if (result.next()) {
                return new GenreModel(
                        result.getInt("id"),
                        result.getString("name"));
            }
        }
        catch (Exception e) {
            this.logger.error("Failed to find id: " + e.getMessage());
        }

        return null;
    }

    @Override
    public void add(GenreModel model) {
        this.logger.debug("Add");

        try {
            this.database.executeUpdate(""
                    + "INSERT INTO " + this.table + " ("
                    + "name"
                    + ") VALUES ("
                    + "'" + model.getName() + "'"
                    + ");");
        }
        catch (Exception e) {
            this.logger.error("Failed to add: " + e.getMessage());
        }
    }

    @Override
    public void change(int id, GenreModel model) {
        this.logger.debug("Change");

        try {
            this.database.executeUpdate(""
                    + "UPDATE " + this.table + " SET "
                    + "name='" + model.getName() + "' "
                    + "WHERE "
                    + "id=" + id
                    + ";");
        }
        catch (Exception e) {
            this.logger.error("Failed to change: " + e.getMessage());
        }
    }

    @Override
    public ChoiceBoxModel[] findChoiceBox() {
        this.logger.debug("Find Choice Box");

        try {
            final int total = this.database.tableTotal(this.table);
            final ResultSet result = this.database.executeQuery(""
                    + "SELECT "
                    + "id, "
                    + "name "
                    + "FROM " + this.table
                    + ";");

            final ChoiceBoxModel[] models = new ChoiceBoxModel[total + 1];

            models[0] = new ChoiceBoxModel("Select Genre");

            int i = 1;
            while (result.next()) {
                models[i] = new ChoiceBoxModel(
                        result.getInt("id"),
                        result.getString("name"));

                i++;
            }

            return models;
        }
        catch (Exception e) {
            this.logger.error("Failed to find choice box: " + e.getMessage());
        }

        return null;
    }
}
