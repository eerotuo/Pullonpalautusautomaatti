package com.example.eerot.tokaviikko;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    TextView textView;
    SeekBar seekBar;
    Context context = null;
    String name;
    int koodi;


    BottleDispenser bottleDispenser = BottleDispenser.getInstance();
    double money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        spinner = findViewById(R.id.spinner);
        textView = findViewById(R.id.textView);
        seekBar = findViewById(R.id.seekBar);
        context = MainActivity.this;

        final ArrayList valinnat = new ArrayList();


        valinnat.add(0, "Valitse juomasi:");
        valinnat.add("Coca-Cola Zero");
        valinnat.add("Coca-Cola Zero 1,5l");
        valinnat.add("Fanta Zero");
        valinnat.add("Pepsi Max");
        valinnat.add("Pepsi Max 1,5l");

        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item, valinnat);
        dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {



                if (parent.getItemAtPosition(position).equals("Coca-Cola Zero")){
                    name = parent.getItemAtPosition(position).toString();
                    koodi = bottleDispenser.buyBottle("Coca-Cola Zero", MainActivity.this);

                }
                if (parent.getItemAtPosition(position).equals("Pepsi Max 1,5l")){
                    name = parent.getItemAtPosition(position).toString();
                    koodi = bottleDispenser.buyBottle("Pepsi Max 1,5l", MainActivity.this);

                }

                else if (parent.getItemAtPosition(position).equals("Pepsi Max")){
                    name = parent.getItemAtPosition(position).toString();
                    //kuittipullot.add(name);
                    bottleDispenser.buyBottle("Pepsi Max", MainActivity.this);
                }
                else if (parent.getItemAtPosition(position).equals("Fanta Zero 1,5l")){
                    name = parent.getItemAtPosition(position).toString();
                    //kuittipullot.add(name);
                    bottleDispenser.buyBottle("Fanta Zero 1,5l", MainActivity.this);
                }

                else if (parent.getItemAtPosition(position).equals("Fanta Zero")){
                    name = parent.getItemAtPosition(position).toString();
                    //kuittipullot.add(name);
                    bottleDispenser.buyBottle("Fanta Zero", MainActivity.this);
                }

                printMoney();
                parent.setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                money = progress;
                textView.setText(String.valueOf(money));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                money = bottleDispenser.addMoney(money);
                seekBar.setProgress(0);
                printMoney();

            }


        });




    }

    public void addMoney(View v){

        money = bottleDispenser.addMoney();
        DecimalFormat formatter = new DecimalFormat("#0.00");
        textView.setText("Rahaa koneessa: "+ formatter.format(money));


    }


    public void printMoney(){

        money = bottleDispenser.getMoney();
        DecimalFormat formatter = new DecimalFormat("#0.00");

        textView.setText("Rahaa koneessa: "+ formatter.format(money) );

    }

    public void retunMoney(View v){

        money = bottleDispenser.returnMoney();
        DecimalFormat formatter = new DecimalFormat("#0.00");
        textView.setText("Rahaa palautui: "+ formatter.format(money));


    }


    public void printResipt(View s){

        double hinta = 0;
        ArrayList<Bottle> kuittipullot = new ArrayList<Bottle>();
        kuittipullot = bottleDispenser.getKuittipullot();

        try {

            OutputStreamWriter writer = new OutputStreamWriter(context.openFileOutput( "kuitti.txt", Context.MODE_PRIVATE));
            DecimalFormat formatter = new DecimalFormat("#0.00");


            writer.write("Ostamasi juomat:\n");
            for (int t= 0; t< kuittipullot.size(); t++){

                name = kuittipullot.get(t).getName();
                System.out.println(name);

                hinta = bottleDispenser.findPrize(kuittipullot, name);

                writer.write("Ostettu juoma "+ name + " Hinta: "+ formatter.format(hinta)+"\n");

            }


            writer.close();


        } catch (FileNotFoundException e) {

            Toast.makeText(MainActivity.this, "Virhe", Toast.LENGTH_SHORT).show();
            e.printStackTrace();

        } catch (IOException e) {
            Toast.makeText(MainActivity.this, "Virhe", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }finally {
            Toast.makeText(MainActivity.this, "Kuitti tulostettu tiedostoon", Toast.LENGTH_SHORT).show();

        }

    }




}
