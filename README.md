# ShoppingAppFinal
Shopalog - Group 4 Final Project
Group Member	Role
Alexander - Project Manager
Conrad - UI/UX Designer
Brayden - Backend developer
Will - Frontend developer
Introduction
Shopalog will be a shopping list application to help people record and organize purchases they intend to make. With it, you will be able to create a list of items, and for each item you add to the list, you will enter the name and price of the item and optionally attach a photo. The list will be able to be sorted by item name, price, or time added, and the total cost of the items will always be displayed. Additionally, the shopping list will be able to be exported to a CSV file for use with spreadsheet apps like Excel to help you keep track of your purchases and spending.

Functional Requirements
As someone who buys groceries	I want to be able to create/edit shopping lists	So that I don't forget to buy things I need
Given the new list button is pressed	When the user chooses to either save or abandon changes	Then create a new, empty list
Given the add item button is pressed	When the required inputs are filled	Then add the item and update total cost and list (keep the sort)
Given the delete item button is pressed	When user selects 'yes' in the confirmation dialog	Then delete the item and update the list
As someone who makes a lot of purchases	I want to be able to export shopping lists to excel	So that I can keep track of purchases and spending
Given a shopping list is open	When export button is clicked open a save file dialog	Then export the list as a CSV file to the specified path, and show a dialog asking to open the file
Given a CSV file was exported	When user chooses to open file	Then open the csv file Desktop.getDesktop().open() (Only for desktop Java apps)
