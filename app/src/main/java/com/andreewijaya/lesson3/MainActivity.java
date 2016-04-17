package com.andreewijaya.lesson3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    int numberOfCoffee = 0;
    int priceCoffee = 5;
    int whippedCreamPrice = 1;
    int chocolateSprinklePrice = 2;
    //ada TextView dengan nama quantity
    TextView quantity;
    TextView confirmation;
    TextView price;
    CheckBox whippedCream;
    CheckBox chocolateSprinkle;
    EditText nameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ternyata quantity itu TextView dengan id quantityOrdered
        quantity = (TextView) findViewById(R.id.quantityOrdered);
        price = (TextView) findViewById(R.id.price_text_view);
        confirmation = (TextView) findViewById(R.id.order_confirmation_text_view);
        nameEditText = (EditText) findViewById(R.id.name_edit_text);
        whippedCream = (CheckBox) findViewById(R.id.whipped_cream_check_box);
        chocolateSprinkle = (CheckBox) findViewById(R.id.chocolate_sprinkles_check_box);
    }

    public void minus(View v){
        //kalau jumlah kopi nya 0 tidak bisa dkurangi
        if (numberOfCoffee > 0){
            numberOfCoffee = numberOfCoffee - 1;
            Log.v("jumlah_kopi", String.valueOf(numberOfCoffee));
        }else{
            numberOfCoffee = 0;
        }

        updatePrice(numberOfCoffee);

    }
    public void plus(View v){
        numberOfCoffee = numberOfCoffee + 1;
        updatePrice(numberOfCoffee);

    }

    public void submitOrder(View v){
        //kalau jumlah kopi 0, print "anda tidak memiliki order"
        //kalau lebih dari 0, terima kasih
        //panggil apa isi edittext
        String message= "";
        if (numberOfCoffee > 0){

            message = "Terima kasih ";
            //message ditambah nama

            String name = nameEditText.getText().toString();
            message += name; //message = message + name;
            message = message + "\n" + numberOfCoffee + " Gelas Kopi";
            if(whippedCream.isChecked()){
                message += "\nwhipped cream topping";
            }
            if(chocolateSprinkle.isChecked()){
                message += "\nchocolate sprinkle topping";
            }
            message += "\nFor a total of: $" + calculatePrice(numberOfCoffee);

        }else{
            message = "Anda tidak memiliki order";
        }

        confirmation.setText(message);
    }

    private int calculatePrice(int numberOfCoffee){
        int pricePerCup = priceCoffee;//5

        //kalau pakai topping whippedCream
        if(whippedCream.isChecked()){
            pricePerCup += whippedCreamPrice;//6
            //pricePerCup = pricePerCup + whippedCreamPrice;
        }
        if(chocolateSprinkle.isChecked()){
            pricePerCup += chocolateSprinklePrice;//8 atau 7
        }

        int totalPrice = numberOfCoffee * pricePerCup;
        return totalPrice;

    }
    private void updatePrice(int numberOfCoffee){
        quantity.setText(String.valueOf(numberOfCoffee));
        price.setText("$"+String.valueOf(calculatePrice(numberOfCoffee)));

    }
}
