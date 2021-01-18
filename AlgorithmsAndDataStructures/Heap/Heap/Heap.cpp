#include <iostream>
#include <algorithm>
#include <iomanip>
#include "Heap.h"

using namespace std;

template<typename T>
Heap<T>::Heap(int n)
{
	this->n = n;
	number = 0;
	root = nullptr;
	last = nullptr;
}


template<typename T>
Heap<T>::~Heap()
{
	delete root;
	delete last;
}


template<typename T>
void Heap<T>::insert(T data)
{
	if(number + 1 > n)
	{
		throw 1;
	}
	else
	{
		if (root == nullptr) {
			root = new Node<T>(data);
			last = root;
		}
		else
		{
			Node<T>* cur = last;
			while (cur->pParent != nullptr && cur == cur->pParent->pRightNode) {
				cur = cur->pParent;
			}

			if (cur->pParent != nullptr) {
				if (cur->pParent->pRightNode != nullptr) {
					cur = cur->pParent->pRightNode;
					while (cur->pLeftNode != nullptr)
					{
						cur = cur->pLeftNode;
					}
				}
				else {
					cur = cur->pParent;
				}
			}
			else {
				while (cur->pLeftNode != nullptr) {
					cur = cur->pLeftNode;
				}
			}

			if (cur->pLeftNode != nullptr)
			{
				cur->pRightNode = new Node<T>(data, nullptr, nullptr, cur);
				Node<T>* tmp = last;
				last = cur->pRightNode;
				last->pPreLast = tmp;
			}
			else
			{
				cur->pLeftNode = new Node<T>(data, nullptr, nullptr, cur);
				Node<T>* tmp = last;
				last = cur->pLeftNode;
				last->pPreLast = tmp;
			}
			fixUp(last);
		}
		number++;
	}
	

}

template<typename T>
void Heap<T>::fixUp(Node<T>* a)
{
	while (a->pParent != nullptr && a->pParent->data < a->data)
	{
		Node<T>* tmp = new Node<T>(a->data);
		a->data = a->pParent->data;
		a->pParent->data = tmp->data;
		a = a->pParent;
	}
}

template<typename T>
Node<T>* Heap<T>::findNode(T data)
{
	int size = 1;
	Node<T>* cur = root;
	if(number > 0)
	{
		while (size != number)
		{
			if (cur->data == data)
			{
				return cur;
			}
			else
			{
				if (cur->pLeftNode != nullptr)
				{
					cur = cur->pLeftNode;
					size++;
				}
				else
				{
					if (cur == cur->pParent->pLeftNode)
					{
						if (cur->pParent->pRightNode != nullptr)
						{
							cur = cur->pParent->pRightNode;
							size++;
						}
						else
						{
							cur = cur->pParent->pParent->pRightNode;
							size++;
						}
					}
					else
					{
						while (cur != cur->pParent->pLeftNode)
						{
							cur = cur->pParent;
						}
						cur = cur->pParent->pRightNode;
						size++;
					}
				}
			}
		}
		if (cur->data == data)
		{
			return cur;
		}
		else
		{
			return nullptr;
		}
	}
	else
	{
		return nullptr;
	}
	

}

template<typename T>
void Heap<T>::deleteNode(T data)
{
	int i = 0;
	while(true)
	{
		Node<T>* cur = findNode(data);
		if (cur != nullptr)
		{
			if(number == 1)
			{
				root = nullptr;
				number--;
				break;
			}
			else
			{
				fixDown(cur);
				number--;
				i++;
			}
		}
		else
		{
			if(i == 0)
			{
				throw 1;
			}
			break;
		}
	}
}

template<typename T>
void Heap<T>::fixDown(Node<T>* cur)
{
	while (cur->pLeftNode != nullptr)
	{
		if (cur->pRightNode != nullptr)
		{
			if (cur->pLeftNode->data >= cur->pRightNode->data)
			{
				cur->data = cur->pLeftNode->data;
				cur = cur->pLeftNode;
			}
			else
			{
				cur->data = cur->pRightNode->data;
				cur = cur->pRightNode;
			}
		}
		else
		{
			cur->data = cur->pLeftNode->data;
			cur = cur->pLeftNode;
		}
	}
	if (cur == last)
	{
		Node<T>* tmp = last;
		last = cur->pPreLast;
		if (tmp == tmp->pParent->pLeftNode)
		{
			tmp->pParent->pLeftNode = nullptr;
		}
		else
		{
			tmp->pParent->pRightNode = nullptr;
		}
	}
	else
	{
		cur->data = last->data;
		fixUp(cur);
		Node<T>* tmp = last;
		last = last->pPreLast;
		if (tmp == tmp->pParent->pLeftNode)
		{
			tmp->pParent->pLeftNode = nullptr;
		}
		else
		{
			tmp->pParent->pRightNode = nullptr;
		}
	}
}

template<typename T>
void Heap<T>::printHeap(Node<T>* p, int a)
{
	if (p != nullptr) {
		if (p->pRightNode) {
			printHeap(p->pRightNode, a + 4);
		}
		if (a) {
			cout << setw(a) << ' ';
		}
		if (p->pRightNode) std::cout << " /\n" << std::setw(a) << ' ';
		std::cout << p->data << "\n ";
		if (p->pLeftNode) {
			cout << setw(a) << ' ' << " \\\n";
			printHeap(p->pLeftNode, a + 4);
		}
	}
}

template<typename T>
Node<T>* Heap<T>::getRoot()
{
	return root;
}

template<typename T>
void Heap<T>::extend(int a)
{
	n = n + a;
}





