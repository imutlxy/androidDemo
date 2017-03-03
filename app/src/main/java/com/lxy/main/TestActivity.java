package com.lxy.main;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.lxy.R;
import com.lxy.moduel.Artical;
import com.lxy.moduel.Chapter;
import com.lxy.util.Utils;
import com.lxy.view.HorizontalScrollViewEx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gaohuang on 17-2-20.
 */
public class TestActivity extends Activity {

    private static final String TAG = "TestActivity";

    private HorizontalScrollViewEx container;

    private List<Artical> articalList;
    private List<List<Chapter>> allChapterList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

//        articalList = new ArrayList<>();
//        allChapterList = new ArrayList<>();
//
//        initData();
//
//        container = (HorizontalScrollViewEx) findViewById(R.id.container);
//        containerAddViews();
//
//        Log.d(TAG, "screenWidth=" + Utils.getScreenMetrics(this).widthPixels);
//        Log.d(TAG, "screenHeight=" + Utils.getScreenMetrics(this).heightPixels);
    }

    private void initData() {

        for (int i = 0; i < 1; i++) {
            Artical artical = new Artical();
            artical.chapterName = "第" + i + "章";

            articalList.add(artical);

            List<Chapter> chapterList = new ArrayList<>();

            for (int j = 0; j < 1; j++) {
                Chapter chapter = new Chapter();
                chapter.title = "第" + i + "章" + "--第" + j + "节";
                chapter.des = "描述";

                chapterList.add(chapter);
            }
            allChapterList.add(chapterList);
        }

        print();
    }

    private void print() {
        for (int i = 0; i < allChapterList.size(); i++) {
            List<Chapter> chapterList = allChapterList.get(i);
//            Log.d(TAG, "chapterList.size=" + chapterList.size());
//            Log.d(TAG, "i=" + i + ";chapterList.size=" + chapterList.size());

            for (int j = 0; j < chapterList.size(); j++) {
//                Log.d(TAG, "j=" + j + ";title=" + chapterList.get(j).title);
            }
        }
    }

    private void containerAddViews() {
        for (int i = 0; i < articalList.size(); i++) {
            Artical artical = articalList.get(i);
            View view = getContainerView(i, artical);
            container.addView(view);
        }
    }

    private View getContainerView(int i, Artical artical) {
        View view = getLayoutInflater().inflate(R.layout.vertical_layout, container, false);
        TextView chaper = (TextView) view.findViewById(R.id.chaper);
        ListView listview = (ListView) view.findViewById(R.id.listview);

        chaper.setText(artical.chapterName);

        List<Chapter> chapterList = allChapterList.get(i);

        ArticleAdapter articleAdapter = new ArticleAdapter(this);
        articleAdapter.setChapterList(chapterList);
        listview.setAdapter(articleAdapter);

        return view;
    }
}
