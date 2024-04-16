package com.example.mmassignmentburger;

import java.util.ArrayList;
import java.util.List;

public class burger {
    List<String> getRecommendations(String preference) {
        List<String> recommendation = new ArrayList<String>();
        if (preference.equals("Beef Burgers")) {
            recommendation.add("The Classic");
            recommendation.add("Not So Classic");
            recommendation.add("Stingy Burger(Spicy)");
            recommendation.add("Happy's Burger");
        }
        else if (preference.equals("Chicken Burger")) {
            recommendation.add("Simple Chicken");
            recommendation.add("Chickenery Zinger");
            recommendation.add("The Gold Digger");
            recommendation.add("Rusty Pot");
        }
        else if (preference.equals("Vegan Burger")) {
            recommendation.add("Save Da Animals");
            recommendation.add("No Meat No Greet");
            recommendation.add("Da Falafel Burger");

        }
        else {
            recommendation.add("Choice not covered");
        }
        return recommendation;
    }
}

