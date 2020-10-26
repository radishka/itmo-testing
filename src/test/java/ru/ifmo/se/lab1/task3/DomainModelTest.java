package ru.ifmo.se.lab1.task3;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;

public class DomainModelTest {
    Trillian trillian;
    Arthur arthur;
    Ford ford;
    Zafod zafod;
    Door door;
    Rodents rodents;
    Story story;

    String expected1_1 = "Rodents hypnotized Arthur!\n" +
            "Trillian grabs Arthur's hand and pulls to the door.\n" +
            "Arthur can't go!\n" +
            "Ford is trying to open the door...\n";
    String expected1_2 = "Trillian grabs Arthur's hand and pulls to the door.\n" +
            "Trillian and Arthur near the door.\n" +
            "Ford is trying to open the door...\n";
    String expected1_3 = "Rodents hypnotized Arthur!\n" +
            "Ford is trying to open the door...\n";
    String expected1_4 = "Ford is trying to open the door...\n";

    String expected2 = "Ford opened the door!\n";
    String expected3 = "Zafod is trying to open the door...\n" +
            "Zafod opened the door!\n";
    String expected4 = "Zafod is trying to open the door...\n" +
            "Ford and Zafod can't open the door.\n";


    @Before
    public void Initialize(){
        trillian = new Trillian();
        ford = new Ford();
        arthur = new Arthur();
        zafod = new Zafod();
        door = new Door();
        rodents = new Rodents();
        story = new Story();
    }

    @Test
    public void trillianIsDespArthurIsHypn(){
        trillian.setDesperately(true);
        rodents.toHypnotize(arthur);

        story.tellStory(trillian, arthur, rodents, door, ford, zafod);

        Assert.assertThat(story.stringBuilder1.toString() + story.stringBuilder2.toString(), anyOf(is(expected1_1 + expected2),
                is(expected1_1 + expected3),
                is(expected1_1 + expected4)));
    }

    @Test
    public void trillianIsDespArthurNotHypn(){
        trillian.setDesperately(true);

        story.tellStory(trillian, arthur, rodents, door, ford, zafod);

        Assert.assertThat(story.stringBuilder1.toString() + story.stringBuilder2.toString(), anyOf(is(expected1_2 + expected2),
                is(expected1_2 + expected3),
                is(expected1_2 + expected4)));
    }

    @Test
    public void trillianNotDespArthurIsHypn(){
        rodents.toHypnotize(arthur);

        story.tellStory(trillian, arthur, rodents, door, ford, zafod);

        Assert.assertThat(story.stringBuilder1.toString() + story.stringBuilder2.toString(), anyOf(is(expected1_3 + expected2),
                is(expected1_3 + expected3),
                is(expected1_3 + expected4)));
    }

    @Test
    public void trillianNotDespArthurNotHypn(){
        story.tellStory(trillian, arthur, rodents, door, ford, zafod);

        Assert.assertThat(story.stringBuilder1.toString() + story.stringBuilder2.toString(), anyOf(is(expected1_4 + expected2),
                is(expected1_4 + expected3),
                is(expected1_4 + expected4)));
    }

}
