package de.rnd7.e4.test.addressbar;

import javax.inject.Inject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class MyToolControl {
	@Inject
	public MyToolControl(final Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE) {
			@Override
			public Point computeSize(int wHint, int hHint) {
				final Point size = super.computeSize(wHint, hHint);
				
				return new Point(100, size.y);
			}
		};
		composite.setLayout(new GridLayout());
		final Text text = new Text(composite, SWT.BORDER);

		text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	}
}
