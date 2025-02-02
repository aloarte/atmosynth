project(secrets)

cmake_minimum_required(VERSION 3.4.1)

add_library(
        secrets
        SHARED
        src/main/cpp/secrets.cpp
)

include_directories(src/main/cpp)