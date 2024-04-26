public class HistoryClass implements History {
    private String from;
    private String to;
    private double initialValue;
    private double finalValue;

    public HistoryClass(String from, String to, double initialValue, double finalValue) {
        this.from = from;
        this.to = to;
        this.initialValue = initialValue;
        this.finalValue = finalValue;
    }

    @Override
    public String toString() {
        return "HistoryClass{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", initialValue=" + initialValue +
                ", finalValue=" + finalValue +
                '}';
    }
}
