package de.st_ddt.crazyplugin.commands;

import org.bukkit.command.CommandSender;

import de.st_ddt.crazyplugin.CrazyPluginInterface;
import de.st_ddt.crazyplugin.exceptions.CrazyCommandPermissionException;
import de.st_ddt.crazyplugin.exceptions.CrazyCommandUsageException;
import de.st_ddt.crazyplugin.exceptions.CrazyException;
import de.st_ddt.crazyutil.locales.Localized;

public class CrazyPluginCommandMainReload extends CrazyCommandExecutor<CrazyPluginInterface>
{

	public CrazyPluginCommandMainReload(final CrazyPluginInterface plugin)
	{
		super(plugin);
	}

	@Override
	@Localized("CRAZYPLUGIN.COMMAND.CONFIG.RELOADED")
	public void command(final CommandSender sender, final String[] args) throws CrazyException
	{
		if (!sender.hasPermission(plugin.getName().toLowerCase() + ".reload"))
			throw new CrazyCommandPermissionException();
		if (args.length != 0)
			throw new CrazyCommandUsageException("");
		plugin.reloadConfig();
		plugin.loadConfiguration();
		plugin.sendLocaleMessage("COMMAND.CONFIG.RELOADED", sender);
	}
}
