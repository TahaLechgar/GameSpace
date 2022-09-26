package classes;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;

public class DateManagement {

    public static boolean checkTime(){
//       String currentTime =  c
       int hour = LocalTime.now().getHour();
       return hour >= 9 && hour <= 11 || hour >= 14 && hour <= 19;
    }

    public static String getAvailableAt(String hour, int duration){
        if(hour == null)
            hour =  new SimpleDateFormat("HH:mm").format(new Timestamp(new Date().getTime()));

        String[] hm = hour.split(":", 0);
        hm[0] = String.valueOf(Integer.parseInt(hm[0]) + duration);
        return String.join(":", hm);
    }

    public static int availableHours(String hour){
        if(hour == null){
            hour =  new SimpleDateFormat("HH:mm").format(new Timestamp(new Date().getTime()));
        }

        String[] hm = hour.split(":", 0);
        if(Integer.parseInt(hm[0]) <= 12){
            hm[0] = String.valueOf(Integer.parseInt(hm[0]) + 2);
        }
        int availableHours = 20 - Integer.parseInt(hm[0]);

        if(Integer.parseInt(hm[1]) < 30){
            availableHours++;
        }
        return availableHours;
    }
}
