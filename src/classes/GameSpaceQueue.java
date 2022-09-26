package classes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class GameSpaceQueue {

    public static ArrayList<Reservation> playing = new ArrayList<>();
    public static LinkedList<Reservation> playersQueue = new LinkedList<>();

    public static Reservation checkPosteAvailability(Poste poste){
        if(playing.size() == 0){
            return null;
        }
        for(Reservation instance: GameSpaceQueue.playing){
            if(poste.getId() == instance.getPoste().getId()){
                return instance;
            }
        }
        return null;
    }

    public static Reservation getLastReservationForGivenPoste(Poste poste){
        Reservation posteState = checkPosteAvailability(poste);
        if(posteState == null)
            return null;

        Iterator<Reservation> x = playersQueue.descendingIterator();

        // print list with descending order
        while (x.hasNext()) {
            Reservation instance = x.next();
            if(poste.getId() == instance.getPoste().getId()){
                System.out.println(instance);
                return instance;
            }
        }

        return posteState;
    }

    public static Reservation getFirstReservationForGivenPoste(Poste poste){
        Reservation posteState = checkPosteAvailability(poste);
        if(posteState == null)
            return null;

        for (Reservation instance : playersQueue) {
            if(poste.getId() == instance.getPoste().getId()){
                System.out.println(instance);
                return instance;
            }
        }
        return posteState;
    }

    public static void moveReservationFromQueueToPlaying(Poste poste){

        for (Reservation instance : playersQueue) {
            if(poste.getId() == instance.getPoste().getId()){
                playing.removeIf(reservation -> reservation.getPoste().getId() == instance.getPoste().getId());
                playing.add(instance);
                playersQueue.remove(instance);
            }
        }
    }

}
