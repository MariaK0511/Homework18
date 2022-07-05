package task1;

public class Lines {
    private String line;

    public Lines(String line) {
        this.line = line;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    @Override
    public String toString() {
        return "Lines{" +
                "line='" + line + '\'' +
                '}';
    }
}
