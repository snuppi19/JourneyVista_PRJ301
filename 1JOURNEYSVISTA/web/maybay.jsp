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
        <style>
            .flight-detail-row {
                display: flex;
                margin-bottom: 15px;
                border-bottom: 1px solid #eee;
                padding-bottom: 10px;
            }

            .detail-label {
                width: 130px;
                color: #666;
                font-weight: 500;
            }

            .detail-value {
                flex: 1;
                color: #333;
                font-weight: 600;
            }

            .flight-header {
                background: #f8f9fa;
                padding: 15px;
                border-radius: 8px;
                margin-bottom: 20px;
            }

            .flight-price {
                color: #ff6b6b;
                font-size: 24px;
                font-weight: bold;
                text-align: right;
                margin-top: 20px;
            }

            .airline-logo {
                width: 120px;
                margin-bottom: 15px;
            }

            .route-info {
                display: flex;
                align-items: center;
                justify-content: space-between;
                margin: 20px 0;
            }

            .city {
                text-align: center;
                flex: 1;
            }

            .city-name {
                font-size: 18px;
                font-weight: bold;
            }

            .flight-line {
                flex: 2;
                height: 2px;
                background: #ddd;
                position: relative;
                margin: 0 20px;
            }

            .flight-line::after {
                content: '✈️';
                position: absolute;
                top: -12px;
                left: 50%;
                transform: translateX(-50%);
            }

            .modal-content {
                border-radius: 12px;
            }

            .modal-header {
                background: #f8f9fa;
                border-radius: 12px 12px 0 0;
            }

            .book-button {
                background: #ff6b6b;
                border: none;
                padding: 12px 30px;
                border-radius: 8px;
                color: white;
                font-weight: 600;
            }

            .book-button:hover {
                background: #ff5252;
            }
        </style>

    </head>
    <body>
        <div class="hehe">


            <div class="container">

                <header>
                    <img src="./images/jouneyVista.png" alt="logo" class="logo"/>
                    <div class="top-nav">
                        <img src="./images/vietnam.png" alt="vietnam logo"><span> VI | VND</span> 
                        <a href="">🎉 Travel Fest</a>

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
                <nav class="main-nav">

                    <b>   <a href=""><i>Đưa bạn đến những chân trời mới chỉ với một vài bước đơn giản</i></a> </b>
                    <!--                    <b>   <a href=""><i style="color: tomato">Right here ▼</i></a></b>-->
                </nav>


                <div class="booking-form">
                    <div class="tabs">
                        <!--                        <div class="tab hover-effect"><a href="khachsan.jsp"><i class="bi bi-house-heart-fill" style="margin-right: 5px"></i> Khách sạn</a></div>-->
                        <div class="tab active"><i class="bi bi-airplane" style="margin-right: 12px"></i> Vé máy bay</div>
                        <!--                        <div class="tab hover-effect"><a href="xekhach.jsp"><i class="bi bi-bus-front" style="margin-right: 5px"></i>   Vé xe khách</a></div>-->
                    </div>
                    <form action="order" method="get">
                        <div>
                            <label><input type="radio" name="trip-type" checked> Một chiều / Khứ hồi</label>
                            <span style="color: red; margin-left: 30px">${requestScope.errP}</span>
                            <span style="color: red; margin-left: 30px">${requestScope.errU}</span>
                            <span style="color: red; margin-left: 30px">${requestScope.same}</span>
                            <span style="color: red; margin-left: 30px">${requestScope.errDate}</span>
                            <span style="color: red; margin-left: 30px">${requestScope.errDateStart}</span>
                        </div>
                        <input type="hidden" name="userid" value="${sessionScope.dataUser.id}">
                        <div class="form-group">
                            <select id="fromSelect" name="address_start" required>
                                <option value="" disabled selected>Từ</option>
                                <option value="Hanoi">Hà Nội</option>
                                <option value="Ho-Chi-Minh">Thành phố Hồ Chí Minh</option>
                                <option value="Da-Nang">Đà Nẵng</option>
                                <option value="Nha-Trang">Nha Trang</option>
                            </select>

                            <button class="swap" type="button" onclick="swapValues()"><i class="bi bi-arrow-left-right"></i></button> 

                            <select id="toSelect" name="address_end" required>
                                <option value="" disabled selected>Đến</option>
                                <option value="Hanoi">Hà Nội</option>
                                <option value="Ho-Chi-Minh">Thành phố Hồ Chí Minh</option>
                                <option value="Da-Nang">Đà Nẵng</option>
                                <option value="Nha-Trang">Nha Trang</option>
                            </select>
                        </div>
                        <span style="margin-bottom: 12px">Ngày Đi:</span>
                        <span style="margin-bottom: 12px;margin-left: 320px">Ngày Khứ Hồi:</span>
                        <span style="margin-bottom: 12px;margin-left: 270px">Hạng:</span>
                        <div class="form-group">
                            <input type="date" name="date_start" value="2024-10-28" required>
                            <input type="date" id="returnDate" name="date_return" value="2024-10-28" disabled  >                       
                            <select name="type">
                                <option value="phothong">Phổ Thông</option>
                                <option value="hangnhat">Hạng Nhất</option>
                                <option value="thuonggia">Thương Gia</option>

                            </select>
                        </div>

                        <div>
                            <label><input type="checkbox" id="roundTripCheckbox" onclick="ReturnDate()"> Khứ hồi</label>
                        </div>
                        <button class="search-btn" type="submit">Tiếp Tục</button>
                    </form>


                </div>
                <div class="trusted-by">
                    <p>Trusted by</p>
                    <img width="100px" src="./images/vietnamairline.png" alt="Partner 1">
                    <img width="100px" src="./images/muongthanh.png" alt="Partner 2">
                    <img width="30px" src="./images/messex.png" alt="Partner 3">
                    <img width="100px" src="./images/quatar.png" alt="Partner 4">
                    <img width="100px" src="./images/etihad.png" style="width: 60px;height: 20px" alt="Partner 5">
                </div>
            </div>
        </div>

        <div class="container my-5" >
            <h2 class="text-start mb-4 para"><i class="bi bi-airplane-fill" style="color: blue; margin-right: 7px"></i>Các Chuyến Bay Phổ Biến</h2>
            <div class="row g-4 texth5">
                <div class="col-md-6 col-lg-3 " onclick="showFlightModal('Vietnam Airline,Hanoi + Ho-Chi-Minh,7330 ,Số Ghế : 1 ,Thương Gia ,One-Way', 'userordercosan?id=${sessionScope.dataUser.id}')">
                    <div class="card maybaybody soft-box">
                        <img src="./images/hcm.png" class="card-img-top" alt="Chuyến đi và Danh thắng">

                        <div class="card-body">
                            <span  style="color: grey" > VietNam AirLine</span>
                            <h5 class="card-title fs-6 text-start">Hà Nội - TP HCM</h5>
                            <h5 class="card-title fs-6 text-start" style="color: orange">đ7.331.040 <del style="color: red">đ8.331.040</del></h5>
                            <span class="badge bg-orange position-absolute top-0 start-0 m-2" style="font-weight: normal;
                                  padding: 0.7em 1.2em;
                                  border-radius: 0.25rem;  background-color: black;">Thương Gia</span>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-lg-3" >
                    <div class="card maybaybody soft-box">
                        <img src="./images/danang.png" class="card-img-top" alt="Fun Activities">
                        <div class="card-body">
                            <span  style="color: grey" > VietNam AirLine</span>
                            <h5 class="card-title fs-6 text-start">Hà Nội - Đà Nẵng</h5>
                            <h5 class="card-title fs-6 text-start" style="color: orange"><del>đ1.331.040</del> <span style="font-weight: bold; color: red;">Sold</span></h5>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-lg-3">
                    <div class="card maybaybody soft-box">
                        <img src="./images/phuquoc.png" class="card-img-top" alt="Travel Insurance">
                        <div class="card-body">
                            <span  style="color: grey" > VietNam AirLine</span>
                            <h5 class="card-title fs-6 text-start">Hà Nội - Phú Quốc</h5>
                            <h5 class="card-title fs-6 text-start" style="color: orange"><del>đ1.331.040</del> <span style="font-weight: bold; color: red;">Sold</span></h5>
                            <span class="badge bg-orange position-absolute top-0 start-0 m-2" style="font-weight: normal;
                                  padding: 0.7em 1.2em;
                                  border-radius: 0.25rem;  background-color: black;">Thương Gia</span>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-lg-3">
                    <div class="card maybaybody soft-box">
                        <img src="./images/nhatrang.png" class="card-img-top" alt="Đặt trước, trả sau">
                        <div class="card-body">
                            <span  style="color: grey" > VietNam AirLine</span>
                            <h5 class="card-title fs-6 text-start">Hà Nội- Nha Trang</h5>
                            <h5 class="card-title fs-6 text-start" style="color: orange"><del>đ1.331.040</del> <span style="font-weight: bold; color: red;">Sold</span></h5>
                            <span class="badge bg-orange position-absolute top-0 start-0 m-2" style="font-weight: normal;
                                  padding: 0.7em 1.2em;
                                  border-radius: 0.25rem;  background-color: black;">Phổ Thông</span>
                        </div>
                    </div>
                </div>
            </div>


            <div class="row g-4 texth5" style="margin-top: 30px">
                <div class="col-md-6 col-lg-3" onclick="showFlightModal('Viet Jet,Ho-Chi-Minh + Hanoi,7330 ,Số Ghế : 1 ,Thuong Gia ,One-Way', 'userordercosan?id=${sessionScope.dataUser.id}')">
                    <div class="card maybaybody soft-box">
                        <img src="./images/hotay.png" class="card-img-top" alt="Chuyến đi và Danh thắng">

                        <div class="card-body">
                            <span  style="color: grey" > Viet Jet</span>
                            <h5 class="card-title fs-6 text-start">TP HCM- Hà Nội</h5>
                            <h5 class="card-title fs-6 text-start" style="color: orange">đ7.331.040 <del style="color: red">đ8.331.040</del></h5>
                            <span class="badge bg-orange position-absolute top-0 start-0 m-2" style="font-weight: normal;
                                  padding: 0.7em 1.2em;
                                  border-radius: 0.25rem;  background-color: black;">Thương Gia</span>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-lg-3" onclick="showFlightModal('Etihad,Da-Nang + Nha-Trang,2330 ,Số Ghế : 1 ,Pho Thong ,One-Way', 'userordercosan?id=${sessionScope.dataUser.id}')">
                    <div class="card maybaybody soft-box">
                        <img src="./images/biennhatrang.png" class="card-img-top" alt="Fun Activities">
                        <div class="card-body">
                            <span  style="color: grey" > Etihad</span>
                            <h5 class="card-title fs-6 text-start">Đà Nẵng - Nha Trang</h5>
                            <h5 class="card-title fs-6 text-start" style="color: orange">đ2.330.000 </h5>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-lg-3" onclick="showFlightModal('BamBoo Airway,Nha-Trang + Ho-Chi-Minh,4333,Số Ghế : 1  ,Thuong Gia ,One-Way', 'userordercosan?id=${sessionScope.dataUser.id}')">
                    <div class="card maybaybody soft-box">
                        <img src="./images/hcm.png" class="card-img-top" alt="Travel Insurance">
                        <div class="card-body">
                            <span  style="color: grey" > BamBoo Airway</span>
                            <h5 class="card-title fs-6 text-start">Nha Trang - TP HCM</h5>
                            <h5 class="card-title fs-6 text-start" style="color: orange">đ4.333.000<del style="color: red"> đ5.333.000</del> </h5>
                            <span class="badge bg-orange position-absolute top-0 start-0 m-2" style="font-weight: normal;
                                  padding: 0.7em 1.2em;
                                  border-radius: 0.25rem;  background-color: black;">Thương Gia</span>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-lg-3" >
                    <div class="card maybaybody soft-box">
                        <img src="./images/biennhatrang.png" class="card-img-top" alt="Đặt trước, trả sau">
                        <div class="card-body">
                            <span  style="color: grey" >  BamBoo Airway</span>
                            <h5 class="card-title fs-6 text-start">TP HCM- Nha Trang</h5>
                            <h5 class="card-title fs-6 text-start" style="color: orange"><del>đ1.331.040</del> <span style="font-weight: bold; color: red;">Sold</span></h5>
                            <span class="badge bg-orange position-absolute top-0 start-0 m-2" style="font-weight: normal;
                                  padding: 0.7em 1.2em;
                                  border-radius: 0.25rem;  background-color: black;">Phổ Thông</span>
                        </div>
                    </div>
                </div>
            </div>





            <div class="text-center mt-4">




                <button class="btn btn-primary book"  >Đặt vé máy bay có sẵn tại đây<i class="bi bi-arrow-up-circle"></i></button>
            </div>
        </div>

        <div class="container my-5">
            <h2 class="text-start mb-4 para">Nâng tầm chuyến đi theo cách bạn muốn</h2>
            <div class="row g-4 texth5">
                <div class="col-md-6 col-lg-3">
                    <div class="card h-100 option-card anhloii soft-box">
                        <img src="./images/cau2tay.png" class="card-img-top" alt="Chuyến đi và Danh thắng">
                        <div class="card-body">
                            <h5 class="card-title fs-6"><a href="newjsp.jsp" style="text-decoration: none ; color: black">Chuyến đi và Danh Thắng</a></h5>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-lg-3">
                    <div class="card h-100 option-card soft-box">
                        <img src="./images/tambien.png" class="card-img-top" alt="Fun Activities">
                        <div class="card-body">
                            <h5 class="card-title fs-6"><a href="newjsp.jsp" style="text-decoration: none ; color: black">Funny Activity</a></h5>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-lg-3">
                    <div class="card h-100 option-card soft-box">
                        <img src="./images/baohiem.png" class="card-img-top" alt="Travel Insurance">
                        <div class="card-body">
                            <h5 class="card-title fs-6"><a href="travelinsurance.jsp" style="text-decoration: none ; color: black">Travel Insurance</a></h5>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-lg-3">
                    <div class="card h-100 option-card anhloi soft-box">
                        <img src="./images/buynow.png" class="card-img-top" alt="Đặt trước, trả sau">
                        <div class="card-body">
                            <h5 class="card-title fs-6">Đặt trước, trả sau</h5>
                        </div>
                    </div>
                </div>
            </div>

            <div class="text-center mt-4">
                <button class="btn btn-primary book" onclick="scrollToTop()" >Lên Đầu Trang <i class="bi bi-arrow-up-circle"></i></button>
            </div>
        </div>



        <!-- Modal Bootstrap -->
        <div class="modal fade" id="flightModal" tabindex="-1" aria-labelledby="flightModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="flightModalLabel" style="color: black">Chi Tiết Chuyến Bay Có Sẵn</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="flight-header">
                            <img src="./images/vietnamAir.png" alt="Vietnam Airlines" class="airline-logo">
                            <div class="route-info">
                                <div class="city">
                                    <div class="city-name" id="departureCity" style="color: black">Hà Nội</div>

                                </div>
                                <div class="flight-line"></div>
                                <div class="city">
                                    <div class="city-name" id="arrivalCity" style="color: black">TP HCM</div>

                                </div>
                            </div>
                        </div>

                        <div class="flight-details">
                            <div class="flight-detail-row">
                                <div class="detail-label">Hãng bay:</div>
                                <div class="detail-value" id="airline"></div>
                            </div>
                            <div class="flight-detail-row">
                                <div class="detail-label">Hạng vé:</div>
                                <div class="detail-value" id="class"></div>
                            </div>
                            <div class="flight-detail-row">
                                <div class="detail-label">Loại:</div>
                                <div class="detail-value" id="flightNumber"></div>
                            </div>
                            <div class="flight-detail-row">
                                <div class="detail-label">Số Ghế :</div>
                                <div class="detail-value" id="flightNumber"> 1</div>
                            </div>

                            <div class="flight-price">
                                7.331.040 ₫
                                <div style="font-size: 14px; color: #666; text-decoration: line-through;">8.331.040 ₫</div>
                            </div>
                        </div>
                    </div>
                    <form id="flightForm" action="userordercosan" method="get" style="display:none;">
                        <input type="hidden" name="userID" value="${sessionScope.dataUser.id}">
                        <input type="hidden" name="departureCity" id="hiddenDepartureCity">
                        <input type="hidden" name="arrivalCity" id="hiddenArrivalCity">
                        <input type="hidden" name="price" id="hiddenPrice">
                        <input type="hidden" name="airline" id="hiddenAirline">
                        <input type="hidden" name="classType" id="hiddenClassType">
                        <input type="hidden" name="flightNumber" id="hiddenFlightNumber">
                    </form>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                        <button type="button" class="book-button" id="bookFlightButton">Đặt vé ngay</button>
                    </div>
                </div>
            </div>
        </div>




        <script>
            // Hàm hiển thị modal với thông tin chuyến bay
            function showFlightModal(details, bookingUrl) {
                // Parse thông tin từ chuỗi details
                const [airline, route, price, seats, classType, flightType] = details.split(',').map(item => item.trim());

                // Parse route để lấy điểm đi và đến
                const [departure, arrival] = route.split('+').map(city => city.trim());

                // Cập nhật thông tin trong modal
                document.getElementById('departureCity').textContent = departure;
                document.getElementById('arrivalCity').textContent = arrival;
                document.getElementById('airline').textContent = airline;
                document.getElementById('class').textContent = classType;
                document.getElementById('flightNumber').textContent = flightType;

                // Cập nhật giá vé

                const priceElement = document.querySelector('.flight-price');
                priceElement.innerHTML = 'Giá: ' + price * 1000 + ' /người';

                // Cập nhật logo hãng bay (có thể thêm logic để chọn logo dựa trên tên hãng)
                const logoSrc = './images/' + airline.toLowerCase().replace(/\s+/g, '') + '.png';
                document.querySelector('.airline-logo').src = logoSrc;

                document.getElementById('hiddenDepartureCity').value = departure;
                document.getElementById('hiddenArrivalCity').value = arrival;
                document.getElementById('hiddenAirline').value = airline;
                document.getElementById('hiddenPrice').value = price;
                document.getElementById('hiddenClassType').value = classType;
                document.getElementById('hiddenFlightNumber').value = flightType;

                // Cập nhật URL cho nút đặt vé
                const bookButton = document.getElementById('bookFlightButton');
                bookButton.onclick = function () {

                    document.getElementById('flightForm').submit();
                };

                // Hiển thị modal
                const flightModal = new bootstrap.Modal(document.getElementById('flightModal'));
                flightModal.show();
            }


        </script>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                const today = new Date();
                const formattedDate = today.toISOString().split("T")[0];
                document.querySelector("input[name='date_start']").value = formattedDate;
            });
        </script>
        <script>
            function scrollToTop() {
                window.scrollTo({
                    top: 0,
                    behavior: 'smooth'
                });
            }
        </script>
        <script src="./script/script.js"></script>   
        <!-- Thêm các script này vào trước thẻ đóng </body> -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>


</html>