package ruby.keyboardwarrior.data.tasks;

public class Todo{
    private String todo;
    
    public Todo(String todo){
        this.todo = todo;
    }

    public String getTodo() {
        return todo;
    }
    
    public String toString(){
        return todo;
    }
}
