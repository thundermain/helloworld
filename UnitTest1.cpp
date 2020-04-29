#include "pch.h"
#include "CppUnitTest.h"
using namespace Microsoft::VisualStudio::CppUnitTestFramework;
namespace UnitTest1
{
	TEST_CLASS(UnitTest1)
	{
	public:

		TEST_METHOD(TestMethod1)//整数加法运算
		{
			int Max = 50; //最大值
			int tishu = 5;//数量
			int  Symbo = 1;//请连续输入使用的运算符（在“+”，“-”，“*”，“/”中选择输入）
			int Xiaoshu = 1;//0表示没有小数，1表示有小数 int Print = 0;//用户选择输出方式（1表示输出到文件，0表示输出屏幕）
			int  Print = 1;//是否输出到文件0.不通过文件输出  1.通过文件输出
			int a, b;//随机数
			int ans[100];
			int i = 0;
			char type = ' ';//运算符
			int result[100];//答案
			for (i = 1; i <= tishu; i++) {

			}
			for (i = 1; i <= tishu; i++) {
				Assert::AreEqual(ans[i], result[i]);
			};

		}
		TEST_METHOD(TestMethod2)//整数减法运算
		{
			int Max = 50; //最大值
			int tishu = 5;//数量
			int  Symbo = 2;//请连续输入使用的运算符（在“+”，“-”，“*”，“/”中选择输入）
			int Xiaoshu = 1;//0表示没有小数，1表示有小数 
			int Print = 0;//用户选择输出方式（1表示输出到文件，0表示输出屏幕）
			int a, b;//随机数
			int ans[100];
			int i = 0;
			char type = ' ';//运算符
			int result[100];//答案
			for (i = 1; i <= tishu; i++) {

			}
			for (i = 1; i <= tishu; i++) {
				Assert::AreEqual(ans[i], result[i]);
			};

		}
		TEST_METHOD(TestMethod3)//整数乘法运算
		{
			int Max = 50; //最大值
			int tishu = 5;//数量
			int  Symbo = 9;//请连续输入使用的运算符（在“+”，“-”，“*”，“/”中选择输入）
			int Xiaoshu = 0;//0表示没有小数，1表示有小数 
			int Print = 0;//用户选择输出方式（1表示输出到文件，0表示输出屏幕）
			int a, b;//随机数
			int ans[100];
			int i = 0;
			char type = ' ';//运算符
			int result[100];//答案
			for (i = 1; i <= tishu; i++) {

			}
			for (i = 1; i <= tishu; i++) {
				Assert::AreEqual(ans[i], result[i]);
			};

		}
		TEST_METHOD(TestMethod4)//整数乘法运算
		{
			int Max = 50; //最大值
			int tishu = 5;//数量
			int  Symbo = 4;//请连续输入使用的运算符（在“+”，“-”，“*”，“/”中选择输入）
			int Xiaoshu = 0;//0表示没有小数，1表示有小数 
			int Print = 0;//用户选择输出方式（1表示输出到文件，0表示输出屏幕）
			int a, b;//随机数
			int ans[100];
			int i = 0;
			char type = ' ';//运算符
			int result[100];//答案
			for (i = 1; i <= tishu; i++) {

			}
			for (i = 1; i <= tishu; i++) {
				Assert::AreEqual(ans[i], result[i]);
			};

		}
		TEST_METHOD(TestMethod5)// 小数加法运算
		{
			int Max = 10; //最大值
			int tishu = 3;//数量
			int  Symbo = 3;//请连续输入使用的运算符（在“+”，“-”，“*”，“/”中选择输入）
			int Xiaoshu = 2;//0表示没有小数，1表示有小数
			int  Print = 1;//是否输出到文件0.不通过文件输出  1.通过文件输出
			float a = 6, b = 3;
			float ans = 9;
			float result = a + b;
			Assert::AreEqual(ans, result);
		}
		TEST_METHOD(TestMethod6)// 小数减法运算
		{
			int Max = 10; //最大值
			int tishu = 3;//数量
			int  Symbo = 3;//请连续输入使用的运算符（在“+”，“-”，“*”，“/”中选择输入）
			int Xiaoshu = 2;//0表示没有小数，1表示有小数
			int  Print = 1;//是否输出到文件0.不通过文件输出  1.通过文件输出
			float a = 6.0, b = 3.0;
			float ans = 3.0;
			float result = a - b;
			Assert::AreEqual(ans, result);
		}
		TEST_METHOD(TestMethod7)// 小数乘法运算
		{
			int Max = 10; //最大值
			int tishu = 3;//数量
			int  Symbo = 3;//请连续输入使用的运算符（在“+”，“-”，“*”，“/”中选择输入）
			int Xiaoshu = 2;//0表示没有小数，1表示有小数
			int  Print = 1;//是否输出到文件0.不通过文件输出  1.通过文件输出
			float a = 6.0, b = 3.0;
			float ans = 18.0;
			float result = a * b;
			Assert::AreEqual(ans, result);
		}
		TEST_METHOD(TestMethod8)//小数除法运算
		{
			int Max = 50; //最大值
			int tishu = 4;//数量
			int  Symbo = 3;//请连续输入使用的运算符（在“+”，“-”，“*”，“/”中选择输入）
			int Xiaoshu = 2;//0表示没有小数，1表示有小数
			int Print = 0;//用户选择输出方式（1表示输出到文件，0表示输出屏幕）
			float a = 18, b = 6;
			float ans = 3.0;
			float result = a / b;
			Assert::AreEqual(ans, result);
		}
		TEST_METHOD(TestMethod9)//小数除法运算
		{
			int Max = 50; //最大值
			int tishu = 4;//数量
			int  Symbo = 3;//请连续输入使用的运算符（在“+”，“-”，“*”，“/”中选择输入）
			int Xiaoshu = 2;//0表示没有小数，1表示有小数
			int Print = 0;//用户选择输出方式（1表示输出到文件，0表示输出屏幕）
			float a = 0, b = 6;
			float ans = 0;
			float result = a / b;
			Assert::AreEqual(ans, result);
		}
		
	};
}
