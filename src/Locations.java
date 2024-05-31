import java.util.ArrayList;

public class Locations {
    private ArrayList<Setting> settings = new ArrayList<>();
    private static Setting currentSetting;
    public Locations(Setting one){
        settings.add(one);
        currentSetting = one;
    }
    public void addSetting(Setting x){
        settings.add(x);
    }
    public void changeSetting(int idx){
        currentSetting = settings.get(idx);
    }

    public static Setting getCurrentSetting() {
        return currentSetting;
    }
}
