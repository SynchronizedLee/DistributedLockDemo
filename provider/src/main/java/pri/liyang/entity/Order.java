package pri.liyang.entity;

import java.io.Serializable;

/**
 * (Order)实体类
 *
 * @author makejava
 * @since 2021-01-31 22:40:49
 */
public class Order implements Serializable {
    private static final long serialVersionUID = 965028988720664012L;
    /**
     * 订单id
     */
    private Integer orderId;
    /**
     * 订单状态，0是未被抢，1是已被抢
     */
    private Integer orderState;


    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

}