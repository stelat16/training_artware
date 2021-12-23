public enum ArithmeticOperation {
    ADD("+") {
        public double apply(int a, int b) {
            return a + b;
        }
    },
    DIVIDE("/") {
        public double apply(int a, int b) {
            return (double) a / b;
        }
    },
    SUBTRACT("-") {
        public double apply(int a, int b) {
            return a - b;
        }
    },
    MULTIPLY("*") {
        public double apply(int a, int b) {
            return a * b;
        }
    };

    private final String text;

    ArithmeticOperation(String text) {
        this.text = text;
    }

    public abstract double apply(int a, int b);

    public String getText() {
        return this.text;
    }
}
