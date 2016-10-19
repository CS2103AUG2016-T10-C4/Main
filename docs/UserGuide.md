# User Guide

* [About](#about)
* [Quick Start](#quick-start)
* [Features](#features)
* [FAQ](#faq)
* [Command Summary](#command-summary)

## About

KeyboardWarrior is a scheduler and task manager application that accepts short and intuitive commands to provide a quick and convenient way to manage tasks using only the keyboard.

It allows you to schedule, reschedule, update, and delete tasks with just a single command. You will no longer have to rely on your fallible memory to manage your schedule.

## Quick Start

1. Ensure you have Java version `1.8.0_60` or later installed in your Computer.<br>
   > Having any Java 8 version is not enough. <br>
   This app will not work with earlier versions of Java 8.
   
2. Download the latest `KeyboardWarrior.jar` from the [releases](../../../releases) tab. <br>
3. Copy the file to the folder you want to use as the home folder for your KeyboardWarrior. <br>
4. Double-click the file to start the app. The GUI should appear in a few seconds. <br><br>
   <img src='images/GUI 19th Oct.jpg' width="600" height="350"> <br><br>
5. Type the command in the command box and press <kbd>Enter</kbd> to execute it. <br> 
6. Some example commands you can try:
   * **`help`** : Opens the help window.
   * **`add`**` Do CS2103 Tutorial` : 
     Adds a To-do task `Do CS2103 Tutorial` KeyboardWarrior.
   * **`delete`**` 1` : Deletes the 1st task shown.
   * **`exit`** : Exits the app. <br>
7. Refer to the [Features](#features) section below for details of each command.<br>


## Features

> **Command Format**
> * Words that are `**bolded**` are commands.
> * Words in `UPPER_CASE` are the parameters.
> * Items in `[Square Brackets]` are optional.
> * Items with `...` after them can have multiple instances.
> * Items within `(Parentheses)` with `|` between them need to have at least one of them used as the parameter.
> * The order of the format must be strictly adhered to.


#### Viewing help 
Format: `help`

> Help is also shown if you enter an incorrect command (e.g. `abcd`).
 
#### Add a To-do task
Adds a To-do task to the KeyboardWarrior<br>
Format: `add TASK`

Examples: 
* `add Buy Chocolate`
* `add Download Github`
* `add Download Eclipse`

#### Add a Deadline task
Adds a Deadline to the KeyboardWarrior<br>
Format: `add TASK by DATE [TIME]`

Examples: 
* `add Do CS2103 Tutorial by Thursday`
* `add Submit Lab report by 020314 2030`

> Acceptable `TIME` parameter format:
> * 24-hour clock format
> Acceptable `DATE` parameter format:
> * DDMMYY
> * DDMM
> * Today
> * Tomorrow
> * A specific day spelt in full
> * A specific day in abbreviated form

#### Add Event
Adds a specific task to the KeyboardWarrior that will be able to show any combinations of the following parameters:<br>

Format: `add DATE [STARTTIME [to ENDTIME]] EVENT [@VENUE]`

Examples: 
* `add 010116 1810 Go to the mall`
* `add Sunday 0210 to 0300 Group Meeting @I3 MR9`
* `add Fri 1410 to 1600 Basketball Tryouts @13 Computing Dr 117417`

#### Find item
Find all items in the **KeyboardWarrior** that contains the phrase or venue indicated<br>
Format: `find (KEYWORD | @VENUE`

Examples: 
* `find baskebtall`
* `find @I3`

#### Show Calendar
This displays a calendar of the items within the timeframe entered.<br>
Format: `show PERIOD [to ENDDATE]`

> Acceptable `PERIOD` parameter format:
> * Follows the same format as the `DATE` parameter specified earlier, as well as the following
> * **week**
> * **month**
> * A specific month spelt out in full
> * A specific month in abbreviated form
> * MONTH YYYY 

Examples: 
* `show week`
* `show month`
* `show Saturday`
* `show 0405`
* `show Feb`

#### Complete a To-do or Deadline 
Mark a To-do as complete and delete it from the KeyboardWarrior. Action is irreversible.<br>
Format: `complete INDEX...`

> Complete the calendar task at the specified `INDEX`
  The index refers to the index number shown on the calendar<br>
  The index **must be a positive integer or a letter followed by a number** 1, D2, T3, ...

Example: 
* `complete T1`<br>
   Mark To-do 1 as complete and delete it from the keep in viewfinder.
* `complete D2`<br>
   Mark deadline 2 as complete and delete it from the keep in viewfinder.
* `complete 3`<br>
   Mark task 3 as complete and delete it from the todo.

#### Delete Items from Calenda
Delete a specified calendar task from the KeyboardWarrior. Action is irreversible.<br>
Format: `remove INDEX`

> Complete the calendar task at the specified `INDEX`
  The index refers to the index number shown on the calendar<br>
  The index **must be a positive integer or a letter followed by a number** 1, D2, T3, ...

Example: 
* `delete T1`<br>
   delete To-do 1 from the keep in viewfinder.
* `delete D2`<br>
   delete Deadline 2 from the keep in viewfinder.
* `delete 3`<br>
   delete task 3 it from the viewfinder.

#### Exiting the program : `exit`
Exits the program.<br>
Format: `exit`  

#### Saving the data 
Calendar data in KeyboardWarrior are saved in the hard disk automatically after any command that changes the data.<br>
There is no need to save manually.

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with 
       the file that contains the data of your previous KeyboardWarrior folder.
       
## Command Summary

Command | Format  
-------- | :-------- 
Help | `help`
(Todo)| `add TASK`
(Deadline)| `add TASK by [DATE] [TIME]`
Add | `add DATE TIME to [TIME] TASK @ [VENUE]`
Complete | `complete INDEX`
Remove | `remove INDEX`
Find | `find [DATE] [TIME] [KEYWORD] @[VENUE]`
Show | `show [TIMEFRAME]`
Exit | `exit`
 User Guide

* [About](#about)
* [Quick Start](#quick-start)
* [Features](#features)
# User Guide

* [About](#about)
* [Quick Start](#quick-start)
* [Features](#features)
* [FAQ](#faq)
* [Command Summary](#command-summary)

## About

KeyboardWarrior is a scheduler and task manager application that accepts short and intuitive commands to provide a quick and convenient way to manage tasks using only the keyboard.

It allows you to schedule, reschedule, update, and delete tasks with just a single command. You will no longer have to rely on your fallible memory to manage your schedule.

## Quick Start

1. Ensure you have Java version `1.8.0_60` or later installed in your Computer.<br>
   > Having any Java 8 version is not enough. <br>
   This app will not work with earlier versions of Java 8.
   
2. Download the latest `KeyboardWarrior.jar` from the [releases](../../../releases) tab. <br>
3. Copy the file to the folder you want to use as the home folder for your KeyboardWarrior. <br>
4. Double-click the file to start the app. The GUI should appear in a few seconds. <br><br>
   <img src='images/GUI 19th Oct.jpg' width="600" height="350"> <br><br>
5. Type the command in the command box and press <kbd>Enter</kbd> to execute it. <br> 
6. Some example commands you can try:
   * **`help`** : Opens the help window.
   * **`add`**` Do CS2103 Tutorial` : 
     Adds a To-do task `Do CS2103 Tutorial` KeyboardWarrior.
   * **`delete`**` 1` : Deletes the 1st task shown.
   * **`exit`** : Exits the app. <br>
7. Refer to the [Features](#features) section below for details of each command.<br>


## Features

> **Command Format**
> * Words that are `**bolded**` are commands.
> * Words in `UPPER_CASE` are the parameters.
> * Items in `[Square Brackets]` are optional.
> * Items with `...` after them can have multiple instances.
> * Items within `(Parentheses)` with `|` between them need to have at least one of them used as the parameter.
> * The order of the format must be strictly adhered to.


#### Viewing help 
Format: `help`

> Help is also shown if you enter an incorrect command (e.g. `abcd`).
 
#### Add a To-do task
Adds a To-do task to the KeyboardWarrior<br>
Format: `add TASK`

Examples: 
* `add Buy Chocolate`
* `add Download Github`
* `add Download Eclipse`

#### Add a Deadline task
Adds a Deadline to the KeyboardWarrior<br>
Format: `add TASK by DATE [TIME]`

Examples: 
* `add Do CS2103 Tutorial by Thursday`
* `add Submit Lab report by 020314 2030`

> Acceptable `TIME` parameter format:
> * 24-hour clock format
> Acceptable `DATE` parameter format:
> * DDMMYY
> * DDMM
> * Today
> * Tomorrow
> * A specific day spelt in full
> * A specific day in abbreviated form

#### Add Event
Adds a specific task to the KeyboardWarrior that will be able to show any combinations of the following parameters:<br>

Format: `add DATE [STARTTIME [to ENDTIME]] EVENT [@VENUE]`

Examples: 
* `add 010116 1810 Go to the mall`
* `add Sunday 0210 to 0300 Group Meeting @I3 MR9`
* `add Fri 1410 to 1600 Basketball Tryouts @13 Computing Dr 117417`

#### Find item
Find all items in the **KeyboardWarrior** that contains the phrase or venue indicated<br>
Format: `find (KEYWORD | @VENUE`

Examples: 
* `find baskebtall`
* `find @I3`

#### Show Calendar
This displays a calendar of the items within the timeframe entered.<br>
Format: `show PERIOD [to ENDDATE]`

> Acceptable `PERIOD` parameter format:
> * Follows the same format as the `DATE` parameter specified earlier, as well as the following
> * **week**
> * **month**
> * A specific month spelt out in full
> * A specific month in abbreviated form
> * MONTH YYYY 

Examples: 
* `show week`
* `show month`
* `show Saturday`
* `show 0405`
* `show Feb`

#### Complete a To-do or Deadline 
Mark a To-do as complete and delete it from the KeyboardWarrior. Action is irreversible.<br>
Format: `complete INDEX...`

> Complete the calendar task at the specified `INDEX`
  The index refers to the index number shown on the calendar<br>
  The index **must be a positive integer or a letter followed by a number** 1, D2, T3, ...

Example: 
* `complete T1`<br>
   Mark To-do 1 as complete and delete it from the keep in viewfinder.
* `complete D2`<br>
   Mark deadline 2 as complete and delete it from the keep in viewfinder.
* `complete 3`<br>
   Mark task 3 as complete and delete it from the todo.

#### Delete Items from Calenda
Delete a specified calendar task from the KeyboardWarrior. Action is irreversible.<br>
Format: `remove INDEX`

> Complete the calendar task at the specified `INDEX`
  The index refers to the index number shown on the calendar<br>
  The index **must be a positive integer or a letter followed by a number** 1, D2, T3, ...

Example: 
* `delete T1`<br>
   delete To-do 1 from the keep in viewfinder.
* `delete D2`<br>
   delete Deadline 2 from the keep in viewfinder.
* `delete 3`<br>
   delete task 3 it from the viewfinder.

#### Exiting the program : `exit`
Exits the program.<br>
Format: `exit`  

#### Saving the data 
Calendar data in KeyboardWarrior are saved in the hard disk automatically after any command that changes the data.<br>
There is no need to save manually.

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with 
       the file that contains the data of your previous KeyboardWarrior folder.
       
## Command Summary

Command | Format  
-------- | :-------- 
Help | `help`
(Todo)| `add TASK`
(Deadline)| `add TASK by [DATE] [TIME]`
Add | `add DATE TIME to [TIME] TASK @ [VENUE]`
Complete | `complete INDEX`
Remove | `remove INDEX`
Find | `find [DATE] [TIME] [KEYWORD] @[VENUE]`
Show | `show [TIMEFRAME]`
Exit | `exit`
 [FAQ](#faq)
* [Command Summary](#command-summary)

## About

KeyboardWarrior is a scheduler and task manager application that accepts flexible command formats via the keyboard. Our application allows you to track their tasks and deadlines in a systematic way. 

This application is suitable especially for those who receives tasks that cannot be done at the moment which can then be scheduled for a later date using our application. This product is for those who require referring to tasks to be done, rescheduling and updating tasks, and discarding completed tasks or those that are unable to be done. If you prefer to type on your keyboard over using a mouse, then this product is more suitable for you as it wholly accepts keyboard inputs.

Our product provides a convenient and quick way to manage tasks as everything can be done using just the keyboard. Commands are short and intuitive and require just one or a few to complete an action. You will not have to rely on memory, which can be unreliable, to manage your schedule anymore.


## Quick Start

1. Ensure you have Java version `1.8.0_60` or later installed in your Computer.<br>
   > Having any Java 8 version is not enough. <br>
   This app will not work with earlier versions of Java 8.
   
2. Download the latest `KeyboardWarrior.jar` from the [releases](../../../releases) tab. <br>
3. Copy the file to the folder you want to use as the home folder for your KeyboardWarrior. <br>
4. Double-click the file to start the app. The GUI should appear in a few seconds. <br><br>
   <img src='images/Keyboard Warrior UI.jpg' width="600" height="350"> <br><br>
5. Type the command in the command box and press <kbd>Enter</kbd> to execute it. <br> 
> 	e.g. typing **`help`** and pressing <kbd>Enter</kbd> will open the help window.

6. Some example commands you can try:
   * **`add`**` Do CS2103 Tutorial` : 
     Adds a To-do task `Do CS2103 Tutorial` KeyboardWarrior.
   * **`remove`**` 3` : Deletes the 3rd task shown.
   * **`exit`** : Exits the app. <br>
7. Refer to the [Features](#features) section below for details of each command.<br>


## Features

> **Command Format**
> * Words in `UPPER_CASE` are the parameters.
> * Items in `SQUARE_BRACKETS` are optional.
> * Items with `...` after them can have multiple instances.
> * The order of parameters is fixed.
> * DATE parameter can be written in DDMMYY format.
> * DATE parameter can be the name of the day in full (e.g. Sunday).
> * Date parameter can be the name of the day in abbreviated form (e.g. Sun).
> * TIME parameter must be written in 24-hour clock format.

#### Viewing help : `help`
Format: `help`

> Help is also shown if you enter an incorrect command (e.g. `abcd`).
 
#### Adding a To-do: `add`
Adds a To-do task to the KeyboardWarrior<br>
Format: `add TASK`

Examples: 
* `add Buy Chocolate`
* `add Download Github`
* `add Download Eclipse`

#### Adding a Deadline: `add`
Adds a Deadline to the KeyboardWarrior<br>
Format: `add TASK by [DATE] [TIME]`

Examples: 
* `add Do CS2103 Tutorial by Thursday`
* `add Submit Lab report by 020314 2030`

#### Adding a calendar task: `add`
Adds a specific task to the KeyboardWarrior that will be able to show any combinations of the following parameters:<br>

Format: `add DATE TIME [to TIME] TASK [@VENUE]`

Examples: 
* `add 010116 1810 Go to the mall`
* `add Sunday 0210 to 0300 Group Meeting @I3 MR9`
* `add Fri 1410 to 1600 Basketball Tryouts @13 Computing Dr 117417`

#### Find a task: `find`
Find all task in the KeyboardWarrior that contains a phrase, time, date or venue<br>
Format: `find [KEYWORD] [@VENUE]`

Examples: 
* `find baskebtall`
* `find @I3`

#### Show Calendar : `show`
Shows a calendar in the KeyboardWarrior.<br>
Format: `show TIMEFRAME`

> TIMEFRAME parameter accepts the following:
> * A specific date written in DDMM format (e.g. 0102).
> * A specific day of the current week spelt in full (e.g. Monday).
> * A specific day of the current week in abbreviated form (e.g. Mon).
> * The current week.
> * The current month.
> * A specific month of the year spelt in full (e.g. August).
> * A specific month of the year in abbreviated form (e.g. Aug). 

Examples: 
* `show week`
* `show month`
* `show Saturday`
* `show 0405`
* `show Feb`

#### Complete a To-do or Deadline : `complete`
Mark a To-do as complete and delete it from the KeyboardWarrior. Action is irreversible.<br>
Format: `complete INDEX`

> Complete the calendar task at the specified `INDEX`
  The index refers to the index number shown on the calendar<br>
  The index **must be a positive integer or a letter followed by a number** 1, D2, T3, ...

Example: 
* `complete T1`<br>
   Mark To-do 1 as complete and delete it from the keep in viewfinder.
* `complete D2`<br>
   Mark deadline 2 as complete and delete it from the keep in viewfinder.
* `complete 3`<br>
   Mark task 3 as complete and delete it from the todo.

#### Delete a task from the calendar : `remove`
Delete a specified calendar task from the KeyboardWarrior. Action is irreversible.<br>
Format: `remove INDEX`

> Complete the calendar task at the specified `INDEX`
  The index refers to the index number shown on the calendar<br>
  The index **must be a positive integer or a letter followed by a number** 1, D2, T3, ...

Example: 
* `delete T1`<br>
   delete To-do 1 from the keep in viewfinder.
* `delete D2`<br>
   delete Deadline 2 from the keep in viewfinder.
* `delete 3`<br>
   delete task 3 it from the viewfinder.

#### Exiting the program : `exit`
Exits the program.<br>
Format: `exit`  

#### Saving the data 
Calendar data in KeyboardWarrior are saved in the hard disk automatically after any command that changes the data.<br>
There is no need to save manually.

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with 
       the file that contains the data of your previous KeyboardWarrior folder.
       
## Command Summary

Command | Format  
-------- | :-------- 
Help | `help`
(Todo)| `add TASK`
(Deadline)| `add TASK by [DATE] [TIME]`
Add | `add DATE TIME to [TIME] TASK @ [VENUE]`
Complete | `complete INDEX`
Remove | `remove INDEX`
Find | `find [DATE] [TIME] [KEYWORD] @[VENUE]`
Show | `show [TIMEFRAME]`
Exit | `exit`
