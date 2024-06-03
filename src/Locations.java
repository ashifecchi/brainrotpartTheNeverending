import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Locations {
    private ArrayList<Setting> settings = new ArrayList<>();
    private static Setting currentSetting;
    public Locations(Setting one){
        settings.add(one);
        if (settings.size()<2) {
            currentSetting = one;
        }
    }
    public void addSetting(Setting x){
        settings.add(x);
    }

    public static void setCurrentSetting(Setting setting) {
        currentSetting = setting;
    }

    public void changeSetting(int idx){
        currentSetting = settings.get(idx);
    }
    public static ArrayList<Rectangle> getCurrentSettingBounds(){
        return currentSetting.getCollision();
    }
    public static Setting getCurrentSetting() {
        return currentSetting;
    }
}
