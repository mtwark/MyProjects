#include <stdlib.h>
#include <stdio.h>
#include <string.h>

typedef struct NodeObj Node;
typedef struct LinkedListObj LinkedList;
typedef struct QueueObj Queue;
Node* newNode(int number);
LinkedList* newLinkedList(void);
void insert(int number, LinkedList* link);
void printQueue(FILE* out, Queue* q);
Queue* newQueue(void);
void enqueue(FILE* out, int number, Queue* q);
void dequeue(FILE* out, Queue* q);
void updateTail(LinkedList* link);
void freeNode(Node* n);
void freeLinkedList(LinkedList* link);
void freeQueue(Queue* q);