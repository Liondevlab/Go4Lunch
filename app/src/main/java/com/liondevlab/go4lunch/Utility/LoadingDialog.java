package com.liondevlab.go4lunch.Utility;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import androidx.fragment.app.Fragment;

import com.liondevlab.go4lunch.R;
import com.liondevlab.go4lunch.databinding.DialogLayoutBinding;

/**
 * Go4Lunch
 * Created by LioNDeVLaB on 18/11/2021
 */
public class LoadingDialog {

	private Activity activity;
	private AlertDialog alertDialog;

	public LoadingDialog(Fragment fragment) {
		this.activity = activity;
	}

	public void startLoading() {
		AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.loadingDialogStyle);
		DialogLayoutBinding binding = DialogLayoutBinding.inflate(LayoutInflater.from(activity), null, false);
		builder.setView(binding.getRoot());
		builder.setCancelable(false);
		alertDialog = builder.create();
		binding.rotateLoading.start();
		alertDialog.show();
	}

	public void stopLoading() {
		alertDialog.dismiss();
	}
}
