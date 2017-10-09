Nume: Vinatoru Andrei-Ioan
Grupa: 335CB
===============================================================================================

	In implementarea temei am folosit cunostinte de POO si notiunile de multithreading in
 Java invatate la laborator si la curs. Acestea includ Thread (Runnable) si Executor Service.

	In cadrul implementarii am creat in clasa Main un ArrayBlockingQueue care trebuie sa 
fluidizeze procesul de producator consumator, fara a folosi o variabila sincronizata, este deja
asa.
	In Event calculez fiecare tip de eveniment, iar atunci cand primesc un nou eveniment, 
in metoda run() il calculez si apoi incerc sa il adaug sincronizat in array-ul specific.
	I Generator citesc elementele din fisier (avand fisierele ca parametru in constructor), 
formez evenimente noi cu aceste informatii (dupa ce astept cele N secunde), apoi le ofer cozii 
de evenimente spre a fi procesate.

	In Thread-ul Main formez thread-urile generatorilor , le dau drumul, iar apoi pornesc un
threadpool cu ajutorul ExecutorService care va lua elementele din coada in paralel. Astfel, cand
nu mai sunt elemente de procesat in coada si nici evenimente de citit, thread-urile de citire vor
da join. Apoi, cu toate elementele in vectorii specifici, se afiseaza in fisierele de scriere 
aferente.