let httpRequest, username, password, url;

const registerfunc = function(){
    const botann = document.getElementById("signup");
    const lenchecker = document.getElementById("signupUsername");
    const pschecker = document.getElementById("signupPassword");
    pschecker.addEventListener("blur", mimalen);
    lenchecker.addEventListener("blur", lenimpl);
    botann.addEventListener("click", executor);
}

const mimalen = function(){
    password = document.getElementById("signupPassword").value;
    const hint = document.getElementById("pshint");
    if(password.length < 6){
        hint.style.display = "";
    } else{
        hint.style.display = "none";
    }
}

const lenimpl = function(){
    username = document.getElementById("signupUsername").value;
    const hint = document.getElementById("signuphint");
    console.log("length = "+ username.length);
    if(username.length < 3){
        hint.style.display = "";
    } else{
        hint.style.display = "none";
    }
}

const handler = function(){
    if(httpRequest.readyState === 4 && httpRequest.status === 200){
        console.log(httpRequest.responseText);
        const result = document.getElementById("result");
        result.innerHTML = httpRequest.responseText;
        result.style.display = "initial";
    }
}

const executor = function(){
    console.log("executing\n");
    username = document.getElementById("signupUsername").value;
    password = document.getElementById("signupPassword").value;
    if(username.length < 3 || password.length < 6) return;
    httpRequest = new XMLHttpRequest();
    httpRequest.onreadystatechange = handler;
    url = "http://127.0.0.1:8080/Backend/register";
    httpRequest.open("POST", url);
    httpRequest.setRequestHeader('Content-Type', 'application/json');
    username = document.getElementById("signupUsername").value;
    password = document.getElementById("signupPassword").value;
    const data = {
        "username":username,
        "password":password
    }
    httpRequest.send(JSON.stringify(data));
    const string = JSON.stringify(data)
    console.log(typeof data, typeof string, typeof JSON.parse(string));
}

export{
    registerfunc,
}