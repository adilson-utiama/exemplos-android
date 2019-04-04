package com.asuprojects.testescomponentes.expandablerecyclerview;

import android.view.View;
import android.widget.TextView;
import com.asuprojects.testescomponentes.R;
import com.asuprojects.testescomponentes.expandablerecyclerview.lib.ChildViewHolder;

public class MoviesViewHolder extends ChildViewHolder {

    private TextView mMoviesTextView;

    public MoviesViewHolder(View itemView) {
        super(itemView);
        mMoviesTextView = itemView.findViewById(R.id.tv_movies);
    }

    public void bind(Movies movies) {
        mMoviesTextView.setText(movies.getName());
    }
}
