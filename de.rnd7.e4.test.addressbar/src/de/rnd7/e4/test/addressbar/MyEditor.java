package de.rnd7.e4.test.addressbar;

import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class MyEditor {

	private final Text text;

	@Inject
	public MyEditor(final Composite parent, final MDirtyable dirtyable) {
		final GridLayout layout = new GridLayout();
		layout.marginLeft = 0;
		layout.marginRight = 0;
		layout.marginTop = 0;
		layout.marginBottom = 0;
		layout.horizontalSpacing = 0;
		layout.verticalSpacing = 5;
		parent.setLayout(layout);
		parent.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_CYAN));
		final Label label = new Label(parent, SWT.NONE);
		label.setText("This is a shared header");

		final CTabFolder folder = new CTabFolder(parent, SWT.BOTTOM);
		folder.setLayoutData(new GridData(GridData.FILL_BOTH));

		final CTabItem a = new CTabItem(folder, SWT.NONE);
		a.setText("a");

		final CTabItem b = new CTabItem(folder, SWT.NONE);
		b.setText("b");

		this.text = new Text(folder, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
		this.text.setLayoutData(new GridData(GridData.FILL_BOTH));
		this.text.addModifyListener(e -> {
			dirtyable.setDirty(true);
		});

		a.setControl(this.text);
		folder.setSelection(0);
	}

	@Persist
	public void save(final MDirtyable dirtyable) {
		dirtyable.setDirty(false);
	}

	@PreDestroy
	public void dispose() {

	}

	public void setInput(final String string) {
		this.text.setText(string);
	}

}
