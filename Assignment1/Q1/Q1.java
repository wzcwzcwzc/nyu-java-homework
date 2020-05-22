// Zancheng Wang
// zw2316@nyu.edu
// Assignment #1
// 10 Feb 2020
//
// This file shows two characteristics of Java


// 1. inheritance

/*
class in java can inherit traits from other classes,
when class A inherit from class B, the class A will get 
all of variables and methods from B.

In the Figure 1, class Student and Professor inherit from Person Class,
which means both Student and Professor have name, phone number, email
and they also have eat() function.

(Figure 1 is in the zip file)

*/

// 2. Polymorphism

/*
Polymorphism means many different shapes
you can have different objects from a group of classes,
each with the same name.

Class Animal has a function called eat()
Class Dog and Class Cat inherit Animal

and then we can use
Animal a = new Cat()
a.eat()
to get eat() function in Class cat
*/

abstract class Animal{
	public abstract void eat();
}

class Dog extends Animal{
	public void eat(){
    	System.out.println("meat");

   	}
}

class Cat extends Animal{
	public void eat(){
      	System.out.println("fish");
   	}
}

class Q1{
	// Polymorphism
	public static void main(String[] args) {
		Animal a = new Cat();
		Animal b = new Dog();
		a.eat();
		b.eat();
	}
}