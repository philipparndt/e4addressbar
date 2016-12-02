package de.rnd7.e4.test.addressbar;

import org.eclipse.swt.layout.GridLayout;

/**
 * @author vispat
 *
 */
final class LayoutUtil {

	private LayoutUtil() {
		// Make sure that utility classes (classes that contain only static methods) do not have a public constructor.
	}

	public static GridLayout getBorderless(final GridLayout layout) {
		GridLayout result = removeSpacing(layout);
		result = removeMargin(result);

		return result;
	}

	public static GridLayout removeSpacing(final GridLayout layout) {
		layout.verticalSpacing = layout.horizontalSpacing = 0;

		return layout;
	}

	/**
	 * You should normally use this layout to create a inner composite
	 *
	 * @param layout
	 * @return
	 */
	public static GridLayout removeMargin(final GridLayout layout) {
		layout.marginWidth = layout.marginHeight = 0;
		layout.marginTop = layout.marginBottom = 0;
		layout.marginLeft = layout.marginRight = 0;

		return layout;
	}

}
