class A {
    public void meth(A a) { System.out.println("A: meth(A a)"); }
    public void meth(D d) { System.out.println("A: meth(D d)"); }
    public void meth(E e) { System.out.println("A: meth(E e)"); }
}

class B extends A {
    public void meth(A a) { System.out.println("B: meth(A a)"); }
    public void meth(B b) { System.out.println("B: meth(B b)"); }
    public void meth(D d) { System.out.println("B: meth(D d)"); }
    public void meth(E e) { System.out.println("B: meth(E e)"); }
}

class C extends A {
    public void meth(A a) { System.out.println("C: meth(A a)"); }
    public void meth(C c) { System.out.println("C: meth(C c)"); }
}

class D {
    
}

class E extends D {
    
}

public class Example1 {
     public static void main(String []args){
        B b = new B();
        A a = b;
        a.meth(a);
        a.meth(b);
        
        C c = new C();
        E e = new E();
        D d = e;
        
        ((A) c).meth(a);
        c.meth((A) c);
        a.meth(d);
        a.meth(e);
     }
}
