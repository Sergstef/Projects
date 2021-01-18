#include <iostream>
#include <string>
#include <list>
#include <iterator>
#include "BClass.h"

using namespace std;



BinaryNumber::BinaryNumber(int decimalNumber, int bit)
{
	this->decimalNumber = decimalNumber;
	this->bit = bit;
	toBinary(decimalNumber);
}

BinaryNumber::BinaryNumber(string binaryNumber, int bit)
{
	this->binaryNumber = binaryNumber;
	this->bit = bit;
	toDecimal(binaryNumber);
}

BinaryNumber::~BinaryNumber()
{
}


void BinaryNumber::toBinary(int a)
{
	int i;
	int remainder;
	string result;
	int copyDecimalNumber = a;
	if ((a <= 0 && a >= -pow(2, bit - 1))
		|| (a >= 0 && a < pow(2, bit - 1)))
	{
		if (a >= 0)
		{
			while (copyDecimalNumber != 0)
			{
				remainder = copyDecimalNumber % 2;
				result = to_string(remainder);
				binaryNumber += result;
				copyDecimalNumber = copyDecimalNumber / 2;
			}
			reverse(binaryNumber.begin(), binaryNumber.end());


			while (binaryNumber.length() != bit)
			{
				binaryNumber.insert(0, "0");
			}

		}
		else
		{
			while (copyDecimalNumber != 0)
			{
				remainder = abs(copyDecimalNumber) % 2;
				result = to_string(remainder);
				binaryNumber += result;
				copyDecimalNumber = copyDecimalNumber / 2;
			}
			reverse(binaryNumber.begin(), binaryNumber.end());


			while (binaryNumber.length() != bit)
			{
				binaryNumber.insert(0, "0");
			}
			for (i = 0; i < binaryNumber.length(); i++)
			{
				if (binaryNumber[i] == '0')
				{
					binaryNumber[i] = '1';
				}
				else
				{
					binaryNumber[i] = '0';
				}
			}
			reverse(binaryNumber.begin(), binaryNumber.end());
			for (i = 0; i < binaryNumber.length(); i++)
			{
				if (binaryNumber[i] == '0')
				{
					binaryNumber[i] = '1';
					break;
				}
				else
				{
					binaryNumber[i] = '0';
				}
			}
			reverse(binaryNumber.begin(), binaryNumber.end());

		}
	}
	else
	{
		throw 1;
	}
}

void BinaryNumber::toDecimal(string a)
{
	int i;
	int degree = bit - 1;
	string copyBinaryNumber = a;
	decimalNumber = 0;
	if (copyBinaryNumber[0] == '1')
	{
		reverse(copyBinaryNumber.begin(), copyBinaryNumber.end());
		for (i = 0; i < copyBinaryNumber.length(); i++)
		{
			if (copyBinaryNumber[i] == '1')
			{
				copyBinaryNumber[i] = '0';
				break;
			}
			else
			{
				copyBinaryNumber[i] = '1';
			}
		}
		reverse(copyBinaryNumber.begin(), copyBinaryNumber.end());
		for (i = 0; i < copyBinaryNumber.length(); i++)
		{
			if (copyBinaryNumber[i] == '0')
			{
				copyBinaryNumber[i] = '1';
			}
			else
			{
				copyBinaryNumber[i] = '0';
			}
		}
		for (i = 0; i < copyBinaryNumber.length(); i++)
		{
			if (copyBinaryNumber[i] == '1')
			{
				decimalNumber += (1 * pow(2, degree));
				degree--;
			}
			else
			{
				decimalNumber += (0 * pow(2, degree));
				degree--;
			}
		}
		decimalNumber = -decimalNumber;
	}
	else
	{
		for (i = 0; i < copyBinaryNumber.length(); i++)
		{
			if (copyBinaryNumber[i] == '1')
			{
				decimalNumber += (1 * pow(2, degree));
				degree--;
			}
			else
			{
				decimalNumber += (0 * pow(2, degree));
				degree--;
			}
		}
	}
}

int BinaryNumber::getDecimalNumber()
{
	return decimalNumber;
}

string BinaryNumber::getBinaryNumber()
{
	return binaryNumber;
}

