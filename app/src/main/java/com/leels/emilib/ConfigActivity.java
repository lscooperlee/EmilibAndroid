package com.leels.emilib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ConfigActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config);

        Button bt = (Button) findViewById(R.id.button);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String addr = ((EditText) findViewById(R.id.editTextAddr)).getText().toString();
                String msg = ((EditText) findViewById(R.id.editTextMsg)).getText().toString();
                String cmd = ((EditText) findViewById(R.id.editTextCmd)).getText().toString();

                Intent returnIntent = new Intent();
                returnIntent.putExtra("addr",addr);
                returnIntent.putExtra("msg",msg);
                returnIntent.putExtra("cmd",cmd);

                returnIntent.putExtra("id", getIntent().getIntExtra("id", 0));

                setResult(Activity.RESULT_OK,returnIntent);
                finish();

            }
        });
    }
}
