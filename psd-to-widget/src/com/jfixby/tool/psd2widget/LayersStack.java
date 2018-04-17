
package com.jfixby.tool.psd2widget;

import com.jfixby.psd.unpacker.api.PSDLayer;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.strings.Strings;

public class LayersStack {
	final List<PSDLayer> stack = Collections.newList();

	public void pop (final PSDLayer input) {
		this.stack.removeLast();
	}

	public void push (final PSDLayer input) {
		this.stack.add(input);
	}

	@Override
	public String toString () {
		return Strings.wrapSequence(i -> this.stack.getElementAt(i).getName(), this.stack.size(), "", "", "/");
	}

// public void print () {
// this.stack.print("LayersStack");
// }

}
