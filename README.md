# Documentazione My Ticket

# 1. Analisi dei Requisiti

## Obiettivo

Realizzare una piattaforma che mette in contatto i gestori di locali che organizzano eventi e i possibili clienti, mettendo a disposizione gli strumenti per gestire la creazione, la modifica e l’eliminazione di eventi e dei ticket. I Clienti hanno la possibilità di visionare gli eventi disponibili e di acquistare i biglietti.

## Prodotto

Il principale problema che risolve MyTicket è quello di poter monitorare l’accesso agli eventi dei clienti da parte degli organizzatori. È necessario avere una situazione organizzata e chiara del numero di partecipanti, soprattutto negli eventi a numero chiuso o nella quale la prenotazione è obbligatoria. Per gli organizzatori è importante avere sotto controllo gli incassi provenienti dalla vendita dei biglietti e poter controllare le statistiche per migliorare le prestazioni future. Il cliente ottiene la possibilità di visionare velocemente un catalogo di eventi con i possibili biglietti acquistabili e controllare la disponibilità e il prezzo.

# 2. Fase di Progettazione

## Oggetti di Dominio e Classi

![Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-18_alle_16.29.54.png](Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-18_alle_16.29.54.png)

Le entità fondamentali che risultano dallo studio dei requisiti sono queste:

- **User**
- **Manager → User**
- **Business**
- **Event**
- **Ticket**
- **Order**

### Osservazioni e scelte fatte

1. Inizialmente è stato pensato di creare un unica entità sia per gli **utenti**, sia per i **manager**. In un secondo momento è stato adottato un sistema gerarchico realizzabile tramite la logica di JPA, dove la tabella **manager** estende virtualmente la tabella **utente.** In questo modo è possibile eliminare la ridondanza dovuta alla presenza degli stessi dati di base (nome, cognome, etc) e inoltre semplificare in modo significativo la logica di gestione delle due entità.
2. Durante la fase di Login, l'utente non dovrà preoccuparsi di scegliere il tipo di utente (manager, utente), la logica di controllo dell'autenticazione provvederà ad assegnare il ruolo corretto in funzione del risultato ottenuto. Questo garantisce che un utente non può accedere alle risorse di un Manager e viceversa.
3. Un **Evento** offre la possibilità agli **Utenti** di acquistare ticket diversificati (es. Normale, Con Drink, Tavolo) con prezzi diversi.

## Use Cases

Alcuni principali use cases che determinano il funzionamento della piattaforma tenendo conto delle eventuali dipendenze e attori che ne fanno parte.

### Funzionalità lato Utente

- Registrazione
- Login
- Visualizzazione Eventi
    - Visualizzazione Tutti gli eventi
    - Visualizzazione Eventi imminenti
    - Visualizzazione Dettagli Evento
- Visualizzare Tickets
    - Visualizzare i biglietti che offre l'evento
    - Aggiungere i biglietti al carrello
- Visualizzare il Carrello
    - Concludere l'ordine
- Accedere all'Area riservata
    - Visualizzare le informazioni Utente
    - Visualizzare gli Ordini effettuati

    ![Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-18_alle_16.49.22.png](Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-18_alle_16.49.22.png)

### Funzionalità lato Manager

- Registrazione
- Login
- Visualizzazione Dashboard
    - Statistiche
- Gestione Eventi
    - Gestione Biglietti Evento
- Dettagli Azienda

    ![Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-18_alle_16.54.14.png](Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-18_alle_16.54.14.png)

## Pattern architetturale

Prima di passare all'analisi delle Sequenze e gli scenari specifici, è importante fare alcune considerazioni sul tipo di architettura scelta. In particolare su come viene organizzato il funzionamento del Backend. 

> In seguito verranno specificate meglio le scelte tecniche specifiche adottate.

### Model-View-Controller (MVC)

![Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Untitled.png](Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Untitled.png)

L'MVC è un pattern utilizzato per suddividere e organizzare il codice del programma in parti ben distinte. In particolare:

