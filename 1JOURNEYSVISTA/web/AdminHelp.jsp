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
                        <a href="customer.jsp">
                            <span class="icon">
                                <ion-icon name="people-outline"></ion-icon>
                            </span>
                            <span class="title">Customers</span>
                        </a>
                    </li>


                    <li>
                        <a href="">
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
                            <h2>Vista Customer Question</h2>
                            <a href="adminhelplist" class="btn">Click here to VIEW question</a>
                        </div>
                       
                            <table id="customerTable">
                                <thead>
                                    <tr >
                                        <td>QuestionID</td>
                                        <td>Name</td>
                                        <td>Messenger</td>
                                        <td>Answer</td>
                                    </tr>
                                </thead>

                                <tbody>
                                    <c:forEach items="${sessionScope.Alist}" var="c">
                                        <tr class="customerRow">
                                            <td>${c.getU_id()}</td>
                                            <td> 
                                                <c:forEach items="${Ulist}" var="v">
                                                    <c:if test="${c.getU_id() == v.id}">
                                                        <c:forEach items="${userList}" var="u">
                                                            <c:if test="${u.id == v.user_id}">
                                                                ${u.fullname}
                                                                
                                                            </c:if>
                                                        </c:forEach>
                                                    </c:if>
                                                </c:forEach>
                                            </td>

                                            <td>
                                                <c:forEach items="${Ulist}" var="v">
                                                    <c:if test="${c.getU_id() == v.id}">
                                                        ${v.description}
                                                    </c:if>
                                                </c:forEach>

                                            </td>

                                            <td>
                                                <c:choose>
                                                    <c:when test="${not empty c.description}">
                                                      ${c.description}
                                                    </c:when>
                                                    <c:otherwise>
                                                      <span class="status return">Chờ Xử Lí</span>
                                                    </c:otherwise>
                                                </c:choose>

                                            </td>


                                        </tr>
                                    </c:forEach>





                                </tbody>
                            </table>
  
                    </div>

                    <!-- ================= New Customers ================ -->
                    <div class="recentCustomers">
                        <div class="cardHeader">
                            <h2>Recent Questions</h2>
                        </div>

                        <table>
                            <c:forEach items="${sessionScope.Alist}" var="c">
                                <tr class="customerRow">

                                    <td>
                                        <form action="adminhelplist" method="post" >
                                            <button type="submit" style="background:none; border:none; padding:0; text-align:left; color:inherit; font:inherit; cursor: pointer;">
                                                <h4 >QuestionID<br> <span>${c.getU_id()}</span> </h4> 
                                                <input type="hidden" name="questionID" value="${c.getU_id()}">
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


       

        </script>
        <!-- ====== ionicons ======= -->
        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
    </body>

</html>
