const httpRequest = new XMLHttpRequest();

const handler = function(){
    if(httpRequest.readyState === 4 && httpRequest.status === 200){
        console.log(httpRequest.responseText);
        const target = document.getElementById("info");
        if(httpRequest.responseText === "0"){
            target.innerHTML = '<a href="signin.html"><i class="fa fa-lock"></i>登录 / 注册</a>';
        } else{
            target.innerHTML = httpRequest.responseText;
            console.log(httpRequest.responseText);
        }
    }
}

const update = function(){
    httpRequest.onreadystatechange = handler;
    const url = "http://localhost:8080/Backend/getInfo";
    // httpRequest.setRequestHeader('Content-Type', 'application/json');
    httpRequest.withCredentials = true;
    httpRequest.open("POST", url);
    httpRequest.send();
}

export{
    update,
}