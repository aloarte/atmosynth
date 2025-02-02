#ifndef SECRETS_H
#define SECRETS_H

#include <jni.h>

extern "C" JNIEXPORT jstring JNICALL
Java_com_devalr_data_Secrets_getApiKeyFromNative(JNIEnv* env, jobject thiz);

#endif // SECRETS_H