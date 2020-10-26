package ru.ifmo.se.lab1.task3;

public class Person {

    protected boolean hypnotized = false;
    protected Hand hand;
    protected boolean isDesperately = false;
    protected Hand inHand = null;

    public Hand grab(Hand hand){
        setInHand(hand);
        return hand;
    }

    public void pullTheHand(Object forward){

    }

    public void setDesperately(boolean b){
        this.isDesperately = b;
    }

    public boolean getDesperately(){
        return isDesperately;
    }

    public void setInHand(Hand inHand) {
        this.inHand = inHand;
    }

    public Hand getInHand() {
        return inHand;
    }

    public void setHypnotized(boolean hypnotized) {
        this.hypnotized = hypnotized;
    }

    public boolean isHypnotized() {
        return hypnotized;
    }

    public Hand getHand() {
        return this.hand;
    }
}
