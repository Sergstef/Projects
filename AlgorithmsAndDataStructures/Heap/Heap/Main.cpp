#include <iostream>
#include <list>
#include "Heap.cpp"
#include "Node.cpp"
using namespace std;


//realization of binary tree

int main()
{
	setlocale(LC_ALL, "ru");
	cout << "1. Построить дерево" << endl;
	cout << "0. Выйти" << endl;
	int n;
	cin >> n;
	while (n != 0)
	{
		switch (n)
		{
		case 1:
			cout << "Введите размер дерева" << endl;
			int a;
			int size = 0;
			int num;
			cin >> a;
			Heap<int> tree(a);
			cout << endl;
			cout << "1. Добавить элемент" << endl;
			cout << "2. Удалить элемент" << endl;
			cout << "3. Найти элемент" << endl;
			cout << "4. Увеличить дерево" << endl;
			cout << "0. Выйти" << endl;
			cout << endl;
			cout << "Введите операцию" << endl;
			cin >> a;
			while (a != 0)
			{
				switch (a)
				{
				case 1:
					cout << "Введите число" << endl;
					cin >> num;
					try
					{
						tree.insert(num);
						tree.printHeap(tree.getRoot(), 0);
					}
					catch(const int n)
					{
						cout << "Дерево заполнено. Расширьте его." << endl;
					}
					break;
				case 2:
					cout << "Введите число, которое хотите удалить" << endl;
					cin >> num;
					try
					{
						tree.deleteNode(num);
						if(tree.getRoot() != nullptr)
						{
							tree.printHeap(tree.getRoot(), 0);
						}
					}
					catch (const int y)
					{
						if (y == 1)
						{
							cout << "Такого числа нет в дереве" << endl;
						}
					}
					break;
				case 3:
					cout << "Введите число, которое хотите найти" << endl;
					cin >> num;
					if(tree.findNode(num) == nullptr)
					{
						cout << "Такого числа нет в дереве";
					}
					else
					{
						cout << "Такое число есть в дереве" << endl;
						tree.printHeap(tree.getRoot(), 0);
					}
					break;
				case 4:
					cout << "Введите число, на которое хотите увеличить дерево" << endl;
					cin >> num;
					tree.extend(num);
					cout << "Дерево увеличено" << endl;
					break;
				}
				cout << endl;
				cout << "1. Добавить элемент" << endl;
				cout << "2. Удалить элемент" << endl;
				cout << "3. Найти элемент" << endl;
				cout << "4. Увеличить дерево" << endl;
				cout << "0. Выйти" << endl;
				cout << endl;
				cout << "Введите операцию" << endl;
				cin >> a;
			}
			break;
		}
	}
}