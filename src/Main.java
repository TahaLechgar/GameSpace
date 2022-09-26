import classes.*;
import enums.Games;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {


        Poste p1 = new Poste("PS5", "Samsung", String.valueOf(Games.FIFA), String.valueOf(Games.PES));
        Poste p2 = new Poste("PS5", "Samsung", String.valueOf(Games.AssassinsCreed), String.valueOf(Games.PES));
        System.out.println(p1);

        LinkedList<Reservation> playersQueue = new LinkedList<>();
        GameSpaceQueue.playersQueue.addLast(new Reservation(String.valueOf(Games.FIFA) , "Taha Lechgar", 1, p1));
        GameSpaceQueue.playersQueue.addLast(new Reservation(String.valueOf(Games.PES), "Thomas Edison", 2, p2));
        GameSpaceQueue.playing.add(new Reservation(String.valueOf(Games.FIFA) , "Taha Lechgar", 2, p1));
        System.out.println(playersQueue);
        System.out.println(LocalTime.now().getHour());
        System.out.println(LocalTime.now().getMinute());

        Display display = new Display();


        if(!DateManagement.checkTime()){
            System.out.println("GameSpace is out of service");
            System.exit(0);
        }

        while(true){
            boolean exit = false;
            display.menu();

            String choice = scanner.nextLine();

            while(!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("0")){
                choice = scanner.nextLine();
            }

            switch (choice) {
                case "1" -> {
                    String playerName = display.enterPlayerData();
                    String gameChoice = display.chooseGame();
                    Poste chosenPoste = display.choosePoste(gameChoice);
                    int availableHours = display.availableHoursForPosteChosen(chosenPoste);
                    int duration = display.chooseDuration(availableHours);
                    Reservation instance = new Reservation(gameChoice, playerName, duration, chosenPoste);
                    if (GameSpaceQueue.checkPosteAvailability(chosenPoste) == null) {
                        GameSpaceQueue.playing.add(instance);
                    } else {
                        GameSpaceQueue.playersQueue.addLast(instance);
                    }
                }
                case "2" -> {
                    System.out.println("Queue:");
                    for(Reservation instance: GameSpaceQueue.playersQueue){
                        System.out.println(instance);
                    }
                    System.out.println("Playing");
                    for(Reservation instance: GameSpaceQueue.playing){
                        System.out.println(instance);
                    }
                }
                case "3" -> System.out.println(3);
                case "0" -> exit = true;
            }
            if(exit) break;
        }
    }

    public static void enterPlayerData(){
        System.out.println("Enter a player name : \n");
        String playerName = scanner.nextLine();
    }

    public static void chooseGame(){

        System.out.println("Choose a game from the list : \n");
        Games[] games = Games.values();
        for(int i = 0; i < Games.values().length; i++){
            System.out.println(i + " - " + games[i]);
        }

        String choice = scanner.nextLine();

        while(Integer.parseInt(choice) > Games.values().length || Integer.parseInt(choice) < 0){
            choice = scanner.nextLine();
        }

        String game = String.valueOf(Games.values()[Integer.parseInt(choice)]);

        System.out.println(game);
        ArrayList<Poste> possiblePostes = new ArrayList<>();
        ArrayList<Integer> possiblePostesIds = new ArrayList<>();

        for(Poste poste: Poste.allPostes){
            if(poste.getGames().contains(game)){
                possiblePostes.add(poste);
                possiblePostesIds.add(poste.getId());
            }
        }

        System.out.println("Choose a poste : ");

        for(Poste poste: possiblePostes){
            String status ;
            Reservation instance = GameSpaceQueue.checkPosteAvailability(poste);
            if(instance == null){
                status = " Available";
            }
            else{
                status = " Occupied until : " + instance.getAvailableAt();
            }
            System.out.println("poste " + poste.getId() + status);
        }
        int posteChoice;
        do {
            posteChoice = Integer.parseInt(scanner.nextLine());
        } while (!possiblePostesIds.contains(posteChoice));

        Poste posteChosen = Poste.getPosteById(posteChoice);
        Reservation instance = GameSpaceQueue.checkPosteAvailability(posteChosen);
        String availability = (instance == null) ? null : instance.getAvailableAt();
        int availableHoursForChosenPoste = DateManagement.availableHours(availability);
        System.out.println("available hours : " + availableHoursForChosenPoste);
        for(int i = 0; i < availableHoursForChosenPoste; i++){
            if(i == 0) {
                System.out.println("30 minute");
                continue;
            }
            System.out.println(i + " heure");
        }


//        for(Poste poste: possiblePostes){
//            if(poste.getId() == posteChoice){
//                posteChosen = poste;
//                break;
//            }
//        }

        System.out.println("Choose a duration");

//        for(Duration duration: Duration.values()){
//            String msg = String.valueOf(duration);
//            if(!msg.equals("LUXE")){
//                msg += " HOUR";
//            }
//            System.out.println(msg);
//        }

        int timeChoice = Integer.parseInt(scanner.nextLine());
        while(timeChoice <= availableHoursForChosenPoste){
            timeChoice = Integer.parseInt(scanner.nextLine());
        }

    }

}