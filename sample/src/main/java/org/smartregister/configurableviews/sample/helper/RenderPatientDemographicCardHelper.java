package org.smartregister.configurableviews.sample.helper;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.apache.commons.lang3.text.WordUtils;
import org.smartregister.configurableviews.sample.R;
import org.smartregister.configurableviews.sample.Utils;
import org.smartregister.configurableviews.sample.Constants;
import org.smartregister.repository.BaseRepository;

import java.util.Map;

/**
 * Created by ndegwamartin on 23/11/2017.
 */

public class RenderPatientDemographicCardHelper extends BaseRenderHelper {
    private static final String TAG = RenderPatientDemographicCardHelper.class.getCanonicalName();

    public RenderPatientDemographicCardHelper(Context context, BaseRepository detailsRepository) {
        super(context, detailsRepository);
    }

    @Override
    public void renderView(final View view, final Map<String, String> patientDetails) {
        new Handler().post(new Runnable() {

            @Override
            public void run() {
                try {
                    if (view.getTag(R.id.VIEW_CONFIGURATION_ID) == Constants.CONFIGURATION.PATIENT_DETAILS_INTREATMENT) {

                        TextView patientTypeTextView = (TextView) view.findViewById(R.id.patientTypeTextView);
                        if (patientDetails.get(Constants.KEY.PATIENT_TYPE) != null && !patientDetails.get(Constants.KEY.PATIENT_TYPE).isEmpty()) {
                            patientTypeTextView.setText(WordUtils.capitalizeFully(patientDetails.get(Constants.KEY.PATIENT_TYPE).replace(Constants.CHAR.UNDERSCORE, Constants.CHAR.SPACE)));
                            patientTypeTextView.setVisibility(View.VISIBLE);
                        }
                        if (patientDetails.get(Constants.KEY.SITE_OF_DISEASE) != null && !patientDetails.get(Constants.KEY.SITE_OF_DISEASE).isEmpty()) {
                            TextView siteOfDiseaseTextView = (TextView) view.findViewById(R.id.siteOfDiseaseTextView);
                            siteOfDiseaseTextView.setText(Utils.getTBTypeByCode(patientDetails.get(Constants.KEY.SITE_OF_DISEASE)));
                            siteOfDiseaseTextView.setVisibility(View.VISIBLE);
                        }
                    }
                    TextView tbReachIdTextView = (TextView) view.findViewById(R.id.tbReachIdTextView);
                    tbReachIdTextView.setText(Utils.formatIdentifier(patientDetails.get(Constants.KEY.PARTICIPANT_ID)));

                    TextView clientAgeTextView = (TextView) view.findViewById(R.id.clientAgeTextView);
                    String dobString = patientDetails.get(Constants.KEY.DOB);
                    String formattedAge = Utils.getFormattedAgeString(dobString);
                    clientAgeTextView.setText(formattedAge);

                    TextView clientNameTextView = (TextView) view.findViewById(R.id.clientNameTextView);
                    String fullName = patientDetails.get(Constants.KEY.FIRST_NAME) + " " + patientDetails.get(Constants.KEY.LAST_NAME);
                    clientNameTextView.setText(fullName);
                    TextView clientGenderTextView = (TextView) view.findViewById(R.id.clientGenderTextView);
                    clientGenderTextView.setText(WordUtils.capitalize(patientDetails.get(Constants.KEY.GENDER)));

                    TextView clientInitalsTextView = (TextView) view.findViewById(R.id.clientInitalsTextView);
                    clientInitalsTextView.setText(Utils.getShortInitials(fullName));

                    if (patientDetails.get(Constants.KEY.GENDER).equals(Constants.GENDER.MALE)) {
                        clientInitalsTextView.setBackgroundColor(context.getResources().getColor(R.color.male_light_blue));
                        clientInitalsTextView.setTextColor(context.getResources().getColor(R.color.male_blue));

                    } else if (patientDetails.get(Constants.KEY.GENDER).equals(Constants.GENDER.FEMALE)) {
                        clientInitalsTextView.setBackgroundColor(context.getResources().getColor(R.color.female_light_pink));
                        clientInitalsTextView.setTextColor(context.getResources().getColor(R.color.female_pink));
                    } else {
                        clientInitalsTextView.setBackgroundColor(context.getResources().getColor(R.color.gender_neutral_light_green));
                        clientInitalsTextView.setTextColor(context.getResources().getColor(R.color.gender_neutral_green));
                    }
                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                }
            }

        });
    }
}
