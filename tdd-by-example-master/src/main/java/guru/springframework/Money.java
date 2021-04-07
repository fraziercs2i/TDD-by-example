package guru.springframework;

public class Money implements Expression{
    protected final int amount;
    protected final String currency;

    public Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    //protected abstract String currency();
    //public Money times(int multiplier) {
       // return null;
    //}
    protected String currency() {
        return currency;
    }
    public static Money dollar (int amount) {
        return new  Money (amount,"USD");
    }
    public static Money franc(int amount) {
        return new Money(amount,"CHF");
    }
    public boolean equals(Object object) {
        Money money = (Money) object;
        return amount == money.amount
                && this.currency == money.currency;
       // return amount == money.amount
        //       && getClass().equals(object.getClass()); // to check if a franc is equal to dollar
        //return true;
    }
    @Override
    public Money reduce(Bank bank, String to){
        //return this;
         // int rate =(currency.equals("CHF") && to.equals("USD")) ? 2 : 1;
        return new Money(amount / bank.rate(this.currency,to), to);
    }
    @Override
    public String toString() {
        return "Money{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }
    public Expression times(int multiplier){
        //amount *= multiplier;
        //return Money.dollar(amount * multiplier);
        return new Money(amount * multiplier, this.currency);
    }
    public Expression plus(Expression addend){
        //return new Money(amount + addend.amount, currency);
        return new Sum(this,addend);
    }
}
