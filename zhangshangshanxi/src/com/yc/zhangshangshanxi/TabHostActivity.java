package com.yc.zhangshangshanxi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.yc.zhangshangshanxi.model.PullToRefreshListView;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-9-27
 * Time: 下午3:00
 * To change this template use File | Settings | File Templates.
 */
public class TabHostActivity extends Activity {
    private TabHost tabHost;
    private static final  String Information = "information";
    private static final  String Event = "event";
    private static final  String Album = "album";
    private static final  String More = "more";
    private String TAG = "tabHost Test";

    private List<RadioButton> titleRadioButtonList = new ArrayList<RadioButton>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabhost_main);

        tabHost = (TabHost) findViewById(R.id.tabs);
        tabHost.setup();
        tabHost.setOnTabChangedListener(onTabChangeListener);

        init();
        initDate();
    }



    private void init() {

        View messageRadio  = LayoutInflater.from(this).inflate(R.layout.radio_message,null);
        View eventRadio  = LayoutInflater.from(this).inflate(R.layout.radio_event, null);
        View albumRadio  = LayoutInflater.from(this).inflate(R.layout.radio_album, null);
        View moreRadio  = LayoutInflater.from(this).inflate(R.layout.radio_more, null);
//        View[] radios = new View[]{messageRadio,eventRadio,albumRadio,moreRadio};



        tabHost.addTab(tabHost.newTabSpec(Information).setIndicator(messageRadio).setContent(R.id.tab_message));
        tabHost.addTab(tabHost.newTabSpec(Event).setIndicator(eventRadio).setContent(R.id.tab_activity));
        tabHost.addTab(tabHost.newTabSpec(Album).setIndicator(albumRadio).setContent(R.id.tab_description));
        tabHost.addTab(tabHost.newTabSpec(More).setIndicator(moreRadio).setContent(R.id.tab_more));

    }

    private void initDate() {


    }


    private TabHost.OnTabChangeListener onTabChangeListener  = new TabHost.OnTabChangeListener() {
        @Override
        public void onTabChanged(String tabId) {

            if (tabId.equals(Information))
            {
                   displayInformation();
            }else if (tabId.equals(Event))
            {
                  displayEvent();
            }else if (tabId.equals(Album))
            {
                displayAlbum();
            }else if (tabId.equals(More))
            {
                displayMore();

            }
        }
    };

    private void displayInformation() {
        initInformationTitleData();
        initInformationData();
    }

    private void initInformationTitleData() {
         LinearLayout titleListView = (LinearLayout) findViewById(R.id.tab_title_listView);


        String[] strings = new String[]{"要闻","西安","潮购","安居","生活"};

        for (int i=0;i<strings.length;i++)
        {
            Button button = (Button) LayoutInflater.from(this).inflate(R.layout.title_radio,null);
//            Button button   = (Button) findViewById(R.id.title_radio_button);
//            Log.d(TAG,"button = "+button);
            button.setText(strings[i]);
            titleListView.addView(button);
        }

    }



    private List initInformationData() {
//         List<> li;
        return null;
    }

    private void displayEvent() {
         PullToRefreshListView eventListView = (PullToRefreshListView) findViewById(R.id.tab_activity_listView);
        List<Map<String,Object>>  eventDate =  initEventDate();
        eventListView.setAdapter(new SimpleAdapter(this,eventDate,R.layout.content_small_item,new String[]{"image","title","time","location"},new int[]{R.id.content_bag_image,R.id.content_small_title,R.id.content_small_time,R.id.content_small_location}){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                if (convertView==null)
                {
                    LayoutInflater layoutInflater = getLayoutInflater();

                    if (position==0)
                    {
                        convertView =   layoutInflater.inflate(R.layout.content_bag_item,parent,false);
                        LinearLayout imageView = (LinearLayout) convertView.findViewById(R.id.content_bag_image);
                        TextView textView = (TextView) convertView.findViewById(R.id.content_bag_text);
                        textView.setText("好多人啊");
                        imageView.setBackground(getResources().getDrawable(R.drawable.about_us));
//
                        return  convertView ;
                    } else
                    {
                        convertView = layoutInflater.inflate(R.layout.content_small_item,parent,false);
                    }
                }




                    Map<String, Object> itemData = (Map<String, Object>) getItem(position-1);

                    ImageView imageView = (ImageView) convertView.findViewById(R.id.content_small_image);
                    TextView titleView = (TextView) convertView.findViewById(R.id.content_small_title);
                    TextView timeView = (TextView) convertView.findViewById(R.id.content_small_time);
                    TextView locationView = (TextView) convertView.findViewById(R.id.content_small_location);
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.about_us_icon));
                    titleView.setText(itemData.get("title").toString());
                    timeView.setText(itemData.get("time").toString());
                    locationView.setText(itemData.get("location").toString());

                    return convertView;


            }
        });
    }

    private List initEventDate() {
        List<Map<String,Object>>  list = new ArrayList<Map<String, Object>>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(int i=0;i<5;i++)
        {
            HashMap<String,Object> hashMap = new HashMap<String, Object>();
            hashMap.put("image",R.drawable.about_us_icon);
            hashMap.put("title","闯闯！！！"+i);
            hashMap.put("time",simpleDateFormat.format(new Date()))  ;
            hashMap.put("location","闯闯出品");
            list.add(hashMap);
        }
        return list;
    }

    private void displayAlbum() {
         ListView albumListView = (ListView) findViewById(R.id.tab_album_listView);
        List<Map<String,Object>>  albumDate =  initAlbumDate();
        albumListView.setAdapter(new SimpleAdapter(this,albumDate,R.layout.album_content_item,new String[]{"image","title","text"},new int[]{R.id.album_item_image,R.id.album_item_title_text,R.id.album_item_text})
        );
    }

    private List initAlbumDate() {
        List<Map<String,Object>>  list  = new ArrayList<Map<String, Object>>();
         for(int i=0;i<5;i++)
         {
             HashMap<String,Object> hashMap = new HashMap<String, Object>();
             hashMap.put("image",R.drawable.about_us);
             hashMap.put("title","闯闯！！！"+i);
             hashMap.put("text","闯闯啊 闯闯啊"+i);
             list.add(hashMap);
         }

        return  list;

    }

    private void displayMore() {
        final   Button feedbackButton = (Button) findViewById(R.id.tab_more_feedback_button);
        feedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==feedbackButton.getId())
                {
                    Intent intent = new Intent(TabHostActivity.this,FeedbackActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

}
