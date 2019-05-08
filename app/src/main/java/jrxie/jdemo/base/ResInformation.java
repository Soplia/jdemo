package jrxie.jdemo.base;

/**
 * Created by jrxie on 2019/5/5.
 */

public class ResInformation {

    private int id ;
    private String title ;
    private String imgUrl ;

    public ResInformation(String title , String imgUrl) {
        this.title = title ;
        this.imgUrl = imgUrl ;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
