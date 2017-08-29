#include "server_http.hpp"

typedef SimpleWeb::Server<SimpleWeb::HTTP> HttpServer;

using namespace std;

int main() {
  // Construct HTTP-server at port 8080 using 1 thread, and without request timeout
  HttpServer server(8080, 1, 0);

  // Response to all GET requests
  server.resource["^.*$"]["GET"] = [](shared_ptr<HttpServer::Response> response, shared_ptr<HttpServer::Request> request) {
    std::string content = "<h1>The web server is working!</h1>";
    *response << "HTTP/1.1 200 OK\r\nContent-Length: " << content.length() << "\r\n\r\n" << content;
  };

  server.start();
}
