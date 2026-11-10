// var now = new Date();
// var str = now.getFullYear() + "-" + fix((now.getMonth() + 1), 2) + "-" + fix(now.getDate(), 2) + "T" + fix(now.getHours(), 2) + ":" + fix(now.getMinutes(), 2);
//
// // /*日期格式化:将日期格式化为两位，不足补零*/
// $("#starttime").val(str);
//
// function fix(num, length) {
//     return ('' + num).length < length ? ((new Array(length + 1)).join('0') + num).slice(-length) : '' + num;
// }
var str = $("#planname").val()
var title_des = str.split('-')
console.log(str)
$("#planname").val(title_des[0])
$("#plandes").val(title_des[1])


/*添加计划*/
function editPlan() {
    var inputElems = $("input")
    var selectElems = $("select")

    // 获取所有的input          // 依次设置值
    var id = $(inputElems[0]).val()
    var title = $(inputElems[1]).val() + "-" + $(inputElems[2]).val()// 任务名称
    var time = $(inputElems[3]).val()
    var username = $(inputElems[4]).val()
    var starttime = time.split('')
    starttime.splice(10,1,' ')
    starttime = starttime.join('')
    var status = 0
    var taskid = selectElems.val()


    /* 方法一：FormData 传递*/
    // 定义formData对象，并赋值
    var formData = new FormData()
    formData.append('id',id)
    formData.append('title',title)
    formData.append('starttime',starttime)
    formData.append('username',username)
    formData.append('status',status)
    formData.append('taskid',taskid)

    $.ajax({
        type:"POST",
        url:"/updatePlan",
        contentType:false,
        processData: false, // 上传进度
        cache: false,
        data:formData,
        success: function (data){
            window.location.href = '/listPlanIndex'

        }
    });
    // // 方法二：json对象传递
    // var formObj = {
    //     taskname,
    //     taskdes,
    //     tasktype,
    //     starttime
    // }
    // formJSON = JSON.stringify(obj)
    // // 发送请求
    // $.ajax({
    //     type:"POST",
    //     url:"/insertPlan",
    //     contentType:"application/json",
    //     data:formJSON,
    //     dataType:"json",
    //     success: function (data){
    //         alert('后端返回：', JSON.stringify(data));
    //     }
    // });
}


