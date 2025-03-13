<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Bảo hiểm Du lịch</title>
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
    <h1>Bảo hiểm Du lịch</h1>
    <p><i>cho những chuyến đi không còn lo âu</i></p>
    <p>Ước mơ đi du lịch không còn phải lo lắng nay đã thành sự thật. Với đối tác bảo hiểm của chúng tôi, bạn sẽ được bảo vệ khỏi mọi vấn đề có thể xảy ra trong chuyến đi, như hoãn chuyến, huỷ chuyến, thất lạc hành lý, hay tai nạn.</p>
</div>

<!-- Section -->
<div class="section">
    <h2><p>An tâm cho mọi chuyến đi của bạn với gói bảo hiểm từ Journey Vista</p></h2>
    <p>Tìm hiểu thêm về Bảo hiểm du lịch</p>

    <video width="640" height="360" controls>
        <source src="video/World of Peppa Pig - Trailer.mp4" type="video/mp4">
        Trình duyệt của bạn không hỗ trợ thẻ video. Vui lòng nâng cấp trình duyệt để xem video.
    </video>
    <div class="insurance-list">
        <!-- Insurance Item 1 -->
        <div class="insurance-item">
            <img src="images/baohiem.png" alt="Bảo hiểm Chuyến bay">
            <h3>Bảo hiểm Chuyến bay</h3>
            <p>Bảo vệ chuyến bay của bạn trước những sự cố không mong muốn như hoãn chuyến, hủy chuyến hay thất lạc hành lý.</p>
        </div>

        <!-- Insurance Item 2 -->
        <div class="insurance-item">
            <img src="images/baohiem.png" alt="Bảo hiểm Du lịch">
            <h3>Bảo hiểm Du lịch</h3>
            <p>An tâm du lịch với bảo hiểm toàn diện, bao gồm chăm sóc y tế, tai nạn và hỗ trợ khẩn cấp.</p>
        </div>

        <!-- Insurance Item 3 -->
        <div class="insurance-item">
            <img src="images/baohiem.png" alt="Bảo hiểm Sức khỏe">
            <h3>Bảo hiểm Sức khỏe</h3>
            <p>Bảo vệ sức khỏe của bạn trong suốt chuyến đi, với các gói bảo hiểm y tế phù hợp với mọi nhu cầu.</p>
        </div>
    </div>
</div>

<!-- Footer -->
<div class="footer">
    <p>© 2024 Journey Vista. Tất cả các quyền được bảo lưu.</p>
</div>

</body>
</html>
