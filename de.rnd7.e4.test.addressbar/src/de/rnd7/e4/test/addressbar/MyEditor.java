package de.rnd7.e4.test.addressbar;

import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class MyEditor {

	@Inject
	public MyEditor(final Composite parent, final MDirtyable dirtyable) {
		final Text text = new Text(parent, SWT.BORDER | SWT.MULTI);
		text.setLayoutData(new GridData(GridData.FILL));
		text.addModifyListener(e -> {
			dirtyable.setDirty(true);
		});
	}

	@Persist
	public void save(final MDirtyable dirtyable) {
		dirtyable.setDirty(false);
	}

	@PreDestroy
	public void dispose() {

	}

}
