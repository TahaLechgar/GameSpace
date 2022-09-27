import classes.*;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {


//        if(!DateManagement.checkTime()){
//            System.out.println("GameSpace is out of service");
//            System.exit(0);
//        }
        

        Display display = new Display();

        display.setupPostes();
        Statistics.load();
        for (MonthlyIncome mi: MonthlyIncome.allTimeReservations){
            System.out.println(mi.getIncome() + " " + mi.getMonth());
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
                    if(duration == -1){
                        break;
                    }

                    Reservation instance = new Reservation(gameChoice, playerName, duration, chosenPoste);
                    Reservation posteAvailability = GameSpaceQueue.checkPosteAvailability(chosenPoste);
                    if (posteAvailability == null) {
                        String availableAt = DateManagement.getAvailableAt(null, duration);
                        instance.setAvailableAt(availableAt);
                        GameSpaceQueue.playing.add(instance);
                        GameSpaceQueue.allTimeReservations.add(instance);
                        Statistics.save();
                    } else {
                        String availableAt = DateManagement.getAvailableAt(posteAvailability.getAvailableAt(), duration);
                        instance.setAvailableAt(availableAt);
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
                case "3" ->{
                    System.out.println("Your Monthly income is : " + MonthlyIncome.getMonthlyIncome(String.valueOf(LocalDate.now().getMonth())));
                    System.out.println("");
                }
                case "0" -> exit = true;
            }
            if(exit) break;
        }
    }




}