// var now = new Date();
// var str = now.getFullYear() + "-" + fix((now.getMonth() + 1), 2) + "-" + fix(now.getDate(), 2) + "T" + fix(now.getHours(), 2) + ":" + fix(now.getMinutes(), 2);
//
// // /*日期格式化:将日期格式化为两位，不足补零*/
// $("#starttime").val(str);
//
// function fix(num, length) {
//     return ('' + num).length < length ? ((new Array(length + 1)).join('0') + num).slice(-length) : '' + num;
// }

/*添加任务*/
function addTask(){
    let taskName=$("#taskname").val();
    let taskDes=$("#taskDes").val();
    let userName=$("#username").val();
    let taskCategorys="";
    $("input[name='checkbox]:checkbox:checked").each(function(){
        taskCategorys+=$(this).val()+",";    
    })
    alert(taskCategorys+":"+taskName+":"+taskDes+":"+userName);
}