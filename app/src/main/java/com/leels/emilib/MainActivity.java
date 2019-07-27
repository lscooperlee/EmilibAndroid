package com.leels.emilib;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    HashMap<Integer, EmiMsg> msgMap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        msgMap = new HashMap<>();

        Button bt1 = (Button) findViewById(R.id.button1);

        bt1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent;
                intent = new Intent(MainActivity.this, ConfigActivity.class);
                startActivityForResult(intent, 0);

                return false;
            }
        });

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmiMsg msg = new EmiMsg("127.0.0.1", 1, 2, null, 0);
                int ret = msg.send();
                Log.d("ttt", "onClick: " + Integer.toString(ret));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {

                String addr = data.getStringExtra("addr");
                int msgnum = Integer.parseInt(data.getStringExtra("msg"));
                int cmd = Integer.parseInt(data.getStringExtra("cmd"));

                EmiMsg msg = new EmiMsg(addr, msgnum, cmd, null, 0);
            }
        }
    }
}
