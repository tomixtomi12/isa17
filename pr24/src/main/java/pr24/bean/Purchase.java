package pr24.bean;

import pr24.CreditCard;
import pr24.PurchaseOrder;

public interface Purchase {
  
  public boolean processOrder(PurchaseOrder order, CreditCard card);

}
