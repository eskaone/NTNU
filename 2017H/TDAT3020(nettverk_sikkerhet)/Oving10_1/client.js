function generateHash(password) {
    var options={keySize: 512/32, iterations: 4096};
    var salt = cryptoJS.lib.WordArray.random(128/8).toString();
    return cryptoJS.PBKDF2(password, salt, options).toString();
}

$('#login').on('click', function(){
    var payload = {username: $('#username').val(), password: $('#password').val()}
    console.log(payload);
    $.get('/login', payload, function(data) {
        window.localStorage.setItem('token', data.token);
        $.ajaxSetup({
            headers: {
                'x-access-token': data.token
            }
        });
    });
});

$('#secret').on('click', function(){
    var token = window.localStorage.getItem('token');    
    console.log(token);
    $.get('/secure', token, function(data) {
        
        console.log("data: " + data);
    });
});

// 

var token = window.localStorage.getItem('token');
