package de.rnd7.e4.test.addressbar;

import java.util.Optional;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

final class TabFolderToolbarManager {

	private TabFolderToolbarManager() {

	}

	static void install(final CTabFolder folder, final IToolBarManager manager, final Optional<ToolbarCreator> globalActions) {
		updateToolbar(manager, folder, globalActions);
		folder.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				updateToolbar(manager, folder, globalActions);
			}
		});
	}

	private static void updateToolbar(final IToolBarManager manager, final CTabFolder folder, final Optional<ToolbarCreator> globalActions) {
		final CTabItem tabItem = folder.getSelection();

		manager.removeAll();

		globalActions.ifPresent(ga -> ga.createActions(manager));

		if ((tabItem != null) && (tabItem.getData() instanceof ToolbarCreator)) {
			manager.add(new Separator());

			final ToolbarCreator creator = (ToolbarCreator) tabItem.getData();
			creator.createActions(manager);
		}

		manager.update(true);
	}
}
