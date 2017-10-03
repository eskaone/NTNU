var _ = require('lodash');
var express = require('express');
var bodyParser = require('body-parser');
var logger = require('morgan');
var cryptoJS = require('crypto-js');
var fs = require('fs');
var https = require('https');
var pug = require('pug');
var jwt = require('jsonwebtoken');
var app = express();
var router = express.Router();

var credentials = {
    key: fs.readFileSync('sslcert/server.key', 'utf8'),
    cert: fs.readFileSync('sslcert/server.crt', 'utf8')
};

var users = [
    {
        id: 1,
        name: 'admin',
        salt: "3301f9a59fdd1034bfbdfbca48f8f2b6",
        hash: "99a1d079689031a03f0b842e339ee2d3f6f698177158028a82003211bb6491c0ee4fd21c2eab33d44c42786c77c7bba39d355f47c2a0803b3d540b00cbc2d9a5"
    }
];

app.set('views', __dirname + '/views')
app.set('view engine', 'pug')

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
    extended: true
}));
app.use(express.static('./'));
app.use(logger('dev'));

var server = https.createServer(credentials, app);

//routes
router.post('/login',(req,res)=>{
    console.log(req.body);
    var user = _.find(users, {name: req.body.username});
    console.log(user);
    if(user && validPassword(req.body.password, user)){
        user.token = jwt.sign({id: user.id}, 'secret', {expiresIn: 30});
        var token = jwt.sign({id: user.id}, 'secret', {expiresIn: 30});
        res.status(200).json({token: token, message: 'Token given!'});
    } else {
        res.status(401).send("Failed to log in.");    
    }
});

router.post('/register', function (req, res) {
    if(req.body.pword === req.body.pword_repeat && !_.find(users, {name: req.body.username})) {
        var username = req.body.username;
        var password = req.body.password;
        var salt = cryptoJS.lib.WordArray.random(128/8).toString();
        users.push({
            id: users.length+1,
            name: username,
            salt: salt,
            hash: generateHash(password, salt)
        });
        var user = users[_.findIndex(users, {name: username})];
        var token = jwt.sign({id: user.id}, 'secret', {expiresIn: 30});
        user.token = token;
        res.status(200).json({token: token, message: 'Token given!'});
    } else {
        res.status(403).send("Failed to register.");
    }
});

router.get('/', (req,res)=>{
    res.redirect('/login');
});

router.get('/login', (req,res)=>{
    res.render('login', {title: 'Log in'});
});

router.get('/register', (req,res)=>{
    res.render('register', {title: 'Register user'});
});

router.post('/logout',(req,res)=>{
    //unauthorize user here
    res.redirect('/');
});

router.use(function(req, res, next){
    var token = req.body.token || req.query.token || req.headers['x-access-token'];
    console.log('token from req body: ' + token)
    if(token) {
        jwt.verify(token, 'secret', function(err, decoded){
            if(err) {
                return res.json({ success: false, message: 'Failed to authenticate token.' });
            } else {
                req.decoded = decoded;    
                next();
            }
        });
        
    } else {
        return res.status(403).send({ 
            success: false, 
            message: 'No token provided.' 
        });
    }
});

router.get('/secret', (req,res)=>{
    res.render('secret', {data: users});
});

function generateHash(password, salt) {
    var options={keySize: 512/32, iterations: 4096};
    return cryptoJS.PBKDF2(password, salt, options).toString();
}

function validPassword(password_input, user) {
    if(user && user.hash.toString() === generateHash(password_input, user.salt)) {
       return true;
    } else {
       return false;
    }
}

function issueToken(payload) {
    var token = jwt.sign(payload, process.env.TOKEN_SECRET || "I hope this is secure enough");
    console.log(token);
    return token;
}

function verifyToken(token) {
    return jwt.verify(token, process.env.TOKEN_SECRET || "I hope this is secure enough");
}

app.use('/', router);


//start server
server.listen(8080, function () {
  console.log('Server running on 8080')
})