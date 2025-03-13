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
                            <h2>Favourite Lists</h2>

<!--                      <a href="userorder?id=${sessionScope.dataUser.id}" class="btn">ReLoad to SHOW my trip</a>-->
                        </div>
                        <c:if test="${empty requestScope.ord}">
                            <table>
                                <thead>
                                    <tr>
                                        <td>Plane</td>
                                        <td>Start</td>
                                        <td>End</td>
                                        <td>Star Date</td>
                                        <td>End Date</td>
                                        <td>Note</td>

                                        <td>Type</td>
                                        <td>Total Money</td>
                                    </tr>
                                </thead>

                                <tbody>
                                    <c:forEach items="${sessionScope.fvList}" var="c">
                                        <tr>
                                            <td>
                                                <c:forEach items="${vList}" var="v">
                                                    <c:if test="${v.id == c.vehicles_id}">
                                                        ${v.name}
                                                    </c:if>
                                                </c:forEach>

                                            </td>
                                            <td>${c.address_start}</td>
                                            <td>${c.address_end}</td>
                                            <td>${c.order_date}</td>
                                            <td>${c.order_end}</td>
                                            <td>${c.note}</td>
                                            <td>
                                                <c:forEach items="${deList}" var="d">

                                                    <c:if test="${d.order_id == c.id}">
                                                        ${d.type}
                                                    </c:if>
                                                </c:forEach>
                                            </td>



                                            <td><fmt:formatNumber value="${c.total_money * 1000}" type="number" groupingUsed="true" /> VND</td>


                                        </tr>
                                    </c:forEach>





                                </tbody>
                            </table>
                        </c:if>
                        <c:if test="${not empty requestScope.ord}">  
                            <span>
                                User Kit <span style="color: red; margin-right: 45px;">Delete From FavouritList :</span>
                                <button onclick="cal('${ord.user_id}', '${ord.id}')">
                                    <span style="color: red;"><i class="bi bi-x-lg"style="font-size: 20px;margin-left: 25px;margin-right: 25px;"></i></span>
                                </button>
                            </span>

                            <table>
                                <thead>
                                    <tr>
                                        <td>Plane</td>
                                        <td>Start</td>
                                        <td>End</td>
                                        <td>Star Date</td>
                                        <td>End Date</td>
                                        <td>Note</td>

                                        <td>Type</td>
                                        <td>Total Money</td>
                                    </tr>
                                </thead>

                                <tbody>


                                    <tr>
                                        <td>${vhc.name}</td>
                                        <td>${ord.address_start}</td>
                                        <td>${ord.address_end}</td>
                                        <td>${ord.order_date}</td>
                                        <td>${ord.order_end}</td>
                                        <td>${ord.note}</td>
                                        <td>
                                            ${orde.type}
                                        </td>

                                        <td><fmt:formatNumber value="${ord.total_money * 1000}" type="number" groupingUsed="true" /> VND</td>



                                    </tr>


                                </tbody>
                            </table>

                        </c:if>
                    </div>

                    <!-- ================= New Customers ================ -->
                    <div class="recentCustomers">
                        <div class="cardHeader">
                            <h2>Recent Trip</h2>
                        </div>

                        <table>
                            <c:forEach items="${sessionScope.fvList}" var="c">
                                <tr>

                                    <td>
                                        <form action="favourite" method="post" >
                                            <button type="submit" style="background:none; border:none; padding:0; text-align:left; color:inherit; font:inherit; cursor: pointer;">
                                                <h4 > <fmt:formatNumber value="${c.total_money * 1000}" type="number" groupingUsed="true" /> VND<br> <span>${c.address_start}- ${c.address_end}</span> </h4> 
                                                <input type="hidden" name="detailID" value="${c.id}">
                                            </button>
                                        </form>
                                    </td>  
                                </tr>
                            </c:forEach> 
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <!-- =========== Scripts =========  -->

        <script>

            function cal(user_id, extraParam) {
                let check = window.confirm(" DELETE this trip from favouriteList ? ");
                if (check) {
                    window.location = "cancelck?id=" + user_id + "&extraParam=" + extraParam;

                } else {
                    return;
                }
            }

        </script>
        <!-- ====== ionicons ======= -->
        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
    </body>

</html>
