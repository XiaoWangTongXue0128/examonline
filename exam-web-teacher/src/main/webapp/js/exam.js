var exam = {} ;

exam.toQuery = function(pageNo){
    pageNo = pageNo?pageNo:1 ;

    var name = $('#search-name').val();
    if(!name){
        name = $('#search-type').val()
    }
    var status = $('#search-status').val();
    var startTime = $('#search-start-time').val();
    var endTime = $('#search-end-time').val();

    var param = {
        name:name,
        status:status,
        startTime:startTime,
        endTime:endTime,

        pageNo:pageNo
    }

    $.post('exam/examGridTemplate.html',param,function(view){
        $('#part-2').replaceWith(view);
    });


}

exam.toClearQuery = function(){
    $('.search-box .form-control').val('');
    exam.toQuery();
}

exam.toPageQuery = function(pageNo){
    exam.toQuery(pageNo);
}

exam.toAdd = function(){

    $.post('exam/form.html',{},function(view){
        main.showDialog({
            title:'新建考试信息',
            content:view,
            submit:function(){
                exam.save();
            }
        });
    });

}

exam.save = function(id){
    var param = {
        name:$('#exam-form-name').val()
    } ;
    var url  ;
    if(id){
        url = 'exam/update' ;
        param.id = id ;
    }else{
        url = 'exam/save' ;
    }

    $.post(url,param,function(f){
        if(f == true){
            alert('操作成功') ;
            main.closeDialog() ;

            if(id){
                //编辑，刷新fill填充页的名称展示部分
                $('#fill-exam-name').html(param.name) ;
            }else{
                //保存，刷新表格
                exam.toClearQuery();
            }

        }else{
            alert('名称重复，操作失败');
        }
    });
}

exam.toEdit = function(){
    var id=$('#fill-form-id').val()
    $.post("exam/form.html",{id:id},function(view){
        main.showDialog({
            title:'编辑考试名称',
            content:view,
            submit:function(){
                exam.save(id);
            }
        })
    });
}