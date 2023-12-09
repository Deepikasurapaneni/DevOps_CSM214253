#include <stdio.h>
#include <stdlib.h>
#define MAX_BRANCHES 5
#define MAX_STUDENTS 60
struct student {
int rank;
char name[100];
int pref[MAX_BRANCHES];
};
void allocate_seats(struct student *students, int college_allotment[][MAX_BRANCHES], int n, int m) 
{
    int i, j;
    for (i = 0; i < n; i++)
    {
        for (j = 0; j < MAX_BRANCHES; j++)
	    {
            int college = students[i].pref[j] / 10 - 1;
            int branch = students[i].pref[j] % 10 - 1;
            if (college_allotment[college][branch] < 60)
	        {
	        	college_allotment[college][branch]++;
                break;
            }
        }
    }
}
int main() 
{
    int n, m, i, j;
    FILE *fp;
    fp = fopen("input.txt", "r");
    if (fp == NULL)
    {
        printf("Could not open file\n");
        return 0;
    }
    fscanf(fp, "%d%d", &n, &m);
    struct student students[MAX_STUDENTS];
    int college_allotment[n][MAX_BRANCHES];
    for (i = 0; i < n; i++)
	 {
        for (j = 0; j < m; j++) 
	    {
            college_allotment[i][j] = 0;
        }
     }
    for (i = 0; i < n; i++)
	 {
        fscanf(fp, "%d%s", &students[i].rank, students[i].name);
        for (j = 0; j < m; j++)
        {
            fscanf(fp, "%d", &students[i].pref[j]);
        }
     }
    fclose(fp);
    allocate_seats(students, college_allotment, n, m);
    for (i = 0; i < n; i++) 
    {
        printf("%d %s ", students[i].rank, students[i].name);
        for (j = 0; j < m; j++)
	    {
            int college = students[i].pref[j] / 10 - 1;
            int branch = students[i].pref[j] % 10 - 1;
            if (college_allotment[college][branch] > 0) 
	        {
	        	college_allotment[college][branch]--;
                printf("%d-%d \n", college + 1, branch + 1);
                break;
            }
        }
    }   
return 0;	
}
