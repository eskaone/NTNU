#include <iostream>
#include <thread>
#include <mutex>
#include <vector>
#include <cmath>


using namespace std;

bool isPrime(int x) {
  int TOP = static_cast<int>(sqrt(static_cast<double>(x))) + 1;
  for ( int i=2; i != TOP; ++i ) {
    if (x % i == 0) return false;
  }
  return true;
}

/*
vector<int> findPrimes(int from, int to) {

  vector<int> primes;
  
  for (int i = from; i <= to; i++) {
    if(isPrime(i)) {
      primes.emplace_back(i);
    }
  }
  return primes;
}

vector<thread> createThreads(int amt) {
  vector<thread> threads;
  
  for(int i = 0; i < amt; ++i) {
    threads.emplace_back(thread([&](){
      // task??
    }));
  }
  return threads;
}

void execute(int from, int to, int threadAmt) {
  vector<thread> threads = createThreads(threadAmt);
  for(int i = 0; i < (int)threads.size(); i++) {
    //add the calculate prime method to threads
    //threads.at(i) 
  }
}
*/

// for(int i = 0; i < (int)threads.size(); i++) {
//   threads.at(i).join();
// }

// for(int i = 0; i < (int)primes.size(); i++) {
//   cout << primes.at(i) << endl;
// }

//mutex primes_mutex;
//lock_guard<mutex> lock(primes_mutex);

//cout << "Find all primes between " << from << " - " << to << ":\n";





void findPrimes(int from, int to, int threadAmt) {
  cout << "Find all primes between " << from << " - " << to << ":\n";
  vector<int> primes;
  vector<thread> threads; 
  mutex primes_mutex;
  
  for(int i = 0; i < threadAmt; ++i) {
    threads.emplace_back(thread([&](){
      for (int i = from; i <= to; i++) {
        if(isPrime(i)) {
          lock_guard<mutex> lock(primes_mutex);
          primes.emplace_back(i);
        }
      }
    }));
  }
  
  for(int i = 0; i < (int)threads.size(); i++) {
    threads.at(i).join();
  }
  
  for(int i = 0; i < (int)primes.size(); i++) {
    cout << primes.at(i) << endl;
  }
}




int main() {
  findPrimes(2, 30, 2);
}