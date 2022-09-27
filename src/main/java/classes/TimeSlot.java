package classes;

import java.util.ArrayList;

public class TimeSlot {

    public static ArrayList<TimeSlot> timeSlots = new ArrayList<>();
    private final int duration;
    private final int price;

    public TimeSlot(int duration, int price) {
        this.duration = duration;
        this.price = price;
        timeSlots.add(this);
    }
}
