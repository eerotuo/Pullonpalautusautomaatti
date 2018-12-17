package com.example.eerot.tokaviikko;

public class Bottle {

    private String Nimi;
    private String Valmistaja;
    private double Energia;
    private double Koko;
    private double Hinta;


    public Bottle() {

        Nimi = "Pepsi Max";
        Valmistaja = "Pepsi";
        Energia = 0.3;
        Koko = 0.5;
        Hinta = 1.80;

    }
    public Bottle(String name, String manuf, double energy, double size, double price) {
        Nimi = name;
        Valmistaja = manuf;
        Energia = energy;
        Koko = size;
        Hinta = price;
    }

    public String getName() {
        return Nimi;
    }

    public double getEnergy() {
        return Energia;
    }

    public String getManufacturer() {
        return Valmistaja;
    }

    public double getPrize() {
        return Hinta;
    }

    public double getSize() {
        return Koko;
    }
}
