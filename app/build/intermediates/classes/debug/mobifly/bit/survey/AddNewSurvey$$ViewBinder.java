// Generated code from Butter Knife. Do not modify!
package mobifly.bit.survey;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class AddNewSurvey$$ViewBinder<T extends mobifly.bit.survey.AddNewSurvey> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296343, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131296343, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131296345, "field 'previous' and method 'setPrevious'");
    target.previous = finder.castView(view, 2131296345, "field 'previous'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.setPrevious(p0);
        }
      });
    view = finder.findRequiredView(source, 2131296344, "field 'next' and method 'setNext'");
    target.next = finder.castView(view, 2131296344, "field 'next'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.setNext(p0);
        }
      });
  }

  @Override public void unbind(T target) {
    target.toolbar = null;
    target.previous = null;
    target.next = null;
  }
}
