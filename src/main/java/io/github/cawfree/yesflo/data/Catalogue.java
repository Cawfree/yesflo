package io.github.cawfree.yesflo.data;

/**
 * Created by Alexander Thomas (@Cawfree) on 30/03/17.
 */

import java.util.Map;

import io.github.cawfree.yesflo.elements.Component;

/** Represents a Catalogue of Components. (This is where we represent and look up the 'core' implementation of Process calls.) */
public class Catalogue <T extends Component> {

    /* Member Variables. */
    private final Map<String, T> mLibrary;

    /** Constructor. */
    public Catalogue(final Map<String, T> pLibrary) {
        // Initialize Member Variables.
        this.mLibrary = pLibrary;
    }

    /* Getters. */
    public final Map<String, T> getLibrary() {
        return this.mLibrary;
    }

}
