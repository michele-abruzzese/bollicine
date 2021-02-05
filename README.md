# bollicine
---------- Run Test in Selenium IDE ----------
I test funzionali sono stati divisi per funzionalità (vedi Test Plan/ paragrafo 9)
I test funzionali dell'inserimento/modifica prodotto, login utente e registrazione utente possono essere esguiti in intellij

I Test per L'inserimento di una nuova carta di credito in fase di acuisto e per l'inserimento di un nuovo indirizzo di spedizione in fase di acquisto 
sono eseguibili tramite Slenium IDE come estensione del browser.

Prerequisiti per l'esecuzione dei test:
1. Avviare il server (Tomcat)
2. Loggarsi con un account utente
3. Inserire un prodotto nel carrello
(I passi 2 e 3 sono necessari per non dover eseguire entrambi i passi in ogni Test Case)

Passi per eseguire i test di cui sopra:
1. Aggiungere l'estensione Selenium IDE al browser
2. Avviare Selenium IDE
3. All'avvio scegliere "open an existing project"
4. Scegliere il file che comprende la test suite composta dai Test Case nella cartella "Script for Selenium IDE" 
 (per fare il test dell'indirizzo di spedizione si sceglie "TC_IS" viceversa, per il test della carta di credito si sceglie "TC_CC")
5. Fare click su "Run all tests"

Nell Log di Selenium IDE si evince il risultato di ogni Test Case eseguito
