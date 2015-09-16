// Generated code from Butter Knife. Do not modify!
package fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class FourthFragmentForm$$ViewBinder<T extends fragment.FourthFragmentForm> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296368, "field 'editTextEmailId'");
    target.editTextEmailId = finder.castView(view, 2131296368, "field 'editTextEmailId'");
    view = finder.findRequiredView(source, 2131296369, "field 'editTextOwnerName'");
    target.editTextOwnerName = finder.castView(view, 2131296369, "field 'editTextOwnerName'");
    view = finder.findRequiredView(source, 2131296370, "field 'editTextOwnerContact'");
    target.editTextOwnerContact = finder.castView(view, 2131296370, "field 'editTextOwnerContact'");
  }

  @Override public void unbind(T target) {
    target.editTextEmailId = null;
    target.editTextOwnerName = null;
    target.editTextOwnerContact = null;
  }
}
