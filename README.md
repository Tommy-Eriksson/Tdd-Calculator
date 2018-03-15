<H1>YH utbildning systemintegratör</h1>
<a href="https://www.lernia.se/utbildning/yrkeshogskoleutbildning/systemintegrator/">Lernia systemintegratör</a>

<h5>Andra uppgiften i den inledande kursen Java. </h5>

<br />
<br />
<h3>Testdriven utveckling</h3>

<p>Ert mål för denna laboration är att utforma en kalkylator som kan hantera multiplikation, division, subtraktion, addition och modulus operationer. </p>
<p>Ni skall arbeta enligt TDD-metodik, alltså ni skall utforma ett brett antal tester för att säkerställa att er applikation fungerar tillfredställande. Ni kommer också behöva kasta ett antal olika exceptions vid tillfällen där ett tal är antingen för litet eller för stort som resulterar i en overflow efter kalkylering. Det skall också kastas ett exceptions vid tillfällen där något tal divideras med noll.</p>
<p>Denna funktion skall ta emot en sträng med ett matematiskt uttryck och returnerar en sträng med rätt resultat. 
Funktionen skall på denna nivå kunna klara att räkna ut obegränsat antal operander och operatorer ihop utan krav på parenteser. </p>

<p>Exempel på vad funktionen skall klara:</p>
<pre>5*4+7-3*0+4-10 
7*4+6-3/2
</pre>
<p>Exempel på vad funktionen inte behöver klara</p>
<pre>
5 - -5
5+(6*7)
</pre>
<p>Ni måste här tänka på att prioritet på operatorerna går från vänster och * / och % har högre prioritet, alltså:</p>
<pre>
7*6/2 = 21
5+6/2*2 = 11 
</pre>
<p>Ni skall kasta en ArithmeticException ur funktionen calculateExpression om ett tal divideras med noll. Detta skall testas med expectedExceptions. </p>
<p>Det skall kastas lämpliga exceptions ur calculateExpression vid ett overflow, alltså där två för stora tal adderas ihop och resulterar ett tal för stort för att kunna hanteras av datatypen double.
Det skall kastas exceptions eller resultera i ett felmeddelande om input är felaktigt. 
En felaktig input kan t.ex. vara flera operatorer ihop i följd, bokstäver, för många mellanrum(space) mellan operanderna osv. 
Ni skall genom tester fånga upp tillfredställande antal fel. Med det menas att det inte finns några krav på att fånga upp alla fel. Testerna skall visa att funktionen calculateExpression svarar allmänt tillfredställande vid felaktiga inputs. Det är fortfarande okej att exempelvis inte klara uttryck som 3—1 eller 10—1; men detta får gärna implementeras.</p>
<p>Exempel på felaktiga inputs:</p>
<pre>
5++p
5++4
3    4
sdf
*3
0+0//
osv...</pre>
<p>Allt ovan skall bevisas genom tester och det är viktigt att ni testar gränsvärden och udda värden. </p>
