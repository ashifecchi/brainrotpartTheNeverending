import java.awt.*;

public class InteractableObject extends Rectangle{
    private String name;
    private Rectangle area;
    public InteractableObject(String name, Rectangle rect){
        this.name = name;
        area = rect;
    }

    public String getName() {
        return name;
    }
}