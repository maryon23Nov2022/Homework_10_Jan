let httpRequest, url;

const searchHandler = function(){
    if(httpRequest.readyState === 4 && httpRequest.status === 200){
        if(httpRequest.responseText === "用户未登录"){
            location.assign("http://127.0.0.1:4040/signin.html");
            return;
        }
        const data = JSON.parse(httpRequest.responseText);
        const bookNameList = JSON.parse(data.bookNameList);
        const dateList = JSON.parse(data.dateList);
        const bookIdList = JSON.parse(data.bookIdList);
        const view = document.querySelector("#customers");
        let content = `
            <tr>
                <th>书名</th>
                <th style="width: 40%">借阅日期</th>
                <th>操作</th>
            </tr>
        `;
        for(let i = 0; i < bookNameList.length; ++ i){
            let date = new Date(dateList[i]);
            content += `
                <tr>
                    <td>${bookNameList[i]}</td>
                    <td>${date.toString().substring(4, 24)}</td>
                    <td>
                        <button type="button" class="return" id="return${bookIdList[i]}">还书</button>
                    </td>
                </tr>
            `
        }
        view.innerHTML = content;

        const returnTrigger = document.querySelectorAll(".return");
        console.log(returnTrigger.length);
        for(let i = 0; i < returnTrigger.length; ++ i){
            let index = 0;
            for(let j = 6; j < returnTrigger[i].id.length; ++ j){
                index = index * 10 + returnTrigger[i].id.charCodeAt(j) - 48;
            }
            returnTrigger[i].addEventListener("click", function(){
                returnBook(index);
            });
        }

        document.querySelector("#number").innerHTML = `已借书籍：${bookIdList.length}本`
    }
}

const search = function(){
    httpRequest = new XMLHttpRequest();
    httpRequest.onreadystatechange = searchHandler;
    url = "http://127.0.0.1:8080/Backend/transaction/search";
    httpRequest.open("GET", url, true);
    httpRequest.withCredentials = true;
    httpRequest.send();
}

const returnHandler = function(){
    if(httpRequest.readyState === 4 && httpRequest.status === 200){
        alert(httpRequest.responseText);
        search();
    }
}

const returnBook = function(index){
    const data ={
        bookId: index,
    }
    httpRequest = new XMLHttpRequest();
    httpRequest.onreadystatechange = returnHandler;
    url = "http://127.0.0.1:8080/Backend/returnBook";
    httpRequest.open("POST", url, true);
    httpRequest.setRequestHeader('Content-Type', 'application/json');
    httpRequest.withCredentials = true;
    httpRequest.send(JSON.stringify(data));
}

export{
    search,
}