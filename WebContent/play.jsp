<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="resource.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width,initial-scale=1"/>
<title>HIt&amp;Blow</title>
<link type="text/css" rel="stylesheet" href="./css/play.css"/>
<script type="text/javascript" src="./js/play.js"></script>
</head>
<body>
<center>
<h2>Hit&amp;Blow</h2>
<p>【ルール説明】<br/>
   CPUが設定した3桁の数字を当てるゲームです。<br/>
   数字と位置が一致すれば"HIT"、数字のみ一致すれば"BLOW"<br/>
   EX)CPU:｢820｣ YOU:｢230｣→"1HIT 1BLOW"<br/>
   CPUが3桁の中で同じ数字(111や334など)を設定することはありません。<br/>
   3HIT目指して頑張ってください。<br/>
   リセットを押すとCPUの設定した数字を変えられます。
</p>
<form id="game" name="game" action="HBcontroller" method="post" >
<p>
3桁の数字を入力してください<br/>
<input type="text" name="youranswer" />
</p>
<button type="submit" value="結果表示" onclick="return check();">結果表示</button> &ensp;
<button type="submit" name="reset" value="reset">リセット</button><br/>

<jsp:useBean id="result" class="java.util.ArrayList" type="java.util.List<resource.Info>" scope="session"/>
<!-- 3HITしたら文章を表示 -->
<% for(int i=0;i<result.size();i++){ %>
<% if(result.get(i).getHit()==3){%>
<p>おめでとうございます！数字を当てることができました！<br/>
   下のボタンから再挑戦できますよ!
</p>
<button type="submit" name="reset" value="reset">再挑戦</button><br/>
<% break;} %>
<% } %>
<!-- ゲームを行っていたら結果を表示 -->
<% if(result.size()!=0){ %>
<br/>
<table class="result">
<tr><th>回数</th><th>あなたの解答</th><th>HIT</th><th>BLOW</th></tr>
<% for(int i=0;i<result.size();i++){ %>
<tr><td><%=result.get(i).getCount()%>回目</td>
    <td><%=result.get(i).getYourAnswer()%></td>
    <td><%=result.get(i).getHit()%></td>
    <td><%=result.get(i).getBlow()%></td>
</tr>

<% } %>
<% } %>
</table>
</form>
</center>
</body>
</html>