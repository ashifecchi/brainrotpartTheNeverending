import java.awt.*;

public class InteractableObject extends Rectangle{
    private String name;
    public InteractableObject(String name, Rectangle rect){
        super(rect);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}