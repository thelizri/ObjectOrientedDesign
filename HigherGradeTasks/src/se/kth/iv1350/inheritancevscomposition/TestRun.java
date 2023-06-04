package se.kth.iv1350.inheritancevscomposition;

public class TestRun {
    public static void main(String[] args) {
        MyRandomWithInheritance myRandom = new MyRandomWithInheritance();
        System.out.println("Random biased boolean (inheritance): " + myRandom.nextBiasedBoolean(0.5f));

        MyRandomWithComposition myRandomWithComposition = new MyRandomWithComposition();
        System.out.println("Random biased boolean (composition): " + myRandomWithComposition.nextBiasedBoolean(0.5f));
    }
}
