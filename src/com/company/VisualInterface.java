package com.company;

import java.util.*;


public class VisualInterface {
    private Scanner scan;
    private Dictionary dict;

    public VisualInterface(){
        System.out.println("Добро пожаловать в словарь!!!");
        scan= new Scanner(System.in);
        selectDictionary();
    }
    private void selectDictionary(){
        String dicTypeInput = "";

        while (true) {
            System.out.println("Выберите словарь(укажите соответствующую цифру):\n\t1 - 4 латинские буквы\n\t2 - 5 цифр\n\t0 - Выход");

            dicTypeInput = scan.next();

            switch (dicTypeInput) {
                case "0" -> System.exit(0);
                case "1" -> {
                    dict = new Dictionary("4 латинские буквы", "[a-zA-Z]{4}", "dic1");
                    showFunctionMenu();
                }
                case "2" -> {
                    dict = new Dictionary("5 цифр", "[0-9]{5}", "dic2");
                    showFunctionMenu();
                }
                default -> System.out.println("Указанного словаря не существует");
            }
        }

    }
    private void showFunctionMenu(){
        String actionInput = "";
        while(!actionInput.equals("0")) {
            System.out.println("Словарь: " + dict.getName());
            System.out.println("Выберите действие(укажите соответствующую цифру):\n\t1 - Просмотреть словарь\n\t2 - Найти слово\n\t" +
                    "3 - Добавить слово\n\t4 - Удалить слово\n\t0 - Назад");

            actionInput = scan.next();
            switch (actionInput){
                case "0":
                    return;
                case "1":
                    showDict();
                    break;
                case "2":
                    findWord();
                    break;
                case "3":
                    addWord();
                    break;
                case "4":
                    removeWord();
                    break;
                default:
                    System.out.println("Такой функции нет, выберите что-то из предложенного ниже:");
            }
        }
    }

    private void removeWord() {

        System.out.println("Удаление слова из словаря \""+ dict.getName()+"\":");
        System.out.println("Введите слово-ключ:");
        if(dict.remove(scan.next())){
            System.out.println("Успешно");
        }
        else System.out.println("По этому ключу нет слова");
        System.out.println();
    }

    private void findWord() {
        System.out.println("Поиск слова в словаре \""+ dict.getName()+"\":");
        System.out.println("Введите слово-ключ:");
        System.out.println("Значение:"+dict.find(scan.next()));
        System.out.println();
    }

    private void showDict(){
        System.out.println("Содержимое словаря \""+ dict.getName()+"\":");
        dict.show();
        System.out.println();
    }

    private void addWord(){
        String key;
        String value;
        System.out.println("Добавление слова в словарь \""+ dict.getName()+"\":");
        System.out.println("Введите слово-ключ:");
        key= scan.next();
        if(!dict.checkKey(key)){
            System.out.println("Неверный ключ");
            return;
        }
        System.out.println("Введите слово-значение:");
        value= scan.next();
        if(dict.add(key,value)){
            System.out.println("Успешно");
        }
        else System.out.println("По этому ключу уже есть слово, сначала удалите его");
        System.out.println();
    }
}
