package greed.game;

public class PaymentReason implements Reason {
	private GreedCard paidCard;
	
	public PaymentReason(GreedCard paidCard) {
		this.paidCard = paidCard;
	}

	public GreedCard getPaidCard() {
		return paidCard;
	}

}
