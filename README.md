# bollicine

Progetto realizzato per il corso di Ingegneria del software della laurea triennale in Informatica presso l'Università degli studi di Salerno.

Il progetto prevede la realizzazione di un sito di e-commerce che mira alla vendita di vini e, in primo luogo, lo scopo fondamentale del progetto è quello di generare la documentazione adatta alla descrizione di tutte le scelte architetturali e non. 

Il progetto è  stato realizzato da Michele Abruzzese e Rocco Pagliarulo.


---------- Run Test in Selenium IDE ----------
I test sono stati divisi per funzionalità (vedi Test Plan/ paragrafo 9)
I test funzionali dell'inserimento/modifica prodotto, login utente e registrazione utente possono essere eseguiti in intellij

I Test per L'inserimento di una nuova carta di credito in fase di acquisto e per l'inserimento di un nuovo indirizzo di spedizione in fase di acquisto 
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

---------- Selenium Test in Intellij IDE ----------
I test funzionali dell'inserimento/modifica prodotto, login utente e registrazione utente possono essere eseguiti in Intellij.
I test sono stati configurati con un driver di tipo ChromeDriver

Prerequisiti per l'esecuzione dei test:
1. Browser Chrome
2. ChromeDriver.exe compatibile con la propria versione di Chrome
3. Il file ChromeDriver deve essere presente nella cartella:"src/main/webapp/WEB-INF/utility/chromedriver.exe"
4. Avviare il server (Tomcat)

Passi per eseguire i test:
1. Tasto destro sulla cartella "src/test/java/SeleniumTest"
2. Click su "Run Test in Selenium Test"

Nell Log di Intellij si evince il risultato di ogni Test Case eseguito
