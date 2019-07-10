package com.hist.weatherview.weatherlife.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hist.item.weatherlife.WeatherLifeBase;
import com.hist.item.weatherlife.WeatherLifeItem;
import com.hist.item.weatherlife.WeatherLifeResult;
//import com.hist.item.weatherlife.old.WeatherLifeItem;
import com.hist.item.weatherlife.old.WeatherLifeType;
import com.hist.weatherview.R;

import java.util.ArrayList;

import butterknife.BindView;


/***
 *  Author : JJW
 *  Date : 20180208
 *  Desc : 생활 기상 관련 리스트뷰 어뎁터 (ArrayAdapter 기반)
 */

public class WeatherLifeListViewArrayAdapter extends ArrayAdapter<WeatherLifeResult> {
    private WeatherLifeItem weatherLifeItem;
    private LayoutInflater inflater = null;
    private ViewHolder viewHolder = null;
    private Context ctx;
    private String weatherLifeItemTypName;



    public WeatherLifeListViewArrayAdapter(Context c, int textViewResourceId,
                                           WeatherLifeItem item) {

        super(c, textViewResourceId, item.getResult());
        this.inflater = LayoutInflater.from(c);
        this.ctx = c;
        this.weatherLifeItem = item;
        this.weatherLifeItemTypName = item.getType();

    }



    public String getWeatherLifeItemTypName() {
        return weatherLifeItemTypName;
    }

    public void setWeatherLifeItemTypName(String weatherLifeItemTypName) {
        this.weatherLifeItemTypName = weatherLifeItemTypName;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public WeatherLifeResult getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }


    // ListView의 뿌려질 한줄의 Row를 설정 합니다.
    @Override
    public View getView(int position, View convertview, ViewGroup parent) {

        View v = convertview;

        if(v == null){
            viewHolder = new ViewHolder();
            v = inflater.inflate(R.layout.list_item_weather_life, null);
            viewHolder.txtStatus = (TextView)v.findViewById(R.id.txt_lifeweather_item_status);
            viewHolder.txtValue = (TextView)v.findViewById(R.id.txt_lifeweather_item_value);
            viewHolder.txtDesc = (TextView)v.findViewById(R.id.txt_lifeweather_item_desc);
            viewHolder.imgView = (ImageView)v.findViewById(R.id.lifeweather_item_imgview);
            viewHolder.llItem = (LinearLayout) v.findViewById(R.id.ll_item);
            v.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)v.getTag();
        }

        viewHolder.txtDesc.setText(getItem(position).getDescription());
        viewHolder.txtValue.setText(getItem(position).getValue());
        // image 나 button 등에 Tag를 사용해서 position 을 부여해 준다.
        // Tag란 View를 식별할 수 있게 바코드 처럼 Tag를 달아 주는 View의 기능
        // 이라고 생각 하시면 됩니다.

        //String level = weatherLifeLevelChecker.GetWeatherLifeLevel(getItem(position));
        //String level = getItem(position).GetWeatherLifeSeverityLevel();
        //viewHolder.txtStatus.setText(getItem(position).getStatus());
        viewHolder.imgView.setTag(position);
        //viewHolder.imgView.setImageResource(R.drawable.weatherlife_circle_danger);
        //WeatherLifeType type = getItem(position).getType();


        /*
        switch (level)
        {
            case "danger" :
                viewHolder.imgView.setImageResource(R.drawable.weatherlife_circle_danger);

                break;
            case "veryHigh":
                viewHolder.imgView.setImageResource(R.drawable.weatherlife_circle_veryhigh);
                break;
            case "high":
                viewHolder.imgView.setImageResource(R.drawable.weatherlife_circle_high);
                break;
            case "normal" :
                viewHolder.imgView.setImageResource(R.drawable.weatherlife_circle_normal);
                break;
            case "low" :
                viewHolder.imgView.setImageResource(R.drawable.weatherlife_circle_low);
                break;
            default:
                viewHolder.imgView.setImageResource(R.drawable.weatherlife_circle_low);
                break;
        }
*/

        //viewHolder.imgView.setOnClickListener(buttonClickListener);   //click event listener

/*
        viewHolder.btn_button.setTag(position);
        viewHolder.btn_button.setText(getItem(position).button);
        viewHolder.btn_button.setOnClickListener(buttonClickListener);
        viewHolder.cb_box.setTag(position);
        viewHolder.cb_box.setOnClickListener(buttonClickListener);
*/

        return v;
    }


    // Adapter가 관리하는 Data List를 교체 한다.
    // 교체 후 Adapter.notifyDataSetChanged() 메서드로 변경 사실을
    // Adapter에 알려 주어 ListView에 적용 되도록 한다.
    public void setArrayList(WeatherLifeItem item){
        this.weatherLifeItem = item;
    }

    public WeatherLifeItem getArrayList(){
        return this.weatherLifeItem;
    }

    public String GetWeatherTypeName(ArrayList<WeatherLifeItem> arrays)
    {
        String retVal;
        if(arrays == null)
        {
            retVal = "";
        } else {
            //retVal = arrays.get(0).GetWeatherTypeName();
            retVal = "";
        }

        return retVal;
    }

    public WeatherLifeItem getWeatherLifeItem() {
        return weatherLifeItem;
    }

    /*private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                // 이미지 클릭
                case R.id.iv_image:
                    Toast.makeText(
                            mContext,
                            "이미지 Tag = " + v.getTag(),
                            Toast.LENGTH_SHORT
                    ).show();
                    break;

                // 버튼 클릭
                case R.id.btn_button:
                    Toast.makeText(
                            mContext,
                            "버튼 Tag = " + v.getTag(),
                            Toast.LENGTH_SHORT
                    ).show();
                    break;

                // CheckBox
                case R.id.cb_box:
                    Toast.makeText(
                            mContext,
                            "체크박스 Tag = " + v.getTag(),
                            Toast.LENGTH_SHORT
                    ).show();
                    break;

                default:
                    break;
            }
        }
    };*/

    /*
     * ViewHolder
     * getView의 속도 향상을 위해 쓴다.
     * 한번의 findViewByID 로 재사용 하기 위해 viewHolder를 사용 한다.
     */
    class ViewHolder{


        //@BindView(R.id.txt_lifeweather_item_status)
        public TextView txtStatus = null;
        //@BindView(R.id.txt_lifeweather_item_value)
        public ImageView imgView = null;
        //@BindView(R.id.txt_lifeweather_item_desc)/
        public TextView txtDesc = null;
        //@BindView(R.id.lifeweather_item_imgview)
        public TextView txtValue = null;
        public LinearLayout llNoItem = null;
        public LinearLayout llItem = null;
        //public Button btn_button = null;
        //public CheckBox cb_box = null;
    }

    @Override
    protected void finalize() throws Throwable {
        free();
        super.finalize();
    }

    private void free(){
        inflater = null;
        weatherLifeItem = null;
        viewHolder = null;
        ctx = null;
    }


}
