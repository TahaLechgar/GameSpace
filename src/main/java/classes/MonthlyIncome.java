package classes;

import com.github.cliftonlabs.json_simple.JsonObject;

import java.util.ArrayList;
import java.util.Objects;

public class MonthlyIncome {

    private final String month;
    private final int income;
    public static ArrayList<MonthlyIncome> allTimeReservations = new ArrayList<>();
    public float getIncome() {
        return income;
    }
    public String getMonth() {
        return month;
    }
    public MonthlyIncome(String month, int income) {
        this.month = month;
        this.income = income;
    }

    public static MonthlyIncome fromJsonObject(JsonObject jsonObject){
        String month = (String) jsonObject.get("month");
        int income = Integer.parseInt(String.valueOf(jsonObject.get("pricePaid"))) ;
        return new MonthlyIncome(month, income);
    }

    public static int getMonthlyIncome(String month){
        int income = 0;
        for(MonthlyIncome monthlyIncome: allTimeReservations){
            if(Objects.equals(monthlyIncome.getMonth(), month))
                income+=monthlyIncome.getIncome();
        }
        return income;
    }
}
