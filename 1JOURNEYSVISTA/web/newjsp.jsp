<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Khám Phá Việt Nam</title>
        <style>
            body {
                margin: 0;
                font-family: Arial, sans-serif;
                background-color: #f8f9fa;
            }

            .header {
                background-image: url('images/lokaimg.png');
                color: white;
                padding: 40px;
                text-align: start;
            }

            .header h1 {
                font-size: 36px;
                margin: 0;
            }

            .header p {
                font-size: 18px;
                width: 400px;
                margin-top: 10px;
            }

            .section {
                padding: 40px;
                text-align: center;
            }

            .section h2 {
                font-size: 28px;
                color: #333;
                margin-bottom: 20px;
            }

            .section p {
                font-size: 18px;
                color: #555;
                margin-bottom: 40px;
            }

            .insurance-list {
                display: flex;
                justify-content: space-around;
                flex-wrap: wrap;
            }

            .insurance-item {
                background-color: white;
                border: 1px solid #ddd;
                border-radius: 8px;
                width: 300px;
                margin: 20px;
                text-align: left;
                padding: 20px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                transition: transform 0.2s;
            }

            .insurance-item:hover {
                transform: translateY(-5px);
            }

            .insurance-item img {
                width: 100%;
                border-radius: 8px;
            }

            .insurance-item h3 {
                font-size: 22px;
                margin-top: 15px;
                color: #007bff;
            }

            .insurance-item p {
                font-size: 16px;
                color: #555;
            }

            .footer {
                background-color: #f1f1f1;
                padding: 40px;
                text-align: center;
            }

            .footer p {
                font-size: 16px;
                color: #333;
            }

            .top-nav {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 20px;
            }

            .top-nav img {
                height: 30px; /* Điều chỉnh kích thước logo nếu cần */
            }

            .user-actions {
                display: flex;
                align-items: center;
            }

            .btn {
                text-decoration: none;
                padding: 10px 20px;
                border-radius: 5px;
            }

            .btn-outline {
                border: 1px solid #007bff;
                color: #007bff;
                background-color: transparent;
                margin-left: 10px;
            }

            .btn-outline:hover {
                background-color: #007bff;
                color: white;
            }

            .btn-blue {
                background-color: #007bff;
                color: white;
                margin-left: 10px;
            }

            .btn-blue:hover {
                background-color: #0056b3;
            }

            .btn-back {
                background-color: #007bff; /* Màu nền cho nút quay lại */
                color: white; /* Màu chữ */
                border: none;
                padding: 10px 20px;
                border-radius: 5px;
                cursor: pointer;
                margin-right: auto; /* Đẩy nút về bên trái */
                font-size: 16px;
                transition: background-color 0.3s, transform 0.2s;
            }

            .btn-back:hover {
                background-color: #0056b3; /* Màu nền khi hover */
                transform: scale(1.05); /* Hiệu ứng phóng to */
            }

            .btn-logout {
                background-color: white; /* Màu nền của nút Đăng xuất */
                color: #007bff; /* Màu chữ */
                border: 1px solid #007bff; /* Đường viền */
                margin-left: 10px;
            }

            .btn-logout:hover {
                background-color: #007bff; /* Màu nền khi hover */
                color: white; /* Màu chữ khi hover */
            }
            body {
                margin: 0;
                font-family: Arial, sans-serif;
                background-color: #f8f9fa; /* Nền sáng */
                line-height: 1.6; /* Tăng khoảng cách giữa các dòng */
            }

            /* Thêm hiệu ứng chuyển động nhẹ */
            .section {
                transition: background-color 0.3s ease; /* Hiệu ứng khi di chuột */
            }

            .section:hover {
                background-color: #e9ecef; /* Nền xám nhạt khi di chuột */
            }

            /* Cải thiện khoảng cách cho các section */
            header, main, footer {
                max-width: 1200px; /* Giới hạn chiều rộng tối đa */
                margin: 0 auto; /* Căn giữa các phần tử */
                padding: 20px; /* Thêm khoảng cách cho các phần tử */
            }

            /* Điều chỉnh độ dày cho tiêu đề */
            h1, h2 {
                margin: 0;
                font-weight: bold; /* Đậm hơn */
            }

            h1 {
                font-size: 48px; /* Kích thước tiêu đề chính lớn hơn */
                color: #343a40; /* Màu sắc tối cho tiêu đề chính */
            }

            h2 {
                font-size: 32px; /* Kích thước tiêu đề phụ */
                color: #007bff; /* Màu sắc chủ đề */
            }

            /* Tăng cường độ tương phản cho văn bản */
            p {
                color: #495057; /* Màu chữ tối hơn để dễ đọc */
            }

            /* Thêm padding cho section */
            section {
                padding: 40px; /* Thêm khoảng cách cho mỗi section */
                background-color: #ffffff; /* Nền trắng cho mỗi section */
                border-radius: 8px; /* Bo tròn các góc */
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* Thêm bóng cho section */
                margin-bottom: 20px; /* Khoảng cách giữa các section */
            }

        </style>
    </head>
    <body>

        <div class="header">
            <div class="top-nav">
                <button class="btn-back" onclick="window.history.back();">Quay lại</button> <!-- Đưa nút về bên trái -->
                <div class="user-actions">
                    <c:choose>
                        <c:when test="${not empty sessionScope.dataUser}">
                            <span style="color: orange; margin-left: 10px">${sessionScope.dataUser.fullname}</span>
                            <a href="logout" class="btn btn-logout">Đăng xuất</a> <!-- Đổi màu nút Đăng xuất thành trắng -->
                        </c:when>
                        <c:otherwise>
                            <a href="Login.jsp" class="btn btn-outline">Đăng Nhập</a>
                            <a href="register.jsp" class="btn btn-blue">Đăng ký</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <h2 style="color: white">Khám Phá Việt Nam</h2>
            <p><i style="color: white">cho những chuyến đi không còn lo âu</i></p>

        </div>

        <!-- Section -->
        <header>
            <h1>Khám Phá Việt Nam</h1>
        </header>

        <main>
            <section id="da-nang">
                <h2 style="color: black">Đà Nẵng - Thiên Đường Biển</h2>
                <img src="images/hcmblog2.png" alt="Đà Nẵng" style="width: 100%;">
                <p>Đà Nẵng, một trong những thành phố biển đẹp nhất Việt Nam, nổi tiếng với những bãi biển tuyệt đẹp như Mỹ Khê và Non Nước. Ngoài việc thư giãn trên bãi cát trắng mịn, bạn còn có thể tham quan cầu Rồng, cầu tình yêu và Ngũ Hành Sơn.</p>
                <p>Không chỉ có cảnh đẹp, Đà Nẵng còn là nơi lý tưởng để thưởng thức ẩm thực phong phú. Hãy thử món mì Quảng, bún chả cá và hải sản tươi ngon ở đây.</p>
                <p>Với sự kết hợp giữa thiên nhiên tuyệt đẹp và văn hóa độc đáo, Đà Nẵng xứng đáng là một trong những điểm đến không thể bỏ lỡ khi bạn đến Việt Nam.</p>
            </section>

            <section id="ha-noi">
                <h2 style="color: black">Hà Nội - Thủ Đô Ngàn Năm Văn Hiến</h2>
                <img src="images/hanoicatoc.png" alt="Hà Nội" style="width: 100%;">
                <p>Hà Nội, thủ đô của Việt Nam, nổi tiếng với lịch sử lâu đời và văn hóa phong phú. Thành phố này là nơi hội tụ nhiều di sản văn hóa và kiến trúc độc đáo như Văn Miếu - Quốc Tử Giám, Hồ Hoàn Kiếm và cầu Long Biên. Hãy thưởng thức món phở nổi tiếng và khám phá các quán cà phê có không gian cổ kính ở đây.</p>
            </section>



            <section id="ho-chi-minh">
                <h2 style="color: black">Thành Phố Hồ Chí Minh - Trung Tâm Kinh Tế Sôi Động</h2>
                <img src="images/hcmblog.png" alt="TP. Hồ Chí Minh" style="width: 100%;">
                <p>TP. Hồ Chí Minh, nơi được mệnh danh là "thành phố không bao giờ ngủ", là trung tâm kinh tế lớn nhất Việt Nam. Với các điểm tham quan như Bến Nhà Rồng, chợ Bến Thành và Nhà thờ Đức Bà, thành phố này mang đến cho du khách những trải nghiệm thú vị và đa dạng.</p>
            </section>

            <section id="hoi-an">
                <h2 style="color: black">Hội An - Phố Cổ Di Sản Thế Giới</h2>
                <img src="images/danangchua.png" alt="Hội An" style="width: 100%;">
                <p>Hội An là một thành phố cổ xinh đẹp, nổi tiếng với kiến trúc đặc trưng và văn hóa phong phú. Đến đây, bạn có thể dạo bước trên các con phố cổ, thưởng thức các món ăn đặc sản như cao lầu và bánh mì Hội An. Phố cổ Hội An cũng rất đẹp vào ban đêm với những chiếc đèn lồng lung linh.</p>
            </section>
        </main>

        <footer>
            <p>&copy; 2024 Blog Du Lịch Việt Nam. Tất cả các quyền được bảo lưu.</p>
        </footer>

        <!-- Footer -->


    </body>
</html>
