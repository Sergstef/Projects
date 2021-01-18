#pragma once
template <typename T>
class Element {
public:
	Element* pNextElement;
	T data;
	Element(T data = T(), Element* pNextElement = nullptr);
};

