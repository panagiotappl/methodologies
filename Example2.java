class A {
    public void meth(A a) { System.out.println("A: meth(A a)"); }
    public void meth(B b) { System.out.println("A: meth(B b)"); }
    public void meth(D d) { System.out.println("A: meth(D d)"); d.foo(this); }
    public void meth(E e) { System.out.println("A: meth(E e)"); }
}

class B extends A {
    public void meth(A a) { System.out.println("B: meth(A a)"); }
    public void meth(B b) { System.out.println("B: meth(B b)"); }
    public void meth(D d) { System.out.println("B: meth(D d)"); }
    public void meth(E e) { System.out.println("B: meth(E e)"); e.foo(this); }
}

class D {
    void foo(A a) { System.out.println("foo(A a)"); }
    void foo(B b) { System.out.println("foo(B b)"); foo(this); }
    void foo(D d) { System.out.println("foo(D d)"); }
    void foo(E e) { System.out.println("foo(E e)"); }
}

class E extends D {
    
}

public class Example2 {
     public static void main(String []args){
        B b = new B();
        A a = b;
        E e = new E();
        D d = e;
        
        a.meth(d);
        a.meth(a);
        d.foo(b);
        e.foo(b);
        a.meth(e);
        e.foo(d);
     }
}
