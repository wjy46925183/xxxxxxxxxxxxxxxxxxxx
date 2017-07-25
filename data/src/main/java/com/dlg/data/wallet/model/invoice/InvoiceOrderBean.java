package com.dlg.data.wallet.model.invoice;

/**
 * 作者：关蕤
 * 主要功能：发票管理订单列表实体
 * 创建时间：2017/7/7 11:57
 */
public class InvoiceOrderBean {
    private boolean checked;//是否选中
    private long time;//时间
    private String action;//动作
    private String player;//对象
    private int amount;//金额

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
