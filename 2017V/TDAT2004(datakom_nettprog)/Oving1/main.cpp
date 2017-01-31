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
  cout << "Find all primes between " << from << " - " << to << ":" << endl;
  for (int i = 0; i < (int)primes.size(); i++) {
    cout << primes.at(i) << endl;
  }
}

void findPrimesWithThreads(int from, int to, int amt) {
  vector<thread> threads;
  mutex primes_mutex;

  for (int i = 0; i < amt; ++i) {
    threads.emplace_back(thread([&] {
      lock_guard<mutex> lock(primes_mutex);
      findPrimes(from, to);
    }));
  }
  for (int i = 0; i < (int)threads.size(); i++) {
    threads.at(i).join();
  }
}


// for(int i = 0; i < (int)threads.size(); i++) {
//   threads.at(i).join();
// }

// for(int i = 0; i < (int)primes.size(); i++) {
//   cout << primes.at(i) << endl;
// }


// void findPrimes(int from, int to, int threadAmt) {
//   cout << "Find all primes between " << from << " - " << to << ":\n";
//   vector<int> primes;
//   vector<thread> threads;
//   mutex primes_mutex;

//   for(int i = 0; i < threadAmt; ++i) {
//     threads.emplace_back(thread([&](){
//       for (int i = from; i <= to; i++) {
//         if(isPrime(i)) {
//           lock_guard<mutex> lock(primes_mutex);
//           primes.emplace_back(i);
//         }
//       }
//     }));
//   }

//   for(int i = 0; i < (int)threads.size(); i++) {
//     threads.at(i).join();
//   }

//   for(int i = 0; i < (int)primes.size(); i++) {
//     cout << primes.at(i) << endl;
//   }
// }


int main() {
  // findPrimes(2, 300);
  findPrimesWithThreads(2, 100, 4);


  // vector<int> primes;
  // mutex primes_mutex;


  // thread t1 = thread([&]() {
  //   for (int i = 2; i <= 30; i++) {
  //     if (isPrime(i)) {
  //       for(int j = 0; j < (int)primes.size(); j++) {
  //         if(primes.at(j) != i) {
  //           lock_guard<mutex> lock(primes_mutex);
  //           primes.emplace_back(i);
  //         }
  //       }
        
  //     }
  //   }
  // });

  // thread t2 = thread([&]() {
  //   for (int i = 2; i <= 30; i++) {
  //     if (isPrime(i)) {
  //       for(int j = 0; j < (int)primes.size(); j++) {
  //         if(primes.at(j) != i) {
  //           lock_guard<mutex> lock(primes_mutex);
  //           primes.emplace_back(i);
  //         }
  //       }
        
  //     }
  //   }
  // });

  // thread t3 = thread([&]() {
  //   for (int i = 2; i <= 30; i++) {
  //     if (isPrime(i)) {
  //       lock_guard<mutex> lock(primes_mutex);
  //       primes.emplace_back(i);
  //     }
  //   }
  // });

  // t1.join();
  // t2.join();
  // t3.join();

  // cout << "Find all primes between " << 2 << " - " << 30 << ":\n";
  // for (int i = 0; i < (int)primes.size(); i++) {
  //   cout << primes.at(i) << "\n";
  // }
}