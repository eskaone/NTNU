var _ = require("lodash");
var fs = require('fs');
var https = require('https');
var express = require('express');
var logger = require('morgan');
var bodyParser = require('body-parser');
var cryptoJS = require('crypto-js');
var jwt = require('jsonwebtoken');

var users = [
    {
        id: 1,
        name: 'admin',
        salt: "bc9d872f5085c4ae56c02573d16c1c8a",
        hash: "38601c388caf55d3445e3ef51ee84573523d21b122f08194ed6b0ea4336b11687bc9f01985c2d5f44c3ffb2a20c8980fad3e80edc664c1a4cef940dedb911044"
    }
];

var token = null;
var secret = 'IHopeThisIsSecureEnough';

var credentials = {
    key: fs.readFileSync('sslcert/server.key', 'utf8'),
    cert: fs.readFileSync('sslcert/server.crt', 'utf8')
};

var app = express();
var port = process.env.PORT || '8080';

app.use(logger('dev'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

var server = https.createServer(credentials, app);

//routers
app.get('/', function (req, res) {
    res.sendFile('views/login.html', {root: __dirname })
});

app.get('/register', function (req, res) {
    res.sendFile('views/register.html', {root: __dirname })
});

app.get('/auth', function (req, res) {
    res.json({token: token});
});

app.get('/secret', function(req, res) {
    console.log('token stored: ' + token);
    if(token) {
        jwt.verify(token, secret, function(err, decoded){
            if(err) {
                return res.json({ success: false, message: 'Failed to authenticate token.' });
            } else {
                console.log("token accepted!");
                return res.status(200).json({message: 'you need to login to see this, I hope.. Expires in 1 min!', token: token}); 
            }
        });
    } else {
        return res.status(403).send({ 
            success: false, 
            message: 'No token provided.' 
        });
    }
});

app.post('/login', function (req, res) {
    var username = req.body.username;
    var hash = req.body.hash;
    var user = users[_.findIndex(users, {name: username})];

    if(user && validPassword(hash, username)) {
        var payload = {id: user.id};
        token = issueToken(payload);
        res.status(200).json({token: token});
    } else {
        res.sendStatus(403);
    }
});

app.post('/register', function(req, res){
    var hashedPassword = req.body.hash;
    var salt = cryptoJS.lib.WordArray.random(128/8).toString();
    users.push({
            id: users.length+1,
            name: req.body.salt,
            salt: salt,
            hash: generateHash(hashedPassword, salt)
        });
    console.log(users[users.length-1]);
    var payload = {id: users[users.length-1].id};
    token = issueToken(payload);
    res.status(200).json({token: token});
});

function generateHash(password, salt) {
    var options={keySize: 512/32, iterations: 4096};
    return cryptoJS.PBKDF2(password, salt, options).toString();
}

function validPassword(password, username) {
    var user = users[_.findIndex(users, {name: username})];
    if(user && user.hash.toString() === generateHash(password, user.salt)) {
       return true;
    } else {
       return false;
    }
}

function issueToken(payload) {
    var token = jwt.sign(payload, secret, {expiresIn: 60});
    return token;
}

function verifyToken(token, verified) {
    return jwt.verify(token, secret, {}, verified);
}

server.listen(port);
