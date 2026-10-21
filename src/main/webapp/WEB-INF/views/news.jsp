<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html data-wf-page="650071e17cd3c26438525fbb" data-wf-site="64e5ed3359b16400aec286b0">
<head>
  <meta charset="utf-8">
  <title>最新消息</title>
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
      <a href="/" class="link-10">首頁</a>&gt;最新消息
    </div>
    <div class="f-container-regular-2">
      <div class="ex-wrap">
        <h1 class="ex-title">最新消息</h1>
        <div class="ex-sub">商店公告、活動與更新資訊</div>

        <c:if test="${empty newsList}">
          <div class="ex-card ex-empty">目前還沒有消息，敬請期待！</div>
        </c:if>

        <c:forEach var="n" items="${newsList}">
          <details class="ex-card ex-news">
            <summary>
              <span class="ex-card-title"><c:out value="${n.title}"/></span>
              <span class="ex-muted"><fmt:formatDate value="${n.createTime}" pattern="yyyy-MM-dd"/></span>
            </summary>
            <div class="ex-news-body"><c:out value="${n.content}"/></div>
          </details>
        </c:forEach>
      </div>
    </div>
    <%@ include file="/WEB-INF/views/member-profile/_footer.jspf" %>
  </div>
  <script src="https://d3e54v103j8qbb.cloudfront.net/js/jquery-3.5.1.min.dc5e7f18c8.js?site=64e5ed3359b16400aec286b0" type="text/javascript" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
  <script src="/js/webflow.js" type="text/javascript"></script>
</body>
</html>
