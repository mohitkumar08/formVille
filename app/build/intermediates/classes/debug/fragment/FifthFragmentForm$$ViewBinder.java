// Generated code from Butter Knife. Do not modify!
package fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class FifthFragmentForm$$ViewBinder<T extends fragment.FifthFragmentForm> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296360, "field 'builderName'");
    target.builderName = finder.castView(view, 2131296360, "field 'builderName'");
    view = finder.findRequiredView(source, 2131296361, "field 'builderContact'");
    target.builderContact = finder.castView(view, 2131296361, "field 'builderContact'");
    view = finder.findRequiredView(source, 2131296362, "field 'architectName'");
    target.architectName = finder.castView(view, 2131296362, "field 'architectName'");
    view = finder.findRequiredView(source, 2131296363, "field 'architectContact'");
    target.architectContact = finder.castView(view, 2131296363, "field 'architectContact'");
  }

  @Override public void unbind(T target) {
    target.builderName = null;
    target.builderContact = null;
    target.architectName = null;
    target.architectContact = null;
  }
}
