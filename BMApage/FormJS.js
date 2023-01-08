let httpRequest, username, password, url;

const main = function(){
    const botann = document.getElementById("login");
    botann.addEventListener("click", executor);
}

const handler = function(){
    if(httpRequest.readyState === 4 && httpRequest.status === 200){
        console.log(httpRequest.responseText);
    }
}

const executor = function(){
    console.log("executing\n");
    httpRequest = new XMLHttpRequest();
    httpRequest.onreadystatechange = handler;
    url = "http://localhost:8080/Servlet-demo/hello";
    httpRequest.open("POST", url);
    httpRequest.setRequestHeader('Content-Type', 'application/json');
    username = document.getElementById("loginUsername").value;
    password = document.getElementById("loginPassword").value;
    const data = {
        "username":username,
        "password":password
    }
    httpRequest.send(JSON.stringify(data));
    const string = JSON.stringify(data)
    console.log(typeof data, typeof string, typeof JSON.parse(string));
}

export{
    main,
}