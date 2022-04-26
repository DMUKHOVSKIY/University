import java.io.Serializable;

public class Marks implements Serializable {
    private String lesson;
    private int mark;

    public Marks(String lesson, int mark) {
        this.lesson = lesson;
        this.mark = mark;
    }

    public String getLesson() {
        return lesson;
    }

    public int getMark() {
        return mark;
    }

    @Override
    public String toString() {
        return lesson + '-' + mark;
    }
}
