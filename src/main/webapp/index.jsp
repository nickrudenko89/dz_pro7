<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.js"></script>
    <title>Online shop</title>
    <script>
        function makeLoginForm (isRegisterForm) {
            var formHead = '', submitButton = '', registerHref = '', passwordTr = '';
            if(isRegisterForm) {
                formHead = '<form action="/authorize?method=register" method="post">';
                submitButton = '<td><input type="submit" value="Register" /></td>';
            }
            else {
                formHead = '<form action="/authorize?method=authorize" method="post">';
                submitButton = '<td><input type="submit" value="Login" /></td>';
                passwordTr = '<td>Password: </td><td><input type="password" name="password" /></td>';
                registerHref = '<tr><td><a href="index.jsp?do=register">Registration</a></td></tr>';
            }
            var $form = $(
                formHead +
                '<table>' +
                '<tr>' +
                '<td>Login: </td>' +
                '<td><input type="text" name="login" /></td>' +
                '</tr>' +
                '<tr>' +
                passwordTr +
                '<tr>' +
                submitButton +
                '</tr>' +
                registerHref +
                '</table>' +
                '</form>');
            $('#form').append($form);
        }
        window.onload = function () {
            urlParams = new URLSearchParams(window.location.search);
            if (urlParams.get('do') != null) {
                if (!urlParams.get('do').localeCompare('register')) {
                    makeLoginForm(true);
                }
                else if(!urlParams.get('do').localeCompare('auth')) {
                   makeLoginForm(false)
                }
            }
            else {
                makeLoginForm(false)
            }
            if (urlParams.get('error') != null) {
                var errorMsg;
                if (!urlParams.get('error').localeCompare('bad_data')) {
                    errorMsg = "Enter valid data.";
                }
                else if(!urlParams.get('error').localeCompare('login_fail')) {
                    errorMsg = "Incorrect login or password.";
                }
                else if(!urlParams.get('error').localeCompare('user_exists')) {
                    errorMsg = "User is already exists.";
                }
                $('#errorMsg').append(errorMsg);
            }
        }
    </script>
</head>
<body>
<div id="errorMsg" style="color: red"></div>
<div id="form"></div>
</body>
</html>
