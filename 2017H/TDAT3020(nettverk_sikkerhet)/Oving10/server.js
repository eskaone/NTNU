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
        salt: "3301f9a59fdd1034bfbdfbca48f8f2b6",
        hash: "99a1d079689031a03f0b842e339ee2d3f6f698177158028a82003211bb6491c0ee4fd21c2eab33d44c42786c77c7bba39d355f47c2a0803b3d540b00cbc2d9a5"
    }
];

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

app.get('/users', function(req, res, next) {
    console.log(req.id);
    console.log(res.id);
    loginReq(req, res, next);
});

app.post('/users', function (req, res) {
    res.redirect("/");
});

app.post('/', function (req, res) {
    var username = req.body.uname;
    var password = req.body.pword;
    var user = users[_.findIndex(users, {name: username})];
    console.log(user);

    if(user && validPassword(password, username)) {
        var payload = {id: user.id};
        res.status(200).json({message: 'Success!' ,token: jwt.sign(payload, 'secret')});
    } else {
        res.status(403).send("Log in failed.");
    }
});

app.post('/register', function (req, res) {
    if(req.body.pword === req.body.pword_repeat) {
        var username = req.body.uname;
        var password = req.body.pword;
        var salt = cryptoJS.lib.WordArray.random(128/8).toString();
        users.push({
            id: users.length+1,
            name: username,
            salt: salt,
            hash: generateHash(password, salt)
        });
        var user = users[_.findIndex(users, {name: username})];
        console.log(users);
        res.json(user);
    } else {
        res.status(403).send("Password don't match!");
    }
});

function loginReq(req, res, next) {
    if(req.user) {
        next();
    } else {
        return res.status(401).json({message: 'Unauthorized user!'});
    }
}

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
    var token = jwt.sign(payload, process.env.TOKEN_SECRET || " I hope this is secure enough");
    return token;
}

function verifyToken(token, verified) {
    return jwt.verify(token, process.env.TOKEN_SECRET || " I hope this is secure enough", {}, verified);
}

server.listen(port);
