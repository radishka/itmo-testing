package ru.ifmo.se.task3;

public interface UsingTheDoor {

    default void tryToOpen(Door door) {

        if (Math.random() < 0.5){
            door.setOpened(true);
        } else {
            door.setOpened(false);
        }
    }
}
