// 查找图书界面呈现
let hdtab = new App.Tabs({
    container: document.getElementById("hdtabs"),
    index: 0,
    data: [
        ["#", "精确查询"],
        ["#", "模糊查询"]
    ]
});
let nforms = document.getElementsByTagName("form");
nforms[hdtab.index].style.display = "flex";
// 查找方式切换
for (let i = 0; i < 2; i++) {
    hdtab.nTabs[i].addEventListener('click', (event) => {
        nforms[(i + 1) % 2].style.display = "none";
        nforms[i].style.display = "flex";
    });
}

//  查找到的书籍信息
window.SearchBooks = window.SearchBooks || {};
let exactBtn = document.getElementById("exactBtn");
let fuzzyBtn = document.getElementById("fuzzyBtn");
exactBtn.addEventListener('click', (event) => {
    event.preventDefault();
    let myBookID = document.getElementById("bookID").value;
    if(myBookID){
        $.ajax({
            url: "http://localhost:8080/search/book",
            type: "POST",
            beforeSend: function (request) {
                request.setRequestHeader("bookID", myBookID);
            },
            data: JSON.stringify({
                "bookID": myBookID,
            }),
            contentType: 'application/json',
            dataType: "json",
            success: function (response) {
                let listSearch = document.getElementsByClassName('bookList')[0];
                listSearch.innerHTML = '';
                let card = document.createElement('li');
                card.classList.add('bookCard');
                listSearch.appendChild(card);
                let oneBook = new BookforManager(card, response[0]);
                listSearch.classList.remove('none');
            },
            error: function (error) {
                console.log(error);
                alert("没有找到书哦");
            }
        });
    }else{
        alert("请输入要查找的书号");
    }

});
fuzzyBtn.addEventListener('click', () => {
    event.preventDefault();
    let myBookName = document.getElementById("bookName").value;
    let myPublisher = document.getElementById("publisher").value;
    let myAuthor = document.getElementById("author").value;
    let myTran = document.getElementById("translator").value;

    if(myBookName || myPublisher ||myAuthor || myTran){
        $.ajax({
            url: "http://localhost:8080/search/book",
            type: "POST",
            dataType: "json",
            contentType: 'application/json',
            data: JSON.stringify({
                "bookName": myBookName || "",
                "publisher": myPublisher || "",
                "author": myAuthor || "",
                "translator": myTran || ""
            }),
            success: function (response) {
                console.log(response);
                let num = response.length;
                let listSearch = document.getElementsByClassName('listforSearch')[0];
                listSearch.innerHTML = '';
                for (let i = 0; i < num; i++) {
                    let card = document.createElement('li');
                    card.classList.add('bookCard');
                    listSearch.appendChild(card);
                    let oneBook = new BookforManager(card, response[i]);
                }
                listSearch.classList.remove('none');
            },
            error: function (error) {
                console.log(error);
                alert("没有找到书哦");
            }
        });
    }else{
        alert("请至少输入一条检索信息");
    }

});

