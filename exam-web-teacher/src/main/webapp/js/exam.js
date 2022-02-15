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

exam.save = function(){
    $.post('exam/save',{name:$('#exam-form-name').val()},function(f){
        if(f == true){
            alert('保存成功') ;

            main.closeDialog() ;

            exam.toClearQuery();

        }else{
            alert('名称重复，保存失败');
        }
    });
}