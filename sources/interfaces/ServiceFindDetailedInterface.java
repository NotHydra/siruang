package interfaces;

public interface ServiceFindDetailedInterface<ModelDetailedType> {
    public ModelDetailedType[] findDetailed();

    public ModelDetailedType findIdDetailed(int id);
}
