<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<base href="/sso/" >
<meta charset="utf-8">
<title>博客云-遇见最好的你</title>
<link rel="stylesheet" href="css/basic.css" />
<link href="css/autoemail.css" type="text/css" rel="stylesheet" />
<script  type="text/javascript"  src="js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="js/reg.js"></script>
<script src="js/emailComplete.js"></script>	    
	    
</head>

<body>
    	
        <div class="ucenter clearfix">
            <div class="uc-box clearfix">
            	<div class="title_main">
                    用户注册
                    <ul class="clearfix">
                    <li class="on">邮箱注册</li>
                    </ul>
            	</div>
            	<div id="register" class="ucon">
            		 <form id="personRegForm" method="post" onsubmit="return false;">
                        <input type="hidden" value="" name="signature">
                        <input type="hidden" value="" name="registerTime">
                        <input type="hidden" value="" name="referer">
            			<table class="log-reg-table" width="620" cellspacing="0" cellpadding="0" border="0">
            				<tbody>
            					
                                <tr>
                                    <td align="right">用户名：</td>
                                    <td>
                                        <div class="intbox">
                                        <input id="username" class="int-type w-thir" type="text" tips="用户名" name="username" rule=""> 注册后不能更改
                                        </div>
                                        <label id="username_msg" class="labelbox seclabel" style="width:240px;"></label>
                                    </td>
                                </tr>
                                <tr>
                                     <td align="right">密码：</td>
                                    <td>
                                        <div class="intbox">
                                        <input id="password"  class="int-type w-thir" type="password" tips="密码" name="password" rule="">
                                        </div>
                                        <label id="password_msg" class="labelbox seclabel" style="width:240px"></label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="nopad"></td>
                                    <td class="nopad">
                                        <ul class="ucr-stronger clearfix">
                                            <li id="weak">弱</li>
                                            <li id="middle">中</li>
                                            <li id="strength">强</li>
                                        </ul>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right">确认密码：</td>
                                    <td>
                                        <div class="intbox">
                                        <input id="reppassword" class="int-type w-thir" type="password" tips="确认密码" name="reppassword" rule="">
                                        </div>
                                        <label id="reppassword_msg" class="labelbox"></label>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right">联系电话：</td>
                                    <td>
                                        <div class="intbox">
                                        <input id="tel" class="int-type w-thir" type="text" tips="联系电话" name="phone" rule="">
                                        </div>
                                        <label id="tel_msg" class="labelbox seclabel" style="width:240px;"></label>
                                    </td>
                                </tr>
                                
                                <tr>
            						<td width="130" align="right">E-mail：</td>
                                    <td width="490">
                                    	<div class="toemail">
                                    	 <div class="intbox" style="position: relative;">
		                                     <div class="parentCls">
		      									  <input id="regemail" class="inputElem" type="text" name="email"  />
		      								   
		    								</div>
		    							</div>	
		    								 <label id="regemail_msg" class="labelbox"  style="float:left"></label>
	    								 </div>
                                    </td>
                                </tr>
                                
                            
                                <tr>
                                    <td align="right"> </td>
                                    <td class="checkbox">
                                    <p>
                                        <label>
                                        <input id="argee" class="check" type="checkbox" name="agree" value="1" checked="checked">我已阅读并同意<a class="tips" href="#">《博客云服务条款》</a>
                                        </label>
                                    </p>
                                    </td>
                                </tr>
                                <tr>
                                    <td> </td>
                                    <td>
                                        <a id="button"   class="ubg bigtn reggrey redbig" href="javascript:registUserInfo()">注册</a>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </form>
				</div>
				<div class="uside">
                    <div class="side-top nonebor tologin">
                        <p class="tit">已有博客云账号?</p>
                        <a class="ubg bigtn secbig hov_on" href="page/login">登录</a>
                    </div>
               </div>
               
            </div>
        </div>
        
<div align="center" style="padding-top: 30px" >
			Powered by <a  href="http://www.tianfang1314.cn" target="_blank">天方形田科技</a> &nbsp;&nbsp;
 Copyright © 2015-2016 荣登文化(RD工程)
			  <p><a href="http://www.miitbeian.gov.cn/" target="_blank" >湘ICP备15010299</a>  </p>
		</div>
		
	</div>
        
	
		
    </body>

	    

</html>
