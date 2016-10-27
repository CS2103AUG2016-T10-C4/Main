<!-- @@author A0124453M -->

# User Guide

* [About Our Product](#1-about-our-product)
* [Getting Started](#2-getting-started)
* [Features](#3-features)
* [Command Summary](#4-command-summary)
* [Frequently Asked Questions (FAQ)](#5-frequently-asked-questions-faq)
* [Glossary](#6-glossary)

<br>

<!-- @@author A0139716X -->

## 1. About Our Product
<br>
KeyboardWarrior is a scheduler and task manager application that accepts short and intuitive commands to provide a quick and convenient way to manage tasks using only the keyboard.

It allows you to schedule, reschedule, update, and delete tasks with just a single command. You will no longer have to rely on your fallible memory to manage your schedule.

<br>

<!-- @@author A0124453M -->

## 2. Getting Started
<br>
### 2.1. Before Using the Application
<br>
1. Ensure you have Java version `1.8.0_60` or later installed in your Computer.
   > Having any Java 8 version is not enough. <br>
   This app will not work with earlier versions of Java 8.
   
2. Download the latest `KeyboardWarrior.jar` from the [releases](../../../releases) tab.

3. Copy the file to the folder you want to use as the home folder for your **KeyboardWarrior**.

<br>
### 2.2. Launching the Application
<br>
Double-click the file to start the app. The GUI should appear in a few seconds. <br><br>
   <img src='images/GUI 19th Oct.jpg' width="600" height="350"> <br><br>
   
### 2.3. Entering Commands
<br>
Type the command in the command box and press <kbd>Enter</kbd> to execute it.

<!-- @@author A0144556Y -->

Some example commands you can try:

   * **`help`** : Opens the help window.
   * **`add`**` Do CS2103 Tutorial` : 
     Adds a To-do task `Do CS2103 Tutorial` KeyboardWarrior.
   * **`delete`** `1` : Deletes the 1st task shown.
   * **`exit`** : Exits the app.
   
Refer to the [Features](#3-features) section below for details of each command.

<br>

<!-- @@author A0139716X -->

## 3. Features
<br>
To understand the following command formats, you can refer to these rules below:

<br>
> 1) Words that are within ** ** are commands.
> 
>     E.g. Format for adding Deadline: **add** TASK by DATE [TIME]
> 
> The word `**add**` here is the command that determines the action to be done.
> 
> 2) Words in UPPERCASE are the parameters.
>
>     E.g. Format for adding Deadline: **add** TASK by DATE [TIME]
> 
> The words `TASK`, `DATE` and `TIME` here are the parameters that represents the details of the Deadline item to be created. They should be replaced by what you want the details to be. For example, `DATE` should be replaced by a specific date of the Deadline.  
> 
> 3) Items in [square brackets] are optional.
> 
>     E.g. Format for adding Deadline: **add** TASK by DATE [TIME]
> 
> The `TIME` parameter in this case is optional. The command will still be valid even if no specific time for the `TIME` parameter is indicated.
> 
> 4) Items with ... after them can have multiple instances.
> 
>     E.g. Format for marking completed items: **complete** INDEXâ€¦
> 			
> The `...` means that the command can accept more than one `INDEX` parameter after the command word `complete`. For example, a valid command would be â€œcomplete INDEX1 INDEX2 INDEX3â€�, assuming INDEX1, INDEX2 and INDEX3 follow the proper conventions for `INDEX` parameters.
> 
> 5) Items within (parentheses) with `|` between them need to have at least one of them used as the parameter.
> 
>     E.g. Format for finding items: **find** (KEYWORD | @VENUE)
> 
> There needs to be either an input as the `KEYWORD` parameter or the `VENUE` parameter or two inputs for both. Valid commands would be â€œfind KEYWORDâ€�, â€œfind @VENUEâ€� or â€œfind KEYWORD @VENUEâ€�.
> 
> 6) The order of the format must be strictly adhered to.

<br>
#### 3.1.1. Viewing help 
<br>
Format: `**help**`

This will load the help window which will show all the available commands that can be used in **KeyboardWarrior**. It will help you learn about the commands if you are a new user or you can refer to a commandâ€™s exact function and format when needed.

<!-- @@author A0144556Y -->

<br>
Example:

`**help**`

> Displays a pop-up menu that gives the command summary. 

<!-- @@author A0124453M -->

<br>
#### 3.1.2. Add a To-do task
<br>
Format: `**add** TASK`

This adds a To-do to the schedule in **KeyboardWarrior**. A To-do is a kind of task that needs to be done by no specific deadline. It only contains a description of the task. 

It is useful for things that you might want to do in the near future but do not have a strict deadline for completion, e.g. reading a book for leisure.

<br>
> Parameter usage:
> 
> **TASK**
> 
> The TASK parameter should be the name or a short description of the To-do to be created.	

<br>
Examples: 

`**add** buy chocolate milk`

> Creates a new To-do with the description â€œBuy Chocolateâ€�.

<br>
#### 3.1.3. Add a Deadline task
<br>
Format: `**add** TASK by DATE [TIME]`

This adds a Deadline to the schedule in **KeyboardWarrior**. A Deadline is a kind of task that has to be completed by a specific deadline. Hence the date of the deadline should be specified in the command in addition to its description. A specific time here is optional.

This is suitable for most work-related tasks as they are usually scheduled within a certain timeframe to meet clientsâ€™ demands, e.g. proposal submissions. 

<br> 
> Parameter usage:
> 
> **TASK**
> 
> The TASK parameter should be the name or a short description of the Deadline to be created.
>  
> **DATE**
> 
> The DATE parameter represents the specific day of the deadline.
> 
> Acceptable formats:
> 
> > 1) DDMMYY format.
> > 
>     E.g. 160417 represents the date April 16, 2017.
> 
> > 2) DDMM format.
> > 
> The year would be automatically set as the current year.
> 
> >     E.g. 0302 represents the date February 03 of the current year. 
> 
> > 3) Today.
> >
> The deadline would be set as the current day.
> 
> >		E.g. today represents the current day.
> 
> > 4) Tomorrow.
> >
> The deadline would be set as the day after the current day.
> 
> >The following forms are accepted: tomorrow, tmr and tmrw.
> 
> >	    E.g. tmr represents the day after the current day.
> 
> > 5) A specific day spelt out in full.
> > 
> The resulting deadline would be the coming indicated day.
> 
> >If the day indicated is the same as the current day, the resulting deadline would be the same day next week.
> >
>     E.g. Sunday represents the coming Sunday.
> 
> > 6) A specific day in its accepted abbreviated form as shown below
> > 
		Sunday:		Sun
	 	Monday:	    Mon
	 	Tuesday:	Tu, Tue or Tues
	 	Wednesday:	Wed
	 	Thursday:	Th, Thu, Thur or Thurs
	 	Friday:		Fri
	 	Saturday:	Sat
