// Generated code from Butter Knife. Do not modify!
package fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class SecondFragmentForm$$ViewBinder<T extends fragment.SecondFragmentForm> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296374, "field 'plotSize'");
    target.plotSize = finder.castView(view, 2131296374, "field 'plotSize'");
    view = finder.findRequiredView(source, 2131296375, "field 'floors'");
    target.floors = finder.castView(view, 2131296375, "field 'floors'");
    view = finder.findRequiredView(source, 2131296371, "field 'spinnerTypeOfConstruction' and method 'onSelectTypeOfCons'");
    target.spinnerTypeOfConstruction = finder.castView(view, 2131296371, "field 'spinnerTypeOfConstruction'");
    ((android.widget.AdapterView<?>) view).setOnItemSelectedListener(
      new android.widget.AdapterView.OnItemSelectedListener() {
        @Override public void onItemSelected(
          android.widget.AdapterView<?> p0,
          android.view.View p1,
          int p2,
          long p3
        ) {
          target.onSelectTypeOfCons(p2);
        }
        @Override public void onNothingSelected(
          android.widget.AdapterView<?> p0
        ) {
          
        }
      });
    view = finder.findRequiredView(source, 2131296372, "field 'spinnerCategory' and method 'OnSelectCategory'");
    target.spinnerCategory = finder.castView(view, 2131296372, "field 'spinnerCategory'");
    ((android.widget.AdapterView<?>) view).setOnItemSelectedListener(
      new android.widget.AdapterView.OnItemSelectedListener() {
        @Override public void onItemSelected(
          android.widget.AdapterView<?> p0,
          android.view.View p1,
          int p2,
          long p3
        ) {
          target.OnSelectCategory(p2);
        }
        @Override public void onNothingSelected(
          android.widget.AdapterView<?> p0
        ) {
          
        }
      });
    view = finder.findRequiredView(source, 2131296373, "field 'spinnerStayType' and method 'OnSelectStayType'");
    target.spinnerStayType = finder.castView(view, 2131296373, "field 'spinnerStayType'");
    ((android.widget.AdapterView<?>) view).setOnItemSelectedListener(
      new android.widget.AdapterView.OnItemSelectedListener() {
        @Override public void onItemSelected(
          android.widget.AdapterView<?> p0,
          android.view.View p1,
          int p2,
          long p3
        ) {
          target.OnSelectStayType(p2);
        }
        @Override public void onNothingSelected(
          android.widget.AdapterView<?> p0
        ) {
          
        }
      });
  }

  @Override public void unbind(T target) {
    target.plotSize = null;
    target.floors = null;
    target.spinnerTypeOfConstruction = null;
    target.spinnerCategory = null;
    target.spinnerStayType = null;
  }
}
