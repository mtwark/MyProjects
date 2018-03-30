FLAGS   = -std=c99 -Wall
SOURCES = queue.c queueClient.c
OBJECTS = queue.o queueClient.o
EXEBIN  = queueClient

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES)
	gcc -c $(FLAGS) $(SOURCES)

clean :
	rm -f $(EXEBIN) $(OBJECTS)
	
check :
	valgrind --leak-check=full queueClient test-input.txt valOut.txt