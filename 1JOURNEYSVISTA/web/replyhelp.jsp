<%-- 
    Document   : createhelp
    Created on : Oct 24, 2024, 8:34:02 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            @import url("https://fonts.googleapis.com/css?family=Open+Sans:100,200,300,400,500,600,700,800,900");

            html, body, div, span, applet, object, iframe, h1, h2, h3, h4, h5, h6, p, blockquote, div
            pre, a, abbr, acronym, address, big, cite, code, del, dfn, em, font, img, ins, kbd, q,
            s, samp, small, strike, strong, sub, sup, tt, var, b, u, i, center, dl, dt, dd, ol, ul, li,
            figure, header, nav, section, article, aside, footer, figcaption, button {
                margin: 0;
                padding: 0;
                border: 0;
                outline: 0;
            }

            .html, body {
                font-family: 'Open Sans', sans-serif;
                background-color: #474a56;
            }

            section {
                margin-top: 50px;
                margin-bottom: 50px;
            }

            section.contact-us #contact {
                position: relative;
                display: block;
                width: 380px;
                height: auto;
                background-color: #fff;
                padding: 40px;
                margin-left: auto;
                margin-right: auto;
                border-radius: 15px;
                box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.05);
            }

            section.contact-us .section-heading {
                position: relative;
                display: block;
                margin: auto;
            }

            .section-heading h4 {
                line-height: 40px;
                font-size: 28px;
                font-weight: 900;
                color: #dc8cdb;
                text-align: center;
                text-transform: uppercase;
                margin-bottom: 40px;
            }

            input, textarea {
                width: 350px;
                position: relative;
                display: block;
                background-color: #f4f7fb;
                font-family: 'Open Sans', sans-serif;
                font-size: 12px;
                font-weight: 500;
                border: none;
                box-shadow: none;
                border-radius: 5px;
                outline-color: #9e9e9e;
            }

            input {
                height: 40px;
                padding: 0px 15px;
            }

            textarea {
                min-height: 140px;
                max-height: 180px;
                padding: 15px;
                resize: none;
            }

            .contact-us span {
                height: 20px;
                font-size: 12px;
                margin-bottom: 20px;
            }

            .valid_info_name, .valid_info_email, .valid_info_message{
                display: inline-block;
                font-size: 13px;
                margin: 5px 2px;
            }

            .valid {
                border: 2px solid green;
                outline-color: green;
            }

            .invalid {
                border: 2px solid red;
                outline-color: red;
            }

            .btn {
                display: inline-flex;
                width: 100%;
                justify-content: flex-end;
            }

            #form-submit {
                position: relative;
                display: inline-block;
                float: right;
                font-size: 12px;
                font-weight: bold;
                letter-spacing: 1px;
                text-transform: uppercase;
                color: #fff;
                background: rgb(219,138,222);
                background: linear-gradient(-145deg, rgba(219,138,222,1) 0%, rgba(246,191,159,1) 100%);
                padding: 12px 20px;
                border-radius: 5px;
                border: none;
                outline: none;
                cursor: pointer;
                transition: all .3s;
                transition: all .3s;
            }

            #form-submit:disabled {
                border: 1px solid #9e9e9e;
                background: transparent;
                color: #9e9e9e;
                transition: none;
                transform: none;
                cursor: default;
            }

            #form-submit:hover:disabled{
                border: 1px solid #9e9e9e;
                color: #9e9e9e;
                background: transparent;
                transition: none;
                transform: none;
                cursor: default;
            }

            button:active {
                transform: scale(0.95);
            }

            button:focus {
                outline: none;
            }

            button.ghost {
                background-color: transparent;
                border-color: #FFFFFF;
            }
            .back-button {
                background-color: #f0f0f0; /* Màu nền */
                border: none; /* Xóa viền */
                color: #333; /* Màu chữ */
                padding: 10px 20px; /* Khoảng cách trong nút */
                border-radius: 5px; /* Bo góc */
                cursor: pointer; /* Con trỏ khi hover */
                margin-right: 10px; /* Khoảng cách với nút gửi */
                font-size: 14px; /* Kích thước chữ */
                font-weight: 600; /* Đậm chữ */
                transition: background-color 0.3s ease; /* Hiệu ứng chuyển màu nền */
            }

            .back-button:hover {
                background-color: #e0e0e0; /* Màu nền khi hover */
            }

            .back-button:active {
                transform: scale(0.95); /* Hiệu ứng nhấn nút */
            }

            .back-button:focus {
                outline: none; /* Xóa viền khi focus */
            }
        </style>
    </head>
    <section class="contact-us" id="contact-section">
        <form id="contact" action="help" method="post">

            <div class="section-heading">
                <h4>Contact us</h4>
            </div>

            <div class="inputField">

                <input type="text" name="questionID" value="${requestScope.questionID}"  autocomplete="on" required>
                <span class="valid_info_name"></span>
            </div>

            <div class="inputField">
                <input type="text" name="mess" id="email" value="${requestScope.mess}" placeholder="Your email" required="" />
                <span class="valid_info_email"></span>
            </div>


            <div class="inputField">
                <textarea name="message" id="message" placeholder="Your message" required></textarea>
                <span class="valid_info_message"></span>
            </div>

            <div class="inputField btn">
                <button type="button" onclick="window.history.back();" class="back-button">Back</button>
                <button type="submit" id="form-submit" class="main-gradient-button" >REPLY MESSAGE</button>
            </div>

        </form>
    </section>



    <script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
</html>
