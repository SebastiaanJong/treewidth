package Util.Tuple;

public class Pair<A,B> {
    private final A a;
    private final B b;
    public Pair(A a, B b) {
        this.a = a;
        this.b = b;
    }

    public A fst(){
        return this.a;
    }

    public B snd(){
        return this.b;
    }
}
