#include "List.h"
#include <iostream>
using namespace std;

template<typename T>
List<T>::List(int a)
{
	number = 0;
	size = a;
	head = nullptr;
}

template <typename T>
List<T>::~List()
{
	clear();
}
template <typename T>
void List<T>::pushBack(T data)
{
	number++;
	if (number > size)
	{
		number--;
		throw 1;
	}
	if (head == nullptr)
	{
		head = new Element<T>(data);
	}
	else if (head != nullptr && number <= size)
	{
		Element<T>* current = this->head;
		while (current->pNextElement != nullptr)
		{
			current = current->pNextElement;
		}
		current->pNextElement = new Element<T>(data);
	}
}

template<typename T>
void List<T>::popFront()
{
	if (number != 0)
	{
		Element<T>* first = head;
		head = head->pNextElement;
		delete first;
		number--;
	}
	else
	{
		throw 1;
	}

}

template<typename T>
void List<T>::clear()
{
	while (number)
	{
		popFront();
	}
}

template<typename T>
int List<T>::getNumber()
{
	return number;
}

template<typename T>
int List<T>::getSize()
{
	return size;
}

template<typename T>
void List<T>::insert(T data, int index)
{

	if (index <= number && index >= 0)
	{
		if (number + 1 <= size)
		{
			if (index == 0)
			{
				head = new Element<T>(data, head);
			}
			if (index == number && index != 0)
			{
				pushBack(data);
				number--;
			}
			if (index > 0 && index < number)
			{
				int counter = 1;
				Element<T>* previous = this->head;
				while (index)
				{
					if (counter == index)
					{
						Element<T>* newElement = new Element<T>(data, previous->pNextElement);
						previous->pNextElement = newElement;
					}
					counter++;
					previous = previous->pNextElement;
				}
			}
			number++;
		}
		else
		{
			throw 1;
		}
	}
	else
	{
		throw 2;
	}
}

template<typename T>
void List<T>::removeAt(int index)
{
	if (number != 0)
	{
		if (index < size && index >= 0)
		{
			if (index >= number)
			{
				throw 1;
			}
			if (index == 0)
			{
				popFront();
			}
			if (index > 0 && index < number)
			{
				Element<T>* previous = this->head;
				int counter = 1;
				while (counter != index + 1)
				{
					if (counter == index)
					{
						Element<T>* deletedElement = previous->pNextElement;
						previous->pNextElement = deletedElement->pNextElement;
						delete deletedElement;
						number--;
					}
					counter++;
					previous = previous->pNextElement;
				}
			}
		}
		else
		{
			throw 1;
		}

	}
	else
	{
		throw 2;
	}

}

template<typename T>
void List<T>::extendSize(int number)
{
	size = size + number;
}



template<typename T>
void List<T>::findElement(T data)
{
	int numberOfElements = 0;
	int index = 0;
	Element<T>* current = this->head;
	while (current != nullptr)
	{
		if (current->data == data)
		{
			numberOfElements++;
			cout << index << endl;
		}
		current = current->pNextElement;
		index++;
	}
	if (numberOfElements == 0)
	{
		throw 1;
	}

}
template<typename T>
T& List<T>::operator[](int index)
{
	int counter = 0;
	Element<T>* current = this->head;
	while (current != nullptr)
	{
		if (counter == index)
		{
			return current->data;
		}
		current = current->pNextElement;
		counter++;
	}
		
	
}