function Record(obj){
    var x = $(obj).parent().parent().find("td");
    var recordid = $.trim(x.eq(0).text());

    $.ajax({
        type: "POST",
        url: "/getImage?record_id=" + recordid,
        async: false,
        dataType: "json",
        //data: calendarData,
        success: function (data) {
            var res = eval(data);
            console.log(res);
            $.each(res,function (i,d){
                var img_path = d.img_path;
                $("#record_img").attr("src","data:image/jpg;base64,"+img_path);
            })
        }
    });


    // // document.getElementById()

    //var file = document.getElementById('img_path')
    // var file = $("#img_path").
    //
    // console.log(file)
    // var img = file.files[0]
    // console.log(img)
    // var reader = new FileReader();
    // reader.readAsDataURL(img);
    // reader.onload = function (e) {
    //     $("#record_img").attr("src",this.result)
    // };
}
