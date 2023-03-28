const inputFrames = document.querySelectorAll(".overlay input");
console.log(inputFrames.length);
const tips = document.querySelectorAll(".tips");
let httpRequest, list, url, id;

inputFrames.forEach(element => {
    element.addEventListener("focus", function(){
        for(let i = 0; i < tips.length; ++ i) tips[i].style.display = "none";
    });
});

const addBook = function(){
    console.log("addBook");
    id = null;
    url = "http://127.0.0.1:8080/backend/addBook";
    const title = document.querySelector("#title");
    title.innerHTML = "添加书籍";
    for(let i = 0; i < tips.length; ++ i) tips[i].style.display = "none";
    location.assign("BookAdding.html#divOne");
}

const updateBook = function(index){
    id = list[index].id;
    url = "http://127.0.0.1:8080/backend/updateBook";
    for(let i = 0; i < tips.length; ++ i) tips[i].style.display = "none";

    inputFrames[0].value = list[index].bookName;
    inputFrames[1].value = list[index].author;
    inputFrames[2].value = list[index].surplus;
    const description = document.querySelector("#description");
    description.value = list[index].description;
    const title = document.querySelector("#title");
    title.innerHTML = "修改数据";
    console.log(description);

    location.assign("BookAdding.html#divOne");
}

const addBookHandler = function(){
    if(httpRequest.readyState === 4 && httpRequest.status === 200){
        console.log(httpRequest.responseText);
        tips[3].innerHTML = httpRequest.responseText;
        tips[3].style.display = "initial";
        setTimeout(()=>{
            location.assign("BookAdding.html");
        }, 1000)
    }
}

const main = function(){
    const bookName = document.getElementById("bookName").value;
    const author = document.getElementById("author").value;
    const description = document.getElementById("description").value;
    const surplus = document.getElementById("surplus").value;
    if(bookName.length === 0){
        console.log(bookName.length);
        tips[0].style.display = "initial";
        return;
    } else if(author.length === 0){
        tips[1].style.display = "initial";
        return;
    } else if(surplus.length === 0){
        tips[2].innerHTML = "数量不能为空";
        tips[2].style.display = "initial";
        return;
    }
    console.log(surplus.length);
    let num = 0;
    for(let i = 0; i < surplus.length; ++ i){
        if(surplus.charCodeAt(i) < 48 || 57 < surplus.charCodeAt(i)){
            console.log(surplus.charCodeAt(i));
            tips[2].innerHTML = "非法输入";
            tips[2].style.display = "initial";
            return;
        }
        num = num * 10 + surplus.charCodeAt(i) - 48;
    }
    const classification = document.querySelectorAll("input[type=checkbox]")
    let classificationResult = "";
    for(let i = 0; i < 5; ++ i){
        console.log(classification[i].value);
        if(classification[i].checked) 
            classificationResult =  classificationResult.concat(classification[i].value);
    }
    const type = document.querySelector("input[name=type]:checked").value;
    const book = {
        id: id,
        bookName: bookName,
        author: author,
        description: description,
        surplus: num,
        classification: classificationResult,
        type: type,
    }
    console.log(book);
    httpRequest = new XMLHttpRequest();
    httpRequest.onreadystatechange = addBookHandler;
    httpRequest.open("POST", url);
    httpRequest.setRequestHeader('Content-Type', 'application/json');
    httpRequest.withCredentials = true;
    httpRequest.send(JSON.stringify(book));
}

const searchHandler = function(){
    if(httpRequest.readyState === 4 && httpRequest.status === 200){
        console.log(httpRequest.responseText);
        list = JSON.parse(httpRequest.responseText);
        const view = document.querySelector("#customers");
        let content = `
            <tr>
                <th width="15%">书名</th>
                <th width="15%">作者</th>
                <th width="40%">描述</th>
                <th width="15%">数量</th>
                <th width="15%"><button type="button" id="addBook" onclick="addBook()">添加书籍</button></th>
            </tr>
        `
        for(let i = 0; i < list.length; ++ i){
            console.log(i, list[i].description, typeof list[i].description, list[i].id);
            if(list[i].description === undefined){
                list[i].description = "";
            }
            content += `
                <tr>
                    <td>${list[i].bookName}</td>
                    <td>${list[i].author}</td>
                    <td>${list[i].description}</td>
                    <td>${list[i].surplus}</td>
                    <td><button type="button" onclick="updateBook(${i})">修改</button></td>
                </tr>
            `
        }
        view.innerHTML = content;
    }
}

const search = function(){
    const keyword = document.querySelector("#keyword").value;
    const classification = document.querySelector("select[name=classification]").value;
    const type = document.querySelector("select[name=type]").value;
    const data = {
        keyword: keyword,
        classification: classification,
        type: type,
        operation: '0',
    }
    console.log(data);
    httpRequest = new XMLHttpRequest();
    httpRequest.onreadystatechange = searchHandler;
    url = "http://127.0.0.1:8080/backend/search";
    httpRequest.open("POST", url);
    httpRequest.setRequestHeader('Content-Type', 'application/json');
    httpRequest.send(JSON.stringify(data));
}

search();