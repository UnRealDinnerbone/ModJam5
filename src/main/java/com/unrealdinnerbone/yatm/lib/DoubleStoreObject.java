package com.unrealdinnerbone.yatm.lib;

public class DoubleStoreObject<A, B>
{
    private A a;
    private B b;

    public DoubleStoreObject(A a, B b) {
        this.a = a;
    }

    public A getA() {
        return a;
    }

    public B getB() {
        return b;
    }
}
