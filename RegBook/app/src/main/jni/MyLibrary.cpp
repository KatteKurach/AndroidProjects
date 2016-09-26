//
// Created by Ekaterina Kurach on 9/26/16.
//
#include "com_gerbook_regbook_MyNDK.h"
#include <stdio.h>
#include <string.h>

JNIEXPORT jstring JNICALL Java_com_gerbook_regbook_MyNDK_validate(JNIEnv* env, jobject, jstring a) {
    const char *nativeString = env->GetStringUTFChars(a, JNI_FALSE);

    int length = strlen(nativeString);

    if (length > 13){
        return env->NewStringUTF("It's very long number.");
    }
    if (length < 13){
        return env->NewStringUTF("It's too shot.");
    }
    int k = 1;
    char num[10] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    for (int i = 0; i < length; i++){
        int count = 0;
        for (int j = 0; j < 10; j++){
            if (nativeString[i] != num[j]){
                count++;
            }
        }
        if (count == 10){
            return env->NewStringUTF("Try again, you write letters.");
        }
        if (nativeString[i] == '+'){
            return env->NewStringUTF("Loose +.");
        }
        if (nativeString[k] != '3' || nativeString[k + 1] != '7'
            || nativeString[k + 2] != '5') {
            return env->NewStringUTF("Loose 375");
        }
    }
    return env->NewStringUTF("good");
}

