package com.methaporce.model;

public class Movie {
    private final int id;
    private String name;
    private boolean available;

    public Movie(int id, String name) {
        this.id = id;
        this.name = name;
        this.available = false;
    }

    public Movie(int id, String name, boolean available) {
        this.id = id;
        this.name = name;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setName(String new_name) {
        this.name = new_name;
    }

    public void setAvailability(boolean is_available) {
        this.available = is_available;
    }

    public String toString() {
        return "id=" + id + ", \tnombre=" + name;
    }
}
