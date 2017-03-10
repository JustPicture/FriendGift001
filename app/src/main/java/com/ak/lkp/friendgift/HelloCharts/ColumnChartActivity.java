package com.ak.lkp.friendgift.HelloCharts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ak.lkp.friendgift.R;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;

public class ColumnChartActivity extends AppCompatActivity {
    private PieChartData data;
    String[] date = {"10-22","11-22","12-22","1-22","6-22","5-23","5-22","6-22","5-23","5-22"};//X轴的标注
    List<Float> items = new ArrayList<>();
    private List<AxisValue> mAxisXValues = new ArrayList<AxisValue>();
    private ColumnChartView colum_chart;//图表view
    private ColumnChartData colum_data;//图表数据Model

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_column_chart);
        colum_chart = (ColumnChartView) findViewById(R.id.columnchar);
        getNum();
        getAxisXLables();
        initColumData();
    }
    /**
     * 设置X 轴的显示
     */
    private void getAxisXLables(){
        for (int i = 0; i < date.length; i++) {
            mAxisXValues.add(new AxisValue(i).setLabel(date[i]));
        }
    }

    private void getNum() {
            for (int i = 0; i < 10; i++) {
                items.add((float) Math.random() * 10f + 10);
            }
    }



    private void initColumData() {
        int numSubcolums = 1;
        int numColums = items.size();
        List<Column> colums = new ArrayList<Column>();
        List<SubcolumnValue> values;
        for (int i = 0; i < numColums; i++) {
            values = new ArrayList<SubcolumnValue>();
            values.add(new SubcolumnValue(items.get(i), ChartUtils.pickColor()));//第一个值是数值，第二个值是颜色
            Column colum = new Column(values); //一个柱状图的实例
            colum.setHasLabels(true);//设置是否显示每个柱状图的高度
//            colum.setHasLabelsOnlyForSelected(true);//点击的时候是否显示柱状图的高度，和setHasLabels()和用的时候，setHasLabels()失效
            colums.add(colum);
        }
        colum_data = new ColumnChartData(colums);
        //
        Axis axisX = new Axis();
        Axis axisY = new Axis().setHasLines(true);
        axisX.setName("Axis X");
        axisY.setName("Axis Y");
        axisX.setValues(mAxisXValues);
        colum_data.setAxisXBottom(axisX);
        colum_data.setAxisYLeft(axisY);
        colum_chart.setColumnChartData(colum_data);

//        //如果你不想使用系统默认生成的坐标轴数值，比如让月份从1开始，不要出现0；可以使用这个：
//        Axis axis=new Axis(List<AxisValue> values)
//        //设置坐标轴的名称
//        axis.setName("月/年");
//        //设置坐标数值的位置，默认在坐标线的外面
//        axis.setInside(false);
//        //设置坐标轴的数据是否倾斜
//        axisY.setHasTiltedLabels(true);
//        //设置坐标轴的轴线，及文字标注的颜色
//        axisy.setTextColor(int color);
//        //设置网格线
//        axis.setHasSeparationLine(true);
//        //设置精度，缩放时坐标轴精度默认会增加，而月份不可能出现小数，所以应设置一下
//        axisX.setFormatter(new SimpleAxisValueFormatter(0));

////设置x轴,在上面或下面
//        colum_data .setAxisXBottom(axis);
//        //设置yz轴，在左面或右面
//        colum_data .setAxisYLeft(axis);
//        //设置图表中点的数值颜色
//        colum_data.setValueLabelsTextColor(Color.RED);
    }
}
