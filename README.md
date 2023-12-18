 
# Exercici 1

## Descripció
L'exercici 1 consisteix a crear una sentència SQL per tal de proporcionar una visió detallada de les regions amb les següents dades per a cada una:

1. **Nom de la Regió:** La columna corresponent al nom de la regió.

2. **Mitjana de Vendes per Empleat per Regió:** Aquest valor es calcula mitjançant la divisió de la suma total de les vendes per la regió pel nombre d'empleats a aquesta regió. Aquesta informació indica la rendibilitat mitjana de les vendes per cada empleat en una regió específica.

3. **Diferència Respecte la Mitjana de Vendes Més Alta:** Es calcula restant la mitjana de vendes per empleat per la regió a la mitjana de vendes més alta global. Això mostra com es desvia la rendibilitat mitjana de les vendes per empleat d'una regió respecte a la mitjana més alta.

La consulta busca proporcionar una perspectiva detallada de la rendibilitat mitjana de les vendes per empleat per a cada regió, destacant com aquesta mitjana es compara amb el rendiment més alt a nivell global.


# Exercici 2

## Descripció
L'exercici 2 consisteix a crear un projecte basat en [Spring Boot](https://spring.io/) per a fer el seguiment de dades relacionades amb les vendes d'empleats. Aquest README proporciona una visió general dels **punts d'accés** disponibles per a cada entitat de l'aplicació.

