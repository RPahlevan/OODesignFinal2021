package common;

/**
 * The Vendor enum stores the various vendors that RUs can be
 * associated with.
 *
 * @author ebreojh
 */
public enum Vendor {
    ERICSSON("Ericsson"),
    NOKIA("Nokia");

    private final String label;

    Vendor(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
