const httpRequest = new XMLHttpRequest();

const handler = function(){
    if(httpRequest.readyState === 4 && httpRequest.status === 200){
        console.log(httpRequest.responseText);
        const info1 = document.getElementById("info1");
        const info2 = document.getElementById("info2");
        if(httpRequest.responseText === "0"){
            info1.style.display = "";
            info2.style.display = "none";
        } else{
            const info = document.querySelector(".zh_dropdown-head");
            info.innerHTML = `${httpRequest.responseText} <i class="zh_arrow">&lt</i>`;
            info1.style.display = "none";
            info2.style.display = "";
            console.log(info.innerHTML);
        }
    }
}

const update = function(){
    httpRequest.onreadystatechange = handler;
    const url = "http://localhost:8080/Backend/getInfo";
    httpRequest.open("GET", url);
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
    const url = "http://localhost:8080/Backend/logout";
    httpRequest.open("GET", url);
    httpRequest.withCredentials = true;
    httpRequest.send();
}

export{
    update,
    logoutTrigger,
}