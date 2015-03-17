package com.example.karan92.fandroidmic;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Karan92 on 3/16/2015.
 */
public class AdapterForKingdoms extends RecyclerView.Adapter<AdapterForKingdoms.ViewHolder> {

    private List<Kingdoms> mKingdoms;
    private Context mContext;
    OnItemClickListner mItemClickListener;

    public AdapterForKingdoms (Context myContext, List<Kingdoms> kingdoms){
        mContext = myContext;
        mKingdoms = kingdoms;
    }

    public interface OnItemClickListner{
        public void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(OnItemClickListner mItemClickListener)
    {
        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public AdapterForKingdoms.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v;

        v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_cardview,viewGroup,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(AdapterForKingdoms.ViewHolder viewHolder, int i) {
                Kingdoms kingdom = mKingdoms.get(i);
                viewHolder.bindMovieData(kingdom);
    }

    @Override
    public int getItemCount() {
        return mKingdoms.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView kingdomImage;
        public TextView kingdomTitle;

        public ViewHolder(final View itemView) {
            super(itemView);
            kingdomImage = (ImageView) itemView.findViewById(R.id.kingdomImg);
            kingdomTitle = (TextView) itemView.findViewById(R.id.kingdomTitle);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mItemClickListener !=null){
                        mItemClickListener.onItemClick(itemView,getPosition());
                    }
                }
            });
        }

        public void bindMovieData(Kingdoms kingdom)
        {
            kingdomTitle.setText(kingdom.getName());
            Picasso.with(kingdomImage.getContext())
                    .load(kingdom.getImage())
                    .placeholder(R.drawable.progress_animation)
                    .into(kingdomImage);
        }
    }
}
