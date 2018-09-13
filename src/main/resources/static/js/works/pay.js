
let payBtn = document.getElementById('payBtn');

payBtn.addEventListener('click',(event)=>{
    event.preventDefault();
    let payAmount = parseInt(document.getElementById('amount').value);
    if (payAmount > 0){
        $.ajax({
            url: 'http://localhost:8080/reader/charge',
            type: "post",
            data: JSON.stringify({
                "readerId": window.Person.studentID, // 用户名，如学号/一卡通
                "amount": payAmount      // 存款金额
            }),
            contentType: 'application/json',
            dataType: "json",
            success: function (response) {
                console.log(response);
                if(response.status == 200){
                    document.querySelector('.pay_sucess').classList.remove('none');
                    document.querySelector('.balance>span').innerHTML = response.balance;
                    let t=setTimeout("document.querySelector('.pay_sucess').classList.add('none')",5000);
                }
            },
            error: function (error) {
                console.log(error);
                document.querySelector('.pay_failed').classList.remove('none');
                let t=setTimeout("document.querySelector('.pay_failed').classList.add('none')",5000);
            }
        });
    }else{
        alert("请输入正整数金额啊~~~~~~");
    }

});



