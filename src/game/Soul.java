package game;

public class Soul {
    private int amount;
    public Soul(int startMoney){
        amount = startMoney;
    }
    public Soul(){
        amount = 100;
    }
    public void reduceMoney(int reduceAmount){
        amount -= reduceAmount;
    }
    public void addMoney(int increaseAmount){
        amount += increaseAmount;
    }

    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount){
        this.amount = amount;
    }

}
