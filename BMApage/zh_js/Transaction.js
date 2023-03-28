let httpRequest, list, url;

const searchHandler = function(){
    if(httpRequest.readyState === 4 && httpRequest.status === 200){
        // console.log(httpRequest.responseText);
        list = JSON.parse(httpRequest.responseText);
        const view = document.querySelector("#customers");
        let content = `
            <tr>
                <th width="15%">书名</th>
                <th width="15%">作者</th>
                <th width="40%">描述</th>
                <th width="15%">数量</th>
                <th width="15%">操作</th>
            </tr>
        `
        for(let i = 0; i < list.length; ++ i){
            // console.log(i, list[i].description, typeof list[i].description, list[i].id);
            if(list[i].description === undefined){
                list[i].description = "";
            }
            content += `
                <tr>
                    <td>${list[i].bookName}</td>
                    <td>${list[i].author}</td>
                    <td>${list[i].description}</td>
                    <td>${list[i].surplus}</td>
                    <td>
                        
                        <button type="button" class="borrow" id="borrow${list[i].id}">借书</button>
                    </td>
                </tr>
            `
        }
        view.innerHTML = content;

        const borrowTrigger = document.querySelectorAll(".borrow");
        console.log(borrowTrigger.length);
        for(let i = 0; i < borrowTrigger.length; ++ i){
            let index = 0;
            for(let j = 6; j < borrowTrigger[i].id.length; ++ j){
                index = index * 10 + borrowTrigger[i].id.charCodeAt(j) - 48;
            }
            borrowTrigger[i].addEventListener("click", function(){
                borrowBook(index);
            });
        }

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
        operation: '1',
    }
    console.log(data);
    httpRequest = new XMLHttpRequest();
    httpRequest.onreadystatechange = searchHandler;
    url = "http://127.0.0.1:8080/backend/search";
    httpRequest.open("POST", url, false);
    httpRequest.withCredentials = true;
    httpRequest.setRequestHeader('Content-Type', 'application/json');
    httpRequest.send(JSON.stringify(data));
}

const borrowHandler = function(){
    if(httpRequest.readyState === 4 && httpRequest.status === 200){
        console.log(httpRequest.responseText);
        alert(httpRequest.responseText);
        search();
    }
}

const borrowBook = function(index){
    const data = {
        bookId: index,
    }
    httpRequest = new XMLHttpRequest();
    httpRequest.onreadystatechange = borrowHandler;
    url = "http://127.0.0.1:8080/backend/borrowBook"
    httpRequest.open("POST", url);
    httpRequest.setRequestHeader('Content-Type', 'application/json');
    httpRequest.withCredentials = true;
    httpRequest.send(JSON.stringify(data));
}

export{
    search,
    borrowBook,
}