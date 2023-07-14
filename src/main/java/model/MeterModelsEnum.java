package model;

public enum MeterModelsEnum {

    CRONOS_6001A("Cronos 6001-A"),
    CRONOS_6003("Cronos 6003"),
    CRONOS_7023("Cronos 7023"),
    CRONOS_6021L("Cronos 6021L"),
    CRONOS_7023L("Cronos 7023L"),
    CRONOS_6001NG("Cronos 6001-NG"),
    CRONOS_6003NG("Cronos 6003-NG"),
    CRONOS_6021NG("Cronos 6021-NG"),
    CRONOS_6031NG("Cronos 6031-NG"),
    CRONOS_7021NG("Cronos 7021-NG"),
    CRONOS_7023NG("Cronos 7023-NG"),
    ARES_7021("Ares 7021"),
    ARES_7031("Ares 7031"),
    ARES_7023("Ares 7023"),
    ARES_8023_15("Ares 8023 15"),
    ARES_8023_200("Ares 8023 200"),
    ARES_8023_25("Ares 8023 2,5");

    private final String name;

    MeterModelsEnum(String name) {
        this.name = name;

    }

    @Override
    public String toString() {
            return name;
    }
}
