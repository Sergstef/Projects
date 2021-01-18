#pragma once

template<typename T>
class Node
{
public:
	T data;
	Node* pLeftNode;
	Node* pRightNode;
	Node* pParent;
	Node* pPreLast;
	Node(T data = T(), Node* pLeftNode = nullptr, Node* pRightNode = nullptr,
		Node* pParent = nullptr, Node* pPreLast = nullptr);
};