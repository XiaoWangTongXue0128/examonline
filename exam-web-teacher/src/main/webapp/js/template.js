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
//-----------------------动静分离----------------------------------------
var separator = '}-|-{' ;
var subject_editor  ;
var option_editors = [] ;
var answer_editor ; //综合题答案编辑器

template.static.toAddQuestion = function(){
    $('.modal-dialog').addClass('modal-lg');
    $.post('template/questionTemplate.html',{},function(view){
        main.showDialog({
            title:'添加试题',
            content:view,
            submit:function(){
                template.static.cacheQuestion();
            }
        });

        //初始化文本编辑器
        //题干
        subject_editor = new E('#static-question-subject') ;
        editorDefaultInit(subject_editor) ;
        subject_editor.create() ;

        //默认初始化单选题选项
        template.static.changeQuestion1();
    });

}

template.static.changeQuestion = function(){
    //切换新的编辑器
    $('#static-question-subject').replaceWith('<div id="static-question-subject"></div>');
    var element = $('#static-question-subject').toArray()[0];
    subject_editor = new E(element) ;
    editorDefaultInit(subject_editor);
    subject_editor.create();

    //清空之前的选项编辑器数组
    option_editors = [] ;

    //清空之前的题型选项
    $('#question-template .row:gt(2)').remove();


    var type = $('#static-form-question-type').val();

    if(type == '单选题'){
        template.static.changeQuestion1();
    }else if(type == '多选题'){
        template.static.changeQuestion2();
    }else if(type == '判断题'){
        template.static.changeQuestion3();
    }else if(type == '填空题'){
        template.static.changeQuestion4();
    }else if(type == '综合题'){
        template.static.changeQuestion5();
    }
}

//添加考题时，切换至单选题
template.static.changeQuestion1 = function() {
    //清空题干内容（需要重新填写）
    subject_editor.txt.html('');

    //增加选项（默认4个）
    //哪来的选项？（questin-clone-box)
    for (var i = 0; i < 4; i++) {
        var option = $('#question-clone-box .row:eq(0)').clone();
        $('#question-template').append(option);
    }
    //将所有的选项内容转换成编辑器
    $('#question-template .question1-option-content').each(function(i,element){
        var e = new E(element) ;
        editorDefaultInit(e) ;
        e.create();
        option_editors.push(e);
    });

    //为选项排序
    template.static.optionSort('.question1-option')


    //增加一个添加选项的按钮
    $('#question-template').append(`
        <div style="text-align:center" class="row">
          <a class="btn btn-link" onclick="template.static.addOption1()">
               <span class="glyphicon glyphicon-plus"></span>增加选项
           </a>
        </div>
    `);
}

template.static.optionSort = function(classId){
    $('#question-template '+classId+' span').each(function(i,span){
        // 字符A变成数字， 然后加序号（0,1,2,3），再将数字转换成字符
        span.innerHTML = String.fromCharCode('A'.charCodeAt(0) + i) ;
    });

    //设置选项值（0,1,2,3）
    $('#question-template '+classId+' input').each(function(i,input){
        // 字符A变成数字， 然后加序号（0,1,2,3），再将数字转换成字符
        input.value = i ;
    });
}

template.static.addOption1 = function(){
    var option = $('#question-clone-box .row:eq(0)').clone();
    $('#question-template .row:last').before(option);
    var element = $('#question-template .question1-option-content:last').toArray()[0] ;
    var e = new E(element) ;
    editorDefaultInit(e);
    e.create();
    option_editors.push(e);

    template.static.optionSort('.question1-option');
}

template.static.removeOption1 = function(curr_a){
    //确保至少有2个选项
    if($('#question-template .question1').length == 2){
        alert('至少保留2个选项')
        return ;
    }


    if(!confirm('是否确认删除该选项')){
        return ;
    }

    //  a     <div part3> <div row>
    $(curr_a).parent().parent().remove();

    //重新计算选项
    template.static.optionSort('.question1-option');
}


//切换至多选题
template.static.changeQuestion2 = function() {
    //清空题干内容（需要重新填写）
    subject_editor.txt.html('');

    //增加选项（默认4个）
    //哪来的选项？（questin-clone-box)
    for (var i = 0; i < 4; i++) {
        var option = $('#question-clone-box .row:eq(1)').clone();
        $('#question-template').append(option);
    }
    //将所有的选项内容转换成编辑器
    $('#question-template .question2-option-content').each(function(i,element){
        var e = new E(element) ;
        editorDefaultInit(e) ;
        e.create();
        option_editors.push(e);
    });

    //为选项排序
    template.static.optionSort('.question2-option')


    //增加一个添加选项的按钮
    $('#question-template').append(`
        <div style="text-align:center" class="row">
          <a class="btn btn-link" onclick="template.static.addOption2()">
               <span class="glyphicon glyphicon-plus"></span>增加选项
           </a>
        </div>
    `);
}

