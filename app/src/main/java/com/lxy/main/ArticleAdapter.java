package com.lxy.main;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lxy.R;
import com.lxy.moduel.Artical;
import com.lxy.moduel.Chapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by gaohuang on 17-2-20.
 */
public class ArticleAdapter extends BaseAdapter {

    private List<Chapter> chapterList;
    private Activity activity;

    public ArticleAdapter(Activity activity) {
        this.activity = activity;
        chapterList = new ArrayList<>();
    }

    public void setChapterList(List<Chapter> chapterList) {
        this.chapterList = chapterList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return chapterList.size();
    }

    @Override
    public Object getItem(int position) {
        return chapterList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        View view = null;
        if (convertView == null) {
            view = activity.getLayoutInflater().inflate(R.layout.dictory_item,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.chapter_title = (TextView) view.findViewById(R.id.chapter_title);
            viewHolder.chapter_description = (TextView) view.findViewById(R.id.chapter_description);

            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        Chapter chapter = chapterList.get(position);
        viewHolder.chapter_title.setText(chapter.title);
        viewHolder.chapter_description.setText(chapter.des);

        return view;
    }

    static class ViewHolder {
        TextView chapter_title;
        TextView chapter_description;
    }

}
