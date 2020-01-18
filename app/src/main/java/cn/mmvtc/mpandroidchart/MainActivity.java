package cn.mmvtc.mpandroidchart;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private LineChart linechartOne;
    private List <String> monthList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linechartOne = (LineChart) findViewById(R.id.linechart_one);
        //设置边界
        linechartOne.setDrawBorders(true);
        //创建数据
        ArrayList < Entry > entryList = new ArrayList <>();
        for (int i = 0; i < 12; i++) {
            entryList.add(new Entry(i,(float)Math.random()*60));
        }
        //创建一条直线
        LineDataSet lineDataSet = new LineDataSet(entryList,"温度");
        //设置直线的类型 曲线
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        //设置空心 实心
        lineDataSet.setDrawCircleHole(true);
        //设置直线文字的大小
        lineDataSet.setValueTextSize(10f);
        //设置数据体
        LineData lineData = new LineData(lineDataSet);
        //与设置自定义X轴类似，设置曲线显示值为整数，可在设置曲线LineDataSet 时,修改值的类型
        lineData.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                int IValue=(int)value;
                return String.valueOf(IValue);
            }
        });
        //控件设计数据
        linechartOne.setData(lineData);
        //LineChart_one控件的视图操作
        setLineChartOneView();
        //对x轴的操作
        setXAxix();
        //对y轴的操作
        setYAxix();
        //对图例操作
        setLegend();
        //对Description描述的操作
        setDescription();
        //设置MarkerView
        //setMarkerView();
    }
    //设置月份的集合
    private void setMonthListMessage(){
        monthList.add("一月");
        monthList.add("二月");
        monthList.add("三月");
        monthList.add("四月");
        monthList.add("五月");
        monthList.add("六月");
        monthList.add("七月");
        monthList.add("八月");
        monthList.add("九月");
        monthList.add("十月");
        monthList.add("十一月");
        monthList.add("十二月");
    }
    //对LineChart_one布局的操作
    private void setLineChartOneView(){
        linechartOne.setNoDataTextColor(Color.GREEN);
    }
    //对x轴操作
    private void setXAxix(){
        //获取X轴的操作对象
        XAxis xAxis = linechartOne.getXAxis();
        //设置x轴在下方
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //设置最大值和最小值
        //xAxis.setAxisMinimum(0f);
       // xAxis.setAxisMaximum(12f);
        //设置x轴的间隔
        xAxis.setGranularity(1f);
        //设置X轴的坐标数量
        xAxis.setLabelCount(12,true);
        //设置X轴坐标的名字
        setMonthListMessage();
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return monthList.get((int)value);
            }
        });
        //设置文本颜色
        xAxis.setTextColor(Color.RED);
        //设置坐标轴颜色
        xAxis.setAxisLineColor(Color.YELLOW);

    }
    //对y轴的操作
    private void setYAxix(){
        //得到左右2边的y轴
        YAxis axisLeft = linechartOne.getAxisLeft();
        YAxis axisRight = linechartOne.getAxisRight();
        //设置Y轴的值
        axisLeft.setAxisMinimum(0f);
        axisLeft.setAxisMaximum(100f);

        axisRight.setAxisMinimum(0f);
        axisRight.setAxisMaximum(100f);
        //设置y轴显示百分比
        axisLeft.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return (int)value+"%";
            }
        });
        //右边的Y轴不显示
       // axisRight.setEnabled(false);
        //设置y轴右边的坐标距离为1
        axisRight.setGranularity(1f);
        //设置y轴右边文本颜色
        axisRight.setTextColor(Color.BLUE);
        //设置y轴右边的颜色
        axisRight.setAxisLineColor(Color.RED);
        //设置y轴右边的网格线颜色
        axisRight.setGridColor(Color.YELLOW);

        //限制线的操作
        //获取限制线
        LimitLine limitLine = new LimitLine(93,"限高线");
        //设置宽度
        limitLine.setLineWidth(4f);
        //设置字体颜色
        limitLine.setTextColor(Color.RED);
        //设置字体大小
        limitLine.setTextSize(20f);
        //设置线条颜色
        limitLine.setLineColor(Color.GREEN);
        //限高线添加在右Y轴上
        axisRight.addLimitLine(limitLine);
    }
    //对图例操作.Legend(图例：即上图所示的曲线图下面的 温度)
    private void setLegend(){
        //获取图例对象
        Legend legend = linechartOne.getLegend();
        //设置字体颜色
        legend.setTextColor(Color.BLACK);
        //设置字体位置
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        //水平位置居中
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        //水平排列
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        //设置标签是否换行(标签过多就自己换行)
        legend.setWordWrapEnabled(true);
        //设置标签不显示
        legend.setEnabled(false);

    }
    //对Description(描述)的操作
    private void setDescription(){
        Description description = new Description();
        //隐藏描述
       // description.setEnabled(false);
        //设置描述信息
        description.setText("X轴的描述信息");
        description.setTextSize(20f);
        description.setTextColor(Color.RED);
        //设置描述
        linechartOne.setDescription(description);
    }
    //设置MarkerView
    private void setMarkerView(){
        MyMarkerView myMarkerView = new MyMarkerView(this);
        linechartOne.setMarker(myMarkerView);
    }
}
