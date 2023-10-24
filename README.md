# ShopALong - Group 4 Final Project

## Design Document

Brayden Cummins, Will Blaker, Conrad Hale, Alex Gajic

## Introduction

Have you ever needed to have an app to keep track of your shopping lists? Have you ever wanted to attach a picture to
the shopping list so you can remember what to buy? ShopALong can help you:

* Manage and keep track of many different shopping lists.
* Add photos to specific items in your list so you remember what the item looks like.
* Add substitution items under certain items in case the main item is out of stock.
* Export your shopping list to a CSV so you can view your list in excel.
* Add prices to your shopping items to see the total cost of your list(s).

Shopalog will be a shopping list application to help people record and organize purchases they intend to make.
With it, you will be able to create a list of items, and for each item you add to the list, you will enter the name
and price of the item and optionally attach a photo.
The list will be able to be sorted by item name, price, or time added, and the total cost of the items will always
be displayed.
Additionally, the shopping list will be able to be exported to a CSV file for use with spreadsheet apps like Excel to
help you keep track of your purchases and spending.

## Storyboard

![Untitled](https://github.com/gajicav/ShoppingAppFinal/assets/89746050/0086308b-8ed0-4ae9-b3f8-3f851c220188)

## Functional Requirements

### Requirement 1: Create/Edit Shopping Lists

#### Scenario

As a user who buys groceries, I want to be able to create/edit shopping lists.

#### Dependencies

You have items you want to add to a shopping list.

#### Assumptions

A shopping list is a list of items in a grocery store.

A grocery item consists of a name, description, price, and a photo.

#### Examples

1.1

**Given** I have a group of grocery items I want to buy.

**When** I input the items into a grocery list.

**Then** I can save the list to the app to look at/edit later.

1.2

**Given** I want to create multiple shopping lists for my next grocery visit.

**When** I create a list for the bathroom items and the kitchen items.

**Then** I can differentiate my items into different lists based on what room they go in.

1.3

**Given** I want to take pictures of the items on my shopping list.

**When** I take a picture of an item.

**Then** I can save the picture to the item in the shopping list.

1.4

**Given** The items on my shopping lists have a name, description, and price.

**When** I input the name, description, and price.

**Then** I can save the name, description, and price of the item on the shopping list.

### Requirement 2: Export shopping list to CSV

#### Scenario

As a user who makes a lot of purchases, I want to be able to export shopping lists to a CSV.

#### Dependencies

A shopping list has been created with items in the list.

#### Assumptions

Your shopping list has items in the list.

You want to export and view the list in another program.

#### Examples

2.1

**Given** I created a shopping list with items.

**When** I click the export to CSV button.

**Then** I can download a CSV file of my shopping list.

2.2

**Given** I downloaded my shopping list to a CSV final.

**When** I open the CSV file.

**Then** I can see all the items that I added to the list.

2.3

**Given** I opened the CSV file that I downloaded.

**When** I view the items in the CSV.

**Then** I can see all the attributes of the items that I entered in the app.

## Class Diagram

![image](https://github.com/gajicav/ShoppingAppFinal/assets/71296854/f9c29678-5c7e-464d-b979-f3f4f703efab)

## Class Diagram Description

**ItemDTO**: Holds the data of an item

**ShoppingListDTO:** Holds the data of a shopping list and contains a list of ItemDTO to manage items within a shopping
list.

**ItemDAO and ShoppingListDAO**: Responsible for managing the data of the application, such as saving, updating,
deleting, and retrieving items and shopping lists

**ItemService and ShoppingListService**: Use ItemDAO and ShoppingListDAO to interact with the data and perform
operations like creating, editing, deleting items and shopping lists, calculating the total cost of a list, and
exporting a list to CSV.

**ShoppingListController**: Takes the user input from the UI, calls the appropriate methods in the service layer, and
updates the UI accordingly

## Scrum Roles

* Project Manager: Alex
* Frontend Developer: Will
* Backend Developer: Brayden Cummins
* UI/UX Designer: Conrad

## TODO

* Make shopping list table so multiple lists can exist
* Add user support + mysql
* Allow exporting lists to csv
* Validate data
* Use AJAX
* What else?
