<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/11/10 0010
  Time: 21:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script src="/ssm/js/jquery.js"></script>
    <script src="/ssm/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
    <link rel="stylesheet" href="/ssm/bootstrap-3.3.7-dist/css/bootstrap.css" />
</head>
<body>
        <table  class="table table-bordered table-striped" align="center" style="width: 850px
;height: 450px">
            <caption>
                <h1 style="text-align: center">鲜花管理系统</h1>
            </caption>
            <tr>
                <td colspan="2">
                    <button id="addFlower" data-toggle="modal" data-target="#addModel" class="btn btn-success">添加鲜花</button>
                    <button onclick="deleteBatch()" class="btn btn-danger">批量删除</button>
                </td>
                <td colspan="4">
                    <form id="form" class="form-inline" action="/ssm/index" method="post">
                        <div class="form-group">
                            <select id="category" name="cid" class="form-control">
                                <option value="-1">鲜花种类</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" name="fname" value="${queryVo.fname}"
                            placeholder="请输入鲜花姓名" />
                            <label>价格:</label>
                            <input type="text" style="width: 100px" name="start" class="form-control" value="${queryVo.start}" placeholder="起始价格" />
                            <input type="text" style="width: 100px" name="end" class="form-control" value="${queryVo.end}" placeholder="结束价格" />
                        </div>
                        <button type="submit" class="btn btn-primary">查询</button>
                    </form>
                </td>
            </tr>
            <tr>
                <th>
                    <div class="checkbox">
                        <label>
                            <input type="checkbox" >
                        </label>
                    </div>
                </th>
                <th>编号</th>
                <th>名称</th>
                <th>价格</th>
                <th>种类</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${flowers}" var="flower">
                <tr>
                    <td>
                        <div class="checkbox">
                            <label>
                                <input class="son" type="checkbox" value="${flower.fid}">
                            </label>
                        </div>
                    </td>
                    <td>${flower.fid}</td>
                    <td>${flower.fname}</td>
                    <td>${flower.price}</td>
                    <td>${flower.cname}</td>
                    <td>
                        <button id="updateBtn" onclick="updateFlower(${flower.fid})" data-toggle="modal" data-target="#updateModel" class="btn btn-info">修改</button>
                        <button  onclick="deleteBatch()" class="btn btn-warning">删除</button>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="6">
                    ${pages}
                </td>
            </tr>
        </table>
<script>
    //异步查询鲜花种类
    $.ajax({
        url: "/ssm/queryCategories",
        type: "get",
        dataType: "json",
        success: function (data) {
            for (var i = 0; i <data.length ; i++) {
                $('#category').append("<option value="+data[i].cid+">"+data[i].cname+"</option>");
            }
            //通过后台传递的cid和所有鲜花种类进行比较，相等的选中
            $('#category option').each(function () {
                //获取后台request域中的cid
                var cid='${queryVo.cid}';
                if ($(this).val()==cid){
                    $(this).prop('selected',true);
                }
            })
        }
    });
    //选中鲜花种类查询对应种类下的所有鲜花
    $('#category').change(function () {
        //将表单序列化
        var formJson=$('#form').serialize();
        location.href="/ssm/index?"+formJson;
    });
</script>

</body>
<%--添加页面模态窗--%>
<div class="modal fade" id="addModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">添加鲜花</h4>
            </div>
            <div class="modal-body">
                <form id="addForm" action="/ssm/addOrUpdateFlower" method="post" class="form-horizontal">
                    <div class="form-group">
                        <label  class="col-sm-2 control-label">鲜花名称</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="fname" placeholder="请输入鲜花名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="col-sm-2 control-label">价格</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="price" placeholder="请输入价格">
                        </div>
                    </div>

                    <div class="form-group">
                        <label style="position: relative;top:25px" class="col-sm-2 control-label">鲜花种类</label>
                        <div class="col-sm-offset-2 col-sm-10">
                            <select id="categoryModal" name="cid" class="form-control">
                                <option value="-1">鲜花种类</option>
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="subAdd" type="button" class="btn btn-primary">提交</button>
            </div>
        </div>
    </div>
</div>
    <script>
        //点击鲜花种类 异步查询所有花种
        $('#addFlower').click(function () {
            $.ajax({
                url: "/ssm/queryCategories",
                type: "get",
                dataType: "json",
                success: function (data) {
                    for (var i = 0; i < data.length; i++) {
                        $('#categoryModal').append("<option value=" + data[i].cid + ">" + data[i].cname + "</option>");
                    }
                }
            });
        });
        /*点击添加按钮提交表单*/
        $('#subAdd').click(function () {
            //提交这个模态窗 form id为addForm
            $('#addForm').submit();
        });

    </script>

<%--修改页面模态窗--%>
<div class="modal fade" id="updateModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">修改鲜花</h4>
            </div>
            <div class="modal-body">
                <form id="updateForm" action="/ssm/addOrUpdateFlower" method="post" class="form-horizontal">
                    <input type="hidden" name="fid" id="fid" />
                    <div class="form-group">
                        <label  class="col-sm-2 control-label">鲜花名称</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="fname" id="fname" placeholder="请输入鲜花名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="col-sm-2 control-label">价格</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="price" id="price" placeholder="请输入价格">
                        </div>
                    </div>

                    <div class="form-group">
                        <label style="position: relative;top:25px" class="col-sm-2 control-label">鲜花种类</label>
                        <div class="col-sm-offset-2 col-sm-10">
                            <select id="updateCategoryModal" name="cid" class="form-control">
                                <option value="-1">鲜花种类</option>
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="subUpdate" type="button" class="btn btn-primary">提交</button>
            </div>
        </div>
    </div>
</div>
<script>
    //点击修改按钮异步查询鲜花
     function updateFlower(fid) {
         $.ajax({
             url : "/ssm/queryByFid",
             data: {'fid':fid},//提交的数据
             type: "get",
             dataType: "json",
             success : function (data) {
                 var cid =data.cid;
                 $('#fname').val(data.fname);
                 $('#price').val(data.price);
                 //将隐藏域的值置为鲜花的主键，更新的时候需要根据主键更新
                 $('#fid').val(data.fid);
                 //异步查询鲜花的种类 如果当前的主键
                 $.ajax({
                     url: "/ssm/queryCategories",
                     type: "get",
                     dataType: "json",
                     success: function (data) {
                         for (var i = 0; i < data.length; i++) {
                             $('#updateCategoryModal').append("<option value=" + data[i].cid + ">" + data[i].cname + "</option>");
                         }
                         $('#updateCategoryModal option').each(function () {
                            if ($(this).val()==cid){
                                $(this).prop('selected',true);
                            }
                         });
                     }
                 });
             }
         });
     }
    /*点击修改按钮提交表单*/
    $('#subUpdate').click(function () {
        //提交这个模态窗 form id为addForm
        $('#updateForm').submit();
    });
    //单删和多删
    function deleteBatch () {
        //获取勾中的记录，将鲜花的主键通过,号进行拼接，传到后台
         if($('.son:checked').length==0){
            //如果一条数据都没勾中会弹出一个提示框
            alert("至少选中一条数据")
        }else {
            //定义一个数组用于存放勾中的id号
            var fids=[];
            //获取勾中的复选框
            $('.son:checked').each(function () {
                //讲勾选的id放到自定义的数组中
                    fids.push($(this).val());
                });
            //join:将数组内容通过制定分隔符进行分割，并且拼接成字符串，默认的分隔符是,号
            //向后台发出删除请求figs.join()
            location.href="/ssm/deleteBatch?fids="+fids.join();
        }
    }

</script>
</html>
