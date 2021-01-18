#include <iostream>
#include "Element.cpp"
#include "List.cpp"
using namespace std;



int main()
{
	setlocale(LC_ALL, "ru");
	cout << "Сначала создайте список." << endl;
	cout << "Введите операцию которую хотите сделать: 1.Создать список;" << endl;
	int a;
	int size;
	cin >> a;
	while (a != 1)
	{
		cout << "Ошибка! Нажмите 1, чтобы создать список." << endl;
		cin >> a;
	}
	if (a == 1)
	{
		cout << "Введите размер списка" << endl;
		cin >> size;
	}
	List<int> lst(size);
	cout << endl;

	cout << "Введите операцию которую хотите сделать:" << endl;
	cout << "1. Завершить программу;" << endl;
	cout << "2. Вставить элемент;" << endl;
	cout << "3. Удалить элемент;" << endl;
	cout << "4. Расширить лист;" << endl;
	cout << "5. Удалить лист;" << endl;
	cout << "6. Найти элемент по индексу;" << endl;
	cout << "7. Найти элемент по значению;" << endl;
	cout << "8. Вывести список;" << endl;
	cout << "9. Получить размер списка;" << endl;
	cout << "10. Получить количество элементов в списке;" << endl;
	cout << "11. Добавить элемент по индексу;" << endl;
	cout << "12. Удалить элемент по индексу;" << endl;
	cout << endl;
	cin >> a;
	int index;
	int number;
	int element;
	while (a != 1)
	{
		if (a > 12 || a < 1)
		{
			cout << "Ошибка! Такой операции нет." << endl;
			cout << endl;
		}
		switch (a)
		{
		case 2:
			cout << "Введите цифру, которую хотите вставить" << endl;
			cout << endl;
			number;
			cin >> number;
			try
			{
				lst.pushBack(number);
				cout << "Операция выполнена" << endl;
				cout << endl;
			}
			catch (const int n)
			{
				cout << "Список переполнен. Расширьте его" << endl;
				cout << endl;
			}
			break;
		case 3:
			try
			{
				lst.popFront();
				cout << "Операция выполнена" << endl;
				cout << endl;
			}
			catch (const int n)
			{
				cout << "Нет элементов в листе" << endl;
				cout << endl;
			}
			break;
		case 4:
			cout << "Введите число, на которое хотите увеличить лист" << endl;
			cout << endl;
			cin >> number;
			lst.extendSize(number);
			break;
		case 5:
			lst.clear();
			cout << "Лист удален" << endl;
			cout << endl;
			cout << "Нажмите 1 для завершения работы программы" << endl;
			break;
		case 6:
			cout << "Введите индекс элемента" << endl;
			cout << endl;
			cin >> index;
			cout << endl;
			if (index < lst.getNumber() && index >= 0)
			{
				cout << "Найденный элемент " << lst[index] << endl;
				cout << endl;
			}
			else
			{
				cout << "Такого элемента не существует" << endl;
				cout << endl;
			}
			break;
		case 7:
			cout << "Введите элемент, который вы хотите найти" << endl;
			cout << endl;
			cin >> element;
			cout << endl;
			cout << "Индекс элемента в списке" << endl;
			cout << endl;
			try
			{
				lst.findElement(element);
				cout << "Операция выполнена" << endl;
				cout << endl;
			}
			catch (const int n)
			{
				cout << "Нет такого элемента" << endl;
				cout << endl;
			}
			break;
		case 8:
			for (int i = 0; i < lst.getNumber(); i++)
			{
				cout << lst[i] << " ";
				cout << endl;
			}
			break;
		case 9:
			cout << lst.getSize() << endl;
			break;
		case 10:
			cout << lst.getNumber() << endl;
			break;
		case 11:
			cout << "Введите цифру, которую хотите положить в список" << endl;
			cout << endl;
			cin >> number;
			cout << "Введите индекс в списке" << endl;
			cout << endl;
			cin >> index;
			try
			{
				lst.insert(number, index);
				cout << "Операция выполнена" << endl;
				cout << endl;
			}
			catch (const int n)
			{
				if (n == 1)
				{
					cout << "Список переполнен. Расширьте его" << endl;
					cout << endl;
				}
				if (n == 2)
				{
					cout << "Такого индекса не существует" << endl;
					cout << endl;
				}
			}
			break;
		case 12:
			cout << "Введите индекс элемента, который хотите удалить" << endl;
			cout << endl;
			cin >> index;
			try
			{
				lst.removeAt(index);
				cout << "Операция выполнена" << endl;
				cout << endl;
			}
			catch (const int n)
			{
				if (n == 1)
				{
					cout << "Элемента с таким индексом не существует" << endl;
					cout << endl;
				}
				if (n == 2)
				{
					cout << "Нет элементов в листе" << endl;
					cout << endl;
				}
			}
			break;
		}
		cout << endl;
		cout << "Введите операцию которую хотите сделать:" << endl;
		cout << "1. Завершить программу;" << endl;
		cout << "2. Вставить элемент;" << endl;
		cout << "3. Удалить элемент;" << endl;
		cout << "4. Расширить лист;" << endl;
		cout << "5. Удалить лист;" << endl;
		cout << "6. Найти элемент по индексу;" << endl;
		cout << "7. Найти элемент по значению;" << endl;
		cout << "8. Вывести список;" << endl;
		cout << "9. Получить размер списка;" << endl;
		cout << "10. Получить количество элементов в списке;" << endl;
		cout << "11. Добавить элемент по индексу;" << endl;
		cout << "12. Удалить элемент по индексу;" << endl;
		cout << endl;
		cin >> a;
	}



	/*lst.push(2);
	lst.push(6);
	lst.push(7);
	lst.push(8);
	lst.push(9);
	lst.push(10);
	lst.extendSize();
	lst.push(10);
	for(int i = 0; i < lst.getNumber(); i++)
	{
		cout << lst[i] << " ";
	}
	cout << endl;
	lst.findElement(10);
	cout << "Found element is " << lst[3] << endl;
	cout << lst.getNumber() << endl;
	lst.popFront();
	for (int i = 0; i < lst.getNumber(); i++)
	{
		cout << lst[i] << " ";
	}
	cout << endl;
	cout << lst.getNumber() << endl;
	cout << "Метод clear" << endl;
	lst.clear();
	cout << lst.getNumber() << endl;


	setlocale(LC_ALL, "ru");

	BinaryNumber num(178);
	BinaryNumber num1(63);
	BinaryNumber num2(8);
	num.toBinary();
	num1.toBinary();
	num2.toBinary();
	cout << num.getBinaryNumber() << endl;
	cout << num1.getBinaryNumber() << endl;
	cout << num2.getBinaryNumber() << endl;

	*/

}
