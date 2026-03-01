**Laboration: Databasdesign & Spring Boot Entities**

*Detektiv- och tjuvspel – Scotland Yard inspirerat*

Kurs: Databaser & Backend-utveckling

# **Introduktion**

I denna laboration ska du skapa databasen för ett flerspelarspel där detektiver jagar tjuvar på en karta. Systemet hanterar spelsessioner, spelarrörelser, ledtrådar och statistik.

Du kommer att:

* Skriva SQL-queries för att skapa alla tabeller i MySQL

* Skapa motsvarande JPA-entiteter i Spring Boot

* Förstå relationer mellan tabeller och hur de mappas i Java

*Läs igenom hela instruktionen innan du börjar. Tänk på att namnge klasser och fält enligt Java-konventioner (camelCase) även om kolumnnamnen i databasen använder underscore.*

# **Krav & förberedelser**

Innan du börjar, se till att du har följande på plats:

* MySQL 8.x installerat och igång

* Spring Boot-projekt skapat (använd Spring Initializr med beroenden: Spring Data JPA, MySQL Driver)

* application.yaml konfigurerat med din databasanslutning

* Jakarta Persistence (JPA) / Hibernate aktiverat

# **Modifiera application.yaml filen**
För att du ska kunna köra projektet lokalt på din dator behöver du först göra följande ändringar i application.yaml file
- Ersätt namnet för databasen med den du kör lokalt
- Ersätt lösenordet med den du använder

# **MySQL körande i Docker**
Om du kör MySql på en Docker container behöver du först navigera in i container miljön för att kunna göra dina queries.
Detta gör du genom att i terminalen köra skriptet
```bash docker ps bash ``` för att se din körande container
kopiera container_id och ersätt det i kommandot:
```bash docker exec -it container_id bash ```
Nu när du är inne i container miljön kan du logga in på MySql som vanligt med. Bara byta ut lösenordet
mysql -uroot -p mysupersecretpassword

# **Databasöversikt**

Nedan visas en översikt över alla tabeller och deras relationer.

**Tabeller i systemet:**

| Tabell | Beskrivning |
| :---- | :---- |
| player | Alla användare i systemet: detektiver, tjuvar och admins |
| games | Spelsessioner med status, tidsstämplar och vinnare |
| map | Spelkartor med dimensioner |
| location | Platser/noder på en karta (gator, byggnader, gömställen) |
| route | Förbindelser mellan platser (kanter i grafen) |
| game\_player | Kopplingstabell – vilka spelare deltar i vilket spel |
| move | Rörelshistorik – varje spelares drag per omgång |
| clue | Ledtrådar som tjuven lämnar och detektiver hittar |
| catch\_attempt | Gripningsförsök av detektiver |
| player\_stats | Samlad statistik per spelare |

**Relationsöversikt:**

* En map har många locations och routes

* En location tillhör en map

* En route kopplar ihop två locations (from\_location → to\_location)

* En games refererar till en map

* game\_player kopplar samman games och player (många-till-många)

* move, clue och catch\_attempt refererar till games och player

* player\_stats har en ett-till-ett-relation med player

# **Del 1 – Skapa tabellerna i MySQL**

Skriv en CREATE TABLE-sats för varje tabell nedan. Specifikationerna beskriver vilka kolumner som ska finnas, deras datatyper och begränsningar. Notera alla primärnycklar (primary keys) och främmande nycklar (foreign keys).

* Skapa tabellerna i den ordning de presenteras, eftersom senare tabeller refererar till tidigare skapade tabeller.*

## **Tabell 1: player**

Lagrar alla registrerade användare i spelet.

| Kolumn | Datatyp | Begränsningar / Beskrivning |
| :---- | :---- | :---- |
| id | INT | Primärnyckel, auto-increment |
| username | VARCHAR(50) | NOT NULL, UNIQUE |
| email | VARCHAR(100) | NOT NULL, UNIQUE |
| password\_hash | VARCHAR(255) | NOT NULL |
| role | ENUM | Tillåtna värden: 'detective', 'thief', 'admin' – NOT NULL |
| created_at | TIMESTAMP | DEFAULT CURRENT\_TIMESTAMP |
| last_login | TIMESTAMP | Kan vara NULL |

## **Tabell 2: game**

Representerar en spelsession.

| Kolumn | Datatyp | Begränsningar / Beskrivning |
| :---- | :---- | :---- |
| id | INT | Primärnyckel, auto-increment |
| status | ENUM | Värden: 'waiting', 'active', 'completed', 'abandoned' – DEFAULT 'waiting' |
| map_id | INT | Refererar till map(id) |
| started_at | TIMESTAMP | Kan vara NULL |
| ended_at | TIMESTAMP | Kan vara NULL |
| winner_role | ENUM | Värden: 'detective', 'thief' – kan vara NULL |
| created_at | TIMESTAMP | DEFAULT CURRENT\_TIMESTAMP |

