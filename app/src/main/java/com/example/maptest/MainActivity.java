package com.example.maptest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Map;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {

    private EditText etChoseCountry;
    private EditText etEnterCountry;
    private EditText etEnterCapital;
    private Button btnAdd;
    private TextView tvCapital;
    private TextView tvCapName;
    private TextView tvAllCountries;

    private Map<String, String> countries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etChoseCountry = findViewById(R.id.editTextChoseCountry);
        etEnterCountry   = findViewById(R.id.EditTextEnterCountry);
        etEnterCapital = findViewById(R.id.EditTextEnterCapital);
        btnAdd = findViewById(R.id.buttonAdd);
        tvCapital = findViewById(R.id.textViewCapital);
        tvCapName = findViewById(R.id.textViewCapName);
        tvAllCountries = findViewById(R.id.tvAllCountries);
        countries = new TreeMap<>();

        etChoseCountry.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String capital = countries.get(charSequence.toString());
                if(capital != null) {
                    tvCapital.setText(capital);
                } else {
                    tvCapital.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fieldsAreFilled()) {
                    countries.put(etEnterCountry.getText().toString().trim(), etEnterCapital.getText().toString().trim());
                    etEnterCountry.setText("");
                    etEnterCapital.setText("");
                    showAllCountries();
                }
            }
        });
    }


    private boolean fieldsAreFilled() {
        return etEnterCapital.getText() !=null && !etEnterCapital.getText().toString().trim().isEmpty()
                && etEnterCountry.getText() != null && !etEnterCountry.getText().toString().trim().isEmpty();
    }

    private void showAllCountries() {
        tvAllCountries.setText("");
        StringBuilder builder = new StringBuilder();
        for(String country : countries.keySet()) {
            builder.append(country).append(" - ").append(countries.get(country)).append("\n");
        }
        tvAllCountries.setText(builder.toString());
    }
}