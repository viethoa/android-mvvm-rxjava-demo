package com.viethoa.mvvm.BaseApplications.utilities;

import android.content.Intent;
import android.net.Uri;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.TextView;

import com.viethoa.mvvm.BaseApplications.BaseApplication;

/**
 * Created by VietHoa on 12/01/16.
 */
public class TextViewUtils {

    public static TextView makeClickableTextView(TextView textView) {
        textView.setLinksClickable(true);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        CharSequence charSequence = textView.getText();
        SpannableStringBuilder sp = new SpannableStringBuilder(charSequence);
        URLSpan[] spans = sp.getSpans(0, charSequence.length(), URLSpan.class);
        for (URLSpan urlSpan : spans) {
            MySpan mySpan = new MySpan(urlSpan.getURL());
            sp.setSpan(mySpan, sp.getSpanStart(urlSpan),
                    sp.getSpanEnd(urlSpan), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        }
        textView.setText(sp);

        return textView;
    }

    private static class MySpan extends ClickableSpan {
        private String mUrl;

        public MySpan(String url) {
            super();
            mUrl = url;
        }

        @Override
        public void onClick(View widget) {
            if (!mUrl.startsWith("http://") && !mUrl.startsWith("https://"))
                mUrl = "http://" + mUrl;

            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mUrl));
            browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            BaseApplication.getApplication().startActivity(browserIntent);
        }
    }
}
