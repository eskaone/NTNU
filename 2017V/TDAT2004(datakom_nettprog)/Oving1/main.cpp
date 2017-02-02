#include <iostream>
#include <thread>
#include <mutex>
#include <vector>
#include <cmath>


using namespace std;

bool isPrime(int x) {
  int TOP = static_cast<int>(sqrt(static_cast<double>(x))) + 1;
  for (int i = 2; i != TOP; ++i) {
    if (x % i == 0)
      return false;
  }
  return true;
}

void findPrimes(int from, int to) {
  vector<int> primes;
  
  for (int i = from; i <= to; i++) {
    if (isPrime(i)) {
      primes.emplace_back(i);
    }
  }
  cout << "\nFind all primes between " << from << " - " << to << ":" << endl;
  for (int i = 0; i < (int)primes.size(); i++) {
    cout << primes.at(i) << endl;
  }
}

void findPrimesWithThreads(int from, int to, int amt) {
  vector<thread> threads;
  mutex primes_mutex;
  int length = to - from;
  int split = length / amt;
  int splitCurr = split;
  int fromVar = from;

  for (int i = 0; i < amt; ++i) {
    threads.emplace_back(thread([&] {
      lock_guard<mutex> lock(primes_mutex);
      findPrimes(fromVar, splitCurr);
      fromVar = splitCurr+1;
      splitCurr += split;
    }));
  }
  for (int i = 0; i < (int)threads.size(); i++) {
    threads.at(i).join();
  }
}

int main() {
  // findPrimes(2, 300);
  findPrimesWithThreads(2, 1000, 5);

}