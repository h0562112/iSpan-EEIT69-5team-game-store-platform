<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>後臺 - 5Steam</title>

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
<link rel='stylesheet' href="<c:url value='/css/custom.css' />"
	type="text/css" />
<link rel='stylesheet' href="<c:url value='/css/styles.css' />"
	type="text/css" />
<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
	crossorigin="anonymous"></script>
<script src="<c:url value='/js/scripts.js' />"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	function deleteItem(ids, empno) {
		if (confirm('確定要刪除商品編號為: ' + empno + ' 這筆紀錄?')) {
			let url = "<c:url value='/adminpages/item/ItemDelete' />" + "/"
					+ ids;

			// 创建一个表单并设置属性
			let form = document.createElement('form');
			form.method = 'POST';
			form.action = url;

			// 添加 empNo 字段
			let input1 = document.createElement("input");
			input1.type = "hidden";
			input1.name = "empNo";
			input1.value = empno;
			form.appendChild(input1);

			// 添加 _method 字段
			let input2 = document.createElement("input");
			input2.type = "hidden";
			input2.name = "_method";
			input2.value = "DELETE";
			form.appendChild(input2);

			// 添加 CSRF 令牌字段
			let csrfInput = document.createElement("input");
			csrfInput.type = "hidden";
			csrfInput.name = "${_csrf.parameterName}";
			csrfInput.value = "${_csrf.token}";
			form.appendChild(csrfInput);

			document.body.appendChild(form); // 将表单添加到页面中
			form.submit(); // 提交表单

			return true;
		} else {
			return false;
		}
	}
	
	function queryItemByKeyword(){
	    var keyword = document.getElementById('form1').value;
	    window.location.href = '/adminpages/item/queryItemByKeyword?keyword=' + keyword;
	}
	
	
    // 檢查success屬性是否存在並為true
    <c:if test="${not empty success and success eq true}">
        // 使用jQuery選擇模態並顯示它
        $(document).ready(function() {
            $('#successModal').modal('show');
        });
    </c:if>
	
</script>

</head>

