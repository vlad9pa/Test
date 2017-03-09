<html>
<head>
    <title>Phone Book</title>
</head>
<body>
<form method="GET" action="/logout">
    <input type="submit" value="Log Out">
</form>
<form method="post" id="contact" action="/user/phonebook">
    <table>
        <tr>
            <td class = "label">FirstName:</td>
            <td><input type = "text" name = "firstName" /></td>
        </tr>
        <tr>
            <td class = "label">LastName:</td>
            <td><input type = "text" name = "lastName" /></td>
        </tr>
        <tr>
            <td class = "label">SecondName:</td>
            <td><input type = "text" name = "secondName" /></td>
        </tr>
        <tr>
            <td class = "label">Password:</td>
            <td><input type = "text" name = "email" /></td>
        </tr>
        <tr>
            <td class="label">FullName:</td>
            <td><input type="text" name="mobileNumber"></td>
        </tr>
        <tr>
            <td class="label">FullName:</td>
            <td><input type="text" name="homePhoneNumber"></td>
        </tr>
        <tr>
            <td colspan = "2"><input type = "submit" value = "Create Account" /></td>
        </tr>
    </table>
</form>
</body>
</html>