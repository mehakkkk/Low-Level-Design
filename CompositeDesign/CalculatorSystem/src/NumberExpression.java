public class NumberExpression implements ArithmeticExpression{
    Number value;

    public NumberExpression(Number num)
    {
        this.value = num;
    }
    @Override
    public Number evaluate() {
        return value;
    }
}
