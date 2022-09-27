package classes;


import com.github.cliftonlabs.json_simple.JsonObject;

import java.time.LocalDate;

public class Reservation {

    private String game;
    private String playerName;
    private int duration;
    private int pricePaid;
    private Poste poste;
    private String availableAt;

    private final String month = String.valueOf(LocalDate.now().getMonth());


    public Reservation(String game, String playerName, int duration, Poste poste ) {
        this.game = game;
        this.playerName = playerName;
        this.duration = duration;
        this.poste = poste;
        switch(duration){
            case 0 -> this.pricePaid = 5;
            case 1 -> this.pricePaid = 10;
            case 2 -> this.pricePaid = 18;
            case 3 -> this.pricePaid = 28;
            case 4 -> this.pricePaid = 36;
            case 5 -> this.pricePaid = 40;
            case 9 -> this.pricePaid = 65;
        }
    }

    // Getters
    public String getGame() {
        return game;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getDuration() {
        return duration;
    }
    public String getAvailableAt() {
        return availableAt;
    }


    public Poste getPoste() {
        return poste;
    }

    //Setters
    public void setGame(String game) {
        this.game = game;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setPoste(Poste poste) {
        this.poste = poste;
    }

    public void setAvailableAt(String availableAt) {
        this.availableAt = availableAt;
        DateManagement.timer(availableAt, this.poste);
    }

    public String toString(){
        return   this.game + " | " + this.playerName + " | " + this.duration + " hours | poste : " + this.poste.getId() ;
    }

    public JsonObject toJsonObject(){
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("month", month);
        jsonObject.put("pricePaid", pricePaid);
        return jsonObject;
    }



}
