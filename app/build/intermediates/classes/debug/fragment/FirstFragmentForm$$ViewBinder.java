// Generated code from Butter Knife. Do not modify!
package fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class FirstFragmentForm$$ViewBinder<T extends fragment.FirstFragmentForm> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296366, "field 'address'");
    target.address = finder.castView(view, 2131296366, "field 'address'");
    view = finder.findRequiredView(source, 2131296365, "field 'editTextPlotNo'");
    target.editTextPlotNo = finder.castView(view, 2131296365, "field 'editTextPlotNo'");
    view = finder.findRequiredView(source, 2131296367, "field 'editTextLocality'");
    target.editTextLocality = finder.castView(view, 2131296367, "field 'editTextLocality'");
    view = finder.findRequiredView(source, 2131296364, "field 'spinnerCity' and method 'onNothingSelected'");
    target.spinnerCity = finder.castView(view, 2131296364, "field 'spinnerCity'");
    ((android.widget.AdapterView<?>) view).setOnItemSelectedListener(
      new android.widget.AdapterView.OnItemSelectedListener() {
        @Override public void onItemSelected(
          android.widget.AdapterView<?> p0,
          android.view.View p1,
          int p2,
          long p3
        ) {
          target.onNothingSelected(p2);
        }
        @Override public void onNothingSelected(
          android.widget.AdapterView<?> p0
        ) {
          
        }
      });
  }

  @Override public void unbind(T target) {
    target.address = null;
    target.editTextPlotNo = null;
    target.editTextLocality = null;
    target.spinnerCity = null;
  }
}
