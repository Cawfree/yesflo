package io.github.cawfree.yesflo.elements;

/**
 * Created by Alexander Thomas (@Cawfree) on 16/04/17.
 */

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/** Asserts the definition of a Port interface for a Component. */
public class Param implements Serializable {

    /* Member Variables. */
    @JsonProperty("name")
    private final String mName;
    @JsonProperty("type")
    private final String mType;

    /** Constructor. */
    @JsonCreator
    public Param(@JsonProperty("name")final String pName, @JsonProperty("type")final String pType) {
        // Initialize Member Variables.
        this.mName = pName;
        this.mType = pType;
    }

    /* Getters. */
    public final String getName() { return this.mName; }
    public final String getType() { return this.mType; }

}