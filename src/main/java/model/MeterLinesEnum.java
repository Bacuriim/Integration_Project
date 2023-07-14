package model;

public enum MeterLinesEnum {

    CRONOS("Cronos"),
    ARES("Ares");

    private final String name;

    MeterLinesEnum(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
