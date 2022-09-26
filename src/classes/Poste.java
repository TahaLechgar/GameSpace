package classes;

import java.util.ArrayList;
import java.util.Arrays;

public class Poste {

    public static int postesNumber = 0;
    public static ArrayList<Poste> allPostes = new ArrayList<>();

    private final int id;
    private String type;
    private String monitor;
    private ArrayList<String> games;

    // Getters
    public String getType(){
        return type;
    }
    public String getMonitor(){
        return monitor;
    }
    public ArrayList<String> getGames(){
        return games;
    }

    public int getId() {
        return id;
    }

    //Setters
    public void setType(String type){
        this.type = type;
    }
    public void setMonitor(String monitor){
        this.monitor = monitor;
    }
    public void addGames(String... games){
        this.games.addAll(Arrays.asList(games));
    }

    public Poste(String type, String monitor, String... games){
        postesNumber++;
        this.id = postesNumber;
        this.type = type;
        this.monitor = monitor;
        this.games = new ArrayList<>();
        this.games.addAll(Arrays.asList(games));
        allPostes.add(this);
    }

    public String toString() {
        StringBuilder games = new StringBuilder("\nGames : ");
        for(String game : this.games){
            games.append(game).append(", ");
        }
        return "Id : " + this.id +"\nType : " + this.type + "\nMonitor : " + this.monitor + games;
    }

    public static Poste getPosteById(int id){
        for(Poste poste: allPostes){
            if(poste.getId() == id)
                return poste;
        }
        return null;
    }
}
