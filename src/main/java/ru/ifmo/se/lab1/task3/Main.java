package ru.ifmo.se.lab1.task3;

public class Main {
    public static void main(String[] args){

        Trillian trillian = new Trillian();
        Arthur arthur = new Arthur();
        Ford ford = new Ford();
        Zafod zafod = new Zafod();
        Door door = new Door();
        Rodents rodents = new Rodents();
        Story story = new Story();

        trillian.setDesperately(true);

        rodents.toHypnotize(arthur);

        story.tellStory(trillian, arthur, rodents, door, ford, zafod);

        System.out.println(story.stringBuilder1);
        System.out.println(story.stringBuilder2);
    }

}
