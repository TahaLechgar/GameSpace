package classes;

import enums.Games;

import java.util.ArrayList;
import java.util.Scanner;

public class Display {

    public Scanner scanner = new Scanner(System.in);


    public void menu(){
        System.out.println("----- Welcome to GameSpace -----");
        System.out.println("  Please select an option : ");
        System.out.println(" 1 - Add player");
        System.out.println(" 2 - Check the queue");
        System.out.println(" 3 - Get monthly revenue");
        System.out.println(" 0 - Exit");
    }

    public String enterPlayerData(){
        System.out.println("Enter firstname and lastname : \n");
        return scanner.nextLine();
    }

    public String chooseGame(){
        System.out.println("Choose a game from the list : \n");
        Games[] games = Games.values();
        for(int i = 0; i < Games.values().length; i++){
            System.out.println(i + " - " + games[i]);
        }
        int choice = Integer.parseInt(scanner.nextLine());

        while(choice > Games.values().length || choice < 0){
            choice = Integer.parseInt(scanner.nextLine());
        }
        return  String.valueOf(Games.values()[choice]);

    }

    public Poste choosePoste(String game){
        ArrayList<Poste> possiblePostes = new ArrayList<>();
        ArrayList<Integer> possiblePostesIds = new ArrayList<>();

        for(Poste poste: Poste.allPostes){
            if(poste.getGames().contains(game)){
                possiblePostes.add(poste);
                possiblePostesIds.add(poste.getId());
            }
        }
        displayPossiblePostes(possiblePostes);
        return Poste.getPosteById(posteChoice(possiblePostesIds));
    }

    public int availableHoursForPosteChosen(Poste poste){
        Player instance = GameSpaceQueue.checkPosteAvailability(poste);
        String availability = (instance == null) ? null : instance.getAvailableAt();
        int availableHours = DateManagement.availableHours(availability);
        System.out.println("available hours : " + availableHours);
        for(int i = 0; i < availableHours; i++){
            if(i == 0) {
                System.out.println("30 minute");
                continue;
            }
            System.out.println(i + " heure");
        }
        return availableHours;
    }

    public int chooseDuration(int availableHours){
        System.out.println("choose a duration");
        int durationChoice = Integer.parseInt(scanner.nextLine());
        while(durationChoice > availableHours){
            durationChoice = Integer.parseInt(scanner.nextLine());
        }
        return durationChoice;
    }

    private void displayPossiblePostes(ArrayList<Poste> possiblePostes) {
        System.out.println("Choose a poste : ");

        for(Poste poste: possiblePostes){
            String status ;
            Player instance = GameSpaceQueue.checkPosteAvailability(poste);
            if(instance == null){
                status = " Available";
            }
            else{
                status = " Occupied until : " + instance.getAvailableAt();
            }
            System.out.println("poste " + poste.getId() + status);
        }
    }

    private int posteChoice(ArrayList<Integer> possiblePostesIds){
        int posteChoice;
        do {
            posteChoice = Integer.parseInt(scanner.nextLine());
        } while (!possiblePostesIds.contains(posteChoice));
        return posteChoice;
    }

}
