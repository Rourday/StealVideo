<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
 <HEAD>
  <TITLE>AD.无感监控系统</TITLE>
  <META NAME="Generator" CONTENT="EditPlus">
  <META NAME="Author" CONTENT="">
  <META NAME="Keywords" CONTENT="">
  <META NAME="Description" CONTENT="">
 </HEAD>
 <style type="text/css">
 body{
 }
 .logo{
  border:none;
  height:80px;
  width:1350px;
  margin-left:0px;
  margin-top:0px;
  }
  .logo img{
    height:60px;
  }
  
 .video{
    float:left;
  margin-left:100px;
    border: 1px solid #eee;
	height: 425px;
	width: 560px;
	
 }
 .video img
 {  margin-left: auto;
    margin-right: auto;
	height: 420px;
	width: 555px;
 }
 .control
 {
   float:left;
   padding-top:90px;
   padding-left:50px;
 }
 .content{
     width:900px;
	 height:500px;
     margin-top:10px;
	 margin-left:26px;
	 }
 #start{
 }
 #save{
   padding-top:30px;
 }
 #find{
   padding-top:30px;
 }
 </style>
 <script type="text/javascript">
 function stop()
 {
	document.getElementById("start").src="结束.jpg";
 }
 </script>

 <BODY>
 <div class="logo"><img src="logo.jpg"></div>
 <div class="content">
 <div class="video">
 <img src="2.jpg" >
 </div>
 <div class="control">
 <table id="basketball" width="99" height="98"  border="0"  cellpadding="0" cellspacing="0">
 <tr>
 <td><img src="开始.jpg" id="start" onclick="stop()"></td>
 </tr>
 <tr>
 <td ><img src="保存.jpg" id="save"></td>
 </tr>
 <tr>
 <td><img src="查看.jpg" id="find"></td>
 </tr>
 </div>
  </div>
 </BODY>
</HTML>
