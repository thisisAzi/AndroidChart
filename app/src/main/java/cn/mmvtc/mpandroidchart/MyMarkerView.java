package cn.mmvtc.mpandroidchart;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

import java.text.DecimalFormat;

public class MyMarkerView extends MarkerView {
    private TextView tvContent;
    private DecimalFormat format=new DecimalFormat("##0");
    public MyMarkerView(Context context) {
        super(context, R.layout.markerview);
        tvContent=(TextView)findViewById(R.id.mv_one);
    }
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        tvContent.setText(format.format(e.getY()));
        super.refreshContent(e, highlight);
    }
    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth()/2),-getHeight()-10);
    }


}
