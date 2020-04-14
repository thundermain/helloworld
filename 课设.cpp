#include <stdio.h>  
#include <stdlib.h> 
#include <malloc.h>
#include<string.h>
#include<time.h>
struct test
{
	char ask[500];//选择题题目
	char answer1[100];
	char answer2[100];
	char answer3[100];
	char answer4[100];
	char da[100];
	int title[100];//随机抽题
	char right[10];
}  m[1000];
void menu()
{
	printf(" ***********客观题考试系统*********\n");
	printf(" *********************************************\n");
	printf(" 1.试题录入                                ***\n");
	printf(" 2.试题抽取并答题                          ***\n");
	printf(" 3.判卷并打分                              ***\n");
	printf(" 4.退出系统                                ***\n");
	printf(" ********************************************\n");
}
int input(int cnt)
{
	int i, count, choice = 0;
	printf("开始输入记录...\n");
	count = cnt;
	for (i = cnt; i<1000; i++)
	{
		printf("Continue?(1  Yes  0  No):");
		scanf("%d", &choice);
		getchar();
		if (choice == 0)
			break;
		else
			if (choice == 1)
			{
				printf("请输入第%d道题题目，题干和选项:\n", i + 1);
				printf("请输入题目:");
				gets_s(m[i].ask);
				printf("请输入选项A:");
				gets_s(m[i].answer1);
				printf("请输入选项B:");
				gets_s(m[i].answer2);
				printf("请输入选项C:");
				gets_s(m[i].answer3);
				printf("请输入选项D:");
				gets_s(m[i].answer4);
				printf("请输入答案:");
				scanf("%c", &m[i].right);
				cnt++;

			}
	}
	if (count != cnt)
		printf("\n题目输入成功：");
	else
		printf("\n未输入记录！");
	return cnt;
}

void rande(int cnt)
{
	int i, j, x = 1, n, t;
	int title[100];
	
	
		for (i = 1; i <= 5; i++)
		{
		x = rand() % 5;
			title[i] = x;
			for (j = 0; j<i; j++)
				if (title[i] == title[j])
				{
					x = rand() % 5;
				}
			printf(" =============================================\n");
			printf("%s\n", m[x].ask);
			printf("A:%s\n", m[x].answer1);
			printf("B:%s\n", m[x].answer2);
			printf("C:%s\n", m[x].answer3);
			printf("D:%s\n", m[x].answer4);
			printf("请输入你的答案：");
			scanf("%s", m[x].da);
			printf(" =============================================\n");
		}
	printf("答题成功，按任意键返回主菜单...");
}
void ask(int cnt)
{
	int i, n;
	int count = 0;
	for (i = 0; i<cnt; i++)
	{
		if (!strcmp(m[i].da, m[i].right))
		{
			count++;
		}
	}
	printf("你答对的总题数是%d\n", count);
	printf("你最后的得分为%d", 20*count);
	system("pause");
}
int main()
{
	FILE * fp;
	int i = 0, count = 0, select;

	fp = fopen("file08.txt", "a+");
	if (fp == NULL)
	{
		printf("File open error!\n");
		exit(0);
	}
	while (!feof(fp))
	{
		if (fread(&m[i], sizeof(struct test), 1, fp) == 1)
		{
			i++;
			count++;
		}
	}
	fclose(fp);

	while (1)
	{
		printf("当前文件夹总共有%d条记录.\n", count);
		menu();
		printf("请输入你的选择：");
		scanf("%d", &select);
		switch (select)
		{
		case 1:
			count = input(count); break;
		case 2:
			rande(count); break;
		case 3:
			ask(count); break;
		case 4:
			fp = fopen("file08.txt", "w");
			printf("开始保存文件，按任意键继续........");
			getchar();
			getchar();
			for (i = 0; i<count; i++)
			{
				fwrite(&m[i], sizeof(struct test), 1, fp);
			}
			fclose(fp);
			printf("\n文件保存成功，按任意键退出系统.....");
			getchar();
			exit(0);
		default:printf("输入错误，请重新输入.....");
		}
	}
}
