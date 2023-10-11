<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html><!--  This site was created in Webflow. https://www.webflow.com  -->
<!--  Last Published: Wed Sep 13 2023 01:56:47 GMT+0000 (Coordinated Universal Time)  -->
<html data-wf-page="650071e17cd3c26438525fb8" data-wf-site="64e5ed3359b16400aec286b0">
<head>
  <meta charset="utf-8">
  <title>friend list</title>
  <meta content="friend list" property="og:title">
  <meta content="friend list" property="twitter:title">
  <meta content="width=device-width, initial-scale=1" name="viewport">
  <meta content="Webflow" name="generator">
  
   <link rel='stylesheet' href="<c:url value='/bootstrap-3.4.1/css/bootstrap.css' />"  type="text/css" />
    <c:url var="jqueryJsUrl" value="/bootstrap-3.4.1/js/jquery.js" />
	<script src="${jqueryJsUrl}"></script>
	<c:url var="bootstrapJsUrl" value="/bootstrap-3.4.1/js/bootstrap.js" />
	<script src="${bootstrapJsUrl}"></script>
  
 <link rel='stylesheet' href="<c:url value='/css/normalize.css' />"  type="text/css" />
 <link rel='stylesheet' href="<c:url value='/css/webflow.css' />"  type="text/css" />
 <link rel='stylesheet' href="<c:url value='/css/5teams-wondrous-site.webflow.css' />"  type="text/css" />
  <link href="https://fonts.googleapis.com" rel="preconnect">
  <link href="https://fonts.gstatic.com" rel="preconnect" crossorigin="anonymous">
  <script src="https://ajax.googleapis.com/ajax/libs/webfont/1.6.26/webfont.js" type="text/javascript"></script>
  <script type="text/javascript">WebFont.load({  google: {    families: ["Open Sans:300,300italic,400,400italic,600,600italic,700,700italic,800,800italic","Varela Round:400","Exo:100,100italic,200,200italic,300,300italic,400,400italic,500,500italic,600,600italic,700,700italic,800,800italic,900,900italic","Oswald:200,300,400,500,600,700","Great Vibes:400"]  }});</script>
  <script type="text/javascript">!function(o,c){var n=c.documentElement,t=" w-mod-";n.className+=t+"js",("ontouchstart"in o||o.DocumentTouch&&c instanceof DocumentTouch)&&(n.className+=t+"touch")}(window,document);</script>
  <link href="images/favicon.png" rel="shortcut icon" type="image/x-icon">
  <link href="images/webclip.png" rel="apple-touch-icon">
</head>

<style>
#add_person {	
	box-sizing: content-box;
	border-radius: 30px;
}

#add_person:hover {
	background-color: #0f4c81;
}

#add_person_button:hover{
	cursor: pointer;
}

.friendsss{
	background-color: #e8e8e8;
}

}
</style>

<body>
<script>
    var globalEmpId = null;
    var globalEmpname = null;
    var globalEmpmyname = null;
