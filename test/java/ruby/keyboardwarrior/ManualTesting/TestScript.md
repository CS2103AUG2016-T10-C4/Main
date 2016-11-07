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
2. **Output**: "17) a deadline by Tue 08-Nov-2016" will appear at the bottom of Deadlines, number of Deadlines will be updated to 17

###Add Deadline with date and time###
1. **Input**: add another deadline by 081116 2359
2. **Output**: "18) another deadline by Tue 23:59 08-Nov-2016" will appear at the bottom of Deadlines, number of Deadlines will be updated to 18

###Add Event###
1. **Input**: add an event from 081116 2359 091116 1200
2. **Output**: "20) Tue 23:59 08-Nov-2016 to Wed 12:00 09-Nov-2016	an event" will appear at the bottom of Events, number of Events will be updated to 20

###Add Todo with tag###
1. **Input**: add task with tag #atag
2. **Output**: "17) task with tag	[atag]" will appear at the bottom of Todos, number of Todos will be updated to 17

###Add Deadline with tag###
1. **Input**: add a deadline with tag by 081116 #atag
2. **Output**: "19) a deadline with tag by Tue 08-Nov-2016 [atag]" will appear at the bottom of Deadlines, number of Deadlines will be updated to 19

###Add Event with tag###
1. **Input**: add an event with tag from 081116 2359 091116 1200 #atag
2. **Output**: "21) Tue 23:59 08-Nov-2016 to Wed 12:00 09-Nov-2016	an event with tag [atag]" will appear at the bottom of Events, number of Events will be updated to 21

###Add item with multiple tags###
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
2. **Output**: 1 Todo ("a todo") and 1 Deadline ("a deadline task by Tue 08-Nov-2016") will be shown

###Find by multiple keyword###
1. **Input**: find a an
2. **Output**: in addition to the 1 Todo and 1 Deadline listed, 1 Event ("Tue 23:59 08-Nov-2016 to Wed 12:00 09-Nov-2016	an event") will also be shown

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
5. **Output**: 4 new items under Todos, number of Todos will be updated to 4

###Delete###
1. **Input**: list todo
2. **Output**: the Todos list of 4 items will be shown
3. **Input**: delete 2
4. **Input**: delete 3
5. **Output**: Todo of index 2 and 3 on the displayed list ("new todo2" and "new todo3") will be removed, number of Todos will be updated to 2

###Edit###
1. **Input**: list todo
2. **Output**: the Todos list of 2 items will be shown
3. **Input**: edit 2 changed todo4
4. **Output**: Todo of index 2 on the displayed list ("new todo4") will be changed to "changed todo4", number of Todos remain at 2

###Undo add###
1. **Input**: add deadline1 by 081116
2. **Output**: "1) deadline1 by Tue 08-Nov-2016" will appear at the bottom of Deadlines, number of Deadlines will be updated to 1
3. **Input**: undo
4. **Output**: Deadline that was just added ("deadline 1 by Tue 08-Nov-2016") will be removed, number of Deadlines will be updated to 0

###Undo delete##
1. **Input**: list todo
2. **Output**: the Todos list of 2 items will be shown
3. **Input**: delete 1
4. **Output**: Todo of index 1 on the displayed list ("new todo1") will be removed, number of Todos will be updated to 1
5. **Input**: undo
6. **Output**: Todo that was just delete ("new todo1") will be added back to the list of Todos, number of Todos will be updated to 2

###Exit###
1. **Input**: Exit
2. **Output**: program closes
