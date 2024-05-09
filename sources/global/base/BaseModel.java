package global.base;

public abstract class BaseModel {
    protected final int id;

    protected BaseModel() {
        this.id = -1;
    }

    protected BaseModel(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public abstract String toString();
}
