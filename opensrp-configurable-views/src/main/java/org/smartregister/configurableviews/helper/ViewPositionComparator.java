package org.smartregister.configurableviews.helper;


import org.smartregister.configurableviews.model.View;
import org.smartregister.view.contract.IView;

import java.util.Comparator;

/**
 * Created by samuelgithengi on 11/21/17.
 */

public class ViewPositionComparator implements Comparator<IView> {

    @Override
    public int compare(IView v1, IView v2) {
        if (v1.getResidence() == null && v2.getResidence() == null)
            return 0;
        else if (v1.getResidence() == null && v2.getResidence() != null)
            return 1;
        else if (v1.getResidence() != null && v2.getResidence() == null)
            return -1;
        else {
            int comparison = Integer.valueOf(v1.getResidence().getPosition()).compareTo(Integer.valueOf(v2.getResidence().getPosition()));
            if (comparison == 0)
                comparison = v1.getIdentifier().compareTo(v2.getIdentifier());
            return comparison;
        }
    }

}