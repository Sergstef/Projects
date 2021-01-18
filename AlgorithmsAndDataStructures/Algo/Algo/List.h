#pragma once
#include"Element.h"
template <typename T>
class List
{
	int number;
	int size;
	Element<T>* head;

public:
	List(int a);
	~List();

	void pushBack(T data);
	void popFront();
	void clear();
	int getNumber();
	int getSize();
	void insert(T data, int index);
	void  removeAt(int index);
	void extendSize(int number);
	void findElement(T data);
	T& operator[](const int index);
};
