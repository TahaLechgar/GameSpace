package classes;


public class Reservation {

    private String game;
    private String playerName;
    private int duration;
    private Poste poste;
    private String availableAt;


    public Reservation(String game, String playerName, int duration, Poste poste ) {
        this.game = game;
        this.playerName = playerName;
        this.duration = duration;
        this.poste = poste;
//        this.availableAt = availableAt;
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
    }

    public String toString(){
        return   this.game + " | " + this.playerName + " | " + this.duration + " hours | poste : " + this.poste.getId() ;
    }

}
