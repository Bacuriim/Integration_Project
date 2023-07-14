package model;

public enum CategoriasEnum{
    CRONOS_OLD(LinhasEnum.CRONOS,"Cronos Old"),
    CRONOS_L(LinhasEnum.CRONOS,"Cronos L"),
    CRONOS_NG(LinhasEnum.CRONOS,"Cronos-NG"),
    ARES_TB(LinhasEnum.ARES,"Ares TB"),
    ARES_THS(LinhasEnum.ARES,"Ares THS");
    private final String Category;

    CategoriasEnum(LinhasEnum cronos, String Category) {

        this.Category = Category;
    }

    @Override

    public String toString(){

        return Category;
    }
}
