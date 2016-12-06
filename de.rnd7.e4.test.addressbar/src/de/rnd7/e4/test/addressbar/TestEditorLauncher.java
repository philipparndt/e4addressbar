package de.rnd7.e4.test.addressbar;

import java.util.UUID;

import javax.inject.Inject;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;

class TestEditorLauncher {
	private final EPartService partService;

	@Inject
	public TestEditorLauncher(final EPartService partService) {
		this.partService = partService;
	}

	public void launchNewEditor() {
		final MPart part = this.partService.createPart("de.rnd7.e4.test.addressbar.partdescriptor.myeditor");
		part.setLabel("New Dynamic Editor");
		this.partService.showPart(part, PartState.ACTIVATE);

		final MyEditor editor = (MyEditor) part.getObject();
		if (editor != null) {
			editor.setInput(UUID.randomUUID().toString());
		}
	}
}
