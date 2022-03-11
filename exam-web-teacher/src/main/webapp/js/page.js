var page = {}

page.toStudentList = function(className){
    var param = {
        className:className,
        examId:$('#exam-id').val()
    }

    $.post('exam/studentPageList.html',param,function(view){
        $('#studentGrid').replaceWith(view);
    });
}

page.changeStudentStatus = function(select,studentId){
    var param = {
        status : select.value,
        studentId : studentId,
        examId : $('#exam-id').val()
    }
    $.post('exam/changeStudentStatus',param,function(){
        alert('状态设置成功') ;
    });
}