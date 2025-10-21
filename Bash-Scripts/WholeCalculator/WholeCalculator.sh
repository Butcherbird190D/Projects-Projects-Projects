#!/bin/bash

result=0
remainder=0
operations="amsd"

echo "Welcome to the whole number calculator"

echo "Please enter the first number"
read num1

echo "Please enter the second number"
read num2

if ! [[ $num1 =~ ^[0-9] ]] || ! [[ $num2 =~ ^[0-9] ]]; then
    echo "You must enter two whole numbers"
    exit
fi

echo "Please enter the operator [a] Add, [m] Multiply, [d] Divide, [s] Subtract."
read operator

operator=$(echo "$operator" | tr '[:upper:]' '[:lower:]' )


if ! [[ "$operator" =~ [amds] ]]; then
echo "You must enter the correct operator a or m or d or s"
exit
fi


if [ $operator = "a" ]; then
    result=$(($num1 + $num2))
    echo "The sum of the two numbers is ${result}"
    exit
elif [ $operator = "m" ]; then
    result=$(($num1 * $num2))
    echo "The product of the two numbers is ${result}"
    exit
elif [ $operator = "d" ]; then
    if ! [ $num2 = 0 ]; then
        result=$(($num1 / $num2))
        remainder=$(( $num1 % $num2))
        echo "The quotient is ${result} with a whole number remainder of ${remainder}"
        exit
    else
        echo "You cannot divide by zero"
    fi
elif [ $operator = "s" ]; then
    result=$(($num1 - $num2))
    echo "The difference of the two numbers is ${result}"
    exit
fi