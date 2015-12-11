package dicegame;

/**
 * @author Maria Bartoszuk, w1510769
 */

import javax.swing.ImageIcon;

/**
 * @author Maria
 */

public interface DieIntf {
    
    public ImageIcon getDieImage();
    
    public void setDieImage(ImageIcon dieImage);
    
    public void setValue(int v);
    
    public int getValue();
}
