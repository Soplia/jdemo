package jrxie.jdemo.base;

/**
 * Created by jrxie on 2019/5/5.
 */

public class ResyslerViewIndicator {

    private int id ;
    private String title ;
    private ResInformation cpOne ;
    private ResInformation cpTwo ;
    private ResInformation cpThree ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ResInformation getCpOne() {
        return cpOne;
    }

    public void setCpOne(ResInformation cpOne) {
        this.cpOne = cpOne;
    }

    public ResInformation getCpTwo() {
        return cpTwo;
    }

    public void setCpTwo(ResInformation cpTwo) {
        this.cpTwo = cpTwo;
    }

    public ResInformation getCpThree() {
        return cpThree;
    }

    public void setCpThree(ResInformation cpThree) {
        this.cpThree = cpThree;
    }
}

