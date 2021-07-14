package com.example.android.justjava;

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

    /**
     * Views
     */

    TextView quantityTextView;
    CheckBox CreamCheckBox;
    CheckBox chocolateCheckBox;
    EditText eText;


    /**
     * Life cycle
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        CreamCheckBox = (CheckBox) findViewById(R.id.cream_checkbox);
        chocolateCheckBox = findViewById(R.id.chocolate_checkbox);
        eText = findViewById(R.id.album_description_view);

    }


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        String nameClient = eText.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, "idmitrievnz@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for: " + nameClient);
        intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary(5, nameClient));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     *This method sent massage to email
     */

    private String createOrderSummary(int price, String nameClient) {
        boolean hasWhippedCream = CreamCheckBox.isChecked();
        boolean hasChocolate = chocolateCheckBox.isChecked();
        if (hasChocolate) {
            price = price + 2;
        }
        if (hasWhippedCream) {
            price = price + 1;
        }
        price = quantity * price;
//        Log.i("igor", "cream: " + hasWhippedCream);
        return getString(R.string.create_order_summary, nameClient, hasWhippedCream, hasChocolate, quantity, price);
    }


    /**
     * This is increment button
     */
    public void increment(View view) {

        if (quantity >= 10) {
            Toast.makeText(this, getString(R.string.toast_increment), Toast.LENGTH_SHORT).show();
        } else {
            quantity++;
            quantityTextView.setText("" + quantity);
        }

    }

    /**
     * This is decrement button
     */
    public void decrement(View view) {
        if (quantity <= 1) {
            Toast.makeText(this, getString(R.string.toast_decrement), Toast.LENGTH_SHORT).show();
        } else {
            quantity--;
            quantityTextView.setText("" + quantity);

        }

    }



}