## Taula de continguts
- [Configuració](#configuració)
- [Consola H2](#consola-h2)
- [Endpoints](#endpoints)
  - [Endpoints de Regions](#endpoints-de-regions)
  - [Endpoints de Employees](#endpoints-de-empleats)
  - [Endpoints de Sales](#endpoints-de-vendes)
  - [Endpoints de States](#endpoints-de-estats)
- [Tests](#tests)

## Configuració

Per tal de configurar l'entorn i executar l'aplicació Spring Boot amb Maven, segueix aquestes passes:

1. **Instal·la el JDK (Java Development Kit):** Assegura't de tenir el JDK instal·lat al teu sistema. Pots descarregar el JDK des del [lloc web oficial d'Oracle](https://www.oracle.com/java/technologies/javase-downloads.html) o utilitzar una distribució com [OpenJDK](https://openjdk.java.net/).

2. **Configura la variable d'entorn JAVA_HOME:** Després d'instal·lar el JDK, configura la variable d'entorn `JAVA_HOME` al directori d'instal·lació del JDK. Això generalment es realitza afegint la següent línia al teu perfil d'usuari o a l'arxiu `.bashrc` (o equivalent):

    `export JAVA_HOME=/ruta/al/jdk export PATH=$PATH:$JAVA_HOME/bin`

    Recorda substituir `/ruta/al/jdk` amb la ruta real del teu directori d'instal·lació del JDK.

3. **Descarrega i instala Apache Maven:** Descarrega la versió més recent d'Apache Maven des del [lloc web oficial d'Apache Maven](https://maven.apache.org/download.cgi). Després d'instal·lar Maven, verifica la seva instal·lació executant `mvn -v` des de la línia de comandes.

4. **Afegeix Maven al PATH:** Configura la variable d'entorn `PATH` per incloure el directori de l'executable Maven. Afegeix la següent línia al teu perfil d'usuari o a l'arxiu `.bashrc` (o equivalent):

    `export PATH=$PATH:/ruta/al/maven/bin`

    Recorda substituir `/ruta/al/maven` amb la ruta real del teu directori d'instal·lació de Maven.

5. **Executa l'aplicació Spring Boot:** Utilitza la comanda `mvnw` proporcionada per arrancar l'aplicació Spring Boot. Des de la carpeta del projecte, executa la següent comanda:

    `./mvnw spring-boot:run`

    Això iniciarà l'aplicació Spring Boot i la farà accessible a través de l'adreça `http://localhost:8080`.

## Consola H2

Per accedir a la consola H2, segueix aquestes passes:

1. **URL de la Consola H2:** Pots accedir a la consola H2 utilitzant la següent URL: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

2. **Configuració de la Base de Dades H2:** A la pàgina d'inici de la consola H2, introdueix la següent configuració:

    - **JDBC URL:** `jdbc:h2:file:~/salesmanagementdb`
    - **Usuari:** `root`
    - **Contrasenya:** `root`

    Assegura't de seleccionar el JDBC URL correcte i prem "Connect" per accedir a la base de dades H2.


Ara pots explorar la base de dades i realitzar consultes a través de la consola H2.
## Endpoints

Per a usar els endpoints, es recomana usar una eina de testeig API com pot ser  [Postman](https://www.postman.com/) o [Insomnia](https://insomnia.rest/)

### Endpoints de Regions

#### 1. Llistar les Regions
- [`GET http://localhost:8080/api/regions`](#)
- [`GET http://localhost:8080/api/regions?name=Espanya`](#)
- **Descripció:** Obté totes les regions, opcionalment filtrades per nom

#### 2. Llistar les Regions de manera paginada
- [`GET http://localhost:8080/api/regionsPaginated`](#)
- [`GET http://localhost:8080/api/regionsPaginated?name=Espanya&page=0&size=5`](#)
- [`GET http://localhost:8080/api/regionsPaginated?page=0&size=2`](#)
- **Descripció:** Endpoint per cercar regions per nom amb paginació

### Endpoints de States

#### 1. Llistar els States
- [`GET http://localhost:8080/api/states`](#)
- [`GET http://localhost:8080/api/states?name=Catalunya`](#)
- **Descripció:** Obté tots els states, opcionalment filtrades per nom

#### 2. Llistar els States de manera paginada
- [`GET http://localhost:8080/api/statesPaginated`](#)
- [`GET http://localhost:8080/api/statesPaginated?name=Catalunya&page=0&size=5`](#)
- [`GET http://localhost:8080/api/regionsPaginated?page=0&size=2`](#)
- **Descripció:** Endpoint per cercar states per nom amb paginació

### Endpoints de Employees

#### 1. Llistar els Employees
- [`GET http://localhost:8080/api/employees`](#)
- [`GET http://localhost:8080/api/employees?name=Xavier`](#)
- **Descripció:** Obté tots els employees, opcionalment filtrades per nom

#### 2. Llistar els Employees de manera paginada
- [`GET http://localhost:8080/api/employeesPaginated`](#)
- [`GET http://localhost:8080/api/employeesPaginated?name=Xavier&page=0&size=5`](#)
- [`GET http://localhost:8080/api/employeesPaginated?page=0&size=2`](#)
- **Descripció:** Endpoint per cercar employees per nom amb paginació

#### 3. Obtenir Quantitat de Sales per Empleat
- [`GET http://localhost:8080/api/employees/1/salesAmount`](#)
- **Descripció:** Recupera la quantitat total de vendes per a un empleat específic.

#### 4. Afegir Venda a un Employee
- [`POST http://localhost:8080/api/employees/1/addSale/200`](#)
- **Descripció:** Afegeix una nova venda per a un empleat específic.

### Endpoints de Sales

#### 1. Llistar les Sales
- [`GET http://localhost:8080/api/sales`](#)
- [`GET http://localhost:8080/api/sales?amount=200`](#)
- **Descripció:** Obté totes les sales, opcionalment filtrades per quantitat

#### 2. Llistar les Sales de manera paginada
- [`GET http://localhost:8080/api/salesPaginated`](#)
- [`GET http://localhost:8080/api/salesPaginated?amount=200&page=0&size=5`](#)
- [`GET http://localhost:8080/api/salesPaginated?page=0&size=2`](#)
- **Descripció:** Endpoint per cercar sales per quantitat amb paginació
## Tests
### RegionJPAUnitTest

El test `RegionJPAUnitTest` està dissenyat per assegurar que les operacions CRUD (Create, Read, Update, Delete) per a l'entitat `Region` funcionin correctament a la capa de persistència de dades.

### StateJPAUnitTest

El test `StateJPAUnitTest` està dissenyat per assegurar que les operacions CRUD (Create, Read, Update, Delete) per a l'entitat `State` funcionin correctament a la capa de persistència de dades.

### EmployeeJPAUnitTest

El test `EmployeeJPAUnitTest` està dissenyat per assegurar que les operacions CRUD (Create, Read, Update, Delete) per a l'entitat `Employee` funcionin correctament a la capa de persistència de dades.
### SaleJPAUnitTest

El test `SaleJPAUnitTest` està dissenyat per assegurar que les operacions CRUD (Create, Read, Update, Delete) per a l'entitat `Sale` funcionin correctament a la capa de persistència de dades.

# Exercici 3

## Descripció
L'exercici 3 implica el desenvolupament d'un projecte amb Angular 16, orientat a mostrar les dades dels cotxes de la marca **Ford**. Aquesta aplicació web utilitza CarQueryAPI com a font principal per a obtenir la informació pertinent.

### Error de CORS
:warning: The CarQuery API does not contain the Access-Control-Allow-Origin header in its responses, so this package cannot be used within a browser. :warning:

Aquesta API no té els headers per tal de poder fer requests des d'Angular (o React usant axios), per això, l'alternativa que s'ha plantejat, és usar JQuery per fer aquestes consultes ja que JQuery no està directament lligat a usar **XMLHttpRequest**.

## Taula de continguts
- [Configuració](#configuració)
- [Servidor de Desenvolupament](#servidor-de-desenvolupament)
- [Angular CLI](#angular-cli)
- [Material Angular](#material-angular)
## Configuració

Per configurar l'aplicació Angular 16 per a la visualització de cotxes Ford al teu ordinador, segueix aquests passos:
1. **Instal·la Node.js:** Assegura't de tenir Node.js instal·lat al teu sistema. Pots descarregar-lo des del [lloc web oficial de Node.js](https://nodejs.org/).
2. **Instal·la Angular CLI:** Utilitza la següent comanda per instal·lar Angular CLI globalment al teu ordinador.     `npm install -g @angular/cli`

3. **Instal·la Dependències:** Navega fins al directori del projecte i executa la següent comanda per instal·lar les dependències del projecte.

    `npm install`

## Servidor de Desenvolupament

Per iniciar el servidor de desenvolupament, executa la següent comanda:

`ng serve`

Això iniciarà el servidor de desenvolupament i farà accessible l'aplicació a través de l'adreça [http://localhost:4200](http://localhost:4200/).

## Angular CLI

L'Angular CLI (Command Line Interface) proporciona eines poderoses per desenvolupar, provar i desplegar aplicacions Angular. Pots trobar més informació sobre les comandes disponibles [aquí](https://angular.io/cli).

## Material Angular
S'ha utilitzat el framework [Material Angular](https://material.angular.io/) per a dissenyar i estilitzar els components de l'aplicació.
