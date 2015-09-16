// Generated code from Butter Knife. Do not modify!
package fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class SixFragmentForm$$ViewBinder<T extends fragment.SixFragmentForm> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296380, "field 'submitData' and method 'submitData'");
    target.submitData = finder.castView(view, 2131296380, "field 'submitData'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.submitData();
        }
      });
    view = finder.findRequiredView(source, 2131296378, "field 'imageView' and method 'captureImage'");
    target.imageView = finder.castView(view, 2131296378, "field 'imageView'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.captureImage();
        }
      });
    view = finder.findRequiredView(source, 2131296376, "field 'spinnerSurveyor' and method 'onSelectDetailCons'");
    target.spinnerSurveyor = finder.castView(view, 2131296376, "field 'spinnerSurveyor'");
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
    view = finder.findRequiredView(source, 2131296377, "field 'remarks'");
    target.remarks = finder.castView(view, 2131296377, "field 'remarks'");
  }

  @Override public void unbind(T target) {
    target.submitData = null;
    target.imageView = null;
    target.spinnerSurveyor = null;
    target.remarks = null;
  }
}
