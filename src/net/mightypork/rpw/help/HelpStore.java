package net.mightypork.rpw.help;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.mightypork.rpw.Config;
import net.mightypork.rpw.Paths;
import net.mightypork.rpw.utils.FileUtils;
import net.mightypork.rpw.utils.Log;
import net.mightypork.rpw.utils.SimpleConfig;


public class HelpStore {

	private static List<HelpPage> pages = new ArrayList<HelpPage>();


	public static void load() {


		Log.f2("Loading help pages");

		InputStream in;

		in = FileUtils.getResource(Paths.DATA_DIR_HELP + "index.txt");
		String text = FileUtils.streamToString(in);
		Map<String, String> pageMap = SimpleConfig.mapFromString(text);


		for (Entry<String, String> entry : pageMap.entrySet()) {

			if (Config.LOG_HELP_LOADING) Log.f3("Loading file: " + entry.getKey() + " (\"" + entry.getValue() + "\")");

			try {
				pages.add(new HelpPage(entry.getValue(), entry.getKey()));

			} catch (Exception e) {
				Log.w("Error while loading a help page " + entry.getKey());
			}

		}

		Log.f2("Loading help pages - done.");
	}


	public static List<HelpPage> getPages() {

		return pages;
	}
}
