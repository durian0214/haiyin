package com.durian.lib.base;

/**
 * Created by durian on 2017/11/2.
 */

public class BasePresenter  <V> {
    public  V myView ;

    public void attachView(V myView) {
        this.myView = myView;
    }

    public void detachView() {
        this.myView = null;
    }

}
