package com.nabil.nahla.countries.adapters;

import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestBuilder;
import com.nabil.nahla.countries.R;
import com.nabil.nahla.countries.models.Country;
import com.nabil.nahla.countries.utils.glideSVG.GlideApp;
import com.nabil.nahla.countries.utils.glideSVG.SvgSoftwareLayerSetter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryVH> {
    ArrayList<Country> countries;
    RequestBuilder<PictureDrawable> requestBuilder;

    public CountryAdapter(ArrayList<Country> countries) {
        this.countries = countries;
    }

    @NonNull
    @Override
    public CountryVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_country, viewGroup, false);
        return new CountryVH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryVH countryVH, int i) {
        countryVH.emptyVH();

        requestBuilder = GlideApp.with(countryVH.itemView.getContext())
                .as(PictureDrawable.class)
                .error(R.drawable.ic_worldwide)
                .placeholder(R.drawable.ic_worldwide)
                .fitCenter()
                .transition(withCrossFade())
                .listener(new SvgSoftwareLayerSetter());

        Uri uri = Uri.parse(countries.get(i).getFlag());
        requestBuilder.load(uri)
                .into(countryVH.flagIV);

        countryVH.nameTV.setText(countries.get(i).getName());
        countryVH.capitalTV.setText(countries.get(i).getCapital());
        countryVH.continentTV.setText(countries.get(i).getRegion());
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    class CountryVH extends RecyclerView.ViewHolder {

        @BindView(R.id.flagIV)
        ImageView flagIV;
        @BindView(R.id.nameTV)
        TextView nameTV;
        @BindView(R.id.capitalTV)
        TextView capitalTV;
        @BindView(R.id.continentTV)
        TextView continentTV;

        public CountryVH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void emptyVH() {
            GlideApp.with(itemView.getContext())
                    .load(R.drawable.ic_worldwide)
                    .error(R.drawable.ic_worldwide)
                    .placeholder(R.drawable.ic_worldwide)
                    .fitCenter()
                    .into(flagIV);

            nameTV.setText("");
            capitalTV.setText("");
            continentTV.setText("");
        }
    }
}
