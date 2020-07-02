package com.example.a;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a.prestener.Mainpresetener;
import com.example.a.view.Mainview;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AFragment extends Fragment implements Mainview {


    private ArrayList<Pbean.ListBean> list;
    private Rvadapter rvadapter;
    private Mainpresetener mainpresetener;
    private int Clickposition;

    public AFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_a, container, false);
        mainpresetener = new Mainpresetener(this);
        initview(inflate);
        initData();
        return inflate;
    }

    private void initData() {
        mainpresetener.getData();

    }

    private void initview(View inflate) {
        RecyclerView rv = inflate.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        list = new ArrayList<>();
        rvadapter = new Rvadapter(list, getContext());
        rv.setAdapter(rvadapter);
        rvadapter.setOnitemClicklisten(new Rvadapter.OnitemClicklisten() {
            @Override
            public void OnitemClicklisten(int position) {
                //记录下标
                Clickposition = position;
                Intent intent = new Intent(getContext(), Main2Activity.class);
                intent.putExtra("link", list.get(position).getLink());
                startActivityForResult(intent, 100);
            }
        });
    }
    //回传结果
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 200) {
            long time = data.getLongExtra("time", 0);
            //累加时间并显示
            Pbean.ListBean listBean = list.get(Clickposition);
            time += listBean.getAddtime();
            listBean.setAddtime(time);
            rvadapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onsuccess(Pbean pbean) {
        List<Pbean.ListBean> list1 = pbean.getList();
        list.addAll(list1);
        //传值
        EventBus.getDefault().postSticky(pbean);
        rvadapter.notifyDataSetChanged();
    }

}
