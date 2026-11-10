var now = new Date();
var str = now.getFullYear() + "-" + fix((now.getMonth() + 1), 2) + "-" + fix(now.getDate(), 2) + " " + fix(now.getHours(), 2) + ":" + fix(now.getMinutes(), 2);

// /*日期格式化:将日期格式化为两位，不足补零*/
// $("#starttime").val(str);
$("#settingtime").val(str);

function fix(num, length) {
    return ('' + num).length < length ? ((new Array(length + 1)).join('0') + num).slice(-length) : '' + num;
}

/*添加任务*/
function editTask() {
    var inputElems = $("input")
    console.log(inputElems)
    // 获取所有的input          // 依次设置值
    var id = $(inputElems[0]).val()
    var taskname = $(inputElems[1]).val()// 任务名称
    var taskdes = $(inputElems[2]).val() // 任务描述
    var tasktype = $(inputElems[3]).val() // 任务类型
    var username = $(inputElems[4]).val()
    var settingtime = $(inputElems[5]).val()
    // var time_set = $(inputElems[5]).val()
    // var time_set = $
    // time_set = time_set.toString()
    // console.log(time_set)
    // var settingtime = time_set.split('')
    // settingtime.splice(10,1,' ')
    // settingtime = settingtime.join('')

    /* 方法一：FormData 传递*/
    // 定义formData对象，并赋值
    var formData = new FormData()
    formData.append('id',id)
    formData.append('taskname',taskname)
    formData.append('taskdes',taskdes)
    formData.append('tasktype',tasktype)
    formData.append('username',username)
    formData.append('settingtime',settingtime)

    $.ajax({
        type:"POST",
        url:"/updateTask",
        contentType:false,
        processData: false, // 上传进度
        cache: false,
        data:formData,
        success: function (data){
            window.location.href = '/listAll'
            // alert(data)
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