## **Tabell 3: map**

Beskriver en spelkarta med ett rutmönster.

| Kolumn | Datatyp | Begränsningar / Beskrivning |
| :---- | :---- | :---- |
| id | INT | Primärnyckel, auto-increment |
| name | VARCHAR(100) | NOT NULL |
| grid_width | INT | NOT NULL |
| grid_height | INT | NOT NULL |
| description | TEXT | Kan vara NULL |

## **Tabell 4: location**

En plats/nod på en karta. Varje plats tillhör en karta.

| Kolumn | Datatyp | Begränsningar / Beskrivning |
| :---- | :---- | :---- |
| id | INT | Primärnyckel, auto-increment |
| map_id | INT | NOT NULL – Refererar till map(id) |
| name | VARCHAR(100) | NOT NULL |
| coord_x | INT | NOT NULL |
| coord_y | INT | NOT NULL |
| type | ENUM | Värden: 'street', 'building', 'hideout', 'checkpoint' – DEFAULT 'street' |

## **Tabell 5: route**

En förbindelse (kant) mellan två platser på kartan. Anger transportmedel och avstånd.

| Kolumn | Datatyp | Begränsningar / Beskrivning |
| :---- | :---- | :---- |
| id | INT | Primärnyckel, auto-increment |
| map_id | INT | NOT NULL – Refererar till map(id) |
| from_location | INT | NOT NULL – Refererar till location(id) |
| to_location | INT | NOT NULL – Refererar till location(id) |
| transport | ENUM | Värden: 'foot', 'vehicle', 'subway' – DEFAULT 'foot' |
| distance | INT | DEFAULT 1 |

## **Tabell 6: game_player**

Kopplingstabell som anger vilka spelare som deltar i ett spel och i vilken roll.

| Kolumn | Datatyp | Begränsningar / Beskrivning |
| :---- | :---- | :---- |
| id | INT | Primärnyckel, auto-increment |
| game_id | INT | NOT NULL – Refererar till game(id) |
| player_id | INT | NOT NULL – Refererar till player(id) |
| role | ENUM | Värden: 'detective', 'thief' – NOT NULL |
| is_caught | BOOLEAN | DEFAULT FALSE |
| joined_at | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP |

## **Tabell 7: move**

Lagrar varje spelares rörelse under en omgång. from\_loc kan vara NULL vid spelets start.

| Kolumn | Datatyp | Begränsningar / Beskrivning |
| :---- | :---- | :---- |
| id | INT | Primärnyckel, auto-increment |
| game_id | INT | NOT NULL – Refererar till game(id) |
| player_id | INT | NOT NULL – Refererar till player(id) |
| from_loc | INT | Kan vara NULL – Refererar till location(id) |
| to_loc | INT | NOT NULL – Refererar till location(id) |
| transport | ENUM | Värden: 'foot', 'vehicle', 'subway' – DEFAULT 'foot' |
| turn_number | INT | NOT NULL |
| moved_at | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP |

## **Tabell 8: clue**

Ledtrådar kopplade till ett spel och en plats. found\_by anger vilken detektiv som hittade ledtråden.

| Kolumn | Datatyp | Begränsningar / Beskrivning |
| :---- | :---- | :---- |
| id | INT | Primärnyckel, auto-increment |
| game_id | INT | NOT NULL – Refererar till games(id) |
| location_id | INT | NOT NULL – Refererar till location(id) |
| found_by | INT | Kan vara NULL – Refererar till player(id) |
| description | TEXT | Kan vara NULL |
| is_found | BOOLEAN | DEFAULT FALSE |
| created_at | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP |
| found_at | TIMESTAMP | Kan vara NULL |

## **Tabell 9: catch\_attempt**

Registrerar varje gripningsförsök: vilken detektiv försökte gripa vilken tjuv, på vilken plats, och om det lyckades.

| Kolumn | Datatyp | Begränsningar / Beskrivning |
| :---- | :---- | :---- |
| id | INT | Primärnyckel, auto-increment |
| game_id | INT | NOT NULL – Refererar till game(id) |
| detective_id | INT | NOT NULL – Refererar till player(id) |
| thief_id | INT | NOT NULL – Refererar till player(id) |
| location_id | INT | NOT NULL – Refererar till location(id) |
| successful | BOOLEAN | NOT NULL |
| attempted_at | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP |

## **Tabell 10: player\_stats**

En-till-en-relation med player. Lagrar ackumulerad statistik för varje spelare.

| Kolumn | Datatyp | Begränsningar / Beskrivning |
| :---- | :---- | :---- |
| id | INT | Primärnyckel, auto-increment |
| player_id | INT | NOT NULL, UNIQUE – Refererar till player(id) |
| games_played | INT | DEFAULT 0 |
| games_won | INT | DEFAULT 0 |
| times_caught | INT | DEFAULT 0 |
| thieves_caught | INT | DEFAULT 0 |

