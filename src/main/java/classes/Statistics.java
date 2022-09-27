package classes;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class Statistics {
    public static void save(Path path){
        JsonArray ja = new JsonArray();
        for(Reservation reservation: GameSpaceQueue.allTimeReservations){
            ja.add(reservation.toJsonObject());
        }
        String jsonText = Jsoner.serialize(ja);
        try {
            Files.write(path,jsonText.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

//    static void load(){
//        load(getDefaultPath());
//    }

    public static void save(){
        save(getDefaultPath());
    }

    private static Path getDefaultPath(){
        String home = System.getProperty("user.home");
        return Paths.get(home).resolve("MonthIncome.json");
    }

    public static void load(){
        load(getDefaultPath());
    }

    static void load(Path path){
        String jsonText = null;
        JsonArray ja = null;
        try {
            jsonText = new String(Files.readAllBytes(path));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        try {
            ja = (JsonArray) Jsoner.deserialize(jsonText);
        } catch (JsonException ex) {
            throw new RuntimeException(ex);
        }

        for (Object o : ja) {
            JsonObject jo = (JsonObject) o;
            MonthlyIncome data = MonthlyIncome.fromJsonObject(jo);
            MonthlyIncome.allTimeReservations.add(data);
        }
    }

}
