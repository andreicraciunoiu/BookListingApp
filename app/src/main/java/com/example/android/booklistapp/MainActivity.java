package com.example.android.booklistapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private String bookTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button searchButton = (Button) findViewById(R.id.search_button);
        editText = (EditText)findViewById(R.id.search_title);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookTitle = editText.getText().toString();
                Intent i = new Intent(getApplicationContext(), RequestResult.class);
                i.putExtra("TITLE",bookTitle);
                startActivity(i);
            }
        });
    }
}