- **Model:** contiene i metodi egli oggetti per l'accesso ai dati.
- **View:** il suo compito è quello di visualizzare i dati all'utente e ne gestisce l'interazione tra UI e backend.
- **Controller**: si occupa di gestire le richieste da parte della view e inviare le corrispondenti risposte in relazione ai dati ricevuti ed ai dati del model.

### Architettura 3-tier

Nel caso specifico si è scelto di dividere la parte di view da quella di model e controller. È una scelta che offre una grande flessibilità, in quanto permette di separare la logica e le chiamate al database, dalla logica di visualizzazione. 

![Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Untitled%201.png](Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Untitled%201.png)

Questa struttura oltre a fornire una maggiore organizzazione, risolve il problema di creare una applicazione monolitica. 

Le tre principali parti che compongono questa architettura sono:

- **Front-End**: Realizzazione di una Single Page Application. Gestisce esclusivamente la parte di visualizzazione. Inoltra le richieste dei dati al backend, riceve le risposte e mostra correttamente i dati.
- **Back-End**: Ha il compito di gestire l'autenticazione degli utenti, gestire le richieste in arrivo da parte del frontend, e di instradare le query verso il database. Inoltre gestisce la logica della piattaforma.
- **Database**: Il database relazionale si occupa di mantenere tutti i dati che servono all'applicazione per funzionare. Verrà consultato esclusivamente dal backend.

## Sequenze e Scenari

### **Registrazione**

Si analizza il caso della registrazione da parte di un Manager. 

I servizi BusinessService e ManagerService si occupano della logica di persistenza dei dati sul database.

![Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-18_alle_18.16.51.png](Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-18_alle_18.16.51.png)

Si analizza anche il caso della registrazione di un Utente normale. Come nel caso precedente il servizio UserService si occupa della persistenza dell'utente sul database.

![Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-18_alle_18.17.18.png](Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-18_alle_18.17.18.png)

I servizi che si occupano del salvataggio, prima di procedere con la persistenza, devono **criptare** la password tramite un algoritmo sicuro.

### **Login**

Il Login implica un processo di autenticazione da parte del Backend. In questo caso non è importante se chi fa il login è un User o un Manager, perché nella risposta verrà inserito il Ruolo ("USER", "MANAGER") che era stato precedentemente inserito in fase di registrazione. Quando il client invia la richiesta di autenticazione, il backend si occupa di verificare che l'utente sia effettivamente presente all'interno del database, e in caso affermativo, passa l'utente al servizio JWT. 

Il Servizio JWT si occupa di generare un token JWT che conterrà le informazioni minime per il riconoscimento dell'utente (username, ruolo). Questo token verrà inoltrato all'interno della response nel campo header ("Authorization").

Sarà compito del Client di inserire il token in ogni richiesta che necessita di autorizzazione. In questo modo il Backend sa sempre chi sta facendo una determinata richiesta.

![Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-19_alle_12.36.41.png](Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-19_alle_12.36.41.png)

### **Ordine dell'utente**

Si analizza il caso di un nuovo ordine effettuato da un utente. In particolare questo è uno scenario in cui l'utente deve essere autenticato. Quindi il backend prima di eseguire la logica di salvataggio verifica la validità del token. In caso affermativo la richiesta viene passata al servizio dedicato agli ordini. 

**Ogni volta che un servizio necessita dell'utente corrente, si interfaccia con un'altro servizio (currentUserService) che ha l'unico compito di ricavare dalla sessione corrente l'utente, e restituirlo. Lanciando un'errore in caso di utente non presente.**

Per semplicità nella lettura dei diagrammi il servizio **currentUserService** verrà omesso, ma ogni scenario che necessita di autenticazione verrà contrassegnato da un'etichetta.

![Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-19_alle_12.53.46.png](Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-19_alle_12.53.46.png)

### Aggiunta Evento Manager

![Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-19_alle_13.04.44.png](Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-19_alle_13.04.44.png)

### Aggiunta Biglietto Manager

Ogni manager ha la possibilità di aggiungere biglietti agli eventi creati. Infatti i biglietti dipendono dall'esistenza di un Evento.

![Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-19_alle_13.12.44.png](Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-19_alle_13.12.44.png)

# 3. Scelte tecniche

Una volta creato il prototipo e organizzato le funzionalità necessarie è importante scegliere le giuste tecnologie per mettere in pratica quello che è stato pianificato.

In particolare lo stack degli strumenti utilizzati è il seguente:

- Spring Boot
- React JS
- Ant Design
- NodeJS
- JUnit5, Jest (Testing)

### Spring Boot

Spring Boot è una soluzione per il framework Spring di Java, che è stato rilasciato nel 2012 e **riduce la complessità di configurazione di nuovi progetti Spring**. A questo scopo, Spring Boot definisce una configurazione di base che include le linee guida per l'uso del framework e tutte le librerie di terze parti rilevanti, rendendo quindi l'avvio di nuovi progetti il più semplice possibile. 

![Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/springbootimg.png](Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/springbootimg.png)

### React JS

React è una libreria JavaScript per creare interfacce utente. In particolare viene utilizzato per realizzare la parte del Frontend, ovvero la "V" del pattern MVC. Si occupa di gestire tutte le interazioni con l'utente, e visualizzare i dati corretti che provengono dal backend. React offre l'enorme vantaggio di gestire gli stati e tutto ciò che riguarda l'aspetto dinamico delle pagine in modo molto semplice ed intuitivo. 

![Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/download.png](Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/download.png)

Aggiungendo un livello ulteriore allo sviluppo web classico (HTML, JS, CSS), semplifica notevolmente la scrittura del codice da parte del programmatore.

### Ant Design

ReactJS utilizza delle funzioni speciali chiamati Componenti, permettendo di riutilizzare parti di codice che identificano una specifica parte di interfaccia grafica. Grazie a questo, esistono molte librerie di componenti, distribuite sul web che offrono una grande quantità di questi componenti. Aiutando e velocizzando lo sviluppo, scrivendo codice più pulito e ottenendo consistenza grafica nel progetto.

![Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/download.jpg](Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/download.jpg)

# 4. Backend

