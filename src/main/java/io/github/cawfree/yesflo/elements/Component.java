package io.github.cawfree.yesflo.elements;

/**
 * Created by Alexander Thomas (@Cawfree) on 30/03/17.
 */

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/** This represents a 'core' implementation of a Process; this is the type we refer to when defining Process calls. */
public class Component <T extends Param> {

    /* Member Variables. */
    @JsonProperty("name")
    private final String  mName;
    @JsonProperty("description")
    private final String  mDescription;
    @JsonProperty("icon")
    private final String  mIcon;
    @JsonProperty("inports")
    private final List<T> mInports;
    @JsonProperty("outports")
    private final List<T> mOutports;

    /** Constructor. */
    @JsonCreator
    public Component(@JsonProperty("name")final String pName, @JsonProperty("description")final String pDescription, @JsonProperty("icon")final String pIcon, @JsonProperty("inports")final List<T> pInports, @JsonProperty("outports")final List<T> pOutports) {
        // Initialize Member Variables.
        this.mName        = pName;
        this.mDescription = pDescription;
        this.mIcon        = pIcon;
        this.mInports     = pInports;
        this.mOutports    = pOutports;
    }

    /* Getters. */
    public final String getName() {
        return this.mName;
    }

    public final String getDescription() {
        return this.mDescription;
    }

    public final String getIcon() {
        return this.mIcon;
    }

    public final List<T> getInports() {
        return this.mInports;
    }

    public final List<T> getOutports() {
        return this.mOutports;
    }

}
