package sg.com.kaplan.pdma_assignment2_bagit;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

class MyViewHolder extends RecyclerView.ViewHolder {
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;

    public MyViewHolder(View v) {
        super(v);
        tv1 = v.findViewById(R.id.fromcountry);
        tv2 = v.findViewById(R.id.tocountry);
        tv3 = v.findViewById(R.id.fromdate);
        tv4 = v.findViewById(R.id.todate);
    }

    // one-way data-binding
    public void bind(MyData mData) {
        tv1.setText(mData.getText1());
        tv2.setText(mData.getText2());
        tv3.setText(mData.getText3());
        tv4.setText(mData.getText4());
    }
}
