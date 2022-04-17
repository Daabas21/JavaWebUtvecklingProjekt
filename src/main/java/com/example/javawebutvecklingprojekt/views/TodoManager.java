package com.example.javawebutvecklingprojekt.views;

import com.example.javawebutvecklingprojekt.entities.Todo;
import com.example.javawebutvecklingprojekt.security.PrincipalUtils;
import com.example.javawebutvecklingprojekt.services.TodoService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.editor.Editor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;
import java.util.List;

@Route(value = "/", layout = AppView.class)
@PermitAll
public class TodoManager extends VerticalLayout {

    //    TextField nameField = new TextField();
    TextField todoField = new TextField();
    TextField placeField = new TextField();
    TextField dayField = new TextField();
    TextField timeField = new TextField();

    TodoService todoService;

    Grid<Todo> grid = new Grid<>(Todo.class, false);
    Editor<Todo> editor = grid.getEditor();

    Binder<Todo> binder = new BeanValidationBinder<>(Todo.class);

    public TodoManager(TodoService todoService) {
        this.todoService = todoService;

        ValidationMessage nameValidationMessage = new ValidationMessage();
        ValidationMessage todoValidationMessage = new ValidationMessage();
        ValidationMessage placeValidationMessage = new ValidationMessage();

        setAlignItems(Alignment.CENTER);
        add(new H1("Todo view"));

        grid.setItems(todoService.findAll(PrincipalUtils.getName()));

        grid.addComponentColumn(todo -> {
                    Button deleteButton = new Button(new Icon(VaadinIcon.TRASH), e -> {
                        todoService.deleteTodoById(todo.getId());
                        updateItems();
                    });

                    deleteButton.addThemeVariants(ButtonVariant.LUMO_ICON,
                            ButtonVariant.LUMO_ERROR,
                            ButtonVariant.LUMO_TERTIARY
                    );
                    return deleteButton;
                }
        );
//        Grid.Column<Todo> nameColumn = grid.addColumn(todo -> todo.getTodoUser().getName()).setHeader("Name").setWidth("120px");
        Grid.Column<Todo> todoColumn = grid.addColumn(Todo::getTodo).setHeader("Todo");
        Grid.Column<Todo> placeColumn = grid.addColumn(Todo::getPlace).setHeader("Place");
        Grid.Column<Todo> dayColumn = grid.addColumn(Todo::getDay).setHeader("Day");
        Grid.Column<Todo> timeColumn = grid.addColumn(Todo::getTid).setHeader("Time").setSortable(true);
        Grid.Column<Todo> editColumn = grid.addComponentColumn(todo ->
                new Button("Edit", e -> {
                    if (editor.isOpen())
                        editor.cancel();
                    grid.getEditor().editItem(todo);
                })
        ).setWidth("150px").setFlexGrow(0);

        editor.setBinder(binder);
        editor.setBuffered(false);

//        nameField.setWidthFull();
//        binder.forField(nameField)
//                .asRequired("Mame must not be empty")
//                .withStatusLabel(nameValidationMessage)
//                .bind(todo -> todo.getTodoUser().getName(), (todo, s) -> todo.getTodoUser().setName(s));
//        nameColumn.setEditorComponent(nameField);

        todoField.setWidthFull();
        binder.forField(todoField).asRequired("Todo must not be empty")
                .withStatusLabel(todoValidationMessage)
                .bind(Todo::getTodo, Todo::setTodo);
        todoColumn.setEditorComponent(todoField);

        placeField.setWidthFull();
        binder.forField(placeField).asRequired("Place must not be empty")
                .withStatusLabel(placeValidationMessage)
                .bind(Todo::getPlace, Todo::setPlace);
        placeColumn.setEditorComponent(placeField);

        dayField.setWidthFull();
        binder.forField(dayField).bind(Todo::getDay, Todo::setDay);
        dayColumn.setEditorComponent(dayField);

        timeField.setWidthFull();
        binder.forField(timeField).bind(Todo::getTid, Todo::setTid);
        timeColumn.setEditorComponent(timeField);

        Button saveButton = new Button("Save", e -> {
            editSave();
            updateItems();
        });
        Button cancelButton = new Button(VaadinIcon.CLOSE.create(),
                e -> editor.cancel());
        cancelButton.addThemeVariants(ButtonVariant.LUMO_ICON,
                ButtonVariant.LUMO_ERROR);
        HorizontalLayout actions = new HorizontalLayout(saveButton,
                cancelButton);
        actions.setPadding(false);
        editColumn.setEditorComponent(actions);

        editor.addCancelListener(e -> {
            nameValidationMessage.setText("");
            todoValidationMessage.setText("");
            placeValidationMessage.setText("");
        });

        getThemeList().clear();
        getThemeList().add("spacing-s");

        add(grid, nameValidationMessage, todoValidationMessage,
                placeValidationMessage);


        Button addButton = new Button("Add Todo", e -> {
            Dialog dialog = new Dialog();
            TodoForm todoForm = new TodoForm(todoService, this , PrincipalUtils.getName());
            todoForm.setFormVisible(new Todo());
            dialog.add(todoForm);
            dialog.open();
        });

        add(addButton);

    }

    private void editSave() {
        Todo todo = binder.validate().getBinder().getBean();
        Notification.show("submited");
        todoService.updateTodoById(todo.getId(), todo);
        editor.save();
    }

    public void updateItems() {
        grid.setItems(todoService.findAll(PrincipalUtils.getName()));
    }


}
