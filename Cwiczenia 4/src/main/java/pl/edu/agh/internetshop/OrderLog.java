package pl.edu.agh.internetshop;

import java.util.Date;

public class OrderLog {
    public Order order;
    private Date date;

    public OrderLog(Order order) {
        this.order = order;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
