package com.itfitness.hellochartpie;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.PieChartView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button one;
    private Button two;
    private Button three;
    private Button four;
    private Button five;
    private Button six;
    private Button seven;

    private PieChartView piechart;
    private PieChartData data;         //存放数据

    private boolean hasLabels = false;                   //是否有标签
    private boolean hasLabelsOutside = false;           //标签是否在扇形外面
    private boolean hasCenterCircle = false;            //是否有中心圆
    private boolean hasCenterText1 = false;             //是否有中心的文字
    private boolean hasCenterText2 = false;             //是否有中心的文字2
    private boolean isExploded = false;                  //是否是炸开的图像
    private boolean hasLabelForSelected = false;         //选中的扇形显示标签

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        one = (Button) findViewById(R.id.one);
        two = (Button) findViewById(R.id.two);
        three = (Button) findViewById(R.id.three);
        four = (Button) findViewById(R.id.four);
        five = (Button) findViewById(R.id.five);
        six = (Button) findViewById(R.id.six);
        seven = (Button) findViewById(R.id.seven);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        piechart = (PieChartView) findViewById(R.id.piechart);
        piechart.setOnValueTouchListener(new PieChartOnValueSelectListener() {
            @Override
            public void onValueSelected(int i, SliceValue sliceValue) {
                Toast.makeText(MainActivity.this, "选中值"+sliceValue, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onValueDeselected() {

            }
        });
        initDatas();
    }
    private void initDatas(){
        int numValues = 6;
        //初始化数据
        List<SliceValue> values = new ArrayList<SliceValue>();
        for (int i = 0; i < numValues; ++i) {
            SliceValue sliceValue = new SliceValue((float) Math.random() * 30 + 15, ChartUtils.pickColor());
            values.add(sliceValue);
        }

        data = new PieChartData(values);
        data.setHasLabels(hasLabels);
        data.setHasLabelsOnlyForSelected(hasLabelForSelected);
        data.setHasLabelsOutside(hasLabelsOutside);
        data.setHasCenterCircle(hasCenterCircle);

        if (isExploded) {
            data.setSlicesSpacing(24);
        }

        if (hasCenterText1) {
            data.setCenterText1("Hello!");//设置中心文字1
        }

        if (hasCenterText2) {
            data.setCenterText2("Charts (Roboto Italic)");//设置中心文字2
        }

        piechart.setPieChartData(data);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.one:
                hasLabels=!hasLabels;
                break;
            case R.id.two:
                hasLabelsOutside=!hasLabelsOutside;
                break;
            case R.id.three:
                hasCenterCircle=!hasCenterCircle;
                break;
            case R.id.four:
                hasCenterText1=!hasCenterText1;
                break;
            case R.id.five:
                hasCenterText2=!hasCenterText2;
                break;
            case R.id.six:
                isExploded=!isExploded;
                break;
            case R.id.seven:
                hasLabelForSelected=!hasLabelForSelected;
                break;
        }
        initDatas();
    }
}
