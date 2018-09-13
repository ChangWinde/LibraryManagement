let searchStuBtn = document.getElementById('searchStuBtn');
searchStuBtn.addEventListener('click',()=>{
    event.preventDefault();
    let stuID = document.getElementById('searchStuID').value;
    if (stuID) {
        $.ajax({
            url: 'http://localhost:8080/search/reader',
            type: "POST",
            data: JSON.stringify({
                "studentID": stuID,      // 用户名
            }),
            contentType: 'application/json',
            dataType: "json",
            success: function (response) {
                console.log(response);
                let studentMsg = document.getElementsByClassName('studentRES')[0];
                let template = `<p>学号：${response.readerId}</p>
                <p>姓名：${response.name}</p>
                <p>卡余额：${response.balance}</p>
                <ul class="bookList">

                </ul>`;
                studentMsg.innerHTML = template;

                let studentBook = document.querySelector('.studentRES>.bookList');
                for (let i = 0, num = response.books.length; i < num; i++) {
                    let card = document.createElement('li');
                    card.classList.add('bookCard');
                    studentBook.appendChild(card);
                    let oneBook = new BookforManager(card, response.books[i]);
                }

                studentMsg.classList.remove('none');
            },
            error: function (error) {
                console.log(error);
                alert("查找失败，请检查查找信息");
            }
        });
    } else {
        alert("搜索框没有内容，请输入~")
    }
});