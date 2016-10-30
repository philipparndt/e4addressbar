package de.rnd7.e4.test.addressbar;

import javax.inject.Inject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class MyToolControl {
	@Inject
	public MyToolControl(final Composite parent) {
		parent.setLayout(new TrimBarGridLayout());

		final Text text = new Text(parent, SWT.BORDER);
		text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	}
}
