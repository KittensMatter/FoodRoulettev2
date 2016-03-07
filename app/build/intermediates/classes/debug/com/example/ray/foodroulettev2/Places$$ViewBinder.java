// Generated code from Butter Knife. Do not modify!
package com.example.ray.foodroulettev2;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class Places$$ViewBinder<T extends com.example.ray.foodroulettev2.Places> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492998, "field 'placeEditText'");
    target.placeEditText = finder.castView(view, 2131492998, "field 'placeEditText'");
    view = finder.findRequiredView(source, 2131492999, "field 'addressEditText'");
    target.addressEditText = finder.castView(view, 2131492999, "field 'addressEditText'");
    view = finder.findRequiredView(source, 2131493002, "field 'listView'");
    target.listView = finder.castView(view, 2131493002, "field 'listView'");
    view = finder.findRequiredView(source, 2131493000, "method 'addButtonOnClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.addButtonOnClick();
        }
      });
    view = finder.findRequiredView(source, 2131493001, "method 'clearButtonOnClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.clearButtonOnClick();
        }
      });
  }

  @Override public void unbind(T target) {
    target.placeEditText = null;
    target.addressEditText = null;
    target.listView = null;
  }
}
