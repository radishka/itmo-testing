package ru.ifmo.se.lab1.task3;

public class Hand {

    private Person belongsto;

    public Hand(Person person){
        this.belongsto = person;
    }

    public Person getBelongsto() {
        return belongsto;
    }
}
