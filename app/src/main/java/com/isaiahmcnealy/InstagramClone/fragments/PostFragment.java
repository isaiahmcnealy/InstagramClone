package com.isaiahmcnealy.InstagramClone.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.isaiahmcnealy.InstagramClone.Post;
import com.isaiahmcnealy.InstagramClone.PostAdapter;
import com.isaiahmcnealy.InstagramClone.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostFragment extends Fragment {

    public static final String TAG = "PostFragment";
    private RecyclerView rvPost;
    private PostAdapter adapter;
    private List<Post> allPost;


    public PostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvPost = view.findViewById(R.id.rvPost);

        // Steps to create a recycler view
        // 0. create layout for one row
        // 1. create the adapter
        allPost = new ArrayList<>();
        adapter = new PostAdapter(getContext(), allPost);
        // 2. create the data source
        // 3. set the adapter on the recyclerView
        rvPost.setAdapter(adapter);
        // 4. set the layout manager on the recycler view
        rvPost.setLayoutManager(new LinearLayoutManager(getContext()));
        queryPosts();

    }

    private void queryPosts() {
        // Specify which class to query
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting post");
                    return;
                }
                for (Post post : posts) {
                    Log.i(TAG, "User: " + post.getUser().getUsername() + ", Post:" + post.getDescription());
                }
                allPost.addAll(posts);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
