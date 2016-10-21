package com.android.szparag.colortv.adapters;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ciemek on 20/10/2016.
 */

/**
 *  Base abstract adapter for RecyclerViews. Sets the hierarchy, basic functionalities around
 *  list of items and custom (nullable) click listener.
 *
 * @param <T> Generic type that adapter will handle in overriden child class
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected RecyclerOnPosClickListener    recyclerOnPosClickListener;
    protected List<T>                       items;


    public BaseAdapter() {
        items = new ArrayList<T>();
    }

    public BaseAdapter(@Nullable RecyclerOnPosClickListener clickListener) {
        this();
        if (clickListener != null) {
            setRecyclerOnPosClickListener(clickListener);
        }
    }


    @Override
    public int getItemCount() {
        return items.size();
    }


    public void setRecyclerOnPosClickListener(RecyclerOnPosClickListener clickListener) {
        recyclerOnPosClickListener = clickListener;
    }

    public void addItem(T item) {
        items.add(item);
        notifyItemInserted(items.size()-1);
    }

    public void addItemNoNotify(T item) {
        items.add(item);
    }

    public void addItems(List<T> items) {
        items.addAll(items);
        notifyDataSetChanged();
    }

    public void updateItem(T item) {
        items.clear();
        items.add(item);
        notifyDataSetChanged();
    }

    public void updateItems(T[] item) {
        updateItems(Arrays.asList(item));
    }

    public void updateItems(List<T> item) {
        items.clear();
        items.addAll(item);
        notifyDataSetChanged();
    }


    public List<T> getItems() {
        return items;
    }

    public T getItem(int position) {
        return items.get(position);
    }
}