> 	
>> 	The resulting deadline would be the coming indicated day.
>> 
>     E.g. Thur represents the coming Thursday
> 
> **TIME**
> 
> The TIME parameter represents the specific time of deadline.
> 
> Acceptable format:
> > 
> 24-hour clock format
> 
> >     E.g. 2030 represents the time 8:30 p.m.

<!-- @@author A0144556Y -->

<br>
Examples: 

`**add** complete keynote presentation by Wednesday`

> Creates a new Deadline with the description â€œComplete Keynote Presentationâ€� and a deadline of the coming Tuesday.

`**add** submit invoice by 171016 1700`

> Creates a new Deadline with the description â€œSubmit Invoiceâ€� and a deadline of the coming Wednesday, 5:00 p.m.

<!-- @@author A0139716X -->

<br>
#### 3.1.4. Add Event
<br>
Format: `**add** DATE [STARTTIME [to ENDTIME]] EVENT [@VENUE]`

This adds an Event to the schedule in **KeyboardWarrior**. An Event is an activity that occurs at a specific date or time.  You have the option to indicate either a time period which includes a starting and ending time or just a start time of the event. If there is no specific start time, just the day it occurs needs to be specified. The venue is also an optional parameter.

(Note: The start time needs to be indicated if an end time is indicated.) 

This is suitable for things you might have to attend at a particular time with specific end times, e.g. meetings, or without, e.g. birthday parties. It can also be used to record down an activity that you have not decided a start time for.

