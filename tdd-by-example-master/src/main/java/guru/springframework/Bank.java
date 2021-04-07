package guru.springframework;

public class Bank {
    Money reduce(Expression source, String toCurrency){
       /* Sum sum = (Sum) source;
        int amount = sum.augmend.amount + sum.addmend.amount;
        return new Money(amount,toCurrency);*/
        //if (source instanceof Money) return (Money) source;
        //return Sum.reduce(toCurrency);
        return source.reduce(toCurrency);

    }
}
