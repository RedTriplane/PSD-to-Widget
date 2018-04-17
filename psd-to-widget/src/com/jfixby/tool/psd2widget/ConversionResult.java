
package com.jfixby.tool.psd2widget;

import com.jfixby.psd.unpacker.api.PSDLayer;
import com.jfixby.r3.widget.io.WidgetStructure;
import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.names.ID;

public class ConversionResult {

	Map<WidgetStructure, WidgetPackingResult> requred_raster = Collections.newMap();

	Map<PSDLayer, WidgetPackingResult> ancestors = Collections.newMap();

	public Collection<ID> listAllRequredAssets () {
		final List<ID> list = Collections.newList();

		for (int i = 0; i < this.requred_raster.size(); i++) {
			final WidgetPackingResult result_i = this.requred_raster.getValueAt(i);
			final List<ID> required = result_i.listRequiredAssets();
			list.addAll(required);
		}
		return list;
	}

	public void putResult (final WidgetStructure structure, final WidgetPackingResult result_i) {
		this.requred_raster.put(structure, result_i);
		final Collection<PSDLayer> anc = result_i.getAncestors();
		for (int i = 0; i < anc.size(); i++) {
			this.ancestors.put(anc.getElementAt(i), result_i);
		}

	}

	public WidgetPackingResult getStrucutreResultByLayer (final PSDLayer layer) {

		final WidgetPackingResult result = this.ancestors.get(layer);
		if (result == null) {
// this.ancestors.print("ancestors");

			L.d("layer", layer);

			Err.reportError("Layer not found");

		}
		return result;
	}

}
