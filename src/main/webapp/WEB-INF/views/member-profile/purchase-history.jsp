<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html data-wf-page="650071e17cd3c26438525fbb" data-wf-site="64e5ed3359b16400aec286b0">
<head>
  <meta charset="utf-8">
  <title>購買記錄</title>
  <meta content="width=device-width, initial-scale=1" name="viewport">
  <link rel="stylesheet" href="<c:url value='/css/normalize.css' />" type="text/css" />
  <link rel="stylesheet" href="<c:url value='/css/webflow.css' />" type="text/css" />
  <link rel="stylesheet" href="<c:url value='/css/5teams-wondrous-site.webflow.css' />" type="text/css" />
  <link rel="stylesheet" href="<c:url value='/css/member-extras.css' />" type="text/css" />
  <link href="/images/favicon.png" rel="shortcut icon" type="image/x-icon">
</head>
<body class="body-7">
  <%@ include file="/WEB-INF/views/member-profile/_nav.jspf" %>
  <div class="f-section-large-4">
    <div class="text-block-25">
      <a href="/" class="link-10">首頁</a>&gt;會員中心&gt;購買記錄
    </div>
    <div class="f-container-regular-2">
      <div class="ex-wrap">
        <h1 class="ex-title">購買記錄</h1>
        <div class="ex-sub">${sessionScope.myUser.username} 的所有訂單</div>

        <c:if test="${empty orders}">
          <div class="ex-card ex-empty">
            還沒有購買記錄。<br>
            <a href="/" class="link-10" style="text-decoration: underline;">去逛逛有什麼好玩的遊戲</a>
          </div>
        </c:if>

        <c:forEach var="o" items="${orders}">
          <div class="ex-card">
            <div class="ex-card-head">
              <span class="ex-card-title">訂單 #${o.id}</span>
              <span class="ex-muted"><fmt:formatDate value="${o.createTime}" pattern="yyyy-MM-dd HH:mm"/></span>
            </div>
            <table class="ex-table">
              <c:forEach var="it" items="${o.items}">
                <tr>
                  <td>
                    <a href="<c:url value='/findgameid?id=${it.itemId}' />" class="link-10"><c:out value="${it.itemName}"/></a>
                  </td>
                  <td>$${it.price}</td>
                </tr>
              </c:forEach>
            </table>
            <div class="ex-sum">
              <c:if test="${o.discountAmount > 0}">
                <div class="ex-muted">小計 $${o.subtotal}</div>
                <div class="ex-discount">優惠券 <c:out value="${o.couponCode}"/>：-$${o.discountAmount}</div>
              </c:if>
              <div>實付 <strong>$${o.totalAmount}</strong></div>
            </div>
          </div>
        </c:forEach>
      </div>
    </div>
    <%@ include file="/WEB-INF/views/member-profile/_footer.jspf" %>
  </div>
  <script src="https://d3e54v103j8qbb.cloudfront.net/js/jquery-3.5.1.min.dc5e7f18c8.js?site=64e5ed3359b16400aec286b0" type="text/javascript" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
  <script src="/js/webflow.js" type="text/javascript"></script>
</body>
</html>
