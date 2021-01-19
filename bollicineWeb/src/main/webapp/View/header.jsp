<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*"%>
<head>
	<title>head</title>
	<!-- per responsive -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0 ">
	<link href="../Style.css?ts=<?=time()?>&quot" rel="stylesheet" type="text/css">
	<link href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="../fontawesome-free-5.13.0-web/css/all.css">
</head>
<body onload="myFun()" onresize="myFun()">
	<div id="header">
		<img src="../imgs/bollicine%20logo.png">
		
		<div id="nav">
			<button id="bars" onclick="myOpen()"><i class="fa fa-bars fa-2x" ></i></button>
			<button id="closeMenu" onclick="myClose()"><i class="fas fa-times fa-2x"></i></button>
			<div id="contentNav">
			 
			<%
				//navbar per utente non registrato
				if((Boolean) session.getAttribute("adminRoles")==null){
			%>
	  			<a class="navElement" href="CatalogoView.jsp">Catalogo</a>
	  			
	  			<div class="dropdownNav">
	  				<a class="navElement">Categorie <i class="fas fa-caret-down"></i></a>
	  				<div class="dropdown-contentNav">
						<a class="elementContentNav" href="./CategoriesView?cat=br" >Bianchi</a>
						<a class="elementContentNav" href="./CategoriesView?cat=an">Rossi</a>
						<a class="elementContentNav" href="./CategoriesView?cat=col">Spumanti</a>
  					</div>
	  			</div>
	  			
	  			

	  			<a class="navElement" href="login-form.jsp">Accedi</a>
	  			<a class="navElement" href="product?action=viewC"><i class="fas fa-shopping-cart"></i></a>
	  			
  			<%
				}
  			%>
  				
  			<%
				//navbar per amministratore
  				if (((Boolean) session.getAttribute("adminRoles")!=null)&&((Boolean) session.getAttribute("adminRoles"))){
  			%>
  				<div class="dropdown">
  					<a>administrator</a>
  					<div class="dropdown-content">
						<a class="elementContent" href="./OrderAdmin">Ordini</a>
						<a class="elementContent" href="./productAdmin">Prodotti</a>
						<a class="elementContent" href="./Logout">Logout</a>
  					</div>
  				</div>
  			
	  		<%
  				}
  			%>
  				
  			<%
  				//navbar per utente registrato
  				if(request.getSession().getAttribute("datiUtente")!=null){
 					//Dati_anagraficiBean datiUtente=(Dati_anagraficiBean)request.getSession().getAttribute("datiUtente");
  			%>
  				<a class="navElement" href="ProductView.jsp">Catalogo</a>
	  			
	  			<div class="dropdownNav">
	  				<a class="navElement">Categorie <i class="fas fa-caret-down"></i></a>
	  				<div class="dropdown-contentNav">
						<a class="elementContentNav" href="./CategoriesView?cat=br" >Bracciali</a>
						<a class="elementContentNav" href="./CategoriesView?cat=an">Anelli</a>
						<a class="elementContentNav" href="./CategoriesView?cat=col">Collane</a>
						<a class="elementContentNav" href="./CategoriesView?cat=ore">Orecchini</a>
						<a class="elementContentNav" href="./CategoriesView?cat=orol">Orologi</a>
  					</div>
	  			</div>
	  			
	  			<a class="navElement" href="./CategoriesView?cat=dia">Diamanti </a>
	  			
	  			<div class="dropdownNav">
	  				<a class="navElement">Ricorrenze speciali <i class="fas fa-caret-down"></i></a>
	  				<div class="dropdown-contentNav">
						<a class="elementContentNav" href="./CategoriesView?tag=Matrimonio">Matrimonio</a>
						<a class="elementContentNav" href="./CategoriesView?tag=FPapa">Festa del papà</a>
						<a class="elementContentNav" href="./CategoriesView?tag=FMamma">Festa della mamma</a>
						<a class="elementContentNav" href="./CategoriesView?tag=Compleanno">Compleanni</a>
  					</div>
	  			
	  			</div>
	  			
	  			<a class="navElement" href="login-form.jsp">Accedi</a>
	  			<a class="navElement" href="product?action=viewC"><i class="fas fa-shopping-cart"></i></a>
	  			<div class="dropdown">
	  				<a class="navElement"><i class="far fa-user"></i></a>
	  				<div class="dropdown-content">
						<a class="elementContent" href="./Logout">logout</a> 
						<a class="elementContent" href="./OrdiniCliente">I miei ordini</a>
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