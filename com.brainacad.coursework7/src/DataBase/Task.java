package DataBase;

import java.util.List;
import java.util.Map;

/**
 * Created by Зая on 18.02.2016.
 */
public class Task {
    private int id;
    private String description;
    private static int countId;
    private TaskType type;

    public Task(String description,TaskType t) {
        this.description = description;
        id = countId++;
        type=t;

    }

    public TaskType getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Task" +
                "id=" + id +
                ", description=" + description + '\'' +
                ", type=" + type +
                "\n";
    }
}


