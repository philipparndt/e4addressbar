package de.rnd7.e4.test.addressbar;

import java.util.Optional;

import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.ToolBarManager;
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
		final Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(LayoutUtil.removeMargin(new GridLayout()));

		final Composite headerComposite = new Composite(composite, SWT.NONE);
		headerComposite.setLayout(new GridLayout(2, false));
		headerComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		final Label label = new Label(headerComposite, SWT.NONE);
		label.setText("This is a shared header");
		label.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		final ToolBarManager manager = new ToolBarManager();
		manager.createControl(headerComposite);

		final CTabFolder folder = new CTabFolder(composite, SWT.BOTTOM);
		folder.setLayoutData(new GridData(GridData.FILL_BOTH));
		folder.setTabHeight(20);

		final CTabItem a = new CTabItem(folder, SWT.NONE);
		a.setText("Page A");
		a.setData(new ToolbarCreator() {
			@Override
			public void createActions(final IToolBarManager manager) {
				manager.add(new Action("Action for page a") {
				});
			}
		});

		final CTabItem b = new CTabItem(folder, SWT.NONE);
		b.setText("Page B");

		this.text = new Text(folder, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
		this.text.setLayoutData(new GridData(GridData.FILL_BOTH));
		this.text.addModifyListener(e -> {
			dirtyable.setDirty(true);
		});

		a.setControl(this.text);

		folder.setSelection(0);

		TabFolderToolbarManager.install(folder, manager, Optional.of(this::createActions));
	}

	public void createActions(final IToolBarManager manager) {
		manager.add(new Action("My global action") {
		});
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
