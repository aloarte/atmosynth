project(secrets)

cmake_minimum_required(VERSION 3.4.1)

add_library(
        secrets
        SHARED
        src/main/cpp/secrets.cpp
)

include_directories(${ANDROID_NDK}/sources/android/native_app_glue)

include_directories(src/main/cpp)

target_link_libraries(
        secrets
        android
        log
)