string BinaryNumber::sum(BinaryNumber b)
{
	string copySecondBinaryNumber = b.getBinaryNumber();
	string copyBinaryNumber = binaryNumber;
	string result;
	int i;
	if (decimalNumber + b.getDecimalNumber() < pow(2, bit - 1)
		&& decimalNumber + b.getDecimalNumber() >= -pow(2, bit - 1))
	{
		reverse(copyBinaryNumber.begin(), copyBinaryNumber.end());
		reverse(copySecondBinaryNumber.begin(), copySecondBinaryNumber.end());
		for (i = 0; i < copyBinaryNumber.length(); i++)
		{
			if (copyBinaryNumber[i] == '1' && copySecondBinaryNumber[i] == '0'
				|| copyBinaryNumber[i] == '0' && copySecondBinaryNumber[i] == '1')
			{
				result.insert(i, "1");
			}
			else if (copyBinaryNumber[i] == '1' && copySecondBinaryNumber[i] == '1')
			{
				result.insert(i, "0");
				if (copyBinaryNumber[i + 1] == '1')
				{
					copyBinaryNumber[i + 1] = '2';
				}
				else if (copyBinaryNumber[i + 1] == '0')
				{
					copyBinaryNumber[i + 1] = '1';
				}
			}
			else if (copyBinaryNumber[i] == '0' && copySecondBinaryNumber[i] == '0')
			{
				result.insert(i, "0");
			}
			else if (copyBinaryNumber[i] == '2' && copySecondBinaryNumber[i] == '0')
			{
				result.insert(i, "0");
				if (copyBinaryNumber[i + 1] == '1')
				{
					copyBinaryNumber[i + 1] = '2';
				}
				else if (copyBinaryNumber[i + 1] == '0')
				{
					copyBinaryNumber[i + 1] = '1';
				}
			}
			else if (copyBinaryNumber[i] == '2' && copySecondBinaryNumber[i] == '1')
			{
				result.insert(i, "1");
				if (copyBinaryNumber[i + 1] == '1')
				{
					copyBinaryNumber[i + 1] = '2';
				}
				else if (copyBinaryNumber[i + 1] == '0')
				{
					copyBinaryNumber[i + 1] = '1';
				}
			}
		}
		reverse(result.begin(), result.end());
		return result;
	}
	else
	{
		throw 1;
	}
}

string BinaryNumber::subtraction(BinaryNumber b)
{
	string result;
	if (decimalNumber - b.getDecimalNumber() < pow(2, bit - 1)
		&& decimalNumber - b.getDecimalNumber() >= -pow(2, bit - 1))
	{
		BinaryNumber first(decimalNumber, bit);
		BinaryNumber second(-b.getDecimalNumber(), bit);
		result = first.sum(second);
		return result;
	}
	else
	{
		throw 1;
	}
}

string BinaryNumber::multiply(BinaryNumber b)
{
	int i = 0;
	int firstMultiplier;
	int secondMultiplier;
	string strResult;
	if (decimalNumber * b.getDecimalNumber() < pow(2, bit - 1)
		&& decimalNumber * b.getDecimalNumber() >= -pow(2, bit - 1))
	{
		if (firstMultiplier == 0 || secondMultiplier == 0)
		{
			while (strResult.length() != bit)
			{
				strResult += '0';
			}
		}
		if(decimalNumber >= b.getDecimalNumber())
		{
			firstMultiplier = decimalNumber;
			secondMultiplier = b.getDecimalNumber();
		}
		else
		{
			firstMultiplier = b.getDecimalNumber();
			secondMultiplier = decimalNumber;
		}
		BinaryNumber firMul(firstMultiplier, bit);
		BinaryNumber number(firstMultiplier, bit);
		while (i != abs(secondMultiplier))
		{
			strResult = firMul.sum(number);
			firMul.setBinaryNumber(strResult);
			i++;
		}
		if (secondMultiplier < 0)
		{
			BinaryNumber res(-firMul.getDecimalNumber(), bit);
			strResult = res.getBinaryNumber();
		}
		return strResult;
	}
	else
	{
		throw 1;
	}
}

void BinaryNumber::setDecimalNumber(int a)
{
	decimalNumber = a;
}

void BinaryNumber::setBinaryNumber(string a)
{
	binaryNumber = a;
}
string BinaryNumber::division(BinaryNumber b)
{
	string strResult;
	string remainder;
	if (b.getDecimalNumber() != 0
		&& decimalNumber / b.getDecimalNumber() < pow(2, bit - 1)
		&& decimalNumber / b.getDecimalNumber() >= -pow(2, bit - 1))
	{
		string nul;
		while (nul.length() != bit)
		{
			nul += '0';
		}
		if (decimalNumber == 0)
		{
			strResult = nul;
		}
		else
		{
			BinaryNumber second(abs(b.getDecimalNumber()), bit);
			BinaryNumber copyReminder(abs(decimalNumber), bit);
			BinaryNumber res(0, bit);
			while (remainder != nul)
			{
				remainder = copyReminder.subtraction(second);
				if (remainder[0] == '1')
				{
					break;
				}
				copyReminder.setBinaryNumber(remainder);
				copyReminder.setDecimalNumber(copyReminder.getDecimalNumber() - second.getDecimalNumber());
				res.addOne();
			}
			strResult = res.getBinaryNumber();
			if (decimalNumber < 0 && b.getDecimalNumber() > 0
				|| decimalNumber > 0 && b.getDecimalNumber() < 0)
			{
				BinaryNumber res2(-res.getDecimalNumber(), bit);
				strResult = res2.getBinaryNumber();
			}
		}
		return strResult;
	}
	else
	{
		throw 1;
	}
}

