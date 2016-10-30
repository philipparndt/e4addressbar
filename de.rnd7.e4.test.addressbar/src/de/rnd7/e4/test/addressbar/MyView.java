package de.rnd7.e4.test.addressbar;

import javax.inject.Inject;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

public class MyView {
	@Inject
	public MyView(final Composite parent) {
		parent.setLayout(new GridLayout());
	}
}
