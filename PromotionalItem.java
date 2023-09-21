package ex1;

import java.util.Date;

public class PromotionalItem {
    private String idItem;
    private long promotionalPrice;
    private Date startTime;
    private Date endTime;
    private int status;

    public PromotionalItem(String idItem, long promotionalPrice, Date startTime, Date endTime, int status) {
        this.idItem = idItem;
        this.promotionalPrice = promotionalPrice;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    public String getIdItem() {
        return idItem;
    }

    public long getPromotionalPrice() {
        return promotionalPrice;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public int isStatus() {
        return status;
    }
}
