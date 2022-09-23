A cél egy álláskereső alkalmazás létrehozása. 
A feladatot Java 11 nyelven, Spring framework segítségével valósítsa meg. Az adatbázishoz célszerű inmemory adatbázist használni vagy file alapú megoldást, melynek tartalmaznia kell már meglévő adatokat is! Inmemory database esetén kezdeti adatfeltöltést is tartalmazzon a megoldás.
Követelmények: 
1. Az alkalmazás biztosítson lehetőséget kliensalkalmazások regisztrációjára (POST /client). A kliens átadja a nevét (validáció: max 100 karakter), e-mail címét (validáció: érvényes email cím formátum, bármilyen regexp használatával, valamint egyediség ellenőrzése). A responseban a szerver egy api kulcsot ad vissza UUID formátumban. 
2. Az alkalmazás biztosítson lehetőséget állások létrehozására (POST /position). A kliens átadja az állás megnevezését (validáció: max 50 karakter), a munkavégzés földrajzi helyét (validáció: max 50 karakter). A szerver első lépésben ellenőrzi az api kulcs érvényességét. Nem érvényes api kulcs esetén hibaüzenettel tér vissza. A szerver mentse el az állást, majd térjen vissza egy URL-lel a responseban, hogy milyen oldalon érhető el a pozició. 
3. Az alkalmazás biztosítson lehetőséget állások keresésére (GET /position/search). A kliens átadja a keresett keywordöt (pl.: "finance", validáció: max 50 karakter) valamint a lokációt (pl.: "london", validáció: max 50 karakter). A szerver első lépésben ellenőrzi az api kulcs érvényességét. Nem érvényes api kulcs esetén hibaüzenettel tér vissza. 
Érvényes api kulcs esetén az átadott adatokkal bekérdez az adatbázisban tárolt állások. 
Találatnak számít a részleges egyezés is pl: ha az állás megnevezése: “Java backend developer” és a keresésben a feltétel annyi, hogy “developer” akkor az találat. Ha a keresés sikerrel járt a kliens számára egy URL listával kell visszatérni a hírdetésekhez tartozó URL-el. 
4.  A keresési eredmények megnyitásához pedig szükséges implementálni egy GET (/position/id) kérést.
A szerver validációs hibák esetén egységes hibatípussal térjen vissza, részletezve, hogy milyen mezőkkel milyen validációs hiba történt. 
Az API réteget válassza el az adatelérési rétegtől.
Az alkalmazáshoz swagger-ui használata nem kötelező, de ajánlott.
A fejlesztés után soroljon fel továbbfejlesztési lehetőségeket ezzel a projekttel kapcsolatban, hogy teljes mértékben production ready alkalmazás legyen és az üzemeltetés is elfogadja tőlünk ezt az alkalmazást.
Készítsen rövid leírást az alkalmazásról, hogyan és milyen eszközökkel lehet bekonfigurálni és futtatni.

5. api kulcs: lehetne kódolva tárolni, limiteket beállítani, lejárati időt beállítani (és valamilyen módon törölni lejárat után).
6. kliens: törlés is lehetne, esetleg valamennyi inaktivitás után értesítő levél/automatikus törlés.
7. állások: lehetne leírás is, esetleg azokat létrehozó kliens jelölése is / vagy cég/department.
8. security: api kulcs mellett lehet springsecurity/oauth is.
9. tesztek: unit, integration mindenképpen, security, environment.
10. validateek: ha szétbontom jobban őket (pl emailt ellenőrző regexpet) lehet pontosabb visszajelzést is adni, hogy mi nem stimmel vele.

Application.properties-ben lehet beállítani milyen adatbázisból dolgozzon (jelenleg h2, illetve mysql lehetőségek), .env-ben lehet beállítani környezeti változókat.

/client - needs json input clientName and clientEmail

/position - (needs json input jobPosition and location) and apiKey as queryparam

/position/search - needs keyWord, location, apiKey as queryparams. keyWord and location are not needed, can be empty or blank. If both are, then returns all positions.

/position/{id} - needs id

invalid inputs result in 400 response with error description.


Develop branch has unit tests for saveClient and a different approach for initial db population.

ApiKeyInHashMap branch has the api keys in a hashmap instead of db. The hash map does not contain the initial db populations api keys (I did try to save them, but it seems the ApplicationRunner runs before the service is really created for runtime, and at runtime the hashmap is created again, deleting the previously populated one). Plan to move the initial population to the actual service from of the ApplicationRunner. Also this way the api keys will be deleted upon application restart, even if the db is switched from h2 to some sql server and from create-drop to update.
