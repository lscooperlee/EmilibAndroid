package com.leels.emilib;

public class EmiMsg {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    String addr = null;
    int flag = 0;
    int msg = 0;
    int cmd = 0;
    byte[] data = null;

    public EmiMsg(String destIP, int msgNum, int cmd_, byte[] data_, int flag_) {
        addr = destIP;
        flag = flag_;
        msg = msgNum;
        cmd = cmd_;
        data = data_;
    }

    public int send() {
        return sendmsg(addr, msg, cmd, flag);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native int sendmsg(String ip, int id, int cmd, int flag);
}
