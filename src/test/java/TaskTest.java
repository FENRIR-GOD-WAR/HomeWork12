import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TasksTest {

    @Test

    public void simpleTaskMatchesWhenTitleContainsQuery() {

        SimpleTask task = new SimpleTask(1, "Позвонить родителям");

        Assertions.assertTrue(task.matches("родителям"));

        Assertions.assertFalse(task.matches("коллегам"));

    }

    @Test

    public void epicMatchesWhenAnySubtaskContainsQuery() {

        String[] subs = {"Молоко", "Яйца", "Хлеб"};

        Epic epic = new Epic(2, subs);

        Assertions.assertTrue(epic.matches("Яйца"));

        Assertions.assertFalse(epic.matches("Масло"));

    }

    @Test

    public void meetingMatchesWhenTopicOrProjectContainsQuery() {

        Meeting meeting = new Meeting(3, "Выкатка 3й версии приложения", "Приложение НетоБанка", "Во вторник после обеда");

        Assertions.assertTrue(meeting.matches("Выкатка"));

        Assertions.assertTrue(meeting.matches("НетоБанка"));

        Assertions.assertFalse(meeting.matches("пятница"));

    }

    @Test
    public void shouldReturnIdFromConstructor() {
        Task task = new Task(100);
        Assertions.assertEquals(100, task.getId());
    }

    @Test
    public void matchesShouldAlwaysReturnFalse() {
        Task task = new Task(1);
        Assertions.assertFalse(task.matches("anything"));
    }

    // ----- equals -----

    @Test
    public void equalsShouldBeTrueForSameObject() {
        Task task = new Task(1);
        Assertions.assertEquals(task, task);
    }

    @Test
    public void equalsShouldBeFalseForNull() {
        Task task = new Task(1);
        Assertions.assertNotEquals(task, null);
    }

    @Test
    public void equalsShouldBeFalseForDifferentClass() {
        Task task = new Task(1);
        SimpleTask other = new SimpleTask(1, "test");
        Assertions.assertNotEquals(task, other);
    }

    @Test
    public void equalsShouldBeTrueForSameId() {
        Task t1 = new Task(10);
        Task t2 = new Task(10);
        Assertions.assertEquals(t1, t2);
    }

    @Test
    public void equalsShouldBeFalseForDifferentId() {
        Task t1 = new Task(10);
        Task t2 = new Task(20);
        Assertions.assertNotEquals(t1, t2);
    }

    // ----- hashCode -----

    @Test
    public void hashCodesShouldBeEqualForSameId() {
        Task t1 = new Task(5);
        Task t2 = new Task(5);
        Assertions.assertEquals(t1.hashCode(), t2.hashCode());
    }

    @Test
    public void hashCodesShouldDifferForDifferentId() {
        Task t1 = new Task(5);
        Task t2 = new Task(6);
        Assertions.assertNotEquals(t1.hashCode(), t2.hashCode());
    }

}
