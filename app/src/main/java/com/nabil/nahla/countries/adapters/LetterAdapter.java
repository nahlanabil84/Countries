package com.nabil.nahla.countries.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nabil.nahla.countries.R;
import com.nabil.nahla.countries.views.HomeActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LetterAdapter extends RecyclerView.Adapter<LetterAdapter.LetterVH> {
    ArrayList<Character> letters;

    public LetterAdapter() {
        char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        letters = new ArrayList<>();
        for (char ch : alphabet) {
            this.letters.add(ch);
        }

    }

    @NonNull
    @Override
    public LetterVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_letter, viewGroup, false);
        return new LetterVH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LetterVH letterVH, int i) {
        letterVH.emptyVH();
        letterVH.bindVH();
    }

    @Override
    public int getItemCount() {
        return letters.size();
    }

    class LetterVH extends RecyclerView.ViewHolder {

        @BindView(R.id.letterTV)
        TextView letterTV;

        public LetterVH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void emptyVH() {
            letterTV.setText("");
        }

        void bindVH() {
            letterTV.setText(letters.get(getAdapterPosition()).toString());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemView.getContext().startActivity(HomeActivity.newInstance(itemView.getContext(), letterTV.getText().toString()));
                }
            });
        }
    }
}
