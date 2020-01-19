#include <iostream>

using namespace std;

class ClassA {
    public:
        long i1;
        long i2;

        ClassA() {
            this->i1 = 10;
            this->i2 = 11;
            cout << "A was initialized" << endl;
        }

        virtual void methA() {
            cout << "Virtual method in class A called methA" << endl;
        }


};

class ClassB { 
    public:
        long j1;
        long j2;

        ClassB() {
            this->j1 = 20;
            this->j2 = 21;
            cout << "B was initialized" << endl;
        }


        virtual void methB() {
            cout << "Virtual method in class B called methB" << endl;
        }

};

class ClassC {
    public: 
        long k1;
        long k2;

        ClassC() {
            this->k1 = 30;
            this->k2 = 31;
            cout << "C was initialized" << endl;
        }

        virtual void methC() {
            cout << "Virtual method in class C called methC" << endl;
        }
};

class ClassD: public ClassA, public ClassB, public ClassC {
    public:
        long l1;
        long l2;
        long l3;

        ClassD() {
            this->l1 = 40;
            this->l2 = 41;
            this->l3 = 42;

            cout << "D was initialized" << endl;
        }

        void methB() { 
            cout << "Method in class D called methB" << endl;
        }

        void methD() {
            cout << "Method in class D called methD" << endl;
        }
};


int main(int argc, char *argv[]) {
    ClassD* d = new ClassD();

    ClassA* a = new ClassA();
    ClassB* b = new ClassB();
    ClassC* c = new ClassC();
    
    cout << "Address of a: " <<  *((long*) a)<< endl;
    for(int i = 0 ; i < 3; i++) {
        cout << *((long*) a + i)  << endl;
    }
    delete a;

    cout << "Address of b: " << *((long*) b) << endl;
    for(int i = 0 ; i < 3; i++) {
        cout << *((long*) b + i)  << endl;
    }
    delete b;

    cout << "Address of c: " <<  *((long*) c) << endl;
    for(int i = 0 ; i < 3; i++) {
        cout <<  *((long*) c + i)  << endl;
    }
    delete c;

    cout << "Address of d: " << *((long*) d) << endl;
    for(int i = 0 ; i < 12; i++) {
        cout << hex << *((long*) d + i)  << endl;
    }
    delete d;

    return 0;
}