# Developer Guide 

## Table of Contents

* [Introduction] (#introduction)
* [Setting Up](#setting-up)
* [Architecture](#architecture)
* [UI Component](#ui-component)
* [Backend Component](#backend-component)
* [Data Component](#data-component)
* [Implementation](#implementation)
* [Testing](#testing)
* [Future Development](#future-development)
* [Appendix A: User Stories](#appendix-a--user-stories)
* [Appendix B: Use Cases](#appendix-b--use-cases)
* [Appendix C: Non Functional Requirements](#appendix-c--non-functional-requirements)
* [Appendix D: Glossary](#appendix-d--glossary)
* [Appendix E : Product Survey](#appendix-e-product-survey)

## Introduction

**KeyboardWarrior** is an intuitive task manager that will help users manage their schedule and To-do tasks. This advance tool is a Java desktop application that has a UI and can accept natural language commands via keyboard.  

This guide describes the design and implementation of **KeyboardWarrior**. It will help you understand how **KeyboardWarrior** works and how you can further contribute to its development. We have organised this guide in a top-down manner so that you can understand the big picture before moving on to the more detailed sections.

## Setting up

#### Prerequisites

You will have to:

1. Have **JDK `1.8.0_60`**  or later. <br>

    > Having any Java 8 version is not enough. <br>
    This app will not work with earlier versions of Java 8.
    
2. Have the latest version of **KeyboardWarrior** downloaded. <br>

#### Running KeyboardWarrior

1. Copy the downloaded files to the folder you want to use as the Home folder for your **KeyboardWarrior**.
2. Double-click the file to start the app.

## Architecture

<img src="images/Architecture.jpg" width="700"> <br>
> Figure: The **_Architecture Diagram_** given above explains the high-level design of the App. 

**KeyboardWarrior** is made up of four main components. You can interact with the app primarily through the command line interface (CLI). Actions initiated here by the user are then sent as commands by the UI to the Backend and Data component. The Backend component contains the Logic component which parses the command and then runs the corresponding action. The action done would also go through Storage and into the Collated Files. A response would then by reflected to the user. 

The **`UI`** component consists of JavaFX's FXML files which define the layout that is displayed to you, how you interact with it through CLI and the Java files which control these FXML files. 

The **`Backend`** component contains all the logic needed to parse your commands, store collated data into individual files, etc.

The **`Data`** component represents objects involved in the collation of source files such as the authors of the project and code snippets that were written.

The **`Test Driver`** component tests the `UI`, `Backend` and `Data` components. It utilises `JUnit` for unit testing.

The sections below give more details of each component.


## UI Component

{TODO: Insert UI image}

> Figure: Structure of the `UI` Component.

{TODO: Add UI Component outline}

## Backend Component

{TODO: Insert Backend image}

> Figure: Structure of the `Backend` Component.

{TODO: Add Backend Component outline}

## Data Component

{TODO: Insert Data image}

> Figure: Structure of the `Data` Component.

{TODO: Add Data Component outline}


## Implementation

{TODO: Add implementation outline}

## Testing

**KeyboardWarrior** uses `JUnit` to perform unit tests on the `UI`, `Backend` and `Data` components. Every method is unit tested to ensure everything works as intended.

You can find the tests in the `./test/java/ruby/keyboardwarrior` folder.

We have two types of tests:

1. **Logic Tests** - {TODO: Add description}
  
2. **Parser Tests** - {TODO: Add description}
  
 
#### Troubleshooting tests
 **Problem: Tests fail because NullPointException when AssertionError is expected**
 * Reason: Assertions are not enabled for `JUnit` tests. 
   This can happen if you are not using a recent Eclipse version (i.e. _Neon_ or later)
 * Solution: Enable assertions in `JUnit` tests as described 
   [here](http://stackoverflow.com/questions/2522897/eclipse-junit-ea-vm-option). <br>
   Delete run configurations created when you ran tests earlier.
  
## Future Development	

These are several additions that can be made to **KeyboardWarrior**	 to further increase its usefulness and usability.

### **More Commands**

More commands can be added on to the current application. This allows for greater functionality to suit various users.

> Examples:
> * Search for empty slots.
> * Add recurring tasks.
> * Add tags to task. 

### **Integration With Other Scheduler Apps**

**KeyboardWarrior** currently does not integrate with any other scheduler apps (e.g. `Google Calender`). This added functionality would be useful to attract current users of the other scheduler apps.

### **Secure Data**

Data could be secured through encryption so that only users can only access their own data to ensure privacy.

## Appendix A : User Stories

**Priorities**: High (must have) - `* * *`, Medium (nice to have)  - `* *`,  Low (unlikely to have) - `*`

Priority | As a ... | I want to ... | So that I can...
-------- | :------- | :--------- | :-----------
`* * *` | new user | see usage instructions | find out how to use the app
`* * *` | basic user | add an event with start and end time| know how long I need to spend on the event
`* * *` | basic user | add a deadline with date and time | decide what needs to be done soon
`* * *` | basic user | add a task with only description | record tasks that needs to be done some day
`* * *` | basic user | list all tasks that were added | can get a overview on all my tasks
`* * *` | basic user | find a specific task | refer to the task details quickly
`* * *` | basic user | edit a task | update the task details when I need to
`* * *` | basic user | delete a task | remove tasks that I no longer need to do
`* * *` | basic user | undo actions made | undo my mistakes quickly
`* * *` | basic user | be able to enter commands differently | have more flexibility in my commands
`* * *` | basic user | specify a folder as the data storage location | have more control on the where the data is stored
`* *` | intermediate user | add priority levels to my tasks | do more urgent tasks first
`* *` | intermediate user | clear all task that are completed | remove them at once instead individually
`* *` | intermediate user | add tags to my tasks | organize my tasks into different categories
`* *` | intermediate user | list all completed tasks | remove them if needed
`* *` | intermediate user | list all incomplete tasks | check on my progress
`* *` | intermediate user | see a list of commands I have added before | trace my action if needed
`* *` | intermediate user | redo an undone command | do not need to manually type out the changes if needed
`* *` | intermediate user | pin a tasks | keep important tasks at the top
`* *` | intermediate user | check time remaining | find out how much time remaining without manually calculating
`* *` | intermediate user | search for empty slots | I can find suitable slots to put in new tasks
`*` | advanced user | add weekly recurring tasks | do not need to add them individually
`*` | advanced user | sort my tasks in a specific order ( e.g. by priority, by date, by alphabetical) | easily find tasks in sequence
`*` | advanced user | see statistics on my tasks | know how many tasks I completed and did not complete
`*` | advanced user | reserve slots | avoid using the slots by mistake

## Appendix B : Use Cases

`Software System:` **KeyboardWarrior** <br>
`Use case:` UC01 - Adding a calendar task <br>
`Actor:` User <br>
`MSS:` <br>
1. User chooses to add a calendar task. <br>
2. User types in command and required parameters. <br>
3. **KeyboardWarrior** displays task in Calendar pane. <br>
`Use case ends.` <br>
`Extensions:` <br>
2.a. **KeyboardWarrior** detects an error in the entered parameters. <br>
2.a.1. **KeyboardWarrior** requests for the correct parameters. <br>
2.a.2. User enters new parameters. <br>
Steps 2.a.1 - 2.a.2 are repeated until the parameters entered are correct. <br>
Use case resumes from step 3. <br>
`Use case ends.` <br>

--

`Software System:` **KeyboardWarrior** <br>
`Use case:` UC02 - Finding a task <br>
`Actor:` User <br>
`MSS:`  <br>
1. User chooses to find a task. <br>
2. User types in command and required parameters. <br>
3. **KeyboardWarrior** displays task on Calendar pane. <br>
`Use case ends.` <br>
`Extensions:` <br>
2.a. **KeyboardWarrior** does not find searched task. <br>
2.a.1. **KeyboardWarrior** notifies user that there is no such task. <br>
`Use case ends.` <br>

--

`Software System:` **KeyboardWarrior** <br>
`Use case:` UC03 - Complete todo task <br>
`Actor:` User <br>
`MSS:` <br>
1. User chooses to complete a Keep in View or Deadline. <br>
2. User types in command and required parameters. <br>
3. **KeyboardWarrior** removes task from Todo pane or Deadline pane. <br>
`Use case ends.` <br>
`Extensions:` <br>
2.a. **KeyboardWarrior** does not find searched task. <br>
2.a.1. **KeyboardWarrior** notifies user that there is no such task. <br>
`Use case ends.` <br>

## Appendix C : Non Functional Requirements

* Should work on computers without internet connection.
* Should be fully CLI capable with keyboard shortcuts for mouse interactions.
*  Should work standalone in any mainstream operating system (OS) with Java 8 or higher installed.
* Should store data only in a human editable text file.
* Should be coded in OOP format.
* Should be able to hold at least 1000 individual task entries.
* Should take up less than 10mb storage space.
* Should come with automated unit tests and open source codes.
* Should work without any installer.
*  Should be able to process task efficiently by using efficient searching algorithms.
* Should be able to support command line interface.

## Appendix D : Glossary

##### Mainstream OS

> Windows, Linux, Unix, OS-X

{TODO: Add more terms to Glossary}

## Appendix E : Product Survey

{TODO: Compare pros and cons}

####Google calendar
1.	Users can create multiple calendars for different usages.
2.	Contains a world clock feature
3.	Allows for specific location to be added to the events. (In the case of multiple possible locations for a place, e.g. Macdonald's)
4.	Pop up notifications for coming up events.
5.	'Find a time' feature to automatically look for empty slots for planning an event.
6.	Countdown timer popup window.
7.	Allows users to share their calendars with one another.
8.	Users can sync or import other calendar applications with google calendars to create a master calendar.

####Todo.txt
1.	A simple app that saves all task into a single "todo.txt" file
2.	Supports command line inputs (CLI)
3.	Allows grouping of related tasks into one project
4.	Allows for priority to be set to tasks and tasks will auto arrange themselves according to their priority
5.	Able to mark tasks as complete

####Fantastical 
1.	Supports syncing with other calendar apps such as Google Calendar.
2.	When scheduling a group event, allows you to quickly find out if coworkers are available during that duration (provided coworkers are using supported Calendar apps).
3.	Has a quick access 'mini-window' for your convenience.
4.	Has separate calendar sets based on your needs, e.g Leisure Calendar, Work Calendar, etc.
5.	Able to write in natural language style.
6.	Allows Day, Week, Month and Year views.
7.	Users can type in various languages such as Spanish or Japanese.

####Cal
1.	Supports syncing with other calendar apps such as Google Calendar
2.	Specializes only in showing your daily agenda with minimal clutter
3.	Allows Syncing with any-do so that your to-do list is also imported into the calendar.
