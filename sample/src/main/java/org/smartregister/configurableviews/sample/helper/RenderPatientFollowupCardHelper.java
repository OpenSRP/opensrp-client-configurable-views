package org.smartregister.configurableviews.sample.helper;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.smartregister.configurableviews.sample.R;
import org.smartregister.configurableviews.sample.Utils;
import org.smartregister.configurableviews.sample.Constants;
import org.smartregister.repository.BaseRepository;

import java.util.Map;

/**
 * Created by ndegwamartin on 23/11/2017.
 */

public class RenderPatientFollowupCardHelper extends BaseRenderHelper {

    private static final String TAG = RenderPatientFollowupCardHelper.class.getCanonicalName();

    public RenderPatientFollowupCardHelper(Context context, BaseRepository detailsRepository) {
        super(context, detailsRepository);
    }

    @Override
    public void renderView(final View view, final Map<String, String> patientDetails) {
        new Handler().post(new Runnable() {

            @Override
            public void run() {
                try {
                    Button followUpView = (Button) view.findViewById(R.id.follow_up_button);
                    followUpView.setAllCaps(false);
                    if (followUpView != null && patientDetails.get(Constants.KEY.NEXT_VISIT_DATE) != null) {

                        followUpView.setText(context.getString(R.string.followup) + " - due " + Utils.formatDate(org.smartregister.util.Utils.toDate(patientDetails.get(Constants.KEY.NEXT_VISIT_DATE), true), "dd/MM"));
                        DateTime treatmentStartDate = DateTime.parse(patientDetails.get(Constants.KEY.NEXT_VISIT_DATE).toString());
                        int due = Days.daysBetween(new DateTime().withTimeAtStartOfDay(), treatmentStartDate.withTimeAtStartOfDay()).getDays();
                        if (due < 0) {
                            followUpView.setBackgroundResource(R.drawable.due_vaccine_red_bg);
                            followUpView.setTextColor(context.getResources().getColor(R.color.white));
                        } else if (due == 0) {
                            followUpView.setBackgroundResource(R.drawable.due_vaccine_blue_bg);
                            followUpView.setTextColor(context.getResources().getColor(R.color.white));
                        } else {
                            followUpView.setTextColor(context.getResources().getColor(R.color.client_list_grey));
                            followUpView.setBackgroundResource(R.drawable.due_vaccine_na_bg);
                        }


                    } else {
                        followUpView.setText(R.string.followup);
                        followUpView.setTextColor(context.getResources().getColor(R.color.client_list_grey));
                        followUpView.setBackgroundResource(R.drawable.due_vaccine_na_bg);
                    }
                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                }
            }

        });
    }
}
