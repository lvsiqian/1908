package com.example.a;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BFragment extends Fragment {


    private ArrayList<Pbean.ListBean> list;
    private RecyclerView rv;
    private Rvadapter rvadapter;


    public BFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_b, container, false);
        initview(inflate);
        EventBus.getDefault().register(this);
        return inflate;
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public  void revice( Pbean  pbean){
        List<Pbean.ListBean> list1 = pbean.getList();
        for (int i = 0; i < list1.size(); i++) {
            if(list1.get(i).getType()==1){
                list.addAll(list1);
                rvadapter.notifyDataSetChanged();

            }

        }

    }

    private void initview(View inflate) {
        rv = inflate.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        list = new ArrayList<>();
        rvadapter = new Rvadapter(list, getContext());
        rv.setAdapter(rvadapter);
        rvadapter.setOnitemLongClicklisten(new Rvadapter.OnitemLongClicklisten() {
            @Override
            public void OnitemLongClick(int position) {
                //弹窗
                AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                        .setMessage("是否删除？")
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                list.remove(position);
                                rvadapter.notifyDataSetChanged();
                                Toast.makeText(getContext(), "删除成功", Toast.LENGTH_SHORT).show();
                                ;
                            }
                        }).create();
                alertDialog.show();
            }
        });



    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
