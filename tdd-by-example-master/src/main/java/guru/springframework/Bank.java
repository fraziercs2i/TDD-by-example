package guru.springframework;

import java.util.HashMap;

public class Bank {
    private final HashMap<Pair, Integer> rateMap = new HashMap<>();
    Money reduce(Expression source, String toCurrency){
       /* Sum sum = (Sum) source;
        int amount = sum.augmend.amount + sum.addmend.amount;
        return new Money(amount,toCurrency);*/
        //if (source instanceof Money) return (Money) source;
        //return Sum.reduce(toCurrency);
        return source.reduce(this, toCurrency);
    }
    public int rate(String from, String to){
        // return (from.equals("CHF")&&to.equals("USD")) ? 2:1;
        if (from.equals(to))  {
            return 1;
        }
         return rateMap.get(new Pair(from,to));
    }

    public void addRate(String from, String to, int rate) {
        rateMap.put(new Pair(from,to),rate);
    }
}
