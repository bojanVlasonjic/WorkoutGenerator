# Workout generator

## Sadržaj:
* [Motivacija](#motivacija)
* [Pregled-problema](#pregled-problema)
* [Metodologija rada](#metodologija-rada)
  * [Ulazi u sistem](#ulazi-u-sistem)
  * [Izlazi iz sistema](#izlazi-iz-sistema)
  * [Baza znanja](#baza-znanja)
* [Primer rezonovanja](#primer-rezonovanja)
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
-	Benč klupa
-	Bučice sa tegovima
-	Rusko  zvono
-	Šipka za zgibove
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
-	Korisnik je od opreme odabrao šipku za zgibove i šipku sa tegovima.
-	Korisnikova telesna masa je 70kg.
-	Korisnikov nivo spremnosti je rekreativac (srednji nivo).
-	Opredelio se za kombinovan trening snage i kondicije.

Na osnovu ovih činjenica, pokreće se rezoner i redom sledeća pravila se okidaju:
- Pravila za određivanje sledeće grupe mišića koju bi korisnik trebao da trenira:
  - Prethodni trening su rađene grudi, na redu su npr. noge.

- Pravila za izlistavanje vežbi za određenu grupu mišića u zavisnosti od opreme:
  -	Odabrana je šipka za zgibove, ne postoje vežbe za noge sa ovom spravom.
  -	Odabrana je šipka sa tegovima, pronađi vežbe za noge sa ovom spravom. 
  -	Vežbe sa sopstvenom težinom su podrazumevane, pronađi vežbe za noge.

-	Pravila koja određuju intenzitet treninga na osnovu nivoa spremnosti korisnika:
  - Korisnik je srednjeg nivoa spremnosti, za kružne treninge će mu vremenski interval rada biti 30 sekundi. Broj vežbi koje bi trebao da uradi po treningu je 6, broj serija 3, a broj ponavljanja između 6 i 12 (vrednosti fiksirane za nivo spremnosti korisnika, ne uzimaju u obzir vrstu treninga ni ankete).

- Pravila koja određuju opterećenja na osnovu odabrane opreme, nivoa spremnosti i grupe mišića:
  - Korisnik je srednjeg nivoa spremnosti, odabrao je šipku sa tegovima kao deo opreme, grupa mišića koje trenira su noge. Opterećenje za vežbe će biti 50% od telesne mase.

- Pravila za određivanje broja vežbi i serija na osnovu vrste treninga:
  - Tip treninga koji je korisnik odabrao je kombinacija snage i kružnog treninga. Za vežbe snage ćemo prepoloviti broj vežbi koje bi korisnik radio po treningu (dakle imaće tri vežbe), a povećaćemo broj serija na 5. Za kružni trening ćemo izgenerisati 6 vežbi, radiće tri runde (Ovde se preciznije skaliraju ponavljanja na osnovu vrste treninga, anketa koje je popunio i izlaznih vrednosti iz pravila 3).

Od svih vežbi, korisnik će za svaki artikal opreme dobiti izlistano po nekoliko vežbi. Moći će da odabere neke koje bi želeo da radi i time kreira svoj trening. Sa druge strane može pokrenuti i nasumičan odabir čime će se iz skupa odabrati određen broj vežbi na osnovu prethodnih pravila.
Kad se korisnik opredeli za neki trening i doda ga u kolekciju svojih treninga, nudi mu se mogućnost da popuni odgovarajuće ankete. Na osnovu datih anketa, okidaju se pravila navedena u nastavku.

- Pravila za povećanje ili smanjene intenziteta, u zavisnosti od korisnikove ocene:
  -	Ukoliko korisnik nije bio zadovoljan, i tvrdi da se nije umorio, uvećaj mu intenzitet treninga i broj ponavljanja.
  -	Ukoliko korisnik nije bio zadovoljan, i tvrdi da mu je trening bio pretežak, smanji mu intenzitet treninga. 

## Reference:
Nisam pronašao literaturu, ali bih naveo u nastavku neke od aplikacija koje su me inspirisale da pokušam da napravim svoje rešenje:
-	https://wger.de/en/software/api - API koji bih koristio
-	http://www.thewodgenerator.com/category/wod-generator-by-equipment/ - aplikacija koja generiše trening na osnovu odabrane opreme.
-	https://www.jefit.com/exercises/ - aplikacija koja prikaže vežbe na osnovu opreme.


