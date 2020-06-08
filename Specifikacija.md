# Workout generator

## Sadržaj:
* [Motivacija](#motivacija)
* [Pregled-problema](#pregled-problema)
* [Metodologija rada](#metodologija-rada)
  * [Ulazi u sistem](#ulazi-u-sistem)
  * [Izlazi iz sistema](#izlazi-iz-sistema)
  * [Baza znanja](#baza-znanja)
* [Primer rezonovanja](#primer-rezonovanja)
* [Spisak pravila](#spisak-pravila)
* [Reference](#reference)

## Motivacija:

U današnje vreme, sa razvojem tehnologije i pametnih telefona, pristup internetu i društvenim mrežama je postala svakodnevica za veliki deo populacije. U sklopu mnogobrojnih aplikacija dolaze do izražaja i one koje se vezuju za fizičku aktivnost, poput brojanja koraka, brojača unetih kalorija i makronutrijenata, kao i onih koje generišu treninge i demonstriraju vežbe za različite grupe mišića. Korisne su kako za početnike, tako i za rekreativce i sportiste kojima ponestane ideja za trening ili žele da isprobaju nešto novo. Takođe, njihova upotrebljivost se povećava u spletu nesretnih okolnosti usled kojih su ljudi primorani da borave kod kuće.

## Pregled-problema:

Postojeće aplikacije se razlikuju kako po očekivanim ulaznim podacima, tako i po izlaznim podacima koje generišu. U većini slučajeva korisnik bira nivo fizičke spremnosti, koju grupu mišića bi želeo da pogodi, koju opremu poseduje i koliko vremena ima na raspologanju. Kao rezultat dobije ili spisak vežbi koje bi mogao da radi, ili generisan trening u skladu sa zahtevima. Brojevi ponavljanja su mahom predefinisani u zavisnosti od nivoa spremnosti korisnika, a vežbe na izlazu mogu, ali i ne moraju da uključe unetu opremu. 

Moja ideja je da ukombinujem oba pristupa, gde bi korisnik na izlazu dobio predloge vežbi od kojih bi mogao da odabere one koje bi želeo da radi, kao i da pokrene nasumičan odabir vežbi. Takođe, zahtevao bih od korisnika da unese odgovarajuću opremu koju poseduje, nivo spremnosti korisnika i telesnu masu. Ove podatke će, naravno, moći i kasnije da dopuni ili izmeni.

Nakon što korisnik odabere neki trening, popunjavaće anketu na osnovu koje ću pratiti njegov napredak i po potrebi mu povećati opterećenje i broj ponavljanja. Kad se korisnik odabere neki trening i oceni ga, gledao bih da u sledećem treningu ne ponudim vežbe iz prošlog treninga, ne bi li uneo dinamiku i omogućio pogađanje različitih mišićnih grupa.  

## Metodologija-rada:

### Ulazi-u-sistem:
Kako bi korisniku ponudio odgovarajuće rešenje, zahtevaću sledeće podatke:
Oprema koju korisnik poseduje:
-	Nikakva oprema (vežbe sa sopstvenom težinom) – podrazumevano
-	Šipka sa tegovima
-	Bučice sa tegovima
-	Rusko  zvono
-	Šipka za zgibove
- 	Šipka za propadanja
-	Vijača

Nivo spremnosti korisnika:
-	Početnik – trenira svega nekoliko meseci, nije upoznat sa većim brojem vežbi.
-	Rekreativac – solidno poznavanje vežbi, trenira oko godinu dana.
-	Napredni sportista – nekoliko godina u treningu, isprobavao različite vežbe.

Telesna masa korisnika.

(Na osnovu nivoa spremnosti i telesne mase korisnika se određuje optrećenje i broj ponavljanja za određene vežbe).

Tip treninga koji bi korisnik želeo da uradi:
-	Snaga.
-	Kružni trening (kardio).
-	Kombinacija snage i kružnog treninga.

Nakon odabira odgovarajućeg treninga, popune se sledeće ankete:
-	Stepen zamora nakon treninga, ocena od 1-10 – Borgova skala.
-	Koliko je zadovoljan treningom, ocena od 1-10.

### Izlazi-iz-sistema:
-	Određen broj vežbi, od kojih korisnik može da odabere one koje mu pogoduju.
-	Nasumično izgenerisan splet vežbi.

### Baza-znanja:
Postoji ogroman broj vežbi koje se mogu preporučiti korisniku. Sa povećanjem opreme se povećava i broj vežbi. Kako ne bih morao da populišem bazu podataka tolikim vežbama pronašao sam REST API - wger Workout Manager koji sadrži veliki broj vežbi sa odgovarajućom opremom. Doduše, ovde ne postoje vežbe za kondiciju, tako da bih gledao da omogućim administratoru da njih populiše u bazi podataka.

Pored vežbi, akcenat bi bio na čuvanju podataka o korisnicima. Pamtiću korisnikovu opremu, telesnu masu, nivo spremnosti, treninge koje je odradio i ocene tih treninga i slično.

Za pravilan rad sistema bih trebao da znam kako ću skalirati ponavljanja i optrećenja, tako da će pri registraciji će korisnik unositi telesnu masu i nivo spremnosti. Vežbe sa sopstvenom težinom će biti podrazumevane tako da nije neophodno uneti nikakvu opremu. Na osnovu anketa koje korisnik popunjava će se pratiti njegov napredak i po potrebi povećati ili smanjiti broj ponavljanja i opterećenja.

## Primer-rezonovanja:
Recimo da imamo scenario u kom je iskusniji korisnik prosledio sledeće činjenice:
  - Na prošlom treningu je vežbao donji deo tela.
  - Korisnik je od opreme odabrao šipku za zgibove i šipku sa tegovima.
  - Korisnikova telesna masa je 70kg.
  - Korisnikov nivo spremnosti je rekreativac (srednji nivo).
  - Opredelio se za kombinovan trening snage i kondicije.
  - U prethodnoj anketi je tražio povećanje vremenskog intervala za kružni trening.

Na osnovu ovih činjenica, pokreće se rezoner i redom sledeća pravila se okidaju:
- Pravila za određivanje sledeće dve grupe mišića koju bi korisnik trebao da trenira:
  - Na prethodnom treningu je korisnik vežbao gornji deo tela, nasumično odaberi dve grupe mišića za donji deo tela.

- Pravila koja određuju intenzitet treninga na osnovu nivoa spremnosti korisnika i korisničkih faktora:
  - Korisnik je srednjeg nivoa spremnosti, za kružne treninge će mu vremenski interval rada biti 35 sekundi (30 + 5 sekundi uvećanja). Broj vežbi koje bi trebao da uradi po treningu je 5, a broj ponavljanja između 6 i 10 (uračunavaju se korisnički faktori dobijeni na osnovu anketa).
  
- Pravila za određivanje intenziteta na osnovu vrste treninga i korisničkih faktora:
  - Tip treninga koji je korisnik odabrao je kombinacija snage i kružnog treninga i broj vežbi je određen na osnovu prethodne grupe pravila. Broj vežbi za snagu i kružni trening prepolovimo, množimo sa odgovarajućim faktorom i zaokružimo. Recimo da smo dobili 2 vežbe. Za snagu će raditi 5 serija, a za kružni trening 8 intervala u 2 runde. Odmaraće 2 minuta između rundi u kružnom treningu i 1 minut između vežbi snage.


Od svih vežbi, korisnik će za svaki artikal opreme dobiti izlistano po nekoliko vežbi. Moći će da odabere neke koje bi želeo da radi i time kreira svoj trening. Sa druge strane može pokrenuti i nasumičan odabir čime će se iz skupa odabrati određen broj vežbi na osnovu prethodnih pravila.

U slučaju nasumičnog odabira vežbi, za svaku od vežbi snage se odredi opterećenje ukoliko se vežba izvodi upotrebom šipke, bučica ili ruskog zvona. Recimo da je jedna od vežbi snage zadnji čučanj šipkom:
  - Korisnik je srednjeg nivoa spremnosti, vežba se izvodi sa šipkom - skaliraj opterećenje na 45-65% telesne mase.

Kad se korisnik opredeli za neki trening i doda ga u kolekciju svojih treninga, nudi mu se mogućnost da popuni odgovarajuće ankete. Recimo da je korisnik ocenio trening kao izuzetno težak (9/10) i požalio se na opterećenje. U tom slučaju se okida pravilo iz sledeće grupe pravila:

- Pravila za modifikaciju korisničkih faktora na osnovu ocene.
  - Ocena je veća od 8, požalio se na opterećenje - umanji faktor za opterećenje za 0.1.

## Spisak-pravila:

Pravila su odvojena u posebne datoteke, u zavisnosti od logike na koju se odnose. U načelu, postoje tri grupe pravila:
  1. Pravila koja se odnose na pravljenje treninga (agenda-group "workout")
  2. Pravila koja se odnose na ocenjivanje treninga (agenda-group "review")
  3. Pravila koja za svaku vežbu snage određuju opterećenja (agenda-group "workLoad")

U nastavku su navedena sva pravila po njihovim grupama.

- Pravila za određivanje sledeće dve grupe mišića koju bi korisnik trebao da trenira (agenda-group "workout"):
	- Na prethodnom treningu je korisnik vežbao gornji deo tela, nasumišno odaberi dve grupe mišića za donji deo tela.
	- Na prethodnom treningu je korisnik vežbao donji deo tela, nasumično odaberi dve grupe mišića za gornji deo tela.


- Pravila koja određuju intenzitet treninga na osnovu nivoa spremnosti korisnika i korisničkih faktora (agenda-group "workout"):
	- Korisnik je početnik - vremenski intervali rada će biti postavljeni na 20 sekundi, a odmor na 10 sekundi. Radiće 4 vežbe po treningu. Broj ponavljanja će biti između 4 i 8 (uračunaj odgovarajuće faktore dobijene na osnovu anketa).
	- Korisnik je rekreativac - vremenski intervali rada će biti postavljeni na 30 sekundi, a odmor na 15 sekundi. Radiće 5 vežbi po treningu. Broj ponavljanja će biti između 6 i 10 (uračunaj odgovarajuće faktore dobijene na osnovu anketa).
	- Korisnik je napredan - vremenski intervali rada će biti postavljeni na 40 sekundi, a odmor na 20 sekundi. Radiće 6 vežbi po treningu. Broj ponavljanja će biti između 8 i 12 (uračunaj odgovarajuće faktore dobijene na osnovu anketa).


- Pravila za određivanje intenziteta na osnovu vrste treninga (agenda-group "workout") -> Izvrše se nakon prethodne grupe pravila, na osnovu uslova da se  promenio broj vežbi po treningu:
	- Odabran je kružni trening - broj intervala će biti 8. Broj rundi će biti 4. Odmaraće 2 minuta između rundi.
	- Odabran je trening snage - broj serija će biti 3. Odmaraće 2 minuta između vežbi.
	- Odabrana je kombinacija snage i kružnog treninga - prepolovi broj vežbi po treningu. Postavi broj serija na 5. Broj intervala za kružni trening biti 8. Broj rundi će biti 2. Odmaraće 1 minut između vežbi snage, a 2 minuta između rundi u kružnom treningu.
	
	
- Pravila koja određuju opterećenja za prosleđenu vežbu snage u zavisnosti od nivoa spremnosti korisnika i opreme kojom se vežba izvodi (agenda-group "workLoad"):
	- Korisnik je početnog nivoa spremnosti, vežba se izvodi šipkom - skaliraj opterećenje za tu vežbu na 35-50% telesne mase.
	- Korisnik je početnog nivoa spremnosti, vežba se izvodi bučicama - skaliraj opterećenje za tu vežbu na 8-12% telesne mase.
	- Korisnik je početnog nivoa spremnosti, vežba se izvodi ruskim zvonom - skaliraj opterećenje za tu vežbu na 15-20% telesne mase.
	- Korisnik je srednjeg nivoa spremnosti, vežba se izvodi šipkom - skaliraj opterećenje za tu vežbu na 45-65% telesne mase.
	- Korisnik je srednjeg nivoa spremnosti, vežba se izvodi bučicama - skaliraj opterećenje za tu vežbu na 12-16% telesne mase.
	- Korisnik je srednjeg nivoa spremnosti, vežba se izvodi ruskim zvonom - skaliraj opterećenje za tu vežbu na 22-28% telesne mase.
	- Korisnik je naprednog nivoa spremnosti, vežba se izvodi šipkom - skaliraj opterećenje za tu vežbu na 55-70% telesne mase.
	- Korisnik je naprednog nivoa spremnosti, vežba se izvodi bučicama - skaliraj opterećenje za tu vežbu na 15-20% telesne mase.
	- Korisnik je naprednog nivoa spremnosti, vežba se izvodi ruskim zvonom - skaliraj opterećenje za tu vežbu na 24-32% telesne mase. 
	

- Pravila za modifikaciju korisničkih faktora na osnovu ocene (agenda-group "review").
	- Ocena je manja od 3, požalio se na broj ponavljanja - uvećaj faktor za ponavljanja.
	- Ocena je manja od 3, požalio se na opterećenje - uvećaj faktor za opterećenje.
	- Ocena je manja od 3, požalio se na vr. interval - uvećaj faktor za vr. interval rada.
	- Ocena je veća od 8, požalio se na broj ponavljanja - umanji faktor za ponavljanja.
	- Ocena je veća od 8, požalio se na opterećenje - umanji faktor za opterećenje.
	- Ocena je veća od 8, požalio se na vr. interval - umanji faktor za vr. interval rada.


## Reference:
Nisam pronašao literaturu, ali bih naveo u nastavku neke od aplikacija koje su me inspirisale da pokušam da napravim svoje rešenje:
-	https://wger.de/en/software/api - API iz kog sam izvukao pojedine vežbe.
-	http://www.thewodgenerator.com/category/wod-generator-by-equipment/ - aplikacija koja generiše trening na osnovu odabrane opreme.
-	https://www.jefit.com/exercises/ - aplikacija koja prikaže vežbe na osnovu opreme.


