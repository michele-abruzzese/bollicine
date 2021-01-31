<%--
  Created by IntelliJ IDEA.
  User: Michele
  Date: 25/01/2021
  Time: 10:37
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
    <h1 class="intest">Crea gli account</h1>

    <div id="contentLogin">

        <div id="formLogin">

            <div class="nuovoCliente">
                <!-- bottone per aprire l'inserimento di un nuovo gestore catalogo -->
                <button class="buttonLogin" id="BtnRegi0">Nuovo gestore catalogo</button>

                <!-- The Modal -->
                <div id="myModal0" class="modal">
                    <!-- Modal content -->
                    <div class="modal-content">
                        <span class="close">&times;</span>

                        <h2>Inserisci i dati del getore catalogo</h2>

                        <form action="${pageContext.servletContext.contextPath}/AddNewGestore"  method="post" class="dati_anagrafici2">
                            <input type="hidden" name="tipo" value="gestore catalogo">
                            Cognome<br>
                            <input  name="cognome" type="text" minlength="2" maxlength="30" required><br>
                            Nome<br>
                            <input  name="nome" type="text" minlength="2" maxlength="30" required><br>
                            Email<br>
                            <input id="emailControl" name="email" type="text" onfocusout="controlEmail()" minlength="2" maxlength="256" required><br>
                            Password<br>
                            <input id="pw" name="password" type="password" required><br>

                            <input class="showPwInModal" id="showPwInModal" type="button" onclick="showPwd()" value="Mostra/nascondi password">
                            <input class="buttonRegi" type="submit" value="registrati">

                        </form>

                    </div>
                </div>
            </div>

            <div class="nuovoCliente">
                <!-- bottone per aprire l'inserimento di un nuovo gestore ordini -->
                <button class="buttonLogin" id="BtnRegi1">Nuovo gestore ordini</button>

                <!-- The Modal -->
                <div id="myModal1" class="modal">
                    <!-- Modal content -->
                    <div class="modal-content">
                        <span class="close">&times;</span>

                        <h2>Inserisci i dati del gestore ordini</h2>

                        <form action="${pageContext.servletContext.contextPath}/AddNewGestore"  method="post" class="dati_anagrafici2">
                            <input type="hidden" name="tipo" value="gestore ordini">
                            Cognome<br>
                            <input  name="cognome" type="text" minlength="2" maxlength="30" required><br>
                            Nome<br>
                            <input  name="nome" type="text" minlength="2" maxlength="30" required><br>
                            Email<br>
                            <input id="emailControl1" name="email" type="text" onfocusout="controlEmail()" minlength="2" maxlength="256" required><br>
                            Password<br>
                            <input id="pw1" name="password" type="password" maxlength="15" required><br>

                            <input class="showPwInModal" id="showPwInModal1" type="button" onclick="showPwd1()" value="Mostra/nascondi password">
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
        var input = document.getElementById('pw');
        if (input.type === "password") {
            input.type = "text";
        } else {
            input.type = "password";
        }
    }

    function showPwd1() {
        var input = document.getElementById('pw1');
        if (input.type === "password") {
            input.type = "text";
        } else {
            input.type = "password";
        }
    }

    function myFunction() {
        var w = window.outerWidth;
        var h = window.outerHeight;

        var formLogin = document.getElementById("formLogin");
        if (w <= 1295) {
            var padre = document.getElementById("contentLogin");
            padre.insertBefore(formLogin, padre.childNodes[0]);
        }

        if (w > 880) {
            document.getElementById("bars").style.display = "none";
            document.getElementById("closeMenu").style.display = "none";
            document.getElementById("contentNav").style.display = "block";
            document.getElementById("nav").style.textAlign = "left";
        } else {

            document.getElementById("bars").style.display = "initial";
            document.getElementById("contentNav").style.display = "none";
            document.getElementById("nav").style.textAlign = "center";
            document.getElementById("closeMenu").style.display = "none";
        }

        var confermato=<%=request.getSession().getAttribute("confermato")%>
        if(confermato!=null && confermato){
            alert("L'account è stato registrato! accedi per visualizzare le funzionalità");
            <%request.getSession().removeAttribute("confermato");%>
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
    function controlEmail(){
        var email=document.getElementById("emailControl1");
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
</script>
<script>
    // Get the modal
    var modal0 = document.getElementById("myModal0");

    // Get the button that opens the modal
    var btn0 = document.getElementById("BtnRegi0");

    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];

    // When the user clicks the button, open the modal
    btn0.onclick = function() {
        modal0.style.display = "block";
    }

    // When the user clicks on <span> (x), close the modal
    span.onclick = function() {
        modal0.style.display = "none";
    }

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == modal0) {
            modal0.style.display = "none";
        }
    }
</script>

<script>
    // Get the modal
    var modal = document.getElementById("myModal1");

    // Get the button that opens the modal
    var btn = document.getElementById("BtnRegi1");

    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[1];

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
