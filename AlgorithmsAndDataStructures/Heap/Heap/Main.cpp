#include <iostream>
#include <list>
#include "Heap.cpp"
#include "Node.cpp"
using namespace std;


//realization of binary tree

int main()
{
	setlocale(LC_ALL, "ru");
	cout << "1. ��������� ������" << endl;
	cout << "0. �����" << endl;
	int n;
	cin >> n;
	while (n != 0)
	{
		switch (n)
		{
		case 1:
			cout << "������� ������ ������" << endl;
			int a;
			int size = 0;
			int num;
			cin >> a;
			Heap<int> tree(a);
			cout << endl;
			cout << "1. �������� �������" << endl;
			cout << "2. ������� �������" << endl;
			cout << "3. ����� �������" << endl;
			cout << "4. ��������� ������" << endl;
			cout << "0. �����" << endl;
			cout << endl;
			cout << "������� ��������" << endl;
			cin >> a;
			while (a != 0)
			{
				switch (a)
				{
				case 1:
					cout << "������� �����" << endl;
					cin >> num;
					try
					{
						tree.insert(num);
						tree.printHeap(tree.getRoot(), 0);
					}
					catch(const int n)
					{
						cout << "������ ���������. ��������� ���." << endl;
					}
					break;
				case 2:
					cout << "������� �����, ������� ������ �������" << endl;
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
							cout << "������ ����� ��� � ������" << endl;
						}
					}
					break;
				case 3:
					cout << "������� �����, ������� ������ �����" << endl;
					cin >> num;
					if(tree.findNode(num) == nullptr)
					{
						cout << "������ ����� ��� � ������";
					}
					else
					{
						cout << "����� ����� ���� � ������" << endl;
						tree.printHeap(tree.getRoot(), 0);
					}
					break;
				case 4:
					cout << "������� �����, �� ������� ������ ��������� ������" << endl;
					cin >> num;
					tree.extend(num);
					cout << "������ ���������" << endl;
					break;
				}
				cout << endl;
				cout << "1. �������� �������" << endl;
				cout << "2. ������� �������" << endl;
				cout << "3. ����� �������" << endl;
				cout << "4. ��������� ������" << endl;
				cout << "0. �����" << endl;
				cout << endl;
				cout << "������� ��������" << endl;
				cin >> a;
			}
			break;
		}
	}
}