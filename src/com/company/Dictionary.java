package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;

public class Dictionary {
    private String name;
    private String rule;
    private String path;
    private HashMap<String, String> map = new HashMap<>();

    public Dictionary(String name, String rule, String path) {
        this.name = name;
        this.rule = rule;
        this.path = path;

        File file = new File(path);
        if(file.isFile()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(path));
                String line = reader.readLine();

                while(line != null) {
                    String[] splitLine = line.split("-");
                    map.put(splitLine[0], splitLine[1]);
                    line = reader.readLine();
                }

                reader.close();
            }
            catch(Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public String getName() {
        return name;
    }

    public void show() {
        try {
            if(new File(path).exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(path));
                String line = reader.readLine();
                while(line != null) {
                    System.out.println(line);
                    line = reader.readLine();
                }
                reader.close();
            }
            else {
                System.out.println("Ошибка чтения файла");
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String find (String key) {
        if(!map.isEmpty()) {
            if(map.get(key) != null) {
                return map.get(key);
            } else {

                return "Указанного слова нет в словаре";
            }
        } else {
            return "Словарь пуст";
        }
    }

    public boolean checkKey(String key){
        return key.matches(rule);
    }
    public boolean add(String key, String value){
        if(map.get(key)!=null){
            return false;
        }
        map.put(key, value);
        writeDictionary();
        return true;
    }
    public boolean remove(String key){
        if(!map.containsKey(key)){
            return false;
        }
        map.remove(key);
        writeDictionary();
        return true;
    }
    private void writeDictionary(){
        if(new File(path).exists()) {
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
                for(var entry : map.entrySet()) {
                    //String value =  map.get(k);
                    writer.write(entry.getKey() + "-" + entry.getValue() + "\n");
                }
            } catch(Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
