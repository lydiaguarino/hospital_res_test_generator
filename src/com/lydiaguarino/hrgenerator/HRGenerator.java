package com.lydiaguarino.hrgenerator;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.*;

public class HRGenerator {
    public static void main(String[] args) {
        // read in args for generator
        String fnSalt = args[0];
        int hospitalCount = Integer.parseInt(args[1]);
        int residentCount = Integer.parseInt(args[2]);
        int coupleCount = Integer.parseInt(args[3]);
        double locationVariance = 0.5;
        int locationCount = (int)Math.ceil(hospitalCount * locationVariance);
        int prefMax = 15;
        int hospitalPrefsMax = Math.min(residentCount, prefMax);
        int residentPrefsMax = Math.min(hospitalCount, prefMax);
        List<Integer> capacities = Arrays.asList(1,2,3,4,5);
        Charset utf8 = StandardCharsets.UTF_8;

        if (coupleCount > residentCount / 2) {
            throw new Error("Couple count exceeds available residents");
        }

        List<String> hospitalIds = new ArrayList<>();
        List<String> residentIds = new ArrayList<>();
        List<String> locationIds = new ArrayList<>();
        List<Resident> residents = new ArrayList<>();
        List<Hospital> hospitals = new ArrayList<>();

        // generate ids
        for (int i=0; i < hospitalCount; i++) {
            hospitalIds.add('H' + Integer.toString(i));
        }

        for (int j=0; j < residentCount; j++) {
            residentIds.add('R' + Integer.toString(j));
        }

        for (int x=0; x < locationCount; x++) {
            locationIds.add('L' + Integer.toString(x));
        }

        // generate residents
        for (String r: residentIds) {
            residents.add(new Resident(r, getPreferences(hospitalIds, residentPrefsMax)));
        }

        // generate hospitals
        for (String h: hospitalIds) {
            hospitals.add(new Hospital(h, getPreferences(residentIds, hospitalPrefsMax), getRandom(capacities), getRandom(locationIds)));
        }

        // set couples
        List<String> shuffledResidents = shuffleArray(residentIds);
        for (int y = 0; y < coupleCount * 2; y = y + 2) {
            String res = shuffledResidents.get(y);
            String partner = shuffledResidents.get(y + 1);
            int resIndex = Integer.parseInt(res.split("R")[1]);
            int partnerIndex = Integer.parseInt(partner.split("R")[1]);
            residents.get(resIndex).setPartner(partner);
            residents.get(partnerIndex).setPartner(res);
        }

        // output csv files
        List<String> resCSV = new ArrayList<>();
        List<String> hospCSV = new ArrayList<>();
        for (Resident r: residents) {
            resCSV.add(r.toCsvLine());
        }

        for (Hospital h: hospitals) {
            hospCSV.add(h.toCsvLine());
        }

        try {
            Files.write(Paths.get("test_files/" + fnSalt + "_hospitals.csv"), hospCSV, utf8);
            Files.write(Paths.get("test_files/" + fnSalt + "_residents.csv"), resCSV, utf8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> getPreferences(List<String> list, int limit) {
        return shuffleArray(list).subList(0, limit);
    }

    private static void swap(List<String> a, int i, int change) {
        String helper = a.get(i);
        a.set(i, a.get(change));
        a.set(change, helper);
    }

    private static List<String> shuffleArray(List<String> list) {
        List<String> arr = new ArrayList<>(list);
        int n = arr.size();
        Random random = new Random();
        random.nextInt();
        for (int i = 0; i < n; i++) {
            int change = i + random.nextInt(n - i);
            swap(arr, i, change);
        }
        return arr;
    }

    private static <T> T getRandom(List<T> array) {
        int rnd = new Random().nextInt(array.size());
        return array.get(rnd);
    }
}

