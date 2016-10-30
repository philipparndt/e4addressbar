package de.rnd7.e4.test.addressbar;

import java.util.UUID;

import javax.inject.Inject;

import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class MyView {
	@Inject
	public MyView(final Composite parent, final MApplication application, final EPartService partService,
			final EModelService modelService) {

		parent.setLayout(new GridLayout());

		final Button button = new Button(parent, SWT.PUSH);
		button.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
		button.setText("Open editor");
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				final MPart part = partService.createPart("de.rnd7.e4.test.addressbar.partdescriptor.myeditor");
				part.setLabel("New Dynamic Editor");

				final MPartStack stack = (MPartStack) modelService.find("de.rnd7.eclipse4.test.partstack.editor",
						application);

				if (stack != null && stack.isVisible()) {
					stack.getChildren().add(part);
					stack.setSelectedElement(part);
				} else {
					partService.showPart(part, PartState.ACTIVATE);
				}

				final MyEditor editor = (MyEditor) part.getObject();
				if (editor != null) {
					editor.setInput(UUID.randomUUID().toString());
				}
			}
		});

	}
}
