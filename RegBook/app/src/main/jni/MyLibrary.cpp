//
// Created by Ekaterina Kurach on 9/26/16.
//
#include "com_gerbook_regbook_MyNDK.h"

JNIEXPORT jboolean JNICALL Java_com_gerbook_regbook_MyNDK_getStat(JNIEnv* env, jobject, jstring a) {
    const char *nativeString = env->GetStringUTFChars(a, JNI_FALSE);
    if (nativeString == "+"){
        return true;
    } else {
        return false;
    }

}

