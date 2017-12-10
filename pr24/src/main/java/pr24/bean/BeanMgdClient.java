package pr24.bean;

import pr24.CreditCard;
import pr24.PurchaseOrder;

import javax.naming.Context;
import javax.naming.InitialContext;

public class BeanMgdClient {

  public static void test() throws Exception {
    CreditCard card = new CreditCard(12, 2010, "John Doe", "411111111111");
    PurchaseOrder order = new PurchaseOrder();

    Context ctx = new InitialContext();
    Purchase purchase = (Purchase)ctx.lookup("Purchase24BeanRemote");

    boolean status = purchase.processOrder(order, card);
    System.out.println(status ? "Order processed." : "Order rejected.");
  }
}
