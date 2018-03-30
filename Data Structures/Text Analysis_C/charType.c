#include<stdio.h>
#include<ctype.h>
#include<stdlib.h>
#include<string.h>

void extract_chars(char* s, char* a, char* d, char* p, char* w);


int main(int argc, char* argv[])
{
	int counter = 0;
	int lineCounter = 0;
	int alphaCount, digiCount, punctCount, whiteCount;
	alphaCount = digiCount = punctCount = whiteCount = 0;
	FILE *in, *out;
	in = fopen(argv[1], "r");
	out = fopen(argv[2], "w");
	char *a, *d, *p, *w;
	char line[256];

	while(fgets(line, 256, in))
	{
	if(lineCounter > 0) fprintf(out, "\n");
	lineCounter++;
	counter = alphaCount = digiCount = punctCount = whiteCount = 0;
	while(line[counter] != '\0')
	{
		char c = line[counter];
		if(isalpha(c))
		{
			alphaCount++;
		}
		else if(isdigit(c))
		{
			digiCount++;
		}
		else if(ispunct(c))
		{
			punctCount++;
		}
		else if(isspace(c))
		{
			whiteCount++;
		}
		counter++;
	}
	//void function
	//print results
	//move over 2
	a = (char *)malloc(alphaCount + 1);
	d = (char *)malloc(digiCount + 1);
	p = (char *)malloc(punctCount + 1);
	w = (char *)malloc(whiteCount + 1);
	
	
	extract_chars(line, a, d, p, w);
	fprintf(out, "line %d contains:", lineCounter);
	
	if(alphaCount > 1)
		fprintf(out, "\n%d alphabetic characters: %s", alphaCount, a);
	else
		fprintf(out, "\n%d alphabetic character: %s", alphaCount, a);
	
	if(digiCount > 1)
		fprintf(out, "\n%d numeric characters: %s", digiCount, d);
	else
		fprintf(out, "\n%d numeric character: %s", digiCount, d);	
	if(punctCount > 1)
		fprintf(out, "\n%d punctuation characters: %s", punctCount, p);
	else
		fprintf(out, "\n%d punctuation character: %s", punctCount, p);
	
	if(whiteCount > 1)
		fprintf(out, "\n%d whitespace characters: %s", whiteCount, w);
	else
		fprintf(out, "\n%d whitespace character: %s\n", whiteCount, w);
	free(a);
	free(d);
	free(p);
	free(w);
	}
	
	fclose(in);
	fclose(out);

	
	return EXIT_SUCCESS;
}

void extract_chars(char* s, char* a, char* d, char* p, char* w)
{
	int counter, alphaCount, digiCount, punctCount, whiteCount;
	counter =alphaCount = digiCount = punctCount = whiteCount = 0;
	
	while(s[counter] != '\0')
	{
		char c = s[counter];
		if(isalpha(c))
		{
			a[alphaCount] = c;
			alphaCount++;
		}
		else if(isdigit(c))
		{
			d[digiCount] = c;
			digiCount++;
		}
		else if(ispunct(c))
		{
			p[punctCount] = c;
			punctCount++;
		}
		else if(isspace(c))
		{
			w[whiteCount] = c;
			whiteCount++;
		}
		counter++;
	}
	a[alphaCount] = '\0';
	d[digiCount] = '\0';
	p[punctCount] = '\0';
	w[whiteCount] = '\0';

}