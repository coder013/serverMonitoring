package enums;

public enum TableEnum {

    Data("data_");

    private final String name;

    TableEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
