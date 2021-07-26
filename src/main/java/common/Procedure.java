package common;

/**
 * Enum to specify procedure types used in event passing.
 * The values are meant to provide a description of the
 * procedure that is currently taking place.
 *
 * @author ebreojh
 */
public enum Procedure {
    COMMISSION("COMMISSION"),
    DECOMMISSION("DECOMMISSION"),
    CREATE("CREATE"),
    DELETE("DELETE"),
    LIST("LIST"),
    MODIFY("MODIFY"),
    ACKNOWLEDGE("ACKNOWLEDGE"),
    GET("GET");

    private final String desc;

    Procedure(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
