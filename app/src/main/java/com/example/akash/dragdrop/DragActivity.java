package com.example.akash.dragdrop;

import android.content.ClipData;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DragActivity extends AppCompatActivity implements View.OnDragListener, View.OnTouchListener{

  @BindView(R.id.tvScreen3) TextView tvScreen3;
  @BindView(R.id.topleft) RelativeLayout topleft;
  @BindView(R.id.tvScreen4) TextView tvScreen4;
  @BindView(R.id.topmid) RelativeLayout topmid;
  @BindView(R.id.tvScreen5) TextView tvScreen5;
  @BindView(R.id.topend) RelativeLayout topend;
  @BindView(R.id.tvScreen6) TextView tvScreen6;
  @BindView(R.id.midleft) RelativeLayout midleft;
  @BindView(R.id.tvScreen0) TextView tvScreen0;
  @BindView(R.id.midmid) RelativeLayout midmid;
  @BindView(R.id.tvScreen2) TextView tvScreen2;
  @BindView(R.id.midend) RelativeLayout midend;
  @BindView(R.id.tvScreen7) TextView tvScreen7;
  @BindView(R.id.endleft) RelativeLayout endleft;
  @BindView(R.id.tvScreen8) TextView tvScreen8;
  @BindView(R.id.endmid) RelativeLayout endmid;
  @BindView(R.id.tvScreen9) TextView tvScreen9;
  @BindView(R.id.endend) RelativeLayout endend;

  private boolean firstTimeDrop= true;
  private View pv= null;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_drag);
    ButterKnife.bind(this);
    topleft.setOnTouchListener(this);
    topmid.setOnTouchListener(this);
    topend.setOnTouchListener(this);
    midleft.setOnTouchListener(this);
    midmid.setOnTouchListener(this);
    midend.setOnTouchListener(this);
    endleft.setOnTouchListener(this);
    endmid.setOnTouchListener(this);
    endend.setOnTouchListener(this);
    topleft.setOnDragListener(this);
    topmid.setOnDragListener(this);
    topend.setOnDragListener(this);
    midleft.setOnDragListener(this);
    midmid.setOnDragListener(this);
    midend.setOnDragListener(this);
    endleft.setOnDragListener(this);
    endmid.setOnDragListener(this);
    endend.setOnDragListener(this);
  }

  @Override public boolean onDrag(View view, DragEvent event) {
    int action = event.getAction();
    Drawable normal= getResources().getDrawable(
        R.drawable.green);
    Drawable d;
    Drawable red= getResources().getDrawable(R.drawable.red_border);
    TypedValue a = new TypedValue();
    getTheme().resolveAttribute(android.R.attr.windowBackground, a, true);
    if (a.type >= TypedValue.TYPE_FIRST_COLOR_INT && a.type <= TypedValue.TYPE_LAST_COLOR_INT) {
      // windowBackground is a color
       d = new ColorDrawable(a.data);
    } else {
      // windowBackground is not a color, probably a drawable
      d = getResources().getDrawable(a.resourceId);
    }
    switch (action) {
      case DragEvent.ACTION_DRAG_STARTED:
        topmid.setBackground(red);
        midleft.setBackground(red);
        endmid.setBackground(red);
        break;
      case DragEvent.ACTION_DRAG_ENTERED:
        break;
      case DragEvent.ACTION_DRAG_EXITED:
        break;
      case DragEvent.ACTION_DROP:
        View v;

        if(firstTimeDrop) {
           v = (View) event.getLocalState();
           firstTimeDrop= false;
        }else {
          v=pv;
        }

        if(v!= null && view !=null && v.getId() != view.getId()) {
          ViewGroup owner = (ViewGroup) view.getParent();
          owner.removeView(v);

          RelativeLayout container = (RelativeLayout) view;
          container.addView(v);
          v.setVisibility(View.VISIBLE);
        }
        pv= view;
        break;
      case DragEvent.ACTION_DRAG_ENDED:
        topmid.setBackground(d);
        midleft.setBackground(d);
        endmid.setBackground(d);
        break;
      default:
        break;
    }
    return true;
  }

  @Override public boolean onTouch(View view, MotionEvent motionEvent) {
    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
      ClipData data = ClipData.newPlainText("", "");
      View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
          view);
      view.startDrag(data, shadowBuilder, view, 0);
      return true;
    } else {
      return false;
    }
  }
}
