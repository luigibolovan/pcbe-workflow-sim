<div align="center">
<h1>Programare concurenta si bazata pe evenimente</h1>
</div>

<div align="right">
<h4>Universitatea  Politehnica Timisoara</h4>
</div>

<div align="right">
<h4>Facultatea de Automatica si Calculatoare, CTI-RO</h4>
</div>

<div align="right">
<h4>Luigi Ionut Bolovan, 1.2</h4>
</div>

<div align="center">
<h2>Evaluare distribuita - partea 2</h2>
</div>



## Contributia la cele doua proiecte

### Primul proiect - [*Workflow simulator*](https://github.com/luigibolovan/pcbe-workflow-sim)
  Aportul meu la primul proiect este dezvoltarea sistemului care se ocupa cu schimbarea starilor documentatiei(*reprezentate printr-un enum*), cu pornirea celor doua departamente(*cel de creare al documentatiei si cel de publicare al acesteia*)
si cu furnizarea de informatii legate de documentatie(e.g. *documentatie scrisa*). Sistemul este cel care are monitorii pentru rezolvarea problemei de tip *readers-writers* intalnita in cadrul proiectului.

### Al doilea proiect - [*Sistem de stiri*](https://github.com/luigibolovan/pcbe-news-system)
  In cadrul celui de-al doilea proiect am fost responsabil de elaborarea conceptului proiectului si de elaborarea sistemului de stiri bazat pe event dispatcher-ul oferit de biblioteca [Guava](https://github.com/google/guava/wiki/EventBusExplained) de la Google.
Sistemul de stiri se ocupa de publicarea evenimentelor de ```adaugare```, ```actualizare``` si ```stergere``` al stirilor, de adaugarea sau stergerea stirilor din baza de date a sistemului(*mock database, o simpla lista de stiri*) si de abonarea si dezabonarea cititorilor de la sistemul de stiri.

## Problema de concurenta frecventa
### Descrierea problemei
  O problema de concurenta care poate aparea frecvent in sistemele software este ***Producer-consumer***. Aceasta consta in existenta a doua entitati software, un producer, respectiv un consumer, care impart un buffer comun.
Producer-ul pune obiecte in buffer, iar consumerul preia obiectele din buffer si le consuma ulterior. Una dintre constrangeri este reprezentata de problema dezvoltarii producer-ului si consumer-ului in asa fel incat, in timpul schimbului de date dintre entitatile software, datele nu sunt duplicate sau pierdute si consumer-ul preia datele in aceeasi ordine in care sunt furnizate de catre producer.
O alta constrangere este legata de utilizarea bufferelor finite. In acest caz producer-ul trebuie sa se asigure ca nu adauga date in cazul in care buffer-ul este plin, iar consumer-ul trebuie prevenit sa nu citeasca dintr-un buffer gol.
### Solutii
  O solutie a acestui tip de problema de concurenta il reprezinta utilizarea de semafoare si mutex-uri.
  
  Mutexul va fi adaugat pentru a rezolva problema ce poate aparea atunci cand sunt mai multi produceri si acestia ar adauga obiecte in buffer-ul comun(va permite ca doar un singur producer sa puna in buffer obiecte)
  
  Semaforul va fi folosit pentru a putea tine cont de numarul de obiecte din buffer si de spatiile ramase pana la umplerea buffer-ului.
  
```c

mutex buffer_mutex;

// numarul de elemente din buffer - se incrementeaza la adaugarea unui item in buffer
semaphore fillCount = 0; 
// numarul de spatii din buffer - se decrementeaza la adaugarea unui item in buffer
semaphore emptyCount = BUFFER_SIZE; 

void producer() 
{
    while (true) 
    {
        item = produceItem();
        down(emptyCount);
        down(buffer_mutex);
        add_to_buffer(item);
        up(buffer_mutex);
        up(fillCount);
    }
}

void consumer() 
{
    while (true) 
    {
        down(fillCount);
        down(buffer_mutex);
        item = remove_from_buffer();
        up(buffer_mutex);
        up(emptyCount);
        do_something(item);
    }
}
```

### Exemplu "*lock ordering deadlock*"

```java
public class Main {

    public static void main(String[] args) {
        Thread t1 = new Thread(Main::method1);
        Thread t2 = new Thread(Main::method2);

        t1.start();
        t2.start();
    }

    public static void method1() {
        synchronized (String.class) {
            System.out.println("Lock on String.class");

            synchronized (Integer.class) {
                System.out.println("Lock on Integer.class");
            }
        }
    }

    public static void method2() {
        synchronized (Integer.class) {
            System.out.println("Lock on Integer.class");

            synchronized (String.class) {
                System.out.println("Lock on String.class");
            }
        }
    }
}
```
O situatie care ar putea provoca un *deadlock* este atunci cand thread-ul ```t1``` ar obtine lock-ul pe obiectul de tip String in timpul executiei ```method1()``` si thread-ul ```t2``` ar obtine lock-ul pe obiectul de tip Integer, atunci ambele thread-uri ar astepta release-ul lock-urilor.
