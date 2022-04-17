package com.example.javawebutvecklingprojekt.views;

import com.example.javawebutvecklingprojekt.entities.Todo;
import com.example.javawebutvecklingprojekt.services.TodoService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;

//import static com.example.javawebutvecklingprojekt.views.TodoManager.binder;

public class TodoForm extends FormLayout {

    Binder<Todo> binder = new BeanValidationBinder<>(Todo.class);
//    TextField nameField = new TextField("Name");
    TextField todo = new TextField("Todo");
    TextField place = new TextField("Place");
    TextField day = new TextField("Day");
    TextField tid = new TextField("Time");
    Button saveButton = new Button("Add");

    TodoService todoService;
    TodoManager todoView;
    public TodoForm(TodoService todoService, TodoManager todoView , String name){
        this.todoService = todoService;
        this.todoView = todoView;

        binder.bindInstanceFields(this);

        saveButton.addClickListener(e -> onSave(name));

        add( todo, place, day, tid, saveButton);
    }

    private void onSave(String name) {
        Todo todo = binder.validate().getBinder().getBean();
        todoService.createNewTodo(todo , name);

        setFormVisible(null);
        todoView.updateItems();

        this.getParent().ifPresent(component -> {
            if (component instanceof Dialog)
                ((Dialog) component).close();
        });
    }

    public void setFormVisible(Todo todo){
        if (todo != null){
            binder.setBean(todo);
            setVisible(true);
        }else
            setVisible(false);
    }
}
