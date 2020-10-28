package com.example.justjava;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    int quantity=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {




        EditText name = (EditText) findViewById(R.id.plain_text_input);
        String name_string = name.getText().toString();

        Log.v("MainActivity","Name :" +name_string);
        CheckBox whipped_cream_variable = (CheckBox) findViewById(R.id.whipped_cream);

        boolean whipped_cream_has = whipped_cream_variable.isChecked();

        CheckBox chocolate = (CheckBox) findViewById(R.id.Chocolate);
        boolean haschocolate = chocolate.isChecked();
        displayMessage(createOrderSummary(whipped_cream_has,haschocolate,name_string));
       //  Create the text message with a string

        Intent sendIntent = new Intent(Intent.ACTION_SEND);

        sendIntent.putExtra(Intent.EXTRA_TEXT, createOrderSummary(whipped_cream_has,haschocolate,name_string));
        sendIntent.putExtra(Intent.EXTRA_SUBJECT,"Coffie to " +name_string);
        sendIntent.setType("text/plain");

// Verify that the intent will resolve to an activity
        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(sendIntent);
        }


    }
//     This method will give summary
    private  String  createOrderSummary(boolean addwhippedcream,boolean addchocolate ,String naam){
        String name="Jaykumar ";
        int price = calculatePrice(addwhippedcream ,addchocolate);
        String messagefinal ="Name: "+naam;
        messagefinal+="\nAdd whipped cream: "+addwhippedcream;
        messagefinal+="\nAdd chocolate : "+addchocolate;
        messagefinal+="\nQuantity: "+quantity;
        messagefinal+="\nTotal: $"+ price;
        messagefinal+="\n"+getString(R.string.thank_you) ;

        return  messagefinal;
    }

    /**
     * Calculates the price of the order.
     *
     *
     */
    private int calculatePrice(boolean cream , boolean chocolate) {
        int baseprice = 5 ;

        if(cream){
            baseprice+=1;
        }
        if(chocolate){
            baseprice+=3;
        }
        return  baseprice*quantity;
    }


    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int num) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + num);
    }

    public void decrement(View view ){
        quantity--;
        if(quantity<=0){
            quantity++;

            Toast.makeText(this, "You can not have less than 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        displayQuantity(quantity);
    }

    public void increment(View view ){
        quantity++;
        if(quantity>100){
            quantity--;
            Toast.makeText(this, "You can not have more than 100 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        displayQuantity(quantity);
    }





}