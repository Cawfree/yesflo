package io.github.cawfree.yesflo.components;

/**
 * Created by cawfree on 26/03/17.
 */

/** Defines a Process (Diagram Functional Block). */
public class Process {
    /* Member Variables. */
    private final String mComponent; // The reference to the 'virtual' algorithmic implementation of this graphical element.
    private final int    mX;
    private final int    mY;
    private final int    mWidth;
    private final int    mHeight;
    private final String mLabel; // A user-friendly name written on the diagram.
    /** Constructor. */
    public Process(final String pComponent, final int pX, final int pY, final int pWidth, final int pHeight, final String pLabel) {
        // Initialize Member Variables.
        this.mComponent = pComponent;
        this.mX         = pX;
        this.mY         = pY;
        this.mWidth     = pWidth;
        this.mHeight    = pHeight;
        this.mLabel     = pLabel;
    }
    /* Getters. */
    public final String getComponent() { return this.mComponent; }
    public final int    getX()         { return this.mX;         }
    public final int    getY()         { return this.mY;         }
    public final int    getWidth()     { return this.mWidth;     }
    public final int    getHeight()    { return this.mHeight;    }
    public final String getLabel()     { return this.mLabel;     }
}