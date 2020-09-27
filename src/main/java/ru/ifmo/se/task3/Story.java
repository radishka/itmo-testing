package ru.ifmo.se.task3;

public class Story {

    public  StringBuilder stringBuilder1 = new StringBuilder();
    public  StringBuilder stringBuilder2 = new StringBuilder();

    public void tellStory(Trillian trillian, Arthur arthur, Rodents rodents, Door door, Ford ford, Zafod zafod){

        if (arthur.isHypnotized()){
            stringBuilder1.append("Rodents hypnotized Arthur!\n");
        }

        if(trillian.getDesperately()){
            trillian.grab(arthur.getHand());
            trillian.pullTheHand(door);
            stringBuilder1.append("Trillian grabs Arthur's hand and pulls to the door.\n");
        }

        if (trillian.getInHand() != null){
            if (trillian.getInHand().getBelongsto().isHypnotized()){
                stringBuilder1.append("Arthur can't go!\n");
            } else {
                stringBuilder1.append("Trillian and Arthur near the door.\n");
            }
        }

        stringBuilder1.append("Ford is trying to open the door...\n");
        ford.tryToOpen(door);
        if (door.isOpened()){
            stringBuilder2.append("Ford opened the door!\n");
        } else {
            stringBuilder2.append("Zafod is trying to open the door...\n");
            zafod.tryToOpen(door);
            if (door.isOpened()){
                stringBuilder2.append("Zafod opened the door!\n");
            } else {
                stringBuilder2.append("Ford and Zafod can't open the door.\n");
            }
        }
    }
}
