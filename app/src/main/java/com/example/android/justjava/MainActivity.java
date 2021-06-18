package com.example.android.justjava;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 1;

    /*
     * Views
     */

     TextView quantityTextView ;
     TextView orderSummaryTextView;
     CheckBox vsbitieCreamCheckBox;
     CheckBox chocolateCheckBox;
     EditText eText;



    /*
     * Life cycle
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
         orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
         vsbitieCreamCheckBox = (CheckBox) findViewById(R.id.cream_checkbox);
         chocolateCheckBox = findViewById(R.id.chocolate_checkbox);
         eText =  findViewById(R.id.album_description_view);

    }


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
//            displayMessage(createOrderSummary(5));
        String nameClient = eText.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL,"idmitrievnz@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT,"Just Java order for: "+ nameClient);
        intent.putExtra(Intent.EXTRA_TEXT,createOrderSummary(5) );
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    private   String createOrderSummary(int price){
        boolean hasWhippedCream = vsbitieCreamCheckBox.isChecked();
        boolean hasChocolate = chocolateCheckBox.isChecked();
        String nameClient = eText.getText().toString();
        if (hasChocolate){
            price = price + 2 ;
        }
        if(hasWhippedCream){
            price = price + 1;
        }
        price = quantity * price;
//        Log.i("igor", "kpem: " + hasWhippedCream);
        return  "Name: " + nameClient +
                "\nAdd Fucking cream?: " + hasWhippedCream +
                "\nAdd The Chocolate?: " + hasChocolate +
                "\nQuantity: " + quantity +
                "\nTotal: $" + price + "\n"+getString(R.string.thank_you);

    }





    /**
     * eto ya sdelal pribavlenie knopka
     */
    public void increment(View view) {

        if (quantity >= 10){
            Toast.makeText(this,"You cannot order more than 10 cups ",Toast.LENGTH_SHORT).show();
        } else {
            quantity = quantity + 1;
            displayQuantity(quantity);
        }

}

    /**
     * eto ubalenie knopka
     */
    public void decrement(View view) {
        if (quantity <= 1){
            Toast.makeText(this,"You cannot order less than 1 cup ",Toast.LENGTH_SHORT).show();
        } else {
            quantity = quantity - 1;
            displayQuantity(quantity);
        }

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    public void displayQuantity(int number ) {


        if (quantity>10){
            Context context = getApplicationContext();
            Toast toast = Toast.makeText(context,"You cannot order more than ".concat("10cups"),Toast.LENGTH_LONG);
            toast.show();
        }
        quantityTextView.setText("" + number);
    }

//    /**
//     * This method displays the given text on the screen.
//     */
//    private void displayMessage(String message) {
//        orderSummaryTextView.setText(message);
//    }
}
