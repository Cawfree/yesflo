package io.github.cawfree.yesflo.elements;

/**
 * Created by cawfree on 26/03/17.
 */

/** Defines a Parameter; these are the I/O mechanisms for the Diagram. */
public class Port {

    /* Member Variables. */
    private final Terminal mTerminal;
    private final int      mX;
    private final int      mY;
    private final int      mWidth;
    private final int      mHeight;
    /** Constructor. */
    public Port(final Terminal pTerminal, final int pX, final int pY, final int pWidth, final int pHeight) {
        // Initialize Member Variables.
        this.mTerminal = pTerminal;
        this.mX        = pX;
        this.mY        = pY;
        this.mWidth    = pWidth;
        this.mHeight   = pHeight;
    }
    /* Getters. */
    public final Terminal getTerminal() { return this.mTerminal; }
    public final int      getX()        { return this.mX;        }
    public final int      getY()        { return this.mY;        }
    public final int      getWidth()    { return this.mWidth;    }
    public final int      getHeight()   { return this.mHeight;   }
}