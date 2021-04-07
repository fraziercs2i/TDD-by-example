package guru.springframework;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MoneyTest {
    @Test
    void testMultiplication() {
        Money five = Money.dollar(5);
        //Dollar five = new Dollar(5);
        //Dollar product = five.times(2);
        //assertEquals(new Dollar(10), five.times(2));
        //product = five.times(3);
        //assertEquals(new Dollar(15),five.times(3));
        assertEquals(Money.dollar(10), five.times(2));
        assertEquals(Money.dollar(15), five.times(3));
        Money fiveF = Money.franc(5);
        assertEquals(Money.franc(10), fiveF.times(2));
        assertEquals(Money.franc(15),fiveF.times(3));
    }

    @Test
    void testEquality() {
        // assertEquals(new Dollar(5),new Dollar(5));
        // assertNotEquals(new Dollar(5),new Dollar(8));
        // assertNotEquals(new Dollar(5), new Franc (5));
        assertEquals(Money.dollar(5),Money.dollar(5));
        assertNotEquals(Money.dollar(5), Money.dollar(8));
        assertEquals(Money.franc(5), Money.franc(5));
        assertNotEquals(Money.dollar(5),Money.franc(8));
    }

    @Test
    void testCurrency() {
        assertEquals("USD",Money.dollar(1).currency());
        assertEquals("CHF",Money.franc(1).currency());
    }

    @Test
    void testSimpleAddition() {
        Money five = Money.dollar(5);
        Expression sum = five.plus(five);
        Bank bank = new Bank();
        Money reduced = bank.reduce(sum,"USD");
        assertEquals(Money.dollar(10), reduced);
    }

    @Test
    void testPlussReturnSum() {
        Money five = Money.dollar(5);
        Expression result = five.plus(five);
        Sum sum = (Sum) result;
        assertEquals(five,sum.augmend);
        assertEquals(five,sum.addmend);
    }

    @Test
    void ReduceSum() {
        Expression sum = new Sum(Money.dollar(3),Money.dollar(4));
        Bank bank = new Bank();
        Money result = bank.reduce(sum,"USD");
        assertEquals(Money.dollar(7), result);
    }

    @Test
    void testReduceMoney() {
        Bank bank = new Bank();
        Money result = bank.reduce(Money.dollar(1),"USD");
        assertEquals(Money.dollar(1), result);
    }
    @Test
    void testReduceMoneyDifferntCurrency(){
        Bank bank = new Bank();
        bank.addRate("CHF","USD", 2);
        Money result = bank.reduce(Money.franc(2),"USD");
        assertEquals(Money.dollar(1), result);
    }
    @Test
    void testIdentityRate(){
        assertEquals(1,new Bank().rate("USD","USD"));
        assertEquals(1,new Bank().rate("CHF","CHF"));
    }
    @Test
    public void testMixedAddition() {
        Expression fivebucks = Money.dollar(5);
        Expression tenFrancs = Money.franc(10);
        Bank bank = new Bank();
        bank.addRate("CHF","USD",2);
        Money result = bank.reduce(fivebucks.plus(tenFrancs),"USD");
        assertEquals(Money.dollar(10), result);
    }
    @Test
    public void testSumPlusMoney() {
        Expression fiveBucks = Money.dollar(5);
        Expression tenFrancs = Money.franc(10);
        Bank bank = new Bank();
        bank.addRate("CHF","USD",2);
        Expression sum = new Sum(fiveBucks,tenFrancs).plus(fiveBucks);
        Money result = bank.reduce(sum,"USD");
        assertEquals(Money.dollar(15),result);
    }
    @Test
    public void testSumTimes() {
        Expression fiveBucks = Money.dollar(5);
        Expression tenFrancs = Money.franc(10);
        Bank bank = new Bank();
        bank.addRate("CHF","USD", 2);
        Expression sum = new Sum(fiveBucks, tenFrancs).times(2);
        Money result = bank.reduce(sum,"USD");
        assertEquals(Money.dollar(20),result);
    }
}
