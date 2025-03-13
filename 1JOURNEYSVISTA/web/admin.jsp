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
        <title>Responsive Admin Dashboard | Korsat X Parmaga</title>
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
                        <a href="">
                            <span class="icon">
                                <ion-icon name="logo-apple"></ion-icon>
                            </span>
                            <span class="title">Admin Controller</span>
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
                        <a href="customer.jsp">
                            <span class="icon">
                                <ion-icon name="people-outline"></ion-icon>
                            </span>
                            <span class="title">Customers</span>
                        </a>
                    </li>


                    <li>
                        <a href="AdminHelp.jsp">
                            <span class="icon">
                                <ion-icon name="help-outline"></ion-icon>
                            </span>
                            <span class="title">Help</span>
                        </a>
                    </li>



                    <li>
                        <a href="pass.jsp">
                            <span class="icon">
                                <ion-icon name="lock-closed-outline"></ion-icon>
                            </span>
                            <span class="title">Password</span>
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
                <div class="topbar">
                    <div class="toggle">
                        <ion-icon name="menu-outline"></ion-icon>
                    </div>

                    <div class="search">
                        <label>
                            <input type="text" placeholder="Search EveryThing Here" id="searchBar">
                            <ion-icon name="search-outline"></ion-icon>
                        </label>
                    </div>


                </div>

                <!-- ======================= Cards ================== -->
                <div class="cardBox">
                    <div class="card">
                        <div>
                            <div class="numbers">${sessionScope.ticket}</div>
                            <div class="cardName">Tickets</div>
                        </div>

                        <div class="iconBx">
                            <ion-icon name="ticket-outline"></ion-icon>
                        </div>
                    </div>

                    <div class="card">
                        <div>
                            <div class="numbers">${sessionScope.ordCount}</div>
                            <div class="cardName">Trip</div>
                        </div>

                        <div class="iconBx">
                            <ion-icon name="cart-outline"></ion-icon>
                        </div>
                    </div>

                    <div class="card">
                        <div>
                            <div class="numbers">${sessionScope.process}</div>
                            <div class="cardName">In Process</div>
                        </div>

                        <div class="iconBx">
                            <ion-icon name="chatbubbles-outline"></ion-icon>
                        </div>
                    </div>

                    <div class="card">
                        <div>
                            <div class="numbers"><fmt:formatNumber value="${sessionScope.earning* 1000}" type="number" groupingUsed="true" />Đ</div>
                            <div class="cardName">Earning</div>
                        </div>

                        <div class="iconBx">
                            <ion-icon name="cash-outline"></ion-icon>
                        </div>
                    </div>
                </div>

                <!-- ================ Order Details List ================= -->
                <div class="details">
                    <div class="recentOrders">
                        <div class="cardHeader">
                            <h2>Vista Customer Trips</h2>
                            <a href="orderlist" class="btn">Click here to VIEW</a>
                        </div>
                        <c:if test="${empty requestScope.slORD}">
                            <table id="customerTable">
                                <thead>
                                    <tr >
                                        <td>Name</td>
                                        <td>Phone</td>
                                        <td>Start</td>
                                        <td>End</td>
                                        <td>Plane</td>
                                        <td>Total Money</td>
                                        <td>Type</td>
                                    </tr>
                                </thead>

                                <tbody>
                                    <c:forEach items="${sessionScope.oList}" var="c">
                                        <tr class="customerRow">
                                            <td>${c.fullname}</td>
                                            <td>${c.phone_number}</td>
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
                        <c:if test="${not empty requestScope.slORD}">

                            <span>
                                Admin Kit <span style="color: red; margin-right: 45px;">Cancel :</span>
                                <button onclick="cal('${slORD.id}')">
                                    <span style="color: red;"><i class="bi bi-x-lg"style="font-size: 20px;margin-left: 25px;margin-right: 25px;"></i></span>
                                </button>
                            </span>

                            <table>
                                <thead>
                                    <tr>
                                        <td>Name</td>
                                        <td>Start</td>
                                        <td>End</td>
                                        <td>Start Date</td>
                                        <td>End Date</td>
                                        <td>Note</td>
                                        <td>Tickets</td>
                                        <td>Total Money</td>                
                                        <td>Type</td>
                                    </tr>
                                </thead>

                                <tbody>


                                    <tr>
                                        <td>${slORD.fullname}</td>
                                        <td>${slORD.address_start}</td>
                                        <td>${slORD.address_end}</td>
                                        <td>${slORD.order_date}</td>
                                        <td>${slORD.order_end}</td>
                                        <td>
                                            ${orde.type}
                                        </td>
                                        <td>
                                            ${orde.number_people}
                                        </td>

                                        <td><fmt:formatNumber value="${slORD.total_money * 1000}" type="number" groupingUsed="true" /> VND</td>

                                        <td> 
                                            <c:if test="${slORD.order_isActive != true}">
                                                <span class="status delivered">Paid</span>
                                            </c:if>
                                            <c:if test="${slORD.order_isActive == true}">
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
                            <h2>Recent Customers Trips</h2>
                        </div>

                        <table>
                            <c:forEach items="${sessionScope.oList}" var="c">
                                <tr class="customerRow">

                                    <td>
                                        <form action="orderlist" method="post" >
                                            <button type="submit" style="background:none; border:none; padding:0; text-align:left; color:inherit; font:inherit; cursor: pointer;">
                                                <h4 > ${c.fullname}<br> <span>${c.address_start}- ${c.address_end}</span> </h4> 
                                                <input type="hidden" name="ordID" value="${c.id}">
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
            
            const searchBar = document.getElementById('searchBar');
            const customerRows = document.querySelectorAll('.customerRow');

            
            searchBar.addEventListener('keyup', function () {
                const searchText = searchBar.value.toLowerCase();  

                
                customerRows.forEach(function (row) {
                    let rowText = '';  
                    row.querySelectorAll('td').forEach(function (cell) {
                        rowText += cell.textContent.toLowerCase();  
                    });

                    
                    if (rowText.includes(searchText)) {//thay bang ===
                        row.style.display = '';
                    } else {
                        row.style.display = 'none';
                    }
                });
            });
        </script>


        <script>
            function cal(id) {
                let check = window.confirm("are u sure to CANCEL this trip? ");
                if (check) {
                    window.location = "admincancel?id=" + id;

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
