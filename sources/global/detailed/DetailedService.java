package global.detailed;


import providers.Logger;
import providers.Database;

import global.base.BaseModel;
import global.base.BaseService;

public abstract class DetailedService<ModelType extends BaseModel, ModelDetailedType extends ModelType> extends BaseService<ModelType> {
    protected DetailedService(Logger logger, Database database, String table) {
        super(logger, database, table);
    }

    public void displayDetailed() {
        this.logger.debug("Display Detailed");

        try {
            for (ModelDetailedType model : this.findDetailed()) {
                System.out.println(model);
                System.out.println();
            }
        }
        catch (Exception e) {
            this.logger.error("Failed to display detailed: " + e.getMessage());
        }
    }

    public abstract ModelDetailedType[] findDetailed();

    public abstract ModelDetailedType findIdDetailed(int id);
}
