#include <iostream>
#include <ctime>
#include <string.h>

using namespace std;

class Node {
    public: 
        long i;
        Node* next;
};

Node* createNode() {
    Node* node = new Node();
    return node;
}

long sum(Node* first, Node** node) {
    long counter = 0;
    *node = first;
    while (*node != NULL) { 
        counter++;
        *node = (*node)->next;
    }
    return counter;
}

void run() {
    Node* node = new Node();
    Node* last = node;
    for (int i = 0; i < 20000000; i++) {
        Node* n = new Node();
        n->i = i % 8;
        last->next = n;
        last = n;
    }

    Node* first = node;
    int deleted = 0;
    int i = 0;
    while (node != NULL) {
        if (i % 8 != 0) {
            Node* tmp = node->next;
            if(node->next != NULL) {
                node->next = node->next->next;
            } else {
                node->next = NULL;
            }
            node = node->next;
            delete tmp;
            deleted++;
        } else {
            node = node->next;
        }
        i++;
    }
    cout << "Created 20000000 nodes" << endl;
    cout << "Deleted " << deleted << " nodes" << endl;
    cout << 200000000 - deleted << " nodes left" << endl;
    long counter = 0;
    for(int j = 0; j < 10000; j++) {
        sum(first, &node);
    }
    counter = sum(first, &node);
    
    cout << "Sum is " << counter << endl;
    node = first;
    while (node != NULL) { 
        Node* tmp = node->next;
        delete node;
        node = tmp;
    }
    delete node;
}

int main(int argc, char *argv[]) { 
    clock_t start;
    double seconds;
    int minutes, hours;
    start = clock();

    run();

    seconds = ( clock() - start ) / (double) CLOCKS_PER_SEC;
    cerr << seconds << "S" << endl;
    return 0; 
} 