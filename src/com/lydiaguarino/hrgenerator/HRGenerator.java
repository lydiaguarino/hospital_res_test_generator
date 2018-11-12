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
        String filePrefix = args[0];
        int hospitalCount = Integer.parseInt(args[1]);
        int locationCount = Integer.parseInt(args[2]);
        int residentCount = Integer.parseInt(args[3]);
        int coupleCount = Integer.parseInt(args[4]);
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
            locationIds.add(Integer.toString(x));
        }

        // generate residents
        for (String r: residentIds) {
            residents.add(new Resident(r, getResidentPreferences(hospitalIds, residentPrefsMax)));
        }

        // set couples
        updatePartners(residentIds, residents, coupleCount);


        // generate hospitals
        for (String h: hospitalIds) {
            hospitals.add(new Hospital(h, getHospitalPreferences(h, residents, hospitalPrefsMax), getRandom(capacities), getRandom(locationIds)));
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
            Files.write(Paths.get("test_files/" + filePrefix + "_hospitals.csv"), hospCSV, utf8);
            Files.write(Paths.get("test_files/" + filePrefix + "_residents.csv"), resCSV, utf8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> getResidentPreferences(List<String> list, int limit) {
        return shuffleArray(list).subList(0, limit);
    }

    private static List<String> getHospitalPreferences(String id, List<Resident> residents, int limit) {
        List<String> hospitalApplicants = new ArrayList<>();
        for (Resident r : residents) {
            if (r.preferences.contains(id)) {
                hospitalApplicants.add(r.id);
            }
        }
        return shuffleArray(hospitalApplicants).subList(0, Math.min(limit, hospitalApplicants.size()));
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

    private static void updatePartners(List<String> residentIds, List<Resident> residents, int coupleCount) {
        // choose random pairs by shuffling list and grabbing pairs sequentially
        // There is some wack-a-doo proliferation of array lists in this method - will look at optimizing in the future.
        List<String> shuffledResidents = shuffleArray(residentIds);
        for (int y = 0; y < coupleCount * 2; y = y + 2) {
            String resId = shuffledResidents.get(y);
            String partnerId = shuffledResidents.get(y + 1);
            int resIndex = Integer.parseInt(resId.split("R")[1]);
            int partnerIndex = Integer.parseInt(partnerId.split("R")[1]);
            Resident resA = residents.get(resIndex);
            Resident resB = residents.get(partnerIndex);
            resA.setPartner(partnerId);
            resB.setPartner(resId);
            // update partner B's preference list to partially align with partner A
            // use half of each list, remove duplicates and shuffle
            List<String> resATopPicks = new ArrayList<>(resA.preferences.subList(0, resA.preferences.size() / 2));
            List<String> resBTopPicks = new ArrayList<>(resB.preferences.subList(0, resB.preferences.size() / 2));
            List<String> combinedPicks = new ArrayList<>();
            combinedPicks.addAll(resATopPicks);
            combinedPicks.addAll(resBTopPicks);
            List<String> dedupedList = new ArrayList<>(new HashSet<>(combinedPicks));
            resB.setPreferences(shuffleArray(dedupedList));
        }
    }
}

