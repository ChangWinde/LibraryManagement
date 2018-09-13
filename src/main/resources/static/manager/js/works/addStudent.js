let regBtn = document.getElementById('regBtn');
let regStuForm = document.getElementById('regStuForm');
regBtn.addEventListener('click',(event)=>{
    event.preventDefault();
    let studentID = document.getElementById('regStuID').value,
        password = document.getElementById('regStuPsd').value,
        name = document.getElementById('regStuName').value,
        balance = document.getElementById('regStuBalance').value;
    if (studentID && password&& name&& balance){
        $.ajax({
            url:'http://localhost:8080/manager/addreader',
            type:"POST",
            data: JSON.stringify({
                "readerId":studentID,
                "password":password,
                "name":name,
                "balance":balance
            }),
            contentType:'application/json',
            dataType: "json",
            success: function (response) {
                console.log(response);
                if(response.status == 200){
                    alert("学生注册成功")
                }else{
                    alert("学生注册失败,请稍后重试~");
                }
                regStuForm.reset();
            },
            error: function (error) {
                console.log(error);
                alert("学生注册失败,请稍后重试~");
                regStuForm.reset();
            }
        });
    }else{
        alert("请检查是否填写了所有信息");
    }

});