<body class="sb-nav-fixed">
	
	
	<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
		<!-- Sidebar Toggle-->
		<button class="btn btn-link btn-sm order-lg-0 me-0 me-lg-0"
			id="sidebarToggle" href="#!">
			<font size="4"><i class="fa-solid fa-bars fa-xl"></i></font>
		</button>
		<!-- Navbar Brand-->
		<a class="navbar-brand ps-4" href="<c:url value='/adminpages/adminindex' />"><font size="6"><b>5team</b></font></a>
		<!-- Navbar-->
		<ul class="navbar-nav ms-auto me-0 me-md-3 my-2 my-md-0">
			<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
				<font size="5"><i class="fas fa-user fa-fw"></i></font>
		</a>
				<ul class="dropdown-menu dropdown-menu-end"
					aria-labelledby="navbarDropdown">
					<li><a class="dropdown-item" href="#!"><font size="4">設定</font></a></li>
					<li><hr class="dropdown-divider" /></li>
					<li><a class="dropdown-item" href="<c:url value='/logout'/>"><font size="4">登出</font></a></li>
				</ul>
			</li>
		</ul>
	</nav>
	<div id="layoutSidenav">
		<div id="layoutSidenav_nav">
			<nav class="sb-sidenav accordion sb-sidenav-dark"
				id="sidenavAccordion">
				<div class="sb-sidenav-menu">
					<div class="nav">
						<div class="sb-sidenav-menu-heading"><font size="4">總覽</font></div>
						<a class="nav-link"
							href="<c:url value='/adminpages/adminindex' />">
							<div class="sb-nav-link-icon">
								<font size="4"><i class="fa-solid fa-house"></i><i class="bi bi-person-fill"></i></font>
							</div> <font size="4"><b>首頁</b></font>
						</a> <a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
							data-bs-target="#collapseLayouts" aria-expanded="false"
							aria-controls="collapseLayouts">
							<div class="sb-nav-link-icon">
								<font size="4"><i class="fa-solid fa-people-group"></i></font>
							</div> <font size="4"><b>會員</b></font>
							<div class="sb-sidenav-collapse-arrow">
								<i class="fas fa-angle-down"></i>
							</div>
						</a>
						<div class="collapse" id="collapseLayouts"
							aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
							<nav class="sb-sidenav-menu-nested nav">
								<a class="nav-link"
									href="<c:url value='/adminpages/user/queryUser' />"><font size="4"><b>會員查詢</b></font></a>
							</nav>
						</div>

						<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
							data-bs-target="#collapseLayouts2" aria-expanded="false"
							aria-controls="collapseLayouts2">
							<div class="sb-nav-link-icon">
								<font size="4"><i class="fa-solid fa-briefcase"></i></font>
							</div> <font size="4"><b>員工</b></font>
							<div class="sb-sidenav-collapse-arrow">
								<i class="fas fa-angle-down"></i>
							</div>
						</a>

						<div class="collapse" id="collapseLayouts2"
							aria-labelledby="headingthree" data-bs-parent="#sidenavAccordion">
							<nav class="sb-sidenav-menu-nested nav">
								<a class="nav-link"
									href="<c:url value='/adminpages/employee/queryEmployee' />"><font size="4"><b>員工管理</b></font></a>
								<a class="nav-link"
									href="<c:url value='/adminpages/employee/CreateEmployeeForm' />"><font size="4"><b>新增員工</b></font></a>

							</nav>
						</div>

						<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
							data-bs-target="#collapseLayouts3" aria-expanded="false"
							aria-controls="collapseLayouts3">
							<div class="sb-nav-link-icon">
								<font size="4"><i class="fa-solid fa-cart-shopping"></i></font>
							</div> <font size="4"><b>商品</b></font>
							<div class="sb-sidenav-collapse-arrow">
								<i class="fas fa-angle-down"></i>
							</div>
						</a>
						<div class="collapse" id="collapseLayouts3"
							aria-labelledby="headingthree" data-bs-parent="#sidenavAccordion">
							<nav class="sb-sidenav-menu-nested nav">
								<a class="nav-link"
									href="<c:url value='/adminpages/item/queryItem' />"><font size="4"><b>商品管理</b></font></a> <a
									class="nav-link"
									href="<c:url value='/adminpages/item/CreateItemForm' />"><font size="4"><b>新增商品</b></font></a>
							</nav>
						</div>
						
						
						<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
							data-bs-target="#collapseLayouts5" aria-expanded="false"
							aria-controls="collapseLayouts5">
							<div class="sb-nav-link-icon">
								<font size="5"><i class="fa-regular fa-clipboard"></i></font>
							</div> <font size="4"><b>訂單</b></font>
							<div class="sb-sidenav-collapse-arrow">
								<i class="fas fa-angle-down"></i>
							</div>
						</a>
						<div class="collapse" id="collapseLayouts5"
							aria-labelledby="headingthree" data-bs-parent="#sidenavAccordion">
							<nav class="sb-sidenav-menu-nested nav">
								<a class="nav-link"
									href="<c:url value='/adminpages/item/queryItem' />"><font size="4"><b>訂單查詢</b></font></a> 
							</nav>
						</div>
						


						<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
							data-bs-target="#collapseLayouts4" aria-expanded="false"
							aria-controls="collapseLayouts4">
							<div class="sb-nav-link-icon">
								<font size="4"><i class="fa-solid fa-ticket"></i></font>
							</div> <font size="4"><b>優惠券</b></font>
							<div class="sb-sidenav-collapse-arrow">
								<i class="fas fa-angle-down"></i>
							</div>
						</a>
						<div class="collapse" id="collapseLayouts4"
							aria-labelledby="headingthree" data-bs-parent="#sidenavAccordion">
							<nav class="sb-sidenav-menu-nested nav">
								<a class="nav-link" href=""><font size="4"><b>新增優惠券</b></font></a>
							</nav>
						</div>
						
						<div class="collapse" id="collapseLayouts4"
							aria-labelledby="headingthree" data-bs-parent="#sidenavAccordion">
							<nav class="sb-sidenav-menu-nested nav">
								<a class="nav-link" href=""><font size="4"><b>派發優惠券</b></font></a>
							</nav>
						</div>
						

						
						<div class="sb-sidenav-menu-heading"></div>
					</div>
				</div>
				
				<div class="sb-sidenav-footer">
					<div class="small"><font size="4"><b>目前用戶:</b></font></div>
					<div><font size="4"><b>${sessionScope.myUser.username}</b></font></div>
				</div>
			</nav>
		</div>
		<div id="layoutSidenav_content">



			<div class="input-group">
				<div class="form-outline">
					<input type="search" id="form1" class="form-control"
						placeholder="搜尋" />
				</div>
				<button type="button" class="btn btn-primary" onclick="queryItemByKeyword()">
					<i class="fas fa-search"></i>
				</button>
			</div>



			<main>
				<div class="main-content">
					<div class="row">
						<div class="col-md-12">
							<div class="table-title">
								<div class="row">
									<div
										class="col-sm-6 p-0 d-flex justify-content-lg-start justify-content-lg-start">
										<h2 class="ml-lg-2"><font size="5"><b>商品資料管理</b></font></h2>
									</div>
									<div
										class="col-sm-6 p- d-md-inline-block justify-content-lg-end justify-content-lg-end">

										<a href="<c:url value='/adminpages/item/CreateItemForm' />"
											class="btn btn-success" data-toggle="modal"> <i
											class="fa-sharp fa-light fa-square-plus" id="material-icons"></i>
											<font size="4"><b>新增商品</b></font>
										</a>

									</div>
								</div>
							</div>


							<!-- Modal -->
							<div class="modal fade" id="successModal" tabindex="-1"
								aria-labelledby="exampleModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="btn-close"
												data-bs-dismiss="modal" aria-label="Close"></button>
										</div>
										<div class="modal-body">
											<p>${message}</p>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary"
												data-bs-dismiss="modal">關閉</button>
										</div>
									</div>
								</div>
							</div>


							<table class="table table-striped table-hover">
								<thead>
									<tr>
										<th>商品編號</th>
										<th>商品名稱</th>
										<th>商品描述</th>
										<th>圖片</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>


									<c:forEach var='item' items='${itemList}'>
										<tr>
											<td>${item.itemId}</td>
											<td>${item.itemName}</td>
											<td>${item.itemDescription}</td>
											<td><img width='50' height='60' src='${item.dataUri}'></td>
											<td><a class="edit"
												href="<c:url value='/adminpages/item/findById/${item.id}' />">
													<i class="fa-sharp fa-light fa-pen" id="material-icons"></i>
											</a> <a class="delete"
												onclick="return deleteItem('${item.id}', '${item.itemId}');">
													<i class="fa-sharp fa-light fa-trash" id="material-icons"></i>
											</a></td>
										</tr>
									</c:forEach>

								</tbody>
							</table>


							<!-- 分頁控制 -->
							<div class="clearfix">
								<div class="hint-text">
									<!-- 顯示當前頁數範圍與總數量 -->
									<font size="4">顯示第 <b id="start">${startIndex + 1}</b> 至 <b id="end">${endIndex + 1}</b>
									筆，共有 <b id="total">${totalRecords}</b> 筆</font>
								</div>
								<ul class="pagination">
									<!-- 上一頁按鈕 -->
									<li class="page-item ${currentPage == 1 ? 'disabled' : ''}"
										id="previousPage"><c:if test="${currentPage != 1}">
											<a href="?page=${currentPage - 1}" class="page-link"><font size="4"><b>上一頁</b></font></a>
										</c:if></li>

									<!-- 頁碼按鈕 -->
									<c:forEach var="page" begin="1" end="${totalPages}"
										varStatus="status">

										<li class="page-item ${currentPage == page ? 'active' : ''}">
											<c:if test="${currentPage != page}">
												<a href="?page=${page}" class="page-link"><font size="4"><b>${page}</b></font></a>
											</c:if>
										</li>
										
									</c:forEach>

									<!-- 下一頁按鈕 -->
									<li
										class="page-item ${currentPage == totalPages ? 'disabled' : ''}"
										id="nextPage"><c:if test="${currentPage != totalPages}">
											<a href="?page=${currentPage + 1}" class="page-link"><font size="4"><b>下一頁</b></font></a>
										</c:if></li>
								</ul>
							</div>


						</div>
					</div>
				</div>
			</main>
			<form action="#" method='POST'></form>

			<footer class="py-4 bg-light mt-auto">
				<div class="container-fluid px-4">
					<div
						class="d-flex align-items-center justify-content-between small">
						<div class="text-muted">Copyright &copy; 5team 2023</div>

					</div>
				</div>
			</footer>
		</div>
	</div>
</body>

</html>