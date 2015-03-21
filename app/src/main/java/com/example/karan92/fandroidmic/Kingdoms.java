package com.example.karan92.fandroidmic;

import java.util.List;

/**
 * Created by Karan92 on 3/16/2015.
 */
public class Kingdoms {
    private String id;
    private String name;
    private String image;
    private String climate;
    private String population;
    private String language;
    List<Quest> quests;

    public static class Quest{
        public String id;
        public String name;
        public static String description;
        public String image;
        public Giver giver;
    }

    public static class Giver{
        public String id;
        public String name;
        public String description;
        public String image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<Quest> getQuests() {
        return quests;
    }

    public void setQuests(List<Quest> quests) {
        this.quests = quests;
    }
}
