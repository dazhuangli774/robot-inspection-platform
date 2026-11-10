
// document.addEventListener('DOMContentLoaded', function() {

//     // console.log(monthList)

//     var calendarEl = document.getElementById('calendar');

//     var calendar = new FullCalendar.Calendar(calendarEl, {
//         /*初始化日历*/
//         headerToolbar: {
//             left: 'title',
//             center: '',
//             right: 'addEventButton prevYear prev today next nextYear'
//         },
//         views: 'month',
//         fixedWeekCount: false,
//         showNonCurrentDates: false,
//         locale:'zh-cn',//引入中文包
//         themeSystem: 'bootstrap',
//         dayMaxEvents: true,
//         eventDisplay: 'block',
//         customButtons: {
//             addEventButton : {
//                 text: '新建计划',
//                 click: function () {
//                     window.location.href = "/insertPlanIndex"
//                 }
//             }
//         },
//         eventClick: function(info) {
//             var id = info.event.id
//             console.log(id)
//             //id = toString(id)
//             window.location.href = "/editPlan?id=" + id

//             // alert('Event: ' + info.event.title);
//             // // change the border color just for fun
//             // info.el.style.color = 'red';
//         },
//         /*点击日期*/
//         dateClick: function (info) {
//             alert(info.date.toISOString());
//         },
//         events:events,// 每日日程渲染
//     });
//     calendar.render();
// });

// $.ajax({
//     type: "POST",
//     url: "/listPlan",
//     async: false,
//     dataType: "json",
//     //data: calendarData,
//     success: function (data) {
//         var res = eval(data)
//         events = []
//         $.each(res,function (i,d){
//             var id = d.id
//             var t = d.title
//             var start = d.starttime
//             var status = d.status
//             var color
//             switch (status) {
//                 case "1": color = "#EE5465";break;
//                 case "2": color = "#1AB394";break;
//                 default: color = "#5AAAFF"
//             }
//             console.log(color)

//             events.push({
//                 id: id,
//                 title: t,
//                 start: start,

//                 //backgroundColor: color,
//                 color: color,
//                 eventTextColor: "rgb(0,0,0)"
//                 //borderColor:
//             })
//         })

//     }
// });