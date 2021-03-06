package com.cxy.redisclient.presentation.hash;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TableItem;

import com.cxy.redisclient.integration.I18nFile;
import com.cxy.redisclient.presentation.RedisClient;
import com.cxy.redisclient.presentation.component.TTLTabItem;
import com.cxy.redisclient.presentation.component.UpdateTTLTabItem;

public class UpdateHashDialog extends NewHashDialog {
	private Map<String, String> value;
	private int id;
	
	public UpdateHashDialog(Shell parent, Image image, int id, String server, int db,
			String key, Map<String , String> value) {
		super(parent, image, server, db, key);
		this.id = id;
		this.value = value;
	}

	@Override
	protected void createContents() {
		super.createContents();
		shell.setText(RedisClient.i18nFile.getText(I18nFile.HASHPROPERTY)+": "+key);
		text.setEditable(false);
		text.removeModifyListener(new ModifyKey());
		
		btnOk.setEnabled(true);
		
		Set<Entry<String, String>> set = value.entrySet();
		Iterator<Entry<String, String>> i = set.iterator();
		
		while(i.hasNext()) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>) i.next();
			TableItem item = new TableItem(table, SWT.NONE);
			String[] values = new String[]{entry.getKey(), entry.getValue()};
			
			item.setText(values);
		}
	}
	
	protected TTLTabItem getTTLTabItem(TabFolder tabFolder) {
		return new UpdateTTLTabItem(tabFolder, id, db, key);
	}

}
