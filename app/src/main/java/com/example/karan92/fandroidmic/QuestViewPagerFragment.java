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
public class QuestViewPagerFragment extends Fragment {


    public QuestViewPagerFragment() {
        // Required empty public constructor
    }

    public static QuestViewPagerFragment newInstance (Kingdoms.Quest quest)
    {
        QuestViewPagerFragment fragment = new QuestViewPagerFragment();
        Bundle kingdomFragment = new Bundle(0);

        //Quest Information
        kingdomFragment.putString("questId",quest.id);
        kingdomFragment.putString("questName",quest.name);
        kingdomFragment.putString("questDescription",quest.description);
        kingdomFragment.putString("questGiverImage",quest.giver.image);
        kingdomFragment.putString("questGiverId",quest.giver.id);
        kingdomFragment.putString("questGiverName",quest.giver.name);
        kingdomFragment.putString("questGiverDescription",quest.giver.description);

        fragment.setArguments(kingdomFragment);

        return fragment;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_quest_names, container, false);

        //Get the views in fragment
        TextView mQuestTitle = (TextView) rootView.findViewById(R.id.questName);
        TextView mQuestDescription = (TextView) rootView.findViewById(R.id.questNameDesc);
        TextView mQuestGiverTitle = (TextView) rootView.findViewById(R.id.questNameGiver);
        TextView mQuestGiverDescription = (TextView) rootView.findViewById(R.id.questNameGiverDesc);
        ImageView mQuestImage = (ImageView) rootView.findViewById(R.id.questImg);


        mQuestTitle.setText(getArguments().getString("questName"));
        mQuestDescription.setText(getArguments().getString("questDescription"));
        mQuestGiverTitle.setText(getArguments().getString("questGiverName"));
        mQuestGiverDescription.setText(getArguments().getString("questGiverDescription"));
        Picasso.with(mQuestImage.getContext())
                .load(getArguments().getString("questGiverImage"))
                .placeholder(R.drawable.progress_animation)
                .into(mQuestImage);

        return rootView;

    }


}
