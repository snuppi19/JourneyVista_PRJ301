<%-- 
    Document   : admin
    Created on : Oct 19, 2024, 3:08:22 PM
    Author     : Admin
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Responsive User Dashboard | Korsat X Parmaga</title>
        <!-- ======= Styles ====== -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link rel="stylesheet" href="./css/style.css">
    </head>

    <body>
        <!-- =============== Navigation ================ -->
        <div class="container">
            <div class="navigation">
                <ul>
                    <li>
                        <a href="#">
                            <span class="icon">
                                <ion-icon name="logo-apple"></ion-icon>
                            </span>
                            <span class="title">${sessionScope.dataUser.fullname}</span>
                        </a>
                    </li>

                    <li>
                        <a href="Userdashboard.jsp">
                            <span class="icon">
                                <ion-icon name="home-outline"></ion-icon>
                            </span>
                            <span class="title">Dashboard</span>
                        </a>
                    </li>


                    <li>
                        <a href="favourite?id=${sessionScope.dataUser.id}">
                            <span class="icon">
                                <ion-icon name="newspaper-outline"></ion-icon>
                            </span>
                            <span class="title">Favourite List</span>
                        </a>
                    </li>
                    <li>
                        <a href="help?id=${sessionScope.dataUser.id}"">
                            <span class="icon">
                                <ion-icon name="help-outline"></ion-icon>
                            </span>
                            <span class="title">Help</span>
                        </a>
                    </li>

                    <li>
                        <a href="userupdate?id=${sessionScope.dataUser.id}">
                            <span class="icon">
                                <ion-icon name="settings-outline"></ion-icon>
                            </span>
                            <span class="title">Settings</span>
                        </a>
                    </li>



                    <li>
                        <a href="maybay.jsp">
                            <span class="icon">
                                <ion-icon name="log-out-outline"></ion-icon>
                            </span>
                            <input type="hidden" name="dataUser" value="${sessionScope.dataUser}">
                            <span class="title">BACK</span>
                        </a>
                    </li>
                </ul>
            </div>

            <!-- ========================= Main ==================== -->
            <div class="main">

                <!-- ======================= Cards ================== -->


                <!-- ================ Order Details List ================= -->
                <div class="details">
                    <div class="recentOrders">
                        <div class="cardHeader">
                            <h2>Recent Questions</h2>
                            <a href="createhelp?id=${sessionScope.dataUser.id}" class="btn">Create new QUESTION</a>
                            <a href="helplist?id=${sessionScope.dataUser.id}" class="btn">RELOAD to SHOW my QUESTION       <i class="bi bi-arrow-repeat" style="margin-left: 10px"></i></a>

                        </div>
                        <c:if test="${empty requestScope.ord}">
                            <table id="customerTable">
                                <thead>
                                    <tr>
                                        <td>Name</td>
                                        <td>Messenger</td>
                                        <td>Admin Answer</td>

                                    </tr>
                                </thead>

                                <tbody>
                                    <c:forEach items="${sessionScope.cskhUlist}" var="c">
                                        <tr class="customerRow">
                                            <td>${dataUser.fullname}</td>
                                            <td>${c.description}</td>

                                            <td>
                                                <c:choose>
                                                    <c:when test="${not empty cskhAlist}">
                                                        <c:forEach items="${cskhAlist}" var="v">
                                                            <c:if test="${v.getU_id() == c.id}">
                                                                <c:choose>
                                                                    <c:when test="${not empty v.description}">
                                                                        ${v.description}
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <span class="status return">Đang Xử Lí</span>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:if>
                                                        </c:forEach>
                                                    </c:when>
                                                    <c:otherwise>
                                                        Danh sách trống.
                                                    </c:otherwise>
                                                </c:choose>

                                            </td>


                                        </tr>
                                    </c:forEach>





                                </tbody>
                            </table>
                        </c:if>
                        <c:if test="${not empty requestScope.ord}">


                            <span>
                                User Kit <span style="color: green;margin-right: 30px;">PayMent :</span> 
                                <button onclick="pay('${ord.id}')">
                                    <span style="color: green"><i class="bi bi-credit-card"style="font-size: 20px;margin-left: 25px;margin-right: 25px;"></i></span>
                                </button>
                            </span>
                            <span>
                                User Kit <span style="color: red; margin-right: 45px;">Cancel :</span>
                                <button onclick="cal('${ord.id}')">
                                    <span style="color: red;"><i class="bi bi-x-lg"style="font-size: 20px;margin-left: 25px;margin-right: 25px;"></i></span>
                                </button>
                            </span>

                            <table>
                                <thead>
                                    <tr>
                                        <td>Name</td>
                                        <td>Tickets</td>
                                        <td>Phone</td>

                                        <td>Start Date</td>
                                        <td>End Date</td>
                                        <td>Note</td>

                                        <td>Plane</td>

                                        <td>Total Money</td>                
                                        <td>Type</td>
                                    </tr>
                                </thead>

                                <tbody>


                                    <tr>
                                        <td>${ord.fullname}</td>
                                        <td>${orde.number_people}</td>
                                        <td>${ord.phone_number}</td>
                                        <td>${ord.order_date}</td>
                                        <td>${ord.order_end}</td>
                                        <td>

                                            ${orde.type}
                                        </td>

                                        <td>${vhc.name}</td>
                                        <td><fmt:formatNumber value="${ord.total_money * 1000}" type="number" groupingUsed="true" /> VND</td>

                                        <td> 
                                            <c:if test="${ord.order_isActive != true}">
                                                <span class="status delivered">Payed</span>
                                            </c:if>
                                            <c:if test="${ord.order_isActive == true}">
                                                <span class="status return">Process</span>
                                            </c:if>
                                        </td>

                                    </tr>


                                </tbody>
                            </table>

                        </c:if>
                    </div>

                    <!-- ================= New Customers ================ -->
                    <div class="recentCustomers">
                        <div class="cardHeader">

                            <div class="search">
                                <label>
                                    <input type="text" placeholder="Search EveryThing Here" id="searchBar">
                                    <ion-icon name="search-outline"></ion-icon>
                                </label>
                            </div>

                        </div>
                        <span style="
                              display: block;
                              font-family: 'Arial', sans-serif;
                              font-size: 16px;
                              color: #333;
                              line-height: 1.6;
                              background-color: #f9f9f9;
                              padding: 15px;
                              border-radius: 8px;
                              box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                              margin: 20px 0;
                              text-align: justify;
                              opacity: 0.9;
                              transition: opacity 0.3s ease;
                              ">
                            <strong style="font-size: 18px; color: #000; opacity: 1;">Liên hệ với chúng tôi để có trải nghiệm đặt vé tốt nhất</strong><br><br>
                            Đội ngũ chăm sóc khách hàng của chúng tôi sẵn sàng hỗ trợ bạn mọi lúc. 
                            Hãy liên hệ với chúng tôi nếu bạn cần tư vấn về chuyến bay, giá vé, hoặc bất kỳ thông tin nào khác!
                        </span>


                    </div>
                </div>
            </div>
        </div>

        <!-- =========== Scripts =========  -->

        <script>

            const searchBar = document.getElementById('searchBar');
            const customerRows = document.querySelectorAll('.customerRow');


            searchBar.addEventListener('keyup', function () {
                const searchText = searchBar.value.toLowerCase();


                customerRows.forEach(function (row) {
                    let rowText = '';
                    row.querySelectorAll('td').forEach(function (cell) {
                        rowText += cell.textContent.toLowerCase();
                    });


                    if (rowText.includes(searchText)) {
                        row.style.display = '';
                    } else {
                        row.style.display = 'none';
                    }
                });
            });
        </script>
        <!-- ====== ionicons ======= -->
        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
    </body>

</html>
