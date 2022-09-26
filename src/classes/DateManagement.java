package classes;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DateManagement {

    public static boolean checkTime(){
       int hour = LocalTime.now().getHour();
       return hour >= 9 && hour <= 11 || hour >= 14 && hour <= 19;
    }

    public static String getAvailableAt(String hour, int duration){
        if(hour == null)
            hour =  new SimpleDateFormat("HH:mm").format(new Timestamp(new Date().getTime()));

        String[] hm = hour.split(":", 0);
        int newTime = Integer.parseInt(hm[0]) + duration;
        if(Integer.parseInt(hm[0]) <= 12 && newTime >= 12 ){
            newTime+=2;
        }

        hm[0] = String.valueOf(newTime);
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

        if(Integer.parseInt(hm[0]) == 19 && Integer.parseInt(hm[1]) > 30){
            availableHours = 0;
        }

        return availableHours;
    }

    public static void timer(String time, Poste poste){
        String[] hm = time.split(":");
        long delay = ChronoUnit.MILLIS.between(LocalTime.now(), LocalTime.of(Integer.parseInt(hm[0]), Integer.parseInt(hm[1]), 0));
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(new MyTimeTask(poste), delay, TimeUnit.MILLISECONDS);
    }

    private static class MyTimeTask extends TimerTask
    {

        private final Poste poste;

        public MyTimeTask(Poste poste){
            this.poste = poste;
        }
        public void run()
        {
            GameSpaceQueue.moveReservationFromQueueToPlaying(poste);
        }
    }

}
