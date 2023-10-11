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
  
<%--   <link rel='stylesheet' href="<c:url value='/bootstrap-3.4.1/css/bootstrap.css' />"  type="text/css" /> --%>
<%--     <c:url var="jqueryJsUrl" value="/bootstrap-3.4.1/js/jquery.js" /> --%>
<%-- 	<script src="${jqueryJsUrl}"></script> --%>
<%-- 	<c:url var="bootstrapJsUrl" value="/bootstrap-3.4.1/js/bootstrap.js" /> --%>
<%-- 	<script src="${bootstrapJsUrl}"></script> --%>
  
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

.chat-person {
	width: 200px;
	height: 100%;
	margin: auto;
	float: left;
	background-color: #e8e8e8;
	overflow: auto;
}

.chat-person .left-m {
	height: 70px;
}

.chat-person .left-m:hover {
	background-color: #0f4c81;
}

.chat-person .left-m .left-d {
	width: 50px;
	height: 50px;
	background-color: #4459AB;
	float: left;
	margin-top: 10px;
	margin-left: 10px;
	margin-right: 10px;
	text-align: center;
	line-height: 50px;
	color: white;
	border-radius: 25px;
}

.active {
	background-color: #0f4c81;
}

.chat-person .left-m .right-d {
	width: 110px;
	height: 100%;
	float: left;
	/*background-color: red;*/
	text-align: center;
}

.chat-person .left-m .right-d div {
	margin-top: 8px;
	margin-bottom: 2px;
}

.chat-person ul li:hover {
	background-color: green;
}

.clearfix::after {
	content: "";
	display: block;
	clear: both;
	width: 0;
	height: 0;
	line-height: 0;
	visibility: hidden;
}
/* top */
.chat_top {
	height: 50px;
	border-top-left-radius: 10px;
	border-top-right-radius: 10px;
	padding-left: 20px;
	font-size: 20px;
	line-height: 50px;
	box-sizing: border-box;
	font-weight: 550;
	border-width: 0px;
}
/* middle */
/* 左边 */
.chat_middle {
	height: 455px;
	position: relative;
	box-sizing: border-box;
	overflow: auto;
	border-width: 0px;
}

.chat_left {
	width: 100%;
	height: 120px;
	margin-top: 20px;
}

.chat_left_item_1 {
	width: 50px;
	height: 50px;
	float: left;
	margin-top: 10px;
	margin-left: 10px;
	margin-right: 10px;
	text-align: center;
	line-height: 50px;
	color: white;
	border-radius: 25px;
}

.chat_left_item_2 {
	width: 0%;
	height: 100px;
	float: left;
	margin-top: 10px;
}

.chat_left_item_2 .chat_left_chat {
	float: left;
}

.chat_left_item_2 .chat_left_content {

	white-space: nowrap;
	padding: 15px;
	margin-top: 10px;
	background-color: #f4f5f7;
	display: inline-block;
	border-radius: 10px;
	border-top-left-radius: 0px;
}
/* 右边 */
.chat_right {
	width: 100%;
	height: 120px;
	margin-top: 20px;
}
.chat_left_time{
    width: 200px;
}
.chat_right_item_1 {
	width: 50px;
	height: 50px;
	float: right;
	margin-top: 10px;
	margin-left: 10px;
	margin-right: 10px;
	text-align: center;
	line-height: 50px;
	color: white;
	border-radius: 25px;
}

.chat_right_item_2 {
	width: 55%;
	height: 100px;
	float: right;
	margin-top: 10px;
}

.chat_right_time {
	width: 100%;
	text-align: right;
}

.chat_right_content {
	float: right;
	padding: 15px;
	border-radius: 10px;
	margin-top: 10px;
	border-top-right-radius: 0px;
	background-color: #4F7cff;
	color: white;
}
/* foot */
 .chat_foot { 
	height: 99px; 
	position: relative; 
 } 

.chat_context {
	height: 100%;
	width: 100%;
	font-size: 17px;
	box-sizing: border-box;
	outline: none;
	border-width: 0px;
	padding: 16px;
}

.chat_commit {
	width: 100px;
	height: 30px;
	color: white;
	background-color: #4F7cff;
	line-height: 30px;
	text-align: center;
	border-radius: 5px;
	position: absolute;
	right: 10px;
	bottom: 20px;
	margin-right: 10px;
}

