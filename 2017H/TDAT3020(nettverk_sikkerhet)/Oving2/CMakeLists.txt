cmake_minimum_required (VERSION 2.8.8)
project (web-server-example)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++1y -Wall -Wextra -Wno-unused-parameter")

find_package(Threads REQUIRED)

find_package(Boost 1.53.0 COMPONENTS regex system thread date_time REQUIRED)
include_directories(SYSTEM ${Boost_INCLUDE_DIR})

add_executable(web_server main.cpp)
target_link_libraries(web_server ${CMAKE_THREAD_LIBS_INIT})
target_link_libraries(web_server ${Boost_LIBRARIES})
