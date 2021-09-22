<%--
  Created by IntelliJ IDEA.
  User: 86139
  Date: 2021/8/19
  Time: 23:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>省市级联查询</title>
  </head>
  <body>
  <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
  <script type="text/javascript">

    function loadDataAjax() {
        $.ajax({
            url:"queryProvince",
            type:"get",
            dataType:"json",
            success:function (data) {
                //删除旧的数据，吧以及存在的数据清空
                $("#province").empty();
                $("#province").append("<option value='0'>请选择...</option>")
                $.each(data,function (i,n) {
                    $("#province").append("<option value='"+n.id +"'>" +n.name +"</option>");
                })
            }
        })
    }
    $(function () {

        loadDataAjax();

        //给省份select绑定一个change事件，当select内容发生变化时触发事件
        $("#province").change(function () {
            //获取选中的列表框的值
            var obj = $("#province>option:selected");
            var provinceid = obj.val();

            //做一个ajax请求，获取省份的所有城市信息
            $.post("city",{proid:provinceid},
                function(resp){
                    $.each(resp,function (i,n) {
                        $("#city").append("<option value='"+n.id+"'>"+n.name+"</option>")
                    })
            },"json")
        })
    })


  </script>
  <p>省市级联查询，使用ajax</p>
  <div>
    <table border="2">
      <tr>
        <td>省份：</td>
        <td><select id="province">
              <option value="0">请选择...</option>
            </select>
        </td>
      </tr>
      <tr>
        <td>城市：</td>
        <td>
          <select id="city">
            <option value="0">请选择...</option>
          </select>
        </td>
      </tr>
    </table>
  </div>
  </body>
</html>