[Il Progetto è disponibile su GitHub a questo indirizzo: https://github.com/matteomad1011/myticket-be](https://github.com/matteomad1011/myticket-be)

In questa sezione viene mostrato il procedimento che è stato attuato per scrivere il codice della piattaforma. 

La metodologia scelta per la programmazione è quella di realizzare buona parte del Backend come primo step, per poi costruire il Frontend avendo già a disposizione la maggior parte dei servizi necessari al funzionamento. In un secondo momento si passa a modificare se necessario le due parti in modo iterativo.

La parte di Backend viene costruita implementando una logica di programmazione REST API, con lo scopo di creare un'interfaccia tra il Client e le risorse Web. 

Le API (Application Programming Interface) sono set di definizioni e protocolli. Possono essere considerate come un contratto tra un fornitore di informazioni e l'utente destinatario di tali dati: l'API stabilisce il contenuto richiesto dal consumatore (la richiesta) e il contenuto richiesto dal produttore (la risposta).

Quando una richiesta **Client** viene inviata tramite un'API REST, questa trasferisce al richiedente o all'Endpoint uno stato rappresentativo della risorsa. L'informazione viene consegnata in uno dei diversi formati tramite HTTP: JSON (Javascript Object Notation), HTML, XLT, Python, PHP o testo semplice. Il formato JSON è uno dei linguaggi di programmazione più diffusi, perché, malgrado il nome, è indipendente dal linguaggio e facilmente leggibile da persone e macchine.

### Configurazione iniziale

![Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-21_alle_09.39.43.png](Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-21_alle_09.39.43.png)

Per configurare l'ambiente di sviluppo iniziale è stato utilizzato lo strumento **[Spring Initializr](https://start.spring.io/).**

Lo strumento permette di scegliere tutte le informazioni e le dipendenze legate al progetto. È stato scelto di utilizzare **Java 8** con **Spring Boot 2.5.1.**

Le dipendenze sufficienti per il boot strap dell'applicazione sono le seguenti: 

- **Spring Security:** necessario per gestire l'autenticazione tramite token JWT e ottenere uno strato di sicurezza completo su tutti gli endpoint che necessitano l'autorizzazione.
- **Spring Data JPA:** necessario per creare uno il layer di persistenza con il database MySql. Offre le interfacce per poter gestire le query in modo semplificato.
- **Driver MySQL:** il driver che consente a JPA di comunicare correttamente con il database MySql.
- **Spring Web:** si occupa di configurare l'ambiente MVC e consente di accedere a tutti i metodi che permettono la comunicazione REST tra client e server.
- **Lombok: non necessario.** Offre delle annotazioni che si occupano di automatizzare la creazione di Getter, Setter, Costruttori etc. In modo da semplificare la lettura delle classi e velocizzare la scrittura del codice.

### Struttura del Progetto

Il progetto è strutturato in packages. Ognuno di essi contiene in modo organizzato i file identificati da quella categoria.

1. **Model**
2. **Controller**
3. **Repo**
4. **Service**
5. **Dto**
6. **Exception**
7. **Security**

![Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-21_alle_15.49.57.png](Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-21_alle_15.49.57.png)

### 1. Model

Il package **model** contiene tutte le classi che rappresentano in modo 1:1 le classi che sono state dichiarate durante la progettazione.

Ogni classe rappresenta anche l'entità che viene poi trasformata in tabella sul database.

Tramite l'attributo:

```java
@Entity
public class User {
	...
}
```

![Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-21_alle_15.57.26.png](Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-21_alle_15.57.26.png)

JPA capisce che deve mappare la classe in tabella. Questo sistema permette di gestire a Oggetti le entità del database. Semplificando notevolmente la gestione di query e della logica di business.

È importante osservare che oltre alle entità dichiarate durante la progettazione del diagramma delle Classi, possono essere aggiunte altre classi (aggiuntive) che però non vengono annotate tramite l'attributo @Entity. 

Ad esempio l'enum Authority esporta due valori: MANAGER, USER. Che vengono usati per identificare il ruolo dell'utente registrato. Per semplicità è stato scelto di non creare una tabella dei ruoli in quanto non è stato previsto di modificarne i valori.

---

### 2. Controller

Le classi relative ai controller contengono gli endpoint che Spring Web utilizza per ricevere le richieste da parte del Client. 

Le classi sono organizzate in modo tale da contenere tutti gli endpoint relativi all'entità di dominio in riferimento.

In particolare i controller Crud fanno riferimento a tutti gli endpoint utilizzati dai Manager per creare o modificare gli gli eventi e i biglietti.

Questo perché i Controller vengono categorizzati anche in base alla sicurezza d'accesso. Infatti un Utente normale non potrà accedere agli endpoint relativi al Manager.

![Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-21_alle_16.01.25.png](Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-21_alle_16.01.25.png)

---

### 3. Repositories (Repo)

![Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-21_alle_16.47.49.png](Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-21_alle_16.47.49.png)

L'obiettivo delle Repository di Spring Boot Data è quello di aggiungere un livello di astrazione alla persistenza e ridurre notevolmente la quantità di codice necessario ad implementare l'accesso al Database.

Le Repositories sono interfacce implementate in automatico da Spring Boot che definiscono i metodi per accedere al database.

Ogni metodo simula in modo semplificato la query che viene eseguita. Ad esempio:

```java
User findByUsername(String username);
// Restituisce un Utente con username passato come parametro. 
// Altrimenti ritorna null
```

---

### 4. Service

Tutte le classi che fanno parte dei Servizi si occupano di gestire la logica principale dell'applicazione, interfacciando i Controller con le Repositories. In questo modo si crea uno strato di business logic dove si lavora esclusivamente con i dati ricevuti dalle repository, restituendo il risultato elaborato verso i Controller. 

Questo è un esempio della struttura logica applicata alla gestione degli utenti. 

Il servizio Utenti può avere bisogno di molti dati provenenti da diverse repositories prima di poter elaborare una risposta completa.

![Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-21_alle_17.08.40.png](Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-21_alle_17.08.40.png)

![Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-24_alle_16.46.50.png](Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-24_alle_16.46.50.png)

---

### 5. **DTO (Data Transfer Object)**

In questo package sono contenuti tutti i DTO necessari ai controller per interpretare le richieste e inviare le risposte. Un DTO è una rappresentazione di un oggetto che ha lo scopo di essere trasmesso tra Client e Object. Solitamente questo tipo di classi vengono convertite da JSON a Classe e viceversa (in questo caso Spring Boot esegue questa operazione automaticamente sui controller definiti).

Un esempio può essere il semplice DTO della risposta di Autenticazione, dove non è molto sicuro inviare l'intero oggetto User in quanto può contenere molti dati sensibili. In questo caso viene creato un DTO contenente solamente i dati necessari e inviato come risposta al Client. 

È importante avere sia sulla parte di Front-End sia su quella di Back-End tutti i DTO sincronizzati. Altrimenti durante la comunicazione bidirezionale possono insorgere dovuti a conflitti, valori inesistenti, oppure eccezioni.

### 6. **Exception**

In questo progetto è stata creata una sola Eccezione personalizzata per gestire il caso in cui una risorsa generica non sia stata trovata. È importante creare le proprie Eccezioni sopratutto nel caso di errori complessi che hanno bisogno di essere individuate e gestite in modo specifico.

Questa eccezione in particolare ha la caratteristica che quando viene lanciata, il Controller che la raccoglie lancia in automatico un errore HTTP di tipo 404.

Questa funzionalità può essere ottenuta tramite l'annotazione fornita da Spring Boot chiamata @ResponseStatus, posizionata prima della dichiarazione della stessa.

L'eccezione può essere lanciata da un Servizio nel caso in cui una Repository restituisca un valore Null o un risultato Vuoto.

![Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-24_alle_17.06.22.png](Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-24_alle_17.06.22.png)

![Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-24_alle_17.07.29.png](Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-24_alle_17.07.29.png)

In questo caso il Servizio degli Eventi lancia l'eccezione ResourceNotFoundException se la Repo a cui chiede l'evento tramite l'ID non ritorna nessun valore.

### 7. **Security**

Spring Boot gestisce la sicurezza delle applicazioni tramite il modulo Spring Security. Una volta importato vengono automaticamente bloccati tutti gli accessi ed è necessario configurare manualmente le risorse che vanno rese accessibili.

Per configurare Spring Security è necessario creare una classe che estende WebSecurityConfigurerAdapter, e facendo l'override del metodo .configure(HttpSecurity).

Questa è la configurazione applicata nel progetto:

```java
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	...

	...

@Override
    protected void configure(HttpSecurity http) throws Exception {

        // Enable CORS and disable CSRF
        http.cors().and().csrf().disable();

        // Set session management
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        // Set permission on endpoints
        http.authorizeRequests()

								// Private Endpoints
                .antMatchers("/test/auth").authenticated()
                .antMatchers("/test/role").hasAuthority(Authority.MANAGER.name())

                .antMatchers("/api/order/**").hasAuthority(Authority.USER.name())
                .antMatchers("/api/manager/**").hasAuthority(Authority.MANAGER.name())

								// Public Endpoints
                .antMatchers("/api/register/*").permitAll()
                .antMatchers("/authenticate").permitAll()
                .antMatchers("/public/**").permitAll()
                .antMatchers("/test/**").permitAll()

                // All others Endpoints must be authenticated
                .anyRequest().authenticated();

        // JWT Filter
        http
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
```

### JWT

È importante ricordare l'implementazione di JWT all'interno dell'applicazione. La scelta di questo metodo di autenticazione e autorizzazione influenza molti aspetti legati al funzionamento dell'app.

Spring Security gestisce l'accesso e la sicurezza tramite una catena di Filtri che serialmente controllano le richieste in arrivo e determinano se hanno il permesso per passare o vanno bloccate prima di arrivare alla logica.

![Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Untitled%202.png](Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Untitled%202.png)

Per accedere alle risorse che necessitano di Autenticazione l'utente deve inizialmente fare il login e ricevere un token JWT. Successivamente provvedere ad inviare questo token ad ogni richiesta. Questo token contiene le informazioni che caratterizzano l'utente e il server ha il compito di verificarne l'autenticità.

Per attuare questo metodo di Autenticazione e Autorizzazione è necessario creare un Controller apposito che gestisca l'endpoint per il login, ovviamente questo endpoint deve essere dichiarato come pubblico altrimenti nessuno potrebbe fare l'accesso.

- Una volta che l'utente invia Username e Password, nel caso i dati siano corretti, viene generato un nuovo token JWT ed inviato all'interno dell'Header Authorization. A questo punto è compito del Client salvare questo token in un posto sicuro, per poi inserirlo in tutte le richieste che necessitano di Autenticazione.

È inoltre necessario creare un Filtro di Spring Security Custom che venga eseguito ad ogni richiesta proprio per controllare la presenza o meno del token, e in caso di invalidità oppure assenza di esso rifiutare la richiesta.

# 5. Wireframing

Nella prossima sezione vengono presentati i bozzetti di come l'applicazione finale dovrebbe apparire. Non vengono disegnati i dettagli ma solamente le sezioni che comporranno l'interfaccia complessiva.

Lo strumento utilizzato per realizzare i wireframe è Figma. Qui sotto è disponibile il link al progetto.

[Progetto Condiviso](https://www.figma.com/file/Ad3pHzXSAdA1yL11GzvT8c/Untitled?node-id=0%3A1)

## Pagine pubbliche

### Pagine di Login e Registrazione

L'utente ha a disposizione queste pagine per eseguire la registrazione e il login. Si noti come nella pagina di registrazione è possibile scegliere tra utente di tipo Normale oppure di tipo Manager. Mentre per semplicizzare l'esperienza utente il login è unico per tutti i tipi di utenti.

![Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-20_alle_18.01.23.png](Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-20_alle_18.01.23.png)

![Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-20_alle_18.01.37.png](Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-20_alle_18.01.37.png)

---

### Home page

La home page consente di visualizzare diversi tipi di categorie ed eventi. Per semplicità è stato deciso di mostrare due sole categorie con i rispettivi eventi. Ad esempio una categoria potrebbe essere "Eventi in arrivo prossimamente", oppure la categoria "Tutti gli eventi".

![Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-20_alle_18.08.59.png](Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-20_alle_18.08.59.png)

---

### Dettaglio dell'Evento

Quanto un utente seleziona un qualsiasi evento presente sulla piattaforma, viene indirizzato alla pagina del dettaglio di quell'evento. In questa pagina vengono mostrate tutte le informazioni disponibili per l'evento selezionato. Inoltre vengono mostrati i biglietti disponibili. 

I biglietti possono essere aggiunti al carrello attraverso un bottone.

![Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-20_alle_18.11.33.png](Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-20_alle_18.11.33.png)

---

### Carrello

In questa sezione l'utente che ha precedentemente selezionato dei biglietti, può visionare l'ordine prima di concludere l'acquisto.

Inoltre ha la possibilità di rimuovere i biglietti che non desidera.

![Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-20_alle_18.13.36.png](Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-20_alle_18.13.36.png)

## Pagina Area riservata Utente

Questa pagina è accessibile solamente all'utente che ha eseguito il login. Qui può visionare lo storico degli ordini ed altre eventuali informazioni legate al suo account.

![Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-20_alle_18.49.32.png](Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-20_alle_18.49.32.png)

## Pagine di amministrazione (Manager Only)

Il manager ha una sezione dedicata dove può controllare le statistiche legate alla sua azienda.

Inoltre avrà a disposizione la sezione dove poter gestire gli eventi e i biglietti, potendoli aggiungere ed eliminare in base alle esigenze.

![Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-20_alle_18.55.17.png](Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-20_alle_18.55.17.png)

![Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-20_alle_18.55.08.png](Documentazione%20My%20Ticket%20182592b6d3b544a8a26cfdde6d1bcea2/Schermata_2021-06-20_alle_18.55.08.png)

---
