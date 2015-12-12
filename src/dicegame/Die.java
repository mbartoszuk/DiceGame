package dicegame;

import javax.swing.ImageIcon;

/**
 * @author Maria Bartoszuk, w1510769
 */

public class Die implements DieIntf, Comparable<DieIntf> {
    
    ImageIcon dieFace;
    int dieValue;

    @Override
    public ImageIcon getDieImage() {
        return dieFace;
    }

    @Override
    public void setDieImage(ImageIcon dieImage) {
        this.dieFace = dieImage;
    }

    @Override
    public void setValue(int v) {
        this.dieValue = v;
    }

    @Override
    public int getValue() {
        return dieValue;
    }

    @Override
    public int compareTo(DieIntf other) {
        if (this.getValue() < other.getValue()) {
            return -1;
        } else if (this.getValue() == other.getValue()) {
            return 0;
        } else { //this.getValue() > other.getValue()
            return 1;
        }
    }
    
}
