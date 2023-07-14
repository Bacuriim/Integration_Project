package model;

public enum ModelosEnum {

    CRONOS_6001A(CategoriasEnum.CRONOS_OLD,"Cronos 6001-A"),
    CRONOS_6003(CategoriasEnum.CRONOS_OLD,"Cronos 6003"),
    CRONOS_7023(CategoriasEnum.CRONOS_OLD,"Cronos 7023"),
    CRONOS_6021L(CategoriasEnum.CRONOS_L,"Cronos 6021L"),
    CRONOS_7023L(CategoriasEnum.CRONOS_L,"Cronos 7023L"),
    CRONOS_6001NG(CategoriasEnum.CRONOS_NG,"Cronos 6001-NG"),
    CRONOS_6003NG(CategoriasEnum.CRONOS_NG, "Cronos 6003-NG"),
    CRONOS_6021NG(CategoriasEnum.CRONOS_NG, "Cronos 6021-NG"),
    CRONOS_6031NG(CategoriasEnum.CRONOS_NG, "Cronos 6031-NG"),
    CRONOS_7021NG(CategoriasEnum.CRONOS_NG, "Cronos 7021-NG"),
    CRONOS_7023NG(CategoriasEnum.CRONOS_NG, "Cronos 7023-NG"),
    ARES_7021(CategoriasEnum.ARES_TB,"Ares 7021"),
    ARES_7031(CategoriasEnum.ARES_TB,"Ares 7031"),
    ARES_7023(CategoriasEnum.ARES_TB,"Ares 7023"),
    ARES_8023_15(CategoriasEnum.ARES_THS, "Ares 8023 15"),
    ARES_8023_200(CategoriasEnum.ARES_THS, "Ares 8023 200"),
    ARES_8023_25(CategoriasEnum.ARES_THS, "Ares 8023 2,5");

        private final String Modelos;

        ModelosEnum(CategoriasEnum cronosNg, String Modelos){
            this.Modelos = Modelos;

        }

    @Override

        public String toString(){

        return Modelos;

        }
}
