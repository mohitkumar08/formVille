// Generated code from Butter Knife. Do not modify!
package mobifly.bit.survey;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MainActivity$$ViewBinder<T extends mobifly.bit.survey.MainActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296348, "field 'fab' and method 'startSurvey'");
    target.fab = finder.castView(view, 2131296348, "field 'fab'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.startSurvey();
        }
      });
    view = finder.findRequiredView(source, 2131296347, "field 'recyclerView'");
    target.recyclerView = finder.castView(view, 2131296347, "field 'recyclerView'");
  }

  @Override public void unbind(T target) {
    target.fab = null;
    target.recyclerView = null;
  }
}
