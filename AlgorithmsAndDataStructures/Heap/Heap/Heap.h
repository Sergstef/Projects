#pragma once
#include "Node.h"

template<typename T>
class Heap
{
private:
	int n;
	int number;
	Node<T>* last;
	Node<T>* root;
	void fixUp(Node<T>* a);
	void fixDown(Node<T>* a);
public:
	Heap(int n);
	~Heap();
	void insert(T data);
	Node<T>* findNode(T data);
	void deleteNode(T data);
	void printHeap(Node<T>* b, int a);
	Node<T>* getRoot();
	void extend(int a);
};
