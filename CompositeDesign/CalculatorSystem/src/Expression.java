public class Expression<T extends Number> implements ArithmeticExpression{

    ArithmeticExpression<T> leftExpression;
    ArithmeticExpression<T> rightExpression;
    Operation operation;

    public Expression(ArithmeticExpression<T> leftExpression, ArithmeticExpression<T> rightExpression, Operation operation) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
        this.operation = operation;
    }

    @Override
    public Number evaluate() {
        switch(operation)
        {
            case ADD: return leftExpression.evaluate().doubleValue() + rightExpression.evaluate().doubleValue();
            case SUBTRACT: return leftExpression.evaluate().doubleValue() - rightExpression.evaluate().doubleValue();
            case MULTIPLY: return leftExpression.evaluate().doubleValue() * rightExpression.evaluate().doubleValue();
            case DIVIDE: if (rightExpression.evaluate().doubleValue() == 0) {
                            throw new ArithmeticException("Cannot divide by zero");
                        }
                        return leftExpression.evaluate().doubleValue() / rightExpression.evaluate().doubleValue();
            default:
                throw new UnsupportedOperationException("Operation not supported");
        }
    }
}
