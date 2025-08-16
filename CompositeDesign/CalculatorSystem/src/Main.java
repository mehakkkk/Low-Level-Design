public class Main {
    public static void main(String[] args) {
        // Integer example
        ArithmeticExpression<Integer> fiveInt = new NumberExpression(5);
        ArithmeticExpression<Integer> threeInt = new NumberExpression(3);
        ArithmeticExpression<Integer> additionInt = new Expression<>(fiveInt, threeInt, Operation.ADD);
        System.out.println("5 + 3 = " + additionInt.evaluate()); // Output: 8

        // Double example
        ArithmeticExpression<Double> fiveDouble = new NumberExpression(5.0);
        ArithmeticExpression<Double> twoDouble = new NumberExpression(2.0);
        ArithmeticExpression<Double> divisionDouble = new Expression<>(fiveDouble, twoDouble, Operation.DIVIDE);
        System.out.println("5.0 / 2.0 = " + divisionDouble.evaluate()); // Output: 2.5

        // Long example
        ArithmeticExpression<Long> fiveLong = new NumberExpression(5L);
        ArithmeticExpression<Long> threeLong = new NumberExpression(3L);
        ArithmeticExpression<Long> additionLong = new Expression<>(fiveLong, threeLong, Operation.ADD);
        System.out.println("5L + 3L = " + additionLong.evaluate()); // Output: 8

        // Float example
        ArithmeticExpression<Float> fiveFloat = new NumberExpression(5.0f);
        ArithmeticExpression<Float> twoFloat = new NumberExpression(2.0f);
        ArithmeticExpression<Float> divisionFloat = new Expression<>(fiveFloat, twoFloat, Operation.DIVIDE);
        System.out.println("5.0f / 2.0f = " + divisionFloat.evaluate()); // Output: 2.5

        // Short example
        ArithmeticExpression<Short> fiveShort = new NumberExpression((short) 5);
        ArithmeticExpression<Short> threeShort = new NumberExpression((short) 3);
        ArithmeticExpression<Short> additionShort = new Expression<>(fiveShort, threeShort, Operation.ADD);
        System.out.println("5 + 3 = " + additionShort.evaluate()); // Output: 8
    }
}
