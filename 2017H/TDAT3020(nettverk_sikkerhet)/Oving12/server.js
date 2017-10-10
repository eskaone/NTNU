var express = require('express');
var bodyParser = require('body-parser');
var logger = require('morgan');
var fs = require('fs');
var https = require('https');
var app = express();

var credentials = {
    key: fs.readFileSync('sslcert/server.key', 'utf8'),
    cert: fs.readFileSync('sslcert/server.crt', 'utf8')
};

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
    extended: true
}));
app.use(express.static('./'));
app.use(logger('dev'));

var server = https.createServer(credentials, app);

app.get('/', function(req, res) {
    res.sendFile('views/index.html', {root: __dirname });
});

//start server
server.listen(8080, function () {
  console.log('Server running on 8080')
})