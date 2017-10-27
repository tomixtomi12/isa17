# Internet softverske arhitekture

## Konstrukcija servera i inverzija kontrole

### Primer 01: Primer HTTP servera

Implementiran je trivijalan HTTP server koji na sve GET zahteve odgovara sa 
200 OK, a na sve ostale zahteve odgovara sa 404 Method Not Allowed.

Svaki klasičan server poseduje beskonačnu petlju u kojoj čeka na uspostavu
veze od strane klijenta, i pokreće posebnu nit za obradu svakog pristiglog 
zahteva.

### Primer 02: Klijent/server program pomoću Java RMI

Implementiran je klijent/server program pomoću Java RMI (Remote Method
Invocation) tehnologije. Komunikacija između klijenta i servera se tretira
kao poziv metoda udaljenih objekata. Udaljeni objekat se nalazi u okviru
druge VM.

Prilikom poziva udaljenog objekta podaci se prenose kao serijalizovani Java 
objekti. Java klase omogućavaju serijalizaciju ako implementiraju interfejs
`java.io.Serializable`.

### Primer 03: Primer prenosa programskog koda pomoću Java RMI

Osim podataka, prilikom klijent/server komunikacije u RMI tehnologiji moguće
je prenositi i prevedeni programski kod (klase). U slučaju da je tip parametra
metode udaljenog objekta interfejs, serverski objekat ne mora znati unapred
koja implementacija interfejsa će biti korišćena. U tom slučaju potrebno je
preneti programski kod sa klijenta na server. 

Da bi se sprečilo zlonamerno korišćenje ovih mogućnosti, potrebno je prvo
uključiti `RMISecurityManager` a potom i konfigurisati klijentski i serverski
program na odgovarajući način *prilikom pokretanja*.

## Okruženja za serverske objekte

### Primer 04: EJB session beans

EJB session beans su serverski objekti koji se izvršavaju u posebnom okruženju
- EJB kontejneru. Funkcije kontejnera su višestruke, a za početak bitno nam je
da uočimo razliku između:

* *stateless*: serverski objekti koji ne pamte stanje
* *stateful*: serverski objekti koji pamte stanje za svog klijenta
* *singleton*: serverski objekti koji se instanciraju tačno jednom

### Primer 05: Spring bean scope

Spring je najpopularniji okvir za razvoj serverskih aplikacija u Javi. 
Obezbeđuje rukovanje objektima na nešto drugačiji način nego sa EJB session
beans. Postoji više različitih opsega vidljivosti (scope) Spring komponenti:

* *singleton*: podrazumevano ponašanje - kreira se tačno jedna instanca objekta
* *prototype*: kreira se nova instanca objekta svaki put kada se objekat 
zatraži
* *request*: kreira se nova instanca objekta za svaki HTTP zahtev
* *session*: kreira se nova instanca objekta za svaku HTTP sesiju
* *globalSession*: kreira se nova instanca objekta za svaku globalnu HTTP 
sesiju (za portlet aplikacije)
* *application*: kreira se jedna instanca objekta za ceo `ServletContext`

### Primer 06: Obrada događaja životnog ciklusa za EJB session beans

EJB session bean komponente su u stanju da reaguju na nekoliko različitih
događaja u toku svog životnog ciklusa. EJB kontejner će pozvati odgovarajuće
metode, ako postoje, u datim trenucima životnog ciklusa.

### Primer 07: Obrada događaja životnog ciklusa za Spring beans

Spring komponente su u stanju da reaguju na par različitih
događaja u toku svog životnog ciklusa. Spring će pozvati odgovarajuće
metode, ako postoje, u datim trenucima životnog ciklusa.

### Primer 08: EJB1 zove EJB2 uz ručnu uspostavu veze

Jedan EJB može biti klijent za drugi. Dobijanje reference na drugi EJB može
se obaviti na isti način na koji i klijenti to rade.

## Ubrizgavanje zavisnosti

### Primer 09: EJB1 zove EJB2 uz dependency injection

Dobijanje reference na drugi EJB može se obaviti pomoću *dependency injection*
mehanizma.

### Primer 10: Dependency injection za Spring komponente

Jedna Spring komponenta može dobiti referencu na drugu pomoću dependency 
injection mehanizma. U ovom primeru koristi se *autowiring*.

## Aspekt-orijentisano programiranje

### Primer 11: EJB i AOP

Na EJB session bean komponente može se primeniti aspekt-orijentisano 
programiranje (AOP). U ovom primeru pokazano je kako se aspekt vezuje za
EJB session bean.

### Primer 12: EJB i lifecycle callbacks sa ukusom AOP-a

Primer rukovanja događajima životnog ciklusa EJB session beana na način sličan
aspektima.

### Primer 13: Spring AOP

Primer pisanja aspekata u okviru Springa. Ilustrovano je pisanje *before*, 
*after* i *around* saveta.

## Pristup bazama podataka

### Primer 14: Upotreba JDBC API za pristup relacionim bazama podataka

Ilustrovano je slanje SELECT komandi i čitanje rezultata, slanje višestrukih
INSERT naredbi pomoću `PreparedStatement` i pozivanje uskladištenih procedura.

### Primer 15: Primer pristupa bazi podataka iz servleta

Ovaj primer ilustruje naivni pokušaj da se konekcija sa bazom podataka otvori
prilikom inicijalizacije servleta i koristi za svaku obradu zahteva. Pošto se
zahtevi obrađuju u paralelnim nitima, ista konekcija bi bila korišćena u svim
nitima, što nije dozvoljeno. Ovo je primer kako ne treba :)

## Resource pooling

### Primer 16: Pristup bazi iz web aplikacije uz connection pooling

Prethodni primer je prerađen tako da se za svaku nit za obradu zahteva na serveru
koristi posebna konekcija, a konekcije se pri tome recikliraju u okviru
connection pool strukture.


