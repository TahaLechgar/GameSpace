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




}