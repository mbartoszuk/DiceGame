package dicegame;

import javax.swing.ImageIcon;

/**
 * @author Maria Bartoszuk, w1510769
 */

public class Die implements DieIntf, Comparable<DieIntf> {
    
    public static final int FACESNUMBER = 6;  // Each die has 6 faces
    public static final Die BLANKDIE = new Die();
    
    static {
        BLANKDIE.setValue(0);
        BLANKDIE.setDieImage(new ImageIcon(Die.class.getResource("/dieBlank.png")));
    }
    
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

    /** Comparison by die value. Lower value is less than higher value. */
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
