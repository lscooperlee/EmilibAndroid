#include <jni.h>
#include <string>

#include "emi.h"

extern "C"
JNIEXPORT jint JNICALL
Java_com_leels_emilib_EmiMsg_sendmsg(
        JNIEnv *env, jobject /* this */,
        jstring ip, jint msg_num, jint cmd){

    auto *ipaddr = env->GetStringUTFChars(ip, NULL);
    auto ptr = make_emi_msg(ipaddr, msg_num, cmd, nullptr, 0, 0);
    return emi_msg_send(ptr.get());
}
