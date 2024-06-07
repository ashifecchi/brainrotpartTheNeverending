import java.awt.*;

public class InteractableObject {
    private String name;
    private Rectangle area;
    public InteractableObject(String name, Rectangle rect){
        this.name = name;
        area = rect;
    }
}