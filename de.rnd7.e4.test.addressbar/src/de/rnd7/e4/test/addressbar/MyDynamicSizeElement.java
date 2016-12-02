package de.rnd7.e4.test.addressbar;

import javax.inject.Inject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class MyDynamicSizeElement {
	
	private static final int SIZE_A = 100;
	private static final int SIZE_B = 200;
	
	private int size = SIZE_A;
	@Inject
	public MyDynamicSizeElement(final Composite parent) {
		
		Button button = new Button(parent, SWT.PUSH){
			@Override
			protected void checkSubclass() {
			}
			
			@Override
			public Point computeSize(int wHint, int hHint) {
				final Point size = super.computeSize(wHint, hHint);
				
				return new Point(MyDynamicSizeElement.this.size, size.y);
			}
			
		};
		button.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
		button.setText("Change size");
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (size == SIZE_A) {
					size = SIZE_B;
				}
				else {
					size = SIZE_A;
				}
				
				parent.layout();
			}
		});
	}
}
