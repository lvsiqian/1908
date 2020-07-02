package com.example.a.prestener;

import com.example.a.AFragment;
import com.example.a.Pbean;
import com.example.a.model.Mianmodel;
import com.example.a.net.ResultCallBack;
import com.example.a.view.Mainview;

public class Mainpresetener {
    private Mainview mainview;
    private Mianmodel mianmodel;

    public Mainpresetener(Mainview mainview) {

        this.mainview = mainview;
        mianmodel=new Mianmodel();
    }

    public void getData() {
        mianmodel.getdata(new ResultCallBack<Pbean>() {
            @Override
            public void onsuccess(Pbean pbean) {
                mainview.onsuccess(pbean);

            }
        });
    }
}
