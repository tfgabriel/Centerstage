package org.firstinspires.ftc.teamcode.camerathings

//imi definesc aici niste variabile cu care o sa lucrez prin pipeline
object CameraObjects{

}

class pipelinedarscrisdeivi{
    //vreau sa imi impart o parte din ceea ce vede camera in o grila cu patrate
    //ce vei vedea pe dash:
    //patratele in sine vor avea 2 culori - neagra daca ceea ce se afla in patrat nu e rosu sau albastru
    //sau alb daca sunt rosii sau albe (bine, poti sa le customizezi cum vrei sunt patratele tale)
    //in afara de ele, va mai exista o linie verticala care iti separa streamul de la camera ca sa vezi accurately in ce caz pica fiecare patrat

    //pentru toate chestiile astea, trebuie sa definesc un patrat, o grila in care sa imi tin patratele
    //o functie care sa imi deseneze patratele si sa mi le arate pe stream
    //o functie care sa vada ce culori se afla in cadrul fiecarui patrat si sa compare cu albastru si rosu
    //o functie care se uita in grila de patrate si numara cate patrate sunt in fiecare dintre sectiunile facute de linia de mai sus
    //apoi la sfarsit compara numarul de patrate din fiecare sectiune si iti da cazul


}

class patrat(): Comparable<patrat>{
    override fun compareTo(other: patrat): Int {
        TODO("Not yet implemented")
    }

}