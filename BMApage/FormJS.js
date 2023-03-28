let httpRequest, username, password, url, a = false;
const reason = document.getElementById("reason");
const element = document.getElementById("info");

const main = function(){
    const botann = document.getElementById("login");
    const lenchecker = document.getElementById("loginUsername");
    lenchecker.addEventListener("blur", lenimpl);
    botann.addEventListener("click", executor);
}

const lenimpl = function(){
    reason.style.display = "none";
    username = document.getElementById("loginUsername").value;
    const hint = document.getElementById("hint");
    console.log("length = "+ username.length);
    if(username.length < 3){
        hint.style.display = "";
        a = false;
    } else{
        hint.style.display = "none";
        a = true;
    }
    console.log("a = " + a);
    console.log(a === true);
}

const handler = function(){
    if(httpRequest.readyState === 4 && httpRequest.status === 200){
        console.log(httpRequest.responseText);
        if(httpRequest.responseText === "0"){
            element.innerHTML = '<a href="signin.html"><i class="fa fa-lock"></i>登录 / 注册</a>';
            reason.style.display = "";
        } else{
            element.innerHTML = JSON.parse(httpRequest.responseText).username;
            reason.style.display = "none";
            location.assign("home-v3.html");
        }
    }
}

const executor = function(){
    reason.style.display = "none";
    if(a === false) return;
    console.log("executing\n");
    httpRequest = new XMLHttpRequest();
    httpRequest.onreadystatechange = handler;
    url = "http://127.0.0.1:8080/backend/login";

    httpRequest.withCredentials = true;
    
    httpRequest.open("POST", url);
    httpRequest.setRequestHeader('Content-Type', 'application/json');
    username = document.getElementById("loginUsername").value;
    password = document.getElementById("loginPassword").value;
    const data = {
        username: username,
        password: password,
    }
    httpRequest.send(JSON.stringify(data));
    const string = JSON.stringify(data)
    console.log(typeof data, typeof string, typeof JSON.parse(string));
}

export{
    main,
}