template.static.addOption2 = function(){
    var option = $('#question-clone-box .row:eq(1)').clone();
    $('#question-template .row:last').before(option);
    var element = $('#question-template .question2-option-content:last').toArray()[0] ;
    var e = new E(element) ;
    editorDefaultInit(e);
    e.create();
    option_editors.push(e);

    template.static.optionSort('.question2-option');
}

template.static.removeOption2 = function(curr_a){
    //确保至少有2个选项
    if($('#question-template .question2').length == 2){
        alert('至少保留2个选项')
        return ;
    }


    if(!confirm('是否确认删除该选项')){
        return ;
    }

    //  a     <div part3> <div row>
    $(curr_a).parent().parent().remove();

    //重新计算选项
    template.static.optionSort('.question2-option');
}

//切换至判断题
template.static.changeQuestion3 = function() {

    var option = $('#question-clone-box .row:eq(2)').clone();
    $('#question-template').append(option)

}

//切换至综合题
template.static.changeQuestion5 = function() {

    var option = $('#question-clone-box .row:eq(4)').clone();
    $('#question-template').append(option)

    var element = $('#question-template .question5-option-content').toArray()[0] ;
    var e = new E(element);
    editorDefaultInit(e);
    e.create();
    answer_editor = e ;

}

//切换至填空题
template.static.changeQuestion4 = function() {
    //填空题需要为编辑器增加换一个“填空按钮”
    //这需要重新构建编辑器
    $('#static-question-subject').replaceWith('<div id="static-question-subject"></div>');
    var element = $('#static-question-subject').toArray()[0];
    subject_editor = new E(element) ;
    editorDefaultInit(subject_editor);
    // 注册菜单
    subject_editor.menus.extend("blank", BlankMenu) ;
    subject_editor.config.menus = subject_editor.config.menus.concat('blank')
    subject_editor.create();

    //为题干编辑器增加一个监控时间，监控编辑器内容
    //每次内容变化，都检查【】符号的数量，并生成或取消对应的输入选项
    //每次添加和删除的选项都在最后
    subject_editor.txt.eventHooks.changeEvents.push(function(){
        //console.log(subject_editor.txt.html());
        //console.log('-------------------------');
        var text = subject_editor.txt.html() ;
        //计算【】符合的数量
        var count = 0 ;
        while(true){
            var p = text.indexOf("【】");
            if(p != -1){
                count++ ;
                text = text.substring(p+1);
                continue ;
            }
            break ;
        }
        //检查填空选项的匹配。  count<现有数量，减少选项。 count>现有数量，增加选项，
        var option_count = $('#question-template .row:gt(2)').length ;
        if(count < option_count){
            //需要减少
            var down = option_count - count ;
            for(var i=0;i<down;i++){
                $('#question-template .row:last').remove();
            }
        }else if(count > option_count){
            //需要增加
            //count=6. option_count=4  增加2
            //1,2,3,4,4+1,4+2
            var up = count - option_count ;
            for(var i=1;i<=up;i++){
                var option = $('#question-clone-box .row:eq(3)').clone();
                $('#question-template').append(option);
                $('#question-template .row:last .question4-option span').html(option_count + i) ;
            }
        }
    });
}

//将此次录入的考题存入服务器缓存（session）
template.static.cacheQuestion = function(){
    var param = {
        type : $('#static-form-question-type').val(),
        level : $('#static-form-question-level').val(),
        subject : subject_editor.txt.html()
    }
    var options = '' ;
    var answer = '' ;

    //处理单选题和多选题的选项内容
    if(param.type == '单选题' || param.type == '多选题'){
        //有选项
        for(var i=0;i<option_editors.length;i++){
            var e = option_editors[i] ;
            options += e.txt.html() + separator;
        }
        alert(options) ;
    }


    //处理答案
    if(param.type =='单选题'){
        answer = $('input[name="question1-option"]:checked').val();
    }
    if(param.type == '多选题'){
        $('input[name="question2-option"]:checked').each(function(i,input){
            answer += input.value + separator ;
        });
    }
    if(param.type == '判断题'){
        answer = $('input[name="question3-option"]:checked').val();
    }
    var f = true ;
    if(param.type =='填空题'){
        $('input[name="question4-option"]').each(function(i,input){
            if(!input.value){
                f = false ;
            }
            answer += input.value + separator ;
        });
    }

    if(param.type == '综合题'){
        answer = answer_editor.txt.html();
    }

    if(!answer | !f){
        alert('请选择答案') ;
        return ;
    }

    param.options = options ;
    param.answer = answer ;

    $.post('template/cacheQuestion',param,function(){
        alert('试题添加成功') ;
        main.closeDialog();
    });

}

