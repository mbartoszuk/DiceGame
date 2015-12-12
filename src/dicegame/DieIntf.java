package dicegame;

import javax.swing.ImageIcon;

/**
 * @author Maria Bartoszuk, w1510769
 */

public interface DieIntf {

    /** Image of this die face for displaying in the interface. */
    public ImageIcon getDieImage();
    
    /** Associates this die face with given image. */
    public void setDieImage(ImageIcon dieImage);
    
    /** Gives this die face certain value used in score computing. */
    public void setValue(int v);
    
    /** Returns the value of this die face for computing the score. */
    public int getValue();
}
