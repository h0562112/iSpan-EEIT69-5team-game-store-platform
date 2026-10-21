<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>優惠券管理 - 5team</title>

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
									href=\"<c:url value='/adminpages/orders' />\"><font size="4"><b>訂單查詢</b></font></a> 
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
								<a class="nav-link" href="<c:url value='/adminpages/coupon' />"><font size="4"><b>新增優惠券</b></font></a>
							</nav>
						</div>
						
						<div class="collapse" id="collapseLayouts4"
							aria-labelledby="headingthree" data-bs-parent="#sidenavAccordion">
							<nav class="sb-sidenav-menu-nested nav">
								<a class="nav-link" href="<c:url value='/adminpages/coupon' />"><font size="4"><b>派發優惠券</b></font></a>
							</nav>
						</div>
						

						
						<a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
							data-bs-target="#collapseNews" aria-expanded="false"
							aria-controls="collapseNews">
							<div class="sb-nav-link-icon">
								<font size="4"><i class="fa-regular fa-newspaper"></i></font>
							</div> <font size="4"><b>最新消息</b></font>
							<div class="sb-sidenav-collapse-arrow">
								<i class="fas fa-angle-down"></i>
							</div>
						</a>
						<div class="collapse" id="collapseNews"
							aria-labelledby="headingthree" data-bs-parent="#sidenavAccordion">
							<nav class="sb-sidenav-menu-nested nav">
								<a class="nav-link" href="<c:url value='/adminpages/news' />"><font size="4"><b>消息管理</b></font></a>
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



						<main>
			<div class="container-fluid px-4">
				<h2 class="mt-4"><b>優惠券管理</b></h2>

				<c:if test="${not empty message}"><div class="alert alert-success mt-3"><c:out value="${message}"/></div></c:if>
				<c:if test="${not empty error}"><div class="alert alert-danger mt-3"><c:out value="${error}"/></div></c:if>

				<div class="card mb-4">
					<div class="card-header"><b>新增優惠券</b></div>
					<div class="card-body">
						<form method="post" action="<c:url value='/adminpages/coupon/create' />" class="row g-3">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
							<div class="col-md-3">
								<label class="form-label">優惠碼</label>
								<input type="text" name="code" class="form-control" maxlength="50" required placeholder="例如 WELCOME100">
							</div>
							<div class="col-md-3">
								<label class="form-label">說明</label>
								<input type="text" name="description" class="form-control" maxlength="200" placeholder="例如 新會員見面禮">
							</div>
							<div class="col-md-2">
								<label class="form-label">折扣類型</label>
								<select name="discountType" class="form-select">
									<option value="AMOUNT">折抵金額 ($)</option>
									<option value="PERCENT">折抵百分比 (%)</option>
								</select>
							</div>
							<div class="col-md-2">
								<label class="form-label">折抵數值</label>
								<input type="number" name="discountValue" class="form-control" min="1" required>
							</div>
							<div class="col-md-2">
								<label class="form-label">滿額門檻 ($)</label>
								<input type="number" name="minSpend" class="form-control" min="0" value="0">
							</div>
							<div class="col-md-3">
								<label class="form-label">到期日（可不填）</label>
								<input type="date" name="expireDate" class="form-control">
							</div>
							<div class="col-12">
								<button type="submit" class="btn btn-success">建立優惠券</button>
							</div>
						</form>
					</div>
				</div>

				<div class="card mb-4">
					<div class="card-header"><b>優惠券列表與派發</b></div>
					<div class="card-body">
						<table class="table table-striped table-hover align-middle">
							<thead>
								<tr>
									<th>優惠碼</th><th>內容</th><th>門檻</th><th>到期日</th><th>狀態</th><th>派發給</th><th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${empty coupons}"><tr><td colspan="7" class="text-center text-muted">還沒有優惠券</td></tr></c:if>
								<c:forEach var="cp" items="${coupons}">
									<tr>
										<td><b><c:out value="${cp.code}"/></b><br><small class="text-muted"><c:out value="${cp.description}"/></small></td>
										<td>${cp.discountText}</td>
										<td>${cp.minSpend > 0 ? cp.minSpend : '不限'}</td>
										<td><c:choose><c:when test="${not empty cp.expireTime}"><fmt:formatDate value="${cp.expireTime}" pattern="yyyy-MM-dd"/></c:when><c:otherwise>無</c:otherwise></c:choose></td>
										<td>
											<c:choose>
												<c:when test="${cp.expired}"><span class="badge bg-secondary">已過期</span></c:when>
												<c:when test="${cp.active}"><span class="badge bg-success">啟用中</span></c:when>
												<c:otherwise><span class="badge bg-secondary">已停用</span></c:otherwise>
											</c:choose>
										</td>
										<td>
											<form method="post" action="<c:url value='/adminpages/coupon/${cp.id}/issue' />" class="d-flex gap-2">
												<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
												<input type="text" name="target" class="form-control form-control-sm" placeholder="會員帳號，或輸入 all" required style="min-width:150px">
												<button type="submit" class="btn btn-sm btn-primary text-nowrap">派發</button>
											</form>
										</td>
										<td>
											<form method="post" action="<c:url value='/adminpages/coupon/${cp.id}/toggle' />">
												<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
												<button type="submit" class="btn btn-sm btn-outline-secondary text-nowrap">${cp.active ? '停用' : '啟用'}</button>
											</form>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			</main>

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