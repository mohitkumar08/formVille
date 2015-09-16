// Generated code from Butter Knife. Do not modify!
package fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ThirdFragmentForm$$ViewBinder<T extends fragment.ThirdFragmentForm> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296386, "field 'bathroom'");
    target.bathroom = finder.castView(view, 2131296386, "field 'bathroom'");
    view = finder.findRequiredView(source, 2131296385, "field 'rooms'");
    target.rooms = finder.castView(view, 2131296385, "field 'rooms'");
    view = finder.findRequiredView(source, 2131296387, "field 'kitchen'");
    target.kitchen = finder.castView(view, 2131296387, "field 'kitchen'");
    view = finder.findRequiredView(source, 2131296384, "field 'slabsDecreate'");
    target.slabsDecreate = finder.castView(view, 2131296384, "field 'slabsDecreate'");
    view = finder.findRequiredView(source, 2131296381, "field 'spinnerStageOfConstruction' and method 'onSelectStageOfCons'");
    target.spinnerStageOfConstruction = finder.castView(view, 2131296381, "field 'spinnerStageOfConstruction'");
    ((android.widget.AdapterView<?>) view).setOnItemSelectedListener(
      new android.widget.AdapterView.OnItemSelectedListener() {
        @Override public void onItemSelected(
          android.widget.AdapterView<?> p0,
          android.view.View p1,
          int p2,
          long p3
        ) {
          target.onSelectStageOfCons(p2);
        }
        @Override public void onNothingSelected(
          android.widget.AdapterView<?> p0
        ) {
          
        }
      });
    view = finder.findRequiredView(source, 2131296382, "field 'spinnerWhenConstruction' and method 'onSelectWhenCons'");
    target.spinnerWhenConstruction = finder.castView(view, 2131296382, "field 'spinnerWhenConstruction'");
    ((android.widget.AdapterView<?>) view).setOnItemSelectedListener(
      new android.widget.AdapterView.OnItemSelectedListener() {
        @Override public void onItemSelected(
          android.widget.AdapterView<?> p0,
          android.view.View p1,
          int p2,
          long p3
        ) {
          target.onSelectWhenCons(p2);
        }
        @Override public void onNothingSelected(
          android.widget.AdapterView<?> p0
        ) {
          
        }
      });
    view = finder.findRequiredView(source, 2131296383, "field 'spinnerDetailsConstruction' and method 'onSelectDetailCons'");
    target.spinnerDetailsConstruction = finder.castView(view, 2131296383, "field 'spinnerDetailsConstruction'");
    ((android.widget.AdapterView<?>) view).setOnItemSelectedListener(
      new android.widget.AdapterView.OnItemSelectedListener() {
        @Override public void onItemSelected(
          android.widget.AdapterView<?> p0,
          android.view.View p1,
          int p2,
          long p3
        ) {
          target.onSelectDetailCons(p2);
        }
        @Override public void onNothingSelected(
          android.widget.AdapterView<?> p0
        ) {
          
        }
      });
  }

  @Override public void unbind(T target) {
    target.bathroom = null;
    target.rooms = null;
    target.kitchen = null;
    target.slabsDecreate = null;
    target.spinnerStageOfConstruction = null;
    target.spinnerWhenConstruction = null;
    target.spinnerDetailsConstruction = null;
  }
}
