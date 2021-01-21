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
				int i=0;
				if (products != null && products.size() != 0) {
					Iterator<?> it = products.iterator();
					while (it.hasNext()) {
						ProdottoDTO bean = (ProdottoDTO) it.next();
						if(bean.getDisponibilità()>0){
			%>
			<div class="column">
				<%-- immagine del prodotto, cliccandoci sopra andiamo nella pagina del prodotto in dettaglio --%>
				
				<a href="./ProdottoSingolo?id=<%= bean.getIdProdotto() %>">
					<img src="./GetImmagine?id=<%=bean.getIdProdotto() %>" onerror="this.src='${pageContext.servletContext.contextPath}/imgs/nophoto.png'">
				</a>		
				<h2><%=bean.getNome() %></h2>
				<h2><%=bean.getPrezzo() %> €</h2>
					<!-- bottone per aprire l'inserimento  -->
					<button id="buttonAddCartCatal" class="buttonAddCartCatal<%=i++ %>" onclick="myfunction(this.className)">Aggiungi al carrello</button>
					<div id="addProductInCart">
								<!-- The Modal -->
								<div id="myModal" class="modal">

									<!-- Modal content -->
									<div class="modal-content">
										<span class="close">&times;</span>

										<%
											//se nel carrello la quantità del prodotto è minore della disponibilità allora posso aggiugerne altri

											if(cart.getQ(bean.getIdProdotto())<bean.getDisponibilità()) {
										%>
										<h3>Inserisci la quantità da aggiungere al carrello</h3>
										<input type="hidden" name="id" value="<%=bean.getIdProdotto()%>">
										<input class="qtAddCart" type="number" name="quantita" min="1" required max="<%=bean.getDisponibilità()-cart.getQ(bean.getIdProdotto())%>" placeholder="max <%=bean.getDisponibilità()-cart.getQ(bean.getIdProdotto())%>">
										<input id="buttonAddCartModal" onclick="addCart(<%=bean.getIdProdotto()%>)" type="submit" value="aggiungi al carrello">
										<%
											}else{
										%>
										<h3>Hai raggiunto la disponibilità massima!</h3>
										<%
											}
										%>
									</div>
								</div>
					</div>
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
		var num=0;
		function myfunction(name) {
			var a=name.replace(/[^\d]+/g, '');
			num=Number(a);

			var modal = document.getElementsByClassName("modal")[num];

			modal.style.display = "block";

			var span = document.getElementsByClassName("close")[num];

			// When the user clicks on <span> (x), close the modal
			span.onclick = function() {
				modal.style.display = "none";
			}

			// When the user clicks anywhere outside of the modal, close it
			window.onclick = function(event) {
				if (event.target == modal) {
					modal.style.display = "none";
				}
			}


		}
		function addCart(idProd) {
			var arr= document.getElementsByClassName("qtAddCart");
			var qt = arr[num].value;

			var url="AddInCart?quantita="+qt+"&id="+idProd;
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