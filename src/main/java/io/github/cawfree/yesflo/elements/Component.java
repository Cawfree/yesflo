package io.github.cawfree.yesflo.elements;

/**
 * Created by Alexander Thomas (@Cawfree) on 30/03/17.
 */

import java.util.List;

/** This represents a 'core' implementation of a Process; this is the type we refer to when defining Process calls. */
public class Component {

    /** An abstract Port implementation, which declares the Name and Data Type for a Component's Port. */
    public static final class Port {
        /* Member Variables. */
        private final String mName;
        private final String mType;
        /** Constructor. */
        public Port(final String pName, final String pType) {
            // Initialize Member Variables.
            this.mName = pName;
            this.mType = pType;
        }
        /* Getters. */
        public final String getName() { return this.mName; }
        public final String getType() { return this.mType; }
    }

    /* Member Variables. */
    private final String               mName;
    private final String               mDescription;
    private final String               mIcon;
    private final List<Component.Port> mInports;
    private final List<Component.Port> mOutports;

    /** Constructor. */
    public Component(final String pName, final String pDescription, final String pIcon, final List<Component.Port> pInports, final List<Component.Port> pOutports) {
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

    public final List<Component.Port> getInports() {
        return this.mInports;
    }

    public final List<Component.Port> getOutports() {
        return this.mOutports;
    }

}
