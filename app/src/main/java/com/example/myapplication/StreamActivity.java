package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Map;

public class StreamActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;
    String text;
    Map<Character,String> m = new HashMap<Character, String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stream);
        getSupportActionBar().setTitle("Stream Cipher");
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        text = bundle.getString("text");
        editText = findViewById(R.id.editkey5);
        for(char i='A';i<='Z';i++){
            String temp = Integer.toBinaryString(i);
            String s="";
            if(temp.length()!=8)
            {
                for(int j=0;j<8-temp.length();j++)
                    s+="0";
            }
            m.put(i,s+temp);
        }

        for(char i='a';i<='z';i++){
            String temp = Integer.toBinaryString(i);
            String s="";
            if(temp.length()!=8)
            {
                for(int j=0;j<8-temp.length();j++)
                    s+="0";
            }
            m.put(i,s+temp);
        }
        m.put(' ',"00100000");
    }
    public void encrypt(View view){
        if(editText.length() == 0)
            editText.setError("Enter the key");
        else {
            String key = editText.getText().toString();
            String binaryString = "";
            String keyBinaryString = "";
            for (int i = 0; i < text.length(); i++) {
                binaryString += m.get(text.charAt(i));
            }
            for (int i = 0, j = 0; i < binaryString.length(); i++) {
                keyBinaryString += key.charAt(j);
                j = (j + 1) % key.length();
            }

            String result = "";
            for (int i = 0; i < binaryString.length(); i++) {
                int t = (int) binaryString.charAt(i);
                int k = (int) keyBinaryString.charAt(i);
                int r = (t ^ k);
                result += r;
            }
            textView = findViewById(R.id.show5);
            textView.setText(result);
        }
    }
    public void decrypt(View view){
        if(editText.length() == 0)
            editText.setError("Enter the key");
        else{
            String key = editText.getText().toString();
            String keyBinaryString = "";
            for (int i = 0, j = 0; i < text.length(); i++) {
                keyBinaryString += key.charAt(j);
                j = (j + 1) % key.length();
            }
            String res="";
            for(int i=0;i<text.length();i+=8)
            {
                String s="";
                for(int j=i;j<i+8;j++)
                {
                    int t=(int)text.charAt(j);
                    int k=(int)keyBinaryString.charAt(j-i);
                    int r=(t^k);
                    s+=r;
                }
                    int parseInt = Integer.parseInt(s, 2);
                    char c = (char) parseInt;
                    res += c;
                }
            textView = findViewById(R.id.show5);
            textView.setText(res);
            }
        }
}