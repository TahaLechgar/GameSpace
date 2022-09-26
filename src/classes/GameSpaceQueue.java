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

        for (Reservation instance : playersQueue) {
            if(poste.getId() == instance.getPoste().getId()){
                return instance;
            }
        }
        return posteState;
    }

}
