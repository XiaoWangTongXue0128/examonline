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