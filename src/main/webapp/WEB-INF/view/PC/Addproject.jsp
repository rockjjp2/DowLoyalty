<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title></title>
    <link href="/DowLoyalty/Resources/html/css/Addproject.css" rel="stylesheet" />
    <script src="/DowLoyalty/Resources/html/js/jquery.js"></script>
    <link href="/DowLoyalty/Resources/html/css//jquery.datetimepicker.css" rel="stylesheet" />
    <script src="/DowLoyalty/Resources/html/js/jquery.datetimepicker.js"></script>
    <script src="/DowLoyalty/Resources/html/js/Addproject.js"></script>
    <link href="/DowLoyalty/Resources/html/css/Style.css" rel="stylesheet" />
</head>
<body>

    <!-- 左侧 -->
    <div class="nav-left">
        <ul class="sub-nav">
            <li><span class="li-Yspan"></span><a href="#Activity" class="a-Ycolor">活动信息</a></li>
            <li><span class="li-Nspan"></span><a href="#Person" class="a-Ncolor">参与人员</a></li>
            <li><span class="li-Nspan"></span><a href="#Gift" class="a-Ncolor">礼品配置</a></li>
            <li><span class="li-Nspan"></span><a href="#Product" class="a-Ncolor">产品配置</a></li>
            <li><span class="li-Nspan"></span><a href="#Integral" class="a-Ncolor">积分配置</a></li>
            <li><span class="li-Nspan"></span><a href="#Grade" class="a-Ncolor">等级配置</a></li>
        </ul>
    </div>
    <!-- 右侧 -->
    <div class="nav-right">
        <!--活动信息  -->
        <table id="Activity" class="table actived" style="display:block">
            <tbody>
                <tr>
                    <td style="width: 10%;">项目省份：</td>
                    <td style="width: 80%;">
                        <select id="Province">
                            <option>请选择省份</option>
                            <option>上海</option>
                            <option>北京</option>
                            <option>海南</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>开始时间：</td>
                    <td><input type="text" id="Start_time" readonly="readonly" placeholder="请选择开始时间" /></td>
                </tr>
                <tr>
                    <td>结束时间：</td>
                    <td><input type="text" id="End_time" readonly="readonly" placeholder="请选择结束时间" /></td>
                </tr>
                <tr>
                    <td>项目标题：</td>
                    <td><input id="Title" type="text" placeholder="请输入项目标题" /></td>
                </tr>
                <tr>
                    <td>活动介绍：</td>
                    <td><textarea id="Introduce" rows="3" cols="20" placeholder="请输入活动介绍"></textarea></td>
                </tr>
                <tr>
                    <td colspan="3"><button id="Activity_submit">下一步</button></td>
                </tr>
            </tbody>
        </table>
        <!-- 参与人员 -->
        <div id="Person" class="actived">
            <input id="Retailers" type="button" value="选择零售商">
            <fieldset>
                <div class="div-left field-div">已选:</div>
                <div class="div-left" style="width:90%;">
                    <ul id="Person_UL">
                        <li><span>家乐福</span></li>
                        <li><span>家乐福</span></li>
                        <li><span>家乐福</span></li>
                        <li><span>家乐福</span></li>
                        <li><span>家乐福</span></li>
                        <li><span>家乐福</span></li>
                        <li><span>家乐福</span></li>
                        <li><span>家乐福</span></li>
                    </ul>
                </div>
            </fieldset>

            <input type="button" value="选择推广员">
            <fieldset>
                <div class="div-left field-div">已选:</div>
                <div class="div-left" style="width:90%;">
                    <ul class="li_choose">
                        <li><span>推广员A</span></li>
                        <li><span>乐福</span></li>
                        <li><span>乐福ssss</span></li>
                        <li><span>乐福</span></li>
                        <li><span>乐福</span></li>
                        <li><span>推广员三</span></li>
                        <li><span>乐福</span></li>
                        <li><span>乐福</span></li>
                    </ul>
                </div>
            </fieldset>

            <fieldset>
                <div class="div-left field-div" style="width:15%">发货推广员:</div>
                <div class="div-left field-choose">推广员A</div>
            </fieldset>
            <button id="Activity_submit">下一步</button>
        </div>
        <!-- 礼品配置 -->
        <table id="Gift" class="table actived">
            <thead>
                <tr>
                	<th>分类</th>
                    <th>礼品</th>
                    <th>兑换积分</th>
                    <th>操作</th>
                </tr>
                <tr>
                    <td class="td_line" colspan="4"></td>
                </tr>
            </thead>
            <tbody>
                <tr>
                	<td>100</td>
                    <td><a>面包车<img src="/DowLoyalty/Resources/html/images/smallImg/triangle.png" /></a></td>
                    <td>100</td>
                    <td><a class="delete">删除</a></td>
                </tr>
                <tr>
                    <td colspan="4" class="text-left"><a><img src="/DowLoyalty/Resources/html/images/smallImg/Capture.PNG" style="width: 5%;" /><div>添加礼品</div></a></td>
                </tr>
                <tr>
                    <td colspan="4"><button id="Activity_submit">下一步</button></td>
                </tr>
            </tbody>
        </table>

        <!-- 产品配置 -->
        <table id="Product" class="table actived">
            <thead>
                <tr>
                    <th>产品类别</th>
                    <th>产品</th>
                    <th>规格</th>
                    <th>操作</th>
                </tr>
                <tr>
                    <td class="td_line" colspan="3"></td>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><a>除草剂<img src="/DowLoyalty/Resources/html/images/smallImg/triangle.png" /></a></td>
                    <td>千金<img src="/DowLoyalty/Resources/html/images/smallImg/triangle.png" /></td>
                    <td>150ml袋装</td>
                    <td><a class="delete">删除</a></td>
                </tr>
                <tr>
                    <td colspan="3" class="text-left"><a><img src="/DowLoyalty/Resources/html/images/smallImg/Capture.PNG" style="width: 5%;" /><div>添加产品</div></a></td>
                </tr>
                 <tr>
                    <td colspan="3"><button id="Activity_submit">下一步</button></td>
                </tr>
            </tbody>
        </table>

        <!-- 积分配置 -->
        <table id="Integral" class="table actived">
            <thead>
                <tr>
                    <th>产品</th>
                    <th>规格</th>
                    <th>1积分对应销售额</th>
                    <th>操作</th>
                </tr>
                <tr>
                    <td class="td_line" colspan="3"></td>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><a>千金<img src="/DowLoyalty/Resources/html/images/smallImg/triangle.png" /></a></td>
                    <td>150ml袋装<img src="/DowLoyalty/Resources/html/images/smallImg/triangle.png" /></td>
                    <td>100000</td>
                    <td><a class="delete">删除</a></td>
                </tr>
                <tr>
                    <td colspan="3" class="text-left"><a><img src="/DowLoyalty/Resources/html/images/smallImg/Capture.PNG" style="width: 5%;" /><div>添加条目</div></a></td>
                </tr>
                 <tr>
                    <td colspan="3"><button id="Activity_submit">下一步</button></td>
                </tr>
            </tbody>
        </table>

        <!-- 等级配置 -->
        <table id="Grade" class="table actived">
            <thead>
                <tr>
                    <th>等级</th>
                    <th>积分标线</th>
                    <th>操作</th>
                </tr>
                <tr>
                    <td class="td_line" colspan="2"></td>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>普通会员</td>
                    <td>1</td>
                    <td><a class="delete">删除</a></td>
                </tr>
                <tr>
                    <td class="td_line" colspan="2"></td>
                </tr>
                <tr>
                    <td>银卡会员</td>
                    <td>100</td>
                    <td><a class="delete">删除</a></td>
                </tr>
                <tr>
                    <td class="td_line" colspan="2"></td>
                </tr>
                <tr>
                    <td>金卡会员</td>
                    <td>10000</td>
                    <td><a class="delete">删除</a></td>
                </tr>
                <tr>
                    <td class="td_line" colspan="2"></td>
                </tr>
                <tr>
                    <td>钻石会员</td>
                    <td>100000</td>
                    <td><a class="delete">删除</a></td>
                </tr>
                <tr>
                    <td colspan="3" style="text-align: left;"><a><img src="/DowLoyalty/Resources/html/images/smallImg/Capture.PNG" style="width: 5%;" /><div>添加条目</div></a></td>
                </tr>
                 <tr>
                    <td colspan="2"><button id="Activity_submit">下一步</button></td>
                </tr>
            </tbody>
        </table>
    </div>

    <!-- 显示内容 -->
    <div id="layer" style="display:none">
        <div class="output">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <div class="line">
                <table style="border-spacing: 15px;">
                    <tr>
                        <td style="width: 20%; text-align: right;">零售商:</td>
                        <td style="width:50%"><input style="width:90%;" type="text" /></td>
                        <td style="width:30%"><input style="margin:0;" type="button" value="查询" /> </td>
                    </tr>
                </table>

                <table id="People_Content" class="table_content" border="0" cellpadding="0" cellspacing="0">
                    <thead>
                        <tr>
                            <th style="width:10%;"></th>
                            <th style="width:40%;">零售商</th>
                            <th style="width:40%;">省份</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td data-id="1" data-content="财付通"><input type="checkbox" onclick="return false;" id="checkbox_a1" class="chk_1" disabled="disabled" /><label for="checkbox_a1"></label></td>
                            <td>财付通</td>
                            <td>上海</td>
                        </tr>
                        <tr>
                            <td data-id="2" data-content="支付宝"><input type="checkbox" onclick="return false;" id="checkbox_a2" class="chk_1" disabled="disabled" /><label for="checkbox_a2"></label></td>
                            <td>支付宝</td>
                            <td>广州</td>
                        </tr>
                        <tr>
                            <td data-id="3" data-content="泰迪"><input type="checkbox" onclick="return false;" id="checkbox_a3" class="chk_1" disabled="disabled" /><label for="checkbox_a3"></label></td>
                            <td>泰迪</td>
                            <td>北京</td>
                        </tr>
                        <tr>
                            <td data-id="4" data-content="奥迪"><input type="checkbox" onclick="return false;" id="checkbox_a4" class="chk_1" disabled="disabled" /><label for="checkbox_a4"></label></td>
                            <td>奥迪</td>
                            <td>北京</td>
                        </tr>
                    </tbody>
                </table>

                <table style="width:100%;">
                    <tbody>
                        <tr>
                            <td style="width:50%;"><button id="Person_Submit" class="person_submit">确定</button></td>
                            <td style="width:50%;">
                                <div class="paging">
                                    <ul id="pagination-flickr" class="pagination">
                                        <li class="previous-off">上一页</li>
                                        <li class="active">1</li>
                                        <li><a href="?page=2">2</a></li>
                                        <li><a href="?page=3">3</a></li>
                                        <li><a href="?page=4">4</a></li>
                                        <li><a href="?page=5">5</a></li>
                                        <li><a href="?page=6">6</a></li>
                                        <li><a href="?page=7">7</a></li>
                                        <li class="next"><a href="?page=8">下一页</a></li>
                                    </ul>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <!--<div class="line">
                <table style="border-spacing: 15px;">
                    <tr>
                        <td style="width:20%; text-align: right;">礼品:</td>
                        <td style="width:80%"><input style="" type="text" /></td>
                    </tr>
                </table>
            </div>-->
        </div>

        <div class="hide"></div>
    </div>
    
    <!-- 礼品配置 -->
    <div id="layer1" style="display:none">
        <div class="output">
            <button type="button" class="close1" data-dismiss="modal" aria-hidden="true">×</button>
            <div class="line">
                <table style="border-spacing: 15px;">
                    <tr>
                        <td style="width: 20%; text-align: right;">礼品分类:</td>
                        <td style="width:50%"><select style="width:90%;">
                        
                        </select></td>
                         <td style="width: 20%; text-align: right;">礼品:</td>
                        <td style="width:50%"><select style="width:90%;">
                        
                        </select></td>
                    </tr>
                </table>
                
                <table style="width:100%;">
                    <tbody>
                        <tr>
                            <td style="width:50%;"><button id="Person_Submit1" class="person_submit1">确定</button></td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <!--<div class="line">
                <table style="border-spacing: 15px;">
                    <tr>
                        <td style="width:20%; text-align: right;">礼品:</td>
                        <td style="width:80%"><input style="" type="text" /></td>
                    </tr>
                </table>
            </div>-->
        </div>

        <div class="hide"></div>
    </div>
</body>

</html>