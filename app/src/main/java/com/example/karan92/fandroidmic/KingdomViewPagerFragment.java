package com.example.karan92.fandroidmic;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class KingdomViewPagerFragment extends Fragment {


    public KingdomViewPagerFragment() {
        // Required empty public constructor
    }

    public static KingdomViewPagerFragment newInstance(Kingdoms kingdom)
    {
        KingdomViewPagerFragment fragment = new KingdomViewPagerFragment();
        Bundle kingdomFragment = new Bundle(0);
        kingdomFragment.putString("kingdomId", kingdom.getId());
        kingdomFragment.putString("kingdomName", kingdom.getName());
        kingdomFragment.putString("kingdomClimate", kingdom.getClimate());
        kingdomFragment.putString("kingdomPopulation", kingdom.getPopulation());
        kingdomFragment.putString("kingdomLanguage", kingdom.getLanguage());
        kingdomFragment.putString("kingdomImage", kingdom.getImage());
        fragment.setArguments(kingdomFragment);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_kingdom_name, container, false);


        //Get the views in fragment
        TextView mKingdomTitle = (TextView) rootView.findViewById(R.id.kingdomTitle);
        TextView mKingdomClimate = (TextView) rootView.findViewById(R.id.kingdomClimateR);
        TextView mKingdomPopulation = (TextView) rootView.findViewById(R.id.kingdomPopulationR);
        TextView mKingdomLanguage = (TextView) rootView.findViewById(R.id.kingdomLangR);
        ImageView mKingdomImage = (ImageView) rootView.findViewById(R.id.kingdomImg);

        mKingdomTitle.setText(getArguments().getString("kingdomName"));
        mKingdomClimate.setText(getArguments().getString("kingdomClimate"));
        mKingdomPopulation.setText(getArguments().getString("kingdomPopulation"));
        if(getArguments().getString("kingdomLanguage")==null) {
            mKingdomLanguage.setText("Language is Not Provided");
        }
        else{
            mKingdomLanguage.setText(getArguments().getString("kingdomLanguage"));
        }


        Picasso.with(mKingdomImage.getContext())
                .load(getArguments().getString("kingdomImage"))
                .placeholder(R.drawable.progress)
                .into(mKingdomImage);

        return rootView;
    }


}
