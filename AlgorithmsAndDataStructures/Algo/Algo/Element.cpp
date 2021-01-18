#include"Element.h"
using namespace std;

template <typename T>
Element<T>::Element(T data, Element* pNextElement)
{
	this->data = data;
	this->pNextElement = pNextElement;
}
