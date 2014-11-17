package neresources.api;

import cpw.mods.fml.common.Loader;
import neresources.api.entry.IBaseEntry;
import neresources.api.entry.IOreEntry;
import neresources.api.utils.KeyGen;

import java.util.LinkedHashMap;
import java.util.Map;

public class NEResourcesAPI
{
    private static Map<String,IBaseEntry> registryAPI = new LinkedHashMap<String, IBaseEntry>();

    public static boolean registerEntry(IBaseEntry entry)
    {
        String key = Loader.instance().activeModContainer().getModId()+":"+ entry.getKey();
        if (key == null || key == "") return false;
        if (registryAPI.containsKey(key)) return false;
        registryAPI.put(key,entry);
        return true;
    }

    public static Map<String,IBaseEntry> getRegistryAPI()
    {
        return registryAPI;
    }
}
