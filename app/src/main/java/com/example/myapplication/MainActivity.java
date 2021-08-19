package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    String[] list = {"Select Cipher","Caesar Cipher","Vigenere Cipher","Multiplicative Cipher","Transposition Cipher","Stream Cipher"
                    ,"Autokey Cipher"};
    int selecteditem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Cryptography");
        editText = findViewById(R.id.edittext);
        Spinner spinner = (Spinner)findViewById(R.id.cipherlist);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, list){
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = null;

                // If this is the initial dummy entry, make it hidden
                if (position == 0) {
                    TextView tv = new TextView(getContext());
                    tv.setHeight(0);
                    tv.setVisibility(View.GONE);
                    v = tv;
                }
                else {
                    // Pass convertView as null to prevent reuse of special case views
                    v = super.getDropDownView(position, null, parent);
                }

                // Hide scroll bar because it appears sometimes unnecessarily, this does not prevent scrolling
                parent.setVerticalScrollBarEnabled(false);
                return v;
            }
        };
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    selecteditem = position;
                }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void Submit(View view) {
        if (editText.length() == 0)
            editText.setError("Enter something");
        else {
            Bundle bundle = new Bundle();
            bundle.putString("text", editText.getText().toString());
            if (selecteditem == 1) {
                Intent intent = new Intent(this, CaeserActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            } else if (selecteditem == 2) {
                Intent intent = new Intent(this, VigenereActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            } else if (selecteditem == 3) {
                Intent intent = new Intent(this, MultiplicativeActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            } else if (selecteditem == 4) {
                Intent intent = new Intent(this, TranspositionActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            } else if (selecteditem == 5) {
                Intent intent = new Intent(this, StreamActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            } else if (selecteditem == 6) {
                Intent intent = new Intent(this, AutokeyActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }
    }
}