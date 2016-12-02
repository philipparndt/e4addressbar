package de.rnd7.e4.test.addressbar;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

public class MyAction {

	@Execute
	public void run(Shell parent) {
		MessageDialog.openInformation(parent, "test", "test message");
	}
	
}
