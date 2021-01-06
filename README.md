# Workflow simulator

## Enunt
*Un sistem care simuleaza executia unor secvente de activitati dintr-o
organizatie. Secventa de activitati este descrisa ca o succesiune de
stari legate prin tranzitii (masina de stari). Fiecare stare reprezinta o
activitate, iar activitatile se pot desfasura in paralel. O activitate
consta in lansarea in executie a unei metode specifice dintr-o
clasa Java incarcata de pe disc.*

*In tot sistemul este vizibila o resursa
care memoreaza un set de variabile de mediu globale. Trecerea dintr-o
stare in alta se poate face conditionat de valorile variabilelor de
mediu. De asemenea, activitatea dintr-o stare poate utiliza
informatile din variabilele de mediu (setate de alte stari precedente)
pentru a-si indeplini functia.*

*Dintr-o stare se poate trece in mai multe stari care vor
fi atinse in paralel.*

*Masina de stari precum si numele claselor care trebuie
incarcate pentru fiecare activitate sunt specificate prin elemente de 
configurare.*

### Prerequisites
1. JDK 11
2. Maven

### Running the app
run the following **maven** goal
> **clean javafx:run**

or via terminal: 
> **mvn clean javafx:run**
