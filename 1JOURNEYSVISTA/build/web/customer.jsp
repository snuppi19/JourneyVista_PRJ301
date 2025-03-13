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
                        <a href="admin.jsp">
                            <span class="icon">
                                <ion-icon name="home-outline"></ion-icon>
                            </span>
                            <span class="title">Dashboard</span>
                        </a>
                    </li>

                    <li>
                        <a href="">
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
                            <h2>Vista Customer</h2>
                            <a href="list" class="btn">Click here to VIEW</a>
                        </div>
                        <c:if test="${empty requestScope.user}">
                            <table id="customerTable">
                                <thead>
                                    <tr>
                                        <td>Name</td>
                                        <td>Birthdate</td>
                                        <td>Phone Number</td>
                                        <td>Gender</td>
                                        <td>Address</td>
                                        <td>is_active</td>
                                        <!--                                        <td>Status</td>-->
                                    </tr>
                                </thead>

                                <tbody>

                                    <c:forEach items="${sessionScope.listCustomer}" var="c">
                                        <tr class="customerRow">
                                            <td>${c.fullname}</td>
                                            <td>${c.dob}</td>
                                            <td>${c.phone_number}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${c.gender}">
                                                        Male
                                                    </c:when>
                                                    <c:otherwise>
                                                        Female
                                                    </c:otherwise>
                                                </c:choose>

                                            </td>
                                            <td>${c.address}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${c.is_active}">
                                                        <span class="status delivered">Active</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span class="status return">nonActive</span>
                                                    </c:otherwise>
                                                </c:choose>

                                            </td>

                                            <!--                                            <td><span class="status delivered">Delivered</span></td>-->
                                        </tr>

                                    </c:forEach> 
                                </tbody>
                            </table>
                        </c:if>
                        <c:if test="${not empty requestScope.user}">


                            <span>
                                Admin Kit <span style="color: green;margin-right: 30px;">UPDATE:</span> 
                                <button onclick="up('${user.id}')">
                                    <span style="color: green"><i class="bi bi-person-gear"style="font-size: 20px;margin-left: 25px;margin-right: 25px;"></i></span>
                                </button>
                            </span>
<!--                            <span>
                                Admin Kit <span style="color: red; margin-right: 30px;">DELETE :</span>
                                <button onclick="del('${user.id}')">
                                    <span style="color: red;"><i class="bi bi-person-x-fill"style="font-size: 20px;margin-left: 25px;margin-right: 25px;"></i></span>
                                </button>
                            </span>-->
                            <span>
                                Admin Kit <span style="color: #db4a39;margin-right: 40px;">BLOCK:</span>
                                <button onclick="block('${user.id}')">
                                    <span style="color: red;"><i class="bi bi-person-fill-slash"style="font-size: 20px;margin-left: 25px;margin-right: 25px;"></i></span>
                                </button>
                                <button onclick="block2('${user.id}')">
                                    <span style="color: green;"><i class="bi bi-person-fill"style="font-size: 20px;margin-left: 25px;margin-right: 25px;"></i></span>
                                </button>
                            </span>
                            <table>
                                <thead>
                                    <tr>
                                        <td>ID</td>
                                        <td>Name</td>
                                        <td>Birthdate</td>
                                        <td>Phone</td>
                                        <td>Gender</td>
                                        <td>Email</td>
                                        <td>Created_at</td>
                                        <td>updated_at</td>                
                                        <td>is_active</td>
                                    </tr>
                                </thead>

                                <tbody>


                                    <tr>
                                        <td>${user.id}</td>
                                        <td>${user.fullname}</td>
                                        <td>${user.dob}</td>
                                        <td>${user.phone_number}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${user.gender}">
                                                    Male
                                                </c:when>
                                                <c:otherwise>
                                                    Female
                                                </c:otherwise>
                                            </c:choose>

                                        </td>
                                        <td>${user.email}</td>
                                        <td>${user.created_at}</td>
                                        <td>${user.updated_at}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${user.is_active}">
                                                    <span class="status delivered">Active</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="status return">nonActive</span>
                                                </c:otherwise>
                                            </c:choose>

                                        </td>
                                    </tr>


                                </tbody>
                            </table>
                        </c:if>
                    </div>

                    <!-- ================= New Customers ================ -->
                    <div class="recentCustomers">
                        <div class="cardHeader">
                            <h2>Recent Customers</h2>
                        </div>

                        <table>
                            <c:forEach items="${sessionScope.listCustomer}" var="c">
                                <tr class="customerRow">

                                    <td>
                                        <form action="list" method="post" >
                                            <button type="submit" style="background:none; border:none; padding:0; text-align:left; color:inherit; font:inherit; cursor: pointer;">
                                                <h4 > ${c.fullname}<br> <span>${c.address}</span> </h4> 
                                                <input type="hidden" name="userID" value="${c.id}">
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


                    if (rowText.includes(searchText)) {
                        row.style.display = '';
                    } else {
                        row.style.display = 'none';
                    }
                });
            });
        </script>
        <script>
            function del(id) {
                let check = window.confirm("are u sure to DELETE? ");
                if (check) {
                    window.location = "delete?id=" + id;

                } else {
                    return;
                }
            }
            function up(id) {
                let check = window.confirm("are u sure to UPDATE? ");
                if (check) {
                    window.location = "update?id=" + id;

                } else {
                    return;
                }
            }
            function block(id) {
                let check = window.confirm("are u sure to NON ACTIVE this account?");
                if (check) {
                    window.location = "block?id=" + id;

                } else {
                    return;
                }
            }
            function block2(id) {
                let check = window.confirm("are u sure to ACTIVE this account?");
                if (check) {
                    window.location = "block2?id=" + id;

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
