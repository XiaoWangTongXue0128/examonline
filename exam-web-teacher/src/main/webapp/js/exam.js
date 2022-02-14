var exam = {} ;

exam.toQuery = function(pageNo){
    pageNo = pagNo?pageNo:1 ;


}

exam.toClearQueary = function(){
    $('.search-box .form-control').val('');
    exam.toQuery();
}

exam.toPageQuery = function(pageNo){
    exam.toQuery(pageNo);
}