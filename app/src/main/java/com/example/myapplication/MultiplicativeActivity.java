package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MultiplicativeActivity extends AppCompatActivity {
    EditText editText;
    String text;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplicative);
        getSupportActionBar().setTitle("Multiplicative Cipher");
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        text = bundle.getString("text");
        editText = (EditText)findViewById(R.id.editkey3);
    }
    public void encrypt(View view){
        if(editText.length() == 0)
        { editText.setError("Enter the key"); }
        else {
            int key = Integer.parseInt(editText.getText().toString());
            String res = "";
            for (int i = 0; i < text.length(); i++) {
                if (Character.isUpperCase(text.charAt(i))) {
                    char ch = (char) (((int) text.charAt(i) * key - 65) % 26 + 65);
                    res = res + ch;
                } else if (Character.isLowerCase(text.charAt(i))) {
                    char ch = (char) (((int) text.charAt(i) * key - 97) % 26 + 97);
                    res = res + ch;
                } else
                    res = res + text.charAt(i);
            }
            textView = findViewById(R.id.show3);
            textView.setText(res);
        }
    }
    public void decrypt(View view){
        if(editText.length() == 0)
        { editText.setError("Enter the key"); }
        else {
            int key = Integer.parseInt(editText.getText().toString());
            if (gcd(key, 26) != 1) {
                Toast.makeText(MultiplicativeActivity.this, "Please Enter the Key whose gcd with 26 is 1", Toast.LENGTH_SHORT).show();
                return;
            }
            int q = 0;
            String res = "";
            for (int i = 0; i < 26; i++) {
                if (((i * 26) + 1) % key == 0) {
                    q = ((i * 26) + 1) / key;
                    break;
                }
            }
            for (int i = 0; i < text.length(); i++) {
                if (Character.isUpperCase(text.charAt(i))) {
                    char ch = (char) (((int) text.charAt(i) * q - 65) % 26 + 65);
                    res = res + ch;
                } else if (Character.isLowerCase(text.charAt(i))) {
                    char ch = (char) (((int) text.charAt(i) * q - 97) % 26 + 97);
                    res = res + ch;
                } else
                    res = res + text.charAt(i);
            }
            textView = findViewById(R.id.show3);
            textView.setText(res);
        }
    }
    int gcd(int a,int b)
    {
        if(b==0)
            return a;
        return gcd(b,a%b);
    }
}