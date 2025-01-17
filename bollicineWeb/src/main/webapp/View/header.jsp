<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*"%>
<%@ page import="Model.DTO.AccountDTO" %>
<head>
	<title>head</title>
	<!-- per responsive -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0 ">
	<link href="./Style.css?ts=<?=time()?>&quot" rel="stylesheet" type="text/css">
	<link href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="./fontawesome-free-5.13.0-web/css/all.css">
</head>
<body onload="myFun()" onresize="myFun()">
	<div id="header">
		<img src="${pageContext.servletContext.contextPath}/imgs/bollicine%20logo.jpg">

		<div id="nav">
			<button id="bars" onclick="myOpen()"><i class="fa fa-bars fa-2x" ></i></button>
			<button id="closeMenu" onclick="myClose()"><i class="fas fa-times fa-2x"></i></button>
			<div id="contentNav">
			 
			<%

				//navbar per utente non registrato
				if(session.getAttribute("adminRoles")==null){
			%>
	  			<a class="navElement" href="${pageContext.servletContext.contextPath}/Prodotto">Catalogo</a>
	  			
	  			<div class="dropdownNav">
	  				<a class="navElement">Categorie <i class="fas fa-caret-down"></i></a>
	  				<div class="dropdown-contentNav">
						<a class="elementContentNav" href="${pageContext.servletContext.contextPath}/Categorie?cat=bi" >Bianchi</a>
						<a class="elementContentNav" href="${pageContext.servletContext.contextPath}/Categorie?cat=ro">Rossi</a>
						<a class="elementContentNav" href="${pageContext.servletContext.contextPath}/Categorie?cat=spu">Spumanti</a>
  					</div>
	  			</div>
	  			
	  			

	  			<a class="navElement" href="${pageContext.servletContext.contextPath}/View/Login_Logout/LoginView.jsp">Accedi</a>
	  			<a class="navElement" href="${pageContext.servletContext.contextPath}/View/Carrello/CarrelloView.jsp"><i class="fas fa-shopping-cart"></i></a>
	  			
  			<%
				}
  			%>
  				
  			<%

				//navbar per gestore Catalogo
  				if ((session.getAttribute("adminRoles")!=null)&&(session.getAttribute("adminRoles").equals("gestCat"))){
  			%>
  				<div class="dropdown">
  					<a>administrator</a>
  					<div class="dropdown-content">
						<a class="elementContent" href="${pageContext.servletContext.contextPath}/VisualizzaProdotti">Prodotti</a>
						<a class="elementContent" href="${pageContext.servletContext.contextPath}/Logout">Logout</a>
  					</div>
  				</div>
  			
	  		<%
  				}
  			%>
				<%

					//navbar per admin
					if ((session.getAttribute("adminRoles")!=null)&&(session.getAttribute("adminRoles").equals("admin"))){
				%>
				<div class="dropdown">
					<a>administrator</a>
					<div class="dropdown-content">
						<a class="elementContent" href="${pageContext.servletContext.contextPath}/Logout">Logout</a>
					</div>
				</div>

				<%
					}
				%>
				<%

					//navbar per gestore ordini
					if ((session.getAttribute("adminRoles")!=null)&&(session.getAttribute("adminRoles").equals("gestOrd"))){
				%>
				<div class="dropdown">
					<a>administrator</a>
					<div class="dropdown-content">
						<a class="elementContent" href="${pageContext.servletContext.contextPath}/VisualizzaOrdini">Ordini</a>
						<a class="elementContent" href="${pageContext.servletContext.contextPath}/Logout">Logout</a>
					</div>
				</div>

				<%
					}
				%>

  				
  			<%
  				//navbar per utente registrato
  				if(request.getSession().getAttribute("utente")!=null){
					AccountDTO account=(AccountDTO) request.getSession().getAttribute("utente");
  			%>

	  			<div class="dropdown">
	  				<a class="navElement"><%=account.getNome()%><i class="far fa-user"></i></a>
	  				<div class="dropdown-content">
						<a class="elementContent" href="${pageContext.servletContext.contextPath}/Logout">logout</a>
	  				</div>
	  			</div>
  			<%
  				}
  			%>
  			
  			
  			
  			</div>
		</div>
	</div>
	<script>
		function myOpen() {
				

			var contentNav=document.getElementById("contentNav");
				
			contentNav.style.display="inline-grid";
				
			document.getElementById("nav").style.textAlign="center";
				
			document.getElementById("bars").style.display="none";
				
			document.getElementById("closeMenu").style.display="block";

		}
		
		function myClose(){
			document.getElementById("contentNav").style.display="none";
			document.getElementById("closeMenu").style.display="none";
			document.getElementById("bars").style.display="initial";
		}
		
		
		
		function myFun(){
			
			var w = window.outerWidth;
			if(w>880){
				document.getElementById("bars").style.display="none";
				document.getElementById("closeMenu").style.display="none";
				document.getElementById("contentNav").style.display="block";
				document.getElementById("nav").style.textAlign="left";
			}else{
				
					document.getElementById("bars").style.display="initial";
					document.getElementById("contentNav").style.display="none";
					document.getElementById("nav").style.textAlign="center";
					document.getElementById("closeMenu").style.display="none";
			}
		}
	</script>
</body>
</html>