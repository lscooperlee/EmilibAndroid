package com.leels.emilib;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "EMIMSG";
    HashMap<Integer, EmiMsg> msgMap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (msgMap == null) {
            msgMap = new HashMap<Integer, EmiMsg>();
            msgMap.put(R.id.button1, new EmiMsg("192.168.1.250", 1, 1, null, 0));
            msgMap.put(R.id.button2, new EmiMsg("192.168.1.250", 1, 1, null, 0x00000100));

            msgMap.put(R.id.button3, new EmiMsg("192.168.1.251", 1, 1, null, 0));
            msgMap.put(R.id.button4, new EmiMsg("192.168.1.251", 1, 1, null, 0x00000100));

            msgMap.put(R.id.button5, new EmiMsg("192.168.1.252", 1, 1, null, 0));
            msgMap.put(R.id.button6, new EmiMsg("192.168.1.252", 1, 1, null, 0x00000100));
        }

        for(HashMap.Entry<Integer, EmiMsg> entry: msgMap.entrySet()) {

            final int id = entry.getKey();

            Button bt = (Button) findViewById(id);

            bt.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Intent intent;
                    intent = new Intent(MainActivity.this, ConfigActivity.class);
                    intent.putExtra("id", id);
                    startActivityForResult(intent, 0);

                    return false;
                }
            });

            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EmiMsg msg = msgMap.get(id);
                    int ret = msg.send();
                    Log.d(TAG, "MSG: "+msg.addr + ": " +Integer.toString(msg.msg) + ": "+Integer.toString(msg.cmd));
                    Log.d(TAG, "SEND: "+Integer.toString(ret));
                }
            });

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {

                String addr = data.getStringExtra("addr");
                int msgnum = Integer.parseInt(data.getStringExtra("msg"));
                int cmd = Integer.parseInt(data.getStringExtra("cmd"));

                int flag = data.getStringExtra("check").equals("true") ? 0x00000100 : 0;

                EmiMsg msg = new EmiMsg(addr, msgnum, cmd, null, flag);
                int id = data.getIntExtra("id", 0);

                Log.d(TAG, "onActivityResult: "+Integer.toString(id));
                Log.d(TAG, "MSG save: "+msg.addr + ": " +Integer.toString(msg.msg) + ": "+Integer.toString(msg.cmd) +": " + Integer.toString(msg.flag));

                msgMap.put(id, msg);

            }
        }
    }
}
