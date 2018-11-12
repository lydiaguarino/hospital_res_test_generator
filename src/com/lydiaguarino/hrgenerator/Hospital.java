package com.lydiaguarino.hrgenerator;

import java.util.List;

class Hospital {
    private String id;
    private List<String> preferences;
    private String locationId;
    private int capacity;

    Hospital(String uid, List<String> prefs, int cap, String loc) {
        id = uid;
        preferences = prefs;
        capacity = cap;
        locationId = loc;
    }

    private String getPrefString() {
        StringBuilder prefString = new StringBuilder();
        for (String p: preferences) {
            prefString.append(p).append(" ");
        }
        return prefString.toString().trim();
    }

    String toCsvLine() {
        return id + "," + locationId + "," + Integer.toString(capacity) + "," + getPrefString();
    }
}
