package ru.ifmo.se.task3;

public class Hand {

    private Person belongsto;

    public Hand(Person person){
        this.belongsto = person;
    }

    public Person getBelongsto() {
        return belongsto;
    }
}
