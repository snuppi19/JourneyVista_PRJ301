<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login Form</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;700&display=swap" rel="stylesheet">
        <style>
            body {
                font-family: 'Roboto', sans-serif;
                margin: 0;
                padding: 0;
                display: flex;
                height: 100vh;
            }
            .login-container {
                background: linear-gradient(135deg, #2a2a2a 0%, #1a1a1a 100%);
                color: white;
                width: 50%;
                padding: 3rem;
                box-sizing: border-box;
                display: flex;
                flex-direction: column;
                justify-content: center;
            }
            .image-container {
                width: 50%;
                background-image: url('images/login.png');
                background-size: cover;
                background-position: center;
            }
            .logo {
                display: flex;
                align-items: center;
                margin-bottom: 2rem;
            }

            .logo-icon img {
                width: 250px;
                max-width: 100%;
            }
            h1 {
                font-size: 2.5rem;
                margin-bottom: 2rem;
                font-weight: 700;
            }
            input[type="email"],
            input[type="password"] {
                width: 100%;
                padding: 1rem;
                margin-bottom: 1.5rem;
                background-color: rgba(255,255,255,0.1);
                border: 1px solid rgba(255,255,255,0.2);
                color: white;
                border-radius: 4px;
                box-sizing: border-box;
                transition: all 0.3s ease;
            }
            input[type="email"]:focus,
            input[type="password"]:focus {
                outline: none;
                border-color: #5bc0de;
                box-shadow: 0 0 0 2px rgba(91,192,222,0.25);
            }
            .login-button {
                width: 100%;
                padding: 1rem;
                background-color: #5bc0de;
                color: white;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                font-size: 1rem;
                margin-bottom: 1.5rem;
                transition: background-color 0.3s ease;
            }
            .login-button:hover {
                background-color: #31b0d5;
            }
            .remember-forgot {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 1.5rem;
            }
            .remember-me {
                display: flex;
                align-items: center;
            }
            .remember-me input[type="checkbox"] {
                margin-right: 0.5rem;
            }
            .forgot-password a {
                color: #BABABA;
                text-decoration: none;
                transition: color 0.3s ease;
            }
            .forgot-password a:hover {
                color: #5bc0de;
            }
            .register {
                text-align: center;
                margin-top: 1.5rem;
            }
            .register a {
                color: #5bc0de;
                text-decoration: none;
                transition: color 0.3s ease;
            }
            .register a:hover {
                color: #31b0d5;
            }
            .social-login {
                text-align: center;
                margin-top: 2rem;
            }
            .social-login p {
                margin-bottom: 1rem;
            }
            .social-buttons {
                display: flex;
                justify-content: center;
                gap: 15px;
            }
            .social-button {
                border: none;
                border-radius: 50%;
                width: 45px;
                height: 45px;
                display: flex;
                align-items: center;
                justify-content: center;
                cursor: pointer;
                transition: all 0.3s ease;
            }
            .social-button:hover {
                transform: translateY(-20px);
                box-shadow: 0 4px 8px rgba(0,0,0,0.2);
            }
            .social-button i {
                font-size: 1.4rem;
                color: white;
            }
            .facebook {
                background-color: #3b5998;
            }
            .google {
                background-color: #db4a39;
            }
            .twitter {
                background-color: #00acee;
            }
            .github {
                background-color: #333;
            }

            @media (max-width: 768px) {
                body {
                    flex-direction: column;
                }
                .login-container,
                .image-container {
                    width: 100%;
                }
                .image-container {
                    height: 30vh;
                }
            }
            @keyframes fadeIn {
                from {
                    opacity: 0;
                }
                to {
                    opacity: 1;
                }
            }

            @keyframes slideIn {
                from {
                    transform: translateY(20px);
                    opacity: 0;
                }
                to {
                    transform: translateY(0);
                    opacity: 1;
                }
            }



            .login-container {
                animation: fadeIn 1s ease-out;
            }

            .logo, h1, form > *, .social-login, .register {
                opacity: 0;
                animation: slideIn 0.5s ease-out forwards;
            }

            .logo {
                animation-delay: 0.2s;
            }
            h1 {
                animation-delay: 0.4s;
            }
            form > *:nth-child(1) {
                animation-delay: 0.6s;
            }
            form > *:nth-child(2) {
                animation-delay: 0.8s;
            }
            form > *:nth-child(3) {
                animation-delay: 1s;
            }
            .remember-forgot {
                animation-delay: 1.2s;
            }
            .social-login {
                animation-delay: 1.4s;
            }
            .register {
                animation-delay: 1.6s;
            }



            .social-button {
                transition: transform 0.3s ease, box-shadow 0.3s ease;
            }

            .social-button:hover {
                animation: none;
                transform: translateY(-10px);
                box-shadow: 0 4px 8px rgba(0,0,0,0.2);
            }
        </style>
    </head>
    <body>

        <div class="login-container">
            <div class="logo">
                <div class="logo-icon">
                    <img src="./images/jouneyVista.png" alt="Journey Vista Logo"/>
                </div>
            </div>
            <h1>Let's Start Your Travel</h1>
            <form action="login" method="get">
                <c:set var=" cookie" value="${pageContext.request.cookies}"/>
                <input type="email" placeholder="Email address" name="email" value="${cookie.user.value}" required>
                <input type="password" placeholder="Password" name="password" value="${cookie.pass.value}" required>
                <button type="submit" class="login-button">LOGIN</button>
                <span style="color: red">${requestScope.error}</span>

                <div class="remember-forgot">
                    <div class="remember-me">
                        <input type="checkbox" name="cb" id="cb">
                        <label for="cb">Remember me</label>
                    </div>
                    <div class="forgot-password">                                                                                                                      
                        <a href="forgot.jsp">Forgot password?</a>
                    </div>
                </div>
            </form>
            <div class="social-login">
                <p>Or sign in with:</p>
                <div class="social-buttons">
                    <button type="button" class="social-button facebook">
                        <i class="bi bi-facebook"></i>
                    </button>
                    <button type="button" class="social-button google">
                        <a href="https://accounts.google.com/o/oauth2/auth?scope=email profile openid&redirect_uri=http://localhost:8080/1JOURNEYSVISTA/gg&response_type=code&client_id=48003357874-fmegbf1n4nrm7nhjej6nako9vuqclk9v.apps.googleusercontent.com&approval_prompt=force"><i class="bi bi-google"></i></a>
                    </button>
                    <button type="button" class="social-button twitter">
                        <i class="bi bi-twitter-x"></i>
                    </button>
                    <button type="button" class="social-button github">
                        <i class="bi bi-github"></i>
                    </button>
                </div>
            </div>
            <p class="register">Don't have an account? <a href="register.jsp">Register here</a></p>
            <button type="button" onclick="window.history.back();" class="back-button" style="border-radius: 10px; background-color: grey;color: white">Back</button>
        </div>
        <div class="image-container"></div>
    </body>
</html>