<br>
> Parameter usage:
> 
> **STARTTIME** and **ENDTIME**
> 
> The STARTTIME parameter represents the start time of the Event.
> 
> 
> The ENDTIME parameter represents the end time of the Event. It has to be preceded by 'to'.
> 
> Both the STARTTIME and ENDTIME parameters follows the same format as the TIME parameter specified in 3.1.3. Add Deadline Task.
> 
> **DATE**
> 
> The DATE parameter represents the day the Event is on.
> 
> The DATE parameter follows the same format as the DATE parameter specified in 3.1.3. Add Deadline Task.
> 
> **EVENT**
> 
> The EVENT parameter should be the name or a short description of the Event to be created.
> 
> **VENUE**
> 
> The VENUE parameter represents where the Event will be held.
> 
> The VENUE parameter should be the name of the venue of the Event. It has to be preceded by the @ symbol.

<br>
Examples: 

`**add** Fri 1410 to 1600 Group Meeting @I3 MR9`

> Creates a new Event with the time period as Friday, 2:10 p.m. to 4:00 p.m., the description as â€œGroup Meetingâ€� and the venue as â€œI3 MR9â€�.

<br>
#### 3.1.5. Find item
<br>
Format: `**find** (KEYWORD | @VENUE`

This allows you to find all items in **KeyboardWarrior** that contains the phrase or venue that is indicated.

It is important to note that the `KEYWORD` will also be searched in the venue details of the items. In addition, a combined search is also allowed, hence both parameters can be entered together, where the result returned would have the keyword in the itemâ€™s description and the `VENUE` parameter in the itemâ€™s venue. 

<br>
> Parameter usage:
> 
> **KEYWORD**
> 
> The KEYWORD parameter indicates the word to be searched for in the descriptions and venues of items on **KeyboardWarrior**.
> 
> **VENUE**
> 
> The VENUE parameter indicates the word to be searched only in the venues of items on **KeyboardWarrior**. It has to be preceded by the @ symbol.

<!-- @@author A0144556Y -->

<br>
Examples: 

`**find** basketball`

> Displays all items with the keyword "basketball" found in the description. 

`**find** basketball @UTSH1`

> Displays all items that has both the keyword â€œbasketballâ€� found in its description and its venue set as â€œUTSH 1â€�.

<!-- @@author A0124453M -->

<br>
#### 3.1.6. Show Calendar
<br>
Format: `**show** PERIOD [to ENDDATE]`

This displays a calendar of the items within the timeframe entered. This is useful for seeing how busy you might be during a certain period which would allow to better plan your time.

<br>
> Parameter Usage:
> 
> **PERIOD**
> 
> The PERIOD parameter represents the time period of the calendar to be displayed.
> 
> Formats for displaying all items within a particular day: <br>
> >The PERIOD parameter follows the same format as the DATE parameter specified in 3.1.3. Add Deadline Task.
> 
> Formats for displaying all items within a particular week: <br>
> >The current week. 
> >
>     E.g. week represents the current week from the current day to the same day next week.
> 
> Formats for displaying all items within a particular month: <br>
> >1) The current month.
> >
>     E.g. month represents the current month from the current day to the end of the month.
> 
> > 2) A specific month spelt out in full

> >  The resulting period would be the coming indicated month. <br>
>  If the indicated month is the current month, the resulting period would be the same month next year. 
>>
>     E.g. August represents the coming August.
> 
> > 3) A specific month in its accepted abbreviated form as shown below
> > 
> 				January:	Jan
> 				February:	Feb
> 				March:		Mar
> 				April:		Apr
> 				May:		-
> 				June:		Jun
> 				July:		Jul
> 				August:		Aug
> 				September:	Sep or Sept
> 				October:	Oct
> 				November:	Nov
> 				December:	Dec
> 
> >The resulting period would be the coming indicated month.
> >
>     E.g. Sept represents the coming September.
> 
> > 4) MONTH YYYY format.
> > 
> The resulting period would be the indicated month of the specified year.
> Either the full form or the abbreviated form can be used for the month.
> 
> >  	E.g. Sept 2017 represents September of 2017

