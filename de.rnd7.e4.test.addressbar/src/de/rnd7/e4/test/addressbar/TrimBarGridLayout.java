package de.rnd7.e4.test.addressbar;

import java.lang.reflect.Method;

import org.eclipse.e4.ui.workbench.renderers.swt.TrimBarLayout;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;

@SuppressWarnings("restriction")
class TrimBarGridLayout extends TrimBarLayout {

	private GridLayout layout;

	TrimBarGridLayout() {
		super(true);
	}

	private GridLayout initLayout(final Control control) {
		final Control[] children;
		if (control instanceof Composite) {
			children = ((Composite) control).getChildren();
		} else {
			children = new Control[0];
		}

		if (this.layout == null || children.length != this.layout.numColumns) {
			this.layout = new GridLayout(children.length, false);
		}

		return this.layout;
	}

	@Override
	protected void layout(final Composite composite, final boolean flushCache) {
		try {
			final Method method = Layout.class.getDeclaredMethod("layout", Composite.class, boolean.class);
			method.setAccessible(true);
			method.invoke(this.initLayout(composite), composite, flushCache);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean flushCache(final Control control) {
		try {
			final Method method = GridLayout.class.getDeclaredMethod("flushCache", Control.class);
			method.setAccessible(true);
			return (boolean) method.invoke(this.initLayout(control), control);
		} catch (final Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	protected Point computeSize(final Composite composite, final int wHint, final int hHint, final boolean flushCache) {
		try {
			final Method method = GridLayout.class.getDeclaredMethod("computeSize", Composite.class, int.class,
					int.class, boolean.class);
			method.setAccessible(true);
			return (Point) method.invoke(this.initLayout(composite), composite, wHint, hHint, flushCache);
		} catch (final Exception e) {
			e.printStackTrace();
			return new Point(wHint, hHint);
		}
	}

}
