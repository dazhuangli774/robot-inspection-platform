function robotMove(obj){
    var x = $(obj).parent().parent().find("td");
    var tasktype = $.trim(x.eq(2).text());
    if (confirm("确认执行此任务？")){
        window.location.href = "/robotMove?order=" + tasktype
    }
}
