<%--
  Created by IntelliJ IDEA.
  User: roccopagliarulo
  Date: 21/01/21
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="../../Style.css?ts=<?=time()?>&quot" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="../../fontawesome-free-5.13.0-web/css/all.css" type="text/css">
    <title>Login</title>
</head>
<body onload="myFunction()" onresize="myFunction()">
<script src="http://code.jquery.com/jquery-1.6.4.min.js" type="text/javascript"></script>

<script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.14.0/jquery.validate.min.js"></script>

<%@ include file="../header.jsp" %>
<div id="Login">
    <h1 class="intest">Accedi o crea un account</h1>

    <div id="contentLogin">

        <div id="formLogin">

            <%
                String errore=(String)request.getAttribute("invalidAccess");
                if(errore!=null && errore.equalsIgnoreCase("true")){
            %>
                <div id="errorLogin"><p>Password errata!</p></div>
            <%
                }
            %>
            <h2 class="intest">Hai già un account?</h2>
            <div id="form">
                <form action="${pageContext.servletContext.contextPath}/Login" method="post">

                    <h4 class="h4">Email</h4>
                    <input id="emailControlLog" onfocusout="controlEmailLog()" class="textArea" type="text" name="email" placeholder="email" autocomplete="off" minlength="2" maxlength="256" required><br>

                    <h4 class="h4">Password</h4>
                    <input id="pwd1" class="textArea" type="password" name="password" placeholder="password"  autocomplete="off" maxlength="15" required><br>
                    <input class="buttonLogin" type="submit" value="Login"/>
                    <input class="buttonLogin" type="reset" value="Reset"/><br>

                </form>
            </div>


            <div class="nuovoCliente">
            <!-- bottone per aprire l'inserimento di un nuovo cliente -->
               Non hai un account? <button id="BtnRegi">registrati</button>

                <!-- The Modal -->
                <div id="myModal" class="modal">
                    <!-- Modal content -->
                    <div class="modal-content">
                        <span class="close">&times;</span>

                        <h2>Se non sei registrato crea un account</h2>

                        <form action="${pageContext.servletContext.contextPath}/AddNewClient?action=0"  method="post" class="dati_anagrafici2">

                            <h2>I tuoi dati</h2>

                            Cognome<br>
                            <input  name="cognome" type="text" minlength="2" maxlength="30" required><br>
                            Nome<br>
                            <input  name="nome" type="text" minlength="2" maxlength="30" required><br>
                            Email<br>
                            <input id="emailControl" name="email" type="text" onfocusout="controlEmail()" minlength="2" maxlength="256" required><br>
                            Password<br>
                            <input id="pwd" name="password" type="password" maxlength="15" required><br>

                            <input class="showPwInModal" type="button" onclick="showPwd()" value="Mostra/nascondi password">
                            <input class="buttonRegi" type="submit" value="registrati">

                        </form>

                    </div>
                </div>
            </div>

        </div>

    </div>
</div>
<%@include file="../footer.html"%>

<script>

    $().ready(function(){
        $(".dati_anagrafici2").validate({
            rules:{

                'email':{
                    email:true
                },
                'password':{
                    minlength:4
                }
            },
            messages:{

                'email':{
                    email:'<br><b>Indirizzo email non valido!</b>'
                },
                'password':{
                    minlength:'<br><b>Almeno 4 caratteri</b>'
                }
            }
        });
        jQuery.extend(jQuery.validator.messages, {
            required: "<br><b>Campo obbligatorio</b>"
        });
    });
    validator.form();


    function showPwd() {
        var input = document.getElementById('pwd');
        if (input.type === "password") {
            input.type = "text";
        } else {
            input.type = "password";
        }
    }

    function myFunction() {
        var w = window.outerWidth;
        var h = window.outerHeight;

        var formLogin=document.getElementById("formLogin");
        if(w<=1295){
            var padre=document.getElementById("contentLogin");
            padre.insertBefore(formLogin,padre.childNodes[0]);
        }

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

        var email=<%=request.getSession().getAttribute("confermaEmail")%>
        var confermato=<%=request.getSession().getAttribute("confermato")%>
        var nonConfermato=<%=request.getSession().getAttribute("nonConfermato")%>

        if(email!=null && email){
            alert("abbiamo inviato l'email al tuo account, vai a confermare entro 10 minuti");
            <%request.getSession().removeAttribute("confermaEmail");%>
        }

        if(confermato!=null && confermato){
            alert("L'account è stato confermato! accedi per acquistare");
            <%request.getSession().removeAttribute("confermato");%>
        }

        if(nonConfermato!=null && nonConfermato){
            alert("Il tempo per confermare é scaduto! registrati di nuovo");
            <%request.getSession().removeAttribute("nonConfermato");%>
        }

    }



    function controlEmail(){
        var email=document.getElementById("emailControl");
        var valueEmail=email.value;

        var url="${pageContext.servletContext.contextPath}/ValidateEmail?email="+valueEmail;
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (xhr.readyState == 4 && xhr.status==200) {
                var data=String(xhr.responseText);

                if(data!=0){
                    email.style.borderColor="#c02230";
                    alert("Email già in uso!");
                    email.value="";
                }else {
                    email.style.borderColor="#fff";
                }
            }
        }
        xhr.open('GET', url, true);
        xhr.send(null);

    }

    function controlEmailLog(){
        var email=document.getElementById("emailControlLog");
        var valueEmail=email.value;

        var url="${pageContext.servletContext.contextPath}/ValidateEmail?email="+valueEmail;
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (xhr.readyState == 4 && xhr.status==200) {
                var data=String(xhr.responseText);

                if(data==0){
                    email.style.borderColor="#C02230";
                    alert("Email non valida!");
                    email.value="";
                }else {
                    email.style.borderColor="#fff";
                }
            }
        }
        xhr.open('GET', url, true);
        xhr.send(null);

    }
</script>

<script>
    // Get the modal
    var modal = document.getElementById("myModal");

    // Get the button that opens the modal
    var btn = document.getElementById("BtnRegi");

    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];

    // When the user clicks the button, open the modal
    btn.onclick = function() {
        modal.style.display = "block";
    }

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
</script>
</body>
</html>