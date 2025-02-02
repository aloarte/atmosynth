#include "secrets.h"
#include <fstream>
#include <sstream>
#include <jni.h>
#include <android/asset_manager.h>
#include <android/asset_manager_jni.h>

std::string readApiKeyFromAssets(AAssetManager* assetManager, const char* filename) {
    AAsset* asset = AAssetManager_open(assetManager, filename, AASSET_MODE_UNKNOWN);
    if (asset == nullptr) {
        return "";
    }

    off_t fileLength = AAsset_getLength(asset);
    std::string content(fileLength, '\0');
    AAsset_read(asset, &content[0], fileLength);

    AAsset_close(asset);
    return content;
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_devalr_data_Secrets_getAemetApiKeyFromNative(JNIEnv* env, jobject thiz, jobject assetManager) {
    AAssetManager* mgr = AAssetManager_fromJava(env, assetManager);
    const char* filePath = "aemet_api_key";

    std::string apiKey = readApiKeyFromAssets(mgr, filePath);

    return env->NewStringUTF(apiKey.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_devalr_data_Secrets_getGeminiApiKeyFromNative(JNIEnv* env, jobject thiz, jobject assetManager) {
    AAssetManager* mgr = AAssetManager_fromJava(env, assetManager);
    const char* filePath = "gemini_api_key";

    std::string apiKey = readApiKeyFromAssets(mgr, filePath);

    return env->NewStringUTF(apiKey.c_str());
}