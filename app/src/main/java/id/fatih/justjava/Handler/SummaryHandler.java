package id.fatih.justjava.Handler;


import android.content.Intent;
import android.widget.TextView;

import id.fatih.justjava.R;
import id.fatih.justjava.SummaryActivity;

public class SummaryHandler
{
    private SummaryActivity activity;

    public SummaryHandler(SummaryActivity summaryActivity)
    {
        activity = summaryActivity;
    }

    public Intent getIntent()
    {
        return getActivity().getIntent();
    }

    public void setSummary(String summary)
    {
        getSummaryView().setText(summary);
    }

    private TextView getSummaryView()
    {
        return (TextView) getActivity().findViewById(R.id.txtSummary);
    }

    private SummaryActivity getActivity()
    {
        return activity;
    }
}