# **Del 2 – Skapa JPA-entiteter i Spring Boot**

För varje tabell du skapade i Del 1 ska du nu skriva en motsvarande Java-klass annoterad med JPA-annotationer. Varje entitet ska mappas mot sin tabell och alla relationer ska uttryckas med rätt annotations.

*Kom ihåg annotationerna:* 
- @Entity på varje klass
- @Table(name \= "...") behövs inte om du följt mina anvisningar på namngivningen
- @Id och @GeneratedValue(strategy \= GenerationType.IDENTITY) på primärnyckelfältet

**Grundläggande annotationer att använda:**

* @Entity – markerar klassen som en JPA-entitet

* @Table(name \= "tabellnamn") – mappar till rätt tabell

* @Id – markerar primärnyckelfältet

* @GeneratedValue(strategy = GenerationType.IDENTITY) – auto-increment

* @Column(name = "kolumnnamn") – mappar ett fält till en kolumn

* @Enumerated(EnumType.STRING) – för ENUM-kolumner

* @CreationTimestamp – för automatiska created_at-fält

* @ManyToOne / @OneToMany – för relationer

* @OneToOne – för en-till-en-relationer (t.ex. PlayerStats ↔ Player)

* @JoinColumn(name = "fk_kolumn") – anger vilken kolumn som är foreign key

## **Entitet 1: Player**

Motsvara tabellen player. Skapa ett enum PlayerRole med värdena DETECTIVE, THIEF och ADMIN.

| Fält (Java) | Typ (Java) | Mappas mot kolumn |
| :---- | :---- | :---- |
| id | Long / Integer | id |
| username | String | username |
| email | String | email |
| passwordHash | String | password\_hash |
| role | PlayerRole (enum) | role – använd @Enumerated(EnumType.STRING) |
| createdAt | LocalDateTime / Instant | created_at |
| lastLogin | LocalDateTime / Instant | last_login (nullable) |

## **Entitet 2: Game**

Motsvara tabellen games. Skapa enum GameStatus (WAITING, ACTIVE, COMPLETED, ABANDONED) och WinnerRole (DETECTIVE, THIEF).

| Fält (Java) | Typ (Java) | Mappas mot kolumn / Relation |
| :---- | :---- | :---- |
| id | Long | id |
| status | GameStatus (enum) | status |
| map | Map | @ManyToOne → map_id |
| startedAt | LocalDateTime | started_at (nullable) |
| endedAt | LocalDateTime | ended_at (nullable) |
| winnerRole | WinnerRole (enum) | winner_role (nullable) |
| createdAt | LocalDateTime | created\_at |

## **Entitet 3: Map**

Motsvara tabellen map. Obs: Map är ett reserverat ord i Java – döp klassen till GameMap eller liknande.

| Fält (Java) | Typ (Java) | Mappas mot kolumn |
| :---- | :---- | :---- |
| id | Long | id |
| name | String | name |
| gridWidth | Integer | grid_width |
| gridHeight | Integer | grid_height |
| description | String | description (nullable) |

## **Entitet 4: Location**

Motsvara tabellen location. Skapa enum LocationType (STREET, BUILDING, HIDEOUT, CHECKPOINT).

| Fält (Java) | Typ (Java) | Mappas mot kolumn / Relation |
| :---- | :---- | :---- |
| id | Long | id |
| map | GameMap | @ManyToOne → map\_id |
| name | String | name |
| coordX | Integer | coord\_x |
| coordY | Integer | coord\_y |
| type | LocationType (enum) | type – @Enumerated(EnumType.STRING) |

## **Entitet 5: Route**

Motsvara tabellen route. Skapa enum TransportType (FOOT, VEHICLE, SUBWAY) – denna enum kan återanvändas i Move.

| Fält (Java) | Typ (Java) | Mappas mot kolumn / Relation |
| :---- | :---- | :---- |
| id | Long | id |
| map | GameMap | @ManyToOne → map\_id |
| fromLocation | Location | @ManyToOne → from_location |
| toLocation | Location | @ManyToOne → to_location |
| transport | TransportType (enum) | transport |
| distance | Integer | distance |

## **Entitet 6: GamePlayer**

Kopplingstabell mellan Game och Player. Skapa enum GameRole (DETECTIVE, THIEF).

| Fält (Java) | Typ (Java) | Mappas mot kolumn / Relation |
| :---- | :---- | :---- |
| id | Long | id |
| game | Game | @ManyToOne → game_id |
| player | Player | @ManyToOne → player_id |
| role | GameRole (enum) | role |
| isCaught | Boolean | is_caught |
| joinedAt | LocalDateTime | joined_at |

