package com.leels.emilib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        Button bt = (Button) findViewById(R.id.button);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmiMsg msg = new EmiMsg("127.0.0.1", 1, 2, null, 0);
                int ret = msg.send();
                Log.d("ttt", "onClick: " + Integer.toString(ret));
            }
        });
    }
}
