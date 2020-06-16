package cn.tanlw.interview;

import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 2、用户在交易下单购买时包含了A，B，C三款产品，个数不限；A、B、C各自的单价是unitPrice；
 * 由于存在一定的满减活动或者加价的购买行为，最终付款的总价是totalAmount；
 * 求每款产品按照金额占比分摊下的最终分摊后的金额是多少？
 */
public class OrderPrice {
    Long totalAmount;
    List<Product> productList;
    

    public void calculateProductAmount() {
        if (productList == null) {
            return;
        }
        //Total price
        Product product;
        long totalPrice = 0;
        for (int i = 0; i < productList.size(); i++) {
            product = productList.get(i);
            totalPrice += product.getQuantity() * product.getUnitPrice();
        }
        //TotalAmount
        totalAmount = getTotalAmount(totalPrice);
        Long amount;
        int i = 0;
        Long subTotalAmount = 0L;
        //Set amount for each product
        for (; i < productList.size() - 1; i++) {
            product = productList.get(i);
            amount = (long) (totalAmount * ((float) (product.getQuantity() * product.getUnitPrice()) / totalPrice));
            product.setAmount(amount);
            subTotalAmount += amount;
        }
        //The last one amount = total amount - previous amount
        productList.get(i).setAmount(totalAmount - subTotalAmount);
    }

    /**
     * 根据满减活动 或者 加价购买行为 计算totalAmount
     *
     * @param totalPrice
     * @return
     */
    private Long getTotalAmount(long totalPrice) {
        //满减活动计算规则
        return Long.valueOf((totalPrice / 400) * 50);
        //TODO 加价购买行为计算规则 
    }

    public static class Product {
        /**
         * 产品单价
         */
        private Long unitPrice;

        /**
         * 产品数量
         */
        private int quantity;

        /**
         * 产品分摊所得金额
         */
        private Long amount;

        public Product(Long unitPrice, int quantity) {
            this.unitPrice = unitPrice;
            this.quantity = quantity;
        }

        public Long getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(Long unitPrice) {
            this.unitPrice = unitPrice;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public Long getAmount() {
            return amount;
        }

        public void setAmount(Long amount) {
            this.amount = amount;
        }

        @Override
        public String toString() {
            return "Product{" +
                    "unitPrice=" + unitPrice +
                    ", quantity=" + quantity +
                    ", amount=" + amount +
                    "}\r\n";
        }
    }

    public static void main(String[] args) {
        OrderPrice orderPrice = new OrderPrice();
        initProductList(orderPrice);
        orderPrice.calculateProductAmount();
        System.out.println(orderPrice.productList);
    }

    private static void initProductList(OrderPrice orderPrice) {
        Product productA = new Product(100L, 2);
        Product productB = new Product(25L, 8);
        Product productC = new Product(60L, 5);
        orderPrice.productList = new ArrayList<>();
        orderPrice.productList.add(productA);
        orderPrice.productList.add(productB);
        orderPrice.productList.add(productC);
    }
}