.chat_context {
	resize: none;
}

.chat_context::placeholder {
	color: black;
	font-weight: 500k;
}

.line {
	width: 100%;
	border-top: 1px;
	border-color: #f4f5f7;
	border-style: solid;
}
/*   /* 聊天窗口的基本樣式 */
    .chat-window {
      width: 400px;
      height: 100%;
      background-color: #f1f1f1;
      border: 1px solid #d4d4d4;
    
    }

    .chat-header {
      padding: 10px;
      cursor: move;
      z-index: 10;
      background-color: #0f4c81;
      color: #fff;
    }
    
    .left-m:hover{
    	cursor: pointer;
    }
    
    
   .chat-person {
	  float: left;
	  width: 25%;  /* 或其他你需要的寬度 */
	}
	
	.chat-window {
	  float: right;
	  width: 75%;  /* 或其他你需要的寬度 */
	}

	#chattest1{
	  width: 1000px;
      height: 600px;
       border-radius: 10px;
       border:1px;
       
	}
    
</style>


<body class="body-10">

	<script type="text/javascript">
        // Your JavaScript functions
        function first() {
            // Your code here
        }

        window.onload = function() {
            first();
        };
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
            <a href="" data-w-tab="Tab 4" class="f-tab-button w-inline-block  w--current" style="text-decoration: none;">
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
                 
                    <div class="f-sub-heading-regular" style="white-space: nowrap;">您的好友目錄</div>
                    <div class="f-modal-user-list">
                      <div class="w-layout-grid f-modal-user-grid">
                      
                      
                      
                      
                        <div id="chattest1">
							<div class="chat-person" id="sidler"> </div>
							<div class="chat-window" style="z-index: 10; top: 15px; left: 490px;">
								<div class="chat_commento" id="main" style="display: none;">
									<div class="chat-header">
										<div id="top_left" style="float: left;display:none;"> </div>
										<div id="top_middle" style="display:none;"> </div>
									</div>
									<div class="chat_middle" id="chat_middle_item">
											<div style="color:red;text-align: center;font-size: 32px;margin-top: 50px;">
											    點擊左邊頭像就可以開始進行聊天了喔!!!
											</div>
									</div>
									<div class="line"></div>
									<div class="chat_foot"><textarea class="chat_context" id="chat_context_item" cols="30"
										rows="10" placeholder="請輸入"></textarea>
										<button type="button" class="chat_commit" onclick="SendData();"
										id="button">傳送[enter]</button>
									</div>
								</div>
							</div>
						</div>
					</div>
                       
                       
                       
                      </div>
                    </div>
                 		<div class="first" id="first"style="display: block; margin-left: 100px;">
							<input id="username" class="form-control"
								style="width: 400px; display: none" name="username" type="text"
								value="${employee.id}" /><br />
						</div>
					
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
    
    <script>
    	var basePath="http://10.0.101.88:8080"
	</script>
	<script>
	//我方跟對方ID
	let globalUserID1;
	let globalUserID2;

   		//拿到朋友名稱
	  var friendsListData = [];

	    // 將 forEach 的資料轉儲到全域變數中
	    <c:forEach var="emp" items="${friendsListnew}">
	        // 創建一個 JavaScript 物件，表示一個好友的資料
	        var friendData = {
	            name: "${emp[3]}",
	            // 其他資料屬性
	        };
	        console.log("第一次"+friendData);
	        // 將這個好友的資料加F入到全域變數的陣列中
	        friendsListData.push(friendData);
	    </c:forEach>

	    var friendsemail = [];

	    // 將 forEach 的資料轉儲到全域變數中
	    <c:forEach var="emp" items="${friendsListnew}">
	        // 創建一個 JavaScript 物件，表示一個好友的資料
	        var emailData = {
	            email: "${emp[4]}",
	            // 其他資料屬性
	        };
	        
	        // 將這個好友的資料加入到全域變數的陣列中
	        friendsemail.push(emailData);
	    </c:forEach>
	    
	  //拿到圖片src
	    var friendimages = [];

	    // 將 forEach 的資料轉儲到全域變數中
	    <c:forEach var="emp" items="${friendsList}">
	        // 創建一個 JavaScript 物件，表示一個好友的資料
	        var imagesData = {
	            src: "${emp[5]}",
	            // 其他資料屬性
	        };
	        
	        // 將這個好友的資料加入到全域變數的陣列中
	        friendimages.push(imagesData);
	    </c:forEach>
	  
    function refresh() {
    	var friendname = [];
   		var friendemail = [];
    	var imagessrc = [];
        $.ajax({
                "url": basePath+"/friend/get/"+user,
                type:'get',
                success:function (data){
                    //清空所有侧边栏
                    $("#sidler").children().remove();
                    //添加所有好友
                    let friends=data.data.friends;
                    
                    console.log(friendimages);           
                    console.log("Debug真好玩ㄏㄏ"+friendsListData);           
                    console.log("又出問題"+friends);   
                    console.log(friendsemail);           
                    console.log("開始迴圈");
                    for (var i = 0; i < friendsListData.length; i++) {
                    	var friend = friendsListData[i];
                        // 在这里使用 friend.name、friend 其他属性
                        console.log("Friend Name: " + friend.name);

                        // 将 friend.name 存入 test 数组
                        friendname.push(friend.name);
                    }
//                     console.log(friendname);
                         
                    for (var i = 0; i < friendsemail.length; i++) {
                    	var friend = friendsemail[i];
                        // 在这里使用 friend.name、friend 其他属性
                        console.log("Friend email: " + friend.email);

                        // 将 friend.name 存入 test 数组
                        friendemail.push(friend.email);
                    }
                         
                    for (var i = 0; i < friendimages.length; i++) {
                    	var images = friendimages[i];
                        // 在这里使用 friend.name、friend 其他属性
                        console.log("images src在這邊: " + images.src);

                        // 将 friend.name 存入 test 数组
                        imagessrc.push(images.src);
                    }
                    
                    console.log(friendname);
                    console.log(friendemail);
                    console.log(imagessrc);
                    
                    
                    for (let i=0;i<friends.length;i++){
                        let str;
                        let friend=friends[i];
                        if (friend.status==="accept"){
                        	if(imagessrc[i] == null){
                        		str='<div id='+friend.userID2+' class="left-m">\n' +
                                '			 <div class="left-d"><img src="images/avatar.png" class="f-avatar-image"></div>\n'+
                                '            <div class="left-d" style="display:none">'+friend.userID2+'</div>\n' +
                                '            <div class="right-d" style="margin-left: 10px;">\n' +
                                '                <div>'+ friendname[i] +'</div>\n' +
                                '                <div>'+ friendemail[i] +'</div>\n' +
                                '            </div>\n' +
                                '        </div>' 
                        	}else{
	
								console.log("我在debugrrrr"+friendname);
								console.log("我在debug"+friendname[i]);
								console.log("我在debug"+friendemail[i]);
								
                        		 str='<div id='+friend.userID2+' class="left-m">\n' +
                                 '			 <div class="left-d"><img src="data:image/jpeg;base64,'+ imagessrc[i] +'" class="f-avatar-image"></div>\n'+
                                 '            <div class="left-d" style="display:none">'+friend.userID2+'</div>\n' +
                                 '            <div class="right-d" style="margin-left: 10px;">\n' +
                                 '                <div>'+ friendname[i] +'</div>\n' +
                                 '                <div>'+ friendemail[i] +'</div>\n' +
                                 '            </div>\n' +
                                 '        </div>'   
                        	}
                                                                  
                        }
                        
                        $("#sidler").append(str);      
                        clickSidler();

                    	
                    }
                }
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
        clickSidler();
        //
//         refresh();
    }
</script>
	<script src="http://pv.sohu.com/cityjson?ie=utf-8"></script>
	<script>
	
    // 成功发送
    var send_message=document.getElementById("chat_middle_item");
    // 发送内容
    var message=document.getElementById("chat_context_item");

    //将消息右边气泡加在右边
    function appendRight(user,time,ip,str,image) {
        //创建消息气泡
        console.log(image);
        if(image == ""){
        	var ans='<div class="chat_right_item_1 clearfix"><img src="images/avatar.png"></div>'+
        	'<div class="chat_right_item_1 clearfix" style="display:none">'+user+'</div>'+
            '<div class="chat_right_item_2">'+
            '<div class="chat_right_time clearfix">'+time+'</div>'+
            '<div class="chat_right_content clearfix">'+str+'</div>'
            +'</div>';
        }else{
        	var ans='<div class="chat_right_item_1 clearfix"><img src="data:image/jpeg;base64,'+image+'"></div>'+
        	'<div class="chat_right_item_1 clearfix" style="display:none">'+user+'</div>'+
            '<div class="chat_right_item_2">'+
            '<div class="chat_right_time clearfix">'+time+'</div>'+
            '<div class="chat_right_content clearfix">'+str+'</div>'
            +'</div>';
        }
        
        var oLi=document.createElement("div");
        oLi.setAttribute("class","chat_right");
        oLi.innerHTML=ans;
        //放入主聊天框中
        send_message.append(oLi);
        //让滚轮每次置于最后
        send_message.scrollTop=send_message.scrollHeight;
        //发送后让输入框置于空
        message.value="";
    }

    //创建左边消息气泡
    function appendLeft(user, time, ip, str, image2) {
            // 在這裡使用 images 的 Frid 和 src 屬性
            console.log("image2 存在");
		console.log(image2);
		
		// 初始化 imagesss 陣列
		var imagesss = [];
		var fid;
		
		for (var i = 0; i < image2.length; i++) {
		    var images = image2[i];
		
		    // 在這裡使用 images 的 Frid 和 src 屬性
		    console.log("images Frid在這邊: " + images.Frid);
		    console.log("images src在這邊: " + images.src);
		
		    // 將每一組 Frid 和 src 存為一個物件，然後加入到 imagesss 陣列
		    imagesss.push({
		        Frid: images.Frid,
		        src: images.src
		    });
		}
		
		// 檢查每一個 Frid 是否與 user 相同
		for (var i = 0; i < imagesss.length; i++) {
		    if (user === imagesss[i].Frid) {
		        fid = imagesss[i].src;
		        break;  // 找到後即停止迴圈
		    }
		}
		
		console.log("找到的 fid 是: " + fid);

		
        console.log(imagesss[0]);
        console.log("左边...");
        if(fid == null){
        	var ans='<div class="chat_left_item_1 clearfix"><img src="images/avatar.png"></div>'+
        	'<div class="chat_left_item_1 clearfix" style="display:none">'+user+'</div>'+
            '<div class="chat_left_item_2">'+
            '<div class="chat_left_time clearfix">'+time+'</div>'+
            '<div class="chat_left_content clearfix">'+str+'</div>'
            +'</div>';
        }else{
        	var ans='<div class="chat_left_item_1 clearfix"><img src="data:image/jpeg;base64,'+fid+'"></div>'+
        	'<div class="chat_left_item_1 clearfix" style="display:none">'+user+'</div>'+
            '<div class="chat_left_item_2">'+
            '<div class="chat_left_time clearfix">'+time+'</div>'+
            '<div class="chat_left_content clearfix">'+str+'</div>'
            +'</div>';
        }
        
        var oLi=document.createElement("div");
        oLi.setAttribute("class","chat_left");
        oLi.innerHTML=ans;
        send_message.append(oLi);
        send_message.scrollTop=send_message.scrollHeight;
        message.value="";
    }

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
                
                var user = user1; // 將 "your_username" 替換為實際的使用者名稱
                var cip = returnCitySN["cip"]; // 假設 returnCitySN["cip"] 包含IP地址

                var url = "ws://10.0.101.88:8080/websocket/" + user + "/" + cip; // 建立WebSocket URL

                console.log("WebSocket URL:", url); // 輸出URL到控制台

                //socket打开事件
                ws.onopen = function (event) {
                    console.log("已经与服务器建立了连接...");
                    
                    //登入刷新好友資訊
                    refresh();
                    
                    
                    alert("登陆成功，可以开始聊天了")
                    
                    
                    //显示自己头像
                    let top = document.getElementById("top_middle");
                    top.innerHTML=user;
                    globalUserID1 = user;
                    
                    
						//document.getElementById("info").innerHTML += "<font size='4' color='red'>欢迎</font>"+user +"加入"+ "<br>";
                };
                //接收消息事件
                ws.onmessage = function (event) {
						
                	 var itemPictureBase64 = "${itemPictureBase64}";
                	 
                	 var friendsList = [];  // JavaScript 陣列

                	 var friendsList = [];  // JavaScript 陣列

                	  // 使用 JSTL 來迭代 Java 的 friendsList 集合
                	  <c:forEach var="emp" items="${friendsList}">
                	    // 使用 EL 表達式來取得每一個 'emp' 物件的屬性，然後轉換為 JavaScript 語法
                	    friendsList.push({
                	      Frid: "${emp[0]}", 
                	      src: "${emp[5]}"
                	    });
                	  </c:forEach>

	             	  
                	
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
                        appendRight(data.user,data.date,data.ip,data.message,itemPictureBase64, );
                    //    如果是别人发送消息,放入左边的消息气泡
                    }else if (data.user===uuid && data.message!=='add' &&data.message!=='yes_add'){
                        appendLeft(data.user,data.date,data.ip,data.message,friendsList);
                    }  
                };
                //关闭时间
                ws.onclose = function (event) {
                    alert("发生错误!")
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

    

    function clickSidler(){
        //实现侧边栏的导航
        //因为class属性相同,所以要用for循环
        for (const elementsByClassNameElement of document.getElementsByClassName("left-m")) {
            //遍历所有active属性,该属性标注的标签背景色为白色,先移除所有白色
            elementsByClassNameElement.onclick=function () {
                for (const active of document.getElementsByClassName("active")) {
                    active.classList.remove("active");
                }
                //将点击到的侧边栏标记为白色
                elementsByClassNameElement.classList.add("active");
                //遍历所有left-d属性的标签,取出其内容,设置到聊天框头标题里
                for (const elementsByClassNameElement1 of elementsByClassNameElement.getElementsByClassName("left-d")) {
                    let top = document.getElementById("top_left");
                    top.innerHTML=elementsByClassNameElement1.textContent;
                    //清楚聊天窗体内容
                    let lastElementChild = send_message.lastElementChild;
                    while (lastElementChild){
                        send_message.removeChild(lastElementChild);
                        lastElementChild=send_message.lastElementChild;
                    }
                    //点击和谁聊天
                    if (!elementsByClassNameElement1.classList.contains("group")){
                        uuid=elementsByClassNameElement1.textContent;
                        
                        globalUserID2 = uuid; 
//                         console.log(globalUserID2);
                    }
                }
            }
        }
    }

		document.getElementById("sidler").addEventListener("click", function () {
	    // 選擇現有的 chat_middle 元素
	    let chatMiddleElement = document.getElementById("chat_middle_item");
	    
	    console.log(globalUserID1);
	    console.log(globalUserID2);
	
	    var senderUserid = globalUserID2;
	    var receiverUserid = globalUserID1;
	
	    // 构建 URL
	    var url = '/findByfriendmessage?senderUserid=' + senderUserid + '&receiverUserid=' + receiverUserid;
	
	    // 发起异步请求
	    fetch(url)
	        .then(response => response.text())
	        .then(data => {
	            // 使用 DOMParser 解析 HTML 字符串
	            let parser = new DOMParser();
	            let doc = parser.parseFromString(data, 'text/html');
	            
	            // 获取 <div> 元素
	            let ulElement = doc.querySelector('div');
	            
	            if (ulElement) {
	                // 更新 chat_middle 的內容
	                chatMiddleElement.innerHTML = ulElement.outerHTML;
	            	// 設置滾動條到最底部
	                chatMiddleElement.scrollTop = chatMiddleElement.scrollHeight;
	              
	            }
	        })
	        .catch(error => {
	            console.error('请求出错:', error);
	        });
	});

    function SendData() {
    	console.log("1234578");
    	
        var data = message.value;
        //构建json对象
        var json={}
        json.message=data;
        json.user=user;
        json.uuid=uuid;
        console.log("987654");
        try {
            //向后台服务器发生消息
            if (data!=null && data!==''){
                ws.send(JSON.stringify(json));
                
            }
        } catch (ex) {
            alert(ex.message);
        }
    }
    
    //键盘事件
    document.getElementById("chat_context_item").onkeydown = function (e) {
        e = e || window.event;
        if(e.keyCode == 13){
            SendData();
        }
    }

</script>
    
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
  <script src="https://d3e54v103j8qbb.cloudfront.net/js/jquery-3.5.1.min.dc5e7f18c8.js?site=64e5ed3359b16400aec286b0" type="text/javascript" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
  <script src="js/webflow.js" type="text/javascript"></script>
</body>
</html>