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

exam.save = function(id){
    var param = {
        name:$('#exam-form-name').val()
    } ;
    var url  ;
    if(id){
        url = 'exam/update' ;
        param.id = id ;
    }else{
        url = 'exam/save' ;
    }

    $.post(url,param,function(f){
        if(f == true){
            alert('操作成功') ;
            main.closeDialog() ;

            if(id){
                //编辑，刷新fill填充页的名称展示部分
                $('#fill-exam-name').html(param.name) ;
            }else{
                //保存，刷新表格
                exam.toClearQuery();
            }

        }else{
            alert('名称重复，操作失败');
        }
    });
}

exam.toEdit = function(){
    var id=$('#fill-form-id').val()
    $.post("exam/form.html",{id:id},function(view){
        main.showDefaultDialog({
            title:'编辑考试名称',
            content:view,
            submit:function(){
                exam.save(id);
            }
        })
    });
}

exam.toSelectTemplate = function(){
    $.post('exam/templateSelect.html',{},function(view){
        main.showLgDialog({
            title:'选择模板',
            content:view,
            submit:function(){
                main.closeDialog();
            }
        });
        exam.defaultTemplate();

    });
}

exam.defaultTemplate = function(){
    $('#templateGrid tr td:nth-child(11)').remove();
    $('#templateGrid tr th:nth-child(11)').remove();


    $('#templateGrid tr td:nth-child(7) span').removeAttr('onContextMenu');

    $('#templateGrid tr').dblclick(function(){
        var templateId = $('td:eq(0) input',$(this)).val();
        var templateName = $('td:eq(4)',$(this)).text().trim();
        $('#fill-form-template-name').val(templateName);
        $('#fill-form-template-id').val(templateId);
        main.closeDialog();
    });


}

exam.toShowTemplateDetail = function(){
    var templateId = $('#fill-form-template-id').val();
    if(!templateId){
        //没有选择
        alert('请先选择考试模板');
        return ;
    }

    $.post('exam/templateDetail.html',{templateId:templateId},function(view){
        main.showLgDialog({
            title:'模板详情',
            content:view,
            submit:function(){
                main.closeDialog();
            }
        });
        //处理默认状态
        exam.defaultTemplateDetail();
    });

}

exam.defaultTemplateDetail = function(){
    $('#template-edit-back-btn').remove();

    $('#dynamic-pane .form-control').prop('readOnly',true);
    $('#dynamic-pane input').prop('readOnly',true);
    $('#dynamic-pane .btn').remove();


    $('#static-pane .form-control').prop('readOnly',true);
    $('#static-pane input').prop('readOnly',true);
    $('#static-pane .btn').remove();

}

exam.selectTime = function(no){
    if(no == 1){
        //选择区间方式
        $('#fill-form-duration').val('30').prop('disabled',true);

        $('#fill-form-start-time').val('').prop('disabled',false);
        $('#fill-form-end-time').val('').prop('disabled',false);
    }else{
        //选择了时长方式
        $('#fill-form-start-time').val('').prop('disabled',true);
        $('#fill-form-end-time').val('').prop('disabled',true);

        $('#fill-form-duration').val('30').prop('disabled',false);
    }
}