<%-- 
    Document   : HoSoUser
    Created on : Oct 19, 2024, 12:47:05 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <style>
            @media (min-width: 1025px) {
                .h-custom {
                    height: 100vh !important;
                }
            }
        </style>
    </head>
    <body>
            <section class="h-100 h-custom" style="background-color: #8fc4b7;">
                <div class="container py-5 h-100">
                    <div class="row d-flex justify-content-center align-items-center h-100">
                        <div class="col-lg-8 col-xl-6">
                            <div class="card rounded-3">
                                <img src="./images/register.png"
                                     class="w-100" style="border-top-left-radius: .3rem; border-top-right-radius: .3rem;"
                                     alt="Sample photo">
                                <div class="card-body p-4 p-md-5">
                                    <h3 class="mb-4 pb-2 pb-md-0 mb-md-5 px-md-2">Finish Your Information</h3>
                                   
                                    <form class="px-md-2" action="gg" method="post">    
                                        <c:set var="c" value="${sessionScope.gg}"/>
                                        <input type="hidden" name="email" value="${c.email}">
                                           
                                        <div data-mdb-input-init class="form-outline mb-4">
                                            <input type="text" id="form3Example1q" class="form-control" name="fullname" value="${c.given_name}" required=""/>
                                            <label class="form-label" for="form3Example1q">Name</label>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-6 mb-4">

                                                <div data-mdb-input-init class="form-outline datepicker">
                                                    <input type="date" class="form-control" id="exampleDatepicker1" name="dob" required="" />
                                                    <label for="exampleDatepicker1" class="form-label">Birth of date</label>
                                                </div>
                                                <span style="color: red">${requestScope.err}</span>

                                            </div>
                                            <div class="col-md-6 mb-4">

                                                <select data-mdb-select-init name="gender" required>
                                                    <option disabled>Gender</option>
                                                    <option value="female">Female</option>
                                                    <option value="male">Male</option> 
                                                </select>

                                            </div>
                                        </div>


                                        <div class="col-md-6">

                                            <div data-mdb-input-init class="form-outline">
                                                <input type="tel" id="form3Example1w" class="form-control" placeholder="phone number" name="phone" value="${sessionScope.phone}"required/>
                                                <label class="form-label" for="form3Example1w">Phone</label>
                                            </div>

                                        </div>


                                        <div class="row mb-4 pb-2 pb-md-0 mb-md-5">
                                            <div class="col-md-6">

                                                <div data-mdb-input-init class="form-outline">
                                                    <input type="text" id="form3Example1w" class="form-control" placeholder="your home location" name="address" value="${sessionScope.address}" required/>
                                                    <label class="form-label" for="form3Example1w">Address</label>
                                                </div>

                                            </div>
                                        </div>

                                        <button type="submit" data-mdb-button-init data-mdb-ripple-init class="btn btn-success btn-lg mb-1">Submit</button>

                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        
    </body>
</html>
