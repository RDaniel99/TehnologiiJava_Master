# TehnologiiJava_Master

## Laborator 8: Finalizat complet

Acesta se regaseste in Folderul **Laborator7**
La primul punct am avut patru servicii CRUD pentru Documente folosind JAX-RS. Acestea se regasesc in fisierele AddDocumentServlet, DeleteDocumentServlet, GetDocumentServlet, UpdateDocumentServlet. Pentru a putea folosi JAX-RS, a fost nevoie si de adaugarea unei clase noi prin care se pornesc serviciile de JAX-RS. Acesta se numeste RestApplication.java.
La al doilea punct am avut de facut un filtru ce joaca rol de cache pentru serviciul de GetDocuments, acesta se regaseste in package-ul de filters, in fisierul ViewDocumentsFilter.java.
Al treilea punct a fost de construit un utilitar prin intermediul caruia, in cazul in care documentele ar avea referinte alte documente, sa le putem ordona cronologic. Pentru asta, am implementat algoritmul de sortare topologica in clasa DocumentsDependencySolver, care a fost testata in DocumentDependencySolverShould. Pentru adaugarea unei dependinte de la documentul cu id-ul A la documentul cu id-ul B (insemnand ca A are ca si referinta pe B), se foloseste metoda addEdge(fromDocumentId, toDocumentId). Inainte de a adauga muchii intre noduri (=documente), documentele (=nodurile) trebuie adaugate prin addId(documentId).

## Laborator 6: Finalizat
Acesta se regaseste in Folderul **Laborator5.1**
Pentru a evita anumite erori de configurare, am continuat laboratorul 6 in acelasi folder cu Laboratorul 5 si totodata Laboratorul 6 era o continuare al Laboratorului 5.

## Laborator 5: Finalizat complet

Acesta se regaseste in Folderul **Laborator5.1.**
Din anumite motive, nu puteam sterge folderul Laborator5, acolo s-a produs o eroare de configurari si nu ma puteam conecta la baza de date printr-un connection pool si am fost nevoit sa refac proiectul de la zero.

## Laborator 3: Finalizat complet

Punctul 1:

Unui student trebuie sa i se atribuie un nume si o lista de id-uri de examene, separate prin virgula, fara spatii.
Unui examen trebuie sa i se atribuie un nume, o ora (fixa) de inceput si un numar de minute pentru durata.
Tabelele sunt tinute in MySQL.
Pentru interfata, s-a folosit primefaces unde a fost cazul.
Ca si componenta non-triviala JSF, s-a folosit data table.

Punctul 2:

Ideea algoritmului: Pentru fiecare pereche de examene E1 si E2, construiesc graful in care exista muchie intre E1 si E2 doar daca E1 si E2 se suprapun si exista un student S care vrea sa participe atat la E1, cat si la E2.
Apoi, acest graf il "sparg" in componente conexe. Din fiecare componenta conexa, aleg un subset de noduri astfel incat acestea sa nu fie conectate prin muchie si numarul de noduri alese sa fie maxim. Daca nodurile (=examenele) nu sunt conectate prin muchie, inseamna ca acestea pot fi in aceeasi zi, indiferent daca se suprapun sau nu. Cu atat mai mult, nu va exista conflict intre doua examene din componente conexe diferite.

## OLD

## Laborator 2: Finalizat aproape complet (mai putin ultimul punct)
- prezentat live

## Laborator 1: Finalizat complet (Foldere: Laborator1 si Laborator1_ExternalPython)
Servletul creat pentru rezolvarea primului punct este HelloServlet.
Acesta primeste datele din formul din index.jsp pe care le proceseaza conform enuntului.
Observam ca metoda solveMockWhenIsFalse(request, response) valideaza datele primite din form inainte de a executa requestul efectiv.

Pentru punctul al doilea, scriptul in Python care apeleaza servletul nostru se afla in folderul Laborator1_ExternalPython.

Pentru al treile punct, cateva rezultate sunt:
* sync=false
- 10: 0.20841598510742188 seconds
- 50: 0.9196887016296387 seconds
- 100: 1.7303531169891357 seconds
- 500: 9.975478649139404 seconds
- 1000: 36.24270725250244 seconds

* sync=true
- 10: 0.054033517837524414 seconds
- 50: 0.3820829391479492 seconds
- 100: 1.1623854637145996 seconds
- 500: 12.132383584976196 seconds
- 1000: 46.92441248893738 seconds

O observatie pe care o putem face pe datele de mai sus (reprezentate in graficul de mai jos), ar fi ca pentru numar mic de threaduri concurente (un volum mai mic de requesturi venite simultan), este mai buna o abordare sincrona, in timp ce pentru un numar mai mare de threaduri concurente (un volum mai mare de requesturi venite simultan), o abordare asincrona va fi castigatoare.

![alt text](Laborator1_Punct3.png)
