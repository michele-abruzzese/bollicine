<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	System.out.println("sssssss");
List<ProdottoDTO> products = (List<ProdottoDTO>)  request.getAttribute("products");
if(products == null) {
	response.sendRedirect("./Prodotto");
	return;
}

ProdottoDTO product = (ProdottoDTO) request.getAttribute("product");

CarrelloBean cart = (CarrelloBean) request.getAttribute("cart");
%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,Model.DAO.ProdottoDTO,Beans.CarrelloBean"%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="../Style.css?ts=<?=time()?>&quot" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="../fontawesome-free-5.13.0-web/css/all.css" type="text/css">
	<title>Catalogo</title>
</head>

<body>
	<%@ include file="header.jsp" %>
	<div id="Catalogo">
		<%
			if(request.getParameter("cat")==null && request.getParameter("tag")==null){
		%>
			<div id="videoDiv">
				<video id="video" autoplay loop muted="muted">
					<source src="./imgs/video2.mp4" type="video/mp4">
				</video>
			</div>
			
		<%
			}else if(request.getParameter("cat")!=null){
				
				if(request.getParameter("cat").equalsIgnoreCase("br")){
		%>
				<div id="imgCat">
					<img src="./imgs/bracciali.jpg">
				</div>
		<%
				}else if(request.getParameter("cat").equalsIgnoreCase("an")){
		%>
				<div id="imgCat">
					<img src="./imgs/anelli.jpg">
				</div>
		<%
				}else if(request.getParameter("cat").equalsIgnoreCase("col")){
		%>
				<div id="imgCat">
					<img src="./imgs/collane.jpg">
				</div>
		<%
				}else if(request.getParameter("cat").equalsIgnoreCase("ore")){
		%>
				<div id="imgCat">
					<img src="./imgs/orecchini.jpg">
				</div>
		<%
				}else if(request.getParameter("cat").equalsIgnoreCase("orol")){
		%>
				<div id="imgCat">
					<img src="./imgs/orologi.jpg">
				</div>
		<%
				}else if(request.getParameter("cat").equalsIgnoreCase("dia")){
		%>		
				<div id="imgCat">
					<img src="./imgs/diamanti.jpg">
				</div>
		<% 
				}
				
			}else if(request.getParameter("tag")!=null){
				if(request.getParameter("tag").equalsIgnoreCase("Compleanno")){
		%>	
				<div id="imgCat">
					<img src="./imgs/compleanno.jpg">
				</div>
		<% 		
				}else if(request.getParameter("tag").equalsIgnoreCase("Matrimonio")){
					
		%>
				<div id="imgCat">
					<img src="./imgs/matrimonio.jpg">
				</div>
		<% 
				}else if(request.getParameter("tag").equalsIgnoreCase("FPapa")){
					
		%>
				<div id="imgCat">
					<img src="./imgs/Fpapà.png">
				</div>
		<% 
				}else if(request.getParameter("tag").equalsIgnoreCase("FMamma")){
					
		%>
				<div id="imgCat">
					<img src="./imgs/Fmamma.jpg">
				</div>
		<%
				}
			}
		%>
		<div class="row">
			<%
				if (products != null && products.size() != 0) {
					Iterator<?> it = products.iterator();
					while (it.hasNext()) {
						ProdottoDTO bean = (ProdottoDTO) it.next();
						if(bean.getDisponibilità()>0){
			%>
			<div class="column">
				<%-- immagine del prodotto, cliccandoci sopra andiamo nella pagina del prodotto in dettaglio --%>
				
				<a href="./SingleProductControl?id=<%= bean.getIdProdotto() %>">
					<img src="./GetImmagine?id=<%=bean.getIdProdotto() %>" onerror="this.src='../imgs/nophoto.png'">
				</a>		
				<h1><%=bean.getNome() %></h1>
				<h2><%=bean.getPrezzo() %> €</h2>
				
				
					<input id="buttonAddCartCatal" onclick="addCart(<%=bean.getIdProdotto()%>)" type="submit" value="aggiungi al carrello">
				
			</div>	
			<%
						}
					}
				} else {
			%>
				<h1>no product </h1>
			<%
				}
			%>
		</div>
		
	</div>
	<%@include file="footer.html"%>
	
	<script>
		function addCart(idProd) {
			
			var url="product?action=addC&quantita=1&id="+idProd;
			 var xhr = new XMLHttpRequest();
			    xhr.onreadystatechange = function() {
			        if (xhr.readyState == 4 && xhr.status==200) {
			           alert("prodotto aggiunto al carrello");
			        }
			    }
			    xhr.open('GET', url, true);
			    xhr.send(null);
		}
	
	</script>
</body>
</html>