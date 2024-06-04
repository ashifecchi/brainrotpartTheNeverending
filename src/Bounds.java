import java.awt.*;

public class Bounds extends Rectangle{
    private int topleftx;
    private int toplefty;
    private int bottomrightx;
    private int bottomrighty;
    public Bounds(int x, int y){
        super(x,y);
        topleftx = x;
        toplefty = y;
        bottomrightx = topleftx + this.width;
        bottomrighty = toplefty + this.height;
    }
    public boolean intersects(Rectangle other){
        if (other.intersects(this)){
            return false;
        }
        return true;
    }
}
