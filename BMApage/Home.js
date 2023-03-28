let httpRequest, username = "abc", identity = "student";

const handler = function(){
    if(httpRequest.readyState === 4 && httpRequest.status === 200){
        const data = JSON.parse(httpRequest.responseText);
        username = data.username;
        identity = data.identity;

        const info1 = document.getElementById("info1");
        const info2 = document.getElementById("info2");
        if(info1 === null) return;
        if(httpRequest.responseText === "0"){
            info1.style.display = "";
            info2.style.display = "none";
        } else{
            const info = document.querySelector(".zh_dropdown-head");
            info.innerHTML = `${username} <i class="zh_arrow">&lt</i>`;
            info1.style.display = "none";
            info2.style.display = "";
            // console.log(info.innerHTML);
        }
        console.log("username = " + username);
    }
}

const update = function(){
    httpRequest = new XMLHttpRequest();
    httpRequest.onreadystatechange = handler;
    const url = "http://127.0.0.1:8080/backend/getInfo";
    httpRequest.open("GET", url, false);
    httpRequest.withCredentials = true;
    httpRequest.send();
}

const logoutTrigger = function(){
    const button = document.getElementById("logout");
    button.addEventListener("click", logout);
}

const logout = function(){
    httpRequest.onreadystatechange = function(){
        if(httpRequest.readyState === 4 && httpRequest.status === 200)
            update();
    }
    const url = "http://127.0.0.1:8080/backend/logout";
    httpRequest.open("GET", url);
    httpRequest.withCredentials = true;
    httpRequest.send();
}

export{
    update,
    logoutTrigger,
    username,
    identity,
}