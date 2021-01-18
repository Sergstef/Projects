#include "Node.h"

template<typename T>
Node<T>::Node(T data, Node* pLeftNode, Node* pRightNode,
	Node* pParent, Node* pPreLast)
{
	this->data = data;
	this->pLeftNode = pLeftNode;
	this->pRightNode = pRightNode;
	this->pParent = pParent;
	this->pPreLast = pPreLast;
}