## **Entitet 7: Move**

Lagrar ett drag i spelet. fromLoc kan vara null (spelets start). Återanvänd TransportType från Route.

| Fält (Java) | Typ (Java) | Mappas mot kolumn / Relation |
| :---- | :---- | :---- |
| id | Long | id |
| game | Game | @ManyToOne → game_id |
| player | Player | @ManyToOne → player_id |
| fromLoc | Location | @ManyToOne → from_loc (nullable) |
| toLoc | Location | @ManyToOne → to_loc |
| transport | TransportType (enum) | transport |
| turnNumber | Integer | turn_number |
| movedAt | LocalDateTime | moved_at |

## **Entitet 8: Clue**

En ledtråd kopplad till ett spel och en plats. foundBy är null tills en detektiv hittar den.

| Fält (Java) | Typ (Java) | Mappas mot kolumn / Relation |
| :---- | :---- | :---- |
| id | Long | id |
| game | Game | @ManyToOne → game_id |
| location | Location | @ManyToOne → location_id |
| foundBy | Player | @ManyToOne → found_by (nullable) |
| description | String | description (nullable) |
| isFound | Boolean | is_found |
| createdAt | LocalDateTime | created_at |
| foundAt | LocalDateTime | found_at (nullable) |

## **Entitet 9: CatchAttempt**

Registrerar ett gripningsförsök. Notera att både detective och thief är relationer till Player.

| Fält (Java) | Typ (Java) | Mappas mot kolumn / Relation |
| :---- | :---- | :---- |
| id | Long | id |
| game | Game | @ManyToOne → game_id |
| detective | Player | @ManyToOne → detective_id |
| thief | Player | @ManyToOne → thief_id |
| location | Location | @ManyToOne → location_id |
| successful | Boolean | successful – NOT NULL |
| attemptedAt | LocalDateTime | attempted_at |

## **Entitet 10: PlayerStats**

En-till-en-relation med Player. Använd @OneToOne och @JoinColumn.

| Fält (Java) | Typ (Java) | Mappas mot kolumn / Relation |
| :---- | :---- | :---- |
| id | Long | id |
| player | Player | @OneToOne → player_id (UNIQUE) |
| gamesPlayed | Integer | games_played – DEFAULT 0 |
| gamesWon | Integer | games_won – DEFAULT 0 |
| timesCaught | Integer | times_caught – DEFAULT 0 |
| thievesCaught | Integer | thieves_caught – DEFAULT 0 |

# **Del 3 – Tips och vanliga misstag**

## **SQL-tips**

* Skapa tabellerna i rätt ordning: map → location → route → player → games → game\_player → move → clue → catch\_attempt → player\_stats

* Kom ihåg att ange NOT NULL och DEFAULT-värden där tabellspecifikationen anger det

* ENUM-typer i MySQL deklareras direkt i kolumndefinitionen: ENUM('value1', 'value2')

* Testa dina tabeller med INSERT-satser och kontrollera att foreign key-begränsningarna fungerar

## **Spring Boot / JPA-tips**

* Döp klassen till GameMap istället för Map för att undvika kollision med java.util.Map

* Använd @Column(nullable \= true) explicit för fält som tillåter NULL

* När en tabell har två foreign keys till samma tabell (t.ex. catch\_attempt.detective\_id och thief\_id), behöver du @JoinColumn med olika namn på varje fält

* @CreationTimestamp (Hibernate) sätter automatiskt värdet vid INSERT – användbart för created\_at-fält

* För Boolean-fält som is\_caught och successful, använd Java-typen Boolean (inte boolean) om fältet kan vara null

* Lägg till en no-args-konstruktor i varje entitet (krav för JPA)

## **Felsökning**

| Problem | Lösning |
| :---- | :---- |
| Foreign key-fel vid CREATE TABLE | Kontrollera att den refererade tabellen existerar och att datatypen matchar exakt |
| Entitet sparas inte i databasen | Kontrollera att @Entity och @Table finns, och att du anropar repository.save() |
| ENUM-värden matchar inte | Se till att Java-enumens namn matchar exakt det som lagras i databasen (EnumType.STRING) |
| NullPointerException vid lazy loading | Antingen hämta relationen inom en transaktion eller använd FetchType.EAGER |
| Dubblettfel på UNIQUE-kolumner | Kontrollera att du inte försöker spara samma username/email/player\_id två gånger |

# **Inlämning**

Lämna in följande:

* En .sql-fil med alla CREATE TABLE-satser

* Alla entitetsklasser (.java) i ett Spring Boot-projekt (eller separata filer)

* Eventuella enum-klasser du skapat

*💡 Kontrollera att din SQL-fil går att köra i MySQL utan fel från topp till botten, och att Spring Boot-applikationen startar utan undantag med ddl-auto=validate eller ddl-auto=none.*

