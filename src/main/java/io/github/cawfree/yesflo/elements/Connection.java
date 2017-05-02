package io.github.cawfree.yesflo.elements;

/**
 * Created by @Cawfree on 26/03/17.
 */

/** Defines a base Connection; a means of propagating data to a particular Target. */
public abstract class Connection {

    /** Defines a SourceConnection; a route of data from one called Process to another. */
    public static class Source extends Connection {
        /* Member Variables. */
        private final Terminal mSource;
        /** Constructor. */
        public Source(final Terminal pSource, final Terminal pTarget, final int pRoute) { /** TODO: Watch for order of calls! */
            // Initialize the Parent.
            super(pTarget, pRoute);
            // Initialize Member Variables.
            this.mSource = pSource;
        }
        /* Getters. */
        public final Terminal getSource() { return this.mSource; }
    }

    /** Defines a DataConnection; a direct means of initializing a called Target Process with constant data. */
    public static class Data extends Connection {
        /* Member Variables. */
        private final String mData;
        /** Constructor. */
        public Data(final Terminal pTarget, final String pData, final int pRoute) {
            // Initialize the Parent.
            super(pTarget, pRoute);
            // Initialize Member Variables.
            this.mData = pData;
        }
        /* Getters. */
        public final String getData() { return this.mData; }
    }

    /* Member Variables. */
    private final Terminal mTarget;
    private final int      mRoute;

    /** Constructor. */
    public Connection(final Terminal pTerminal, final int pRoute) {
        // Initialize Member Variables.
        this.mTarget = pTerminal;
        this.mRoute  = pRoute;
    }
    /* Getters. */
    public final Terminal getTarget() { return this.mTarget; }
    public final int      getRoute()  { return this.mRoute;  }
}