> **ENDDATE**
> 
> The ENDDATE parameter represents the ending period of the calendar to be displayed. It must be preceded by 'to'.
> 
> If ENDDATE is a specific day, the calendar will be shown until that day inclusive.
> 
> If ENDDATE is a specific month, the calendar will be shown until the end of that month. 
> 
> If PERIOD is a specific day, the calendar will be shown from that day inclusive.
> 
> If PERIOD is a specific month, the calendar will be shown from the start of that month.
> 
> If PERIOD is the current day, week or month, the calendar will start from the current day.

<br>
Examples:

`**show** week`

> This will show all your events within the current week.

`**show** 2512`

> This will show you all your events on December 25 of the current year.

<br>
#### 3.1.7. Complete a To-do or Deadline 
<br>
Format: `**complete** INDEX...`

This marks a To-do or Deadline of the specified index as completed and in so doing, deletes the item from **KeyboardWarrior**. This action is irreversible. This should be used for tasks that have been completed and do not need to be dealt with nor remembered anymore. You can complete To-dos and Deadlines but not Events.

A list of items must be displayed before using this command.
It is possible to mark multiple tasks as completed in a single command by entering more than one `INDEX`.

<br>
> Parameter Usage:
> 
> **INDEX**
> 
> The INDEX refers to the index number corresponding to the listed item shown on the calendar.
>
> The INDEX must be a letter followed by an integer, <br>
> E.g. D2, T2

<!-- @@author A0139716X -->

<br>
Examples: 

`**complete** D1`

> Mark Deadline 1 as complete and delete it from the keep in viewfinder.
   
`**complete** T1 D2`

> Mark To-do 1 and Dealine 2 as complete and delete it from the keep in viewfinder.

<br>
#### 3.1.8. Delete Items from Calendar
<br>
Format: `**delete** INDEX`

This command will delete the item of the specified index from **KeyboardWarrior**. This action is irreversible. This may be used for tasks that were mistakenly added, tasks that no longer need to be completed or Events that have passed. You can delete To-dos, Deadlines and Events.

A list of items must be displayed before using this command.
It is possible to delete multiple items in a single command by entering more than one `INDEX`.

<br>
> Parameter Usage:
> 
> **INDEX**
> 
> The INDEX refers to the index number corresponding to the listed item shown on the calendar.
>
> The INDEX must be a letter followed by an integer or just an integer, <br>
> E.g. D2, 3, T2

<br>
Examples: 

`**delete** 4 5` 

> The events with index number 4 and 5 are removed from the list. 

<br>
#### 3.1.9. Exiting the program 
<br>
Format: `**exit**`  

This command exits the program, all your events and task inside your calendar will be saved.

<br>
### 3.2 Saving the data 
Calendar data in **KeyboardWarrior** are saved in the hard disk automatically after any command that changes the data.

There is no need to save manually.

<br>     
  
## 4. Command Summary
<br>

Command | Format  
-------- | :-------- 
Help | `**help**`
Add (Todo)| `**add** TASK`
Add (Deadline)| `**add** TASK by DATE [TIME]`
Add (Event) | `**add** DATE [TIME to [TIME]] EVENT [@VENUE]`
Complete | `**complete** INDEX...`
Delete | `**delete** INDEX...`
Find | `**find** (KEYWORD | @VENUE)`
Show | `**show ** PERIOD [to ENDDATE]`
Exit | `**exit**`

<br>

<!-- @@author A0124453M -->

## 5. Frequently Asked Questions (FAQ)
<br>

**Q**: How do I transfer my data to another Computer?

**A**: Install the app in the other computer and overwrite the empty data file it creates with 
       the file that contains the data of your previous **KeyboardWarrior** folder.

<br>       

<!-- @@author A0139716X -->

## 6. Glossary
<br>

**GUI (Graphical User Interface):** <br>
> The visual elements that allow a user to interact with **KeyboardWarrior** on their computer.

**Parameter:** <br>
> A characteristic detail of the task that is input by the user.

**Overwrite:** <br>
> Replacing old data with new data.

**Calendar:** <br>
> A list of items by date within a specified timeframe.

**To-do:** <br>
> A task that requires completion by no specific deadline.

**Deadline:** <br>
> A task that requires complete by a specific deadline.

**Event:** <br>
> A significant happening or activity that occurs within a specific timeframe.
