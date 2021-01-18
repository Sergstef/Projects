#pragma once


class BinaryNumber
{
private:
	int decimalNumber;
	string binaryNumber;
	int bit;
	void toBinary(int a);
	void toDecimal(string a);
	void addOne();
	void setDecimalNumber(int a);
	void setBinaryNumber(string a);

public:
	BinaryNumber(int decimalNumber, int bit);
	BinaryNumber(string binaryNumber, int bit);
	~BinaryNumber();
	string sum(BinaryNumber b);
	string subtraction(BinaryNumber b);
	string multiply(BinaryNumber b);
	string division(BinaryNumber b);
	int getDecimalNumber();
	string getBinaryNumber();
};