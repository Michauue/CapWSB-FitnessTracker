package com.capgemini.wsb.fitnesstracker.training.internal;

// TODO : JavaDoc
/**
 * Typ aktywności sportowej.
 */
public enum ActivityType {

    RUNNING("Running"), // Bieganie
    CYCLING("Cycling"), // Kolarstwo
    WALKING("Walking"), // Spacer
    SWIMMING("Swimming"), // Pływanie
    TENNIS("Tennis"); // Tenis

    private final String displayName;

    /**
     * Tworzy nowy typ aktywności.
     *
     * @param displayName Wyświetlana nazwa typu aktywności.
     */
    ActivityType(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Pobiera wyświetlaną nazwę typu aktywności.
     *
     * @return Wyświetlana nazwa typu aktywności.
     */
    public String getDisplayName() {
        return displayName;
    }

}
