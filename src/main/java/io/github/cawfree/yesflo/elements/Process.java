package io.github.cawfree.yesflo.elements;

/**
 * Created by cawfree on 26/03/17.
 */

/** Defines a Process (Diagram Functional Block). */
public class Process<U extends Component> {
    /* Member Variables. */
    /** TODO: Component should refer to a library type. */
    private final U      mComponent;
    private final int    mX;
    private final int    mY;
    private final int    mWidth;
    private final int    mHeight;
    private final String mLabel; // A user-friendly name written on the diagram.
    /** Constructor. */
    public Process(final U pComponent, final int pX, final int pY, final int pWidth, final int pHeight, final String pLabel) {
        // Initialize Member Variables.
        this.mComponent = pComponent;
        this.mX         = pX;
        this.mY         = pY;
        this.mWidth     = pWidth;
        this.mHeight    = pHeight;
        this.mLabel     = pLabel;
    }
    /* Getters. */
    public final U      getComponent() { return this.mComponent; }
    public final int    getX()         { return this.mX;         }
    public final int    getY()         { return this.mY;         }
    public final int    getWidth()     { return this.mWidth;     }
    public final int    getHeight()    { return this.mHeight;    }
    public final String getLabel()     { return this.mLabel;     }
}