</script>

  <div data-collapse="medium" data-animation="default" data-duration="400" data-easing="ease" data-easing2="ease" role="banner" class="nav-bar-c77py w-nav">
    <div class="nav-main">
      <div class="wrapper nav-bar-wrapper">
        <a href="/" class="brand w-nav-brand">
          <div><img src="images/LOGO.png" loading="lazy" width="214" sizes="(max-width: 479px) 55vw, 213.99147033691406px" alt="" srcset="images/LOGO-p-500.png 500w, images/LOGO.png 556w"></div>
        </a>
        <div class="navigation">
          <nav role="navigation" class="nav-menu w-nav-menu">
            <a href="/" class="nav-link w-nav-link">首頁</a>
            <div data-hover="false" data-delay="0" class="dropdown w-dropdown">
              <div class="dropdown-toggle w-dropdown-toggle">
                <div class="icon w-icon-dropdown-toggle"></div>
                <div>遊戲類別</div>
              </div>
              <nav class="w-dropdown-list">
									<a href="/ItemCategory?category=動作冒險" class="dropdown-link-2 w-dropdown-link">動作冒險</a> 
									<a href="/ItemCategory?category=角色扮演" class="dropdown-link-3 w-dropdown-link">角色扮演</a> 
									<a href="/ItemCategory?category=即時戰略" class="dropdown-link-4 w-dropdown-link">即時戰略</a> 
									<a href="/ItemCategory?category=卡牌策略" class="dropdown-link-5 w-dropdown-link">卡牌策略</a>
									<a href="/ItemCategory?category=熱門商品" class="dropdown-link-5 w-dropdown-link">熱門商品</a>
									<a href="/ItemCategory?category=優惠商品" class="dropdown-link-5 w-dropdown-link">優惠商品</a>
              </nav>
            </div>
            <a href="topup.html" class="nav-link w-nav-link">點數商店</a>
            <a href="account-details/news-copy-2.html" class="nav-link w-nav-link">消息新聞</a>
            <div><img src="images/螢幕擷取畫面_2023-09-02__1_-removebg-preview-removebg-preview.png" loading="lazy" width="68" sizes="(max-width: 991px) 100vw, (max-width: 1439px) 5vw, 67.99715423583984px" alt="" srcset="images/螢幕擷取畫面_2023-09-02__1_-removebg-preview-removebg-preview-p-500.png 500w, images/螢幕擷取畫面_2023-09-02__1_-removebg-preview-removebg-preview.png 552w"></div>
            <div data-hover="false" data-delay="0" class="dropdown w-dropdown">
              <div class="dropdown-toggle w-dropdown-toggle">
                <div class="icon w-icon-dropdown-toggle"></div>
                <div>會員中心</div>
              </div>
              <nav class="w-dropdown-list">
                <a href="/findEmployee.do?id=${sessionScope.myUser.id}&userId=${sessionScope.myUser.id}" class="dropdown-link w-dropdown-link">個人資料</a>
                <a href="/findfriendsid?id=${sessionScope.myUser.id}" aria-current="page" class="dropdown-link-2 w-dropdown-link w--current">好友</a>
                <a href="/findPlayergameid?id=${sessionScope.myUser.id}" class="dropdown-link-3 w-dropdown-link">遊戲庫</a>
                <a href="account-details/coupon.html" class="dropdown-link-4 w-dropdown-link">我的優惠卷</a>
                <a href="account-details/viewpurchasehistory.html" class="dropdown-link-5 w-dropdown-link">檢視購買記錄</a>
              </nav>
            </div>
            <sec:authorize access="isAuthenticated()">
								<span> <sec:authentication property="name" />
								</span>
							</sec:authorize>
             <a href="<c:url value='/logout'/>" aria-current="page"
									class="nav-link w-nav-link">登出</a>
          </nav>
          <div class="menu-button w-nav-button">
            <div class="icon-2 w-icon-nav-menu"></div>
          </div>
          <div data-node-type="commerce-cart-wrapper" data-open-product="" data-wf-cart-type="rightDropdown" data-wf-cart-query="" data-wf-page-link-href-prefix="" class="w-commerce-commercecartwrapper cart">
            <a href="#" data-node-type="commerce-cart-open-link" class="w-commerce-commercecartopenlink cart-button w-inline-block" role="button" aria-haspopup="dialog" aria-label="Open cart">
              <div class="w-inline-block">購物車</div><img src="images/cart-icon.svg" alt="" class="cart-icon">
              <div style="display:none" data-count-hide-rule="empty" class="w-commerce-commercecartopenlinkcount item-count">0</div>
            </a>
            <div data-node-type="commerce-cart-container-wrapper" style="display:none" class="w-commerce-commercecartcontainerwrapper w-commerce-commercecartcontainerwrapper--cartType-rightDropdown">
              <div data-node-type="commerce-cart-container" role="dialog" class="w-commerce-commercecartcontainer cart-container">
                <div class="w-commerce-commercecartheader cart-header">
                  <h4 class="w-commerce-commercecartheading">我的購物車</h4>
                  <a href="#" data-node-type="commerce-cart-close-link" class="w-commerce-commercecartcloselink w-inline-block" role="button" aria-label="Close cart"><svg width="16px" height="16px" viewbox="0 0 16 16">
                      <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                        <g fill-rule="nonzero" fill="#333333">
                          <polygon points="6.23223305 8 0.616116524 13.6161165 2.38388348 15.3838835 8 9.76776695 13.6161165 15.3838835 15.3838835 13.6161165 9.76776695 8 15.3838835 2.38388348 13.6161165 0.616116524 8 6.23223305 2.38388348 0.616116524 0.616116524 2.38388348 6.23223305 8"></polygon>
                        </g>
                      </g>
                    </svg></a>
                </div>
                <div class="w-commerce-commercecartformwrapper">
                  <form data-node-type="commerce-cart-form" style="display:none" class="w-commerce-commercecartform">
                    <script type="text/x-wf-template" id="wf-template-9336d8a7-ba52-2880-0c69-78271ccadf80"></script>
                    <div class="w-commerce-commercecartlist cart-list" data-wf-collection="database.commerceOrder.userItems" data-wf-template-id="wf-template-9336d8a7-ba52-2880-0c69-78271ccadf80"></div>
                    <div class="w-commerce-commercecartfooter cart-footer">
                      <div aria-live="" aria-atomic="false" class="w-commerce-commercecartlineitem">
                        <div>總價</div>
                        <div class="w-commerce-commercecartordervalue">000.00元</div>
                      </div>
                      <div>
                        <div data-node-type="commerce-cart-quick-checkout-actions">
                          <a role="button" aria-haspopup="dialog" aria-label="Apple Pay" data-node-type="commerce-cart-apple-pay-button" style="background-image:-webkit-named-image(apple-pay-logo-white);background-size:100% 50%;background-position:50% 50%;background-repeat:no-repeat" class="w-commerce-commercecartapplepaybutton cart-pay-button" tabindex="0">
                            <div></div>
                          </a>
                          <a role="button" tabindex="0" aria-haspopup="dialog" data-node-type="commerce-cart-quick-checkout-button" style="display:none" class="w-commerce-commercecartquickcheckoutbutton cart-pay-button"><svg class="w-commerce-commercequickcheckoutgoogleicon" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="16" height="16" viewbox="0 0 16 16">
                              <defs>
                                <polygon id="google-mark-a" points="0 .329 3.494 .329 3.494 7.649 0 7.649"></polygon>
                                <polygon id="google-mark-c" points=".894 0 13.169 0 13.169 6.443 .894 6.443"></polygon>
                              </defs>
                              <g fill="none" fill-rule="evenodd">
                                <path fill="#4285F4" d="M10.5967,12.0469 L10.5967,14.0649 L13.1167,14.0649 C14.6047,12.6759 15.4577,10.6209 15.4577,8.1779 C15.4577,7.6339 15.4137,7.0889 15.3257,6.5559 L7.8887,6.5559 L7.8887,9.6329 L12.1507,9.6329 C11.9767,10.6119 11.4147,11.4899 10.5967,12.0469"></path>
                                <path fill="#34A853" d="M7.8887,16 C10.0137,16 11.8107,15.289 13.1147,14.067 C13.1147,14.066 13.1157,14.065 13.1167,14.064 L10.5967,12.047 C10.5877,12.053 10.5807,12.061 10.5727,12.067 C9.8607,12.556 8.9507,12.833 7.8887,12.833 C5.8577,12.833 4.1387,11.457 3.4937,9.605 L0.8747,9.605 L0.8747,11.648 C2.2197,14.319 4.9287,16 7.8887,16"></path>
                                <g transform="translate(0 4)">
                                  <mask id="google-mark-b" fill="#fff">
                                    <use xlink:href="#google-mark-a"></use>
                                  </mask>
                                  <path fill="#FBBC04" d="M3.4639,5.5337 C3.1369,4.5477 3.1359,3.4727 3.4609,2.4757 L3.4639,2.4777 C3.4679,2.4657 3.4749,2.4547 3.4789,2.4427 L3.4939,0.3287 L0.8939,0.3287 C0.8799,0.3577 0.8599,0.3827 0.8459,0.4117 C-0.2821,2.6667 -0.2821,5.3337 0.8459,7.5887 L0.8459,7.5997 C0.8549,7.6167 0.8659,7.6317 0.8749,7.6487 L3.4939,5.6057 C3.4849,5.5807 3.4729,5.5587 3.4639,5.5337" mask="url(#google-mark-b)"></path>
                                </g>
                                <mask id="google-mark-d" fill="#fff">
                                  <use xlink:href="#google-mark-c"></use>
                                </mask>
                                <path fill="#EA4335" d="M0.894,4.3291 L3.478,6.4431 C4.113,4.5611 5.843,3.1671 7.889,3.1671 C9.018,3.1451 10.102,3.5781 10.912,4.3671 L13.169,2.0781 C11.733,0.7231 9.85,-0.0219 7.889,0.0001 C4.941,0.0001 2.245,1.6791 0.894,4.3291" mask="url(#google-mark-d)"></path>
                              </g>
                            </svg><svg class="w-commerce-commercequickcheckoutmicrosofticon" xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewbox="0 0 16 16">
                              <g fill="none" fill-rule="evenodd">
                                <polygon fill="#F05022" points="7 7 1 7 1 1 7 1"></polygon>
                                <polygon fill="#7DB902" points="15 7 9 7 9 1 15 1"></polygon>
                                <polygon fill="#00A4EE" points="7 15 1 15 1 9 7 9"></polygon>
                                <polygon fill="#FFB700" points="15 15 9 15 9 9 15 9"></polygon>
                              </g>
                            </svg>
                            <div>Pay with browser.</div>
                          </a>
                        </div>
                      </div>
                    </div>
                  </form>
                  <div class="w-commerce-commercecartemptystate">
                    <div>No items found.</div>
                  </div>
                  <div aria-live="" style="display:none" data-node-type="commerce-cart-error" class="w-commerce-commercecarterrorstate form-error">
                    <div class="w-cart-error-msg" data-w-cart-quantity-error="Product is not available in this quantity." data-w-cart-general-error="Something went wrong when adding this item to the cart." data-w-cart-checkout-error="Checkout is disabled on this site." data-w-cart-cart_order_min-error="The order minimum was not met. Add more items to your cart to continue." data-w-cart-subscription_error-error="Before you purchase, please use your email invite to verify your address so we can send order updates.">Product is not available in this quantity.</div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="f-section-large-4">
    <div class="text-block-25">
      <a href="/" class="link-10">首頁</a>&gt;會員中心&gt;個人資料
    </div>
    <div class="f-container-regular-2">
      <div class="f-career-wrapper">
        <div data-duration-in="300" data-duration-out="100" data-current="Tab 4" data-easing="ease" class="f-tab-copy w-tabs">
          <div class="f-tab-menu w-tab-menu">
            <a href="/findfriendsid?id=${sessionScope.myUser.id}" data-w-tab="Tab 4" class="f-tab-button w-inline-block  w--current" style="text-decoration: none;">
              <div class="text-block-12">您的好友</div>
            </a>
            <a href="/newFriends?id=${sessionScope.myUser.id}" data-w-tab="Tab 4" class="f-tab-button w-inline-block  w--current" style="text-decoration: none;">
              <div class="text-block-12">新增好友</div>
            </a>
          </div>
          <div class="w-tab-content">
            <div data-w-tab="Tab 4" class="f-tab-pane-2 w-tab-pane w--tab-active">
              <div class="f-modal-base-small myfriend">
                <div class="f-modal-form-block w-form">
                  <form id="email-form" name="email-form" data-name="Email Form" method="get" class="form-5" data-wf-page-id="650071e17cd3c26438525fb8" data-wf-element-id="523fe998-b8ce-1f55-bbb6-5c1ece1be436">
                    <div class="f-sub-heading-regular">您的帳戶</div>
                    
                     		<div class="f-modal-invite"  > 			  
						      <div>	
						      
						      
						      <c:choose>
									<c:when test="${myimage != null}"> 			
										<div class="f-modal-avatar"><img  src="data:image/jpeg;base64,${myimage}" ></div>	    
									</c:when>								   
									<c:otherwise>  
										<div class="f-modal-avatar"><img  src="images/avatar.png" ></div>				             
									</c:otherwise>															  
								</c:choose>
									            
						      	<div></div>   
						        <div class="f-paragraph-regular-7 f-text-weight-medium">${employee.username}</div>
						        <div class="f-paragraph-small-2">${employee.email}</div>
						      </div>
						    </div>  
						    <hr style="border: 1px solid;">
						    <div class="f-sub-heading-regular">可加為好友的使用者如下</div>
						    <script type="text/javascript">
					        // Your JavaScript functions
					        function first() {
					            // Your code here
					        }
					
					        window.onload = function() {
					            first();
					        };
					   </script>	 
                    <div class="f-modal-search">
                     
                     
                    </div>
                    <div class="f-modal-user-list">                                           
           <script type="text/javascript">
					        // Your JavaScript functions
					        function first() {
					            // Your code here
					        }
					
					        window.onload = function() {
					            first();
					        };
					   </script>	           
                    
         <div class="chat_commento" id="main" style="display: none;">			
			
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel">
					<div class="modal-dialog" role="document">
						<div class="modal-content" style="display:none;">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="myModalLabel">添加好友</h4>
							</div>
							<div class="modal-body">
							
							
							  	<input type="hidden" id="csrfToken" value="${_csrf.token}" />

								<form role="search" id="friend">	
									<div class="form-group">
									    <label for="userID2">好友名称:</label> 
									    <input type="text" name="userID2" id="userID2" class="form-control" placeholder="name" value="">
									</div>
									<div class="form-group">
										<label for="">對方好友名稱:</label> <input type="text"
											name="friendIp" id="friendip" class="form-control"
											placeholder="password" value="${employee.username}">
									</div>
									<div class="form-group">
										<label for="">自己名稱:</label> <input type="text"
											name="myIp" id="myip" class="form-control"
											placeholder="name" value="${employee.username}">
									</div>
									<div class="form-group">
										<label for="status">选择类型</label> <select class="form-control"
											name="status" id="status">
											<option selected>好友</option>
											<option>群聊</option>
										</select>
									</div>
								</form>
								
								
								
							</div>
							<div class="modal-footer">
								
								<button type="button" id="save" class="btn btn-primary">保存</button>
							</div>
						</div>
					</div>
				</div>
			
			</div>	
			
			
			           
                    <div class="first" id="first"
		style="display: block; margin-left: 100px;">

		<input id="username" class="form-control"
			style="width: 400px; display: none" name="username" type="text"
			value="${employee.id}" /><br />

	</div>
                    
                    <script>
					    var basePath="http://10.0.101.88:8080"
					</script>
                    
                    	<script>
    <!--    JQuery-->
    $('#myModal').on('shown.bs.modal', function (e) {
        var backdrop = document.querySelector(".modal-backdrop");
        if (backdrop) {
            backdrop.parentNode.removeChild(backdrop);
        }
    });

    $(document).on("click", "#add_person_button", function () {
        $("#myModal").modal('show');
    });

    /*
    * 添加好友
    * */

    $(document).on("click", "#save", function () {
        if ($("#status").val()==='好友'){
            status="accept";
        }else
            status=0;


        //只有在status==0的时候,才向对方发送确认,不然就是加入到群组里
        //只有对方同意添加后才添加
        if(status==="accept"){
            alert("添加好友完毕,待对方同意...")
            var json11={}
            json11.message='add';
            json11.user=user;
            json11.uuid=$("#userID2").val();
            try {
                //向后台服务器发生消息
                ws.send(JSON.stringify(json11));
            } catch (ex) {
                alert(ex.message);
            }
        //    不然就直接加入到群组里
        }else {
            let data = {
                "userID1":user,
                "userID2":$("#userID2").val(),
                "friendIp":$("#friendip").val(),
                //当前的添加对象的状态
                "status":status
            }
            append_friend(data);
        }


        //关闭模态框
        $("#myModal").modal("hide");


    })

    function append_friend(data1) {
    // 获取 CSRF 令牌
    const csrfToken = document.getElementById('csrfToken').value;

    // 打印 data1 对象的内容
    console.log("data1 内容:", data1);

    // 将 CSRF 令牌添加到请求头中
    $.ajax({
        url: basePath + "/friend/save",
        type: "post",
        data: JSON.stringify(data1),
        headers: {
            'Content-Type': 'application/json;charset=utf-8',
            'X-CSRF-TOKEN': csrfToken  // 添加 CSRF 令牌
        },
        success: function(response) {
            // AJAX 请求成功后刷新整个页面
            location.reload();
        },
        error: function(xhr, status, error) {
            // 处理 AJAX 请求错误
            console.error("AJAX 请求错误:", error);
        }
    });
}



    
    function refresh() {
        $.ajax({
                "url": basePath+"/friend/get/"+user,
                type:'get',  
            }
        )
    }
    //输入的用户名
    var input = document.getElementById("username");
    //主页对象
    let main = document.getElementById("main");
    //首页也对象
    var frt = document.getElementById("first");
    //当前对象
    var user;
    //发送对象
    var uuid;
    //当前聊天对象状态
    var status;
    //群聊名称
    var currentGroup;

    function first() {
    	
    
        //获取输入框的值
        user = input.value;
        //使得主页面出现
        main.style.display = 'block';
        //让首页隐藏
        frt.style.display = 'none';
        login(user);
        //让侧边栏可以点击
     
        //
        refresh();
    }
			</script>
                    
                    <script src="http://pv.sohu.com/cityjson?ie=utf-8"></script>
				<script>
			    //webSocket变量
			    var ws;			
			    //登录
			    function login(user1) {
			    	
			    	
			        if (!ws) {
			            //获取到传来的用户
			            var user = user1;
			            try {
			                //创建Websocket连接,ws开头的地址是后台Websocket服务器地址
			                //returnCitySN["cip"]获取ip地址
			                ws = new WebSocket("ws://10.0.101.88:8080/websocket/" + user+"/"+returnCitySN["cip"]);//连接服务器
			                //socket打开事件
			                ws.onopen = function (event) {
			                    console.log("已经与服务器建立了连接...");
			                    
			                    //登入刷新好友資訊
			                    refresh();
			  
// 			                    alert("登陆成功，可以开始聊天了")
			
			                };
			              //接收消息事件
			                ws.onmessage = function (event) {
			
			                    console.log(event)
			                    //获取后台服务器传来的数据转换成json表达式
			                    let data = eval("("+event.data+")");
			                    var s="";
			                    //如果是系统消息
			                    if (data.user==='系统' && uuid===''){
			                        //放入左边的消息气泡
			                        appendLeft('系统',data.date,data.ip,data.message);
			                    //    如果是自己发送消息,放入右边的消息气泡
			                    }else if (data.user===user && data.message!=='add' &&data.message!=='yes_add'){
			                        appendRight(data.user,data.date,data.ip,data.message);
			                    //    如果是别人发送消息,放入左边的消息气泡
			                    }else if (data.user===uuid && data.message!=='add' &&data.message!=='yes_add'){
			                        appendLeft(data.user,data.date,data.ip,data.message);
			                  
			                    
			                    //    添加好友消息
			                    }
			                    if (data.message === 'add') {
			                        if (data.uuid === user) {
			                            if (confirm(data.user + "请求添加好友,您是否同意?")) {
			                                let data2 = {
			                                    "userID1": user,
			                                    "userID2": data.user,
			//                                     "friendIp": $("#friendip").val(),  // 這裡換成了 "${employee.username}"
			                                    "status": "accept",                                 			                                    
			                                }
			                                append_friend(data2)
			                                //我确认同意了
			                                var json12={}
			                                json12.message='yes_add';
			                                json12.user=user;
			                                json12.uuid=data.user;                              
			                                try {
			                                    //向后台服务器发生消息
			                                    ws.send(JSON.stringify(json12));
			                                } catch (ex) {
			                                    alert(ex.message);
			                                }
			                            }
			                        }
			                    //   监听到对方同意添加好友之后
			                    }else if (data.message === 'yes_add') {
			                        if (data.uuid === user) {
			                            let data1 = {
			                                "userID1": user,
			                                "userID2": $("#userID2").val(),
			//                                 "friendIp": $("#friendip").val(),  // 這裡換成了 data.user
			                                "status": status,	                              
			                            }
			                        
			                            append_friend(data1);
			                        }
			                    }
			
			                };
			                //关闭时间
			                ws.onclose = function (event) {
			                    alert("發生錯誤!")
			                    console.log("已经与服务器断开连接...");
			                };
			                //发生错误事件
			                ws.onerror = function (event) {
			                    console.log("WebSocket异常！");
			                };
			            } catch (ex) {
			                alert(ex.message);
			            }
			        } else {
			            ws.close();
			            ws = null;
			        }
			        
			    
			    }
			
			</script>
                    
                    <script>
		                      function handleKeyPress(event) {
		                    	  if (event.keyCode === 13) { // 检查是否按下Enter键 (Enter键的键码是13)
		                    	    // 获取输入框的值
		                    	    var inputValue = document.getElementById('Modal-Field-Search').value;
		                    	    
		                    	    // 构建URL，将输入的值添加到参数中
		                    	    var url = '/findByName?name=' + inputValue;
		                    	    
		                    	    // 导航到新的URL
		                    	    window.location.href = url;     	    	                    	   
		                    	  }
		                    	}
	                      </script>  
                      <div class="w-layout-grid f-modal-user-grid friendsss">
				     		   <c:forEach var='emp' items='${user}'>
								    <!-- 初始化一個變數來追蹤是否有匹配 -->
								    <c:set var="matchFound" value="false" />
								    
								    <!-- 檢查每個 friend -->
								    <c:forEach var="friend" items="${friendsList}">
								    
								        <c:if test="${emp[0] == friend[0] || emp[0] == friend[1]}">
								            <!-- 如果找到匹配，設置變數為 true -->
								            <c:set var="matchFound" value="true" />
								        </c:if>
								    </c:forEach>
								    
								    <!-- 檢查是否找到匹配 -->
								    <c:if test="${!matchFound}">
								        <!-- 如果沒有找到匹配，顯示這段 HTML -->
								        <c:if test="${emp[0] != employee.id}">
								        <div id="add_person" class="w-layout-grid f-modal-user-grid">
								            <!-- ... (你的 HTML 內容) ... -->
								            
								            <div id="add_person" class="w-layout-grid f-modal-user-grid"> 
								        	<div class="f-modal-invite">             
								            <div class="f-modal-avatar">
								            
								            <c:choose>

											   <c:when test="${emp[3] != null}"> 
													<img src="data:image/jpeg;base64,${emp[3]}" class="f-avatar-image">
											   </c:when>
											   
											   <c:otherwise>  
											   		<img src="images/avatar.png" class="f-avatar-image">
											   </c:otherwise>
											  
											</c:choose>
								            								    
											</div>

								            	<div>
								                	<div>${emp[0]}</div>
								                	<div class="f-paragraph-regular-7 f-text-weight-medium">${emp[1]}</div>
								                	<div class="f-paragraph-small-2">${emp[2]}</div>
								            </div>
	<!-- 						            添加一個按鈕，並將 emp[0] 和 emp[1] 作為參數傳遞給 JavaScript 函數  -->
								            <button id="add_person_button"  class="f-button-primary w-button" onclick="showEmpInfo('${emp[0]}', '${emp[1]}')">加為好友</button>
								       	 </div>      
								    	</div>
								            
								        </div>
								        
									    </c:if>
								    </c:if>
								</c:forEach>
							<script>
							var saveButton = document.getElementById("save");
								function showEmpInfo(empId, empName) {
									 globalEmpId = empId;
									 globalEmpname = empName;
								     document.getElementById("userID2").value = globalEmpId;
								     saveButton.click();
							    }
							</script>
                    </div>
                    
                    </div>
                  </form>
                  <div class="w-form-done">
                    <div>Thank you! Your submission has been received!</div>
                  </div>
                  <div class="w-form-fail">
                    <div>Oops! Something went wrong while submitting the form.</div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <section class="footer-light-co111-copy-copy">
      <div class="container">
        <div class="footer-wrapper-two">
          <a href="#" class="footer-brand-2 w-inline-block"><img src="images/LOGO.png" loading="lazy" width="253" sizes="(max-width: 479px) 93vw, 252.9861297607422px" alt="" srcset="images/LOGO-p-500.png 500w, images/LOGO.png 556w"></a>
          <div class="footer-block-two">
            <div class="footer-title">company</div>
            <a href="#" class="footer-link-two">How it works</a>
            <a href="#" class="footer-link-two">Pricing</a>
            <a href="#" class="footer-link-two">Docs</a>
          </div>
          <div class="footer-block-two">
            <div class="footer-title">Quick Link</div>
            <a href="#" class="footer-link-two">Pricing</a>
            <a href="#" class="footer-link-two">Resources</a>
          </div>
          <div class="footer-form w-form">
            <form id="wf-form-Footer-Form" name="wf-form-Footer-Form" data-name="Footer Form" method="get" class="footer-form-container" data-wf-page-id="650071e17cd3c26438525fb8" data-wf-element-id="da20ee2c-0777-11ec-006c-95599e3f13b5">
              <div class="footer-title">Subscribe</div>
              <div class="footer-form-block"><input type="email" class="footer-form-field w-input" maxlength="256" name="Footer-Email-2" data-name="Footer Email 2" aria-label="Get product updates" placeholder="Get product updates" id="Footer-Email-2" required=""><input type="submit" value="" data-wait="Please wait..." class="footer-form-submit w-button"></div>
            </form>
            <div class="w-form-done">
              <div>Thank you! Your submission has been received!</div>
            </div>
            <div class="w-form-fail">
              <div>Oops! Something went wrong while submitting the form.</div>
            </div>
          </div>
        </div>
        <div class="footer-divider-two"></div>
      </div>
      <div class="footer-bottom-2">
        <div class="footer-copyright">© 2023 5team. All rights reserved </div>
        <div class="footer-copyright">
          <a href="#"><strong>聯繫我們:5team44684468@gmail.com</strong></a><strong> </strong>
        </div>
        <a href="#" class="footer-social-link-2 w-inline-block"><img src="images/face-removebg-preview.png" loading="lazy" width="96" alt="" class="image-41"></a>
        <a href="#" class="footer-social-link-2 w-inline-block"><img src="images/Twi-removebg-preview.png" loading="lazy" width="97" alt="" class="image-40"></a>
        <div class="footer-social-block-two">
          <a href="#" class="footer-social-link-2 w-inline-block"><img src="images/YT-removebg-preview.png" loading="lazy" width="93" alt=""></a>
        </div>
      </div>
    </section>
  </div>
  <script src="js/webflow.js" type="text/javascript"></script>
</body>
</html>