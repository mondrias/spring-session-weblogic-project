<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
	function validateLogin() {

		var userName = document.getElementById("txtUserName");
		var password = document.getElementById("txtPassword");
		var loginErrorMessage = "UserName/Password is wrong or not provide.";
		var valid = true;
		if ((userName.value.trim().length <= 0 || password.value.trim().length <= 0)) {
			valid = false;
			document.getElementById('lblErrorMessage').innerHTML = loginErrorMessage;
			
		} else {
			document.getElementById('lblErrorMessage').innerHTML = "";
		}
		return valid;
	};
</script>



	<form name="loginPage" method="POST" action="<c:url value='/login' />"
		onsubmit="return validateLogin();">
		<div align="center" style="padding-top: 10%">
			<table border="1" style="height: 116px; width: 30%; cellpadding: 2;">
				<thead>
					<tr>
						<th colspan="2">Welcome to mPOS</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>User Name:</td>
						<td><input type="text" name="userName" id="txtUserName" value="Admin"
							 style="width: 200px;" autocomplete="off" />*
					    </td>
					</tr>
					<tr>
						<td>Password:</td>
						<td><input type="password" name="password" value="123456"
							id="txtPassword"  style="width: 200px;"  autocomplete="off" />*</td>
					</tr>
					
					<tr>
						<td align="center" colspan="2"><input type="submit"
							value="Login" name="btnLogin" style="width: 149px; height: 32px;" />
						</td>
					</tr>
					<tr>
						<td align="center" colspan="2"><font style="color: red">
								<label id="lblErrorMessage"><c:out value="${error}"></c:out></label>
						</font></td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
