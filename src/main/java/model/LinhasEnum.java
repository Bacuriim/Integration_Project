package model;

public enum LinhasEnum {

    CRONOS("Cronos"),
    ARES("Ares");
    private final String name;

    LinhasEnum(String name) {
        this.name = name;
    }
    @Override

    public String toString(){

        return name;
    }
}
