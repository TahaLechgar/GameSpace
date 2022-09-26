package classes;

import java.util.ArrayList;
import java.util.LinkedList;

public class GameSpaceQueue {

    public static ArrayList<Player> playing = new ArrayList<>();
    public static LinkedList<Player> playersQueue = new LinkedList<>();

    public static Player checkPosteAvailability(Poste poste){
        if(playing.size() == 0){
            return null;
        }
        for(Player instance: GameSpaceQueue.playing){
            if(poste.getId() == instance.getPoste().getId()){
                return instance;
            }
        }
        return null;
    }

}
