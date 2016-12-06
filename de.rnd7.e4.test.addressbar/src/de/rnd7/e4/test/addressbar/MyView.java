package de.rnd7.e4.test.addressbar;

import javax.inject.Inject;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class MyView {
	@Inject
	public MyView(final Composite parent, final IEclipseContext context) {

		parent.setLayout(new GridLayout());

		final Button button = new Button(parent, SWT.PUSH);
		button.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
		button.setText("Open editor");
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				final TestEditorLauncher launcher = ContextInjectionFactory.make(TestEditorLauncher.class, context);
				launcher.launchNewEditor();
			}
		});


	}
}
