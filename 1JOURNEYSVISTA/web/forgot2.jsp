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
                transform: translateY(-15px);
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
            <h1>Let's Begin Your Travel</h1>
            <form action="forgot" method="post"> 
                <input type="password" placeholder="New Password" name="password" required>    
                <input type="hidden"  name="email" value="${sessionScope.email}">    
                <button type="submit" class="login-button">Verify</button>
            </form> 
            <div class="remember-forgot">
                <div class="forgot-password">
                    <  <a href="forgot.jsp">Back </a>
                </div>
            </div>
       


    </div>
    <div class="image-container"></div>
</body>
</html>