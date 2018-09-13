let changeBtn = document.getElementById('changeBtn');
let changeForm = document.getElementById('changeBookForm');
changeBtn.addEventListener('click',(event)=>{
    event.preventDefault();
    let bookID = document.getElementById('changeBookID').value,
        bookName = document.getElementById('changeBookName').value,
        publisher = document.getElementById('changeBookPub').value,
        time = document.getElementById('changeBookTime').value,
        author = document.getElementById('changeBookAut').value,
        translator = document.getElementById('changeBookTra').value,
        status = document.getElementById('changeBookSta').value,
        count = document.getElementById('changeBookCount').value;
    if (bookID && (bookName||publisher||time||author||translator||status||count)){
        $.ajax({
            url:'http://localhost:8080/manager/modifybookinfo',
            type:"POST",
            data: JSON.stringify({
                "bookID":bookID,
                "bookName":bookName,
                "publisher":publisher,
                "time":time,
                "author":author,
                "translator":translator,
                "statusStr":status,
                "countStr":count
            }),
            contentType:'application/json',
            dataType: "json",
            success: function (response) {
                console.log(response);
                if(response.status == 200){
                    alert("图书修改成功");
                }else{
                    alert("图书修改失败,请稍后重试~");
                }
                changeForm.reset();
            },
            error: function (error) {
                console.log(error);
                alert("图书修改失败,请稍后重试~");
                changeForm.reset();
            }
        });
    }else if(bookID && !(bookName||publisher||time||author||translator||status||count)){
        alert('您还没有填写要修改的信息');
    }else if(!bookID){
        alert('书号是修改的必填写项哦')
    }
});