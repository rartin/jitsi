/*
 * SIP Communicator, the OpenSource Java VoIP and Instant Messaging client.
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package net.java.sip.communicator.impl.protocol.gibberish;

import java.awt.image.*;
import java.io.*;
import java.net.*;
import java.util.*;

import javax.imageio.*;
import javax.imageio.stream.*;

import net.java.sip.communicator.impl.gui.utils.*;
import net.java.sip.communicator.impl.gui.utils.ImageLoader.*;
import net.java.sip.communicator.service.protocol.*;
import net.java.sip.communicator.util.*;

/**
 * Reperesents the Gibberish protocol icon. Implements the <tt>ProtocolIcon</tt>
 * interface in order to provide a gibberish logo image in two different sizes.
 * 
 * @author Yana Stamcheva
 */
public class ProtocolIconGibberishImpl
    implements ProtocolIcon
{
    private static Logger logger
        = Logger.getLogger(ProtocolIconGibberishImpl.class); 
    
    /**
     * A hash table containing the protocol icon in different sizes.
     */
    private static Hashtable iconsTable = new Hashtable();
    static {
        iconsTable.put(ProtocolIcon.ICON_SIZE_16x16,    
            loadIcon("resources/images/protocol/gibberish/gibberish-online.png"));

        iconsTable.put(ProtocolIcon.ICON_SIZE_64x64,
            loadIcon("resources/images/protocol/gibberish/gibberish64x64.png"));
    }
        
    /**
     * Implements the <tt>ProtocolIcon.getSupportedSizes()</tt> method. Returns
     * an iterator to a set containing the supported icon sizes.
     * @return an iterator to a set containing the supported icon sizes
     */
    public Iterator getSupportedSizes()
    {
        return iconsTable.keySet().iterator();
    }

    /**
     * Returne TRUE if a icon with the given size is supported, FALSE-otherwise.
     */
    public boolean isSizeSupported(String iconSize)
    {
        return iconsTable.containsKey(iconSize);
    }
    
    /**
     * Returns the icon image in the given size.
     * @param iconSize the icon size; one of ICON_SIZE_XXX constants
     */
    public byte[] getIcon(String iconSize)
    {
        return (byte[])iconsTable.get(iconSize);
    }
    
    /**
     * Returns the icon image used to represent the protocol connecting state.
     * @return the icon image used to represent the protocol connecting state
     */
    public byte[] getConnectingIcon()
    {
        return loadIcon("resources/images/protocol/gibberish/gibberish-online.png");
    }
    
    /**
     * Loads an image from a given image path.
     * @param imagePath The identifier of the image.
     * @return The image for the given identifier.
     */
    public static byte[] loadIcon(String imagePath) {
        InputStream is = ProtocolIconGibberishImpl.class
            .getClassLoader().getResourceAsStream(imagePath);
        
        byte[] icon = null;
        try {
            icon = new byte[is.available()];
            is.read(icon);
        } catch (IOException e) {
            logger.error("Failed to load icon: " + imagePath, e);
        }
        return icon;
    }
}
