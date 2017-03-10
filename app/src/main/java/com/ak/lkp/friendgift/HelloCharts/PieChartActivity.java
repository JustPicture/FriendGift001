package com.ak.lkp.friendgift.HelloCharts;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ak.lkp.friendgift.R;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.PieChartView;

public class PieChartActivity extends AppCompatActivity {

    private boolean hasLabels = true; // 是否显示数据
    private boolean hasLabelsOutside = true; // 数据是否显示在外面
    private boolean hasCenterCircle = true; // 是否含有中圈，显示下面的内容这个必须为true
    private boolean hasCenterText1 = true; // 圆中是否含有内容1
    private boolean hasCenterText2 = true; // 圆中是否含有内容2
    private boolean isExploded = false; // 是否爆破形式
    private boolean hasLabelForSelected = false; // 是否选中显示数据，一般为false

    String[] lable = {"10-22","11-22","12-22","1-22","6-22","5-23","5-22","6-22","5-23","5-22"};//X轴的标注
    int[] score= {50,42,90,33,10,74,22,18,79,20};//图表的数据点
    private List<SliceValue> mAxisXValues = new ArrayList<>();
    List<Integer> items = new ArrayList<>();
    private PieChartView piechar;
    private PieChartData data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);
        piechar = (PieChartView)findViewById(R.id.piechar);
        getData();
        generateData();
    }

    /**
     * 图表的每个点的显示
     */
    private void getData() {
        for (int i = 0; i < score.length; i++) {
            items.add(score[i]);
        }
    }

    private void generateData() {
//        int numValues = 6;
        List<SliceValue> values = new ArrayList<SliceValue>();
        data = new PieChartData();
        data.setHasLabels(hasLabels);// 是否显示数据
        data.setHasLabelsOnlyForSelected(hasLabelForSelected);// 是否选中显示数据，一般为false
        data.setHasLabelsOutside(hasLabelsOutside);// 数据是否显示在外面
//        data.setValueLabelBackgroundEnabled(true);//数据背景不显示
        data.setHasCenterCircle(hasCenterCircle);// 是否含有中圈，显示下面的内容这个必须为true
        // 设置不显示数据的背景颜色
        data.setValueLabelBackgroundEnabled(false);

        data.setHasCenterCircle(true);//是否是环形显示 false为扇形
//        data.setCenterCircleScale(0.5f);////设置环形的大小级别
        data.setValueLabelBackgroundColor(Color.TRANSPARENT);////设置值得背景透明
        data.setValueLabelBackgroundEnabled(true);//数据背景不显示
//        data.setValueLabelsTextColor(Color.BLACK);

        if (isExploded) {
            data.setSlicesSpacing(24);
        }

        if (hasCenterText1) {
            data.setCenterText1("92.14%");
            data.setCenterText1FontSize(22);
            data.setCenterText1Color(getResources().getColor(R.color.colorPrimary));
        }
        if (hasCenterText2) {
            data.setCenterText2("未做占比");
            data.setCenterText2FontSize(18);
            data.setCenterText2Color(getResources().getColor(R.color.red));
        }

        for (int i = 0; i < items.size();i++) {
            SliceValue sliceValue = new SliceValue(items.get(i), ChartUtils.pickColor());
            sliceValue.setLabel(lable[i]);
            values.add(sliceValue);
        }
      data.setValues(values);

        piechar.setPieChartData(data);
        piechar.setViewportCalculationEnabled(true);
    }

}
