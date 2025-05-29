package Delivery_simlulator;

public interface PaymentStrategy {
    public abstract void pay(int money);
}

class CreditCardPayment implements PaymentStrategy{
    @Override
    public void pay(int money) {
        System.out.println("CreditCard Payment");
    }
}

class KakaoPayPayment implements PaymentStrategy{
    @Override
    public void pay(int money) {
        System.out.println("KakaoPay Payment");
    }
}

class BankTransferPayment implements PaymentStrategy{
    @Override
    public void pay(int money) {
        System.out.println("BankTransfer Payment");
    }
}