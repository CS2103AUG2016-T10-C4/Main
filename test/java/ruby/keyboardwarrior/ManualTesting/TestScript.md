#**Test Script**

**Display**: text area showing the task list or help
**Input**: text field at the bottom of the GUI for entering command
**Output**: text area just above the input, that shows input feed back, will automatically fade out after awhile

###Loading Sample Data###
1. Copy the "SampleData.txt" file to the root folder of keyboard warrior
2. Rename the file to "keyboardwarrior"
2. Run the program
3. Sample data will be automatically loaded, with 15 Todos, 16 Deadlines, 19 Events.

###Help###
1. **Input**: help
2. **Output**: Description of entire List of Commands, in text format shown in display window
3. To go back to tasks list, type "list"

###Add Todo###
1. **Input**: add a todo
2. **Output**: "a todo" will appear at the 16th index under Todos, number of Todos will be updated to 16

###Add Deadline with only date###
1. **Input**: add a deadline by 081116
2. **Output**: "17) a deadline task by Tue 08-Nov-2016" will appear at the bottom of Deadlines, number of Deadlines will be updated to 17

###Add Deadline with date and time###
1. **Input**: add another deadline by 081116 2359
2. **Output**: "18) another deadline by Tue 23:59 08-Nov-2016" will appear at the bottom of Deadlines, number of Deadlines will be updated to 18

###Add Event###
1. **Input**: add an event from 081116 2359 091116 1200
2. **Output**: "20) Tue 23:59 08-Nov-2016 to Wed 12:00 09-Nov-2016	an event" will appear at the bottom of Events, number of Events will be updated to 20

###Add with tag###
1. **Input**: add task with tag #atag
2. **Output**: "17) task with tag	[atag]" will appear at the bottom of Todos, number of Todos will be updated to 17

###Add with multiple tags###
1. **Input**: add task with multiple tags #atag #btag #ctag
2. **Output**: "18) task with multiple tags	[atag][btag][ctag]" will appear at the bottom of Todos, number of Todos will be updated to 18

###List Todos###
1. **Input**: list todo
2. **Output**: only the Todos list will be shown

###List Deadlines###
1. **Input**: list deadline
2. **Output**: only the Deadlines list will be shown

###List Events###
1. **Input**: list event
2. **Output**: only the Events list will be shown

###Find by single keyword###
1. **Input**: find a
2. **Output**: 1 Todo (a todo) and 1 Deadline (a deadline task by Tue 08-Nov-2016) will be shown

###Find by multiple keyword###
1. **Input**: find a an
2. **Output**: in addition to the 1 Todo and 1 Deadline listed, 1 Event (Tue 23:59 08-Nov-2016 to Wed 12:00 09-Nov-2016	an event) will also be shown

###Find not case sensitive###
1. **Input**: find A aN
2. **Output**: the same three items (1 Todo, 1 Deadline, 1 Event) will be shown

###Clear###
1. **Input**: clear
2. **Output**: display window will be blank

###Preparing task list for further commands###
1. **Input**: add new todo1
2. **Input**: add new todo2
3. **Input**: add new todo3
4. **Input**: add new todo4
5. **Output**: 4 new tasks under Todos, number of Todos will be updated to 4

###Delete###
1. **Input**: delete 2
2. **Input**: delete 3
3. **Output**: items of index 2 and 3 on the displayed list (todo2 and todo3) are removed, number of Todos will be updated to 2
