<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>


<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container">
<!--   <h3>Header</h3> -->
    <div class="navbar-header">
      <a class="navbar-brand" href="/"><img class="img-thumbnail" width="80" src="/resources/image/maxresdefault.jpg" /></a>
    </div>
    <ul class="nav navbar-nav">
<!--       	<li><a>Home</a></li> -->
<!--       	<li><a>Page 1</a></li> -->
<!--       	<li><a>Page 2</a></li> -->
    </ul>
    <ul class="nav navbar-nav navbar-right">
<%--     <security:authentication property="principal.password"/> --%>
    	<security:authorize access="isAuthenticated()">
    	<li><a>Hello, ${authMyUser.login}!</a></li>
			<li>
				<form:form action="/logout" method="post"
					class="navbar-form navbar-right">
					<button type="submit" class="btn btn-default" >Logout</button>
<!-- 			<tr> -->
<!-- 					<td><input type="submit" value="LogOut"></td> -->
<!-- 			</tr> -->
					
					
				</form:form>
			</li>
		</security:authorize>
		<security:authorize access="!isAuthenticated()">
			<li><a href="/registration">Register</a></li>
			<li><a class="btn btn-default" href="/login">Login</a></li>
		</security:authorize>
    </ul>
  </div>
  </nav>