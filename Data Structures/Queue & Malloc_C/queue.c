#include "queue.h"


typedef struct NodeObj
{
	int val;
	struct NodeObj* next;

} Node;

Node* newNode(int number)
{
	Node* n = malloc(sizeof(Node));
	n->val = number;
	return n;
}


typedef struct LinkedListObj
{
	int size;
	Node* head;

} LinkedList;

LinkedList* newLinkedList(void)
{
	LinkedList* link = malloc(sizeof(LinkedList));
	link->size = 0;
	link->head = NULL;
	return link;
}

void insert(int number, LinkedList* link)
{
	Node* n = newNode(number);
	n->next = link->head;
	link->head = n;
	link->size++;

}




typedef struct QueueObj
{
	LinkedList* link;

} Queue;

Queue* newQueue(void)
{
	Queue* q = malloc(sizeof(Queue));
	q->link = newLinkedList();
	return q;
}

void enqueue(FILE* out, int number, Queue* q)
{
	insert(number, q->link);
	fprintf(out, "enqueued %d\n", number);
}

void dequeue(FILE* out, Queue* q)
{
	int val, i;
	val = i = 0;
	Node* del = q->link->head;
	
	if (q->link->size > 1)
	{
		for (i = 1; i < q->link->size - 1; i++)
		{
			del = del->next;
		}
		val = del->next->val;
		free(del->next);
		del->next = NULL;
		q->link->size--;
		fprintf(out, "%d\n", val);
	}
	else if (q->link->size == 1)
	{
			val = q->link->head->val;
			free(q->link->head);
			q->link->head = NULL;
			q->link->size--;
			fprintf(out, "%d\n", val);
	}
	else if (q->link->size == 0)
	{
		fprintf(out, "empty\n");
	}
}

void printQueue(FILE* out, Queue* q)
{

	int i = 0;
	int j = 0;
	if (q->link->size != 0)
	{
		
		for (j = 0; j < q->link->size; j++)
		{
			Node* current = q->link->head;
			for (i = q->link->size - j - 1; i > 0; i--)
			{
				current = current->next;
			}
			fprintf(out, "%d ", current->val);
		}
	}

	fprintf(out, "\n");
}



void freeNode(Node* n)
{
	free(n);
	n = NULL;
}

void freeLinkedList(LinkedList* link)
{
	while (link->head->next != NULL)
	{
		Node* temp = link->head->next;
		freeNode(link->head);
		link->head = temp;
		freeNode(temp);
	}
	freeNode(link->head);
	free(link);
}

void freeQueue(Queue* q)
{
	freeLinkedList(q->link);
	free(q);
}



