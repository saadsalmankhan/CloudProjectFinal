package com.l124320.cloudprojectfinal;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 840 G1 on 4/29/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.RoomViewHolder> implements View.OnClickListener{




    public class RoomViewHolder extends RecyclerView.ViewHolder{

        TextView RoomNameAdap;
        TextView RoomPriceAdap;
        TextView RoomAvailAdap;
        TextView RoomDetAdap;
        ImageView RoomImageAdap;
        Button BookRoomAdap;


        public RoomViewHolder(View v) {
            super(v);
            RoomNameAdap=v.findViewById(R.id.textViewRoomNameRe);
            RoomPriceAdap=v.findViewById(R.id.textViewRoomPriceRe);
            RoomAvailAdap=v.findViewById(R.id.textviewAvailRe);
            RoomImageAdap=v.findViewById(R.id.imageViewRoom);
            BookRoomAdap=v.findViewById(R.id.buttonBookRe);
            //RoomDetAdap=v.findViewById(R.id.editTextDetailR)

        }


    }


    private List<hotelroom> data;
    private int layouts;

    public MyAdapter(List<hotelroom> data, int layouts) {
        this.data = data;
        this.layouts = layouts;
    }

    @Override
    public int getItemViewType(int position){
        if(data.get(position) !=null){
            return 0;
        }
        else{
            return -1;
        }
    }


    @Override
    public MyAdapter.RoomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType == 0) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.roomviewholder, parent, false);
            return new RoomViewHolder(v);

        }
        else{
            return null;
        }
    }

    @Override
    public void onBindViewHolder(RoomViewHolder holder, int position) {
        if(data != null && holder != null){
            //int i = getItemViewType(position);
            holder.RoomNameAdap.setText(data.get(position).getRoomName());
            holder.RoomPriceAdap.setText(data.get(position).getPrice());
            holder.RoomAvailAdap.setText(data.get(position).getAvailibility());
            //((RoomViewHolder)holder).Room.setText(((hotelroom)data.get(position)).getRoomName());
            //((RoomViewHolder)holder).RoomImageAdap.setImageBitmap(((hotelroom)data.get(position)).get());

        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }



    @Override
    public void onClick(View v) {
        //if(v.getId()==BookRoomAdap)
    }
}
