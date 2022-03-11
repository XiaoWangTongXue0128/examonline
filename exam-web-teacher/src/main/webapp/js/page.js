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

page.toReview = function(studentId,sname){
    //'exam/examId/studentId/page.html'
    //'exam/8/347/page.html'
    var url = 'exam/page.html?sname='+sname+'&studentId='+studentId+'&examId='+$('#exam-id').val() ;
    window.open(url);
}