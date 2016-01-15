package com.oswaldo.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    HashMap<ConversionPair, Double> conversionMap;

    public void convert(View view) {

        EditText editText = (EditText)findViewById(R.id.amount);
        int amount = 0;

        try {

            amount = Integer.parseInt(editText.getText().toString());
        }
        catch (NumberFormatException e) {

            Toast.makeText(getApplicationContext(), "Please enter a valid number.", Toast.LENGTH_LONG)
                    .show();
            return;
        }

        Spinner sourceSpinner = (Spinner)findViewById(R.id.sourceSpinner);
        String sourceCurrency = sourceSpinner.getSelectedItem() != null ?
                sourceSpinner.getSelectedItem().toString() : "USD";

        Spinner targetSpinner = (Spinner)findViewById(R.id.targetSpinner);
        String targetCurrency = targetSpinner.getSelectedItem() != null ?
                targetSpinner.getSelectedItem().toString() : "EUR";

        double conversionRate = !sourceCurrency.equals(targetCurrency) ?
                conversionMap.get(new ConversionPair(sourceCurrency, targetCurrency)).doubleValue()
                : 1.00;

        double convertedAmount = (conversionRate * amount * 100) / 100;

        Toast.makeText(getApplicationContext(), convertedAmount + " " + targetCurrency,
                        Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createConversionMap();
        setContentView(R.layout.activity_main);
    }

    private void populateSpinners() {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.currencies, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner sourceSpinner = (Spinner)findViewById(R.id.sourceSpinner);
        Spinner targetSpinner = (Spinner)findViewById(R.id.targetSpinner);

        sourceSpinner.setAdapter(adapter);
        targetSpinner.setAdapter(adapter);
    }

    private void createConversionMap() {

        conversionMap = new HashMap<ConversionPair, Double>();
        conversionMap.put(new ConversionPair("USD", "EUR"), new Double(0.91));
        conversionMap.put(new ConversionPair("EUR", "USD"), new Double(1.09));
    }
}
