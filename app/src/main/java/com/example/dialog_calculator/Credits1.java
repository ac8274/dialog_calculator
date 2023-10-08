package com.example.dialog_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Credits1 extends AppCompatActivity {

    TextView text;

    Intent gi = getIntent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits1);
        text = findViewById(R.id.textView4);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        menu.add("Calculator");

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        finish();
        return super.onOptionsItemSelected(item);
    }

}