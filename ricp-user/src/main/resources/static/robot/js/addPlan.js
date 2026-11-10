function addPlan() {
    alert("test");
    // var startHour=$("#startHour").val()
    // var startMinute=$("#startMinute").val()
    // var endHour=$("#endHour").val()
    // var endMinute=$("#endMinute").val()
    // var repeat=$("#repeat").val()

    // //检验时间是否正确
    // startHour=parseInt(startHour)
    // startMinute=parseInt(startMinute)
    // endHour=parseInt(endHour)
    // endMinute=parseInt(endMinute)
    // if (startHour>endHour || (startHour==endHour && startMinute>endMinute)){
    //     return false;
    // }
    // var from;
    // var end;
    // if(repeat=="自定义"){
    //     from=$("#from").text();
    //     end=$("#end").text();
    //     alert(from,end);
    // }else if(repeat=="每天"){
    //     alert("每天");
    // }else{
    //     alert("一次");
    // }
    // var inputElems = $("input")
    // var selectElems = $("select")

    // // 获取所有的input          // 依次设置值
    // var title = $(inputElems[0]).val() + "-" + $(inputElems[1]).val()// 任务名称
    // var time = $(inputElems[2]).val()
    // var username = $(inputElems[3]).val()
    // var status = 0
    // var taskid = selectElems.val()

    // console.log(title)
    // console.log(username)
    // console.log(taskid)
    // console.log(repeat)
    // /* 方法一：FormData 传递*/
    // // 定义formData对象，并赋值
    // var formData = new FormData()
    // formData.append('title',title)
    // formData.append('username',username)
    // formData.append('status',status)
    // formData.append('taskid',taskid)

    // $.ajax({
    //     type:"POST",
    //     url:"/insertPlan",
    //     contentType:false,
    //     processData: false, // 上传进度
    //     cache: false,
    //     data:formData,
    //     success: function (data){
    //         window.location.href = '/listPlanIndex'

    //                 // alert(data)
    //     }
    // });
}