package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AutokeyActivity extends AppCompatActivity {
    EditText editText;
    String text;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autokey);
        getSupportActionBar().setTitle("Autokey Cipher");
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        text = bundle.getString("text");
        text = text.replaceAll("\\s","");
        editText = findViewById(R.id.editkey6);
    }
    public void encrypt(View view){
        if(editText.length() == 0)
        { editText.setError("Enter the key"); }
        else {
            final String alphabet = "abcdefghijklmnopqrstuvwxyz";
            String key = editText.getText().toString();
            if (key.matches("[-+]?\\d*\\.?\\d+"))
                key = "" + alphabet.charAt(Integer.parseInt(key));
            String newkey = key.concat(text);
            newkey = newkey.substring(0,newkey.length()-key.length());
            String encryptMsg = "";

            // applying encryption algorithm
            for (int x = 0; x < text.length(); x++) {
                int first = alphabet.indexOf(text.charAt(x));
                int second = alphabet.indexOf(newkey.charAt(x));
                int total = (first + second) % 26;
                encryptMsg += alphabet.charAt(total);
            }
            textView = findViewById(R.id.show6);
            textView.setText(encryptMsg);
        }
    }
    public void decrypt(View view){
        if(editText.length() == 0)
        { editText.setError("Enter the key"); }
        else {
            final String alphabet = "abcdefghijklmnopqrstuvwxyz";
            String key=editText.getText().toString();
            if (key.matches("[-+]?\\d*\\.?\\d+"))
                key = "" + alphabet.charAt(Integer.parseInt(key));
            String decryptMsg = "";
            for (int x = 0; x < text.length(); x++) {
                int get1 = alphabet.indexOf(text.charAt(x));
                int get2 = alphabet.indexOf(key.charAt(x));
                int total = (get1 - get2) % 26;
                total = (total < 0) ? total + 26 : total;
                decryptMsg += alphabet.charAt(total);
                key += alphabet.charAt(total);
            }
            textView = findViewById(R.id.show6);
            textView.setText(decryptMsg);
        }
    }
}