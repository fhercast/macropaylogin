package com.fdac.macropayloginexample.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.fdac.macropayloginexample.R;

public class dialo_messages {
    Context context;


    public dialo_messages(Context context_) {
        context=context_;
    }
    public interface DialogCallback
    {
        void onSuccessResponse(String result);
        void onCancelResponse(String e);
    }

    public Dialog Loader(String msg)
    {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_loader);
        LottieAnimationView anim_progress = dialog.findViewById(R.id.anim_progress);
        TextView txtmsg = dialog.findViewById(R.id.txtmsg);
        txtmsg.setText(msg);
        anim_progress.setAnimation(R.raw.loader);
        anim_progress.loop(true);
        anim_progress.playAnimation();
        dialog.setCancelable(false);
        //AlertDialog dialog_progress = globals.generateProgressDialog(context,msg,"",false);

        return dialog;
    }


}
