var page = {}
page.timer = {} ;

page.timeHandle=function(){
    var type = $('#time-show').attr('type');
    if(type == 'time'){
        //从当前系统时间开始计时
        page.timer.startTime();
    }else{
        //从考试开始时间来计时
        page.timer.startDuration();
    }
}

page.timer.startTime = function(){
    page.timer.updateTime();
    setInterval(function(){
        page.timer.updateTime();
    },1000);
}

page.timer.updateTime = function(){
    var time = page.timer.getCurrTime() ;
    //未来还需要判断结束时间，终止考试



    $('#time-show').html(time);
}

page.timer.getCurrTime = function() {
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    month = month<10 ? '0' + month : month;
    var day = date.getDate();
    day = day<10 ? '0' + day : day;
    var hour = date.getHours();
    hour = hour<10 ? '0' + hour : hour;
    var minute = date.getMinutes();
    minute = minute<10 ? '0' + minute : minute;
    var second = date.getSeconds();
    second = second<10 ? '0' + second : second;

    //return year + '-' + month + '-' + day + ' ' + hour + ':' + minute + ':' + second;
    return hour + ':' + minute + ':' + second;
}

page.timer.startDuration = function(){
    //计算出初始时间   当前系统时间 - startTime 差算出分钟和秒
    var start_ms = $('#page-startTime').val();
    start_ms = parseInt(start_ms) ;
    var curr_ms = new Date().getTime() ;

    var x = curr_ms - start_ms ;
    var m = x/1000/60 ;
    var s = x/1000%60;
    m = parseInt(m) ;
    m = m<10?'0'+m:m;
    s = parseInt(s);
    s = s<10?'0'+s:s;
    $('#time-show').html(m+'分'+s+'秒');

    //定时改变时间
    setInterval(function(){
        var str = $('#time-show').text().trim();
        var i1 = str.indexOf('分') ;
        var i2 = str.indexOf('秒') ;
        m = str.substring(0,i1);
        s = str.substring(i1+1,i2);
        m = parseInt(m) ;
        s = parseInt(s);
        s = s+1 ;
        if(s==60){
            m++ ;
            s = 0 ;
        }
        m = m<10?'0'+m:m;
        s = s<10?'0'+s:s;
        //需要判断是否到达最终的时间，结束考试


        $('#time-show').html(m+'分'+s+'秒');

    },1000) ;

}

page.changePrev = function(){
    //每道题都是一个url
    var question_ul = $('.page-question ul.active');
    if(question_ul.prev().length != 0){
        //切换考题
        question_ul.removeClass('active');
        question_ul.prev().addClass('active');
        //切换题号
        var question_dd = $('.page-part-2 dd.active');
        var no = question_dd.text().trim();
        no = parseInt(no);
        $('.page-part-2 dd').eq(no-2).addClass('active');
        question_dd.removeClass('active');
        question_dd.addClass('complete');
        //判断上一题是否默认勾选标记
        if($('.page-part-2 dd').eq(no-2).hasClass('mark')){
            $('.markBtn').prop('checked',true);
        }else{
            $('.markBtn').prop('checked',false);
        }


    }else{
        alert('已经是第一个题了')
    }
}

page.changeNext = function(){
    var question_ul = $('.page-question ul.active');
    if(question_ul.next().length != 0){
        //切换考题
        question_ul.removeClass('active');
        question_ul.next().addClass('active');
        //切换题号
        var question_dd = $('.page-part-2 dd.active');
        var no = question_dd.text().trim();
        no = parseInt(no);
        $('.page-part-2 dd').eq(no).addClass('active');
        question_dd.removeClass('active');
        //同时当前的考题需要标记成已完成
        question_dd.addClass('complete');

        //判断下一题的标记是否勾选
        //判断上一题是否默认勾选标记
        if($('.page-part-2 dd').eq(no).hasClass('mark')){
            $('.markBtn').prop('checked',true);
        }else{
            $('.markBtn').prop('checked',false);
        }
    }else{
        alert('已经是最后一题了')
    }
}

page.mark = function(ck){
    if(ck.checked){
        $('.page-part-2 dd.active').addClass('mark') ;
    }else{
        $('.page-part-2 dd.active').removeClass('mark') ;
    }
}

page.changeIndex = function(dd){
    $('.page-part-2 dd.active').addClass('complete')
    $('.page-part-2 dd.active').removeClass('active') ;

    $(dd).addClass('active');

    var no = $(dd).text().trim();
    no = parseInt(no);
    no = no-1 ;

    $('.page-question ul.active').removeClass('active');
    $('.page-question ul').eq(no).addClass('active');
}