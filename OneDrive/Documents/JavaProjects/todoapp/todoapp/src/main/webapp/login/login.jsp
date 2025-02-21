<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="../assets/css/styles.css">
<link rel="stylesheet" href="./login.css">
</head>
<body>
	<div class="login-block">
		<form action="login" method = "POST" class="login-form">
			<div class="form-group">
				<label for="username" id="username">Enter username</label>
				<input type="text" name="username" placeholder="Enter your username">
			</div>
			<div class="form-group">
				<label for="password" id="password">Enter password</label>
				<input type="password" name="password" placeholder="Enter your password">
			</div>
			<button type="submit">Log in</button>
		</form>
	</div>
</body>
</html>