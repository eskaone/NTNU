cmake_minimum_required(VERSION 2.8)

project(Oving1)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++1y -pthread -Wall -Wextra -Wno-unused-parameter")

add_executable(Oving1 main.cpp)
