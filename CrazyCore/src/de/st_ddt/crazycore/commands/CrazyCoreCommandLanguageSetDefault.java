package de.st_ddt.crazycore.commands;

import org.bukkit.command.CommandSender;

import de.st_ddt.crazycore.CrazyCore;
import de.st_ddt.crazyplugin.exceptions.CrazyCommandNoSuchException;
import de.st_ddt.crazyplugin.exceptions.CrazyCommandPermissionException;
import de.st_ddt.crazyplugin.exceptions.CrazyCommandUsageException;
import de.st_ddt.crazyplugin.exceptions.CrazyException;
import de.st_ddt.crazyutil.locales.CrazyLocale;
import de.st_ddt.crazyutil.locales.Localized;

public class CrazyCoreCommandLanguageSetDefault extends CrazyCoreCommandExecutor
{

	public CrazyCoreCommandLanguageSetDefault(final CrazyCore plugin)
	{
		super(plugin);
	}

	@Override
	@Localized("CRAZYCORE.COMMAND.LANGUAGE.DEFAULT.SET $LanguageName$ $Language$")
	public void command(final CommandSender sender, final String[] args) throws CrazyException
	{
		if (!sender.hasPermission("crazylanguage.advanced"))
			throw new CrazyCommandPermissionException();
		if (args.length != 1)
			throw new CrazyCommandUsageException("<Language>");
		final String language = args[0].toLowerCase();
		if (!language.matches("[a-z][a-z]_[a-z][a-z]"))
			throw new CrazyCommandNoSuchException("Language", args[0], CrazyLocale.getActiveLanguagesNames(true));
		if (!CrazyLocale.getDefaultLanguage().equals(language))
		{
			CrazyLocale.setDefaultLanguage(language);
			plugin.loadLanguageFiles(language, true);
		}
		plugin.save();
		plugin.sendLocaleMessage("COMMAND.LANGUAGE.DEFAULT.SET", sender, CrazyLocale.getLanguageName(), language);
	}
}
