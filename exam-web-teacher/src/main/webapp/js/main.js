var main={}

main.exit = function(){
    if(!confirm('是否确认退出'))
        return ;
    location.href='common/exit' ;
}

main.toUpdatePwd = function(){

    $.post('common/updatePwdTemplate.html',{},function(view){
        $('#modal-body').html(view);

        //展示模态框
        $('#myModal').modal('show');

    });

}

main.updatePwd = function(){
    var new_pass = $('#new-pass').val();
    var re_pass = $('#re-pass').val();
    var old_pass = $('#old-pass').val();

    if(new_pass != re_pass){
        alert('两次密码不一致') ;
        return ;
    }

    var param = {
        old_pass:old_pass,
        new_pass:new_pass
    }
    $.post('common/updatePwd',param,function(f) {
        if (f == true) {
            alert('密码修改成功') ;
            $('#myModal').modal('hide') ;
            return ;
        }

        alert('原密码不正确') ;
    });

}