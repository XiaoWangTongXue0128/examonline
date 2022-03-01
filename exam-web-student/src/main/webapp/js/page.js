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
