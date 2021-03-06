testy
=====
Testy innholder et lite (og høyst ufullstendig) bibliotekssystem. Det innholder enkle modellklasser som Bok, Laaner, Utlaan, BokEksemplar og Bibliotek. UtlaanManager er ment å inneholde databaserelaterte metoder (Om systemet hadde hatt en database ville det typisk vært sql-statements her) og UtlaanService, BibliotekService osv inneholder funksjonalitet for å låne, levere og bestille bøker, for å sende mail til lånere eller varsle bibliotek via ftp. StoreUtil lagrer, fjerner og get-er objekter. (Igjen ville dette i en ordentlig løsning vært mot en database. I vårt tilfelle lagres alle objektene i en liste, og save, remove og get-ing opererer på denne listen). Metodene i serviceklassene er ofte komplekse, er dårlig skrevet og har vanskelige avhengigheter til andre klasser.
Under er det skissert oppgaver for å skrive tester og forbedre koden. 

1. beregnForfallsdato i FristService er forferdelig. Skriv tester som dekker alle tilfeller (husk edge-tilfellene), og refaktorer.

2. Skriv en test for sendForfallspaaminnelse i UtlaanService

3. Se på metoden sendMailTilAlleLaanere i BibliotekService, og tenk at du skal lage funksjonalitet for mailtypen "boktips". Denne mailtypen skal som for "arrangement"-typen bruke maalgruppe slik at man ikke får mail om bøker man ikke er interessert i. 
  * Skriv tester for eksisterende funksjonalitet. Pass på at du kan erstatte mailService med en testimplementasjon, mock eller annet slik at du ikke faktisk sender mail. Tilsvarende for UtlaanManager er det kanskje enklere å klare å kontrollere hva metoden getForfalteUtlaan(bibliotek) returnerer.
  * Implementer den nye funksjonaliteten på testdrevet vis 
  * Refaktorer metoden slik at innholdet splittes i flere metoder over flere klasser. Kanskje sendForfallspaaminnelse i UtlaanService har noe felles med sendMailTilAlleLaanere?
  
4. Refaktorere finnLedigBokEksemplar i BokService og getLedigBokeksemplar i bibliotekService til felles kode
  * Effektanalyse
  * Skrive tester
  * Refaktorere

5. Om du bruker intelliJ, analyser avhengigheter med depenedency matrix. (Høyreklikk på prosjektrota i project-view til venstre, velg analyze -> dependency matrix). Les mer om dependency-analyse i intelliJ [her](http://www.jetbrains.com/idea/features/dependency_analysis.html). Se om du kan løse opp i noen av avhengighetene.
   *  Bør Utlaan avhenge av FristService?
   *  Bør manager-pakken avhenge av service-pakken?
