


<!-- PROJECT LOGO -->
<br />
<p align="center">

  <h3 align="center">Electronic Store Simulator</h3>

  <p align="center">
    A Java FX Stock Management System
    <br />
  </p>
</p>


<!-- ABOUT THE PROJECT -->
## About The Project

Electronic Store Simulator is a Java FX application that allows a user to add a variety of electronics to a cart and simulate purchases. It is a project that is meant to showcase the basics of object oriented design.

Features:
* Each product has an associated stock and price, which is used to generate data for revenue, $/Sale, number of sales, and most popular items
* Each product has its own set of fields (ex: laptop -> CPU speed, RAM, Storage, etc)
* Products can be added/removed to a cart. The stock of the items, as well as the cart's price is calculated dynamically.

Store with nothing in cart and no purchases:
![ElectronicsStoreScreenShot1](https://user-images.githubusercontent.com/38918965/104411065-b9945380-5537-11eb-882f-0d861c8c4e1c.png)

Store with some items in cart.
![ElectronicsStoreScreenShot2](https://user-images.githubusercontent.com/38918965/104411146-e7799800-5537-11eb-8d44-b72b739f4af4.png)

Store with these items purchased:
![image](https://user-images.githubusercontent.com/38918965/104411294-1d1e8100-5538-11eb-8073-2962a3ed75c3.png)

## A Brief Overview of the Inheritance Model:
The electronics store has 4 main products: Fridge, Toaster Oven, Laptop, and Desktop. The products are organized in an inheritance model as follows:
1. All products share the fields for price, current stock, sold stock, and shelf stock. Therefore all products are subclasses of the Product Class.
2. The Fridge and ToasterOven share fields for wattage, and color, so they are subclasses of the Applicance class.
3. The Laptop and Desktop share fields for CPU speed, ram, storage, and SSD(contains/does not contain), so they are subclasses of the Computer class.

<!-- GETTING STARTED -->
## Using Intellij IDEA:
Unzip the full project and open the folder as a project using intellij. To run the program, make sure that the run configuration has the following in VM options: <br>
"--module-path <Java FX Path> --add-modules=javafx.controls".
<br>
Then run, the program from ElectronicStoreApp.java.
