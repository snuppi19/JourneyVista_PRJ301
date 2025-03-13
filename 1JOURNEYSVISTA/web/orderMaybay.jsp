<%-- 
    Document   : maybay
    Created on : Oct 5, 2024, 4:18:18 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Travel Booking Interface</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link rel="stylesheet" href="./css/styleindex.css">

    </head>
    <body>
        <div class="hehe">


            <div class="container">

                <header>
                    <img src="./images/jouneyVista.png" alt="logo" class="logo"/>
                    <div class="top-nav">
                        <img src="./images/vietnam.png" alt="vietnam logo"><span> VI | VND</span> 
                        <a href="#">🎉 Travel Fest</a>
                       
                        <c:choose>       
                            <c:when test="${not empty sessionScope.dataUser}">
                                <c:if test="${dataUser.id != 3}">
                                    <a href="user?id=${dataUser.id}">User Information</a>
                                </c:if>
                                <c:if test="${dataUser.id == 3}">
                                    <a href="admin.jsp">Admin Controller</a>
                                </c:if>
                            </c:when>
                            <c:otherwise>
                                <a href="">information</a>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>       
                            <c:when test="${not empty sessionScope.dataUser}">
                                <span style="color: orange; margin-left: 10px">${sessionScope.dataUser.fullname}</span>
                                <a href="logout" class="btn btn-outline">Đăng xuất</a>
                            </c:when>
                            <c:otherwise>
                                <a href="Login.jsp" class="btn btn-outline">Đăng Nhập</a>
                                <a href="register.jsp" class="btn btn-blue">Đăng ký</a>
                            </c:otherwise>
                        </c:choose>

                    </div>
                </header>



                <div class="booking-form">
                    <div class="tabs">
                        <div class="tab hover-effect">

                            <a href="maybay.jsp"> < Back</a>

                        </div>
                        <div class="tab active"><i class="bi bi-airplane" style="margin-right: 12px"></i> Vé máy bay</div>
                    </div>
                    <c:if test="${empty requestScope.plane}">
                        <form action="order" method="post">
                            <div>
                                <label><input type="radio" name="trip-type" checked> Select Your Plane : </label>
                            </div>

                            <div class="form-group">
                                <input type="hidden" name="user" value="${sessionScope.user}">
                                <input type="hidden" name="address_start" value="${sessionScope.address_start}">
                                <input type="hidden" name="address_end" value="${sessionScope.address_end}">
                                <input type="hidden" name="date_start" value="${sessionScope.date_start}">
                                <input type="hidden" name="date_end" value="${sessionScope.date_end}">
                                <input type="hidden" name="note" value="${sessionScope.note}">
                                <select id="fromSelect" name="plane" required>
                                    <option value="" disabled selected>Hãng Hàng Không </option>
                                    <option value="vietnamAir">VietNam Airline</option>
                                    <option value="Bamboo">BamBoo Airway</option>
                                    <option value="Vietjet">Viet Jet</option>
                                    <option value="Etihah">Etihah</option>
                                </select>


                            </div>
                            <span style="margin-bottom: 12px">Hành Khách:</span>

                            <div class="form-group">
                                <input type="number" name="nguoilon"  placeholder="số ghế"  value="${requestScope.songuoilon}"required>


                            </div>
                            <div style="margin-bottom: 12px; color: red">${requestScope.error}</div>
                            <button class="search-btn" type="submit">Tiếp Tục</button>
                        </form>
                    </c:if>
                    <c:if test="${not empty requestScope.plane}">
                        <form action="finishmaybay" method="post">

                            <label><span style="margin-bottom: 12px">Your Plane:</span> </label>
                            <label><span style="margin-bottom: 12px; margin-left: 140px"> From : </span> </label>
                            <label><span style="margin-bottom: 12px; margin-left: 175px"> To :  </span> </label>
                            <label><span style="margin-bottom: 12px; margin-left: 195px"> Start Date:  </span> </label>
                            <label><span style="margin-bottom: 12px; margin-left: 140px"> return Date:  </span> </label>



                            <div class="form-group"> 
                                <input type="text" name="plane" value="${requestScope.plane}" readonly>
                                <input type="text" name="address_start" value="${sessionScope.address_start}" readonly>
                                <input type="text" name="address_end" value="${sessionScope.address_end}" readonly>
                                <input type="text" name="date_start" value="${sessionScope.date_start}" readonly>
                                <input type="text" name="date_end" value="${sessionScope.date_end}" readonly>

                            </div>
                            <label><span style="margin-bottom: 12px">Name:</span> </label>
                            <label><span style="margin-bottom: 12px; margin-left: 180px"> Phone Number : </span> </label>
                            <label><span style="margin-bottom: 12px; margin-left: 105px"> Birth of Date :  </span> </label>
                            <label><span style="margin-bottom: 12px; margin-left: 125px"> Email :  </span> </label>
                            <label><span style="margin-bottom: 12px; margin-left: 150px"> Loại Ghế:  </span> </label>
                            <div class="form-group">
                                <input type="hidden" name="userID" value="${sessionScope.nguoidung.id}" readonly>
                                <input type="text" name="user" value="${sessionScope.nguoidung.fullname}" readonly>
                                <input type="text" name="phone" value="${sessionScope.nguoidung.phone_number}" readonly>
                                <input type="text" name="dob" value="${sessionScope.nguoidung.dob}" readonly>
                                <input type="text" name="email" value="${sessionScope.nguoidung.email}" readonly>
                                <input type="text" name="note" value="${sessionScope.note}" readonly>

                            </div>
                            <span style="margin-bottom: 12px">Hành Khách:</span>

                            <div class="form-group">
                                <input type="number" name="nguoilon"  placeholder="số ghế " value="${sessionScope.songuoilon}" readonly>


                            </div>
                            <div class="form-group">
                                <input type="hidden"name="total_money" value="${requestScope.total}">
                                <span  style="margin-bottom: 12px">Total Money: <span class="currency" style="color: red" >${requestScope.total*1000}</span> </span>
                                <span style="color: #1795ce"> <input type="checkbox" name="cb" style="margin-left: 700px;"> Add to your Favourite  list</span>
                            </div>


                            <button class="search-btn" type="submit">Đặt Vé</button>
                        </form>
                    </c:if>

                </div>
                <div class="trusted-by">
                    <p>Trusted by</p>
                    <img width="100px" src="./images/vietnamAir.png" alt="Partner 1">
                    <img width="100px" src="./images/muongthanh.png" alt="Partner 2">
                    <img width="30px" src="./images/messex.png" alt="Partner 3">
                    <img width="100px" src="./images/quatar.png" alt="Partner 4">
                    <img width="100px" src="./images/ehtihad.png" style="width: 60px;height: 20px" alt="Partner 5">
                </div>
            </div>
        </div>
        <div class="container my-5">
            <h2 class="text-start mb-4 para"><i class="bi bi-airplane-fill" style="color: blue; margin-right: 7px"></i>Các Hàng Không Phổ Biến</h2>
            <div class="row g-4 texth5">
                <div class="col-md-6 col-lg-3">
                    <div class="card maybaybody soft-box">
                        <img src="./images/vnaire.png" class="card-img-top" alt="Chuyến đi và Danh thắng">

                        <div class="card-body">
                            <span  style="color: grey" >Quốc Dân của Việt Nam</span>
                            <h5 class="card-title fs-6 text-start">VietNam AirLine</h5>
                            <h5 class="card-title fs-6 text-start" style="color: orange">2.331.000đ-7.331.000đ/ người  </h5>
                            <!--                            <h5 class="card-title fs-6 text-start" style="color: orange">1.331.000đ-5.331.000đ/ trẻ em </h5>
                                                        <h5 class="card-title fs-6 text-start" style="color: orange">900.000đ-3.000.000đ/ em bé </h5>-->

                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-lg-3">
                    <div class="card maybaybody soft-box">
                        <img src="./images/vietjet2.png" class="card-img-top" alt="Fun Activities" >
                        <div class="card-body">
                            <span  style="color: grey" > Hay Delay</span>
                            <h5 class="card-title fs-6 text-start">Viet Jet</h5>
                            <h5 class="card-title fs-6 text-start" style="color: orange">1.331.000đ - 3.331.000đ/nguời  </h5>
                            <!--                            <h5 class="card-title fs-6 text-start" style="color: orange">900.000đ-2.331.000đ/ trẻ em </h5>
                                                        <h5 class="card-title fs-6 text-start" style="color: orange">800.000đ-2.000.000đ/ em bé </h5>-->
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-lg-3">
                    <div class="card maybaybody soft-box">
                        <img src="./images/bamboo.png" class="card-img-top" alt="Travel Insurance">
                        <div class="card-body">
                            <span  style="color: grey" > Bền Vững, khó xảy ra sự cố</span>
                            <h5 class="card-title fs-6 text-start">BamBoo</h5>
                            <h5 class="card-title fs-6 text-start" style="color: orange">1.331.000đ - 5.331.000đ/ người </h5>
                            <!--                            <h5 class="card-title fs-6 text-start" style="color: orange">1.000.000đ-2.771.000đ/ trẻ em </h5>
                                                        <h5 class="card-title fs-6 text-start" style="color: orange">800.000đ-1.331.000đ/ em bé </h5>-->

                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-lg-3">
                    <div class="card maybaybody soft-box">
                        <img src="./images/etihah.png" class="card-img-top" alt="Đặt trước, trả sau" >
                        <div class="card-body">
                            <span  style="color: grey" > Hiện Đại,Tối Tân</span>
                            <h5 class="card-title fs-6 text-start">Ehtihah</h5>
                            <h5 class="card-title fs-6 text-start" style="color: orange">4.331.000đ - 8.331.000đ/ người </h5>
                            <!--                            <h5 class="card-title fs-6 text-start" style="color: orange">3.631.000đ-7.731.000đ/ trẻ em </h5>
                                                        <h5 class="card-title fs-6 text-start" style="color: orange">2.331.000đ-6.331.000đ/ em bé </h5>-->

                        </div>
                    </div>
                </div>
            </div>

        </div>
        <script>
            var formatter = new Intl.NumberFormat('vi-VN', {
                style: 'currency',
                currency: 'VND',
            });

            var currency = document.querySelectorAll('.currency');
            currency.forEach((element) => {

                element.innerHTML = formatter.format(element.innerHTML);
            });
        </script>
        <script src="./script/script.js"></script>   
    </body>
</html>