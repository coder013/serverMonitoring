package enums;

public enum TableEnum {

    AGENT_LOG("agent_log_");

    private final String name;

    TableEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
