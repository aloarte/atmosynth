#ifndef SECRETS_H
#define SECRETS_H

#include <jni.h>

extern "C" JNIEXPORT jstring JNICALL
Java_com_devalr_data_Secrets_getAemetApiKeyFromNative(JNIEnv* env, jobject thiz, jobject assetManager);

extern "C" JNIEXPORT jstring JNICALL
Java_com_devalr_data_Secrets_getGeminiApiKeyFromNative(JNIEnv* env, jobject thiz, jobject assetManager);

#endif