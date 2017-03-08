<html>
<head>
    <title>Registration</title>
</head>
<body>
<h1>Registration</h1>
<form method="post" id="userForm" action="/user/registration">
    <table>
        <tr>
            <td class = "label">Login:</td>
            <td><input type = "text" name = "username" /></td>
        </tr>
        <tr>
            <td class = "label">Password:</td>
            <td><input type = "password" name = "password" /></td>
        </tr>
        <tr>
            <td class="label">FullName:</td>
            <td><input type="text" name="fullName"></td>
        </tr>
        <tr>
            <td colspan = "2"><input type = "submit" value = "Create Account" /></td>
        </tr>
    </table>
</form>
</body>
</html>