#include "crypto.hpp"
#include <cassert>

using namespace std;

int main() {
  // Results copied from https://caligatio.github.io/jsSHA/
  assert(Crypto::hex(Crypto::sha1("Test")) == "640ab2bae07bedc4c163f679a746f7ab7fb5d1fa");
  assert(Crypto::hex(Crypto::sha1(Crypto::sha1("Test"))) == "af31c6cbdecd88726d0a9b3798c71ef41f1624d5");
  
  assert(Crypto::hex(Crypto::sha256("Test")) == "532eaabd9574880dbf76b9b8cc00832c20a6ec113d682299550d7a6e0f345e25");
  assert(Crypto::hex(Crypto::sha256(Crypto::sha256("Test"))) == "c60907e990745f7d91c4423713764d2724571269d3db4856d37c6302792c59a6");
  
  assert(Crypto::hex(Crypto::sha512("Test")) == "c6ee9e33cf5c6715a1d148fd73f7318884b41adcb916021e2bc0e800a5c5dd97f5142178f6ae88c8fdd98e1afb0ce4c8d2c54b5f37b30b7da1997bb33b0b8a31");
  assert(Crypto::hex(Crypto::sha512(Crypto::sha512("Test"))) == "1d114c408ea5a48bc013c6947b042b31706589852608b62b77ccfbdef879feddb1d18bc416fdf0e878a6f72b5ee131f1ef59bb6070201e46a07d1fbaaf1d03f7");
  
  // Results copied from http://www.miraclesalad.com/webtools/md5.php
  assert(Crypto::hex(Crypto::md5("Test")) == "0cbc6611f5540bd0809a388dc95a615b");
  //assert(Crypto::hex(Crypto::md5(Crypto::md5("Test"))) == "5ec20daa6b4e614f2cf221347cf1fe41");

  // Results copied from http://www.freecodeformat.com/pbkdf2.php
  assert(Crypto::hex(Crypto::pbkdf2("QwE", "Saltet til Ola", 2048, 20)) == "ab29d7b5c589e18b52261ecba1d3a7e7cbf212c6");
  assert(Crypto::hex(Crypto::pbkdf2("Password", "Salt", 4096, 256 / 8)) == "f66df50f8aaa11e4d9721e1312ff2e66433a399c2c4b46d937b84e43cc9fc86e");
  assert(Crypto::hex(Crypto::pbkdf2("Password", "Salt", 8192, 512 / 8)) == "a941ccbc34d1ee8ebbd1d34824a419c3dc4eac9cbc7c36ae6c7ca8725e2b618a6ad22241e787af937b0960cf85aa8ea3a258f243e05d3cc9b08af5dd93be046c");
}
