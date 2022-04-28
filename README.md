# Drone-System
Dronex is a Delivery system that transports medications using Drones.

# Features 
- Register Drone
- Load Medication items
- Checking Loaded medication item for a given drone
- Checking available drones for loading
- Check drone battery level for a given drone 

# Technical Features
- H2 Database
- Java, Maven
- SpringBoot,Rest Api
- Swagger

# ❗ Dependencies
To run this project, you will need of following tools installed on your machine:
Java & Kit
IntelliJ IDEA/ Apache NetBeans 
Maven-
  DevTools
  Data JPA
  Spring Web
  Swagger 3.0
  H2 Database
Postman

# Database configuration
In its default configuration, Dronex uses an in-memory database (H2) which
gets populated at startup with data. The h2 console is automatically exposed at http://localhost:8080/h2-console
and it is possible to inspect the content of the database using the jdbc:h2:mem:dronexdb.

# MVC
This application follows an architectural pattern that and is separated into logical components, including: 
the model, the view, and the controllers

![image](https://user-images.githubusercontent.com/88780929/165788667-a29acd05-5563-4fd9-90fd-56d69c1b4c81.png)

# Database Schema
-- Drone
- serialNumber
- model
- battery
- droneState

-- Model
- name
- weightLimit

-- Medication
- name
- weight
- code
- image

# Installation guide
- Clone this repository-- git clone https://github.com/audreySuz/Drone-System.git
- Enter npm install in terminal.
- Then, enter again npm run dev to run the app on dev.

# PostMan Documentation
Documentation on Apis a provided in the link https://documenter.getpostman.com/view/19411942/UyrEiFXV.
Click on link or copy into browser. 
On the right top of the webpage. 
click on run in post man. 
Import into your postman workspace and test.

# Swagger documentation UI
![image](https://user-images.githubusercontent.com/88780929/165786242-4d035c16-1ebe-405e-af47-3fe2e534068a.png)
