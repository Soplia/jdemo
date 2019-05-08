package jrxie.jdemo.bean;

import java.io.Serializable;

/**
 * Created by jrxie on 2019/5/5.
 */

public class ShoppingCart extends Ware implements Serializable {

    private int count ;
    private Boolean isChecked = true ;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }
}
