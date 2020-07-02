package com.example.a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Rvadapter extends RecyclerView.Adapter<Rvadapter.ViewHolder> {
    private ArrayList<Pbean.ListBean> list;
    private Context context;
    private OnitemClicklisten onitemClicklisten;
    private OnitemLongClicklisten  onitemLongClicklisten;

    public void setOnitemLongClicklisten(OnitemLongClicklisten onitemLongClicklisten) {
        this.onitemLongClicklisten = onitemLongClicklisten;
    }

    public void setOnitemClicklisten(OnitemClicklisten onitemClicklisten) {
        this.onitemClicklisten = onitemClicklisten;
    }

    public Rvadapter(ArrayList<Pbean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pbean.ListBean listBean = list.get(position);
        holder.tv_name.setText(list.get(position).getTheme());
        holder.tv_time.setText(list.get(position).getTime());
        Glide.with(context).load(list.get(position).getImage_url()).into(holder.iv);
        holder.tv_yue.setText("累加阅读时间是："+(listBean.getAddtime())+"s");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onitemClicklisten != null) {
                    onitemClicklisten.OnitemClicklisten(position);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(onitemLongClicklisten!=null){
                    onitemLongClicklisten.OnitemLongClick(position);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_name;
        public TextView tv_yue;
        public TextView tv_time;
        public ImageView iv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            this.tv_yue = (TextView) itemView.findViewById(R.id.tv_yue);
            this.tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            this.iv = (ImageView) itemView.findViewById(R.id.iv);
        }
    }

    public interface OnitemClicklisten {
        void OnitemClicklisten(int position);
    }
    public interface OnitemLongClicklisten {
        void OnitemLongClick(int position);
    }


}
