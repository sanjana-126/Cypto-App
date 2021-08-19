package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class TranspositionActivity extends AppCompatActivity {
    String text;
    EditText editText;
    TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transposition);
        getSupportActionBar().setTitle("Transposition Cipher");
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        text = bundle.getString("text");
    }

    public void encrypt(View view){
        editText = findViewById(R.id.editkey4);
        if(editText.length() == 0)
            editText.setError("Enter the key");
        else {
            String key = editText.getText().toString();
            String alpha = "abcdefghijklmnopqrstuvwxyz";


            //converting key into numbers
            int[] keynumber = new int[key.length()];
            int init = 0;
            for (int i = 0; i < alpha.length(); i++) {
                for (int j = 0; j < key.length(); j++) {
                    if (alpha.charAt(i) == key.charAt(j)) {
                        keynumber[j] = init;
                        init++;
                    }
                }
            }

            //checking if plaintext will perfectly fit inside grid
            int extra = text.length() % key.length();
            int dummy = key.length() - extra;
            if (extra != 0) {
                for (int i = 0; i < dummy; i++) {
                    text += "_";
                }
            }

            //calculating number of rows
            int row = text.length() / key.length();

            //creating grid
            char[][] grid = new char[row][key.length()];

            //filling the grid
            int z = 0;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < key.length(); j++) {
                    grid[i][j] = text.charAt(z);
                    z++;
                }
            }

            //getting location of numbers based on keywords
            String numLoc = "";
            for (int i = 0; i < key.length(); i++) {
                for (int j = 0; j < key.length(); j++) {
                    if (keynumber[j] == i)
                        numLoc += j;
                }
            }

            //getting resultant string
            String res = "";
            for (int k = 0; k < key.length(); k++) {
                int d = Character.getNumericValue(numLoc.charAt(k));
                for (int i = 0; i < row; i++)
                    res += grid[i][d];
            }

            //show the encrypted string in textview
            textview = findViewById(R.id.show4);
            textview.setText(res);
        }
    }
    public void decrypt(View view){
        editText = findViewById(R.id.editkey4);
        if(editText.length() == 0)
            editText.setError("Enter the key");
        else {
            String key = editText.getText().toString();
            String alpha = "abcdefghijklmnopqrstuvwxyz";


            //calculating number of rows
            int row = text.length() / key.length();


            //converting key into numbers
            int[] keynumber = new int[key.length()];
            int init = 0;
            for (int i = 0; i < alpha.length(); i++) {
                for (int j = 0; j < key.length(); j++) {
                    if (alpha.charAt(i) == key.charAt(j)) {
                        keynumber[j] = init;
                        init++;
                    }
                }
            }

            //getting location of numbers based on keywords
            String numLoc = "";
            for (int i = 0; i < key.length(); i++) {
                for (int j = 0; j < key.length(); j++) {
                    if (keynumber[j] == i)
                        numLoc += j;
                }
            }

            //creating grid
            char[][] grid = new char[row][key.length()];
            for (int k = 0, i = 0; k < key.length(); k++) {
                int d = Character.getNumericValue(numLoc.charAt(k));
                for (int j = 0; j < row; j++, i++) {
                    grid[j][d] = text.charAt(i);
                }
            }

            String res = "";
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < key.length(); j++) {
                    res += grid[i][j];
                }
            }

            //show the encrypted string in textview
            textview = findViewById(R.id.show4);
            textview.setText(res);
        }
    }
}
