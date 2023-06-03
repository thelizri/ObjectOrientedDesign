package se.kth.iv1350.task2;

public class Main {
    public static void main(String[] args) {
        MyRandomWithInheritance myRandom = new MyRandomWithInheritance();
        System.out.println("Random biased boolean (inheritance): " + myRandom.nextBiasedBoolean(0.3f));

        MyRandomWithComposition myRandomWithComposition = new MyRandomWithComposition();
        System.out.println("Random biased boolean (composition): " + myRandomWithComposition.nextBiasedBoolean(0.7f));
    }
}
