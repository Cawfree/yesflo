package io.github.cawfree.yesflo.components;

/**
 * Created by cawfree on 26/03/17.
 */

import io.github.cawfree.yesflo.components.Process;

/** Define a Terminal. (This is information regarding Process-centric I/O.) */
public class Terminal {
    /* Member Variables. */
    private final Process mProcess;
    private final String  mPort;
    /** Constructor. */
    public Terminal(final Process pProcess, final String pPort) {
        // Initialize Member Variables.
        this.mProcess = pProcess;
        this.mPort    = pPort;
    }
    /* Getters. */
    public final Process getProcess() { return this.mProcess; }
    public final String  getPort()    { return this.mPort;    }
}