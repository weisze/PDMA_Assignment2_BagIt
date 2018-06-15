package sg.com.kaplan.pdma_assignment2_bagit;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

class req_MyViewHolder extends RecyclerView.ViewHolder {
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;

    public req_MyViewHolder(View v) {
        super(v);
        tv1 = v.findViewById(R.id.req_productname);
        tv2 = v.findViewById(R.id.req_fromcountry);
        tv3 = v.findViewById(R.id.req_price);
        tv4 = v.findViewById(R.id.req_date);
    }

    // one-way data-binding
    public void bind(req_MyData mData) {
        tv1.setText(mData.getText1());
        tv2.setText(mData.getText2());
        tv3.setText(mData.getText3());
        tv4.setText(mData.getText4());
    }
}
