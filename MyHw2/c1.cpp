#include <iostream>
#include <ctime>
#include <string.h>

using namespace std;

class Node {
    public: 
        long i;
        long j;
        long k;
        long l;
        long m;
        long n;
        Node* next;
};

int calcNumberOfObjects(int numberOfGBs) {
    return (1000000000 / sizeof(class Node)) * numberOfGBs;
}

int parseArgs(int argc, char *argv[]){
    if(argc == 1) {
        cerr << "No total size of nodes specified. Using default size: 3 GB" << endl;
        int n = calcNumberOfObjects(3);
        cout << "Creating " << n << " nodes" << endl;
        return n;
    } else if (argc == 3 && !strcmp(argv[1], "-n")) {
        cout << "Size of objects to be created: " << argv[2] << " GB" << endl;
        int n = calcNumberOfObjects(stoi(argv[2]));
        cout << "Creating " << n << " nodes" << endl;
        return n;
    }
    cerr << "Invalid arguments" << endl;
    exit(EXIT_FAILURE);
}

void run(int n) {
    Node* first = new Node();
    Node* last = first;
    for (int i = 0; i < n; i++) {
        Node* n = new Node();
        last->next = n;
        last = n;
    }
    cout << first << endl << last << endl;
    for(int i = 0; i < n; i++) {
        Node* n = first->next;
        delete first;
        first = n;
    }
    delete first;
}

int main(int argc, char *argv[]) { 
    int n = parseArgs(argc, argv);
    clock_t start;
    double seconds;
    int minutes, hours;
    start = clock();

    for(int i = 0; i < 15; i++){
        run(n);
    }

    seconds = ( clock() - start ) / (double) CLOCKS_PER_SEC;
    cerr << seconds << "S" << endl;
    return 0; 
} 