void BinaryNumber::addOne()
{
	reverse(binaryNumber.begin(), binaryNumber.end());
	for (int i = 0; i < binaryNumber.length(); i++)
	{
		if (binaryNumber[i] == '0')
		{
			binaryNumber[i] = '1';
			break;
		}
		else
		{
			binaryNumber[i] = '0';
		}
	}
	decimalNumber++;
	reverse(binaryNumber.begin(), binaryNumber.end());
}



int main()
{
	setlocale(LC_ALL, "ru");


	cout << "Выберите операцию: " << endl;
	cout << "1. Создать 2 числа класса Binary " << endl;
	cout << "0. Выйти из программы " << endl;
	int n;
	int bit;
	int decimal;
	int secDec;
	cin >> n;
	while (n != 0)
	{
		switch (n)
		{
		case 1:
			cout << "Введите количество бит?(8,16,32)" << endl;
			cin >> bit;
			cout << "Введите первое десятичное число?" << endl;
			cin >> decimal;
			try
			{
				BinaryNumber a(decimal, bit);
				cout << "Операция выполнена" << endl;
				cout << "Двоичное представление числа: " + a.getBinaryNumber() << endl;
			}
			catch (const int n)
			{
				cout << "Число больше 8 бит" << endl;
				break;
			}
			BinaryNumber a(decimal, bit);
			cout << "Введите второе десятичное число?" << endl;
			cin >> secDec;
			try
			{
				BinaryNumber b(secDec, bit);
				cout << "Операция выполнена" << endl;
				cout << "Двоичное представление числа: " + b.getBinaryNumber() << endl;
			}
			catch (const int n)
			{
				cout << "Число больше 8 бит" << endl;
				break;
			}
			BinaryNumber b(secDec, bit);
			cout << "Выберите операцию: " << endl;
			cout << "1. Сложить 2 числа " << endl;
			cout << "2. Вычесть одно число из другого " << endl;
			cout << "3. Умножить 2 числа " << endl;
			cout << "4. Поделить одно число на другое " << endl;
			cout << "5. Показать двоичное и десятичное представление чисел " << endl;
			cout << "6. Создать новые числа " << endl;
			cout << endl;
			cin >> n;
			while (n != 6)
			{
				switch (n)
				{
				case 1:
					try
					{
						BinaryNumber c(a.sum(b), bit);
						cout << "Двоичное представление ответа: " + c.getBinaryNumber() << endl;
						cout << "Десятичное представление ответа: ";
						cout << c.getDecimalNumber() << endl;
						cout << "Операция выполнена" << endl;
					}
					catch (const int n)
					{
						cout << "Число больше 8 бит" << endl;
					}
					break;
				case 2:
					try
					{
						BinaryNumber c(a.subtraction(b), bit);
						cout << "Двоичное представление ответа: " + c.getBinaryNumber() << endl;
						cout << "Десятичное представление ответа: ";
						cout << c.getDecimalNumber() << endl;
						cout << "Операция выполнена" << endl;
					}
					catch (const int n)
					{
						cout << "Число больше 8 бит" << endl;
					}
					break;
				case 3:
					try
					{
						BinaryNumber c(a.multiply(b), bit);
						cout << "Двоичное представление ответа: " + c.getBinaryNumber() << endl;
						cout << "Десятичное представление ответа: ";
						cout << c.getDecimalNumber() << endl;
						cout << "Операция выполнена" << endl;
					}
					catch (const int n)
					{
						cout << "Число больше 8 бит" << endl;
					}
					break;
				case 4:
					try
					{
						BinaryNumber c(a.division(b), bit);
						cout << "Двоичное представление ответа: " + c.getBinaryNumber() << endl;
						cout << "Десятичное представление ответа: ";
						cout << c.getDecimalNumber() << endl;
						cout << "Операция выполнена" << endl;
					}
					catch (const int n)
					{
						cout << "Число больше 8 бит или второе число равно нулю." << endl;
					}
					break;
				case 5:
					cout << "Двоичное представление первого числа: " + a.getBinaryNumber() << endl;
					cout << "Десятичное представление первого числа: ";
					cout << a.getDecimalNumber() << endl;
					cout << "Двоичное представление второго числа: " + b.getBinaryNumber() << endl;
					cout << "Десятичное представление второго числа: ";
					cout << b.getDecimalNumber() << endl;
					break;
				}
				cout << endl;
				cout << "Выберите операцию: " << endl;
				cout << "1. Сложить 2 числа " << endl;
				cout << "2. Вычесть одно число из другого " << endl;
				cout << "3. Умножить 2 числа " << endl;
				cout << "4. Поделить одно число на другое " << endl;
				cout << "5. Показать двоичное и десятичное представление чисел " << endl;
				cout << "6. Создать новые числа " << endl;
				cout << endl;
				cin >> n;
			}
			break;
		}
		cout << "Выберите операцию: " << endl;
		cout << "1. Создать 2 числа класса Binary " << endl;
		cout << "0. Выйти из программы " << endl;
		cin >> n;
	}

}
