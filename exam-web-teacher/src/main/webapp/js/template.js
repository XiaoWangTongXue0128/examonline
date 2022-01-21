var template = {}

template.dynamic = {} ;
template.static = {} ;

template.dynamic.calculate = function(){
    var total_score = 0 ;
    //每完成一次输入操作，都重新计算
    //单选题处理
    var score = $('#dynamic-form-question1-score').val();
    var count1 = $('#dynamic-form-question1-level1').val();
    var count2 = $('#dynamic-form-question1-level2').val();
    var count3 = $('#dynamic-form-question1-level3').val();
    score = parseInt(score?score:0) ;
    count1 = parseInt(count1?count1:0) ;
    count2 = parseInt(count2?count2:0) ;
    count3 = parseInt(count3?count3:0) ;

    var count = count1 + count2 + count3 ;
    var total = count * score ;
    $('#dynamic-question1-count').html(count+' 题') ;
    $('#dynamic-question1-total').html(total+' 分') ;
    total_score += total ;

    //多选题处理
    var score = $('#dynamic-form-question2-score').val();
    var count1 = $('#dynamic-form-question2-level1').val();
    var count2 = $('#dynamic-form-question2-level2').val();
    var count3 = $('#dynamic-form-question2-level3').val();
    score = parseInt(score?score:0) ;
    count1 = parseInt(count1?count1:0) ;
    count2 = parseInt(count2?count2:0) ;
    count3 = parseInt(count3?count3:0) ;

    var count = count1 + count2 + count3 ;
    var total = count * score ;
    $('#dynamic-question2-count').html(count+' 题') ;
    $('#dynamic-question2-total').html(total+' 分') ;
    total_score += total ;

    //判断题处理
    var score = $('#dynamic-form-question3-score').val();
    var count1 = $('#dynamic-form-question3-level1').val();
    var count2 = $('#dynamic-form-question3-level2').val();
    var count3 = $('#dynamic-form-question3-level3').val();
    score = parseInt(score?score:0) ;
    count1 = parseInt(count1?count1:0) ;
    count2 = parseInt(count2?count2:0) ;
    count3 = parseInt(count3?count3:0) ;

    var count = count1 + count2 + count3 ;
    var total = count * score ;
    $('#dynamic-question3-count').html(count+' 题') ;
    $('#dynamic-question3-total').html(total+' 分') ;
    total_score += total ;

    //填空题处理
    var score = $('#dynamic-form-question4-score').val();
    var count1 = $('#dynamic-form-question4-level1').val();
    var count2 = $('#dynamic-form-question4-level2').val();
    var count3 = $('#dynamic-form-question4-level3').val();
    score = parseInt(score?score:0) ;
    count1 = parseInt(count1?count1:0) ;
    count2 = parseInt(count2?count2:0) ;
    count3 = parseInt(count3?count3:0) ;

    var count = count1 + count2 + count3 ;
    var total = count * score ;
    $('#dynamic-question4-count').html(count+' 题') ;
    $('#dynamic-question4-total').html(total+' 分') ;
    total_score += total ;

    //综合题
    var score = $('#dynamic-form-question5-score').val();
    var count1 = $('#dynamic-form-question5-level1').val();
    var count2 = $('#dynamic-form-question5-level2').val();
    var count3 = $('#dynamic-form-question5-level3').val();
    score = parseInt(score?score:0) ;
    count1 = parseInt(count1?count1:0) ;
    count2 = parseInt(count2?count2:0) ;
    count3 = parseInt(count3?count3:0) ;

    var count = count1 + count2 + count3 ;
    var total = count * score ;
    $('#dynamic-question5-count').html(count+' 题') ;
    $('#dynamic-question5-total').html(total+' 分') ;
    total_score += total ;

    $('#dynamic-form-totalScore').val(total_score) ;

}

template.dynamic.save = function(){
    var score1 = $('#dynamic-form-question1-score').val();
    var count11 = $('#dynamic-form-question1-level1').val();
    var count12 = $('#dynamic-form-question1-level2').val();
    var count13 = $('#dynamic-form-question1-level3').val();

    var score2 = $('#dynamic-form-question2-score').val();
    var count21 = $('#dynamic-form-question2-level1').val();
    var count22 = $('#dynamic-form-question2-level2').val();
    var count23 = $('#dynamic-form-question2-level3').val();

    var score3 = $('#dynamic-form-question3-score').val();
    var count31 = $('#dynamic-form-question3-level1').val();
    var count32 = $('#dynamic-form-question3-level2').val();
    var count33 = $('#dynamic-form-question3-level3').val();

    var score4 = $('#dynamic-form-question4-score').val();
    var count41 = $('#dynamic-form-question4-level1').val();
    var count42 = $('#dynamic-form-question4-level2').val();
    var count43 = $('#dynamic-form-question4-level3').val();

    var score5 = $('#dynamic-form-question5-score').val();
    var count51 = $('#dynamic-form-question5-level1').val();
    var count52 = $('#dynamic-form-question5-level2').val();
    var count53 = $('#dynamic-form-question5-level3').val();

    //正常还需要有验证，  name，course ， totalScore

    var param = {
        type:'动态模板',
        name : $('#dynamic-form-name').val(),
        course : $('#dynamic-form-course').val(),
        totalScore : $('#dynamic-form-totalScore').val(),

        score1:score1?score1:0,
        count11:count11?count11:0,
        count12:count12?count12:0,
        count13:count13?count13:0,

        score2:score2?score2:0,
        count21:count21?count21:0,
        count22:count22?count22:0,
        count23:count23?count23:0,

        score3:score3?score3:0,
        count31:count31?count31:0,
        count32:count32?count32:0,
        count33:count33?count33:0,

        score4:score4?score4:0,
        count41:count41?count41:0,
        count42:count42?count42:0,
        count43:count43?count43:0,

        score5:score5?score5:0,
        count51:count51?count51:0,
        count52:count52?count52:0,
        count53:count53?count53:0
    }

    $.post('template/dynamic/save',param,function(f){
        if(f == true){
            alert('保存成功') ;
            //需要清空组件数据
            $('.dynamic-box .form-control:lt(2)').val('');
            $('.dynamic-box .form-control:gt(1)').val(0);
            //清空徽章信息（每道题数量和总分）
            $('.dynamic-box .badge:even').html('0 题');
            $('.dynamic-box .badge:odd').html('0 分');
        }else{
            alert('保存失败，请检查名称是否重复');
        }
    });
}