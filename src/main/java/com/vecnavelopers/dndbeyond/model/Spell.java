package com.vecnavelopers.dndbeyond.model;

public class Spell {
    private String name;
    private int level;
    private String url;

    // Constructor, getters and setters
    public Spell(String name, int level, String url) {
        this.name = name;
        this.level = level;
        this.url = url;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

