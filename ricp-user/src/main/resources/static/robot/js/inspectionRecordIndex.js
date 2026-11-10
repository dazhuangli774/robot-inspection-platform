var planname = $("table>tbody>tr").find("td:eq(0)");
planname.each(function (){
    var str = $(this).text()
    var title_des = str.split('-')
    $(this).text(title_des[0])
});
var planndes = $("table>tbody>tr").find("td:eq(1)");
planndes.each(function (){
    var str = $(this).text()
    var title_des = str.split('-')
    $(this).text(title_des[1])
});
