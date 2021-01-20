<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%

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
	<link href="../../Style.css?ts=<?=time()?>&quot" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="../../fontawesome-free-5.13.0-web/css/all.css" type="text/css">
	<title>Catalogo</title>
</head>

<body>
	<%@ include file="../header.jsp" %>
	<div id="Catalogo">
		<%
			if(request.getParameter("cat")==null){
		%>
			<div id="imgCat">
				<img src="${pageContext.servletContext.contextPath}/imgs/paesaggio2.jpg">
			</div>
			
		<%
			}else if(request.getParameter("cat")!=null){
				
				if(request.getParameter("cat").equalsIgnoreCase("bi")){
		%>
				<div id="imgCat">
					<img src="${pageContext.servletContext.contextPath}/imgs/vino-bianco.jpg">
				</div>
		<%
				}else if(request.getParameter("cat").equalsIgnoreCase("ro")){
		%>
				<div id="imgCat">
					<img src="${pageContext.servletContext.contextPath}/imgs/vino-rosso.jpg">
				</div>
		<%
				}else if(request.getParameter("cat").equalsIgnoreCase("spu")){
		%>
				<div id="imgCat">
					<img src="${pageContext.servletContext.contextPath}/imgs/spumante.jpg">
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
				
				<a href="./ProdottoSingolo?id=<%= bean.getIdProdotto() %>">
					<img src="./GetImmagine?id=<%=bean.getIdProdotto() %>" onerror="this.src='../imgs/nophoto.png'">
				</a>		
				<h2><%=bean.getNome() %></h2>
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
	<%@include file="../footer.html"%>
	
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