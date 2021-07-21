package common;

public enum Procedure {
    COMMISSION("COMMISSION"),
    DECOMMISSION("DECOMMISSION"),
    CREATE("CREATE"),
    DELETE("DELETE"),
    LIST("LIST"),
    MODIFY("MODIFY"),
    ACKNOWLEDGE("ACKNOWLEDGE");

    private String desc;

    Procedure(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
