<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html data-wf-page="650071e17cd3c26438525fbb" data-wf-site="64e5ed3359b16400aec286b0">
<head>
  <meta charset="utf-8">
  <title>我的優惠券</title>
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
      <a href="/" class="link-10">首頁</a>&gt;會員中心&gt;我的優惠券
    </div>
    <div class="f-container-regular-2">
      <div class="ex-wrap">
        <h1 class="ex-title">我的優惠券</h1>
        <div class="ex-sub">結帳時在購物車選擇優惠券即可折抵</div>

        <input type="hidden" id="csrfToken" value="${_csrf.token}" />
        <div class="ex-card">
          <div class="ex-card-title" style="margin-bottom: 12px;">輸入優惠碼領取</div>
          <div class="ex-claim">
            <input type="text" id="couponCode" placeholder="請輸入優惠碼" maxlength="50" autocomplete="off">
            <button type="button" class="ex-btn" id="claimBtn">領取</button>
          </div>
          <div class="ex-msg" id="claimMsg"></div>
        </div>

        <c:if test="${empty userCoupons}">
          <div class="ex-card ex-empty">你目前沒有優惠券。留意最新消息，或輸入優惠碼領取！</div>
        </c:if>

        <c:forEach var="uc" items="${userCoupons}">
          <div class="ex-card ex-coupon ${uc.usable ? '' : 'is-off'}">
            <div>
              <div class="ex-coupon-value"><c:out value="${uc.coupon.discountText}"/></div>
              <div class="ex-card-title"><c:out value="${uc.coupon.code}"/></div>
              <div class="ex-muted">
                <c:out value="${uc.coupon.description}"/>
                <c:if test="${uc.coupon.minSpend > 0}"> · 滿 $${uc.coupon.minSpend} 可用</c:if>
                <c:choose>
                  <c:when test="${not empty uc.coupon.expireTime}"> · 期限 <fmt:formatDate value="${uc.coupon.expireTime}" pattern="yyyy-MM-dd"/></c:when>
                  <c:otherwise> · 無使用期限</c:otherwise>
                </c:choose>
              </div>
            </div>
            <div>
              <c:choose>
                <c:when test="${uc.usable}">
                  <a href="/cart" class="ex-btn">前往使用</a>
                </c:when>
                <c:when test="${uc.used}"><span class="ex-badge off">已使用</span></c:when>
                <c:when test="${uc.coupon.expired}"><span class="ex-badge off">已過期</span></c:when>
                <c:otherwise><span class="ex-badge off">已停用</span></c:otherwise>
              </c:choose>
            </div>
          </div>
        </c:forEach>
      </div>
    </div>
    <%@ include file="/WEB-INF/views/member-profile/_footer.jspf" %>
  </div>
  <script src="https://d3e54v103j8qbb.cloudfront.net/js/jquery-3.5.1.min.dc5e7f18c8.js?site=64e5ed3359b16400aec286b0" type="text/javascript" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
  <script src="/js/webflow.js" type="text/javascript"></script>
  <script>
    (function () {
      var token = document.getElementById('csrfToken').value;
      var msg = document.getElementById('claimMsg');
      document.getElementById('claimBtn').addEventListener('click', function () {
        var code = document.getElementById('couponCode').value.trim();
        if (!code) { msg.textContent = '請先輸入優惠碼'; msg.style.color = '#b3261e'; return; }
        fetch('/api/coupons/claim?code=' + encodeURIComponent(code), {
          method: 'POST', headers: { 'X-CSRF-TOKEN': token }
        }).then(function (r) { return r.json(); }).then(function (d) {
          msg.textContent = d.message;
          msg.style.color = d.success ? '#1a7f4b' : '#b3261e';
          if (d.success) { setTimeout(function () { location.reload(); }, 800); }
        }).catch(function () {
          msg.textContent = '連線失敗，請稍後再試'; msg.style.color = '#b3261e';
        });
      });
    })();
  </script>
</body>
</html>
