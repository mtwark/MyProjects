#include "queue.h"
int main(int argc, char* argv[])
{
	Queue* q = newQueue();

	FILE *in, *out;
	in = fopen(argv[1], "r");
	out = fopen(argv[2], "w");
	char line[256];

	while (fgets(line, 256, in))
	{
		char c = '\0';
		int d = 0;
		sscanf(line, "%c %d", &c, &d);
		if (c == 'e')
			enqueue(out, d, q);
		if (c == 'd')
			dequeue(out, q);
		if (c == 'p')
			printQueue(out, q);
	}
	freeQueue(q);
	fclose(in);
	fclose(out);

	return 0;
}

//add destructors