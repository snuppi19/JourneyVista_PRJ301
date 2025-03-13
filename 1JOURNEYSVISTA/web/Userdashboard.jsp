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
                        <a href="">
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
                        <a href="help?id=${sessionScope.dataUser.id}">
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
                            <h2>Recent Orders</h2>

                            <a href="userorder?id=${sessionScope.dataUser.id}" class="btn">ReLoad to SHOW my trip</a>
                        </div>
                        <c:if test="${empty requestScope.ord}">
                            <table>
                                <thead>
                                    <tr>
                                        <td>Name</td>
                                        <td>Email</td>
                                        <td>Start</td>
                                        <td>End</td>
                                        <td>Plane</td>
                                        <td>Total Money</td>
                                        <td>Type</td>
                                    </tr>
                                </thead>

                                <tbody>
                                    <c:forEach items="${sessionScope.oList}" var="c">
                                        <tr>
                                            <td>${c.fullname}</td>
                                            <td>${c.email}</td>
                                            <td>${c.address_start}</td>
                                            <td>${c.address_end}</td>
                                            <td>
                                                <c:forEach items="${vList}" var="v">
                                                    <c:if test="${v.id == c.vehicles_id}">
                                                        ${v.name}
                                                    </c:if>
                                                </c:forEach>

                                            </td>
                                            <td><fmt:formatNumber value="${c.total_money * 1000}" type="number" groupingUsed="true" /> VND</td>
                                            <td>
                                                <c:if test="${c.order_isActive != true}">
                                                    <span class="status delivered">Paid</span>
                                                </c:if>
                                                <c:if test="${c.order_isActive == true}">
                                                    <span class="status return">Process</span>
                                                </c:if>

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
                                                <span class="status delivered">Paid</span>
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
                            <h2>Recent Trip</h2>
                        </div>

                        <table>
                            <c:forEach items="${sessionScope.oList}" var="c">
                                <tr>

                                    <td>
                                        <form action="userorder" method="post" >
                                            <button type="submit" style="background:none; border:none; padding:0; text-align:left; color:inherit; font:inherit; cursor: pointer;">
                                                <h4 > ${c.fullname}<br> <span>${c.address_start}- ${c.address_end}</span> </h4> 
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
            function pay(id) {
                let check = window.confirm("are u sure to Pay for this trip ? ");
                if (check) {
                    window.location = "pay?id=" + id;

                } else {
                    return;
                }
            }
            function cal(id) {
                let check = window.confirm("are u sure to Cancel thi trip ? ");
                if (check) {
                    window.location = "cancel?id=" + id;

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
