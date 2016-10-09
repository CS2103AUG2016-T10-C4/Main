package ruby.keyboardwarrior.data;

import java.util.ArrayList;

import ruby.keyboardwarrior.data.tasks.Todo;

public class Todolist {
    private ArrayList<Todo> todolist;
    
    public Todolist(){
        todolist = new ArrayList<Todo>();
    }

    public ArrayList<Todo> getTodolist() {
        return todolist;
    }
    
    public ArrayList<String> toStringArray(){
       ArrayList<String> stringarray= new ArrayList<String>(todolist.size());
       for (Todo todo : todolist){
           stringarray.add(todo.getTodo());
       }
       return stringarray;
    }
    
    public void add(Todo todo){
        todolist.add(todo);
    }
}
