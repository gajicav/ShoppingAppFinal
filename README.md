# ShopALong - Group 4 Final Project
Design Document  
  
Brayden Cummins, Will Blaker, Conrad Hale, Alex Gajic

## Introduction
Have you ever needed to have an app to keep track of your shopping lists? Have you ever wanted to attach a picture to the shopping list so you can remember what to buy? ShopALong can help you:
  
* Manage and keep track of many differnet shopping lists.
* Add photos to specific items in your list so you remember what the item looks like.
* Add substitution items under certain items incase the main item is out of stock.
* Export your shopping list to a CSV so you can view your list in excel.
* Add prices to your shopping items to see the total cost of your list(s).
  
Shopalog will be a shopping list application to help people record and organize purchases they intend to make. 
With it, you will be able to create a list of items, and for each item you add to the list, you will enter the name and price of the item and optionally attach a photo. 
The list will be able to be sorted by item name, price, or time added, and the total cost of the items will always be displayed. 
Additionally, the shopping list will be able to be exported to a CSV file for use with spreadsheet apps like Excel to help you keep track of your purchases and spending.

## Storyboard

**Need to add Storyboard picture. Example picture of what the UI will look like.**

## Functional Requirements

### Requirement 1: Record Monthly House Expenses

#### Scenario

As a user living with multiple roommates, I want to be able to record our monthly house expenses.
  
#### Dependencies

Monthly house expenses are fulfilled.
  
#### Assumptions

House expenses are all in United States Dollars (USD).
  
Monthly expenses consist of rent, electric/gas, water/sewer, wifi, and other.
  
#### Examples

1.1
  
**Given** the water/sewer bill for the current month is $75.80.  

**When** I input $75.80 in the water/sewer bill box.  

**Then** I can save the $75.80 water/sewer bill to the app.
  
1.2
  
**Given** rent is $3000 for the current month.  

**When** I input $3000 in the rent box.  

**Then** I can save the $3000 rent bill to the app.  
  
1.3
  
**Given** the wifi bill for the current month is $60.  

**When** I input $60 in the wifi bill box.  

**Then** I can save the $60 wifi bill to the app.  
  
1.4
  
**Given** the electric/gas bill for the current month is $230.  

**When** I input $230 in the electric/gas bill box.  

**Then** I can save the $230 electric/gas bill to the app.
 
1.5

**Given** the other expenses is $100 for the month.

**When** I input adsadfsdsfad in the other bill box.

**Then** i will not be able to save the other expense bill to the app 
and will get a error toast message of other bill box not inputted correctly.

### Requirement 2: Send Reminders

#### Scenario

As a user inputting house expenses, each time I save an expense, all household members will receive a reminder to pay their share of that certain bill.
  
#### Dependencies

House expense has been saved to the app. 
  
#### Assumptions

Each household member has an active phone plan.
  
Each household member created an account
  
#### Examples

2.1
  
**Given** the water/sewer bill , $75.80 , for the current month has been inputted.  

**When** I click the save button.  

**Then** all household members will get a water/sewer bill reminder of $75.80 sent to their phone.
  
2.2 
  
**Given** the electric/gas bill ,  $230 , for the current month has been inputted.

**When** I click the save button.  

**Then** all household members will get an electric/gas bill reminder of $230 sent to their phone.
  
2.3 
  
**Given** the wifi bill , $60 , for the current month has been inputted. 

**When** I click the save button.  

**Then** all household members will get a wifi bill reminder of $60 sent to their phone.
  
## Class Diagram
  
**Need to add class diagram**
 
 ## Class Diagram Description
 
 **MainActivity:** The first screen that the user will see when the app loads. This screen will allow the option to enter costs/expenses.
 
 **RetrofitInstance** Bootstrap class required for Retrofit
 
 **HouseHold:** Noun class that represents a household.
 
 **Member:** Noun class that represents a member.
  
 **Payment:** Noun class that represents a payment.
 
**IHouseholdDAO:** Hold household data.

**IMemberDAO:** Hold member data.

**IPaymentDAO:** Holds payment data.

## Scrum Roles

* DevOps/Product Owner/Scrum Master: Brayden Cummins
* Frontend Developer: Will and Alex
* Integration Developer: Conrad

## Weekly Meeting

Teams Meeting Link:
