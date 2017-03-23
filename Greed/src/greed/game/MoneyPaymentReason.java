package greed.game;

public class MoneyPaymentReason extends PaymentReason {
	private int amount;

	public MoneyPaymentReason(GreedCard paidCard, int amount) {
		super(paidCard);
		this.amount = amount;
	}
	
	public int getAmount() {
		return amount;
	}
}
