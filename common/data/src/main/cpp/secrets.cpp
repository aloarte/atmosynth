#include "secrets.h"
#include <cstring>

#define API_KEY ""
#define API_KEY_LENGTH strlen(API_KEY)

extern "C" JNIEXPORT jstring JNICALL
Java_com_devalr_data_Secrets_getApiKeyFromNative(JNIEnv* env, jobject thiz) {
    char buffer[API_KEY_LENGTH];

    std::memcpy(buffer, API_KEY, API_KEY_LENGTH);

    return env->NewStringUTF(buffer);
}