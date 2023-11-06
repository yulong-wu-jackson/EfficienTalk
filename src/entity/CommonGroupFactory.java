package entity;

public class CommonGroupFactory implements GroupFactory{
    @Override
    public Group create(String groupName) {
        return new CommonGroup(groupName);
    }
}
