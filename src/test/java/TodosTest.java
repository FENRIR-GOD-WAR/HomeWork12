import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TodosTest {
    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);

        Task[] expected2 = {simpleTask};
        Task[] actual2 = todos.search("родителям");
        Assertions.assertArrayEquals(expected2, actual2);

        Task[] expected3 = {epic};
        Task[] actual3 = todos.search("Яйца");
        Assertions.assertArrayEquals(expected3, actual3);

        Task[] expected4 = {meeting};
        Task[] actual4 = todos.search("НетоБанк");
        Assertions.assertArrayEquals(expected4, actual4);

        Task[] actual5 = todos.search("нет такого");
        Assertions.assertEquals(0, actual5.length);
    }

    @Test
    public void shouldMatchSimpleTaskPositive() {
        SimpleTask task = new SimpleTask(1, "Позвонить маме");
        Assertions.assertTrue(task.matches("Позвонить"));
    }

    @Test
    public void shouldMatchSimpleTaskNegative() {
        SimpleTask task = new SimpleTask(1, "Позвонить маме");
        Assertions.assertFalse(task.matches("купить"));
    }

    @Test
    public void shouldMatchEpicPositive() {
        Epic epic = new Epic(10, new String[]{"Молоко", "Яйца", "Хлеб"});
        Assertions.assertTrue(epic.matches("Яйц"));
    }

    @Test
    public void shouldMatchEpicNegative() {
        Epic epic = new Epic(10, new String[]{"Молоко", "Яйца", "Хлеб"});
        Assertions.assertFalse(epic.matches("Сыр"));
    }

    @Test
    public void shouldMatchMeetingPositiveByTopic() {
        Meeting meeting = new Meeting(15,
                "Спектакль в театре",
                "Проект А",
                "Вечером"
        );
        Assertions.assertTrue(meeting.matches("театр"));
    }

    @Test
    public void shouldMatchMeetingPositiveByProject() {
        Meeting meeting = new Meeting(15,
                "Спектакль",
                "Проект А",
                "Вечером"
        );
        Assertions.assertTrue(meeting.matches("А"));
    }

    @Test
    public void shouldMatchMeetingNegative() {
        Meeting meeting = new Meeting(15,
                "Тема",
                "Проект",
                "Днем"
        );
        Assertions.assertFalse(meeting.matches("Нет"));
    }

    // ----------- TEST TODOS -----------

    @Test
    public void shouldAddAndFindAllTasks() {
        SimpleTask simple = new SimpleTask(1, "Позвонить");
        Epic epic = new Epic(2, new String[]{"Раз", "Два"});
        Meeting meeting = new Meeting(3, "Встреча", "Проект", "Завтра");

        Todos todos = new Todos();
        todos.add(simple);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simple, epic, meeting};
        Task[] actual = todos.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchAndFindOne() {
        SimpleTask simple = new SimpleTask(1, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(2, subtasks);

        Todos todos = new Todos();
        todos.add(simple);
        todos.add(epic);

        Task[] expected = {epic};
        Task[] actual = todos.search("Яйца");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchAndFindMultiple() {
        SimpleTask t1 = new SimpleTask(1, "Купить Молоко");
        Epic t2 = new Epic(2, new String[]{"Молоко", "Хлеб"});
        Meeting t3 = new Meeting(3, "Совещание", "Молочный проект", "Сегодня");

        Todos todos = new Todos();
        todos.add(t1);
        todos.add(t2);
        todos.add(t3);

        Task[] expected = {t1, t2, t3};
        Task[] actual = todos.search("Мол");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnEmptyWhenNoMatch() {
        SimpleTask simple = new SimpleTask(1, "Позвонить");

        Todos todos = new Todos();
        todos.add(simple);

        Task[] actual = todos.search("нет");
        Assertions.assertEquals(0, actual.length);
    }

    // ----------- equals / hashCode -----------

    @Test
    public void shouldCheckEqualsPositive() {
        SimpleTask t1 = new SimpleTask(1, "Позвонить");
        SimpleTask t2 = new SimpleTask(1, "Позвонить");

        Assertions.assertEquals(t1, t2);
    }

    @Test
    public void shouldCheckEqualsNegative() {
        SimpleTask t1 = new SimpleTask(1, "Позвонить");
        SimpleTask t2 = new SimpleTask(2, "Позвонить");

        Assertions.assertNotEquals(t1, t2);
    }

    @Test
    public void shouldCheckHashCode() {
        SimpleTask t1 = new SimpleTask(1, "Позвонить");
        SimpleTask t2 = new SimpleTask(1, "Позвонить");

        Assertions.assertEquals(t1.hashCode(), t2.hashCode());
    }

}

