var exam = {} ;

exam.changeTimeFlag = function(){
    var timeFlag = $('#search-timeFlag').val();
    $.post('exam/examGrid.html',{timeFlag:timeFlag},function(view){
        $('#examGrid').replaceWith(view) ;
    });
}