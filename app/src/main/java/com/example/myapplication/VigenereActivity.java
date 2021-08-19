package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class VigenereActivity extends AppCompatActivity {
    String text;
    String result="";
    EditText editText;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vigenere);
        getSupportActionBar().setTitle("Vigenere Cipher");
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        text = bundle.getString("text");
        editText = findViewById(R.id.editkey2);
    }

    public void encrypt(View view) {
        if(editText.length() == 0)
        { editText.setError("Enter the key"); }
        else {
            String key = editText.getText().toString();
            key = key.toUpperCase();
            text = text.toUpperCase();
            for (int i = 0, j = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                if (c < 'A' || c > 'Z')
                    continue;
                result += (char) ((c + key.charAt(j) - 2 * 'A') % 26 + 'A');
                j = (j + 1) % key.length();
            }
            textView = findViewById(R.id.show2);
            textView.setText(result);
        }
    }
    public void decrypt(View view) {
        if(editText.length() == 0)
        { editText.setError("Enter the key"); }
        else {
            String key = editText.getText().toString();
            key = key.toUpperCase();
            text = text.toUpperCase();
            for (int i = 0, j = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                if (c < 'A' || c > 'Z')
                    continue;
                result += (char) ((c - key.charAt(j) + 26) % 26 + 'A');
                j = (j + 1) % key.length();
            }
            textView = findViewById(R.id.show2);
            textView.setText(result);
        }
    }
}
