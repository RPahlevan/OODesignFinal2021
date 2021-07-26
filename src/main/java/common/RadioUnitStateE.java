package common;

public enum RadioUnitStateE {
	ACTIVATED("ACTIVATED"),
	DEACTIVATED("DEACTIVATED"),
	IDLE("IDLE");
	
	private final String ruState;
	
	RadioUnitStateE(String state)
	{
		this.ruState = state;
	}
    /**
     * Returns the String representation of the Radio Unit State.
     *
     * @return The Radio Unit State information, as a String.
     */
    public String getRuState() {
        return ruState;
    }
}
