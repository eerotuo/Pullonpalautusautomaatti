package com.example.eerot.tokaviikko;
import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

public class BottleDispenser {

    private static BottleDispenser bt = new BottleDispenser();

    public static BottleDispenser getInstance(){
        return bt;
    }

    private int bottles;
    private double money;

    ArrayList<Bottle> bottle_array = new ArrayList<Bottle>();
    ArrayList<Bottle> kuittipullot = new ArrayList<Bottle>();

    public BottleDispenser() {
        bottles = 10;
        money = 0;

        bottle_array.add(new Bottle("Pepsi Max", "Pepsi", 0.3, 0.5, 1.8));
        bottle_array.add(new Bottle("Pepsi Max", "Pepsi", 0.4, 0.5, 2.2 ));
        bottle_array.add(new Bottle("Pepsi Max 1,5l", "Pepsi", 0.3, 1.5, 1.8));
        bottle_array.add(new Bottle("Pepsi Max 1,5l", "Pepsi", 0.4, 1.5, 2.2 ));
        bottle_array.add(new Bottle("Coca-Cola Zero", "Coca-Cola", 0.1, 0.5, 2.0 ));
        bottle_array.add(new Bottle("Coca-Cola Zero", "Coca-Cola", 0.1, 1.5, 2.5 ));
        bottle_array.add(new Bottle("Fanta Zero", "Coca-Cola", 0.3, 0.5, 1.95 ));
        bottle_array.add(new Bottle("Fanta Zero", "Coca-Cola", 0.3, 0.5, 1.95 ));
        bottle_array.add(new Bottle("Fanta Zero 1,5l", "Coca-Cola", 0.3, 1.5, 1.95 ));
        bottle_array.add(new Bottle("Fanta Zero 1,5l", "Coca-Cola", 0.3, 1.5, 1.95 ));
        System.out.println("Käyttäjä lisätty");
    }

    public double addMoney() {
        money += 1;
        return money;
    }

    public double addMoney(double rahaa) {
        money += rahaa;
        return money;
    }

    public int buyBottle(String nimi, Context context) {
        int koodi = 100;
        int index = findBottle(bottle_array, nimi);

        if (index == -11) {

            Toast.makeText(context,"Valitsemasi juoma loppunut.",Toast.LENGTH_SHORT).show();
            return koodi;
        }

        Bottle pullo = bottle_array.get(index);

        if (money < pullo.getPrize()) {

            Toast.makeText(context,"Syötä lisää rahaa.",Toast.LENGTH_SHORT).show();
        }

        else {

            bottles -= 1;
            money -= pullo.getPrize();
            System.out.println("KACHUNK! "+ pullo.getName() +" tipahti masiinasta!");
            Toast.makeText(context,"KACHUNK! "+ pullo.getName() +" tipahti masiinasta!",Toast.LENGTH_SHORT).show();
            kuittipullot.add(pullo);
            deleteBottle(pullo);
            koodi = 1;
        }
       return koodi;
    }

    public double returnMoney() {

        double temp = money;
        money = 0;
        return temp;
    }
    public double getMoney() {

        return money;
    }

    public int findBottle(ArrayList<Bottle> bottle_array, String nimi) {

        int p;
        p = -11;

        System.out.println(nimi);

        for (int i= 0; i<bottle_array.size(); i++) {
            System.out.println(bottle_array.get(i).getName());
            if (bottle_array.get(i).getName().equals(nimi)){
                p = i;
            }
        }
        return p;
    }

    public double findPrize(ArrayList<Bottle> bottle_array, String nimi1) {


        int p = 0;
        double hinta;

        p = findBottle(bottle_array, nimi1);

        System.out.println(p);
        hinta = bottle_array.get(p).getPrize();
        System.out.println(hinta);

        return hinta;
    }


    public void deleteBottle(Bottle pullo) {
        bottle_array.remove(pullo);
    }

    public ArrayList getKuittipullot() {
        return kuittipullot;
    }
}
