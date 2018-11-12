package com.lydiaguarino.hrgenerator;

import java.util.List;

class Resident {
    private String id;
    private List<String> preferences;
    private String partner;

    Resident(String uid, List<String> prefs) {
        id = uid;
        preferences = prefs;
        partner = "none";
    }

    void setPartner(String id) {
        partner = id;
    }

    private String getPrefString() {
        StringBuilder prefString = new StringBuilder();
        for (String p: preferences) {
            prefString.append(p).append(" ");
        }
        return prefString.toString().trim();
    }

    String toCsvLine() {
        return id + "," + getPrefString() + "," + partner;
    }
}
