<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<title>後臺 - 5Steam</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
<link rel='stylesheet' href="<c:url value='/css/custom2.css' />"
	type="text/css" />
<link rel='stylesheet' href="<c:url value='/css/styles.css' />"
	type="text/css" />
<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
	crossorigin="anonymous"></script>
<script src="<c:url value='/js/scripts.js' />"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	let inputFileToLoad = null;
	let image = null;
	let img = null;
	window.addEventListener('load', function() {
		inputFileToLoad = document.getElementById("inputFileToLoad");
		image = document.getElementById("image");
		img = document.getElementById("img");
		inputFileToLoad.addEventListener('change', loadImageFileAsURL);
	});
	function loadImageFileAsURL() {
		let filesSelected = inputFileToLoad.files;
		if (filesSelected.length > 0) {
			let fileReader = new FileReader();

			let fileToLoad = filesSelected[0];

			fileReader.onload = function(fileLoadedEvent) {
				image.value = fileLoadedEvent.target.result;
				console.log("image.value=" + image.value);
				img.src = fileLoadedEvent.target.result;
			};
			fileReader.readAsDataURL(fileToLoad);
		}
	}
	
	$(document).ready(function() {
	    // 檢查是否有錯誤訊息
	    var hasError = false;
	    $('#filenameError').hide();
	    $('#itemIdError').hide();

	    
	    if ($('#filenameError').text().trim() !== '') {
	        $('#filenameError').show();
	        hasError = true;
	    }
	    if ($('#itemIdError').text().trim() !== '') {
	        $('#itemIdError').show();
	        hasError = true;
	    }
	  
	    // 至少一個錯誤訊息
	    if (hasError) {
	        $('#successModal').modal('show'); // 顯示模態視窗
	    }
	});
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

			<main>
				<c:url var='insertUrl' value='/adminpages/item/insertItem' />
				<form:form method='POST' modelAttribute="item"
					action="${insertUrl}">
					<div class="main-content">
						<div class="row">
							<div class="col-md-12">
								<div class="table-title">
									<div class="row">
										<div
											class="col-sm-6 p-0 d-flex justify-content-lg-start justify-content-lg-start">
											<h2 class="ml-lg-2">新增商品資料</h2>
										</div>

									</div>
								</div>
								
								
								
								<!-- Modal -->
								<div class="modal fade" id="successModal" tabindex="-1"
									aria-labelledby="exampleModalLabel" aria-hidden="true">
									<div class="modal-dialog">
										<button type="button" class="btn btn-danger" id="filenameError">
											<font size="8"><b><form:errors cssClass="error" path='filename' /></b></font>
										</button>
										<button type="button" class="btn btn-danger" id="itemIdError">
											<font size="8"><b><form:errors cssClass="error" path='itemId' /></b></font>
										</button>
									</div>
								</div>
								

								
								<table class="table">
									<tbody>
										<tr>
											<td colspan="2" class="table-active">商品編號</td>
											<th scope="row"><form:input type="text"
													placeholder="請輸入商品編號" class="form-control"
													path='itemId' /></th>
											<td colspan="2" class="table-active">商品名稱</td>
											<th scope="row"><form:input type="text"
													placeholder="請輸入商品名稱" class="form-control"
													path='itemName' /></th>
										</tr>
										<tr>
										    <td colspan="2" class="table-active">商品類別</td>
                                            <th scope="row">
                                            <form:select class="form-control" path="itemCategoryId">
                                            <form:options items="${ItemCategory}" itemValue="id" itemLabel="categoryName"/>
                                            </form:select>
                                            </th>
											<td colspan="2" class="table-active">商品折扣</td>
											<th scope="row"><form:input type="text"
													placeholder="請輸入商品折扣" class="form-control" path='discount' /></th>
										</tr>
										<tr>
											<td colspan="2" class="table-active">商品價格</td>
											<th scope="row"><form:input type="text"
													placeholder="請輸入商品價格" class="form-control" path='itemPrice' /></th>
											<td colspan="2" class="table-active">商品庫存</td>
											<th scope="row"><form:input type="text"
													placeholder="請輸入商品庫存" class="form-control" path='itemStock' /></th>
										</tr>
										<tr>
											<td colspan="2" class="table-active">商品描述</td>
											<th colspan="4" scope="row"><form:input type="text"
													placeholder="請輸入商品描述" class="form-control" path='itemDescription' /></th>
										</tr>
										<tr>
											<td colspan="2" class="table-active">商品影片</td>
											<th scope="row"><form:input type="text"
													placeholder="請輸入商品影片" class="form-control" path='mp4' /></th>
										</tr>
										<tr>
											<td colspan="2" class="table-active">商品圖片</td>
											<th scope="row"><form:input type="file"
													id='inputFileToLoad' path='filename'
													value='${item.filename}' placeholder="請挑選圖片"
													class="form-control" /></th>
											<th scope="row">
												<div class='col-sm-2'>
													<c:choose>
														<c:when test='${empty image}'>
															<img id='img' width='60' height='80'>
															<input type='hidden' id='image' name='image'>
														</c:when>
														<c:otherwise>
															<img id='img' width='60' height='80' src='${image}'>
															<input type='hidden' name='image' value='${image}'>
														</c:otherwise>
													</c:choose>
												</div>
											</th>
										</tr>
									</tbody>
								</table>
								
								
								

								
								
								
							</div>

							<c:if test="${not empty exception}">${exception}</c:if>

							<div class="row" style="text-align: center;">
								<div>
									<button type="submit" class="btn btn-primary"
										style="width: 100px;">確認送出</button>
								</div>
							</div>
						</div>
					</div>
				</form:form>
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