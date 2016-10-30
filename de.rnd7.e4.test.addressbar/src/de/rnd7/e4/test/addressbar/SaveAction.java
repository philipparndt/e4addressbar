package de.rnd7.e4.test.addressbar;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

public class SaveAction {
	private Optional<MPart> part = Optional.empty();

	public SaveAction() {

	}

	@Inject
	public void setActivePart(
			@org.eclipse.e4.core.di.annotations.Optional @Named(IServiceConstants.ACTIVE_PART) final MPart part) {
		this.part = Optional.ofNullable(part);
	}

	@Execute
	public void run(final EPartService partService) {
		partService.savePart(this.part.get(), false);
	}

	@CanExecute
	public boolean isEnabled() {
		return this.part.map(MDirtyable::isDirty).orElse(false);
	}

}
