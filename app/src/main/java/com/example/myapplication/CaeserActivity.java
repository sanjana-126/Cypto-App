package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class CaeserActivity extends AppCompatActivity {
    String text;
    StringBuffer modtext = new StringBuffer();
    EditText editText;
    TextView textView;
    int shift;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caeser);
        getSupportActionBar().setTitle("Caeser Cipher");
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        text = bundle.getString("text");
        editText = findViewById(R.id.editshift);
    }
    public void encrypt(View view){
        if(editText.length() == 0)
            editText.setError("Please enter shift");
        else {
            shift = Integer.parseInt(editText.getText().toString());
            for (int i = 0; i < text.length(); i++) {
                char ch = (char) (((int) (text.charAt(i) + shift - 33) % 94) + 33);
                modtext.append(ch);
            }
            String result = modtext.toString();
            textView = findViewById(R.id.show1);
            textView.setText(result);
        }
    }
    public void decrypt(View view){
        if(editText.length() == 0)
            editText.setError("Please enter shift");
        else{
            shift = Integer.parseInt(editText.getText().toString());
            for (int i = 0; i < text.length(); i++) {
                char ch = (char) (((int) (text.charAt(i) - shift - 33) % 94) + 33);
                modtext.append(ch);
            }
            String result = modtext.toString();
            textView = findViewById(R.id.show1);
            textView.setText(result);
        }
    }
}