class BookforSearch {
    // container是容器，card msg是书籍信息
    constructor(container, msg) {
        this.container = container;
        this.msg = msg;
        this.create();
        this.detailBtn = this.container.querySelectorAll('.control>p')[0];
        this.borrowBtn = this.container.querySelectorAll('.control>p')[1];
        this.init();
    }
    create() {
        let template = `<p class="bookID">${this.msg.bookID}</p>
                        <p class="bookName">${this.msg.bookName}</p>
                        <div class="control">
                            <p>详情</p>
                            <p>借书</p>
                        </div>
                        `;
        this.container.innerHTML = template;
    }
    init() {
        this.detailBtn.addEventListener('click', (event) => {
            let mask = document.getElementsByClassName('g-mask')[0];
            let detailMsg = document.getElementsByClassName('detail')[0];
            let template = `<p>书号：
                                <span>${this.msg.bookID}</span>
                            </p>
                            <p>书名:
                                <span>《${this.msg.bookName}》</span>
                            </p>
                            <p>出版社：
                                <span>${this.msg.publisher}</span>
                            </p>
                            <p>出版时间：
                                <span>${this.msg.date}</span>
                            </p>
                            <p>作者：
                                <span>${this.msg.author}</span>
                            </p>
                            <p>译者：
                                <span>${this.msg.translator}</span>
                            </p>
                            <p>是否可借：
                                <span>${this.msg.status}</span>
                            </p>
                            <div class="control">
                                <p>返回</p>
                            </div>`;
            detailMsg.innerHTML = template;
            mask.classList.remove('none');
            detailMsg.classList.remove('none');
            let returnBtn = document.querySelector('.detail>.control>p');
            returnBtn.addEventListener('click',()=>{
                document.querySelector('.detail').innerHTML = '';
                document.querySelector('.g-mask').classList.add('none');
                document.querySelector('.detail').classList.add('none');
            });
        });

        this.borrowBtn.addEventListener('click', (event) => {
            if (window.Person.status != 200){
                alert('登录后才可以借书哦，如果已经登录，请找人工服务');
            }
            $.ajax({
                url: 'http://localhost:8080/reader/borrow',
                type: "POST",
                data: JSON.stringify({
                    "studentID": window.Person.studentID, // 用户名，如学号/一卡通
                    "bookID": this.msg.bookID // 密码
                }),
                contentType: 'application/json',
                dataType: "json",
                success: (response) => {
                    if (response.status == 200 && this.msg.status == true) {
                        alert('借书成功，请在书架查看～');
                        window.Person.books = response.books;
                    } else {
                        alert("借书失败，请检查图书是否可借，或者人工服务");
                    }
                },
                error: function (error) {
                    console.log(error);
                    alert("借书失败，请检查图书是否可借，或者人工服务");
                }
            });
        });
    }
}