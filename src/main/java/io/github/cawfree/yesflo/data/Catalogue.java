package io.github.cawfree.yesflo.data;

/**
 * Created by Alexander Thomas (@Cawfree) on 30/03/17.
 */

import java.io.Serializable;
import java.util.Map;

import io.github.cawfree.yesflo.elements.Component;

/** Represents a Catalogue of Components. (This is where we represent and look up the 'core' implementation of Process calls.) */
public class Catalogue <T extends Component> implements Serializable {

    /* Member Variables. */
    private final Map<String, T> mLibrary;
    private       String         mName;

    /** Constructor. */
    public Catalogue(final Map<String, T> pLibrary, final String pName) {
        // Initialize Member Variables.
        this.mLibrary = pLibrary;
        this.mName    = pName;
    }

    /* Getters and Setters. */
    public final void setName(final String pName) {
        this.mName = pName;
    }

    public final String getName() {
        return this.mName;
    }

    public final Map<String, T> getLibrary() {
        return this.mLibrary;
    }

}
