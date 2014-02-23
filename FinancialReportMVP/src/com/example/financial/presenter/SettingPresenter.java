package com.example.financial.presenter;

import com.example.financial.view.ILoginView;
import com.example.financial.view.ISettingView;

public class SettingPresenter {
	private final ISettingView setview;

	public SettingPresenter(ISettingView v) {
		setview = v;
	}

	public void onAccountClick() {
		setview.goToAccount();
	}

	public void onLogoutClick() {
		setview.logout();
	}
}
