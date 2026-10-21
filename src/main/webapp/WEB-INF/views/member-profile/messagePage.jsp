<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	
<!DOCTYPE html>
<html>
<body>


  <div>
  <script>
  console.log("我的圖片在這邊"+senderUserimage)
  </script>
  <div>
    <c:forEach var="messages" items="${message}">
        <c:if test="${messages.content != 'add' && messages.content != 'yes_add'}">
            <c:if test="${messages.senderUserid == senderUserid}">
                <div class="chat_left clearfix">
                    <c:choose>
                        <c:when test="${receiverUserimage != null}">
                            <div class="chat_left_item_1 "><img src="data:image/jpeg;base64,${receiverUserimage}"></div>
                        </c:when>
                        <c:otherwise>
                            <div class="chat_left_item_1 "><img src="images/avatar.png"></div>
                        </c:otherwise>
                    </c:choose>
                    <!-- 以下這行可能是多餘的，因為上面已經有一個選擇 -->
                    <!-- <div class="chat_left_item_1 "><img src="data:image/jpeg;base64,${receiverUserimage}"></div> -->
                    <div class="chat_left_item_2">
                        <div class="chat_time" style="white-space: nowrap;">${fn:substringBefore(messages.timeStamp, '.')}</div>
                        <div class="chat_left_content">
                            ${messages.content}
                        </div>
                    </div>
                </div> <!-- 這裡結束 chat_left 的 div -->
            </c:if>
            <c:if test="${messages.receiverUserid == senderUserid}">
                <div class="chat_right">
                    <c:choose>
                        <c:when test="${senderUserimage != null}">
                            <div class="chat_right_item_1 "><img src="data:image/jpeg;base64,${senderUserimage}"></div>
                        </c:when>
                        <c:otherwise>
                            <div class="chat_right_item_1 "><img src="images/avatar.png"></div> <!-- 修正類名 -->
                        </c:otherwise>
                    </c:choose>
                    <div class="chat_right_item_2 ">
                        <div class="chat_right_time">${fn:substringBefore(messages.timeStamp, '.')}</div>
                        <div class="chat_right_content">
                            ${messages.content}
                        </div>
                    </div>
                </div> <!-- 這裡結束 chat_right 的 div -->
            </c:if>
        </c:if>
    </c:forEach>
</div>


  
</body>
</html>